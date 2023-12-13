package com.amextra.io.Response;

import java.io.Serializable;

public class DtReferenciasPersonalesList implements Serializable {

    public long idReferencia;
    public String nombreCompleto;
    public String apellidoMaterno;
    public String apellidoPaterno;
    public String telefono;
    public boolean status;
    public IDDireccion idDireccion;
    public IDParentesco idParentesco;

    public long getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(long idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public boolean isStatus() {
        return status;
    }

    public IDDireccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(IDDireccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    public IDParentesco getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(IDParentesco idParentesco) {
        this.idParentesco = idParentesco;
    }

    public long getIDReferencia() { return idReferencia; }
    public void setIDReferencia(long value) { this.idReferencia = value; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String value) { this.nombreCompleto = value; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String value) { this.telefono = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public IDDireccion getIDDireccion() { return idDireccion; }
    public void setIDDireccion(IDDireccion value) { this.idDireccion = value; }

    public IDParentesco getIDParentesco() { return idParentesco; }
    public void setIDParentesco(IDParentesco value) { this.idParentesco = value; }


}
