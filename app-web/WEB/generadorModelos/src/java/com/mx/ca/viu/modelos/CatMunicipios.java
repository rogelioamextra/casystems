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
@Table(name = "cat_municipios")
@NamedQueries({
    @NamedQuery(name = "CatMunicipios.findAll", query = "SELECT c FROM CatMunicipios c"),
    @NamedQuery(name = "CatMunicipios.findByIdMunicipio", query = "SELECT c FROM CatMunicipios c WHERE c.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "CatMunicipios.findByNombre", query = "SELECT c FROM CatMunicipios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatMunicipios.findByCodigoMunicipio", query = "SELECT c FROM CatMunicipios c WHERE c.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "CatMunicipios.findByCodigoEstado", query = "SELECT c FROM CatMunicipios c WHERE c.codigoEstado = :codigoEstado")})
public class CatMunicipios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_municipio")
    private Long idMunicipio;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Column(name = "codigo_estado")
    private String codigoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private List<CatDirecciones> catDireccionesList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private CatEstados idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private List<CatColonias> catColoniasList;

    public CatMunicipios() {
    }

    public CatMunicipios(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public CatMunicipios(Long idMunicipio, String nombre, String codigoMunicipio) {
        this.idMunicipio = idMunicipio;
        this.nombre = nombre;
        this.codigoMunicipio = codigoMunicipio;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public List<CatDirecciones> getCatDireccionesList() {
        return catDireccionesList;
    }

    public void setCatDireccionesList(List<CatDirecciones> catDireccionesList) {
        this.catDireccionesList = catDireccionesList;
    }

    public CatEstados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(CatEstados idEstado) {
        this.idEstado = idEstado;
    }

    public List<CatColonias> getCatColoniasList() {
        return catColoniasList;
    }

    public void setCatColoniasList(List<CatColonias> catColoniasList) {
        this.catColoniasList = catColoniasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMunicipios)) {
            return false;
        }
        CatMunicipios other = (CatMunicipios) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatMunicipios[ idMunicipio=" + idMunicipio + " ]";
    }
    
}
