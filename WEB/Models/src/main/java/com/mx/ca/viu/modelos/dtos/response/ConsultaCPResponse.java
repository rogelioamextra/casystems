/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoColonia;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class ConsultaCPResponse extends GenericResponse implements Serializable{

    @JsonProperty("data")
    Data data;
    
    public ConsultaCPResponse(){
        setResponse(new GenericResponse.Response());
        this.data = new Data();
    }
    
    public static class Data implements Serializable{
    
        @JsonProperty("estado")
        private String estado;
    
        @JsonProperty("idEstado")
        private int idEstado;
    
        @JsonProperty("ciudad")
        private String ciudad;
    
        @JsonProperty("idCiudad")
        private int idCiudad;
    
        @JsonProperty("municipio")
        private String municipio;
        
        @JsonProperty("idMunicipio")
        private int idMunicipio;
        
        @JsonProperty("colonias")
        private List<InfoColonia> colonia;
    
        @JsonProperty("tipoAsentamientos")
        private List<String> tipoAsentamientos;

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public int getIdEstado() {
            return idEstado;
        }

        public void setIdEstado(int idEstado) {
            this.idEstado = idEstado;
        }
        
        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public int getIdCiudad() {
            return idCiudad;
        }

        public void setIdCiudad(int idCiudad) {
            this.idCiudad = idCiudad;
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio;
        }

        public int getIdMunicipio() {
            return idMunicipio;
        }

        public void setIdMunicipio(int idMunicipio) {
            this.idMunicipio = idMunicipio;
        }

        public List<InfoColonia> getColonia() {
            return colonia;
        }

        public void setColonia(List<InfoColonia> colonia) {
            this.colonia = colonia;
        }

        public List<String> getTipoAsentamientos() {
            return tipoAsentamientos;
        }

        public void setTipoAsentamientos(List<String> tipoAsentamientos) {
            this.tipoAsentamientos = tipoAsentamientos;
        }
        
        
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
}
