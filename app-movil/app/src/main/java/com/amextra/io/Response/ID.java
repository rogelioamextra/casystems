package com.amextra.io.Response;

public class ID {
    public long id;
    public String nombre;
    public long idMunicipio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }
}
