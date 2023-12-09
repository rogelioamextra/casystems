package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseSMS implements Serializable {
    public long codigo;
    public String mensaje;

    public long getCodigo() { return codigo; }
    public void setCodigo(long value) { this.codigo = value; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String value) { this.mensaje = value; }
}
