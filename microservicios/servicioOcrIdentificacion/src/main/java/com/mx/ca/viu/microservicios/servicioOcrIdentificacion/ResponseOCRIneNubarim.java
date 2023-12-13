/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioOcrIdentificacion;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jbecerril
 */
public class ResponseOCRIneNubarim {
    
    @JsonProperty("mensaje")
    private String mensaje;
    @JsonProperty("estatus")
    private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    @JsonProperty("tipo")
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    String tipo;

    @JsonProperty("subTipo")
    public String getSubTipo() {
        return this.subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }
    String subTipo;

    @JsonProperty("claveElector")
    public String getClaveElector() {
        return this.claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }
    String claveElector;

    @JsonProperty("curp")
    public String getCurp() {
        return this.curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    String curp;

    @JsonProperty("registro")
    public String getRegistro() {
        return this.registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
    String registro;

    @JsonProperty("estado")
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    String estado;

    @JsonProperty("municipio")
    public String getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    String municipio;

    @JsonProperty("seccion")
    public String getSeccion() {
        return this.seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    String seccion;

    @JsonProperty("localidad")
    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    String localidad;

    @JsonProperty("emision")
    public String getEmision() {
        return this.emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }
    String emision;

    @JsonProperty("vigencia")
    public String getVigencia() {
        return this.vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
    String vigencia;

    @JsonProperty("fechaNacimiento")
    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    String fechaNacimiento;

    @JsonProperty("primerApellido")
    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    String primerApellido;

    @JsonProperty("segundoApellido")
    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    String segundoApellido;

    @JsonProperty("nombres")
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    String nombres;

    @JsonProperty("calle")
    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    String calle;

    @JsonProperty("colonia")
    public String getColonia() {
        return this.colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    String colonia;

    @JsonProperty("ciudad")
    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    String ciudad;

    @JsonProperty("codigoValidacion")
    public String getCodigoValidacion() {
        return this.codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }
    String codigoValidacion;

    @JsonProperty("QR")
    public String getQR() {
        return this.qR;
    }

    public void setQR(String qR) {
        this.qR = qR;
    }
    String qR;

    @JsonProperty("mrz")
    public String getMrz() {
        return this.mrz;
    }

    public void setMrz(String mrz) {
        this.mrz = mrz;
    }
    String mrz;

    @JsonProperty("cic")
    public String getCic() {
        return this.cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }
    String cic;

    @JsonProperty("ocr")
    public String getOcr() {
        return this.ocr;
    }

    public void setOcr(String ocr) {
        this.ocr = ocr;
    }
    String ocr;

    @JsonProperty("identificadorCiudadano")
    public String getIdentificadorCiudadano() {
        return this.identificadorCiudadano;
    }

    public void setIdentificadorCiudadano(String identificadorCiudadano) {
        this.identificadorCiudadano = identificadorCiudadano;
    }
    String identificadorCiudadano;

    @JsonProperty("validacionMRZ")
    public ValidacionMRZ getValidacionMRZ() {
        return this.validacionMRZ;
    }

    public void setValidacionMRZ(ValidacionMRZ validacionMRZ) {
        this.validacionMRZ = validacionMRZ;
    }
    ValidacionMRZ validacionMRZ;

    private static class ValidacionMRZ {

        @JsonProperty("fechaNacimiento")
        public String getFechaNacimiento() {
            return this.fechaNacimiento;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }
        String fechaNacimiento;

        @JsonProperty("sexo")
        public String getSexo() {
            return this.sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }
        String sexo;

        @JsonProperty("vigencia")
        public String getVigencia() {
            return this.vigencia;
        }

        public void setVigencia(String vigencia) {
            this.vigencia = vigencia;
        }
        String vigencia;

        @JsonProperty("emision")
        public String getEmision() {
            return this.emision;
        }

        public void setEmision(String emision) {
            this.emision = emision;
        }
        String emision;

        @JsonProperty("nombre")
        public String getNombre() {
            return this.nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        String nombre;
    }

}
