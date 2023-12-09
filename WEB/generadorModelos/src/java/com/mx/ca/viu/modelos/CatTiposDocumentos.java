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

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_tipos_documentos")
@NamedQueries({
    @NamedQuery(name = "CatTiposDocumentos.findAll", query = "SELECT c FROM CatTiposDocumentos c"),
    @NamedQuery(name = "CatTiposDocumentos.findByIdTiposDocumentos", query = "SELECT c FROM CatTiposDocumentos c WHERE c.idTiposDocumentos = :idTiposDocumentos"),
    @NamedQuery(name = "CatTiposDocumentos.findByNombre", query = "SELECT c FROM CatTiposDocumentos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTiposDocumentos.findByDescripcion", query = "SELECT c FROM CatTiposDocumentos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTiposDocumentos.findByStatus", query = "SELECT c FROM CatTiposDocumentos c WHERE c.status = :status")})
public class CatTiposDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipos_documentos")
    private Long idTiposDocumentos;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTiposDocumentos")
    private List<MvDocumentosTipos> mvDocumentosTiposList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;

    public CatTiposDocumentos() {
    }

    public CatTiposDocumentos(Long idTiposDocumentos) {
        this.idTiposDocumentos = idTiposDocumentos;
    }

    public Long getIdTiposDocumentos() {
        return idTiposDocumentos;
    }

    public void setIdTiposDocumentos(Long idTiposDocumentos) {
        this.idTiposDocumentos = idTiposDocumentos;
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

    public List<MvDocumentosTipos> getMvDocumentosTiposList() {
        return mvDocumentosTiposList;
    }

    public void setMvDocumentosTiposList(List<MvDocumentosTipos> mvDocumentosTiposList) {
        this.mvDocumentosTiposList = mvDocumentosTiposList;
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
        hash += (idTiposDocumentos != null ? idTiposDocumentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTiposDocumentos)) {
            return false;
        }
        CatTiposDocumentos other = (CatTiposDocumentos) object;
        if ((this.idTiposDocumentos == null && other.idTiposDocumentos != null) || (this.idTiposDocumentos != null && !this.idTiposDocumentos.equals(other.idTiposDocumentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTiposDocumentos[ idTiposDocumentos=" + idTiposDocumentos + " ]";
    }
    
}
