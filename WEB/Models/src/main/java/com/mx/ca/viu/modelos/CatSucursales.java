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
@Table(name = "cat_sucursales")
@NamedQueries({
    @NamedQuery(name = "CatSucursales.findAll", query = "SELECT c FROM CatSucursales c"),
    @NamedQuery(name = "CatSucursales.findByIdSucursales", query = "SELECT c FROM CatSucursales c WHERE c.idSucursales = :idSucursales"),
    @NamedQuery(name = "CatSucursales.findByNombre", query = "SELECT c FROM CatSucursales c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatSucursales.findByDescripcion", query = "SELECT c FROM CatSucursales c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatSucursales.findByStatus", query = "SELECT c FROM CatSucursales c WHERE c.status = :status"),
    @NamedQuery(name = "CatSucursales.findByCodEmpresaAmextra", query = "SELECT c FROM CatSucursales c WHERE c.codEmpresaAmextra = :codEmpresaAmextra"),
    @NamedQuery(name = "CatSucursales.findByCodAgenciaAmextra", query = "SELECT c FROM CatSucursales c WHERE c.codAgenciaAmextra = :codAgenciaAmextra")})
public class CatSucursales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sucursales")
    private Long idSucursales;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "cod_empresa_amextra")
    private String codEmpresaAmextra;
    @Column(name = "cod_agencia_amextra")
    private String codAgenciaAmextra;
    @OneToMany(mappedBy = "idSucursal")
    @JsonIgnore

    private List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    @JsonIgnore
    private CatEmpresas idEmpresa;
    @OneToMany(mappedBy = "idSucursal")
    @JsonIgnore
    private List<CatClientes> catClientesList;

    public CatSucursales() {
    }

    public CatSucursales(Long idSucursales) {
        this.idSucursales = idSucursales;
    }

    public CatSucursales(Long idSucursales, String nombre) {
        this.idSucursales = idSucursales;
        this.nombre = nombre;
    }

    public Long getIdSucursales() {
        return idSucursales;
    }

    public void setIdSucursales(Long idSucursales) {
        this.idSucursales = idSucursales;
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

    public String getCodEmpresaAmextra() {
        return codEmpresaAmextra;
    }

    public void setCodEmpresaAmextra(String codEmpresaAmextra) {
        this.codEmpresaAmextra = codEmpresaAmextra;
    }

    public String getCodAgenciaAmextra() {
        return codAgenciaAmextra;
    }

    public void setCodAgenciaAmextra(String codAgenciaAmextra) {
        this.codAgenciaAmextra = codAgenciaAmextra;
    }

    public List<DtConfiguracionEmpresas> getDtConfiguracionEmpresasList() {
        return dtConfiguracionEmpresasList;
    }

    public void setDtConfiguracionEmpresasList(List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList) {
        this.dtConfiguracionEmpresasList = dtConfiguracionEmpresasList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSucursales != null ? idSucursales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatSucursales)) {
            return false;
        }
        CatSucursales other = (CatSucursales) object;
        if ((this.idSucursales == null && other.idSucursales != null) || (this.idSucursales != null && !this.idSucursales.equals(other.idSucursales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatSucursales[ idSucursales=" + idSucursales + " ]";
    }

    public List<CatClientes> getCatClientesList() {
        return catClientesList;
    }

    public void setCatClientesList(List<CatClientes> catClientesList) {
        this.catClientesList = catClientesList;
    }

}
