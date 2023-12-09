/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_ingresos")
@NamedQueries({
    @NamedQuery(name = "DtIngresos.findAll", query = "SELECT d FROM DtIngresos d"),
    @NamedQuery(name = "DtIngresos.findByIdIngresos", query = "SELECT d FROM DtIngresos d WHERE d.idIngresos = :idIngresos"),
    @NamedQuery(name = "DtIngresos.findByDiasVentaAlta", query = "SELECT d FROM DtIngresos d WHERE d.diasVentaAlta = :diasVentaAlta"),
    @NamedQuery(name = "DtIngresos.findByDiasVentaAltaMonto", query = "SELECT d FROM DtIngresos d WHERE d.diasVentaAltaMonto = :diasVentaAltaMonto"),
    @NamedQuery(name = "DtIngresos.findByDiasVentaAltaSemana", query = "SELECT d FROM DtIngresos d WHERE d.diasVentaAltaSemana = :diasVentaAltaSemana"),
    @NamedQuery(name = "DtIngresos.findByDiasVentaBaja", query = "SELECT d FROM DtIngresos d WHERE d.diasVentaBaja = :diasVentaBaja"),
    @NamedQuery(name = "DtIngresos.findByDiasVentaBajaMonto", query = "SELECT d FROM DtIngresos d WHERE d.diasVentaBajaMonto = :diasVentaBajaMonto"),
    @NamedQuery(name = "DtIngresos.findByDiasVentaBajaSemana", query = "SELECT d FROM DtIngresos d WHERE d.diasVentaBajaSemana = :diasVentaBajaSemana"),
    @NamedQuery(name = "DtIngresos.findByTotalAltaBaja", query = "SELECT d FROM DtIngresos d WHERE d.totalAltaBaja = :totalAltaBaja"),
    @NamedQuery(name = "DtIngresos.findByTotalAltaBajaMonto", query = "SELECT d FROM DtIngresos d WHERE d.totalAltaBajaMonto = :totalAltaBajaMonto"),
    @NamedQuery(name = "DtIngresos.findByTotalAltaBajaSemana", query = "SELECT d FROM DtIngresos d WHERE d.totalAltaBajaSemana = :totalAltaBajaSemana"),
    @NamedQuery(name = "DtIngresos.findByMontoMes", query = "SELECT d FROM DtIngresos d WHERE d.montoMes = :montoMes"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaSemanalPromedio", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaSemanalPromedio = :frecuenciaSemanalPromedio"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaSemanalMensual", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaSemanalMensual = :frecuenciaSemanalMensual"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaQuincenalPromedio", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaQuincenalPromedio = :frecuenciaQuincenalPromedio"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaQuincenalMensual", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaQuincenalMensual = :frecuenciaQuincenalMensual"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaMensualPromedio", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaMensualPromedio = :frecuenciaMensualPromedio"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaMensualMensual", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaMensualMensual = :frecuenciaMensualMensual"),
    @NamedQuery(name = "DtIngresos.findByFrecuenciaTotal", query = "SELECT d FROM DtIngresos d WHERE d.frecuenciaTotal = :frecuenciaTotal"),
    @NamedQuery(name = "DtIngresos.findByTransporte", query = "SELECT d FROM DtIngresos d WHERE d.transporte = :transporte"),
    @NamedQuery(name = "DtIngresos.findByRenta", query = "SELECT d FROM DtIngresos d WHERE d.renta = :renta"),
    @NamedQuery(name = "DtIngresos.findByServicios", query = "SELECT d FROM DtIngresos d WHERE d.servicios = :servicios"),
    @NamedQuery(name = "DtIngresos.findByTotalGastos", query = "SELECT d FROM DtIngresos d WHERE d.totalGastos = :totalGastos"),
    @NamedQuery(name = "DtIngresos.findBySueldos", query = "SELECT d FROM DtIngresos d WHERE d.sueldos = :sueldos")})
