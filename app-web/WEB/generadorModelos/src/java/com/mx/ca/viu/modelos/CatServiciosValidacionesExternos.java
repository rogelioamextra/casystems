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
@Table(name = "cat_servicios_validaciones_externos")
@NamedQueries({
    @NamedQuery(name = "CatServiciosValidacionesExternos.findAll", query = "SELECT c FROM CatServiciosValidacionesExternos c"),
    @NamedQuery(name = "CatServiciosValidacionesExternos.findByIdServiciosValidaciones", query = "SELECT c FROM CatServiciosValidacionesExternos c WHERE c.idServiciosValidaciones = :idServiciosValidaciones"),
    @NamedQuery(name = "CatServiciosValidacionesExternos.findByNombre", query = "SELECT c FROM CatServiciosValidacionesExternos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatServiciosValidacionesExternos.findByDescripcion", query = "SELECT c FROM CatServiciosValidacionesExternos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatServiciosValidacionesExternos.findByStatus", query = "SELECT c FROM CatServiciosValidacionesExternos c WHERE c.status = :status"),
    @NamedQuery(name = "CatServiciosValidacionesExternos.findByCoincidencia", query = "SELECT c FROM CatServiciosValidacionesExternos c WHERE c.coincidencia = :coincidencia")})
public class CatServiciosValidacionesExternos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicios_validaciones")
    private Long idServiciosValidaciones;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "coincidencia")
    private Boolean coincidencia;
    @OneToMany(mappedBy = "idServicioValidacion")
    private List<MensajesValidaciones> mensajesValidacionesList;
    @OneToMany(mappedBy = "idServicioValidacion")
    private List<MvAdminNivelRiesgo> mvAdminNivelRiesgoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicioValidacion")
    private List<CatConfiguracionesServiciosExternos> catConfiguracionesServiciosExternosList;
    @OneToMany(mappedBy = "idServicioValidacion")
    private List<DtValidacionesServiciosEmpresa> dtValidacionesServiciosEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicioValidacion")
    private List<MvResultadosValidacionesServiciosDigital> mvResultadosValidacionesServiciosDigitalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicioValidacion")
    private List<MvResultadosValidacionesServiciosBiometricos> mvResultadosValidacionesServiciosBiometricosList;
    @JoinColumn(name = "id_tipos_validaciones_dispoibles", referencedColumnName = "id_tipos_validaciones")
    @ManyToOne
    private CatTiposValidacionesDisponibles idTiposValidacionesDispoibles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicioValidacion")
    private List<MvResultadosValidacionesServiciosAutoridades> mvResultadosValidacionesServiciosAutoridadesList;

    public CatServiciosValidacionesExternos() {
    }

    public CatServiciosValidacionesExternos(Long idServiciosValidaciones) {
        this.idServiciosValidaciones = idServiciosValidaciones;
    }

    public CatServiciosValidacionesExternos(Long idServiciosValidaciones, String nombre, boolean status) {
        this.idServiciosValidaciones = idServiciosValidaciones;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdServiciosValidaciones() {
        return idServiciosValidaciones;
    }

    public void setIdServiciosValidaciones(Long idServiciosValidaciones) {
        this.idServiciosValidaciones = idServiciosValidaciones;
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

    public Boolean getCoincidencia() {
        return coincidencia;
    }

    public void setCoincidencia(Boolean coincidencia) {
        this.coincidencia = coincidencia;
    }

    public List<MensajesValidaciones> getMensajesValidacionesList() {
        return mensajesValidacionesList;
    }

    public void setMensajesValidacionesList(List<MensajesValidaciones> mensajesValidacionesList) {
        this.mensajesValidacionesList = mensajesValidacionesList;
    }

    public List<MvAdminNivelRiesgo> getMvAdminNivelRiesgoList() {
        return mvAdminNivelRiesgoList;
    }

    public void setMvAdminNivelRiesgoList(List<MvAdminNivelRiesgo> mvAdminNivelRiesgoList) {
        this.mvAdminNivelRiesgoList = mvAdminNivelRiesgoList;
    }

    public List<CatConfiguracionesServiciosExternos> getCatConfiguracionesServiciosExternosList() {
        return catConfiguracionesServiciosExternosList;
    }

    public void setCatConfiguracionesServiciosExternosList(List<CatConfiguracionesServiciosExternos> catConfiguracionesServiciosExternosList) {
        this.catConfiguracionesServiciosExternosList = catConfiguracionesServiciosExternosList;
    }

    public List<DtValidacionesServiciosEmpresa> getDtValidacionesServiciosEmpresaList() {
        return dtValidacionesServiciosEmpresaList;
    }

    public void setDtValidacionesServiciosEmpresaList(List<DtValidacionesServiciosEmpresa> dtValidacionesServiciosEmpresaList) {
        this.dtValidacionesServiciosEmpresaList = dtValidacionesServiciosEmpresaList;
    }

    public List<MvResultadosValidacionesServiciosDigital> getMvResultadosValidacionesServiciosDigitalList() {
        return mvResultadosValidacionesServiciosDigitalList;
    }

    public void setMvResultadosValidacionesServiciosDigitalList(List<MvResultadosValidacionesServiciosDigital> mvResultadosValidacionesServiciosDigitalList) {
        this.mvResultadosValidacionesServiciosDigitalList = mvResultadosValidacionesServiciosDigitalList;
    }

    public List<MvResultadosValidacionesServiciosBiometricos> getMvResultadosValidacionesServiciosBiometricosList() {
        return mvResultadosValidacionesServiciosBiometricosList;
    }

    public void setMvResultadosValidacionesServiciosBiometricosList(List<MvResultadosValidacionesServiciosBiometricos> mvResultadosValidacionesServiciosBiometricosList) {
        this.mvResultadosValidacionesServiciosBiometricosList = mvResultadosValidacionesServiciosBiometricosList;
    }

    public CatTiposValidacionesDisponibles getIdTiposValidacionesDispoibles() {
        return idTiposValidacionesDispoibles;
    }

    public void setIdTiposValidacionesDispoibles(CatTiposValidacionesDisponibles idTiposValidacionesDispoibles) {
        this.idTiposValidacionesDispoibles = idTiposValidacionesDispoibles;
    }

    public List<MvResultadosValidacionesServiciosAutoridades> getMvResultadosValidacionesServiciosAutoridadesList() {
        return mvResultadosValidacionesServiciosAutoridadesList;
    }

    public void setMvResultadosValidacionesServiciosAutoridadesList(List<MvResultadosValidacionesServiciosAutoridades> mvResultadosValidacionesServiciosAutoridadesList) {
        this.mvResultadosValidacionesServiciosAutoridadesList = mvResultadosValidacionesServiciosAutoridadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiciosValidaciones != null ? idServiciosValidaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatServiciosValidacionesExternos)) {
            return false;
        }
        CatServiciosValidacionesExternos other = (CatServiciosValidacionesExternos) object;
        if ((this.idServiciosValidaciones == null && other.idServiciosValidaciones != null) || (this.idServiciosValidaciones != null && !this.idServiciosValidaciones.equals(other.idServiciosValidaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatServiciosValidacionesExternos[ idServiciosValidaciones=" + idServiciosValidaciones + " ]";
    }
    
}
