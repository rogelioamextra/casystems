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
@Table(name = "datos_comprobante_extras")
@NamedQueries({
    @NamedQuery(name = "DatosComprobanteExtras.findAll", query = "SELECT d FROM DatosComprobanteExtras d"),
    @NamedQuery(name = "DatosComprobanteExtras.findByIdDatoExtra", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.idDatoExtra = :idDatoExtra"),
    @NamedQuery(name = "DatosComprobanteExtras.findByTotalPagar", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.totalPagar = :totalPagar"),
    @NamedQuery(name = "DatosComprobanteExtras.findByCodigonumerico", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.codigonumerico = :codigonumerico"),
    @NamedQuery(name = "DatosComprobanteExtras.findByFecha", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DatosComprobanteExtras.findByNumeroServicio", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.numeroServicio = :numeroServicio"),
    @NamedQuery(name = "DatosComprobanteExtras.findByTarifa", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.tarifa = :tarifa"),
    @NamedQuery(name = "DatosComprobanteExtras.findByRmu", query = "SELECT d FROM DatosComprobanteExtras d WHERE d.rmu = :rmu")})
public class DatosComprobanteExtras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dato_extra")
    private Long idDatoExtra;
    @Column(name = "total_pagar")
    private String totalPagar;
    @Column(name = "codigonumerico")
    private String codigonumerico;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "numero_servicio")
    private String numeroServicio;
    @Column(name = "tarifa")
    private String tarifa;
    @Column(name = "rmu")
    private String rmu;
    @JoinColumn(name = "id_datos_comprob", referencedColumnName = "id_datos_comprob")
    @ManyToOne
    private DtDatosComprobanteDom idDatosComprob;

    public DatosComprobanteExtras() {
    }

    public DatosComprobanteExtras(Long idDatoExtra) {
        this.idDatoExtra = idDatoExtra;
    }

    public Long getIdDatoExtra() {
        return idDatoExtra;
    }

    public void setIdDatoExtra(Long idDatoExtra) {
        this.idDatoExtra = idDatoExtra;
    }

    public String getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getCodigonumerico() {
        return codigonumerico;
    }

    public void setCodigonumerico(String codigonumerico) {
        this.codigonumerico = codigonumerico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(String numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getRmu() {
        return rmu;
    }

    public void setRmu(String rmu) {
        this.rmu = rmu;
    }

    public DtDatosComprobanteDom getIdDatosComprob() {
        return idDatosComprob;
    }

    public void setIdDatosComprob(DtDatosComprobanteDom idDatosComprob) {
        this.idDatosComprob = idDatosComprob;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatoExtra != null ? idDatoExtra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosComprobanteExtras)) {
            return false;
        }
        DatosComprobanteExtras other = (DatosComprobanteExtras) object;
        if ((this.idDatoExtra == null && other.idDatoExtra != null) || (this.idDatoExtra != null && !this.idDatoExtra.equals(other.idDatoExtra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosComprobanteExtras[ idDatoExtra=" + idDatoExtra + " ]";
    }
    
}
