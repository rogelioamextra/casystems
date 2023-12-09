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
@Table(name = "cat_categorias_campos")
@NamedQueries({
    @NamedQuery(name = "CatCategoriasCampos.findAll", query = "SELECT c FROM CatCategoriasCampos c"),
    @NamedQuery(name = "CatCategoriasCampos.findByIdCategoriaCampo", query = "SELECT c FROM CatCategoriasCampos c WHERE c.idCategoriaCampo = :idCategoriaCampo"),
    @NamedQuery(name = "CatCategoriasCampos.findByNombre", query = "SELECT c FROM CatCategoriasCampos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatCategoriasCampos.findByStatus", query = "SELECT c FROM CatCategoriasCampos c WHERE c.status = :status")})
public class CatCategoriasCampos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_campo")
    private Long idCategoriaCampo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idCategoriaCampo")
    private List<MvDocumentosCategorias> mvDocumentosCategoriasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<MvGuardadosProceso> mvGuardadosProcesoList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @OneToMany(mappedBy = "idCategoria")
    private List<DatosSolicitudDocumentos> datosSolicitudDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<DatosSolicitudCategorias> datosSolicitudCategoriasList;
    @OneToMany(mappedBy = "idCategoria")
    private List<MvConfigAvisos> mvConfigAvisosList;
    @OneToMany(mappedBy = "idCategoria")
    private List<DatosSolicitudAvisos> datosSolicitudAvisosList;
    @OneToMany(mappedBy = "idCategoria")
    private List<CatCampos> catCamposList;
    @OneToMany(mappedBy = "idCategoria")
    private List<DatosSolicitudCampos> datosSolicitudCamposList;
    @Transient
    private boolean banderaStatus;
    public CatCategoriasCampos() {
    }

    public CatCategoriasCampos(Long idCategoriaCampo) {
        this.idCategoriaCampo = idCategoriaCampo;
    }

    public Long getIdCategoriaCampo() {
        return idCategoriaCampo;
    }

    public void setIdCategoriaCampo(Long idCategoriaCampo) {
        this.idCategoriaCampo = idCategoriaCampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MvDocumentosCategorias> getMvDocumentosCategoriasList() {
        return mvDocumentosCategoriasList;
    }

    public void setMvDocumentosCategoriasList(List<MvDocumentosCategorias> mvDocumentosCategoriasList) {
        this.mvDocumentosCategoriasList = mvDocumentosCategoriasList;
    }

    public List<MvGuardadosProceso> getMvGuardadosProcesoList() {
        return mvGuardadosProcesoList;
    }

    public void setMvGuardadosProcesoList(List<MvGuardadosProceso> mvGuardadosProcesoList) {
        this.mvGuardadosProcesoList = mvGuardadosProcesoList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<DatosSolicitudDocumentos> getDatosSolicitudDocumentosList() {
        return datosSolicitudDocumentosList;
    }

    public void setDatosSolicitudDocumentosList(List<DatosSolicitudDocumentos> datosSolicitudDocumentosList) {
        this.datosSolicitudDocumentosList = datosSolicitudDocumentosList;
    }

    public List<DatosSolicitudCategorias> getDatosSolicitudCategoriasList() {
        return datosSolicitudCategoriasList;
    }

    public void setDatosSolicitudCategoriasList(List<DatosSolicitudCategorias> datosSolicitudCategoriasList) {
        this.datosSolicitudCategoriasList = datosSolicitudCategoriasList;
    }

    public List<MvConfigAvisos> getMvConfigAvisosList() {
        return mvConfigAvisosList;
    }

    public void setMvConfigAvisosList(List<MvConfigAvisos> mvConfigAvisosList) {
        this.mvConfigAvisosList = mvConfigAvisosList;
    }

    public List<DatosSolicitudAvisos> getDatosSolicitudAvisosList() {
        return datosSolicitudAvisosList;
    }

    public void setDatosSolicitudAvisosList(List<DatosSolicitudAvisos> datosSolicitudAvisosList) {
        this.datosSolicitudAvisosList = datosSolicitudAvisosList;
    }

    public List<CatCampos> getCatCamposList() {
        return catCamposList;
    }

    public void setCatCamposList(List<CatCampos> catCamposList) {
        this.catCamposList = catCamposList;
    }

    public List<DatosSolicitudCampos> getDatosSolicitudCamposList() {
        return datosSolicitudCamposList;
    }

    public void setDatosSolicitudCamposList(List<DatosSolicitudCampos> datosSolicitudCamposList) {
        this.datosSolicitudCamposList = datosSolicitudCamposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaCampo != null ? idCategoriaCampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCategoriasCampos)) {
            return false;
        }
        CatCategoriasCampos other = (CatCategoriasCampos) object;
        if ((this.idCategoriaCampo == null && other.idCategoriaCampo != null) || (this.idCategoriaCampo != null && !this.idCategoriaCampo.equals(other.idCategoriaCampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatCategoriasCampos[ idCategoriaCampo=" + idCategoriaCampo + " ]";
    }

    public boolean isBanderaStatus() {
        return banderaStatus;
    }

    public void setBanderaStatus(boolean banderaStatus) {
        this.banderaStatus = banderaStatus;
    }
    
    
    
}
