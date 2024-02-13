/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioCorreo.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author rzavaleta
 */
public class EnvioCorreoResponse extends GenericResponse implements Serializable {
    
    @JsonProperty("data")
    Data data;
    
    public EnvioCorreoResponse() {
        setResponse(new Response());
        this.data = new Data();
    }
    
    public static class Data implements Serializable {
        
        @JsonProperty("codigoRecuperaPass")
        private String codigoRecuperaPass;

        public String getCodigoRecuperaPass() {
            return codigoRecuperaPass;
        }

        public void setCodigoRecuperaPass(String codigoRecuperaPass) {
            this.codigoRecuperaPass = codigoRecuperaPass;
        }
                        
    }
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    
}
