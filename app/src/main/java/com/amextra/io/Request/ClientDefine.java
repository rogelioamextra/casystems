package com.amextra.io.Request;

import java.io.Serializable;

public class ClientDefine implements Serializable {
    public String nombres;
    public String apellidoPaterno;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getGetApellidoMaterno() {
        return getApellidoMaterno;
    }

    public void setGetApellidoMaterno(String getApellidoMaterno) {
        this.getApellidoMaterno = getApellidoMaterno;
    }

    public String getApellidoMaterno;
}
