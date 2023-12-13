/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import ch.qos.logback.classic.pattern.Util;
import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.BtValidacionSms;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatConfiguracionesServiciosExternos;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.RequestValidarCodigoSms;
import com.mx.ca.viu.modelos.dtos.response.EnvioSmsResponse;
import com.mx.ca.viu.modelos.dtos.response.ValidacionSmsResponse;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.repositorys.ValidacionSmsRepository;
import com.mx.ca.viu.services.ValidacionSmsServices;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("validacionSmsServices")
public class ValidacionSmsServicesImpl implements ValidacionSmsServices {

    @Autowired
    GenericoRepository GenericoRepository;
    @Autowired
    ValidacionSmsRepository validacionSmsRepository;

    @Override
    public EnvioSmsResponse enviarCodigoValidacion(String numeroEnvio, String curp) {
        EnvioSmsResponse respuesta = new EnvioSmsResponse();
        try {
            CatServiciosValidacionesExternos config = GenericoRepository.findByID(CatServiciosValidacionesExternos.class, 17L);
            Twilio.init(config.getCatConfiguracionesServiciosExternosList().get(0).getUser(), config.getCatConfiguracionesServiciosExternosList().get(0).getPass());
            int codigo = UtilGenerico.getFiveDigitsNumber();
            //proceso de busqueda de ping
            String nip = validacionSmsRepository.obtenPin(curp);

            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(numeroEnvio),
                    new com.twilio.type.PhoneNumber(config.getCatConfiguracionesServiciosExternosList().get(0).getUrl()),
                    "codigo de verificacion amextra:" + codigo + " NIP registrado:" + nip)
                    .create();
//            Message message = Message.creator(
//                    new com.twilio.type.PhoneNumber(numeroEnvio),
//                    new com.twilio.type.PhoneNumber(config.getCatConfiguracionesServiciosExternosList().get(0).getUrl()),
//                    "Está recibiendo este código de validación por parte de AMEXTRA, ya que se encuentra en un proceso de solicitud de crédito.Si es correcto, ingrese el codigo recibido y seleccione si autoriza a AMEXTRA la consulta a su historial de crédito en el teléfono del asesor:" + codigo)
//                    .create();
            System.out.println("estatus del sms: " + message.getStatus());
            validacionSmsRepository.desabilitaTodos(numeroEnvio);
            BtValidacionSms bitacora = new BtValidacionSms();
            bitacora.setCodigoVerificacion(String.valueOf(codigo));
            bitacora.setFechaEnvio(UtilGenerico.obtenerHoraMexico());
            bitacora.setNumeroEnvio(numeroEnvio);
            bitacora.setStatus(Boolean.FALSE);
            GenericoRepository.guardar(bitacora);

            respuesta.getResponse().setCodigo(200);
            respuesta.getResponse().setMensaje("OK");
        } catch (Exception e) {

            if (e.getMessage().contains("Authenticate")) {
                respuesta.getResponse().setMensaje("Credito insuficiente para servicio sms, contacte al administrador");

            } else {
                respuesta.getResponse().setMensaje(e.getMessage());

            }
            System.out.println("error:" + e.getMessage());

            respuesta.getResponse().setCodigo(500);

        }
        return respuesta;

    }

    @Override
    public ValidacionSmsResponse validarCodigo(RequestValidarCodigoSms request) {

        ValidacionSmsResponse response = new ValidacionSmsResponse();
        response.setData(new ValidacionSmsResponse.Data());
        response.setResponse(new GenericResponse.Response());
        try {
            BtValidacionSms respuesta = validacionSmsRepository.validarCodigo(request.getData().getNumeroEnvio(), request.getData().getCodigo());
            CatClientes cliente = validacionSmsRepository.buscarClienteTelefono(request.getData().getNumeroEnvio().replace("+52", ""));
            String nip=validacionSmsRepository.validarPin(request.getData().getCurp(), request.getData().getNip());
            if(nip.contains("ERROR")){
                 response.getResponse().setCodigo(500);
                response.getResponse().setMensaje(nip);
                return response;
            }
            if (cliente == null) {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("NO existe un cliente asociado a este numero de telefono:" + request.getData().getNumeroEnvio());

            } else {
                if(nip.contains("ERROR")){
                    cliente.setAproboNip(false);
                }else{
                    cliente.setAproboNip(true);
                }
                if (respuesta != null) {
                    respuesta.setStatus(Boolean.TRUE);
                    GenericoRepository.update(respuesta);
                    response.getData().setValidado(Boolean.TRUE);

                    cliente.setAproboVerificacionSms(Boolean.TRUE);

                } else {

                    cliente.setAproboVerificacionSms(Boolean.FALSE);
                    response.getData().setValidado(Boolean.FALSE);
                }
                GenericoRepository.update(cliente);
                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");
            }

        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }

        return response;

    }

}
