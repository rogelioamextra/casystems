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
@Table(name = "zona_tipo")
@NamedQueries({
    @NamedQuery(name = "ZonaTipo.findAll", query = "SELECT z FROM ZonaTipo z"),
    @NamedQuery(name = "ZonaTipo.findById", query = "SELECT z FROM ZonaTipo z WHERE z.id = :id"),
    @NamedQuery(name = "ZonaTipo.findByNombre", query = "SELECT z FROM ZonaTipo z WHERE z.nombre = :nombre")})
public class ZonaTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "zonaTipoId")
    private List<Colonia> coloniaList;

    public ZonaTipo() {
    }

    public ZonaTipo(Integer id) {
        this.id = id;
    }

    public ZonaTipo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
        if (!(object instanceof ZonaTipo)) {
            return false;
        }
        ZonaTipo other = (ZonaTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.ZonaTipo[ id=" + id + " ]";
    }
    
}
