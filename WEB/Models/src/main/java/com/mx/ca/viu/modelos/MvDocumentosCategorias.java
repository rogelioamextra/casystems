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
@Table(name = "mv_documentos_categorias")
@NamedQueries({
    @NamedQuery(name = "MvDocumentosCategorias.findAll", query = "SELECT m FROM MvDocumentosCategorias m"),
    @NamedQuery(name = "MvDocumentosCategorias.findByIdDocumentosCategorias", query = "SELECT m FROM MvDocumentosCategorias m WHERE m.idDocumentosCategorias = :idDocumentosCategorias")})
public class MvDocumentosCategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documentos_categorias")
    private Long idDocumentosCategorias;
    @JoinColumn(name = "id_categoria_campo", referencedColumnName = "id_categoria_campo")
    @ManyToOne
    private CatCategoriasCampos idCategoriaCampo;
    @JoinColumn(name = "id_documentos", referencedColumnName = "id_documentos")
    @ManyToOne
    private CatDocumentos idDocumentos;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;

    public MvDocumentosCategorias() {
    }

    public MvDocumentosCategorias(Long idDocumentosCategorias) {
        this.idDocumentosCategorias = idDocumentosCategorias;
    }

    public Long getIdDocumentosCategorias() {
        return idDocumentosCategorias;
    }

    public void setIdDocumentosCategorias(Long idDocumentosCategorias) {
        this.idDocumentosCategorias = idDocumentosCategorias;
    }

    public CatCategoriasCampos getIdCategoriaCampo() {
        return idCategoriaCampo;
    }

    public void setIdCategoriaCampo(CatCategoriasCampos idCategoriaCampo) {
        this.idCategoriaCampo = idCategoriaCampo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentosCategorias != null ? idDocumentosCategorias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvDocumentosCategorias)) {
            return false;
        }
        MvDocumentosCategorias other = (MvDocumentosCategorias) object;
        if ((this.idDocumentosCategorias == null && other.idDocumentosCategorias != null) || (this.idDocumentosCategorias != null && !this.idDocumentosCategorias.equals(other.idDocumentosCategorias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvDocumentosCategorias[ idDocumentosCategorias=" + idDocumentosCategorias + " ]";
    }
    
}
