/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

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
@Table(name = "cat_valores")
@NamedQueries({
    @NamedQuery(name = "CatValores.findAll", query = "SELECT c FROM CatValores c"),
    @NamedQuery(name = "CatValores.findByIdValor", query = "SELECT c FROM CatValores c WHERE c.idValor = :idValor"),
    @NamedQuery(name = "CatValores.findByNombre", query = "SELECT c FROM CatValores c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatValores.findByDescripcion", query = "SELECT c FROM CatValores c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatValores.findByStatus", query = "SELECT c FROM CatValores c WHERE c.status = :status")})
public class CatValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_valor")
    private Long idValor;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValor")
    private List<MvConfigRiesgo> mvConfigRiesgoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValor")
    private List<ValidacionesValores> validacionesValoresList;

    public CatValores() {
    }

    public CatValores(Long idValor) {
        this.idValor = idValor;
    }

    public CatValores(Long idValor, String nombre, boolean status) {
        this.idValor = idValor;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdValor() {
        return idValor;
    }

    public void setIdValor(Long idValor) {
        this.idValor = idValor;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<MvConfigRiesgo> getMvConfigRiesgoList() {
        return mvConfigRiesgoList;
    }

    public void setMvConfigRiesgoList(List<MvConfigRiesgo> mvConfigRiesgoList) {
        this.mvConfigRiesgoList = mvConfigRiesgoList;
    }

    public List<ValidacionesValores> getValidacionesValoresList() {
        return validacionesValoresList;
    }

    public void setValidacionesValoresList(List<ValidacionesValores> validacionesValoresList) {
        this.validacionesValoresList = validacionesValoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValor != null ? idValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatValores)) {
            return false;
        }
        CatValores other = (CatValores) object;
        if ((this.idValor == null && other.idValor != null) || (this.idValor != null && !this.idValor.equals(other.idValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatValores[ idValor=" + idValor + " ]";
    }
    
}
