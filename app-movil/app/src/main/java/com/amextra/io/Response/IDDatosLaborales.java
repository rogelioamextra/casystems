package com.amextra.io.Response;

import java.io.Serializable;

public class IDDatosLaborales implements Serializable {
    public long idDatosLaborales;
    public String puesto;
    public long telefono;
    public boolean recibosNomina;
    public double ingresoMensual;
    public String fechaIngreso;
    public IDDireccion idDireccion;
    public IDGiroNegocio idGiroNegocio;
    public IDCaracteristicaNegocio idCaracteristicaNegocio;

    public TiempoEmpleoActualId idTiempoEmpleoActual;

    public long getIdDatosLaborales() {
        return idDatosLaborales;
    }

    public void setIdDatosLaborales(long idDatosLaborales) {
        this.idDatosLaborales = idDatosLaborales;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public boolean isRecibosNomina() {
        return recibosNomina;
    }

    public void setRecibosNomina(boolean recibosNomina) {
        this.recibosNomina = recibosNomina;
    }

    public double getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(double ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public IDDireccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(IDDireccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    public IDGiroNegocio getIdGiroNegocio() {
        return idGiroNegocio;
    }

    public void setIdGiroNegocio(IDGiroNegocio idGiroNegocio) {
        this.idGiroNegocio = idGiroNegocio;
    }
}
