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
public class CamposProceso implements Serializable{
    @JsonProperty("idCampo")
    private Long idCampos;
    @JsonProperty("nombreCampo")
    private String nombre;
    @JsonProperty("requerido")
    private Boolean requerido;
    @JsonProperty("tipoCampo")
    private TipoCamposProceso tipoCampo;
    
    public CamposProceso(){
    }

    public Long getIdCampos() {
        return idCampos;
    }

    public void setIdCampos(Long idCampos) {
        this.idCampos = idCampos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public TipoCamposProceso getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(TipoCamposProceso tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

}
