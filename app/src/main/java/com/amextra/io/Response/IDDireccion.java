package com.amextra.io.Response;

import java.io.Serializable;

public class IDDireccion  implements Serializable {
    public long idDireccion;
    public String calle;
    public String numeroInterior;
    public String numeroExterior;
    public String cp;
    public String nombreArchivo;

    public long tiempoResidencia;

    public long getTiempoResidenciaMeses() {
        return tiempoResidenciaMeses;
    }

    public void setTiempoResidenciaMeses(long tiempoResidenciaMeses) {
        this.tiempoResidenciaMeses = tiempoResidenciaMeses;
    }

    public long tiempoResidenciaMeses;
    public String comprobante;
    public String geolocalizacionLatitud;
    public String geolocalizacionLongitud;
    public IDTipoResidencia idTipoResidencia;
    public IDEstado idEstado;
    public IDMunicipio idMunicipio;
    public  IDColonia idColonia;
    public IDidTipoVivienda idTipoVivienda;

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String codigoEstado;

    public IDMunicipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(IDMunicipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public IDColonia getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(IDColonia idColonia) {
        this.idColonia = idColonia;
    }

    public boolean isBanderaCambioImagen() {
        return banderaCambioImagen;
    }

    public void setBanderaCambioImagen(boolean banderaCambioImagen) {
        this.banderaCambioImagen = banderaCambioImagen;
    }

    public boolean banderaCambioImagen;
    public long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public long getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(long tiempoResidencia) {
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

    public IDTipoResidencia getIdTipoResidencia() {
        return idTipoResidencia;
    }

    public void setIdTipoResidencia(IDTipoResidencia idTipoResidencia) {
        this.idTipoResidencia = idTipoResidencia;
    }

    public IDidTipoVivienda getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(IDidTipoVivienda idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }
    public IDEstado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(IDEstado idEstado) {
        this.idEstado = idEstado;
    }

}
