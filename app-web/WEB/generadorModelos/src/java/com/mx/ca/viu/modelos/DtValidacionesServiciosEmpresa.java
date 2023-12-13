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
@Table(name = "dt_validaciones_servicios_empresa")
@NamedQueries({
    @NamedQuery(name = "DtValidacionesServiciosEmpresa.findAll", query = "SELECT d FROM DtValidacionesServiciosEmpresa d"),
    @NamedQuery(name = "DtValidacionesServiciosEmpresa.findByIdValidacionServiciosBanco", query = "SELECT d FROM DtValidacionesServiciosEmpresa d WHERE d.idValidacionServiciosBanco = :idValidacionServiciosBanco"),
    @NamedQuery(name = "DtValidacionesServiciosEmpresa.findByStatus", query = "SELECT d FROM DtValidacionesServiciosEmpresa d WHERE d.status = :status")})
public class DtValidacionesServiciosEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_validacion_servicios_banco")
    private Long idValidacionServiciosBanco;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_servicio_validacion", referencedColumnName = "id_servicios_validaciones")
    @ManyToOne
    private CatServiciosValidacionesExternos idServicioValidacion;
    @JoinColumn(name = "id_config_validacion", referencedColumnName = "id_configuracion_validacion")
    @ManyToOne
    private DtConfiguracionValidaciones idConfigValidacion;

    public DtValidacionesServiciosEmpresa() {
    }

    public DtValidacionesServiciosEmpresa(Long idValidacionServiciosBanco) {
        this.idValidacionServiciosBanco = idValidacionServiciosBanco;
    }

    public DtValidacionesServiciosEmpresa(Long idValidacionServiciosBanco, boolean status) {
        this.idValidacionServiciosBanco = idValidacionServiciosBanco;
        this.status = status;
    }

    public Long getIdValidacionServiciosBanco() {
        return idValidacionServiciosBanco;
    }

    public void setIdValidacionServiciosBanco(Long idValidacionServiciosBanco) {
        this.idValidacionServiciosBanco = idValidacionServiciosBanco;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatServiciosValidacionesExternos getIdServicioValidacion() {
        return idServicioValidacion;
    }

    public void setIdServicioValidacion(CatServiciosValidacionesExternos idServicioValidacion) {
        this.idServicioValidacion = idServicioValidacion;
    }

    public DtConfiguracionValidaciones getIdConfigValidacion() {
        return idConfigValidacion;
    }

    public void setIdConfigValidacion(DtConfiguracionValidaciones idConfigValidacion) {
        this.idConfigValidacion = idConfigValidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidacionServiciosBanco != null ? idValidacionServiciosBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtValidacionesServiciosEmpresa)) {
            return false;
        }
        DtValidacionesServiciosEmpresa other = (DtValidacionesServiciosEmpresa) object;
        if ((this.idValidacionServiciosBanco == null && other.idValidacionServiciosBanco != null) || (this.idValidacionServiciosBanco != null && !this.idValidacionServiciosBanco.equals(other.idValidacionServiciosBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa[ idValidacionServiciosBanco=" + idValidacionServiciosBanco + " ]";
    }
    
}
