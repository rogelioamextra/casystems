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
@Table(name = "cat_tipos_viviendas")
@NamedQueries({
    @NamedQuery(name = "CatTiposViviendas.findAll", query = "SELECT c FROM CatTiposViviendas c"),
    @NamedQuery(name = "CatTiposViviendas.findByIdVivienda", query = "SELECT c FROM CatTiposViviendas c WHERE c.idVivienda = :idVivienda"),
    @NamedQuery(name = "CatTiposViviendas.findByNombre", query = "SELECT c FROM CatTiposViviendas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTiposViviendas.findByDescripcion", query = "SELECT c FROM CatTiposViviendas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTiposViviendas.findByStatus", query = "SELECT c FROM CatTiposViviendas c WHERE c.status = :status"),
    @NamedQuery(name = "CatTiposViviendas.findByIdAmextra", query = "SELECT c FROM CatTiposViviendas c WHERE c.idAmextra = :idAmextra")})
public class CatTiposViviendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vivienda")
    private Long idVivienda;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "id_amextra")
    private String idAmextra;
    @OneToMany(mappedBy = "idTipoVivienda")
    private List<CatDirecciones> catDireccionesList;

    public CatTiposViviendas() {
    }

    public CatTiposViviendas(Long idVivienda) {
        this.idVivienda = idVivienda;
    }

    public CatTiposViviendas(Long idVivienda, boolean status) {
        this.idVivienda = idVivienda;
        this.status = status;
    }

    public Long getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Long idVivienda) {
        this.idVivienda = idVivienda;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(String idAmextra) {
        this.idAmextra = idAmextra;
    }

    public List<CatDirecciones> getCatDireccionesList() {
        return catDireccionesList;
    }

    public void setCatDireccionesList(List<CatDirecciones> catDireccionesList) {
        this.catDireccionesList = catDireccionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVivienda != null ? idVivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTiposViviendas)) {
            return false;
        }
        CatTiposViviendas other = (CatTiposViviendas) object;
        if ((this.idVivienda == null && other.idVivienda != null) || (this.idVivienda != null && !this.idVivienda.equals(other.idVivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTiposViviendas[ idVivienda=" + idVivienda + " ]";
    }
    
}
