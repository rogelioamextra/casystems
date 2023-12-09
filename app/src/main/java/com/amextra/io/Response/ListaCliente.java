package com.amextra.io.Response;

public class ListaCliente {
    public String nombre;
    public String id;
    public String curp;



    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaCliente(String nombre, String id, String curp) {
        this.nombre = nombre;
        this.id = id;
        this.curp = curp;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ListaCliente{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", curp='" + curp + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurp() { return curp; }
    public void setCurp(String value) { this.curp = value; }
}
