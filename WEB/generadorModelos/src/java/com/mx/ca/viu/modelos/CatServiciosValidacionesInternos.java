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
import javax.persistence.Lob;
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
@Table(name = "cat_servicios_validaciones_internos")
@NamedQueries({
    @NamedQuery(name = "CatServiciosValidacionesInternos.findAll", query = "SELECT c FROM CatServiciosValidacionesInternos c"),
    @NamedQuery(name = "CatServiciosValidacionesInternos.findByIdServiciosValidacionesInternos", query = "SELECT c FROM CatServiciosValidacionesInternos c WHERE c.idServiciosValidacionesInternos = :idServiciosValidacionesInternos"),
    @NamedQuery(name = "CatServiciosValidacionesInternos.findByNombre", query = "SELECT c FROM CatServiciosValidacionesInternos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatServiciosValidacionesInternos.findByUrl", query = "SELECT c FROM CatServiciosValidacionesInternos c WHERE c.url = :url"),
    @NamedQuery(name = "CatServiciosValidacionesInternos.findByStatus", query = "SELECT c FROM CatServiciosValidacionesInternos c WHERE c.status = :status")})
public class CatServiciosValidacionesInternos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicios_validaciones_internos")
    private Long idServiciosValidacionesInternos;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "url")
    private String url;
    @Lob
    @Column(name = "request")
    private Object request;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne
    private CatDocumentos idDocumento;
    @OneToMany(mappedBy = "idServiciosValidacionesInternos")
    private List<MvServiciosValidacionesInternos> mvServiciosValidacionesInternosList;

    public CatServiciosValidacionesInternos() {
    }

    public CatServiciosValidacionesInternos(Long idServiciosValidacionesInternos) {
        this.idServiciosValidacionesInternos = idServiciosValidacionesInternos;
    }

    public Long getIdServiciosValidacionesInternos() {
        return idServiciosValidacionesInternos;
    }

    public void setIdServiciosValidacionesInternos(Long idServiciosValidacionesInternos) {
        this.idServiciosValidacionesInternos = idServiciosValidacionesInternos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    public List<MvServiciosValidacionesInternos> getMvServiciosValidacionesInternosList() {
        return mvServiciosValidacionesInternosList;
    }

    public void setMvServiciosValidacionesInternosList(List<MvServiciosValidacionesInternos> mvServiciosValidacionesInternosList) {
        this.mvServiciosValidacionesInternosList = mvServiciosValidacionesInternosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiciosValidacionesInternos != null ? idServiciosValidacionesInternos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatServiciosValidacionesInternos)) {
            return false;
        }
        CatServiciosValidacionesInternos other = (CatServiciosValidacionesInternos) object;
        if ((this.idServiciosValidacionesInternos == null && other.idServiciosValidacionesInternos != null) || (this.idServiciosValidacionesInternos != null && !this.idServiciosValidacionesInternos.equals(other.idServiciosValidacionesInternos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatServiciosValidacionesInternos[ idServiciosValidacionesInternos=" + idServiciosValidacionesInternos + " ]";
    }
    
}
