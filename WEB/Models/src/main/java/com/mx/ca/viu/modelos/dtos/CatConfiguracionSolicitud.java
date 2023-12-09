/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos;

import java.io.Serializable;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public class CatConfiguracionSolicitud implements Serializable{ 
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private int desde;
    private int hasta;
    private String estatus;
    private String mensaje;
    private Boolean cnr;
    private Boolean anr;
    private Boolean cm;
    private Boolean ctv;
    
    public CatConfiguracionSolicitud(int id, String nombre, int desde, int hasta, String estatus, String mensaje, boolean cnr, boolean anr, boolean cm, boolean ctv) {
        this.id = id;
        this.nombre = nombre;
        this.desde = desde;
        this.hasta = hasta;
        this.estatus = estatus;
        this.mensaje = mensaje;
        this.cnr = cnr;
        this.anr = anr;
        this.cm = cm;
        this.ctv = ctv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCnr() {
        return cnr;
    }

    public void setCnr(Boolean cnr) {
        this.cnr = cnr;
    }

    public Boolean getAnr() {
        return anr;
    }

    public void setAnr(Boolean anr) {
        this.anr = anr;
    }

    public Boolean getCm() {
        return cm;
    }

    public void setCm(Boolean cm) {
        this.cm = cm;
    }

    public Boolean getCtv() {
        return ctv;
    }

    public void setCtv(Boolean ctv) {
        this.ctv = ctv;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

