/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;

/**
 *
 * @author rzavaleta
 */
public class ActualizaPassResponse extends GenericResponse implements Serializable {
    
    @JsonProperty("data")
    Data data;
    
    public ActualizaPassResponse() {
        setResponse(new Response());
        this.data = new Data();
    }   
    
    public static class Data implements Serializable {
        
        @JsonProperty("email")
        private String email;
        
        @JsonProperty("nombreUsuario")
        private String nombreUsuario;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }
        
    }
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }    
    
}
