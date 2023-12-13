package com.amextra.io.Response;

import java.io.Serializable;

public class IDCiudad implements Serializable {
    public String id;
    public String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

