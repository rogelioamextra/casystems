package com.amextra.io.Response;

import java.io.Serializable;

public class IdMaxEstudios implements Serializable {
    public String idGradoEstudios;
    public String nombre;
    public String descripcion;
    public String estatus;

    public String getIdGradoEstudios() {
        return idGradoEstudios;
    }

    public void setIdGradoEstudios(String idGradoEstudios) {
        this.idGradoEstudios = idGradoEstudios;
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
