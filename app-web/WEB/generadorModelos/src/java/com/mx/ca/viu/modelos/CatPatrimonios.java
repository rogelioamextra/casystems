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
@Table(name = "cat_patrimonios")
@NamedQueries({
    @NamedQuery(name = "CatPatrimonios.findAll", query = "SELECT c FROM CatPatrimonios c"),
    @NamedQuery(name = "CatPatrimonios.findByIdPatrimonio", query = "SELECT c FROM CatPatrimonios c WHERE c.idPatrimonio = :idPatrimonio"),
    @NamedQuery(name = "CatPatrimonios.findByNombre", query = "SELECT c FROM CatPatrimonios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatPatrimonios.findByDescripcion", query = "SELECT c FROM CatPatrimonios c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatPatrimonios.findByStatus", query = "SELECT c FROM CatPatrimonios c WHERE c.status = :status"),
    @NamedQuery(name = "CatPatrimonios.findByIdAmextra", query = "SELECT c FROM CatPatrimonios c WHERE c.idAmextra = :idAmextra")})
public class CatPatrimonios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_patrimonio")
    private Short idPatrimonio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private Short idAmextra;
    @OneToMany(mappedBy = "idTipoPatrimonio")
    private List<DtPatrimonio> dtPatrimonioList;

    public CatPatrimonios() {
    }

    public CatPatrimonios(Short idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public Short getIdPatrimonio() {
        return idPatrimonio;
    }

    public void setIdPatrimonio(Short idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
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

    public List<DtPatrimonio> getDtPatrimonioList() {
        return dtPatrimonioList;
    }

    public void setDtPatrimonioList(List<DtPatrimonio> dtPatrimonioList) {
        this.dtPatrimonioList = dtPatrimonioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatrimonio != null ? idPatrimonio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatPatrimonios)) {
            return false;
        }
        CatPatrimonios other = (CatPatrimonios) object;
        if ((this.idPatrimonio == null && other.idPatrimonio != null) || (this.idPatrimonio != null && !this.idPatrimonio.equals(other.idPatrimonio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatPatrimonios[ idPatrimonio=" + idPatrimonio + " ]";
    }
    
}
