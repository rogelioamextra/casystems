/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_empresas")
@NamedQueries({
    @NamedQuery(name = "CatEmpresas.findAll", query = "SELECT c FROM CatEmpresas c"),
    @NamedQuery(name = "CatEmpresas.findByIdEmpresas", query = "SELECT c FROM CatEmpresas c WHERE c.idEmpresas = :idEmpresas"),
    @NamedQuery(name = "CatEmpresas.findByNombre", query = "SELECT c FROM CatEmpresas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatEmpresas.findByDescripcion", query = "SELECT c FROM CatEmpresas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatEmpresas.findByStatus", query = "SELECT c FROM CatEmpresas c WHERE c.status = :status"),
    @NamedQuery(name = "CatEmpresas.findByColor", query = "SELECT c FROM CatEmpresas c WHERE c.color = :color")})
public class CatEmpresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresas")
    private Long idEmpresas;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<CatZonas> catZonasList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<MvDocumentosCategorias> mvDocumentosCategoriasList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatFolio> catFolioList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatRegiones> catRegionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<DtValidacionesServiciosEmpresa> dtValidacionesServiciosEmpresaList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatCategoriasCampos> catCategoriasCamposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<CatRoles> catRolesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<CatSubdirecciones> catSubdireccionesList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatProductos> catProductosList;
    @OneToMany(mappedBy = "idEmpresas")
    private List<DtConfiguracionValidaciones> dtConfiguracionValidacionesList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatSucursales> catSucursalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<MvDocumentosTipos> mvDocumentosTiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<CatTiposDocumentos> catTiposDocumentosList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatValoresTiempoVida> catValoresTiempoVidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<MvConfigAvisos> mvConfigAvisosList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatCampos> catCamposList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatTipoDato> catTipoDatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<MvConfigSolicitudes> mvConfigSolicitudesList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<CatDocumentos> catDocumentosList;

    public CatEmpresas() {
    }

    public CatEmpresas(Long idEmpresas) {
        this.idEmpresas = idEmpresas;
    }
    
     public CatEmpresas(String nombre) {
        this.nombre = nombre;
    }

    public CatEmpresas(Long idEmpresas, String nombre) {
        this.idEmpresas = idEmpresas;
        this.nombre = nombre;
    }

    public Long getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(Long idEmpresas) {
        this.idEmpresas = idEmpresas;
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<CatZonas> getCatZonasList() {
        return catZonasList;
    }

    public void setCatZonasList(List<CatZonas> catZonasList) {
        this.catZonasList = catZonasList;
    }

    public List<MvDocumentosCategorias> getMvDocumentosCategoriasList() {
        return mvDocumentosCategoriasList;
    }

    public void setMvDocumentosCategoriasList(List<MvDocumentosCategorias> mvDocumentosCategoriasList) {
        this.mvDocumentosCategoriasList = mvDocumentosCategoriasList;
    }

    public List<CatFolio> getCatFolioList() {
        return catFolioList;
    }

    public void setCatFolioList(List<CatFolio> catFolioList) {
        this.catFolioList = catFolioList;
    }

    public List<CatRegiones> getCatRegionesList() {
        return catRegionesList;
    }

    public void setCatRegionesList(List<CatRegiones> catRegionesList) {
        this.catRegionesList = catRegionesList;
    }

    public List<DtConfiguracionEmpresas> getDtConfiguracionEmpresasList() {
        return dtConfiguracionEmpresasList;
    }

    public void setDtConfiguracionEmpresasList(List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList) {
        this.dtConfiguracionEmpresasList = dtConfiguracionEmpresasList;
    }

    public List<DtValidacionesServiciosEmpresa> getDtValidacionesServiciosEmpresaList() {
        return dtValidacionesServiciosEmpresaList;
    }

    public void setDtValidacionesServiciosEmpresaList(List<DtValidacionesServiciosEmpresa> dtValidacionesServiciosEmpresaList) {
        this.dtValidacionesServiciosEmpresaList = dtValidacionesServiciosEmpresaList;
    }

    public List<CatCategoriasCampos> getCatCategoriasCamposList() {
        return catCategoriasCamposList;
    }

    public void setCatCategoriasCamposList(List<CatCategoriasCampos> catCategoriasCamposList) {
        this.catCategoriasCamposList = catCategoriasCamposList;
    }

    public List<CatRoles> getCatRolesList() {
        return catRolesList;
    }

    public void setCatRolesList(List<CatRoles> catRolesList) {
        this.catRolesList = catRolesList;
    }

    public List<CatSubdirecciones> getCatSubdireccionesList() {
        return catSubdireccionesList;
    }

    public void setCatSubdireccionesList(List<CatSubdirecciones> catSubdireccionesList) {
        this.catSubdireccionesList = catSubdireccionesList;
    }

    public List<CatProductos> getCatProductosList() {
        return catProductosList;
    }

    public void setCatProductosList(List<CatProductos> catProductosList) {
        this.catProductosList = catProductosList;
    }

    public List<DtConfiguracionValidaciones> getDtConfiguracionValidacionesList() {
        return dtConfiguracionValidacionesList;
    }

    public void setDtConfiguracionValidacionesList(List<DtConfiguracionValidaciones> dtConfiguracionValidacionesList) {
        this.dtConfiguracionValidacionesList = dtConfiguracionValidacionesList;
    }

    public List<CatSucursales> getCatSucursalesList() {
        return catSucursalesList;
    }

    public void setCatSucursalesList(List<CatSucursales> catSucursalesList) {
        this.catSucursalesList = catSucursalesList;
    }

    public List<MvDocumentosTipos> getMvDocumentosTiposList() {
        return mvDocumentosTiposList;
    }

    public void setMvDocumentosTiposList(List<MvDocumentosTipos> mvDocumentosTiposList) {
        this.mvDocumentosTiposList = mvDocumentosTiposList;
    }

    public List<CatTiposDocumentos> getCatTiposDocumentosList() {
        return catTiposDocumentosList;
    }

    public void setCatTiposDocumentosList(List<CatTiposDocumentos> catTiposDocumentosList) {
        this.catTiposDocumentosList = catTiposDocumentosList;
    }

    public List<CatValoresTiempoVida> getCatValoresTiempoVidaList() {
        return catValoresTiempoVidaList;
    }

    public void setCatValoresTiempoVidaList(List<CatValoresTiempoVida> catValoresTiempoVidaList) {
        this.catValoresTiempoVidaList = catValoresTiempoVidaList;
    }

    public List<MvConfigAvisos> getMvConfigAvisosList() {
        return mvConfigAvisosList;
    }

    public void setMvConfigAvisosList(List<MvConfigAvisos> mvConfigAvisosList) {
        this.mvConfigAvisosList = mvConfigAvisosList;
    }

    public List<CatCampos> getCatCamposList() {
        return catCamposList;
    }

    public void setCatCamposList(List<CatCampos> catCamposList) {
        this.catCamposList = catCamposList;
    }

    public List<CatTipoDato> getCatTipoDatoList() {
        return catTipoDatoList;
    }

    public void setCatTipoDatoList(List<CatTipoDato> catTipoDatoList) {
        this.catTipoDatoList = catTipoDatoList;
    }

    public List<MvConfigSolicitudes> getMvConfigSolicitudesList() {
        return mvConfigSolicitudesList;
    }

    public void setMvConfigSolicitudesList(List<MvConfigSolicitudes> mvConfigSolicitudesList) {
        this.mvConfigSolicitudesList = mvConfigSolicitudesList;
    }

    public List<CatDocumentos> getCatDocumentosList() {
        return catDocumentosList;
    }

    public void setCatDocumentosList(List<CatDocumentos> catDocumentosList) {
        this.catDocumentosList = catDocumentosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresas != null ? idEmpresas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEmpresas)) {
            return false;
        }
        CatEmpresas other = (CatEmpresas) object;
        if ((this.idEmpresas == null && other.idEmpresas != null) || (this.idEmpresas != null && !this.idEmpresas.equals(other.idEmpresas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatEmpresas[ idEmpresas=" + idEmpresas + " ]";
    }
    
}
