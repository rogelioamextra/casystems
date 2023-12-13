/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.response.GestionDatosProcesoResponse;
import java.io.Serializable;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class GestionDatosProcesoRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;
    
    public static class Data implements Serializable {
    
        @JsonProperty("empresa")
        private Long empresa;
        @JsonProperty("producto")
        private Long producto;

        public Long getEmpresa() {
            return empresa;
        }

        public void setEmpresa(Long empresa) {
            this.empresa = empresa;
        }

        public Long getProducto() {
            return producto;
        }

        public void setProducto(Long producto) {
            this.producto = producto;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
}
