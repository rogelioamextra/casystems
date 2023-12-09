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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_regiones")
@NamedQueries({
    @NamedQuery(name = "CatRegiones.findAll", query = "SELECT c FROM CatRegiones c"),
    @NamedQuery(name = "CatRegiones.findByIdRegiones", query = "SELECT c FROM CatRegiones c WHERE c.idRegiones = :idRegiones"),
    @NamedQuery(name = "CatRegiones.findByNombre", query = "SELECT c FROM CatRegiones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatRegiones.findByDescripcion", query = "SELECT c FROM CatRegiones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatRegiones.findByStatus", query = "SELECT c FROM CatRegiones c WHERE c.status = :status")})
public class CatRegiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_regiones")
    private Long idRegiones;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegion")
    private List<CatZonas> catZonasList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @OneToMany(mappedBy = "idRegion")
    private List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList;

    public CatRegiones() {
    }

    public CatRegiones(Long idRegiones) {
        this.idRegiones = idRegiones;
    }

    public CatRegiones(Long idRegiones, String nombre) {
        this.idRegiones = idRegiones;
        this.nombre = nombre;
    }

    public Long getIdRegiones() {
        return idRegiones;
    }

    public void setIdRegiones(Long idRegiones) {
        this.idRegiones = idRegiones;
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

    public List<CatZonas> getCatZonasList() {
        return catZonasList;
    }

    public void setCatZonasList(List<CatZonas> catZonasList) {
        this.catZonasList = catZonasList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<DtConfiguracionEmpresas> getDtConfiguracionEmpresasList() {
        return dtConfiguracionEmpresasList;
    }

    public void setDtConfiguracionEmpresasList(List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList) {
        this.dtConfiguracionEmpresasList = dtConfiguracionEmpresasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegiones != null ? idRegiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatRegiones)) {
            return false;
        }
        CatRegiones other = (CatRegiones) object;
        if ((this.idRegiones == null && other.idRegiones != null) || (this.idRegiones != null && !this.idRegiones.equals(other.idRegiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatRegiones[ idRegiones=" + idRegiones + " ]";
    }
    
}
