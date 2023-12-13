/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cat_parentescos")
@NamedQueries({
    @NamedQuery(name = "CatParentescos.findAll", query = "SELECT c FROM CatParentescos c"),
    @NamedQuery(name = "CatParentescos.findByIdParentesco", query = "SELECT c FROM CatParentescos c WHERE c.idParentesco = :idParentesco"),
    @NamedQuery(name = "CatParentescos.findByNombre", query = "SELECT c FROM CatParentescos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatParentescos.findByDescripcion", query = "SELECT c FROM CatParentescos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatParentescos.findByStatus", query = "SELECT c FROM CatParentescos c WHERE c.status = :status"),
    @NamedQuery(name = "CatParentescos.findByIdAmextra", query = "SELECT c FROM CatParentescos c WHERE c.idAmextra = :idAmextra")})
public class CatParentescos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parentesco")
    private Integer idParentesco;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private Short idAmextra;
    @OneToMany(mappedBy = "idParentesco")
    @JsonIgnore
    private List<DtReferenciasPersonales> dtReferenciasPersonalesList;

    public CatParentescos() {
    }

    public Integer getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(Integer idParentesco) {
        this.idParentesco = idParentesco;
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

    public List<DtReferenciasPersonales> getDtReferenciasPersonalesList() {
        return dtReferenciasPersonalesList;
    }

    public void setDtReferenciasPersonalesList(List<DtReferenciasPersonales> dtReferenciasPersonalesList) {
        this.dtReferenciasPersonalesList = dtReferenciasPersonalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParentesco != null ? idParentesco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatParentescos)) {
            return false;
        }
        CatParentescos other = (CatParentescos) object;
        if ((this.idParentesco == null && other.idParentesco != null) || (this.idParentesco != null && !this.idParentesco.equals(other.idParentesco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatParentescos[ idParentesco=" + idParentesco + " ]";
    }
    
}
