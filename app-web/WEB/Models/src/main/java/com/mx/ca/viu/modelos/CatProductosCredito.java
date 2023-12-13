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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_productos_credito")

@NamedQueries({
    @NamedQuery(name = "CatProductosCredito.findAll", query = "SELECT c FROM CatProductosCredito c"),
    @NamedQuery(name = "CatProductosCredito.findByIdProductosCredito", query = "SELECT c FROM CatProductosCredito c WHERE c.idProductosCredito = :idProductosCredito"),
    @NamedQuery(name = "CatProductosCredito.findByClave", query = "SELECT c FROM CatProductosCredito c WHERE c.clave = :clave"),
    @NamedQuery(name = "CatProductosCredito.findByNombre", query = "SELECT c FROM CatProductosCredito c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatProductosCredito.findByStatus", query = "SELECT c FROM CatProductosCredito c WHERE c.status = :status"),
    @NamedQuery(name = "CatProductosCredito.findByTasaInteres", query = "SELECT c FROM CatProductosCredito c WHERE c.tasaInteres = :tasaInteres")})

public class CatProductosCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_productos_credito")
    private Long idProductosCredito;
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idProductoCredito")
    @JsonIgnore
    private List<MvSolicitudesAmextra> mvSolicitudesAmextraList;
    @Column(name = "tasa_interes")
    private String tasaInteres;

    public CatProductosCredito() {
    }

    public CatProductosCredito(Long idProductosCredito) {
        this.idProductosCredito = idProductosCredito;
    }

    public Long getIdProductosCredito() {
        return idProductosCredito;
    }

    public void setIdProductosCredito(Long idProductosCredito) {
        this.idProductosCredito = idProductosCredito;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public List<MvSolicitudesAmextra> getMvSolicitudesAmextraList() {
        return mvSolicitudesAmextraList;
    }

    public void setMvSolicitudesAmextraList(List<MvSolicitudesAmextra> mvSolicitudesAmextraList) {
        this.mvSolicitudesAmextraList = mvSolicitudesAmextraList;
    }

    public String getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(String tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductosCredito != null ? idProductosCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProductosCredito)) {
            return false;
        }
        CatProductosCredito other = (CatProductosCredito) object;
        if ((this.idProductosCredito == null && other.idProductosCredito != null) || (this.idProductosCredito != null && !this.idProductosCredito.equals(other.idProductosCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatProductosCredito[ idProductosCredito=" + idProductosCredito + " ]";
    }
    
}

