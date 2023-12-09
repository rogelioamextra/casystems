/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "estado")
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findById", query = "SELECT e FROM Estado e WHERE e.id = :id"),
    @NamedQuery(name = "Estado.findByInegiClave", query = "SELECT e FROM Estado e WHERE e.inegiClave = :inegiClave"),
    @NamedQuery(name = "Estado.findByNombre", query = "SELECT e FROM Estado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estado.findByEstadoClave", query = "SELECT e FROM Estado e WHERE e.estadoClave = :estadoClave")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "inegi_clave")
    private String inegiClave;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado_clave")
    private String estadoClave;
    @JsonIgnore
    @OneToMany(mappedBy = "estadoId")
    private List<CodigoPostal> codigoPostalList;

    @JsonIgnore
    @OneToMany(mappedBy = "estadoId")
    private List<Municipio> municipioList;
    @JsonIgnore
    @OneToMany(mappedBy = "estadoId")
    private List<Colonia> coloniaList;
    @JsonIgnore
    @OneToMany(mappedBy = "estadoId")
    private List<Ciudad> ciudadList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarNacimiento")
    

    private List<CatPersonas> catPersonasList;

    public Estado() {
    }

    public Estado(Integer id) {
        this.id = id;
    }

    public Estado(Integer id, String inegiClave, String nombre) {
        this.id = id;
        this.inegiClave = inegiClave;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInegiClave() {
        return inegiClave;
    }

    public void setInegiClave(String inegiClave) {
        this.inegiClave = inegiClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoClave() {
        return estadoClave;
    }

    public void setEstadoClave(String estadoClave) {
        this.estadoClave = estadoClave;
    }

    public List<CodigoPostal> getCodigoPostalList() {
        return codigoPostalList;
    }

    public void setCodigoPostalList(List<CodigoPostal> codigoPostalList) {
        this.codigoPostalList = codigoPostalList;
    }



    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    public List<Colonia> getColoniaList() {
        return coloniaList;
    }

    public void setColoniaList(List<Colonia> coloniaList) {
        this.coloniaList = coloniaList;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public List<CatPersonas> getCatPersonasList() {
        return catPersonasList;
    }

    public void setCatPersonasList(List<CatPersonas> catPersonasList) {
        this.catPersonasList = catPersonasList;
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
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.Estado[ id=" + id + " ]";
    }

}
