package com.amextra.io.Request;

import java.io.Serializable;

public class DataReqCliente implements Serializable {
    public String id;
    public Direccion direccion;
    public String asesorId;
    public Persona persona;


    public String getAsesorId() {
        return asesorId;
    }

    public void setAsesorId(String asesorId) {
        this.asesorId = asesorId;
    }

    public DatosLaborales datosLaborales;

    public Identificacion identificacion;

    public boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Identificacion getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Identificacion identificacion) {
        this.identificacion = identificacion;
    }

    public Referencia[] getReferencias() {
        return referencias;
    }

    public void setReferencias(Referencia[] referencias) {
        this.referencias = referencias;
    }

    public boolean isStatus() {
        return status;
    }

    public Referencia[]  referencias;



    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public Direccion getDireccion() { return direccion; }


    public void setDireccion(Direccion value) { this.direccion = value; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona value) { this.persona = value; }

    public DatosLaborales getDatosLaborales() { return datosLaborales; }
    public void setDatosLaborales(DatosLaborales value) { this.datosLaborales = value; }

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }
}
