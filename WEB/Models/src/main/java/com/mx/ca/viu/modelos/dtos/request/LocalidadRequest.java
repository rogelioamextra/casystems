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
public class LocalidadRequest extends GenericAuth implements Serializable{
    @JsonProperty("data")
    Data data;
    
    public static class Data implements Serializable {
        @JsonProperty("idMunicipio")
        private int idMunicipio;

        public int getIdMunicipio() {
            return idMunicipio;
        }

        public void setIdMunicipio(int idMunicipio) {
            this.idMunicipio = idMunicipio;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
