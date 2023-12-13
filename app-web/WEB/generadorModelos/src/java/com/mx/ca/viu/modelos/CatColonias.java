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
@Table(name = "cat_colonias")
@NamedQueries({
    @NamedQuery(name = "CatColonias.findAll", query = "SELECT c FROM CatColonias c"),
    @NamedQuery(name = "CatColonias.findByIdColonia", query = "SELECT c FROM CatColonias c WHERE c.idColonia = :idColonia"),
    @NamedQuery(name = "CatColonias.findByNombre", query = "SELECT c FROM CatColonias c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatColonias.findByCodigoColonia", query = "SELECT c FROM CatColonias c WHERE c.codigoColonia = :codigoColonia"),
    @NamedQuery(name = "CatColonias.findByTipo", query = "SELECT c FROM CatColonias c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CatColonias.findByCp", query = "SELECT c FROM CatColonias c WHERE c.cp = :cp"),
    @NamedQuery(name = "CatColonias.findByCodigoMunicipio", query = "SELECT c FROM CatColonias c WHERE c.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "CatColonias.findByCodigoEstado", query = "SELECT c FROM CatColonias c WHERE c.codigoEstado = :codigoEstado")})
public class CatColonias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_colonia")
    private Long idColonia;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo_colonia")
    private String codigoColonia;
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "cp")
    private String cp;
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Column(name = "codigo_estado")
    private String codigoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColonia")
    private List<CatDirecciones> catDireccionesList;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private CatMunicipios idMunicipio;

    public CatColonias() {
    }

    public CatColonias(Long idColonia) {
        this.idColonia = idColonia;
    }

    public CatColonias(Long idColonia, String nombre, String cp) {
        this.idColonia = idColonia;
        this.nombre = nombre;
        this.cp = cp;
    }

    public Long getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Long idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoColonia() {
        return codigoColonia;
    }

    public void setCodigoColonia(String codigoColonia) {
        this.codigoColonia = codigoColonia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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

    public CatMunicipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(CatMunicipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColonia != null ? idColonia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatColonias)) {
            return false;
        }
        CatColonias other = (CatColonias) object;
        if ((this.idColonia == null && other.idColonia != null) || (this.idColonia != null && !this.idColonia.equals(other.idColonia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatColonias[ idColonia=" + idColonia + " ]";
    }
    
}
