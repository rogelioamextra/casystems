package com.amextra.io.Response;

import java.io.Serializable;

public class Producto implements Serializable {
    public long idProductosCredito;
    public String clave;

    public long getIdProductosCredito() {
        return idProductosCredito;
    }

    public void setIdProductosCredito(long idProductosCredito) {
        this.idProductosCredito = idProductosCredito;
    }

    public boolean isStatus() {
        return status;
    }

    public String nombre;
    public boolean status;

    public long getIDProductosCredito() { return idProductosCredito; }
    public void setIDProductosCredito(long value) { this.idProductosCredito = value; }

    public String getClave() { return clave; }
    public void setClave(String value) { this.clave = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }
}
