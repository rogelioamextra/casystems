/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String diasVentaAltaMontoDia;
    @Column(name = "dias_venta_alta_semana")
    private String diasVentaAltaMontoSemanal;
    @Column(name = "dias_venta_baja")
    private String diasVentaBaja;
    @Column(name = "dias_venta_baja_monto")
    private String diasVentaBajaMontoDia;
    @Column(name = "dias_venta_baja_semana")
    private String diasVentaBajaMontoSemanal;
    @Column(name = "total_alta_baja")
    private String totalDiasTrabajados;
    @Column(name = "total_alta_baja_monto")
    private String totalMontoDia;
    @Column(name = "total_alta_baja_semana")
    private String totalMontoSemana;
    @Column(name = "monto_mes")
    private String totalMontoMes;
    @Column(name = "frecuencia_semanal_promedio")
    private String inversionSemanalMonto;
    @Column(name = "frecuencia_semanal_mensual")
    private String inversionSemanalMontoMensual;
    @Column(name = "frecuencia_quincenal_promedio")
    private String inversionQuincenalMonto;
    @Column(name = "frecuencia_quincenal_mensual")
    private String inversionQuincenalMontoMensual;
    @Column(name = "frecuencia_mensual_promedio")
    private String inversionMensualMonto;
    @Column(name = "frecuencia_mensual_mensual")
    private String inversionMensualMontoMensual;
    @Column(name = "frecuencia_total")
    private String inversionTotal;
    @Column(name = "transporte")
    private String gastoTransporte;
    @Column(name = "renta")
    private String gastoRenta;
    @Column(name = "servicios")
    private String gastoServicios;
    @Column(name = "sueldos")
    private String gastoSueldos;
    @Column(name = "total_gastos")
    private String gastoTotal;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne
    @JsonIgnore
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

 

    public String getDiasVentaBaja() {
        return diasVentaBaja;
    }

    public void setDiasVentaBaja(String diasVentaBaja) {
        this.diasVentaBaja = diasVentaBaja;
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

    public String getDiasVentaAltaMontoDia() {
        return diasVentaAltaMontoDia;
    }

    public void setDiasVentaAltaMontoDia(String diasVentaAltaMontoDia) {
        this.diasVentaAltaMontoDia = diasVentaAltaMontoDia;
    }

    public String getDiasVentaAltaMontoSemanal() {
        return diasVentaAltaMontoSemanal;
    }

    public void setDiasVentaAltaMontoSemanal(String diasVentaAltaMontoSemanal) {
        this.diasVentaAltaMontoSemanal = diasVentaAltaMontoSemanal;
    }

    public String getDiasVentaBajaMontoDia() {
        return diasVentaBajaMontoDia;
    }

    public void setDiasVentaBajaMontoDia(String diasVentaBajaMontoDia) {
        this.diasVentaBajaMontoDia = diasVentaBajaMontoDia;
    }

    public String getDiasVentaBajaMontoSemanal() {
        return diasVentaBajaMontoSemanal;
    }

    public void setDiasVentaBajaMontoSemanal(String diasVentaBajaMontoSemanal) {
        this.diasVentaBajaMontoSemanal = diasVentaBajaMontoSemanal;
    }

    public String getTotalDiasTrabajados() {
        return totalDiasTrabajados;
    }

    public void setTotalDiasTrabajados(String totalDiasTrabajados) {
        this.totalDiasTrabajados = totalDiasTrabajados;
    }

    public String getTotalMontoDia() {
        return totalMontoDia;
    }

    public void setTotalMontoDia(String totalMontoDia) {
        this.totalMontoDia = totalMontoDia;
    }

    public String getTotalMontoSemana() {
        return totalMontoSemana;
    }

    public void setTotalMontoSemana(String totalMontoSemana) {
        this.totalMontoSemana = totalMontoSemana;
    }

    public String getTotalMontoMes() {
        return totalMontoMes;
    }

    public void setTotalMontoMes(String totalMontoMes) {
        this.totalMontoMes = totalMontoMes;
    }

    public String getInversionSemanalMonto() {
        return inversionSemanalMonto;
    }

    public void setInversionSemanalMonto(String inversionSemanalMonto) {
        this.inversionSemanalMonto = inversionSemanalMonto;
    }

    public String getInversionSemanalMontoMensual() {
        return inversionSemanalMontoMensual;
    }

    public void setInversionSemanalMontoMensual(String inversionSemanalMontoMensual) {
        this.inversionSemanalMontoMensual = inversionSemanalMontoMensual;
    }

    public String getInversionQuincenalMonto() {
        return inversionQuincenalMonto;
    }

    public void setInversionQuincenalMonto(String inversionQuincenalMonto) {
        this.inversionQuincenalMonto = inversionQuincenalMonto;
    }

    public String getInversionQuincenalMontoMensual() {
        return inversionQuincenalMontoMensual;
    }

    public void setInversionQuincenalMontoMensual(String inversionQuincenalMontoMensual) {
        this.inversionQuincenalMontoMensual = inversionQuincenalMontoMensual;
    }

    public String getInversionMensualMonto() {
        return inversionMensualMonto;
    }

    public void setInversionMensualMonto(String inversionMensualMonto) {
        this.inversionMensualMonto = inversionMensualMonto;
    }

    public String getInversionMensualMontoMensual() {
        return inversionMensualMontoMensual;
    }

    public void setInversionMensualMontoMensual(String inversionMensualMontoMensual) {
        this.inversionMensualMontoMensual = inversionMensualMontoMensual;
    }

    public String getInversionTotal() {
        return inversionTotal;
    }

    public void setInversionTotal(String inversionTotal) {
        this.inversionTotal = inversionTotal;
    }

    public String getGastoTransporte() {
        return gastoTransporte;
    }

    public void setGastoTransporte(String gastoTransporte) {
        this.gastoTransporte = gastoTransporte;
    }

    public String getGastoRenta() {
        return gastoRenta;
    }

    public void setGastoRenta(String gastoRenta) {
        this.gastoRenta = gastoRenta;
    }

    public String getGastoServicios() {
        return gastoServicios;
    }

    public void setGastoServicios(String gastoServicios) {
        this.gastoServicios = gastoServicios;
    }

    public String getGastoSueldos() {
        return gastoSueldos;
    }

    public void setGastoSueldos(String gastoSueldos) {
        this.gastoSueldos = gastoSueldos;
    }

    public String getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(String gastoTotal) {
        this.gastoTotal = gastoTotal;
    }
    
    
    
}
