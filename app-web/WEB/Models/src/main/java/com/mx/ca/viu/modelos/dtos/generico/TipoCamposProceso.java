/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Lob;
import org.hibernate.annotations.Type;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public class TipoCamposProceso implements Serializable{
    @JsonProperty("idTipoCampo")
    private Long idTipoCampo;
    @JsonProperty("nombreTipoCampo")
    private String nombreTipoCampo;
    @Lob
    @Type(type = "jsonb")
    private Object informacionCampo;
    
    public TipoCamposProceso(){
    }

    public Long getIdTipoCampo() {
        return idTipoCampo;
    }

    public void setIdTipoCampo(Long idTipoCampo) {
        this.idTipoCampo = idTipoCampo;
    }

    public String getNombreTipoCampo() {
        return nombreTipoCampo;
    }

    public void setNombreTipoCampo(String nombreTipoCampo) {
        this.nombreTipoCampo = nombreTipoCampo;
    }

    public Object getInformacionCampo() {
        return informacionCampo;
    }

    public void setInformacionCampo(Object informacionCampo) {
        this.informacionCampo = informacionCampo;
    }
    
    
}
