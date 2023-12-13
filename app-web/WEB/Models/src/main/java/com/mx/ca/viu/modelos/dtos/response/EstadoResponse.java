/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;

/**
 *
 * @author mramirez
 */
public class EstadoResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public EstadoResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("estado")
        private Estado estado;

        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
