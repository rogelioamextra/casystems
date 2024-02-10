/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;

/**
 *
 * @author mramirez
 */
public class ClienteByCurpResponse extends GenericResponse implements Serializable{
    @JsonProperty("data")
    Data data;

    public ClienteByCurpResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("idCliente")
        private Long idCliente;

        @JsonProperty("nombres")
        private String nombres;
        
        @JsonProperty("apellidoPaterno")
        private String apellidoPaterno;
        
        @JsonProperty("apellidoMaterno")
        private String apellidoMaterno;
        
        @JsonProperty("aproboVerificacionSms")
        private boolean aproboVerificacionSms;
        
        @JsonProperty("telefono")
        private String telefono;

        public Long getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(Long idCliente) {
            this.idCliente = idCliente;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getApellidoPaterno() {
            return apellidoPaterno;
        }

        public void setApellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
        }

        public String getApellidoMaterno() {
            return apellidoMaterno;
        }

        public void setApellidoMaterno(String apellidoMaterno) {
            this.apellidoMaterno = apellidoMaterno;
        }

        public boolean isAproboVerificacionSms() {
            return aproboVerificacionSms;
        }

        public void setAproboVerificacionSms(boolean aproboVerificacionSms) {
            this.aproboVerificacionSms = aproboVerificacionSms;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
        
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
