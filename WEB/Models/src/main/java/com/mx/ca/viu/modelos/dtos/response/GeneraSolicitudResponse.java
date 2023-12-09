/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse.Response;
import java.io.Serializable;

/**
 *
 * @author mramirez
 */
public class GeneraSolicitudResponse extends GenericResponse implements Serializable{
    @JsonProperty("data")
    Data data;
    
    public GeneraSolicitudResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("idSolicitud")
        private Long idSolicitud;

        public Long getIdSolicitud() {
            return idSolicitud;
        }

        public void setIdSolicitud(Long idSolicitud) {
            this.idSolicitud = idSolicitud;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
