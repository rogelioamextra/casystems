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
@Table(name = "cat_caracteristicas_negocios")
@NamedQueries({
    @NamedQuery(name = "CatCaracteristicasNegocios.findAll", query = "SELECT c FROM CatCaracteristicasNegocios c"),
    @NamedQuery(name = "CatCaracteristicasNegocios.findByIdCaracteristicaNegocio", query = "SELECT c FROM CatCaracteristicasNegocios c WHERE c.idCaracteristicaNegocio = :idCaracteristicaNegocio"),
    @NamedQuery(name = "CatCaracteristicasNegocios.findByNombre", query = "SELECT c FROM CatCaracteristicasNegocios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatCaracteristicasNegocios.findByDescripcion", query = "SELECT c FROM CatCaracteristicasNegocios c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatCaracteristicasNegocios.findByStatus", query = "SELECT c FROM CatCaracteristicasNegocios c WHERE c.status = :status"),
    @NamedQuery(name = "CatCaracteristicasNegocios.findByIdAmextra", query = "SELECT c FROM CatCaracteristicasNegocios c WHERE c.idAmextra = :idAmextra")})
public class CatCaracteristicasNegocios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caracteristica_negocio")
    private Long idCaracteristicaNegocio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private BigInteger idAmextra;
    @OneToMany(mappedBy = "idCaracteristicasNegocio")
    private List<DtDatosLaborales> dtDatosLaboralesList;

    public CatCaracteristicasNegocios() {
    }

    public CatCaracteristicasNegocios(Long idCaracteristicaNegocio) {
        this.idCaracteristicaNegocio = idCaracteristicaNegocio;
    }

    public Long getIdCaracteristicaNegocio() {
        return idCaracteristicaNegocio;
    }

    public void setIdCaracteristicaNegocio(Long idCaracteristicaNegocio) {
        this.idCaracteristicaNegocio = idCaracteristicaNegocio;
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
        hash += (idCaracteristicaNegocio != null ? idCaracteristicaNegocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCaracteristicasNegocios)) {
            return false;
        }
        CatCaracteristicasNegocios other = (CatCaracteristicasNegocios) object;
        if ((this.idCaracteristicaNegocio == null && other.idCaracteristicaNegocio != null) || (this.idCaracteristicaNegocio != null && !this.idCaracteristicaNegocio.equals(other.idCaracteristicaNegocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatCaracteristicasNegocios[ idCaracteristicaNegocio=" + idCaracteristicaNegocio + " ]";
    }
    
}
