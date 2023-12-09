package com.amextra.io.Response;

public class Identificacione {
    public long idIdentificaciones;
    public String nombre;
    public Object descripcion;
    public boolean status;
    public long idAmextra;

    public long getIDIdentificaciones() { return idIdentificaciones; }
    public void setIDIdentificaciones(long value) { this.idIdentificaciones = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public Object getDescripcion() { return descripcion; }
    public void setDescripcion(Object value) { this.descripcion = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public long getIDAmextra() { return idAmextra; }
    public void setIDAmextra(long value) { this.idAmextra = value; }
}
