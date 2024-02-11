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
public class RecuperaPassRequest implements Serializable {
    
    @JsonProperty("data")
    Data data;
    
    public static class Data implements Serializable {

        @JsonProperty("email")
        private String email;       

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
    
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
}
