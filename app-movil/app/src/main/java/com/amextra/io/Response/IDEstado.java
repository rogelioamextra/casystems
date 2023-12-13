package com.amextra.io.Response;

import java.io.Serializable;

public class IDEstado implements Serializable {
    public long id;
    public long idEstado;
    public String inegiClave;
    public String nombre;
    public String estadoClave;
    public String codigoEstado;

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public long getId() {
        return id;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInegiClave() {
        return inegiClave;
    }

    public void setInegiClave(String inegiClave) {
        this.inegiClave = inegiClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoClave() {
        return estadoClave;
    }

    public void setEstadoClave(String estadoClave) {
        this.estadoClave = estadoClave;
    }
}
