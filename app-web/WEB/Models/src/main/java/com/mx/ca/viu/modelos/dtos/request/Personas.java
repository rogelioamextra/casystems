/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jbecerril
 */
public class Personas {

    private Long id;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String email;

    private String telefono;

    private String curp;

    private short genero;

    private String fechaNacimiento;

    private short dependientesEconomicos;

    private String rfc;
    private Long estadoCivilId;

    private Integer gradoMaximoEstudiosId;

    private Long nacionalidadId;

    private String lugarNacimientoId;
    
    private Political political;

    public Personas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Political getPolitical() {
        return political;
    }

    public void setPolitical(Political political) {
        this.political = political;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public short getGenero() {
        return genero;
    }

    public void setGenero(short genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public short getDependientesEconomicos() {
        return dependientesEconomicos;
    }

    public void setDependientesEconomicos(short dependientesEconomicos) {
        this.dependientesEconomicos = dependientesEconomicos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Long getEstadoCivilId() {
        return estadoCivilId;
    }

    public void setEstadoCivilId(Long estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

 
    public Long getNacionalidadId() {
        return nacionalidadId;
    }

    public void setNacionalidadId(Long nacionalidadId) {
        this.nacionalidadId = nacionalidadId;
    }

    public Integer getGradoMaximoEstudiosId() {
        return gradoMaximoEstudiosId;
    }

    public void setGradoMaximoEstudiosId(Integer gradoMaximoEstudiosId) {
        this.gradoMaximoEstudiosId = gradoMaximoEstudiosId;
    }

    public String getLugarNacimientoId() {
        return lugarNacimientoId;
    }

    public void setLugarNacimientoId(String lugarNacimientoId) {
        this.lugarNacimientoId = lugarNacimientoId;
    }


 

}
