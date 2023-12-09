/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.validaciones;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class Biometricos {

    private String validacion;

    private String descripcion;

    private String resultados;

    private boolean activo;

    public Biometricos(String validacion, String descripcion, String resultados, boolean activo) {
        this.validacion = validacion;
        this.descripcion = descripcion;
        this.resultados = resultados;
        this.activo = activo;
    }

    public Biometricos() {
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
