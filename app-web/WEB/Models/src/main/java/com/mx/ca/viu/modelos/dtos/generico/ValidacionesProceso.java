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
public class ValidacionesProceso implements Serializable{
    @JsonProperty("idValidacion")
    private Long idValidacion;
    @JsonProperty("nombreValidacion")
    private String nombreValidacion;
    @JsonProperty("idValor")
    private Long idValor;
    @JsonProperty("nombreValor")
    private String nombreValor;
    @JsonProperty("nombreResultado")
    private String nombreResultado;
    
    public ValidacionesProceso(){
    }

    public Long getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(Long idValidacion) {
        this.idValidacion = idValidacion;
    }

    public String getNombreValidacion() {
        return nombreValidacion;
    }

    public void setNombreValidacion(String nombreValidacion) {
        this.nombreValidacion = nombreValidacion;
    }

    public Long getIdValor() {
        return idValor;
    }

    public void setIdValor(Long idValor) {
        this.idValor = idValor;
    }

    public String getNombreValor() {
        return nombreValor;
    }

    public void setNombreValor(String nombreValor) {
        this.nombreValor = nombreValor;
    }

    public String getNombreResultado() {
        return nombreResultado;
    }

    public void setNombreResultado(String nombreResultado) {
        this.nombreResultado = nombreResultado;
    }
    
    
}
