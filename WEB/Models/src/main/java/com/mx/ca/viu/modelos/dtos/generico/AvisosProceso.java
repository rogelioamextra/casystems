/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author linzunza
 */
public class AvisosProceso {
    @JsonProperty("idAvisos")
    private Long idAvisos;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("aviso")
    private String aviso;
    @JsonProperty("nombreArchivo")
    private String nombreArchivo;
    @JsonProperty("documento")
    private Boolean documento;
    @JsonProperty("tipoAviso")
    private TipoAvisosProceso tipoAviso;
    
    public AvisosProceso(){
    }


    public Long getIdAvisos() {
        return idAvisos;
    }

    public void setIdAvisos(Long idAvisos) {
        this.idAvisos = idAvisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Boolean getDocumento() {
        return documento;
    }

    public void setDocumento(Boolean documento) {
        this.documento = documento;
    }

    public TipoAvisosProceso getTipoAviso() {
        return tipoAviso;
    }

    public void setTipoAviso(TipoAvisosProceso tipoAviso) {
        this.tipoAviso = tipoAviso;
    }
    
}
