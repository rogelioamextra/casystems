/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_documentos")
@NamedQueries({
    @NamedQuery(name = "CatDocumentos.findAll", query = "SELECT c FROM CatDocumentos c"),
    @NamedQuery(name = "CatDocumentos.findByIdDocumentos", query = "SELECT c FROM CatDocumentos c WHERE c.idDocumentos = :idDocumentos"),
    @NamedQuery(name = "CatDocumentos.findByNombre", query = "SELECT c FROM CatDocumentos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatDocumentos.findByDescripcion", query = "SELECT c FROM CatDocumentos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatDocumentos.findByStatus", query = "SELECT c FROM CatDocumentos c WHERE c.status = :status"),
    @NamedQuery(name = "CatDocumentos.findByTipoCampo", query = "SELECT c FROM CatDocumentos c WHERE c.tipoCampo = :tipoCampo")})
public class CatDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documentos")
    private Long idDocumentos;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "tipo_campo")
    private Integer tipoCampo;
    @OneToMany(mappedBy = "idDocumentos")
    private List<MvDocumentosCategorias> mvDocumentosCategoriasList;
    @OneToMany(mappedBy = "idDocumento")
    private List<CatServiciosValidacionesInternos> catServiciosValidacionesInternosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<MvConfigRiesgo> mvConfigRiesgoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<DatosSolicitudDocumentos> datosSolicitudDocumentosList;
    @OneToMany(mappedBy = "idDocumento")
    private List<MensajesValidaciones> mensajesValidacionesList;
    @OneToMany(mappedBy = "idDocumentos")
    private List<DtConfiguracionValidaciones> dtConfiguracionValidacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<MvResultadosValidacionesDocumentos> mvResultadosValidacionesDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumentos")
    private List<MvDocumentosTipos> mvDocumentosTiposList;
    @OneToMany(mappedBy = "idDocumento")
    private List<DtIdentificacionCliente> dtIdentificacionClienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<MvConfigNivelRiesgo> mvConfigNivelRiesgoList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @Transient
     private boolean banderaStatus;

    public CatDocumentos() {
    }

    public CatDocumentos(Long idDocumentos) {
        this.idDocumentos = idDocumentos;
    }

    public Long getIdDocumentos() {
        return idDocumentos;
    }

    public void setIdDocumentos(Long idDocumentos) {
        this.idDocumentos = idDocumentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(Integer tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public List<MvDocumentosCategorias> getMvDocumentosCategoriasList() {
        return mvDocumentosCategoriasList;
    }

    public void setMvDocumentosCategoriasList(List<MvDocumentosCategorias> mvDocumentosCategoriasList) {
        this.mvDocumentosCategoriasList = mvDocumentosCategoriasList;
    }

    public List<CatServiciosValidacionesInternos> getCatServiciosValidacionesInternosList() {
        return catServiciosValidacionesInternosList;
    }

    public void setCatServiciosValidacionesInternosList(List<CatServiciosValidacionesInternos> catServiciosValidacionesInternosList) {
        this.catServiciosValidacionesInternosList = catServiciosValidacionesInternosList;
    }

    public List<MvConfigRiesgo> getMvConfigRiesgoList() {
        return mvConfigRiesgoList;
    }

    public void setMvConfigRiesgoList(List<MvConfigRiesgo> mvConfigRiesgoList) {
        this.mvConfigRiesgoList = mvConfigRiesgoList;
    }

    public List<DatosSolicitudDocumentos> getDatosSolicitudDocumentosList() {
        return datosSolicitudDocumentosList;
    }

    public void setDatosSolicitudDocumentosList(List<DatosSolicitudDocumentos> datosSolicitudDocumentosList) {
        this.datosSolicitudDocumentosList = datosSolicitudDocumentosList;
    }

    public List<MensajesValidaciones> getMensajesValidacionesList() {
        return mensajesValidacionesList;
    }

    public void setMensajesValidacionesList(List<MensajesValidaciones> mensajesValidacionesList) {
        this.mensajesValidacionesList = mensajesValidacionesList;
    }

    public List<DtConfiguracionValidaciones> getDtConfiguracionValidacionesList() {
        return dtConfiguracionValidacionesList;
    }

    public void setDtConfiguracionValidacionesList(List<DtConfiguracionValidaciones> dtConfiguracionValidacionesList) {
        this.dtConfiguracionValidacionesList = dtConfiguracionValidacionesList;
    }

    public List<MvResultadosValidacionesDocumentos> getMvResultadosValidacionesDocumentosList() {
        return mvResultadosValidacionesDocumentosList;
    }

    public void setMvResultadosValidacionesDocumentosList(List<MvResultadosValidacionesDocumentos> mvResultadosValidacionesDocumentosList) {
        this.mvResultadosValidacionesDocumentosList = mvResultadosValidacionesDocumentosList;
    }

    public List<MvDocumentosTipos> getMvDocumentosTiposList() {
        return mvDocumentosTiposList;
    }

    public void setMvDocumentosTiposList(List<MvDocumentosTipos> mvDocumentosTiposList) {
        this.mvDocumentosTiposList = mvDocumentosTiposList;
    }

    public List<DtIdentificacionCliente> getDtIdentificacionClienteList() {
        return dtIdentificacionClienteList;
    }

    public void setDtIdentificacionClienteList(List<DtIdentificacionCliente> dtIdentificacionClienteList) {
        this.dtIdentificacionClienteList = dtIdentificacionClienteList;
    }

    public List<MvConfigNivelRiesgo> getMvConfigNivelRiesgoList() {
        return mvConfigNivelRiesgoList;
    }

    public void setMvConfigNivelRiesgoList(List<MvConfigNivelRiesgo> mvConfigNivelRiesgoList) {
        this.mvConfigNivelRiesgoList = mvConfigNivelRiesgoList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentos != null ? idDocumentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDocumentos)) {
            return false;
        }
        CatDocumentos other = (CatDocumentos) object;
        if ((this.idDocumentos == null && other.idDocumentos != null) || (this.idDocumentos != null && !this.idDocumentos.equals(other.idDocumentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatDocumentos[ idDocumentos=" + idDocumentos + " ]";
    }

    public boolean isBanderaStatus() {
        return banderaStatus;
    }

    public void setBanderaStatus(boolean banderaStatus) {
        this.banderaStatus = banderaStatus;
    }
    
    
    
}
