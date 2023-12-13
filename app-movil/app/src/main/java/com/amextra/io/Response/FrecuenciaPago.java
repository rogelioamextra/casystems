package com.amextra.io.Response;

public class FrecuenciaPago {

    public long idFrecuenciaPago;
    public String nombre;
    public Object descripcion;
    public boolean status;
    public String clave;
    public Object idAmextra;

    public long getIDFrecuenciaPago() { return idFrecuenciaPago; }
    public void setIDFrecuenciaPago(long value) { this.idFrecuenciaPago = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public Object getDescripcion() { return descripcion; }
    public void setDescripcion(Object value) { this.descripcion = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public String getClave() { return clave; }
    public void setClave(String value) { this.clave = value; }

    public Object getIDAmextra() { return idAmextra; }
    public void setIDAmextra(Object value) { this.idAmextra = value; }
}
