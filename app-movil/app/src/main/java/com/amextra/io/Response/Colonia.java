package com.amextra.io.Response;

import java.io.Serializable;

public class Colonia implements Serializable {

    public long idColonia;
    public String identificadorMunicipal;

    public long getId() {
        return idColonia;
    }

    public void setId(long id) {
        this.idColonia = id;
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

    public String nombre;
}
