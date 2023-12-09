package com.amextra.io.Response;

import java.io.Serializable;

public class IDidNacionalidad implements Serializable {
    public long idNacionalidad;
    public String nombre;
    public Object descripcion;
    public long codigoPais;
    public String claveNacionalidad;
    public boolean estatus;

    public long getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(long idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
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

    public long getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(long codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getClaveNacionalidad() {
        return claveNacionalidad;
    }

    public void setClaveNacionalidad(String claveNacionalidad) {
        this.claveNacionalidad = claveNacionalidad;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
}
