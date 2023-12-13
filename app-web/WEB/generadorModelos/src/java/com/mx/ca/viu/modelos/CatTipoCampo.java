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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_tipo_campo")
@NamedQueries({
    @NamedQuery(name = "CatTipoCampo.findAll", query = "SELECT c FROM CatTipoCampo c"),
    @NamedQuery(name = "CatTipoCampo.findByIdTipo", query = "SELECT c FROM CatTipoCampo c WHERE c.idTipo = :idTipo"),
    @NamedQuery(name = "CatTipoCampo.findByNombre", query = "SELECT c FROM CatTipoCampo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTipoCampo.findByStatus", query = "SELECT c FROM CatTipoCampo c WHERE c.status = :status")})
public class CatTipoCampo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Long idTipo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idTipoCampo")
    private List<CatCampos> catCamposList;

    public CatTipoCampo() {
    }

    public CatTipoCampo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
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

    public List<CatCampos> getCatCamposList() {
        return catCamposList;
    }

    public void setCatCamposList(List<CatCampos> catCamposList) {
        this.catCamposList = catCamposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTipoCampo)) {
            return false;
        }
        CatTipoCampo other = (CatTipoCampo) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTipoCampo[ idTipo=" + idTipo + " ]";
    }
    
}
