/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CodigoPostal;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;

/**
 *
 * @author jbecerril
 */
public class SolicitudResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public SolicitudResponse() {
        data = new Data();
        setResponse(new Response());
    }

    public static class Data implements Serializable {

        @JsonProperty("solicitudId")
        private String solicitudId;

        public String getSolicitudId() {
            return solicitudId;
        }

        public void setSolicitudId(String solicitudId) {
            this.solicitudId = solicitudId;
        }
        
        

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    

}
