package com.amextra.io.Response;

import java.io.Serializable;

public class GradosEscolare implements Serializable {
    public long idGradoEstudios;
    public String idEstadoCivil;
    public String nombre;
    public Object descripcion;
    public boolean estatus;

    public long getIdGradoEstudios() {
        return idGradoEstudios;
    }

    public void setIdGradoEstudios(long idGradoEstudios) {
        this.idGradoEstudios = idGradoEstudios;
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
}
