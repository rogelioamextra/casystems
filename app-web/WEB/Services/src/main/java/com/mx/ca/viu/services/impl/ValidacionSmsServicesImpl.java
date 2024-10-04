/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import ch.qos.logback.classic.pattern.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.BtValidacionSms;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatConfiguracionesServiciosExternos;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DtValidacionPinAval;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.RequestBodySms;
import com.mx.ca.viu.modelos.dtos.request.RequestEnvioSms;
import com.mx.ca.viu.modelos.dtos.request.RequestValidarCodigoSms;
import com.mx.ca.viu.modelos.dtos.response.EnvioSmsResponse;
import com.mx.ca.viu.modelos.dtos.response.ResponseSms;
import com.mx.ca.viu.modelos.dtos.response.ValidacionSmsResponse;
import com.mx.ca.viu.repositorys.DtValidacionPinAvalRepository;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.repositorys.ValidacionSmsRepository;
import com.mx.ca.viu.services.ValidacionSmsServices;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.Date;
import java.util.List;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    private final String key = "e3e0a1f2c4155d8295fc3e1de3022c180178337a";

    private final String BASE_URL = "https://api.smsmasivos.com.mx";

    private static final Logger logger = LogManager.getLogger(ValidacionSmsServicesImpl.class);

    @Override
    public EnvioSmsResponse enviarCodigoValidacion(RequestEnvioSms request) {
        EnvioSmsResponse respuesta = new EnvioSmsResponse();
        try {

            String nip = "";
            String msg = "";
            // CatServiciosValidacionesExternos config = GenericoRepository.findByID(CatServiciosValidacionesExternos.class, 17L);
            //Twilio.init(config.getCatConfiguracionesServiciosExternosList().get(0).getUser(), config.getCatConfiguracionesServiciosExternosList().get(0).getPass());
            int codigo = UtilGenerico.getFiveDigitsNumber();
            
              if(request.getData().isIsAval()){
              Date todaDate = new Date();
              DtValidacionPinAval aval = new DtValidacionPinAval();
              aval.setCurp(request.getData().getCurp());
              aval.setStatusPin(false);
              aval.setFechaValidacion(todaDate);
              aval.setPin(String.valueOf(codigo));
              aval.setTelefono(request.getData().getNumeroEnvio());
              GenericoRepository.guardar(aval);
            }
            
            

            if (!request.getData().isIsAval()) {

                nip = validacionSmsRepository.obtenPin(request.getData().getCurp());

            }

            logger.error("isAval: " + request.getData().isIsAval());
            logger.error("nip final:  " + nip);
            if (!request.getData().isIsAval()) {
                msg = "Codigo de verificacion cliente amextra: " + codigo + " NIP registrado: " + nip;

            } else {
                String ns = validacionSmsRepository.obtenPinAval(request.getData().getCurp());
                
                logger.error("pin aval from db "+ns);

                msg = "Codigo de verificacion aval amextra: " + ns;

            }
            
            
            boolean credito = valdiaCreditoDisponible();
            logger.error("res credito "+ credito);
            //proceso de busqueda de ping
            if (credito) {

                RequestBodySms req = new RequestBodySms();
                req.setPhoneNumber(request.getData().getNumeroEnvio());
                req.setMessage(msg);
                logger.error("require sms " + req.toString());
                ResponseSms resSms = sendMasiveSms(req);
                logger.error(resSms.toString());

                if (!request.getData().isIsAval()) {
                    validacionSmsRepository.desabilitaTodos(request.getData().getNumeroEnvio());
                    BtValidacionSms bitacora = new BtValidacionSms();
                    bitacora.setCodigoVerificacion(String.valueOf(codigo));
                    bitacora.setFechaEnvio(UtilGenerico.obtenerHoraMexico());
                    bitacora.setNumeroEnvio(request.getData().getNumeroEnvio());
                    bitacora.setStatus(Boolean.FALSE);
                    GenericoRepository.guardar(bitacora);

                }

                respuesta.getResponse().setCodigo(200);
                respuesta.getResponse().setMensaje("OK");
            } else {

                respuesta.getResponse().setMensaje("Credito insuficiente para servicio sms, contacte al administrador");
                respuesta.getResponse().setCodigo(500);

            }
            

        } catch (Exception e) {

            respuesta.getResponse().setMensaje(e.getMessage());

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

            if (!request.getData().isIsAval()) {

                BtValidacionSms respuesta = validacionSmsRepository.validarCodigo(request.getData().getNumeroEnvio(), request.getData().getCodigo());
                CatClientes cliente = validacionSmsRepository.buscarClienteTelefono(request.getData().getNumeroEnvio().replace("+52", ""));
                String nip = validacionSmsRepository.validarPin(request.getData().getCurp(), request.getData().getNip());
                if (nip.contains("ERROR")) {
                    response.getResponse().setCodigo(500);
                    response.getResponse().setMensaje(nip);
                    return response;
                }
                if (cliente == null) {
                    response.getResponse().setCodigo(500);
                    response.getResponse().setMensaje("NO existe un cliente asociado a este numero de telefono:" + request.getData().getNumeroEnvio());

                } else {
                    if (nip.contains("ERROR")) {
                        cliente.setAproboNip(false);
                    } else {
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

            }else{
            
                String respuesta = validacionSmsRepository.validarPinAval(request.getData().getNumeroEnvio(), request.getData().getCodigo(), request.getData().getCurp());
                
                logger.error("response validacion sms :"+respuesta);
                if(respuesta.contains("ERROR")){
                    
                    response.getResponse().setCodigo(500);
                    response.getResponse().setMensaje(respuesta);
                    return response;
                }else{
                    if(respuesta.isEmpty()){
                    
                       response.getData().setValidado(false);
                       response.getResponse().setCodigo(500);
                       response.getResponse().setMensaje("Codigo incorrecto");
                    }else{
                        
                        
                    validacionSmsRepository.actualizaStatusNipAval(request.getData().getNumeroEnvio(), request.getData().getCurp(), request.getData().getCodigo());
                    response.getData().setValidado(Boolean.TRUE);
                    response.getResponse().setCodigo(200);
                    response.getResponse().setMensaje("OK");
                    
                    }
                
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }

        return response;

    }

    private ResponseSms sendMasiveSms(RequestBodySms requestBody) {
        ResponseSms response = new ResponseSms();
        logger.error("inicia ---> sendMasiveSms ");
        String url = BASE_URL + "/sms/send";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            headers.set("apikey", key);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("message", requestBody.getMessage());
            map.add("numbers", requestBody.getPhoneNumber());
            map.add("country_code", requestBody.getLada());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            ResponseEntity<String> res = restTemplate.postForEntity(url, request, String.class);
            logger.error(res.getBody());

            response.setMessage("MENSAJE ENVIADO EXITOSAMENTE AL NUMERO  " + requestBody.getPhoneNumber());
            response.setStatus(true);

        } catch (HttpClientErrorException httpClierror) {

            response.setStatus(false);
            response.setMessage(httpClierror.getResponseBodyAsString());

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            e.getStackTrace();
        }
        logger.error("termina ---> sendMasiveSms ");

        return response;
    }

    private boolean valdiaCreditoDisponible() {
        boolean resp = true;
        String url = BASE_URL + "/credits/consult";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        try {

            headers.set("apikey", key);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("lang", "es");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            ResponseEntity<String> res = restTemplate.postForEntity(url, request, String.class);
            String resultSet = res.getBody();
            logger.error(res.getBody());
            JSONObject json = (JSONObject) JSONValue.parse(resultSet);

            resp = Boolean.parseBoolean(json.getAsString("success"));

        } catch (HttpClientErrorException httpClierror) {

            logger.error(httpClierror.getResponseBodyAsString());
            resp = false;

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
            resp = false;
        }
        return resp;

    }

}
