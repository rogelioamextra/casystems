/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mramirez
 */
public class LocalidadResponse extends GenericResponse implements Serializable {
    @JsonProperty("data")
    Data data;
    
    public LocalidadResponse(){
        setResponse(new Response());
        this.data = new Data();
    }
    
    public static class Data implements Serializable {
        @JsonProperty("localidad")
        private List<Colonia> localidad;

        public List<Colonia> getLocalidad() {
            return localidad;
        }

        public void setLocalidad(List<Colonia> localidad) {
            this.localidad = localidad;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
