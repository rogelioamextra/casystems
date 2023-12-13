package com.amextra.io.Request;

import java.io.Serializable;

public class DataRequestProyeccion implements Serializable {

    public String montoCredito;
    public String numeroCuotas;
    public String frecuenciaId;
    public String productoId;

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getFrecuenciaId() {
        return frecuenciaId;
    }

    public void setFrecuenciaId(String frecuenciaId) {
        this.frecuenciaId = frecuenciaId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String clienteId;

    public String getMontoCredito() { return montoCredito; }
    public void setMontoCredito(String value) { this.montoCredito = value; }

    public String getNumeroCuotas() { return numeroCuotas; }
    public void setNumeroCuotas(String value) { this.numeroCuotas = value; }

    public String getFrecuenciaID() { return frecuenciaId; }
    public void setFrecuenciaID(String value) { this.frecuenciaId = value; }
}
