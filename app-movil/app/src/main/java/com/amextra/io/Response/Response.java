package com.amextra.io.Response;

import java.io.Serializable;

public class Response implements Serializable {
    public long codigo;
    public String mensaje;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
