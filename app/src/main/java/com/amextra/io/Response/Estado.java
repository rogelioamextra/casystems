package com.amextra.io.Response;

import java.io.Serializable;

public class Estado implements Serializable {

    public long idEstado;
    public String inegiClave;
    public String nombre;
    public  String codigoEstado;

    public long getId() {
        return idEstado;
    }

    public void setId(long id) {
        this.idEstado = id;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
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

    public String estadoClave;
}
