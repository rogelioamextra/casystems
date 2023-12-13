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
@Table(name = "asentamiento_tipo")
@NamedQueries({
    @NamedQuery(name = "AsentamientoTipo.findAll", query = "SELECT a FROM AsentamientoTipo a"),
    @NamedQuery(name = "AsentamientoTipo.findById", query = "SELECT a FROM AsentamientoTipo a WHERE a.id = :id"),
    @NamedQuery(name = "AsentamientoTipo.findByNombre", query = "SELECT a FROM AsentamientoTipo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AsentamientoTipo.findBySepomexClave", query = "SELECT a FROM AsentamientoTipo a WHERE a.sepomexClave = :sepomexClave")})
public class AsentamientoTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "sepomex_clave")
    private String sepomexClave;
    @OneToMany(mappedBy = "asentamientoTipoId")
    private List<Colonia> coloniaList;

    public AsentamientoTipo() {
    }

    public AsentamientoTipo(Integer id) {
        this.id = id;
    }

    public AsentamientoTipo(Integer id, String nombre, String sepomexClave) {
        this.id = id;
        this.nombre = nombre;
        this.sepomexClave = sepomexClave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSepomexClave() {
        return sepomexClave;
    }

    public void setSepomexClave(String sepomexClave) {
        this.sepomexClave = sepomexClave;
    }

    public List<Colonia> getColoniaList() {
        return coloniaList;
    }

    public void setColoniaList(List<Colonia> coloniaList) {
        this.coloniaList = coloniaList;
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
        if (!(object instanceof AsentamientoTipo)) {
            return false;
        }
        AsentamientoTipo other = (AsentamientoTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.AsentamientoTipo[ id=" + id + " ]";
    }
    
}
