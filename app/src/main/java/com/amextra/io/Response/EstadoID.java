package com.amextra.io.Response;

public class EstadoID {
    public long id;
    public long idEstado;
    public String codigoEstado;

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String inegiClave;
    public String nombre;
    public String estadoClave;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getInegiClave() { return inegiClave; }
    public void setInegiClave(String value) { this.inegiClave = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public String getEstadoClave() { return estadoClave; }
    public void setEstadoClave(String value) { this.estadoClave = value; }
}
