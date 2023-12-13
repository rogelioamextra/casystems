/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class CatAgendaCambioEstatusRequest implements Serializable {

    @JsonProperty("data")
    Data data;

    public static class Data implements Serializable {

        @JsonProperty("idAgenda")
        private Long idAgenda;
        @JsonProperty("estatus")
        private String estatus;
        @JsonProperty("latitud")
        private String latitud;
        @JsonProperty("longitud")
        private String longitud;

        public Long getIdAgenda() {
            return idAgenda;
        }

        public void setIdAgenda(Long idAgenda) {
            this.idAgenda = idAgenda;
        }

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    

}
