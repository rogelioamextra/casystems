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
@Table(name = "cat_subdirecciones")
@NamedQueries({
    @NamedQuery(name = "CatSubdirecciones.findAll", query = "SELECT c FROM CatSubdirecciones c"),
    @NamedQuery(name = "CatSubdirecciones.findByIdSubdireccion", query = "SELECT c FROM CatSubdirecciones c WHERE c.idSubdireccion = :idSubdireccion"),
    @NamedQuery(name = "CatSubdirecciones.findByNombre", query = "SELECT c FROM CatSubdirecciones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatSubdirecciones.findByStatus", query = "SELECT c FROM CatSubdirecciones c WHERE c.status = :status"),
    @NamedQuery(name = "CatSubdirecciones.findByNoSubdireccion", query = "SELECT c FROM CatSubdirecciones c WHERE c.noSubdireccion = :noSubdireccion")})
public class CatSubdirecciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subdireccion")
    private Long idSubdireccion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "no_subdireccion")
    private String noSubdireccion;
    @OneToMany(mappedBy = "idSubdireccion")
    private List<DtConfiguracionEmpresas> dtConfiguracionEmpresasList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;

    public CatSubdirecciones() {
    }

    public CatSubdirecciones(Long idSubdireccion) {
        this.idSubdireccion = idSubdireccion;
    }

    public CatSubdirecciones(Long idSubdireccion, String nombre) {
        this.idSubdireccion = idSubdireccion;
        this.nombre = nombre;
    }

    public Long getIdSubdireccion() {
        return idSubdireccion;
    }

    public void setIdSubdireccion(Long idSubdireccion) {
        this.idSubdireccion = idSubdireccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNoSubdireccion() {
        return noSubdireccion;
    }

    public void setNoSubdireccion(String noSubdireccion) {
        this.noSubdireccion = noSubdireccion;
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
        hash += (idSubdireccion != null ? idSubdireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatSubdirecciones)) {
            return false;
        }
        CatSubdirecciones other = (CatSubdirecciones) object;
        if ((this.idSubdireccion == null && other.idSubdireccion != null) || (this.idSubdireccion != null && !this.idSubdireccion.equals(other.idSubdireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatSubdirecciones[ idSubdireccion=" + idSubdireccion + " ]";
    }
    
}
