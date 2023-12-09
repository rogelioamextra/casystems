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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_identificaciones")
@NamedQueries({
    @NamedQuery(name = "CatIdentificaciones.findAll", query = "SELECT c FROM CatIdentificaciones c"),
    @NamedQuery(name = "CatIdentificaciones.findByIdIdentificaciones", query = "SELECT c FROM CatIdentificaciones c WHERE c.idIdentificaciones = :idIdentificaciones"),
    @NamedQuery(name = "CatIdentificaciones.findByNombre", query = "SELECT c FROM CatIdentificaciones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatIdentificaciones.findByDescripcion", query = "SELECT c FROM CatIdentificaciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatIdentificaciones.findByStatus", query = "SELECT c FROM CatIdentificaciones c WHERE c.status = :status"),
    @NamedQuery(name = "CatIdentificaciones.findByIdAmextra", query = "SELECT c FROM CatIdentificaciones c WHERE c.idAmextra = :idAmextra")})
public class CatIdentificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_identificaciones")
    private Long idIdentificaciones;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private Short idAmextra;

    public CatIdentificaciones() {
    }

    public Long getIdIdentificaciones() {
        return idIdentificaciones;
    }

    public void setIdIdentificaciones(Long idIdentificaciones) {
        this.idIdentificaciones = idIdentificaciones;
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

    public Short getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(Short idAmextra) {
        this.idAmextra = idAmextra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdentificaciones != null ? idIdentificaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatIdentificaciones)) {
            return false;
        }
        CatIdentificaciones other = (CatIdentificaciones) object;
        if ((this.idIdentificaciones == null && other.idIdentificaciones != null) || (this.idIdentificaciones != null && !this.idIdentificaciones.equals(other.idIdentificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatIdentificaciones[ idIdentificaciones=" + idIdentificaciones + " ]";
    }
    
}
