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
@Table(name = "cat_tipos_residencias")
@NamedQueries({
    @NamedQuery(name = "CatTiposResidencias.findAll", query = "SELECT c FROM CatTiposResidencias c"),
    @NamedQuery(name = "CatTiposResidencias.findByIdTipoResidencia", query = "SELECT c FROM CatTiposResidencias c WHERE c.idTipoResidencia = :idTipoResidencia"),
    @NamedQuery(name = "CatTiposResidencias.findByNombre", query = "SELECT c FROM CatTiposResidencias c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTiposResidencias.findByDescripcion", query = "SELECT c FROM CatTiposResidencias c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTiposResidencias.findByStatus", query = "SELECT c FROM CatTiposResidencias c WHERE c.status = :status"),
    @NamedQuery(name = "CatTiposResidencias.findByIdAmextra", query = "SELECT c FROM CatTiposResidencias c WHERE c.idAmextra = :idAmextra")})
public class CatTiposResidencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_residencia")
    private Long idTipoResidencia;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private String idAmextra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoResidencia")
    @JsonIgnore
    private List<CatDirecciones> catDireccionesList;

    public CatTiposResidencias() {
    }

    public CatTiposResidencias(Long idTipoResidencia) {
        this.idTipoResidencia = idTipoResidencia;
    }

    public Long getIdTipoResidencia() {
        return idTipoResidencia;
    }

    public void setIdTipoResidencia(Long idTipoResidencia) {
        this.idTipoResidencia = idTipoResidencia;
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
        hash += (idTipoResidencia != null ? idTipoResidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTiposResidencias)) {
            return false;
        }
        CatTiposResidencias other = (CatTiposResidencias) object;
        if ((this.idTipoResidencia == null && other.idTipoResidencia != null) || (this.idTipoResidencia != null && !this.idTipoResidencia.equals(other.idTipoResidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTiposResidencias[ idTipoResidencia=" + idTipoResidencia + " ]";
    }
    
}
