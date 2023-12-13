/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_destino_creditos")
@NamedQueries({
    @NamedQuery(name = "CatDestinoCreditos.findAll", query = "SELECT c FROM CatDestinoCreditos c"),
    @NamedQuery(name = "CatDestinoCreditos.findByIdDestinoCredito", query = "SELECT c FROM CatDestinoCreditos c WHERE c.idDestinoCredito = :idDestinoCredito"),
    @NamedQuery(name = "CatDestinoCreditos.findByNombre", query = "SELECT c FROM CatDestinoCreditos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatDestinoCreditos.findByDescripcion", query = "SELECT c FROM CatDestinoCreditos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatDestinoCreditos.findByStatus", query = "SELECT c FROM CatDestinoCreditos c WHERE c.status = :status"),
    @NamedQuery(name = "CatDestinoCreditos.findByIdAmextra", query = "SELECT c FROM CatDestinoCreditos c WHERE c.idAmextra = :idAmextra")})
public class CatDestinoCreditos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_destino_credito")
    private Long idDestinoCredito;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private Long idAmextra;

    public CatDestinoCreditos() {
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

    public Long getIdDestinoCredito() {
        return idDestinoCredito;
    }

    public void setIdDestinoCredito(Long idDestinoCredito) {
        this.idDestinoCredito = idDestinoCredito;
    }

    public Long getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(Long idAmextra) {
        this.idAmextra = idAmextra;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDestinoCredito != null ? idDestinoCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDestinoCreditos)) {
            return false;
        }
        CatDestinoCreditos other = (CatDestinoCreditos) object;
        if ((this.idDestinoCredito == null && other.idDestinoCredito != null) || (this.idDestinoCredito != null && !this.idDestinoCredito.equals(other.idDestinoCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatDestinoCreditos[ idDestinoCredito=" + idDestinoCredito + " ]";
    }
    
}
