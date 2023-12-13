package com.amextra.io.Request;

import com.amextra.io.Response.Patrimonio;

import java.io.Serializable;
import java.util.ArrayList;

public class DataRequestSolicitudCredito implements Serializable {

    public String clienteId;
    public String frecuenciaPagoId;
    public String productoCreditoId;
    public String destinoCreditoId;
    public String plazo;

    public String latitud;
    public String asesorId;
    public boolean revolvente;
    public String fechaSolicitud;
    public String monto;
    public Egresos_ egresos;
    public ArrayList<PatrimoniosCls> patrimonios;
    public Ingresos_ ingresos;

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

    public String longitud;
    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getFrecuenciaPagoId() {
        return frecuenciaPagoId;
    }

    public void setFrecuenciaPagoId(String frecuenciaPagoId) {
        this.frecuenciaPagoId = frecuenciaPagoId;
    }

    public String getProductoCreditoId() {
        return productoCreditoId;
    }

    public void setProductoCreditoId(String productoCreditoId) {
        this.productoCreditoId = productoCreditoId;
    }

    public String getDestinoCreditoId() {
        return destinoCreditoId;
    }

    public void setDestinoCreditoId(String destinoCreditoId) {
        this.destinoCreditoId = destinoCreditoId;
    }

    public String getAsesorId() {
        return asesorId;
    }

    public void setAsesorId(String asesorId) {
        this.asesorId = asesorId;
    }

    public boolean isRevolvente() {
        return revolvente;
    }



    public String getClienteID() { return clienteId; }
    public void setClienteID(String value) { this.clienteId = value; }

    public String getFrecuenciaPagoID() { return frecuenciaPagoId; }
    public void setFrecuenciaPagoID(String value) { this.frecuenciaPagoId = value; }

    public String getProductoCreditoID() { return productoCreditoId; }
    public void setProductoCreditoID(String value) { this.productoCreditoId = value; }

    public String getDestinoCreditoID() { return destinoCreditoId; }
    public void setDestinoCreditoID(String value) { this.destinoCreditoId = value; }

    public String getPlazo() { return plazo; }
    public void setPlazo(String value) { this.plazo = value; }

    public boolean getRevolvente() { return revolvente; }
    public void setRevolvente(boolean value) { this.revolvente = value; }

    public String getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(String value) { this.fechaSolicitud = value; }

    public String getMonto() { return monto; }
    public void setMonto(String value) { this.monto = value; }

    public Egresos_ getEgresos() { return egresos; }
    public void setEgresos(Egresos_ value) { this.egresos = value; }

    public ArrayList<PatrimoniosCls> getPatrimonios() { return patrimonios; }
    public void setPatrimonios(ArrayList<PatrimoniosCls> value) { this.patrimonios = value; }

    public Ingresos_ getIngresos() { return ingresos; }
    public void setIngresos(Ingresos_ value) { this.ingresos = value; }
}
