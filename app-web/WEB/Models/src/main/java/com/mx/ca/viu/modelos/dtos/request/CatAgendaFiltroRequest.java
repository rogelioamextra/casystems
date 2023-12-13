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
public class CatAgendaFiltroRequest implements Serializable {

    @JsonProperty("data")
    Data data;

    public static class Data implements Serializable {

        @JsonProperty("fecha")
        private String fecha;
        @JsonProperty("asesor")
        private String asesor;

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getAsesor() {
            return asesor;
        }

        public void setAsesor(String asesor) {
            this.asesor = asesor;
        }


    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    

}
