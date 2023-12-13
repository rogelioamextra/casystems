package com.amextra.io.Response;

import java.io.Serializable;

public class Patrimonio implements Serializable {

    public String idPatrimonio;
    public String nombre;
    public Object descripcion;
    public boolean status;
    public String idAmextra;

    public String getIDPatrimonio() { return idPatrimonio; }
    public void setIDPatrimonio(String value) { this.idPatrimonio = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public Object getDescripcion() { return descripcion; }
    public void setDescripcion(Object value) { this.descripcion = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public String getIDAmextra() { return idAmextra; }
    public void setIDAmextra(String value) { this.idAmextra = value; }
}
