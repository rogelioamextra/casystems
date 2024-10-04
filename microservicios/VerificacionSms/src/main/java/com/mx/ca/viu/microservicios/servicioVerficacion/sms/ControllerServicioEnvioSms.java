/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioVerficacion.sms;

import com.mx.ca.viu.modelos.dtos.request.RequestEnvioSms;
import com.mx.ca.viu.modelos.dtos.request.RequestValidarCodigoSms;
import com.mx.ca.viu.modelos.dtos.response.EnvioSmsResponse;
import com.mx.ca.viu.modelos.dtos.response.ValidacionSmsResponse;
import com.mx.ca.viu.services.ValidacionSmsServices;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        respuesta = validacionSmsServices.enviarCodigoValidacion(entity );
        return ResponseEntity.ok(respuesta);

    }
    @PostMapping("valida-sms")
    public ResponseEntity<ValidacionSmsResponse> postValidar(@RequestBody RequestValidarCodigoSms entity, Principal principal) {
        ValidacionSmsResponse respuesta = new ValidacionSmsResponse();
         
        respuesta = validacionSmsServices.validarCodigo(entity);
        return ResponseEntity.ok(respuesta);

    }

}
