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
 * @author JoseAlfredoMarinLope
 */
@Service
public class InfoAvisos implements Serializable{
    @JsonProperty("idConfigAviso")
    private Long idConfigAviso;
    @JsonProperty("nombreAviso")
    private String nombreAviso;
    
    @JsonProperty("documento")
    private boolean documento;
    @JsonProperty("nombreArchivo")
    private String nombreArchivo;
    
    @JsonProperty("idTipoAviso")
    private Long idTipoAviso;
    @JsonProperty("nombreTipoAviso")
    private String nombreTipoAviso;
    
    @JsonProperty("status")
    private boolean status;
    
    @JsonProperty("aviso")
    private String aviso;

    
    
    public Long getIdConfigAviso() {
        return idConfigAviso;
    }

    public void setIdConfigAviso(Long idConfigAviso) {
        this.idConfigAviso = idConfigAviso;
    }

    public String getNombreAviso() {
        return nombreAviso;
    }

    public void setNombreAviso(String nombreAviso) {
        this.nombreAviso = nombreAviso;
    }

    public boolean isDocumento() {
        return documento;
    }

    public void setDocumento(boolean documento) {
        this.documento = documento;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Long getIdTipoAviso() {
        return idTipoAviso;
    }

    public void setIdTipoAviso(Long idTipoAviso) {
        this.idTipoAviso = idTipoAviso;
    }

    public String getNombreTipoAviso() {
        return nombreTipoAviso;
    }

    public void setNombreTipoAviso(String nombreTipoAviso) {
        this.nombreTipoAviso = nombreTipoAviso;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }
    
    
    
}
