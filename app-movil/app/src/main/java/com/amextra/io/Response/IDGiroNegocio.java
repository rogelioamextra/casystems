package com.amextra.io.Response;

import java.io.Serializable;

public class IDGiroNegocio implements Serializable {
    public String idGirosNegocio;
    public String nombre;
    public String descripcion;
    public String estatus;

    public String getIdGirosNegocio() {
        return idGirosNegocio;
    }

    public void setIdGirosNegocio(String idGirosNegocio) {
        this.idGirosNegocio = idGirosNegocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
