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
@Table(name = "cat_tipos_validaciones_disponibles")
@NamedQueries({
    @NamedQuery(name = "CatTiposValidacionesDisponibles.findAll", query = "SELECT c FROM CatTiposValidacionesDisponibles c"),
    @NamedQuery(name = "CatTiposValidacionesDisponibles.findByIdTiposValidaciones", query = "SELECT c FROM CatTiposValidacionesDisponibles c WHERE c.idTiposValidaciones = :idTiposValidaciones"),
    @NamedQuery(name = "CatTiposValidacionesDisponibles.findByNombre", query = "SELECT c FROM CatTiposValidacionesDisponibles c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTiposValidacionesDisponibles.findByDescripcion", query = "SELECT c FROM CatTiposValidacionesDisponibles c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTiposValidacionesDisponibles.findByStatus", query = "SELECT c FROM CatTiposValidacionesDisponibles c WHERE c.status = :status")})
public class CatTiposValidacionesDisponibles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipos_validaciones")
    private Long idTiposValidaciones;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idTiposValidacionesDispoibles")
    private List<CatServiciosValidacionesExternos> catServiciosValidacionesExternosList;

    public CatTiposValidacionesDisponibles() {
    }

    public CatTiposValidacionesDisponibles(Long idTiposValidaciones) {
        this.idTiposValidaciones = idTiposValidaciones;
    }

    public CatTiposValidacionesDisponibles(Long idTiposValidaciones, String nombre) {
        this.idTiposValidaciones = idTiposValidaciones;
        this.nombre = nombre;
    }

    public Long getIdTiposValidaciones() {
        return idTiposValidaciones;
    }

    public void setIdTiposValidaciones(Long idTiposValidaciones) {
        this.idTiposValidaciones = idTiposValidaciones;
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

    public List<CatServiciosValidacionesExternos> getCatServiciosValidacionesExternosList() {
        return catServiciosValidacionesExternosList;
    }

    public void setCatServiciosValidacionesExternosList(List<CatServiciosValidacionesExternos> catServiciosValidacionesExternosList) {
        this.catServiciosValidacionesExternosList = catServiciosValidacionesExternosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiposValidaciones != null ? idTiposValidaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTiposValidacionesDisponibles)) {
            return false;
        }
        CatTiposValidacionesDisponibles other = (CatTiposValidacionesDisponibles) object;
        if ((this.idTiposValidaciones == null && other.idTiposValidaciones != null) || (this.idTiposValidaciones != null && !this.idTiposValidaciones.equals(other.idTiposValidaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTiposValidacionesDisponibles[ idTiposValidaciones=" + idTiposValidaciones + " ]";
    }
    
}
