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
@Table(name = "cat_modulo")
@NamedQueries({
    @NamedQuery(name = "CatModulo.findAll", query = "SELECT c FROM CatModulo c"),
    @NamedQuery(name = "CatModulo.findByIdModulo", query = "SELECT c FROM CatModulo c WHERE c.idModulo = :idModulo"),
    @NamedQuery(name = "CatModulo.findByNombre", query = "SELECT c FROM CatModulo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatModulo.findByStatus", query = "SELECT c FROM CatModulo c WHERE c.status = :status")})
public class CatModulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modulo")
    private Long idModulo;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    public CatModulo() {
    }

    public CatModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public CatModulo(Long idModulo, boolean status) {
        this.idModulo = idModulo;
        this.status = status;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatModulo)) {
            return false;
        }
        CatModulo other = (CatModulo) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatModulo[ idModulo=" + idModulo + " ]";
    }
    
}
