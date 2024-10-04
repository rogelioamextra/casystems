package com.amextra.io.Request;

import java.io.Serializable;

public class InformacionAval implements Serializable {
    private String nombreCompleto;
    private String telefono;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
