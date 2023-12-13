/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service
public class CategoriasProceso implements Serializable{
    @JsonProperty("idCategoria")
    private Long idCategoria;
    @JsonProperty("nombreCategoria")
    private String nombreCategoria;
    
    @JsonProperty("campos")
    @OneToMany(mappedBy = "campos")
    private List<CamposProceso> campos;
    
    @JsonProperty("documentos")
    @OneToMany(mappedBy = "documentos")
    private List<DocumentosProceso> documentos;
    
    @JsonProperty("documentosIdentidad")
    @OneToMany(mappedBy = "documentosIdentidad")
    private List<DocumentosProceso> documentosIdentidad;
    
    @JsonProperty("avisos")
    @OneToMany(mappedBy = "avisos")
    private List<AvisosProceso> avisos;
    
    @JsonProperty("botonFinaliza")
    @OneToMany(mappedBy = "botonFinaliza")
    private BotonFinaliza botonFinaliza;
    
    public CategoriasProceso(){
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<CamposProceso> getCampos() {
        return campos;
    }

    public void setCampos(List<CamposProceso> campos) {
        this.campos = campos;
    }

    public List<DocumentosProceso> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentosProceso> documentos) {
        this.documentos = documentos;
    }

    public BotonFinaliza getBotonFinaliza() {
        return botonFinaliza;
    }

    public void setBotonFinaliza(BotonFinaliza botonFinaliza) {
        this.botonFinaliza = botonFinaliza;
    }

    public List<DocumentosProceso> getDocumentosIdentidad() {
        return documentosIdentidad;
    }

    public void setDocumentosIdentidad(List<DocumentosProceso> documentosIdentidad) {
        this.documentosIdentidad = documentosIdentidad;
    }

    public List<AvisosProceso> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<AvisosProceso> avisos) {
        this.avisos = avisos;
    }
    
    
}
