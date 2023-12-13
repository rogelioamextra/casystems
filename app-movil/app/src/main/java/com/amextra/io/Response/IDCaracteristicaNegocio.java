package com.amextra.io.Response;

import java.io.Serializable;

public class IDCaracteristicaNegocio implements Serializable {

    public long idCaracteristicaNegocio;
    public String nombre;

    public boolean status;
    public long        idAmextra;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(long idAmextra) {
        this.idAmextra = idAmextra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public  String descripcion;
}
