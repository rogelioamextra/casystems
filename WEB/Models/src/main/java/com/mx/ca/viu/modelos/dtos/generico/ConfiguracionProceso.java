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
public class ConfiguracionProceso implements Serializable {

    @JsonProperty("categorias")
    @OneToMany(mappedBy = "categorias")
    private List<CategoriasProceso> categorias;

    /*
    @JsonProperty("validacionesNivelRiesgo")
    @OneToMany(mappedBy = "validacionesNivelRiesgo")
    private List<RiesgoProceso> validacionesNivelRiesgo;
    
    @JsonProperty("mensajes")
    @OneToMany(mappedBy = "mensajes")
    private List<MensajesProceso> mensajes;
     */
    public List<CategoriasProceso> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriasProceso> categorias) {
        this.categorias = categorias;
    }
    
    /*
    public List<RiesgoProceso> getValidacionesNivelRiesgo() {
        return validacionesNivelRiesgo;
    }

    public void setValidacionesNivelRiesgo(List<RiesgoProceso> validacionesNivelRiesgo) {
        this.validacionesNivelRiesgo = validacionesNivelRiesgo;
    }

    public List<MensajesProceso> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajesProceso> mensajes) {
        this.mensajes = mensajes;
    }
     */

}
