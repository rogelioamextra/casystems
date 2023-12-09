/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioCurp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.mx.ca.viu.commons.Constantes.mapper;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatListaNegraAmextra;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatPersonas;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DtValidacionCurp;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.RequestNubarimCurp;
import com.mx.ca.viu.modelos.dtos.request.ValidaCurpRequest;
import com.mx.ca.viu.modelos.dtos.response.CurpResponse;
import com.mx.ca.viu.modelos.dtos.response.NubarimCurpResponse;
import com.mx.ca.viu.modelos.dtos.response.ValidacionCurpResponse;
import com.mx.ca.viu.repositorys.CatClientesRepository;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.CatServiciosValidacionesExternosServices;
import com.mx.ca.viu.services.GenericoService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

/**
 *
 * @author jbecerril
 */
@RestController

public class ControllerCurp {

    @Autowired
    CatServiciosValidacionesExternosServices service;
    @Autowired
    public GenericoService genericoService;
    @Autowired
    public CatEstadoService estadoService;
    @Autowired
    CatClientesRepository catClientesRepository;

    @PostMapping("/valida/curp")
    public ResponseEntity<ValidacionCurpResponse> consultar(@RequestBody ValidaCurpRequest request, Principal principal) {
        ValidacionCurpResponse response = new ValidacionCurpResponse();
        try {
            CatServiciosValidacionesExternos servicio = service.buscarServicioId(1L);
            //SE AGREGA REEMPLAZO DE PALABRA CONTENIDO POR LA QUE SE LE ENVIA AL MICROSERVICIO DE NUBARIUM
            RequestNubarimCurp body = new RequestNubarimCurp();
            CatPersonas p = catClientesRepository.buscarCurp(request.getData().getCurp());
            if (p != null) {
                throw new Exception("La curp:" + request.getData().getCurp() + " ya se encuentra registrada");
            }
            List<CatListaNegraAmextra> negra = catClientesRepository.buscarListaNegraCurp(request.getData().getCurp());

            if (negra != null && !negra.isEmpty()) {
                throw new Exception("La curp:" + request.getData().getCurp() + " ya se encuentra en la lista negra amextra");
            }

            body.setCurp(request.getData().getCurp());
            body.setDocumento("0");

            String resultado = UtilGenerico.sendRequest(UtilGenerico.ObjectToJson(body), servicio.getCatConfiguracionesServiciosExternosList().get(0));
            if (resultado.isEmpty()) {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("Servicio de validacion con RENAPO no se encuentra disponible,inserte datos manualmente");
                return ResponseEntity.ok(response);
            }

            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            NubarimCurpResponse curpResponse = mapper.readValue(resultado, NubarimCurpResponse.class);
            if (curpResponse != null && curpResponse.getEstatus().equals("OK")) {
                response.getData().setNombre(curpResponse.getNombre());
                response.getData().setApellidoPaterno(curpResponse.getApellidoPaterno());
                response.getData().setApellidoMaterno(curpResponse.getApellidoMaterno());
                String fechaaux = UtilGenerico.stringToDateString(curpResponse.getFechaNacimiento(), "dd/MM/yyyy", "yyyy-MM-dd");
                response.getData().setFechaNacimiento(fechaaux);
                response.getData().setEdad(UtilGenerico.obtieneEdad(curpResponse.getFechaNacimiento()));
                Estado est = estadoService.buscarEstadoPorSigla(UtilGenerico.curp2Estado(curpResponse.getCurp()));
                response.getData().setEstadoId(est.getInegiClave());
                response.getData().setNombreEstado(est.getNombre());
                response.getData().setSexo(curpResponse.getSexo());
                if (curpResponse.getSexo().equals("HOMBRE")) {
                    response.getData().setCodigoSexo("1");

                } else {
                    response.getData().setCodigoSexo("0");
                }
                if (curpResponse.getPaisNacimiento().equals("MEXICO")) {
                    CatNacionalidades nac = genericoService.findByID(CatNacionalidades.class, 73L);
                    if (nac != null) {
                        response.getData().setNacionalidad(nac.getNombre());
                        response.getData().setNacionalidadId(nac.getIdNacionalidad().toString());
                    }
                }

                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");

            } else {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje(curpResponse.getMensaje());
            }

        } catch (JsonProcessingException e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

}
