/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ImagenesClienteByIdResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public ImagenesClienteByIdResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("imgAnverso")
        private String imgAnverso;
        
        @JsonProperty("imgReverso")
        private String imgReverso;
        
        @JsonProperty("selfie")
        private String selfie;

        @JsonProperty("otraIdentificacion")
        private String otraIdentificacion;
        
        @JsonProperty("comprobanteDomicilio")
        private String comprobanteDomicilio;

        public String getImgAnverso() {
            return imgAnverso;
        }

        public void setImgAnverso(String imgAnverso) {
            this.imgAnverso = imgAnverso;
        }

        public String getImgReverso() {
            return imgReverso;
        }

        public void setImgReverso(String imgReverso) {
            this.imgReverso = imgReverso;
        }

        public String getSelfie() {
            return selfie;
        }

        public void setSelfie(String selfie) {
            this.selfie = selfie;
        }

        public String getOtraIdentificacion() {
            return otraIdentificacion;
        }

        public void setOtraIdentificacion(String otraIdentificacion) {
            this.otraIdentificacion = otraIdentificacion;
        }

        public String getComprobanteDomicilio() {
            return comprobanteDomicilio;
        }

        public void setComprobanteDomicilio(String comprobanteDomicilio) {
            this.comprobanteDomicilio = comprobanteDomicilio;
        }
        
        

      
        
             
        
        

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
