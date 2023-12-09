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
@Table(name = "cat_tipo_dato")
@NamedQueries({
    @NamedQuery(name = "CatTipoDato.findAll", query = "SELECT c FROM CatTipoDato c"),
    @NamedQuery(name = "CatTipoDato.findByIdTipoDato", query = "SELECT c FROM CatTipoDato c WHERE c.idTipoDato = :idTipoDato"),
    @NamedQuery(name = "CatTipoDato.findByNombre", query = "SELECT c FROM CatTipoDato c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTipoDato.findByStatus", query = "SELECT c FROM CatTipoDato c WHERE c.status = :status")})
public class CatTipoDato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_dato")
    private Long idTipoDato;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;

    public CatTipoDato() {
    }

    public CatTipoDato(Long idTipoDato) {
        this.idTipoDato = idTipoDato;
    }

    public CatTipoDato(Long idTipoDato, String nombre) {
        this.idTipoDato = idTipoDato;
        this.nombre = nombre;
    }

    public Long getIdTipoDato() {
        return idTipoDato;
    }

    public void setIdTipoDato(Long idTipoDato) {
        this.idTipoDato = idTipoDato;
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

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDato != null ? idTipoDato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTipoDato)) {
            return false;
        }
        CatTipoDato other = (CatTipoDato) object;
        if ((this.idTipoDato == null && other.idTipoDato != null) || (this.idTipoDato != null && !this.idTipoDato.equals(other.idTipoDato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTipoDato[ idTipoDato=" + idTipoDato + " ]";
    }
    
}
