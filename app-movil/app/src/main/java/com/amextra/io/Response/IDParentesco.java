package com.amextra.io.Response;

import java.io.Serializable;

public class IDParentesco implements Serializable {

    public String getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(String idParentesco) {
        this.idParentesco = idParentesco;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(String idAmextra) {
        this.idAmextra = idAmextra;
    }

    public String idParentesco;
    public String nombre;
    public String descripcion;
    public boolean status;
    public String idAmextra;
}
