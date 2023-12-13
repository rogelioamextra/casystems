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
public class CatAvisosResponse extends GenericResponse implements Serializable {
    
    @JsonProperty("data")
    CatAvisosResponse.Data data;
    
    public CatAvisosResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("avisos")
        private List<AvisosDTO> lista;

        public List<AvisosDTO> getLista() {
            return lista;
        }

        public void setLista(List<AvisosDTO> lista) {
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