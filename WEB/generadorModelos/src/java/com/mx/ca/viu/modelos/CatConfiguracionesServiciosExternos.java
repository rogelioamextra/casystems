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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_configuraciones_servicios_externos")
@NamedQueries({
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findAll", query = "SELECT c FROM CatConfiguracionesServiciosExternos c"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByIdConfiguracionServiciosExternos", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.idConfiguracionServiciosExternos = :idConfiguracionServiciosExternos"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByUrl", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.url = :url"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByUser", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.user = :user"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByPass", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.pass = :pass"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByStatus", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.status = :status"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByTypeRequest", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.typeRequest = :typeRequest"),
    @NamedQuery(name = "CatConfiguracionesServiciosExternos.findByPuerto", query = "SELECT c FROM CatConfiguracionesServiciosExternos c WHERE c.puerto = :puerto")})
public class CatConfiguracionesServiciosExternos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_configuracion_servicios_externos")
    private Long idConfiguracionServiciosExternos;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @Column(name = "pass")
    private String pass;
    @Lob
    @Column(name = "cabeceras")
    private Object cabeceras;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "type_request")
    private String typeRequest;
    @Column(name = "puerto")
    private String puerto;
    @JoinColumn(name = "id_servicio_validacion", referencedColumnName = "id_servicios_validaciones")
    @ManyToOne(optional = false)
    private CatServiciosValidacionesExternos idServicioValidacion;

    public CatConfiguracionesServiciosExternos() {
    }

    public CatConfiguracionesServiciosExternos(Long idConfiguracionServiciosExternos) {
        this.idConfiguracionServiciosExternos = idConfiguracionServiciosExternos;
    }

    public CatConfiguracionesServiciosExternos(Long idConfiguracionServiciosExternos, String url, String user, String pass, boolean status, String typeRequest) {
        this.idConfiguracionServiciosExternos = idConfiguracionServiciosExternos;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.status = status;
        this.typeRequest = typeRequest;
    }

    public Long getIdConfiguracionServiciosExternos() {
        return idConfiguracionServiciosExternos;
    }

    public void setIdConfiguracionServiciosExternos(Long idConfiguracionServiciosExternos) {
        this.idConfiguracionServiciosExternos = idConfiguracionServiciosExternos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Object getCabeceras() {
        return cabeceras;
    }

    public void setCabeceras(Object cabeceras) {
        this.cabeceras = cabeceras;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public CatServiciosValidacionesExternos getIdServicioValidacion() {
        return idServicioValidacion;
    }

    public void setIdServicioValidacion(CatServiciosValidacionesExternos idServicioValidacion) {
        this.idServicioValidacion = idServicioValidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracionServiciosExternos != null ? idConfiguracionServiciosExternos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatConfiguracionesServiciosExternos)) {
            return false;
        }
        CatConfiguracionesServiciosExternos other = (CatConfiguracionesServiciosExternos) object;
        if ((this.idConfiguracionServiciosExternos == null && other.idConfiguracionServiciosExternos != null) || (this.idConfiguracionServiciosExternos != null && !this.idConfiguracionServiciosExternos.equals(other.idConfiguracionServiciosExternos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatConfiguracionesServiciosExternos[ idConfiguracionServiciosExternos=" + idConfiguracionServiciosExternos + " ]";
    }
    
}
