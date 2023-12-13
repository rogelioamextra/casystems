/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

/**
 *
 * @author jbecerril
 */
public class DatosLaborales {

    private Long id;

    private String puesto;

    private String telefono;

    private boolean recibosNomina;

    private float ingresoMensual;

    private String fechaIngreso;
    private Direcciones direccion; 

    private Long giroNegocioId;
    private Long caracteristicasNegocioId;
    private Long tiempoEmpleoActualId;

    public DatosLaborales() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   

    public boolean isRecibosNomina() {
        return recibosNomina;
    }

    public void setRecibosNomina(boolean recibosNomina) {
        this.recibosNomina = recibosNomina;
    }

    public float getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(float ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Direcciones getDireccion() {
        return direccion;
    }

    public void setDireccion(Direcciones direccion) {
        this.direccion = direccion;
    }

    public Long getGiroNegocioId() {
        return giroNegocioId;
    }

    public void setGiroNegocioId(Long giroNegocioId) {
        this.giroNegocioId = giroNegocioId;
    }

    public Long getCaracteristicasNegocioId() {
        return caracteristicasNegocioId;
    }

    public void setCaracteristicasNegocioId(Long caracteristicasNegocioId) {
        this.caracteristicasNegocioId = caracteristicasNegocioId;
    }

    public Long getTiempoEmpleoActualId() {
        return tiempoEmpleoActualId;
    }

    public void setTiempoEmpleoActualId(Long tiempoEmpleoActualId) {
        this.tiempoEmpleoActualId = tiempoEmpleoActualId;
    }

   
    

}
