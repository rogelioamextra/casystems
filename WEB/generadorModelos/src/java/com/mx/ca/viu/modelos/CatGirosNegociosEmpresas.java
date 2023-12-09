/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "cat_giros_negocios_empresas")
@NamedQueries({
    @NamedQuery(name = "CatGirosNegociosEmpresas.findAll", query = "SELECT c FROM CatGirosNegociosEmpresas c"),
    @NamedQuery(name = "CatGirosNegociosEmpresas.findByIdGirosNegocio", query = "SELECT c FROM CatGirosNegociosEmpresas c WHERE c.idGirosNegocio = :idGirosNegocio"),
    @NamedQuery(name = "CatGirosNegociosEmpresas.findByNombre", query = "SELECT c FROM CatGirosNegociosEmpresas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatGirosNegociosEmpresas.findByDescripcion", query = "SELECT c FROM CatGirosNegociosEmpresas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatGirosNegociosEmpresas.findByStatus", query = "SELECT c FROM CatGirosNegociosEmpresas c WHERE c.status = :status"),
    @NamedQuery(name = "CatGirosNegociosEmpresas.findByIdAmextra", query = "SELECT c FROM CatGirosNegociosEmpresas c WHERE c.idAmextra = :idAmextra")})
public class CatGirosNegociosEmpresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_giros_negocio")
    private Long idGirosNegocio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "id_amextra")
    private BigInteger idAmextra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGiroNegocio")
    private List<DtDatosLaborales> dtDatosLaboralesList;

    public CatGirosNegociosEmpresas() {
    }

    public CatGirosNegociosEmpresas(Long idGirosNegocio) {
        this.idGirosNegocio = idGirosNegocio;
    }

    public CatGirosNegociosEmpresas(Long idGirosNegocio, boolean status) {
        this.idGirosNegocio = idGirosNegocio;
        this.status = status;
    }

    public Long getIdGirosNegocio() {
        return idGirosNegocio;
    }

    public void setIdGirosNegocio(Long idGirosNegocio) {
        this.idGirosNegocio = idGirosNegocio;
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

    public BigInteger getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(BigInteger idAmextra) {
        this.idAmextra = idAmextra;
    }

    public List<DtDatosLaborales> getDtDatosLaboralesList() {
        return dtDatosLaboralesList;
    }

    public void setDtDatosLaboralesList(List<DtDatosLaborales> dtDatosLaboralesList) {
        this.dtDatosLaboralesList = dtDatosLaboralesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGirosNegocio != null ? idGirosNegocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatGirosNegociosEmpresas)) {
            return false;
        }
        CatGirosNegociosEmpresas other = (CatGirosNegociosEmpresas) object;
        if ((this.idGirosNegocio == null && other.idGirosNegocio != null) || (this.idGirosNegocio != null && !this.idGirosNegocio.equals(other.idGirosNegocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatGirosNegociosEmpresas[ idGirosNegocio=" + idGirosNegocio + " ]";
    }
    
}
