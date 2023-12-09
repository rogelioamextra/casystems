package com.amextra.io.Response;

import java.io.Serializable;

public class TiempoEmpleoActualId implements Serializable {
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(long idAmextra) {
        this.idAmextra = idAmextra;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String descripcion;
    public long idAmextra;
    public long idTiempoEmpleoActual;
        public String            nombre;
    public boolean status;
}
