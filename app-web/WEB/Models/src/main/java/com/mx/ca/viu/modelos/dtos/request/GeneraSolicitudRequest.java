/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import java.io.Serializable;

/**
 *
 * @author mramirez
 */
public class GeneraSolicitudRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;
    
    public static class Data implements Serializable {
        @JsonProperty("idProducto")
        private Long idProducto;

        public Long getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(Long idProducto) {
            this.idProducto = idProducto;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
