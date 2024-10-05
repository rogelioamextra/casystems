package com.amextra.io.Request;

import java.io.Serializable;

public class InformacionAval implements Serializable {
    private String nombreCompleto;
    private String telefono;
    private boolean confimaSms;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean isConfimaSms() {
        return confimaSms;
    }

    public void setConfimaSms(boolean confimaSms) {
        this.confimaSms = confimaSms;
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
