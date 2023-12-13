package com.amextra.io.Response;

import java.io.Serializable;

public class Ciudade implements Serializable {
    public long id;
    public String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
