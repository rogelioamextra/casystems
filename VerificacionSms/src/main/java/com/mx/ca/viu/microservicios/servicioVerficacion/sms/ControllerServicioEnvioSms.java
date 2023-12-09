/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioVerficacion.sms;

import com.mx.ca.viu.commons.Transform;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatDirecciones;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatTiemposEmpleoActual;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposViviendas;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.CodigoPostal;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoAvisos;
import com.mx.ca.viu.modelos.dtos.generico.InfoColonia;
import com.mx.ca.viu.modelos.dtos.response.AvisosResponse;
import com.mx.ca.viu.modelos.dtos.request.AvisosRequest;
import com.mx.ca.viu.modelos.dtos.request.CatClientesRequest;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ConsultaCPRequest;
import com.mx.ca.viu.modelos.dtos.request.RequestEnvioSms;
import com.mx.ca.viu.modelos.dtos.request.RequestValidarCodigoSms;
import com.mx.ca.viu.modelos.dtos.response.ConsultaCPResponse;
import com.mx.ca.viu.modelos.dtos.response.EnvioSmsResponse;
import com.mx.ca.viu.modelos.dtos.response.LoginEntrarResponse;
import com.mx.ca.viu.modelos.dtos.response.ValidacionSmsResponse;
import com.mx.ca.viu.services.CatAvisosService;
import com.mx.ca.viu.services.CatClientesService;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.DtConsultaCPService;
import com.mx.ca.viu.services.GenericoService;
import com.mx.ca.viu.services.ValidacionSmsServices;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jbecerril
 */
@RestController
@RequestMapping("envio")

public class ControllerServicioEnvioSms {

    @Autowired
    ValidacionSmsServices validacionSmsServices;

  

    @PostMapping("sms")
    public ResponseEntity<EnvioSmsResponse> post(@RequestBody RequestEnvioSms entity, Principal principal) {
        EnvioSmsResponse respuesta = new EnvioSmsResponse();
        respuesta = validacionSmsServices.enviarCodigoValidacion(entity.getData().getNumeroEnvio(),entity.getData().getCurp());
        return ResponseEntity.ok(respuesta);

    }
    @PostMapping("validar")
    public ResponseEntity<ValidacionSmsResponse> postValidar(@RequestBody RequestValidarCodigoSms entity, Principal principal) {
        ValidacionSmsResponse respuesta = new ValidacionSmsResponse();
         
        respuesta = validacionSmsServices.validarCodigo(entity);
        return ResponseEntity.ok(respuesta);

    }

}
