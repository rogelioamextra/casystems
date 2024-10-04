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
 * @author jbecerril
 */
public class RequestValidarCodigoSms extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;

    public static class Data implements Serializable {

        @JsonProperty("numeroEnvio")

        private String numeroEnvio;
        @JsonProperty("codigo")

        private String codigo;
        @JsonProperty("nip")
        private String nip;
        @JsonProperty("curp")
        private String curp;
        
        @JsonProperty("isAval")
        private boolean isAval;

        public boolean isIsAval() {
            return isAval;
        }

        public void setIsAval(boolean isAval) {
            this.isAval = isAval;
        }
        
        
        

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }
        
        

        public String getNumeroEnvio() {
            return numeroEnvio;
        }

        public void setNumeroEnvio(String numeroEnvio) {
            this.numeroEnvio = numeroEnvio;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getCurp() {
            return curp;
        }

        public void setCurp(String curp) {
            this.curp = curp;
        }
        
        
        
        

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    

}
