/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author rzavaleta
 */
public class ActualizaPassRequest {
    
    @JsonProperty("data")
    Data data;
    
    public static class Data implements Serializable {
        
        @JsonProperty("idUsuario")
        private String idUsuario;
        
        @JsonProperty("email")
        private String email;
        
        @JsonProperty("newPass")
        private String newPass;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getNewPass() {
            return newPass;
        }

        public void setNewPass(String newPass) {
            this.newPass = newPass;
        }
        
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }   
    
}
