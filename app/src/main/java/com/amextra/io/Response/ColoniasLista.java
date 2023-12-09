package com.amextra.io.Response;

public class ColoniasLista {
    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(long idColonia) {
        this.idColonia = idColonia;
    }

    public long idColonia;
    public String identificadorMunicipal;
    public String nombre;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getIdentificadorMunicipal() { return identificadorMunicipal; }
    public void setIdentificadorMunicipal(String value) { this.identificadorMunicipal = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }
}
