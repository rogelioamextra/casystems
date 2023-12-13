package com.amextra.io.Response;

import java.io.Serializable;

public class Parentesco  implements Serializable {

    public long idParentesco;
    public String nombre;
    public Object descripcion;
    public boolean status;
    public Object idAmextra;

    public long getIDParentesco() { return idParentesco; }
    public void setIDParentesco(long value) { this.idParentesco = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public Object getDescripcion() { return descripcion; }
    public void setDescripcion(Object value) { this.descripcion = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public Object getIDAmextra() { return idAmextra; }
    public void setIDAmextra(Object value) { this.idAmextra = value; }
}
