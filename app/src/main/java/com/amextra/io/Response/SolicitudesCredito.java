package com.amextra.io.Response;

import java.io.Serializable;

public class SolicitudesCredito implements Serializable {

    public String producto;
    public String cliente;
    public String estatus;
    public String fecha;
    public String monto;
    public String latitud;
    public String longitud;
    public long idSolicitud;

    public String getProducto() { return producto; }
    public void setProducto(String value) { this.producto = value; }

    public String getCliente() { return cliente; }
    public void setCliente(String value) { this.cliente = value; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String value) { this.estatus = value; }

    public String getFecha() { return fecha; }
    public void setFecha(String value) { this.fecha = value; }

    public String getMonto() { return monto; }
    public void setMonto(String value) { this.monto = value; }

    public String getLatitud() { return latitud; }
    public void setLatitud(String value) { this.latitud = value; }

    public String getLongitud() { return longitud; }
    public void setLongitud(String value) { this.longitud = value; }

    public long getIDSolicitud() { return idSolicitud; }
    public void setIDSolicitud(long value) { this.idSolicitud = value; }
}
