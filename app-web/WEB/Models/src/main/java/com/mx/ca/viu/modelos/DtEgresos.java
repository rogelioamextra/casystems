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
@Table(name = "dt_egresos")

public class DtEgresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_egresos")
    private Long idEgresos;
    @Column(name = "alimentos")
    private String alimentos;
    @Column(name = "renta")
    private String renta;
    @Column(name = "gastos_escolares")
    private String gastosEscolares;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "luz")
    private String luz;
    @Column(name = "agua")
    private String agua;
    @Column(name = "gas")
    private String gas;
    @Column(name = "transporte_gasolina")
    private String transporteGasolina;
    @Column(name = "vestido")
    private String vestido;
    @Column(name = "esparcimiento")
    private String esparcimiento;
    @Column(name = "mantenimiento_reparaciones")
    private String mantenimientoReparaciones;
    @Column(name = "total_mensual")
    private String totalMensual;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne
    @JsonIgnore
    private MvSolicitudesAmextra idSolicitud;

    public DtEgresos() {
    }

    public DtEgresos(Long idEgresos) {
        this.idEgresos = idEgresos;
    }

    public Long getIdEgresos() {
        return idEgresos;
    }

    public void setIdEgresos(Long idEgresos) {
        this.idEgresos = idEgresos;
    }

    public String getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(String alimentos) {
        this.alimentos = alimentos;
    }

    public String getRenta() {
        return renta;
    }

    public void setRenta(String renta) {
        this.renta = renta;
    }

    public String getGastosEscolares() {
        return gastosEscolares;
    }

    public void setGastosEscolares(String gastosEscolares) {
        this.gastosEscolares = gastosEscolares;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLuz() {
        return luz;
    }

    public void setLuz(String luz) {
        this.luz = luz;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getTransporteGasolina() {
        return transporteGasolina;
    }

    public void setTransporteGasolina(String transporteGasolina) {
        this.transporteGasolina = transporteGasolina;
    }

    public String getVestido() {
        return vestido;
    }

    public void setVestido(String vestido) {
        this.vestido = vestido;
    }

    public String getEsparcimiento() {
        return esparcimiento;
    }

    public void setEsparcimiento(String esparcimiento) {
        this.esparcimiento = esparcimiento;
    }

    public String getMantenimientoReparaciones() {
        return mantenimientoReparaciones;
    }

    public void setMantenimientoReparaciones(String mantenimientoReparaciones) {
        this.mantenimientoReparaciones = mantenimientoReparaciones;
    }

    public String getTotalMensual() {
        return totalMensual;
    }

    public void setTotalMensual(String totalMensual) {
        this.totalMensual = totalMensual;
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
        hash += (idEgresos != null ? idEgresos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtEgresos)) {
            return false;
        }
        DtEgresos other = (DtEgresos) object;
        if ((this.idEgresos == null && other.idEgresos != null) || (this.idEgresos != null && !this.idEgresos.equals(other.idEgresos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtEgresos[ idEgresos=" + idEgresos + " ]";
    }
    
}
