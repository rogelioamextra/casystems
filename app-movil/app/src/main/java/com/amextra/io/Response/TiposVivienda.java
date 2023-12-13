package com.amextra.io.Response;

import java.io.Serializable;

public class TiposVivienda implements Serializable {


    public long idVivienda;
    public String nombre;
    public String descripcion;

    public long getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(long idVivienda) {
        this.idVivienda = idVivienda;
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

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public boolean estatus;
}
