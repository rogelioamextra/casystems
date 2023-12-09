/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.ConfiguracionProceso;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public class ConfiguracionSolicitudResponse extends GenericResponse implements Serializable {
    
    @JsonProperty("data")
    Data data;
    
    public ConfiguracionSolicitudResponse(){
        setResponse(new Response());
        this.data = new Data();
    }
    
    public static class Data implements Serializable {
        
        @JsonProperty("infoGDP")
        private ConfiguracionProceso infoGDP;
        @JsonProperty("idSolicitud")
        private Long idSolicitud;
        @JsonProperty("nombreProducto")
        private String nombreProducto;

        public ConfiguracionProceso getInfoGDP() {
            return infoGDP;
        }

        public void setInfoGDP(ConfiguracionProceso infoGDP) {
            this.infoGDP = infoGDP;
        }

        public Long getIdSolicitud() {
            return idSolicitud;
        }

        public void setIdSolicitud(Long idSolicitud) {
            this.idSolicitud = idSolicitud;
        }

        public String getNombreProducto() {
            return nombreProducto;
        }

        public void setNombreProducto(String nombreProducto) {
            this.nombreProducto = nombreProducto;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    
    
}
