/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service
public class infoProducto implements Serializable {
    @JsonProperty("IdProducto")
    private Long IdProducto;
    @JsonProperty("nombreProducto")
    private String nombreProducto;
    
    public infoProducto() {
    }

    public infoProducto(Long IdProducto, String nombreProducto) {
        this.IdProducto = IdProducto;
        this.nombreProducto = nombreProducto;
    }

    public Long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    
}
