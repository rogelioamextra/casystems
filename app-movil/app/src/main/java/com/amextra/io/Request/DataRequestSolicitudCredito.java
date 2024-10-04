package com.amextra.io.Request;

import java.io.Serializable;
import java.util.ArrayList;

public class DataRequestSolicitudCredito implements Serializable {

    private String clienteId;
    private String frecuenciaPagoId;
    private String productoCreditoId;
    private String destinoCreditoId;
    private String plazo;

    private String latitud;
    private String asesorId;
    private boolean revolvente;
    private String fechaSolicitud;
    private String monto;
    private Egresos_ egresos;
    private ArrayList<PatrimoniosCls> patrimonios;
    private Ingresos_ ingresos;


    private String disseaseDescription;
    private Boolean sick;
    private Boolean confirmaIngresos = false;


    private ArrayList<Aval> avales = new ArrayList<Aval>();

    public ArrayList<Aval> getAvales() {
        return avales;
    }

    public void setAvales(ArrayList<Aval> avales) {
        this.avales = avales;
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


    public String getClienteID() {
        return clienteId;
    }

    public void setClienteID(String value) {
        this.clienteId = value;
    }

    public String getFrecuenciaPagoID() {
        return frecuenciaPagoId;
    }

    public void setFrecuenciaPagoID(String value) {
        this.frecuenciaPagoId = value;
    }

    public String getProductoCreditoID() {
        return productoCreditoId;
    }

    public void setProductoCreditoID(String value) {
        this.productoCreditoId = value;
    }

    public String getDestinoCreditoID() {
        return destinoCreditoId;
    }

    public void setDestinoCreditoID(String value) {
        this.destinoCreditoId = value;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String value) {
        this.plazo = value;
    }

    public boolean getRevolvente() {
        return revolvente;
    }

    public void setRevolvente(boolean value) {
        this.revolvente = value;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String value) {
        this.fechaSolicitud = value;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String value) {
        this.monto = value;
    }

    public Egresos_ getEgresos() {
        return egresos;
    }

    public void setEgresos(Egresos_ value) {
        this.egresos = value;
    }

    public ArrayList<PatrimoniosCls> getPatrimonios() {
        return patrimonios;
    }

    public void setPatrimonios(ArrayList<PatrimoniosCls> value) {
        this.patrimonios = value;
    }

    public Ingresos_ getIngresos() {
        return ingresos;
    }

    public void setIngresos(Ingresos_ value) {
        this.ingresos = value;
    }

    public String getDisseaseDescription() {
        return disseaseDescription;
    }

    public void setDisseaseDescription(String disseaseDescription) {
        this.disseaseDescription = disseaseDescription;
    }

    public Boolean getSick() {
        return sick;
    }

    public void setSick(Boolean sick) {
        this.sick = sick;
    }

    public Boolean getConfirmaIngresos() {
        return confirmaIngresos;
    }

    public void setConfirmaIngresos(Boolean confirmaIngresos) {
        this.confirmaIngresos = confirmaIngresos;
    }


}
