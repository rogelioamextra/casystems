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
@Table(name = "inegi_clave_municipio")
@NamedQueries({
    @NamedQuery(name = "InegiClaveMunicipio.findAll", query = "SELECT i FROM InegiClaveMunicipio i"),
    @NamedQuery(name = "InegiClaveMunicipio.findById", query = "SELECT i FROM InegiClaveMunicipio i WHERE i.id = :id"),
    @NamedQuery(name = "InegiClaveMunicipio.findByNombre", query = "SELECT i FROM InegiClaveMunicipio i WHERE i.nombre = :nombre")})
public class InegiClaveMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "inegiClaveMunicipioId")
    private List<Colonia> coloniaList;

    public InegiClaveMunicipio() {
    }

    public InegiClaveMunicipio(Integer id) {
        this.id = id;
    }

    public InegiClaveMunicipio(Integer id, String nombre) {
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
        if (!(object instanceof InegiClaveMunicipio)) {
            return false;
        }
        InegiClaveMunicipio other = (InegiClaveMunicipio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.InegiClaveMunicipio[ id=" + id + " ]";
    }
    
}
