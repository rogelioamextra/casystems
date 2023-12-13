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
 * @author mramirez
 */
public class ExtraccionDatosResponse {
    @JsonProperty("response")
    private Response response;
    
    @JsonProperty("data")
    Data data;
    
    public ExtraccionDatosResponse(){
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
        @JsonProperty("datosAutoCompletado")
        private List<DtoSolicitudCampo> datosAutoCompletado;

        public List<DtoSolicitudCampo> getDatosAutoCompletado() {
            return datosAutoCompletado;
        }

        public void setDatosAutoCompletado(List<DtoSolicitudCampo> datosAutoCompletado) {
            this.datosAutoCompletado = datosAutoCompletado;
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
