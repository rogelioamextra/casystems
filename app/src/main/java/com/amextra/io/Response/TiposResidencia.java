package com.amextra.io.Response;

import java.io.Serializable;

public class TiposResidencia implements Serializable {
    public long idTipoResidencia;
    public String nombre;

    public long getIdTipoResidencia() {
        return idTipoResidencia;
    }

    public void setIdTipoResidencia(long idTipoResidencia) {
        this.idTipoResidencia = idTipoResidencia;
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
