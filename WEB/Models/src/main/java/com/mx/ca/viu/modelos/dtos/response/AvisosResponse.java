/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoAvisos;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class AvisosResponse extends GenericResponse implements Serializable{

    @JsonProperty("data")
    Data data;
    
    public AvisosResponse(){
        setResponse(new GenericResponse.Response());
        this.data = new Data();
    }

    public static class Data implements Serializable{
    
        @JsonProperty("infoAvisos")
        private List<InfoAvisos> infoAvisos;

        public List<InfoAvisos> getInfoAvisos() {
            return infoAvisos;
        }

        public void setInfoAvisos(List<InfoAvisos> infoAvisos) {
            this.infoAvisos = infoAvisos;
        }
        
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
}
