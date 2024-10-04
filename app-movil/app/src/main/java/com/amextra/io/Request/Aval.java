package com.amextra.io.Request;

import java.io.Serializable;

public class Aval implements Serializable {
    private String nombre = "";
    private String apellidoPaterno = "";
    private String apellidoMaterno = "";
    private String fechaNacimiento = "";
    private String curp = "";
    private String telefono = "";
    private long tipoIdentificacion = 0L;
    private String reverso = "";
    private String frontal = "";
    private String comprobanteDomicilio = "";

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

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(long tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getReverso() {
        return reverso;
    }

    public void setReverso(String reverso) {
        this.reverso = reverso;
    }

    public String getFrontal() {
        return frontal;
    }

    public void setFrontal(String frontal) {
        this.frontal = frontal;
    }

    public String getComprobanteDomicilio() {
        return comprobanteDomicilio;
    }

    public void setComprobanteDomicilio(String comprobanteDomicilio) {
        this.comprobanteDomicilio = comprobanteDomicilio;
    }


    @Override
    public String toString() {
        return "aval: {" +
                "nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", curp='" + curp + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", reverso='" + reverso + '\'' +
                ", frontal='" + frontal + '\'' +
                ", comprobanteDomicilio='" + comprobanteDomicilio + '\'' +
                '}';
    }
}
