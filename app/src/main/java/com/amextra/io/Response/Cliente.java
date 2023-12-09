package com.amextra.io.Response;

import java.io.Serializable;

public class Cliente implements Serializable {
    public long idCliente;
    public boolean aproboVerificacionSMS;

    public boolean status;
    public IDDireccion idDireccion;
    public IDPersona idPersona;
    public IDDatosLaborales idDatosLaborales;
    public DtReferenciasPersonalesList[] dtReferenciasPersonalesList;

    public IdDtIdentificacion idDtIdentificacion;
    public IdTipoIdentificacion idTipoIdentificacion;



    public boolean isAproboVerificacionSMS() {
        return aproboVerificacionSMS;
    }
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public IDDireccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(IDDireccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    public IDPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(IDPersona idPersona) {
        this.idPersona = idPersona;
    }

    public IDDatosLaborales getIdDatosLaborales() {
        return idDatosLaborales;
    }

    public void setIdDatosLaborales(IDDatosLaborales idDatosLaborales) {
        this.idDatosLaborales = idDatosLaborales;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public boolean getAproboVerificacionSMS() {
        return aproboVerificacionSMS;
    }

    public void setAproboVerificacionSMS(boolean value) {
        this.aproboVerificacionSMS = value;
    }

    public DtReferenciasPersonalesList[] getDtReferenciasPersonalesList() {
        return dtReferenciasPersonalesList;
    }

    public void setDtReferenciasPersonalesList(DtReferenciasPersonalesList[] value) {
        this.dtReferenciasPersonalesList = value;
    }


}
