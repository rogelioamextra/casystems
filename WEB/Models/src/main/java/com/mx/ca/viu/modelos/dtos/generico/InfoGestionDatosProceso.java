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
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service
public class InfoGestionDatosProceso implements Serializable {
    @JsonProperty("idCampo")
    private Long idCampo;
    @JsonProperty("nombreCampo")
    private String nombreCampo;
    @JsonProperty("idCategoriaCampo")
    private Long idCategoriaCampo;
    @JsonProperty("nombreCategoriaCampo")
    private String nombreCategoriaCampo;
    @JsonProperty("idTipoCampo")
    private Long idTipoCampo;
    @JsonProperty("nombreTipoCampo")
    private String nombreTipoCampo;
    @JsonProperty("requerido")
    private boolean requerido;
    @Lob
    @Type(type = "jsonb")
    private Object json;

    @JsonProperty("idDocumento")
    private Long idDocumento;
    @JsonProperty("nombreDocumento")
    private String nombreDocumento;
    
    @JsonProperty("idCatDoc")
    private Long idCatDoc;
    @JsonProperty("nombreCatDoc")
    private String nombreCatDoc;
    
    public InfoGestionDatosProceso() {
    }

    public InfoGestionDatosProceso(Long idCampo, String nombreCampo, Long idCategoriaCampo, String nombreCategoriaCampo, Long idTipoCampo, String nombreTipoCampo, boolean requerido, Object json, Long idDocumento, String nombreDocumento, Long idCatDoc, String nombreCatDoc) {
        this.idCampo = idCampo;
        this.nombreCampo = nombreCampo;
        this.idCategoriaCampo = idCategoriaCampo;
        this.nombreCategoriaCampo = nombreCategoriaCampo;
        this.idTipoCampo = idTipoCampo;
        this.nombreTipoCampo = nombreTipoCampo;
        this.requerido = requerido;
        this.json = json;
        this.idDocumento = idDocumento;
        this.nombreDocumento = nombreDocumento;
        this.idCatDoc = idCatDoc;
        this.nombreCatDoc = nombreCatDoc;
    }

    
    public Long getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public Long getIdCategoriaCampo() {
        return idCategoriaCampo;
    }

    public void setIdCategoriaCampo(Long idCategoriaCampo) {
        this.idCategoriaCampo = idCategoriaCampo;
    }

    public String getNombreCategoriaCampo() {
        return nombreCategoriaCampo;
    }

    public void setNombreCategoriaCampo(String nombreCategoriaCampo) {
        this.nombreCategoriaCampo = nombreCategoriaCampo;
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

    public boolean isRequerido() {
        return requerido;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Long getIdCatDoc() {
        return idCatDoc;
    }

    public void setIdCatDoc(Long idCatDoc) {
        this.idCatDoc = idCatDoc;
    }

    public String getNombreCatDoc() {
        return nombreCatDoc;
    }

    public void setNombreCatDoc(String nombreCatDoc) {
        this.nombreCatDoc = nombreCatDoc;
    }

    
    
    
}
