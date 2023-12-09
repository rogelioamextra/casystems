/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Transient;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_clientes")
@NamedQueries({
    @NamedQuery(name = "CatClientes.findAll", query = "SELECT c FROM CatClientes c"),
    @NamedQuery(name = "CatClientes.findByIdCliente", query = "SELECT c FROM CatClientes c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "CatClientes.findByStatus", query = "SELECT c FROM CatClientes c WHERE c.status = :status"),
    @NamedQuery(name = "CatClientes.findByAproboVerificacionSms", query = "SELECT c FROM CatClientes c WHERE c.aproboVerificacionSms = :aproboVerificacionSms")})
public class CatClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "json")
    @JsonIgnore
    private String json;
    @Basic(optional = false)
    @Column(name = "aprobo_verificacion_sms")
    private boolean aproboVerificacionSms;
    @Column(name = "aprobo_nip")
    private boolean aproboNip;
  
    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CatDirecciones idDireccion;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CatPersonas idPersona;
    @JoinColumn(name = "id_datos_laborales", referencedColumnName = "id_datos_laborales")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private DtDatosLaborales idDatosLaborales;
    @OneToMany(mappedBy = "idCliente", cascade = CascadeType.ALL)
    private List<DtReferenciasPersonales> dtReferenciasPersonalesList;
    @JoinColumn(name = "id_dt_identificacion", referencedColumnName = "id_dt_identificacion")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private DtIdentificaciones idDtIdentificacion;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursales")
    @ManyToOne

    private CatSucursales idSucursal;

    @JoinColumn(name = "id_asesor", referencedColumnName = "id_usuario")
    @ManyToOne
    @JsonIgnore

    private CatUsuarios idAsesor;
    @Transient
    private String nombreAsesor;

    public CatClientes() {
    }

    public CatClientes(Long idCliente) {
        this.idCliente = idCliente;
    }

    public CatClientes(Long idCliente, boolean aproboVerificacionSms) {
        this.idCliente = idCliente;
        this.aproboVerificacionSms = aproboVerificacionSms;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean getAproboVerificacionSms() {
        return aproboVerificacionSms;
    }

    public void setAproboVerificacionSms(boolean aproboVerificacionSms) {
        this.aproboVerificacionSms = aproboVerificacionSms;
    }

    public CatDirecciones getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(CatDirecciones idDireccion) {
        this.idDireccion = idDireccion;
    }

    public CatPersonas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(CatPersonas idPersona) {
        this.idPersona = idPersona;
    }

    public DtDatosLaborales getIdDatosLaborales() {
        return idDatosLaborales;
    }

    public void setIdDatosLaborales(DtDatosLaborales idDatosLaborales) {
        this.idDatosLaborales = idDatosLaborales;
    }

    public List<DtReferenciasPersonales> getDtReferenciasPersonalesList() {
        return dtReferenciasPersonalesList;
    }

    public void setDtReferenciasPersonalesList(List<DtReferenciasPersonales> dtReferenciasPersonalesList) {
        this.dtReferenciasPersonalesList = dtReferenciasPersonalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatClientes)) {
            return false;
        }
        CatClientes other = (CatClientes) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatClientes[ idCliente=" + idCliente + " ]";
    }

    public DtIdentificaciones getIdDtIdentificacion() {
        return idDtIdentificacion;
    }

    public void setIdDtIdentificacion(DtIdentificaciones idDtIdentificacion) {
        this.idDtIdentificacion = idDtIdentificacion;
    }

    public CatSucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(CatSucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public CatUsuarios getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(CatUsuarios idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public boolean isAproboNip() {
        return aproboNip;
    }

    public void setAproboNip(boolean aproboNip) {
        this.aproboNip = aproboNip;
    }

   
    
    
    
    

}