public class DtIngresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ingresos")
    private Long idIngresos;
    @Column(name = "dias_venta_alta")
    private String diasVentaAlta;
    @Column(name = "dias_venta_alta_monto")
    private String diasVentaAltaMonto;
    @Column(name = "dias_venta_alta_semana")
    private String diasVentaAltaSemana;
    @Column(name = "dias_venta_baja")
    private String diasVentaBaja;
    @Column(name = "dias_venta_baja_monto")
    private String diasVentaBajaMonto;
    @Column(name = "dias_venta_baja_semana")
    private String diasVentaBajaSemana;
    @Column(name = "total_alta_baja")
    private String totalAltaBaja;
    @Column(name = "total_alta_baja_monto")
    private String totalAltaBajaMonto;
    @Column(name = "total_alta_baja_semana")
    private String totalAltaBajaSemana;
    @Column(name = "monto_mes")
    private String montoMes;
    @Column(name = "frecuencia_semanal_promedio")
    private String frecuenciaSemanalPromedio;
    @Column(name = "frecuencia_semanal_mensual")
    private String frecuenciaSemanalMensual;
    @Column(name = "frecuencia_quincenal_promedio")
    private String frecuenciaQuincenalPromedio;
    @Column(name = "frecuencia_quincenal_mensual")
    private String frecuenciaQuincenalMensual;
    @Column(name = "frecuencia_mensual_promedio")
    private String frecuenciaMensualPromedio;
    @Column(name = "frecuencia_mensual_mensual")
    private String frecuenciaMensualMensual;
    @Column(name = "frecuencia_total")
    private String frecuenciaTotal;
    @Column(name = "transporte")
    private String transporte;
    @Column(name = "renta")
    private String renta;
    @Column(name = "servicios")
    private String servicios;
    @Column(name = "total_gastos")
    private String totalGastos;
    @Column(name = "sueldos")
    private String sueldos;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne
    private MvSolicitudesAmextra idSolicitud;

    public DtIngresos() {
    }

    public DtIngresos(Long idIngresos) {
        this.idIngresos = idIngresos;
    }

    public Long getIdIngresos() {
        return idIngresos;
    }

    public void setIdIngresos(Long idIngresos) {
        this.idIngresos = idIngresos;
    }

    public String getDiasVentaAlta() {
        return diasVentaAlta;
    }

    public void setDiasVentaAlta(String diasVentaAlta) {
        this.diasVentaAlta = diasVentaAlta;
    }

    public String getDiasVentaAltaMonto() {
        return diasVentaAltaMonto;
    }

    public void setDiasVentaAltaMonto(String diasVentaAltaMonto) {
        this.diasVentaAltaMonto = diasVentaAltaMonto;
    }

    public String getDiasVentaAltaSemana() {
        return diasVentaAltaSemana;
    }

    public void setDiasVentaAltaSemana(String diasVentaAltaSemana) {
        this.diasVentaAltaSemana = diasVentaAltaSemana;
    }

    public String getDiasVentaBaja() {
        return diasVentaBaja;
    }

    public void setDiasVentaBaja(String diasVentaBaja) {
        this.diasVentaBaja = diasVentaBaja;
    }

    public String getDiasVentaBajaMonto() {
        return diasVentaBajaMonto;
    }

    public void setDiasVentaBajaMonto(String diasVentaBajaMonto) {
        this.diasVentaBajaMonto = diasVentaBajaMonto;
    }

    public String getDiasVentaBajaSemana() {
        return diasVentaBajaSemana;
    }

    public void setDiasVentaBajaSemana(String diasVentaBajaSemana) {
        this.diasVentaBajaSemana = diasVentaBajaSemana;
    }

    public String getTotalAltaBaja() {
        return totalAltaBaja;
    }

    public void setTotalAltaBaja(String totalAltaBaja) {
        this.totalAltaBaja = totalAltaBaja;
    }

    public String getTotalAltaBajaMonto() {
        return totalAltaBajaMonto;
    }

    public void setTotalAltaBajaMonto(String totalAltaBajaMonto) {
        this.totalAltaBajaMonto = totalAltaBajaMonto;
    }

    public String getTotalAltaBajaSemana() {
        return totalAltaBajaSemana;
    }

    public void setTotalAltaBajaSemana(String totalAltaBajaSemana) {
        this.totalAltaBajaSemana = totalAltaBajaSemana;
    }

    public String getMontoMes() {
        return montoMes;
    }

    public void setMontoMes(String montoMes) {
        this.montoMes = montoMes;
    }

    public String getFrecuenciaSemanalPromedio() {
        return frecuenciaSemanalPromedio;
    }

    public void setFrecuenciaSemanalPromedio(String frecuenciaSemanalPromedio) {
        this.frecuenciaSemanalPromedio = frecuenciaSemanalPromedio;
    }

    public String getFrecuenciaSemanalMensual() {
        return frecuenciaSemanalMensual;
    }

    public void setFrecuenciaSemanalMensual(String frecuenciaSemanalMensual) {
        this.frecuenciaSemanalMensual = frecuenciaSemanalMensual;
    }

    public String getFrecuenciaQuincenalPromedio() {
        return frecuenciaQuincenalPromedio;
    }

    public void setFrecuenciaQuincenalPromedio(String frecuenciaQuincenalPromedio) {
        this.frecuenciaQuincenalPromedio = frecuenciaQuincenalPromedio;
    }

    public String getFrecuenciaQuincenalMensual() {
        return frecuenciaQuincenalMensual;
    }

    public void setFrecuenciaQuincenalMensual(String frecuenciaQuincenalMensual) {
        this.frecuenciaQuincenalMensual = frecuenciaQuincenalMensual;
    }

    public String getFrecuenciaMensualPromedio() {
        return frecuenciaMensualPromedio;
    }

    public void setFrecuenciaMensualPromedio(String frecuenciaMensualPromedio) {
        this.frecuenciaMensualPromedio = frecuenciaMensualPromedio;
    }

    public String getFrecuenciaMensualMensual() {
        return frecuenciaMensualMensual;
    }

    public void setFrecuenciaMensualMensual(String frecuenciaMensualMensual) {
        this.frecuenciaMensualMensual = frecuenciaMensualMensual;
    }

    public String getFrecuenciaTotal() {
        return frecuenciaTotal;
    }

    public void setFrecuenciaTotal(String frecuenciaTotal) {
        this.frecuenciaTotal = frecuenciaTotal;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getRenta() {
        return renta;
    }

    public void setRenta(String renta) {
        this.renta = renta;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(String totalGastos) {
        this.totalGastos = totalGastos;
    }

    public String getSueldos() {
        return sueldos;
    }

    public void setSueldos(String sueldos) {
        this.sueldos = sueldos;
    }

    public MvSolicitudesAmextra getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(MvSolicitudesAmextra idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngresos != null ? idIngresos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtIngresos)) {
            return false;
        }
        DtIngresos other = (DtIngresos) object;
        if ((this.idIngresos == null && other.idIngresos != null) || (this.idIngresos != null && !this.idIngresos.equals(other.idIngresos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtIngresos[ idIngresos=" + idIngresos + " ]";
    }
    
}
