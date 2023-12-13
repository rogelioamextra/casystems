/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoUsuarioDTO;
import com.mx.ca.viu.modelos.dtos.generico.infoMenus;
import com.mx.ca.viu.modelos.dtos.generico.infoProducto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class LoginEntrarResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;
    
    public LoginEntrarResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {
        @JsonProperty("infoUSer")
        private InfoUsuarioDTO infoUSer;
        
        @JsonProperty("menus")
        private List<infoMenus> infoMenus;
        
        public Data() {
            this.infoUSer = new InfoUsuarioDTO();
        }

        public InfoUsuarioDTO getInfoUSer() {
            return infoUSer;
        }

        public void setInfoUSer(InfoUsuarioDTO infoUSer) {
            this.infoUSer = infoUSer;
        }

        public List<infoMenus> getInfoMenus() {
            return infoMenus;
        }

        public void setInfoMenus(List<infoMenus> infoMenus) {
            this.infoMenus = infoMenus;
        }

        
        
    }

    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
}
