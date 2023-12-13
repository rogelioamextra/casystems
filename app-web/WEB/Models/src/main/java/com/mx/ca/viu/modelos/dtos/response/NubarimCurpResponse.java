/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jbecerril
 */
// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class NubarimCurpResponse {
    
    @JsonProperty("mensaje")
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    @JsonProperty("estatus")
    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    String estatus;

    @JsonProperty("codigoValidacion")
    public String getCodigoValidacion() {
        return this.codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }
    String codigoValidacion;

    @JsonProperty("curp")
    public String getCurp() {
        return this.curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    String curp;

    @JsonProperty("nombre")
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    String nombre;

    @JsonProperty("apellidoPaterno")
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    String apellidoMaterno;

    @JsonProperty("sexo")
    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    String sexo;

    @JsonProperty("fechaNacimiento")
    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    String fechaNacimiento;

    @JsonProperty("paisNacimiento")
    public String getPaisNacimiento() {
        return this.paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }
    String paisNacimiento;

    @JsonProperty("estadoNacimiento")
    public String getEstadoNacimiento() {
        return this.estadoNacimiento;
    }

    public void setEstadoNacimiento(String estadoNacimiento) {
        this.estadoNacimiento = estadoNacimiento;
    }
    String estadoNacimiento;

    @JsonProperty("docProbatorio")
    public int getDocProbatorio() {
        return this.docProbatorio;
    }

    public void setDocProbatorio(int docProbatorio) {
        this.docProbatorio = docProbatorio;
    }
    int docProbatorio;

    @JsonProperty("datosDocProbatorio")
    public DatosDocProbatorio getDatosDocProbatorio() {
        return this.datosDocProbatorio;
    }

    public void setDatosDocProbatorio(DatosDocProbatorio datosDocProbatorio) {
        this.datosDocProbatorio = datosDocProbatorio;
    }
    DatosDocProbatorio datosDocProbatorio;

    @JsonProperty("estatusCurp")
    public String getEstatusCurp() {
        return this.estatusCurp;
    }

    public void setEstatusCurp(String estatusCurp) {
        this.estatusCurp = estatusCurp;
    }
    String estatusCurp;

    @JsonProperty("codigoMensaje")
    public String getCodigoMensaje() {
        return this.codigoMensaje;
    }

    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }
    String codigoMensaje;

    private static class DatosDocProbatorio {

        @JsonProperty("anioReg")
        public String getAnioReg() {
            return this.anioReg;
        }

        public void setAnioReg(String anioReg) {
            this.anioReg = anioReg;
        }
        String anioReg;

        @JsonProperty("claveEntidadRegistro")
        public String getClaveEntidadRegistro() {
            return this.claveEntidadRegistro;
        }

        public void setClaveEntidadRegistro(String claveEntidadRegistro) {
            this.claveEntidadRegistro = claveEntidadRegistro;
        }
        String claveEntidadRegistro;

        @JsonProperty("entidadRegistro")
        public String getEntidadRegistro() {
            return this.entidadRegistro;
        }

        public void setEntidadRegistro(String entidadRegistro) {
            this.entidadRegistro = entidadRegistro;
        }
        String entidadRegistro;

        @JsonProperty("tomo")
        public String getTomo() {
            return this.tomo;
        }

        public void setTomo(String tomo) {
            this.tomo = tomo;
        }
        String tomo;

        @JsonProperty("foja")
        public String getFoja() {
            return this.foja;
        }

        public void setFoja(String foja) {
            this.foja = foja;
        }
        String foja;

        @JsonProperty("numActa")
        public String getNumActa() {
            return this.numActa;
        }

        public void setNumActa(String numActa) {
            this.numActa = numActa;
        }
        String numActa;

        @JsonProperty("libro")
        public String getLibro() {
            return this.libro;
        }

        public void setLibro(String libro) {
            this.libro = libro;
        }
        String libro;

        @JsonProperty("claveMunicipioRegistro")
        public String getClaveMunicipioRegistro() {
            return this.claveMunicipioRegistro;
        }

        public void setClaveMunicipioRegistro(String claveMunicipioRegistro) {
            this.claveMunicipioRegistro = claveMunicipioRegistro;
        }
        String claveMunicipioRegistro;

        @JsonProperty("municipioRegistro")
        public String getMunicipioRegistro() {
            return this.municipioRegistro;
        }

        public void setMunicipioRegistro(String municipioRegistro) {
            this.municipioRegistro = municipioRegistro;
        }
        String municipioRegistro;

        @JsonProperty("numRegExtranjeros")
        public String getNumRegExtranjeros() {
            return this.numRegExtranjeros;
        }

        public void setNumRegExtranjeros(String numRegExtranjeros) {
            this.numRegExtranjeros = numRegExtranjeros;
        }
        String numRegExtranjeros;

        @JsonProperty("folio")
        public String getFolio() {
            return this.folio;
        }

        public void setFolio(String folio) {
            this.folio = folio;
        }
        String folio;
    }
}
