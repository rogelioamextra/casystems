/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "cat_nacionalidades")
@NamedQueries({
    @NamedQuery(name = "CatNacionalidades.findAll", query = "SELECT c FROM CatNacionalidades c"),
    @NamedQuery(name = "CatNacionalidades.findByIdNacionalidad", query = "SELECT c FROM CatNacionalidades c WHERE c.idNacionalidad = :idNacionalidad"),
    @NamedQuery(name = "CatNacionalidades.findByNombre", query = "SELECT c FROM CatNacionalidades c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatNacionalidades.findByDescripcion", query = "SELECT c FROM CatNacionalidades c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatNacionalidades.findByStatus", query = "SELECT c FROM CatNacionalidades c WHERE c.status = :status"),
    @NamedQuery(name = "CatNacionalidades.findByCodigoPais", query = "SELECT c FROM CatNacionalidades c WHERE c.codigoPais = :codigoPais"),
    @NamedQuery(name = "CatNacionalidades.findByClaveNacionalidad", query = "SELECT c FROM CatNacionalidades c WHERE c.claveNacionalidad = :claveNacionalidad"),
    @NamedQuery(name = "CatNacionalidades.findByIdAmextra", query = "SELECT c FROM CatNacionalidades c WHERE c.idAmextra = :idAmextra")})
public class CatNacionalidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nacionalidad")
    private Long idNacionalidad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "codigo_pais")
    private int codigoPais;
    @Basic(optional = false)
    @Column(name = "clave_nacionalidad")
    private String claveNacionalidad;
    @Column(name = "id_amextra")
    private BigInteger idAmextra;
    @OneToMany(mappedBy = "idNacionalidad")
    private List<CatPersonas> catPersonasList;

    public CatNacionalidades() {
    }

    public CatNacionalidades(Long idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public CatNacionalidades(Long idNacionalidad, boolean status, int codigoPais, String claveNacionalidad) {
        this.idNacionalidad = idNacionalidad;
        this.status = status;
        this.codigoPais = codigoPais;
        this.claveNacionalidad = claveNacionalidad;
    }

    public Long getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Long idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getClaveNacionalidad() {
        return claveNacionalidad;
    }

    public void setClaveNacionalidad(String claveNacionalidad) {
        this.claveNacionalidad = claveNacionalidad;
    }

    public BigInteger getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(BigInteger idAmextra) {
        this.idAmextra = idAmextra;
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
        hash += (idNacionalidad != null ? idNacionalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatNacionalidades)) {
            return false;
        }
        CatNacionalidades other = (CatNacionalidades) object;
        if ((this.idNacionalidad == null && other.idNacionalidad != null) || (this.idNacionalidad != null && !this.idNacionalidad.equals(other.idNacionalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatNacionalidades[ idNacionalidad=" + idNacionalidad + " ]";
    }
    
}
