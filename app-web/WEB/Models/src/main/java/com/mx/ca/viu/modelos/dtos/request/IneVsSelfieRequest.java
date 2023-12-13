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
public class IneVsSelfieRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    private Data data;

    public static class Data implements Serializable {

        @JsonProperty("ine")
        private String ine;
        @JsonProperty("selfie")
        private String selfie;
        @JsonProperty("curp")
        private String curp;

        public String getIne() {
            return ine;
        }

        public void setIne(String ine) {
            this.ine = ine;
        }

        public String getSelfie() {
            return selfie;
        }

        public void setSelfie(String selfie) {
            this.selfie = selfie;
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
