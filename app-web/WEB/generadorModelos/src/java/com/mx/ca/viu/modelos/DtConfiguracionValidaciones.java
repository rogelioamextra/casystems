/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_configuracion_validaciones")
@NamedQueries({
    @NamedQuery(name = "DtConfiguracionValidaciones.findAll", query = "SELECT d FROM DtConfiguracionValidaciones d"),
    @NamedQuery(name = "DtConfiguracionValidaciones.findByIdConfiguracionValidacion", query = "SELECT d FROM DtConfiguracionValidaciones d WHERE d.idConfiguracionValidacion = :idConfiguracionValidacion"),
    @NamedQuery(name = "DtConfiguracionValidaciones.findByStatus", query = "SELECT d FROM DtConfiguracionValidaciones d WHERE d.status = :status")})
public class DtConfiguracionValidaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_configuracion_validacion")
    private Long idConfiguracionValidacion;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idConfigValidacion")
    private List<DtValidacionesServiciosEmpresa> dtValidacionesServiciosEmpresaList;
    @JoinColumn(name = "id_documentos", referencedColumnName = "id_documentos")
    @ManyToOne
    private CatDocumentos idDocumentos;
    @JoinColumn(name = "id_empresas", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresas;
    @JoinColumn(name = "id_validaciones", referencedColumnName = "id_validaciones")
    @ManyToOne
    private CatValidaciones idValidaciones;

    public DtConfiguracionValidaciones() {
    }

    public DtConfiguracionValidaciones(Long idConfiguracionValidacion) {
        this.idConfiguracionValidacion = idConfiguracionValidacion;
    }

    public Long getIdConfiguracionValidacion() {
        return idConfiguracionValidacion;
    }

    public void setIdConfiguracionValidacion(Long idConfiguracionValidacion) {
        this.idConfiguracionValidacion = idConfiguracionValidacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DtValidacionesServiciosEmpresa> getDtValidacionesServiciosEmpresaList() {
        return dtValidacionesServiciosEmpresaList;
    }

    public void setDtValidacionesServiciosEmpresaList(List<DtValidacionesServiciosEmpresa> dtValidacionesServiciosEmpresaList) {
        this.dtValidacionesServiciosEmpresaList = dtValidacionesServiciosEmpresaList;
    }

    public CatDocumentos getIdDocumentos() {
        return idDocumentos;
    }

    public void setIdDocumentos(CatDocumentos idDocumentos) {
        this.idDocumentos = idDocumentos;
    }

    public CatEmpresas getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(CatEmpresas idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public CatValidaciones getIdValidaciones() {
        return idValidaciones;
    }

    public void setIdValidaciones(CatValidaciones idValidaciones) {
        this.idValidaciones = idValidaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracionValidacion != null ? idConfiguracionValidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtConfiguracionValidaciones)) {
            return false;
        }
        DtConfiguracionValidaciones other = (DtConfiguracionValidaciones) object;
        if ((this.idConfiguracionValidacion == null && other.idConfiguracionValidacion != null) || (this.idConfiguracionValidacion != null && !this.idConfiguracionValidacion.equals(other.idConfiguracionValidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtConfiguracionValidaciones[ idConfiguracionValidacion=" + idConfiguracionValidacion + " ]";
    }
    
}
