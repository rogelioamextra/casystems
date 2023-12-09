package com.amextra.io.Response;

import java.io.Serializable;

public class DataRespCurp implements Serializable {
    public String nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String fechaNacimiento;
    public String edad;
    public String sexo;


    public String codigoSexo;

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

    public String estadoNacimiento;
    public String nombreEstado;

    public  String nacionalidad;
    public String nacionalidadId;

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

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String value) { this.apellidoPaterno = value; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String value) { this.apellidoMaterno = value; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String value) { this.fechaNacimiento = value; }

    public String getEdad() { return edad; }
    public void setEdad(String value) { this.edad = value; }

    public String getEstadoNacimiento() { return estadoNacimiento; }
    public void setEstadoNacimiento(String value) { this.estadoNacimiento = value; }

    public String getNombreEstado() { return nombreEstado; }
    public void setNombreEstado(String value) { this.nombreEstado = value; }
}
