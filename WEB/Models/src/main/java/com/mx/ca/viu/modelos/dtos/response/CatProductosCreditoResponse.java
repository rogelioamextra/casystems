/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mramirez
 */
public class CatProductosCreditoResponse extends GenericResponse implements Serializable {
    
    @JsonProperty("data")
    CatProductosCreditoResponse.Data data;
    
    public CatProductosCreditoResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("productos")
        private List<CatProductosCredito> lista;

        public List<CatProductosCredito> getLista() {
            return lista;
        }

        public void setLista(List<CatProductosCredito> lista) {
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