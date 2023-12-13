package com.amextra.io.Response;

import java.io.Serializable;

public class CaracteristicasNegocio implements Serializable {
    public long idCaracteristicaNegocio;
    public String nombre;
    public Object descripcion;

    public long getIdCaracteristicaNegocio() {
        return idCaracteristicaNegocio;
    }

    public void setIdCaracteristicaNegocio(long idCaracteristicaNegocio) {
        this.idCaracteristicaNegocio = idCaracteristicaNegocio;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean status;
}
