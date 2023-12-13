/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Lob;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service
public class DocumentosProceso implements Serializable {

    @JsonProperty("idDocumento")
    private Long idDocumentos;
    @JsonProperty("nombreDocumento")
    private String nombre;
    @JsonProperty("idTipoDato")
    private Long idTipoDato;

    @JsonProperty("validacionesServicio")
    List<ValidacionesServicio> validacionesServicio;

    public static class ValidacionesServicio {

        @JsonProperty("url")
        private String url;
        @Lob
        @Type(type = "jsonb")
        @JsonProperty("request")
        private Object request;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getRequest() {
            return request;
        }

        public void setRequest(Object request) {
            this.request = request;
        }

    }

    //@JsonProperty("validaciones")
    //private List<ValidacionesProceso> validaciones;

    public DocumentosProceso() {
        this.validacionesServicio = new ArrayList<>();
    }

    public Long getIdDocumentos() {
        return idDocumentos;
    }

    public void setIdDocumentos(Long idDocumentos) {
        this.idDocumentos = idDocumentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /*
    public List<ValidacionesProceso> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<ValidacionesProceso> validaciones) {
        this.validaciones = validaciones;
    }
    */
    public List<ValidacionesServicio> getValidacionesServicio() {
        return validacionesServicio;
    }

    public void setValidacionesServicio(List<ValidacionesServicio> validacionesServicio) {
        this.validacionesServicio = validacionesServicio;
    }

    public Long getIdTipoDato() {
        return idTipoDato;
    }

    public void setIdTipoDato(Long idTipoDato) {
        this.idTipoDato = idTipoDato;
    }
    
    
}
