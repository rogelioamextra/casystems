package com.amextra.io.Response;

import java.io.Serializable;

public class TieposActuale implements Serializable {

    public long idTiempoEmpleoActual;

    public long getIdTiempoEmpleoActual() {
        return idTiempoEmpleoActual;
    }

    public void setIdTiempoEmpleoActual(long idTiempoEmpleoActual) {
        this.idTiempoEmpleoActual = idTiempoEmpleoActual;
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

    public String nombre;
    public Object descripcion;
    public boolean status;
}
