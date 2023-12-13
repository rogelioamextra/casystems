/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.BtValidacionSms;
import com.mx.ca.viu.modelos.dtos.request.RequestValidarCodigoSms;
import com.mx.ca.viu.modelos.dtos.response.EnvioSmsResponse;
import com.mx.ca.viu.modelos.dtos.response.ValidacionSmsResponse;

/**
 *
 * @author jbecerril
 */
public interface ValidacionSmsServices {

    public EnvioSmsResponse enviarCodigoValidacion(String numeroEnvio, String curp);


    public ValidacionSmsResponse validarCodigo(RequestValidarCodigoSms request);

}
