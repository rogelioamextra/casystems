package com.amextra.io.Request;

import com.amextra.io.Response.IDEstado;

import java.io.Serializable;

public class Direccion implements Serializable {
    public long id;
    public String calle;
    public String numeroInterior;
    public String numeroExterior;
    public String cp;
    public long tiempoResidencia;
    public long tiempoResidenciaMeses;
    public String comprobante;

    public IDEstado idEstado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTiempoResidenciaMes() {
        return tiempoResidenciaMeses;
    }

    public void setTiempoResidenciaMes(long tiempoResidenciaMes) {
        this.tiempoResidenciaMeses = tiempoResidenciaMes;
    }

    public long getTipoResidenciaId() {
        return tipoResidenciaId;
    }

    public void setTipoResidenciaId(long tipoResidenciaId) {
        this.tipoResidenciaId = tipoResidenciaId;
    }

    public long getTipoViviendaId() {
        return tipoViviendaId;
    }

    public void setTipoViviendaId(long tipoViviendaId) {
        this.tipoViviendaId = tipoViviendaId;
    }

    public long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(long ciudadId) {
        this.ciudadId = ciudadId;
    }

    public long getColoniaId() {
        return coloniaId;
    }

    public void setColoniaId(long coloniaId) {
        this.coloniaId = coloniaId;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }

    public long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(long municipioId) {
        this.municipioId = municipioId;
    }

    public boolean isBanderaCambioImagen() {
        return banderaCambioImagen;
    }

    public String geolocalizacionLatitud;
    public String geolocalizacionLongitud;
    public long tipoResidenciaId;
    public long tipoViviendaId;
    public long ciudadId;
    public long coloniaId;
    public String estadoId;
    public long municipioId;
    public boolean banderaCambioImagen;



    public long getTiempoResidenciaMeses() {
        return tiempoResidenciaMeses;
    }

    public void setTiempoResidenciaMeses(long tiempoResidenciaMeses) {
        this.tiempoResidenciaMeses = tiempoResidenciaMeses;
    }



    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getCalle() { return calle; }
    public void setCalle(String value) { this.calle = value; }

    public String getNumeroInterior() { return numeroInterior; }
    public void setNumeroInterior(String value) { this.numeroInterior = value; }

    public Object getNumeroExterior() { return numeroExterior; }
    public void setNumeroExterior(String value) { this.numeroExterior = value; }

    public String getCp() { return cp; }
    public void setCp(String value) { this.cp = value; }

    public long getTiempoResidencia() { return tiempoResidencia; }
    public void setTiempoResidencia(long value) { this.tiempoResidencia = value; }

    public String getComprobante() { return comprobante; }
    public void setComprobante(String value) { this.comprobante = value; }

    public String getGeolocalizacionLatitud() { return geolocalizacionLatitud; }
    public void setGeolocalizacionLatitud(String value) { this.geolocalizacionLatitud = value; }

    public String getGeolocalizacionLongitud() { return geolocalizacionLongitud; }
    public void setGeolocalizacionLongitud(String value) { this.geolocalizacionLongitud = value; }

    public long getTipoResidenciaID() { return tipoResidenciaId; }
    public void setTipoResidenciaID(long value) { this.tipoResidenciaId = value; }

    public long getCiudadID() { return ciudadId; }
    public void setCiudadID(long value) { this.ciudadId = value; }

    public long getColoniaID() { return coloniaId; }
    public void setColoniaID(long value) { this.coloniaId = value; }

    public String getEstadoID() { return estadoId; }
    public void setEstadoID(String value) { this.estadoId = value; }

    public long getMunicipioID() { return municipioId; }
    public void setMunicipioID(long value) { this.municipioId = value; }

    public boolean getBanderaCambioImagen() { return banderaCambioImagen; }
    public void setBanderaCambioImagen(boolean value) { this.banderaCambioImagen = value; }
}