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
@Table(name = "mv_servicios_validaciones_internos")
@NamedQueries({
    @NamedQuery(name = "MvServiciosValidacionesInternos.findAll", query = "SELECT m FROM MvServiciosValidacionesInternos m"),
    @NamedQuery(name = "MvServiciosValidacionesInternos.findById", query = "SELECT m FROM MvServiciosValidacionesInternos m WHERE m.id = :id"),
    @NamedQuery(name = "MvServiciosValidacionesInternos.findByStatus", query = "SELECT m FROM MvServiciosValidacionesInternos m WHERE m.status = :status"),
    @NamedQuery(name = "MvServiciosValidacionesInternos.findByDescripcion", query = "SELECT m FROM MvServiciosValidacionesInternos m WHERE m.descripcion = :descripcion")})
public class MvServiciosValidacionesInternos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_servicios_validaciones_internos", referencedColumnName = "id_servicios_validaciones_internos")
    @ManyToOne
    private CatServiciosValidacionesInternos idServiciosValidacionesInternos;
    @JoinColumn(name = "id_validaciones", referencedColumnName = "id_validaciones")
    @ManyToOne
    private CatValidaciones idValidaciones;

    public MvServiciosValidacionesInternos() {
    }

    public MvServiciosValidacionesInternos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CatServiciosValidacionesInternos getIdServiciosValidacionesInternos() {
        return idServiciosValidacionesInternos;
    }

    public void setIdServiciosValidacionesInternos(CatServiciosValidacionesInternos idServiciosValidacionesInternos) {
        this.idServiciosValidacionesInternos = idServiciosValidacionesInternos;
    }

    public CatValidaciones getIdValidaciones() {
        return idValidaciones;
    }

    public void setIdValidaciones(CatValidaciones idValidaciones) {
        this.idValidaciones = idValidaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvServiciosValidacionesInternos)) {
            return false;
        }
        MvServiciosValidacionesInternos other = (MvServiciosValidacionesInternos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvServiciosValidacionesInternos[ id=" + id + " ]";
    }
    
}
