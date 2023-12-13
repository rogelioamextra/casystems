/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "cat_grado_maximo_estudios")
@NamedQueries({
    @NamedQuery(name = "CatGradoMaximoEstudios.findAll", query = "SELECT c FROM CatGradoMaximoEstudios c"),
    @NamedQuery(name = "CatGradoMaximoEstudios.findByIdGradoEstudios", query = "SELECT c FROM CatGradoMaximoEstudios c WHERE c.idGradoEstudios = :idGradoEstudios"),
    @NamedQuery(name = "CatGradoMaximoEstudios.findByNombre", query = "SELECT c FROM CatGradoMaximoEstudios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatGradoMaximoEstudios.findByDescripcion", query = "SELECT c FROM CatGradoMaximoEstudios c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatGradoMaximoEstudios.findByStatus", query = "SELECT c FROM CatGradoMaximoEstudios c WHERE c.status = :status"),
    @NamedQuery(name = "CatGradoMaximoEstudios.findByIdAmextra", query = "SELECT c FROM CatGradoMaximoEstudios c WHERE c.idAmextra = :idAmextra")})
public class CatGradoMaximoEstudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grado_estudios")
    private Integer idGradoEstudios;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private BigInteger idAmextra;
    @OneToMany(mappedBy = "idGradoMaximoEstudios")
    private List<CatPersonas> catPersonasList;

    public CatGradoMaximoEstudios() {
    }

    public CatGradoMaximoEstudios(Integer idGradoEstudios) {
        this.idGradoEstudios = idGradoEstudios;
    }

    public CatGradoMaximoEstudios(Integer idGradoEstudios, String nombre) {
        this.idGradoEstudios = idGradoEstudios;
        this.nombre = nombre;
    }

    public Integer getIdGradoEstudios() {
        return idGradoEstudios;
    }

    public void setIdGradoEstudios(Integer idGradoEstudios) {
        this.idGradoEstudios = idGradoEstudios;
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

    public BigInteger getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(BigInteger idAmextra) {
        this.idAmextra = idAmextra;
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
        hash += (idGradoEstudios != null ? idGradoEstudios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatGradoMaximoEstudios)) {
            return false;
        }
        CatGradoMaximoEstudios other = (CatGradoMaximoEstudios) object;
        if ((this.idGradoEstudios == null && other.idGradoEstudios != null) || (this.idGradoEstudios != null && !this.idGradoEstudios.equals(other.idGradoEstudios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatGradoMaximoEstudios[ idGradoEstudios=" + idGradoEstudios + " ]";
    }
    
}
