package com.amextra.io.Response;

import java.io.Serializable;

public class IDPersona  implements Serializable {
    public long idPersona;
    public String nombres;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public long dependientesEconomicos;
    public String email;

    public long getDependientesEconomicos() {
        return dependientesEconomicos;
    }

    public void setDependientesEconomicos(long dependientesEconomicos) {
        this.dependientesEconomicos = dependientesEconomicos;
    }

    public String telefono;
    public String curp;
    public long genero;
    public String fechaNacimiento;
    public String rfc;
    public IdMaxEstudios idGradoMaximoEstudios;
    public IDlugarNacimiento lugarNacimiento;
    public IDidNacionalidad idNacionalidad;

    public IDEstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(IDEstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public IDEstadoCivil idEstadoCivil;

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
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

    public long getGenero() {
        return genero;
    }

    public void setGenero(long genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public IdMaxEstudios getIdGradoMaximoEstudios() {
        return idGradoMaximoEstudios;
    }

    public void setIdGradoMaximoEstudios(IdMaxEstudios idGradoMaximoEstudios) {
        this.idGradoMaximoEstudios = idGradoMaximoEstudios;
    }

    public IDlugarNacimiento getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(IDlugarNacimiento lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public IDidNacionalidad getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(IDidNacionalidad idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }
}
