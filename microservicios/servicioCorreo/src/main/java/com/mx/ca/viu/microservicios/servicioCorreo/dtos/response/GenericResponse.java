/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioCorreo.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author rzavaleta
 */
public class GenericResponse {

    @JsonProperty("response")
    private Response response;

    public static class Response {

        @JsonProperty("codigo")
        private Integer codigo;
        @JsonProperty("mensaje")
        private String mensaje;

        public Integer getCodigo() {
            return codigo;
        }

        public void setCodigo(Integer codigo) {
            this.codigo = codigo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
        
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
