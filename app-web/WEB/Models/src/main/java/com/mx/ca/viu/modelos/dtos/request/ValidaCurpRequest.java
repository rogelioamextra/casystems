/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.DtReferenciasPersonales;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ValidaCurpRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;

    public ValidaCurpRequest() {
        setAuth(new Auth());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("curp")
        private String curp;
        
        
        @JsonProperty("posibleCliente")
        private boolean posibleCliente;
                
                
        public String getCurp() {
            return curp;
        }

        public void setCurp(String curp) {
            this.curp = curp;
        }

        public boolean isPosibleCliente() {
            return posibleCliente;
        }

        public void setPosibleCliente(boolean posibleCliente) {
            this.posibleCliente = posibleCliente;
        }
        
        
        

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
