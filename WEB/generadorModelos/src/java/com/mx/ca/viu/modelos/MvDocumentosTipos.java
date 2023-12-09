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
@Table(name = "mv_documentos_tipos")
@NamedQueries({
    @NamedQuery(name = "MvDocumentosTipos.findAll", query = "SELECT m FROM MvDocumentosTipos m"),
    @NamedQuery(name = "MvDocumentosTipos.findByIdDocumentosTipos", query = "SELECT m FROM MvDocumentosTipos m WHERE m.idDocumentosTipos = :idDocumentosTipos")})
public class MvDocumentosTipos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documentos_tipos")
    private Long idDocumentosTipos;
    @JoinColumn(name = "id_documentos", referencedColumnName = "id_documentos")
    @ManyToOne(optional = false)
    private CatDocumentos idDocumentos;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_tipos_documentos", referencedColumnName = "id_tipos_documentos")
    @ManyToOne(optional = false)
    private CatTiposDocumentos idTiposDocumentos;

    public MvDocumentosTipos() {
    }

    public MvDocumentosTipos(Long idDocumentosTipos) {
        this.idDocumentosTipos = idDocumentosTipos;
    }

    public Long getIdDocumentosTipos() {
        return idDocumentosTipos;
    }

    public void setIdDocumentosTipos(Long idDocumentosTipos) {
        this.idDocumentosTipos = idDocumentosTipos;
    }

    public CatDocumentos getIdDocumentos() {
        return idDocumentos;
    }

    public void setIdDocumentos(CatDocumentos idDocumentos) {
        this.idDocumentos = idDocumentos;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatTiposDocumentos getIdTiposDocumentos() {
        return idTiposDocumentos;
    }

    public void setIdTiposDocumentos(CatTiposDocumentos idTiposDocumentos) {
        this.idTiposDocumentos = idTiposDocumentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentosTipos != null ? idDocumentosTipos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvDocumentosTipos)) {
            return false;
        }
        MvDocumentosTipos other = (MvDocumentosTipos) object;
        if ((this.idDocumentosTipos == null && other.idDocumentosTipos != null) || (this.idDocumentosTipos != null && !this.idDocumentosTipos.equals(other.idDocumentosTipos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvDocumentosTipos[ idDocumentosTipos=" + idDocumentosTipos + " ]";
    }
    
}
