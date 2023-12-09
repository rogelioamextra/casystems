/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author jbecerril
 */
public class Cliente implements Serializable {

    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("curp")
    private String curp;

    public Cliente() {
    }
    
    

    public Cliente(String nombre, Long id, String curp) {
        this.nombre = nombre;
        this.id = id;
        this.curp = curp;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    

}
