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
 * @author mramirez
 */
@Service
public class DtoSolicitudCampo implements Serializable{
    @JsonProperty("idCampo")
    private Long idCampo;
    @JsonProperty("idCategoria")
    private Long idCategoria;
    @JsonProperty("valorAutomatico")
    private String valorAutomatico;
    
    public DtoSolicitudCampo(){
    }

    public Long getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getValorAutomatico() {
        return valorAutomatico;
    }

    public void setValorAutomatico(String valorAutomatico) {
        this.valorAutomatico = valorAutomatico;
    }
    
    
}
