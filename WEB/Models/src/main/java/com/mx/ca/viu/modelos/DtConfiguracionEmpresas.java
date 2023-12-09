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
@Table(name = "dt_configuracion_empresas")
@NamedQueries({
    @NamedQuery(name = "DtConfiguracionEmpresas.findAll", query = "SELECT d FROM DtConfiguracionEmpresas d"),
    @NamedQuery(name = "DtConfiguracionEmpresas.findByIdConfiguracionEmpresa", query = "SELECT d FROM DtConfiguracionEmpresas d WHERE d.idConfiguracionEmpresa = :idConfiguracionEmpresa")})
public class DtConfiguracionEmpresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_configuracion_empresa")
    private Long idConfiguracionEmpresa;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_region", referencedColumnName = "id_regiones")
    @ManyToOne
    private CatRegiones idRegion;
    @JoinColumn(name = "id_subdireccion", referencedColumnName = "id_subdireccion")
    @ManyToOne
    private CatSubdirecciones idSubdireccion;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursales")
    @ManyToOne
    private CatSucursales idSucursal;
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    @ManyToOne
    private CatZonas idZona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfiguracionEmpresa")
    private List<CatUsuarios> catUsuariosList;

    public DtConfiguracionEmpresas() {
        idEmpresa=new CatEmpresas();
    }

    public DtConfiguracionEmpresas(Long idConfiguracionEmpresa) {
        this.idConfiguracionEmpresa = idConfiguracionEmpresa;
    }

    public Long getIdConfiguracionEmpresa() {
        return idConfiguracionEmpresa;
    }

    public void setIdConfiguracionEmpresa(Long idConfiguracionEmpresa) {
        this.idConfiguracionEmpresa = idConfiguracionEmpresa;
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

    public CatSubdirecciones getIdSubdireccion() {
        return idSubdireccion;
    }

    public void setIdSubdireccion(CatSubdirecciones idSubdireccion) {
        this.idSubdireccion = idSubdireccion;
    }

    public CatSucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(CatSucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public CatZonas getIdZona() {
        return idZona;
    }

    public void setIdZona(CatZonas idZona) {
        this.idZona = idZona;
    }

    public List<CatUsuarios> getCatUsuariosList() {
        return catUsuariosList;
    }

    public void setCatUsuariosList(List<CatUsuarios> catUsuariosList) {
        this.catUsuariosList = catUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracionEmpresa != null ? idConfiguracionEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtConfiguracionEmpresas)) {
            return false;
        }
        DtConfiguracionEmpresas other = (DtConfiguracionEmpresas) object;
        if ((this.idConfiguracionEmpresa == null && other.idConfiguracionEmpresa != null) || (this.idConfiguracionEmpresa != null && !this.idConfiguracionEmpresa.equals(other.idConfiguracionEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtConfiguracionEmpresas[ idConfiguracionEmpresa=" + idConfiguracionEmpresa + " ]";
    }
    
}
