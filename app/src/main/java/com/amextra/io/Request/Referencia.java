package com.amextra.io.Request;

import java.io.Serializable;

public class Referencia implements Serializable {
    public String id;
    public String nombre;
    public String telefono;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public Direccion direccion;
    public long parentescoId;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String value) { this.telefono = value; }

    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion value) { this.direccion = value; }

    public long getParentescoID() { return parentescoId; }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public long getParentescoId() {
        return parentescoId;
    }

    public void setParentescoId(long parentescoId) {
        this.parentescoId = parentescoId;
    }

    public void setParentescoID(long value) { this.parentescoId = value; }
}
