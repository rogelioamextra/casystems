/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jbecerril
 */
public class Direcciones {
    
    private Long id;
    @JsonProperty(required = true)
    private String calle;
    
    private String numeroInterior;
    
    private String numeroExterior;
    
    private String cp;
    
    private short tiempoResidencia;
    
    private short tiempoResidenciaMeses;
    
    private String comprobante;
    
    private String geolocalizacionLatitud;
    
    private String geolocalizacionLongitud;
    
    private Long tipoResidenciaId;
    
    private Long tipoViviendaId;
    

    
    private Long coloniaId;
    
    private Long estadoId;
    
    private Long municipioId;
    private boolean banderaCambioImagen;


    public Direcciones() {
    }

    public boolean isBanderaCambioImagen() {
        return banderaCambioImagen;
    }

    public void setBanderaCambioImagen(boolean banderaCambioImagen) {
        this.banderaCambioImagen = banderaCambioImagen;
    }
    
    

    public String getCalle() {
        return calle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public short getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(short tiempoResidencia) {
        this.tiempoResidencia = tiempoResidencia;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getGeolocalizacionLatitud() {
        return geolocalizacionLatitud;
    }

    public void setGeolocalizacionLatitud(String geolocalizacionLatitud) {
        this.geolocalizacionLatitud = geolocalizacionLatitud;
    }

    public String getGeolocalizacionLongitud() {
        return geolocalizacionLongitud;
    }

    public void setGeolocalizacionLongitud(String geolocalizacionLongitud) {
        this.geolocalizacionLongitud = geolocalizacionLongitud;
    }

    public Long getTipoResidenciaId() {
        return tipoResidenciaId;
    }

    public void setTipoResidenciaId(Long tipoResidenciaId) {
        this.tipoResidenciaId = tipoResidenciaId;
    }

  

    public Long getTipoViviendaId() {
        return tipoViviendaId;
    }

    public void setTipoViviendaId(Long tipoViviendaId) {
        this.tipoViviendaId = tipoViviendaId;
    }


  
  

    public Long getColoniaId() {
        return coloniaId;
    }

    public void setColoniaId(Long coloniaId) {
        this.coloniaId = coloniaId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public short getTiempoResidenciaMeses() {
        return tiempoResidenciaMeses;
    }

    public void setTiempoResidenciaMeses(short tiempoResidenciaMeses) {
        this.tiempoResidenciaMeses = tiempoResidenciaMeses;
    }

  

   
    

}
