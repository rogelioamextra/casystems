/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ValidacionCurpResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public ValidacionCurpResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("nombre")
        private String nombre;
        @JsonProperty("apellidoPaterno")
        private String apellidoPaterno;
        @JsonProperty("apellidoMaterno")
        private String apellidoMaterno;
        
        @JsonProperty("fechaNacimiento")
        private String fechaNacimiento;
        @JsonProperty("edad")
        private String edad;
        
        @JsonProperty("estadoNacimiento")
        private String estadoId;
        @JsonProperty("nombreEstado")
        private String nombreEstado;
        @JsonProperty("sexo")
        private String sexo;
        @JsonProperty("codigoSexo")
        private String codigoSexo;
        @JsonProperty("nacionalidad")
        private String nacionalidad;
        @JsonProperty("nacionalidadId")
        private String nacionalidadId;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
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

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getEstadoId() {
            return estadoId;
        }

        public void setEstadoId(String estadoId) {
            this.estadoId = estadoId;
        }

      
      
        public String getEdad() {
            return edad;
        }

        public void setEdad(String edad) {
            this.edad = edad;
        }

        public String getNombreEstado() {
            return nombreEstado;
        }

        public void setNombreEstado(String nombreEstado) {
            this.nombreEstado = nombreEstado;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public String getCodigoSexo() {
            return codigoSexo;
        }

        public void setCodigoSexo(String codigoSexo) {
            this.codigoSexo = codigoSexo;
        }

        public String getNacionalidad() {
            return nacionalidad;
        }

        public void setNacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
        }

        public String getNacionalidadId() {
            return nacionalidadId;
        }

        public void setNacionalidadId(String nacionalidadId) {
            this.nacionalidadId = nacionalidadId;
        }

      
        
        
     

      

     
        

    

      
   

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
