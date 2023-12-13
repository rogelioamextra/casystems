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
@Table(name = "cat_productos")
@NamedQueries({
    @NamedQuery(name = "CatProductos.findAll", query = "SELECT c FROM CatProductos c"),
    @NamedQuery(name = "CatProductos.findByIdProductos", query = "SELECT c FROM CatProductos c WHERE c.idProductos = :idProductos"),
    @NamedQuery(name = "CatProductos.findByNombre", query = "SELECT c FROM CatProductos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatProductos.findByStatus", query = "SELECT c FROM CatProductos c WHERE c.status = :status")})
public class CatProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_productos")
    private Long idProductos;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idProducto")
    private List<CatFolio> catFolioList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<MvConfigSolicitudes> mvConfigSolicitudesList;

    public CatProductos() {
    }

    public CatProductos(Long idProductos) {
        this.idProductos = idProductos;
    }

    public CatProductos(Long idProductos, String nombre) {
        this.idProductos = idProductos;
        this.nombre = nombre;
    }

    public Long getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Long idProductos) {
        this.idProductos = idProductos;
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

    public List<CatFolio> getCatFolioList() {
        return catFolioList;
    }

    public void setCatFolioList(List<CatFolio> catFolioList) {
        this.catFolioList = catFolioList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<MvConfigSolicitudes> getMvConfigSolicitudesList() {
        return mvConfigSolicitudesList;
    }

    public void setMvConfigSolicitudesList(List<MvConfigSolicitudes> mvConfigSolicitudesList) {
        this.mvConfigSolicitudesList = mvConfigSolicitudesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductos != null ? idProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProductos)) {
            return false;
        }
        CatProductos other = (CatProductos) object;
        if ((this.idProductos == null && other.idProductos != null) || (this.idProductos != null && !this.idProductos.equals(other.idProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatProductos[ idProductos=" + idProductos + " ]";
    }
    
}
