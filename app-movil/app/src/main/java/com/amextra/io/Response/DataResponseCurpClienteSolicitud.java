package com.amextra.io.Response;

import java.io.Serializable;

public class DataResponseCurpClienteSolicitud implements Serializable {
    public long idCliente;
    public String nombres;
    public String apellidoPaterno;
    public String apellidoMaterno;

    public long getIDCliente() { return idCliente; }
    public void setIDCliente(long value) { this.idCliente = value; }

    public String getNombres() { return nombres; }
    public void setNombres(String value) { this.nombres = value; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String value) { this.apellidoPaterno = value; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String value) { this.apellidoMaterno = value; }
}
