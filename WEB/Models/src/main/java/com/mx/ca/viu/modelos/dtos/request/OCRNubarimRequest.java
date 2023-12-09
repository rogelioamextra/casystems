/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jbecerril
 */
public class OCRNubarimRequest {

    @JsonProperty("id")
    private String id;
    @JsonProperty("idReverso")
    private String idReverso;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdReverso() {
        return idReverso;
    }

    public void setIdReverso(String idReverso) {
        this.idReverso = idReverso;
    }
    

}
