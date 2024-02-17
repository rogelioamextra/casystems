/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoUsuarioDTO;
import java.io.Serializable;

/**
 *
 * @author rzavaleta
 */
public class RecuperaPassResponse extends GenericResponse implements Serializable {
    
    @JsonProperty("data")
    Data data;
    
    public RecuperaPassResponse () {
        setResponse(new Response());
        this.data = new Data();
    }
    
    public static class Data implements Serializable {
        
        @JsonProperty("infoUser")
        private InfoUsuarioDTO infoUser;
        
        public Data () {
            this.infoUser = new InfoUsuarioDTO();
        }

        public InfoUsuarioDTO getInfoUser() {
            return infoUser;
        }

        public void setInfoUser(InfoUsuarioDTO infoUser) {
            this.infoUser = infoUser;
        }        
        
    }
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    
}
