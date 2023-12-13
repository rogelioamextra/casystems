/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.DtoSolicitudCampo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class IdentificacionOcrResponse {

    @JsonProperty("response")
    private Response response;
    
    @JsonProperty("data")
    Data data;
    
    public IdentificacionOcrResponse(){
        this.data = new Data();
    }

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
    
    public static class Data implements Serializable {
        @JsonProperty("noIdentificacion")
        private String noIdentificacion;
        @JsonProperty("claveElector")
        private String claveElector;
        @JsonProperty("vigencia")
        private String vigencia;
        @JsonProperty("emision")
        private String emision;

        public String getNoIdentificacion() {
            return noIdentificacion;
        }

        public void setNoIdentificacion(String noIdentificacion) {
            this.noIdentificacion = noIdentificacion;
        }

        public String getClaveElector() {
            return claveElector;
        }

        public void setClaveElector(String claveElector) {
            this.claveElector = claveElector;
        }

        public String getVigencia() {
            return vigencia;
        }

        public void setVigencia(String vigencia) {
            this.vigencia = vigencia;
        }

        public String getEmision() {
            return emision;
        }

        public void setEmision(String emision) {
            this.emision = emision;
        }
        
        
       
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
