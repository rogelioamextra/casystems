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
@Table(name = "cat_validaciones")
@NamedQueries({
    @NamedQuery(name = "CatValidaciones.findAll", query = "SELECT c FROM CatValidaciones c"),
    @NamedQuery(name = "CatValidaciones.findByIdValidaciones", query = "SELECT c FROM CatValidaciones c WHERE c.idValidaciones = :idValidaciones"),
    @NamedQuery(name = "CatValidaciones.findByNombre", query = "SELECT c FROM CatValidaciones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatValidaciones.findByDescripcion", query = "SELECT c FROM CatValidaciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatValidaciones.findByStatus", query = "SELECT c FROM CatValidaciones c WHERE c.status = :status")})
public class CatValidaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_validaciones")
    private Long idValidaciones;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValidacion")
    private List<MvConfigRiesgo> mvConfigRiesgoList;
    @OneToMany(mappedBy = "idValidaciones")
    private List<MvServiciosValidacionesInternos> mvServiciosValidacionesInternosList;
    @OneToMany(mappedBy = "idValidaciones")
    private List<DtConfiguracionValidaciones> dtConfiguracionValidacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValidacion")
    private List<ValidacionesValores> validacionesValoresList;

    public CatValidaciones() {
    }

    public CatValidaciones(Long idValidaciones) {
        this.idValidaciones = idValidaciones;
    }

    public Long getIdValidaciones() {
        return idValidaciones;
    }

    public void setIdValidaciones(Long idValidaciones) {
        this.idValidaciones = idValidaciones;
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

    public List<MvConfigRiesgo> getMvConfigRiesgoList() {
        return mvConfigRiesgoList;
    }

    public void setMvConfigRiesgoList(List<MvConfigRiesgo> mvConfigRiesgoList) {
        this.mvConfigRiesgoList = mvConfigRiesgoList;
    }

    public List<MvServiciosValidacionesInternos> getMvServiciosValidacionesInternosList() {
        return mvServiciosValidacionesInternosList;
    }

    public void setMvServiciosValidacionesInternosList(List<MvServiciosValidacionesInternos> mvServiciosValidacionesInternosList) {
        this.mvServiciosValidacionesInternosList = mvServiciosValidacionesInternosList;
    }

    public List<DtConfiguracionValidaciones> getDtConfiguracionValidacionesList() {
        return dtConfiguracionValidacionesList;
    }

    public void setDtConfiguracionValidacionesList(List<DtConfiguracionValidaciones> dtConfiguracionValidacionesList) {
        this.dtConfiguracionValidacionesList = dtConfiguracionValidacionesList;
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
        hash += (idValidaciones != null ? idValidaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatValidaciones)) {
            return false;
        }
        CatValidaciones other = (CatValidaciones) object;
        if ((this.idValidaciones == null && other.idValidaciones != null) || (this.idValidaciones != null && !this.idValidaciones.equals(other.idValidaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatValidaciones[ idValidaciones=" + idValidaciones + " ]";
    }
    
}
