package com.amextra.io.Response;

import java.io.Serializable;

public class IDColonia implements Serializable {
    public long id;
    public long idColonia;
    public String identificadorMunicipal;
    public String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(long idColonia) {
        this.idColonia = idColonia;
    }

    public String getIdentificadorMunicipal() {
        return identificadorMunicipal;
    }

    public void setIdentificadorMunicipal(String identificadorMunicipal) {
        this.identificadorMunicipal = identificadorMunicipal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
