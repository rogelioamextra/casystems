/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioCorreo.controllers;

import com.mx.ca.viu.microservicios.servicioCorreo.dtos.request.EmailRecuperaPassRequest;
import com.mx.ca.viu.microservicios.servicioCorreo.dtos.response.EnvioCorreoResponse;
import com.mx.ca.viu.microservicios.servicioCorreo.mail.EmailService;
import com.mx.ca.viu.microservicios.servicioCorreo.mail.templates.MailTemplates;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rzavaleta
 */
@RestController
public class ControllerServicioCorreo {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/correo/recuperapass", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public EnvioCorreoResponse enviaMailRecuperaPass(@RequestBody EmailRecuperaPassRequest request) {

        EnvioCorreoResponse response = new EnvioCorreoResponse();

        try {

            //Generamos un código random de 8 caracteres
            String codigoRecuperacion = generaCodigo();

            // Enviamos correo con código
            String templateCorreo = MailTemplates.RECUPERA_PASS_TEMPLATE;

            // Sobreescribimos los valores del template por los del usuario                
            templateCorreo = templateCorreo.replace("%_NOMBRE_USUARIO_%", request.getData().getNombreUsuario());
            templateCorreo = templateCorreo.replace("%_CODIGO_RECUPERA_PASS_%", codigoRecuperacion);

            // Params = [Para, Asunto, Cuerpo del mensaje]
            emailService.sendEmail(
                    request.getData().getEmail(),
                    "AMEXTRA APPNOUS - Solicitud de Recuperación de Contraseña",
                    templateCorreo
            );
            response.getResponse().setCodigo(200);
            response.getData().setCodigoRecuperaPass(codigoRecuperacion);
            response.getResponse().setMensaje("Hemos enviado un correo electrónico con el código de verificación necesario para continuarcontinuar");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }

        return response;
    }

    private String generaCodigo() {
        int length = 8;
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // Randomly choose between upper case letters and numbers
            boolean isLetter = random.nextBoolean();
            if (isLetter) {
                // Append a random upper case letter
                sb.append((char) (random.nextInt(26) + 'A'));
            } else {
                // Append a random digit
                sb.append(random.nextInt(10));
            }
        }

        return sb.toString();
    }
}
