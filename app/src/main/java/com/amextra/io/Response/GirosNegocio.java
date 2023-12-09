package com.amextra.io.Response;

public class GirosNegocio {
    public long idGirosNegocio;
    public String nombre;

    public long getIdGirosNegocio() {
        return idGirosNegocio;
    }

    public void setIdGirosNegocio(long idGirosNegocio) {
        this.idGirosNegocio = idGirosNegocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Object descripcion;
    public boolean estatus;
}
