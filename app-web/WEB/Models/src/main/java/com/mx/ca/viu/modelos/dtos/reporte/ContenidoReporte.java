/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.reporte;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ContenidoReporte {

    private InputStream logo;
    private String sucursal;
    private String nocredito;
    private String nocliente;
    private String codigoProducto;
    private String tipoOperacion;
    private String montoCredito;
    private String tipoPlan;
    private String tasaInteres;
    private String fechaDesembolso;
    private String plazo;
    private String frecuenciaPago;
    private String estado;
    private String firma;
    private List<DetalleReporte> list;
    private String totalCapital;
    private String totalInteres;
    private String totalIva;
    private String totalCuota;
    private String nombreCliente;
    private String calle;
    private String colonia;
    private String municipio;
    private String ciudad;
    private String estado2;

    public ContenidoReporte() {

        this.sucursal = "";
        this.nocredito = "";
        this.nocliente = "";
        this.codigoProducto = "";
        this.tipoOperacion = "";
        this.montoCredito = "";
        this.tipoPlan = "";
        this.tasaInteres = "";
        this.fechaDesembolso = "";
        this.plazo = "";
        this.frecuenciaPago = "";
        this.estado = "";
        this.firma = "";

        this.totalCapital = "";
        this.totalInteres = "";
        this.totalIva = "";
        this.totalCuota = "";
        this.nombreCliente = "";
        this.calle = "";
        this.colonia = "";
        this.municipio = "";
        this.ciudad = "";
        this.estado2 = "";
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getNocredito() {
        return nocredito;
    }

    public void setNocredito(String nocredito) {
        this.nocredito = nocredito;
    }

    public String getNocliente() {
        return nocliente;
    }

    public void setNocliente(String nocliente) {
        this.nocliente = nocliente;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(String montoCredito) {
        this.montoCredito = montoCredito;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public String getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(String tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public String getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(String fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getFrecuenciaPago() {
        return frecuenciaPago;
    }

    public void setFrecuenciaPago(String frecuenciaPago) {
        this.frecuenciaPago = frecuenciaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public List<DetalleReporte> getList() {
        return list;
    }

    public void setList(List<DetalleReporte> list) {
        this.list = list;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
    }

    public String getTotalInteres() {
        return totalInteres;
    }

    public void setTotalInteres(String totalInteres) {
        this.totalInteres = totalInteres;
    }

    public String getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(String totalIva) {
        this.totalIva = totalIva;
    }

    public String getTotalCuota() {
        return totalCuota;
    }

    public void setTotalCuota(String totalCuota) {
        this.totalCuota = totalCuota;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public InputStream getLogo() {
        return logo;
    }

    public void setLogo(InputStream logo) {
        this.logo = logo;
    }

}
