/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class CatEstadosCivilesResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public CatEstadosCivilesResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("estadosCiviles")
        private List<CatEstadosCiviles> lista;

        public List<CatEstadosCiviles> getLista() {
            return lista;
        }

        public void setLista(List<CatEstadosCiviles> lista) {
            this.lista = lista;
        }

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
