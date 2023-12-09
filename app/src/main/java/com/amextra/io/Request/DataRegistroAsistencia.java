package com.amextra.io.Request;

import java.io.Serializable;

public class DataRegistroAsistencia implements Serializable {
    public long idAgenda;
    public String estatus;
    public String latitud;
    public String longitud;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
