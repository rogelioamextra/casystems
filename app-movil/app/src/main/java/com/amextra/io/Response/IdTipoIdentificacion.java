package com.amextra.io.Response;

import java.io.Serializable;

public class IdTipoIdentificacion implements Serializable {
    public String idIdentificaciones;
    public String nombre;
    public String descripcion;
    public String status;

    public String getIdIdentificaciones() {
        return idIdentificaciones;
    }

    public void setIdIdentificaciones(String idIdentificaciones) {
        this.idIdentificaciones = idIdentificaciones;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(String idAmextra) {
        this.idAmextra = idAmextra;
    }

    public String idAmextra;

}
