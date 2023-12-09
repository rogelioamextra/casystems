/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoGestionDatosProceso;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class GestionDatosProcesoResponse extends GenericResponse implements Serializable{

    @JsonProperty("data")
    DataGDP data;
    
    public GestionDatosProcesoResponse(){
        setResponse(new Response());
        this.data = new DataGDP();
    }
    
    public static class DataGDP implements Serializable{
        @JsonProperty("infoGDP")
        private List<InfoGestionDatosProceso> infoGDP;
        
        //public DataGDP() {
        //    this.infoGDP = new InfoGestionDatosProceso();
        //}
        
        public List<InfoGestionDatosProceso> getInfoGDP() {
            return infoGDP;
        }

        public void setInfoGDP(List<InfoGestionDatosProceso> gestionDP) {
            this.infoGDP = gestionDP;
        }
    }
    
    public DataGDP getData() {
        return data;
    }
    
    public void setData(DataGDP data) {
        this.data = data;
    }
}
