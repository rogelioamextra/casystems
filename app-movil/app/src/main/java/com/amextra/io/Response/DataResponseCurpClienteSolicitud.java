package com.amextra.io.Response;

import java.io.Serializable;

public class DataResponseCurpClienteSolicitud implements Serializable {
    private long idCliente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private boolean aproboVerificacionSms;
    private String telefono;

    public long getIDCliente() {
        return idCliente;
    }

    public void setIDCliente(long value) {
        this.idCliente = value;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String value) {
        this.nombres = value;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    public boolean isAproboVerificacionSms() {
        return aproboVerificacionSms;
    }

    public void setAproboVerificacionSms(boolean aproboVerificacionSms) {
        this.aproboVerificacionSms = aproboVerificacionSms;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
