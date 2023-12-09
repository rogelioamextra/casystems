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
@Table(name = "cat_zonas")
@NamedQueries({
    @NamedQuery(name = "CatZonas.findAll", query = "SELECT c FROM CatZonas c"),
    @NamedQuery(name = "CatZonas.findByIdZona", query = "SELECT c FROM CatZonas c WHERE c.idZona = :idZona"),
    @NamedQuery(name = "CatZonas.findByNombre", query = "SELECT c FROM CatZonas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatZonas.findByDescripcion", query = "SELECT c FROM CatZonas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatZonas.findByStatus", query = "SELECT c FROM CatZonas c WHERE c.status = :status"),
    @NamedQuery(name = "CatZonas.findByNoZona", query = "SELECT c FROM CatZonas c WHERE c.noZona = :noZona")})
public class CatZonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_zona")
    private Long idZona;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "no_zona")
    private String noZona;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_region", referencedColumnName = "id_regiones")
    @ManyToOne(optional = false)
    private CatRegiones idRegion;
    @OneToMany(mappedBy = "idZona")
    private List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList;

    public CatZonas() {
    }

    public CatZonas(Long idZona) {
        this.idZona = idZona;
    }

    public CatZonas(Long idZona, String nombre) {
        this.idZona = idZona;
        this.nombre = nombre;
    }

    public Long getIdZona() {
        return idZona;
    }

    public void setIdZona(Long idZona) {
        this.idZona = idZona;
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

    public String getNoZona() {
        return noZona;
    }

    public void setNoZona(String noZona) {
        this.noZona = noZona;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatRegiones getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(CatRegiones idRegion) {
        this.idRegion = idRegion;
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
        hash += (idZona != null ? idZona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatZonas)) {
            return false;
        }
        CatZonas other = (CatZonas) object;
        if ((this.idZona == null && other.idZona != null) || (this.idZona != null && !this.idZona.equals(other.idZona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatZonas[ idZona=" + idZona + " ]";
    }
    
}
