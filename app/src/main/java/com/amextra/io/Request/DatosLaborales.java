package com.amextra.io.Request;

import java.io.Serializable;

public class DatosLaborales implements Serializable {

    public long id;
    public String puesto;

    public String telefono;
    public boolean recibosNomina;
    public String ingresoMensual;
    public String fechaIngreso;
    public boolean mismaDireccion;
    public Direccion direccion;
    public long giroNegocioId;

    public Long caracteristicasNegocioId;

    public boolean isRecibosNomina() {
        return recibosNomina;
    }

    public boolean isMismaDireccion() {
        return mismaDireccion;
    }

    public long getGiroNegocioId() {
        return giroNegocioId;
    }

    public void setGiroNegocioId(long giroNegocioId) {
        this.giroNegocioId = giroNegocioId;
    }

    public Long getCaracteristicasNegocioId() {
        return caracteristicasNegocioId;
    }

    public void setCaracteristicasNegocioId(Long caracteristicasNegocioId) {
        this.caracteristicasNegocioId = caracteristicasNegocioId;
    }

    public String getTiempoEmpleoActualId() {
        return tiempoEmpleoActualId;
    }

    public void setTiempoEmpleoActualId(String tiempoEmpleoActualId) {
        this.tiempoEmpleoActualId = tiempoEmpleoActualId;
    }

    public String tiempoEmpleoActualId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String value) { this.puesto = value; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String value) { this.telefono = value; }

    public boolean getRecibosNomina() { return recibosNomina; }
    public void setRecibosNomina(boolean value) { this.recibosNomina = value; }

    public String getIngresoMensual() { return ingresoMensual; }
    public void setIngresoMensual(String value) { this.ingresoMensual = value; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String value) { this.fechaIngreso = value; }

    public boolean getMismaDireccion() { return mismaDireccion; }
    public void setMismaDireccion(boolean value) { this.mismaDireccion = value; }

    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion value) { this.direccion = value; }

    public long getGiroNegocioID() { return giroNegocioId; }
    public void setGiroNegocioID(long value) { this.giroNegocioId = value; }
}
