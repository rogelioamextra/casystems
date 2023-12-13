/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.DtReferenciasPersonales;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class CatClientesRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;

    public CatClientesRequest() {
        setAuth(new Auth());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("id")
        private Long id;
        @JsonProperty(value = "direccion", required = true)

        private Direcciones direccion;
        @JsonProperty("persona")
        private Personas persona;
        @JsonProperty("datosLaborales")
        private DatosLaborales datosLaborales;
        @JsonProperty("referencias")
        private List<Referencias> referencias;
        @JsonProperty("identificacion")
        private Identificacion identificacion;
        @JsonProperty("status")
        private boolean status;
        @JsonProperty("asesorId")
        private String asesorId;

        public Direcciones getDireccion() {
            return direccion;
        }

        public void setDireccion(Direcciones direccion) {
            this.direccion = direccion;
        }

        public Personas getPersona() {
            return persona;
        }

        public void setPersona(Personas persona) {
            this.persona = persona;
        }

        public DatosLaborales getDatosLaborales() {
            return datosLaborales;
        }

        public void setDatosLaborales(DatosLaborales datosLaborales) {
            this.datosLaborales = datosLaborales;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public List<Referencias> getReferencias() {
            return referencias;
        }

        public void setReferencias(List<Referencias> referencias) {
            this.referencias = referencias;
        }

        public Identificacion getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(Identificacion identificacion) {
            this.identificacion = identificacion;
        }

        public String getAsesorId() {
            return asesorId;
        }

        public void setAsesorId(String asesorId) {
            this.asesorId = asesorId;
        }

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
