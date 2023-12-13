package com.amextra.io.Response;

import java.io.Serializable;

public class DestinosCredito implements Serializable {

    public long idDestinoCredito;
    public String nombre;
    public String descripcion;
    public boolean status;
    public String idAmextra;

    public long getIDDestinoCredito() { return idDestinoCredito; }
    public void setIDDestinoCredito(long value) { this.idDestinoCredito = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String value) { this.descripcion = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public String getIDAmextra() { return idAmextra; }
    public void setIDAmextra(String value) { this.idAmextra = value; }
}
