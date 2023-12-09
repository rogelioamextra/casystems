/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mramirez
 */
public class OcrIdentificacionRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    private Data data;

    public static class Data implements Serializable {

        @JsonProperty("anverso")
        private String anverso;
        @JsonProperty("reverso")
        private String reverso;
        @JsonProperty("curp")
        private String curp;

        public String getAnverso() {
            return anverso;
        }

        public void setAnverso(String anverso) {
            this.anverso = anverso;
        }

        public String getReverso() {
            return reverso;
        }

        public void setReverso(String reverso) {
            this.reverso = reverso;
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
