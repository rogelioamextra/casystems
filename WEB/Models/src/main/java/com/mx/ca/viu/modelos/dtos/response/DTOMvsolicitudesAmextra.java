/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import java.util.Date;

/**
 *
 * @author jbecerril
 */
public class DTOMvsolicitudesAmextra {

    private String producto;
    private String cliente;
    private String estatus;
    private Date fecha;
    private String monto;
    private String latitud;
    private String longitud;
    private Long idSolicitud;

    public DTOMvsolicitudesAmextra() {
    }

    public DTOMvsolicitudesAmextra(String producto, String cliente, String estatus, Date fecha, String monto) {
        this.producto = producto;
        this.cliente = cliente;
        this.estatus = estatus;
        this.fecha = fecha;
        this.monto = monto;
    }

    

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    
    
   

}
