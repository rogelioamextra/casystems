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
@Table(name = "cat_estados")
@NamedQueries({
    @NamedQuery(name = "CatEstados.findAll", query = "SELECT c FROM CatEstados c"),
    @NamedQuery(name = "CatEstados.findByIdEstado", query = "SELECT c FROM CatEstados c WHERE c.idEstado = :idEstado"),
    @NamedQuery(name = "CatEstados.findByNombre", query = "SELECT c FROM CatEstados c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatEstados.findByCodigoEstado", query = "SELECT c FROM CatEstados c WHERE c.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "CatEstados.findByCodigoEstadoNacimiento", query = "SELECT c FROM CatEstados c WHERE c.codigoEstadoNacimiento = :codigoEstadoNacimiento")})
public class CatEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Long idEstado;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "codigo_estado")
    private String codigoEstado;
    @Column(name = "codigo_estado_nacimiento")
    private String codigoEstadoNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private List<CatDirecciones> catDireccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private List<CatMunicipios> catMunicipiosList;
    @OneToMany(mappedBy = "lugarNacimiento")
    private List<CatPersonas> catPersonasList;

    public CatEstados() {
    }

    public CatEstados(Long idEstado) {
        this.idEstado = idEstado;
    }

    public CatEstados(Long idEstado, String nombre, String codigoEstado) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.codigoEstado = codigoEstado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getCodigoEstadoNacimiento() {
        return codigoEstadoNacimiento;
    }

    public void setCodigoEstadoNacimiento(String codigoEstadoNacimiento) {
        this.codigoEstadoNacimiento = codigoEstadoNacimiento;
    }

    public List<CatDirecciones> getCatDireccionesList() {
        return catDireccionesList;
    }

    public void setCatDireccionesList(List<CatDirecciones> catDireccionesList) {
        this.catDireccionesList = catDireccionesList;
    }

    public List<CatMunicipios> getCatMunicipiosList() {
        return catMunicipiosList;
    }

    public void setCatMunicipiosList(List<CatMunicipios> catMunicipiosList) {
        this.catMunicipiosList = catMunicipiosList;
    }

    public List<CatPersonas> getCatPersonasList() {
        return catPersonasList;
    }

    public void setCatPersonasList(List<CatPersonas> catPersonasList) {
        this.catPersonasList = catPersonasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstados)) {
            return false;
        }
        CatEstados other = (CatEstados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatEstados[ idEstado=" + idEstado + " ]";
    }
    
}
