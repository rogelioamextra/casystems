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
@Table(name = "mv_config_solicitudes")
@NamedQueries({
    @NamedQuery(name = "MvConfigSolicitudes.findAll", query = "SELECT m FROM MvConfigSolicitudes m"),
    @NamedQuery(name = "MvConfigSolicitudes.findByIdConfigSolicitud", query = "SELECT m FROM MvConfigSolicitudes m WHERE m.idConfigSolicitud = :idConfigSolicitud"),
    @NamedQuery(name = "MvConfigSolicitudes.findByStatus", query = "SELECT m FROM MvConfigSolicitudes m WHERE m.status = :status")})
public class MvConfigSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config_solicitud")
    private Long idConfigSolicitud;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigSolicitud")
    private List<MvConfigRiesgo> mvConfigRiesgoList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_productos")
    @ManyToOne(optional = false)
    private CatProductos idProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigSolicitud")
    private List<MvConfigNivelRiesgo> mvConfigNivelRiesgoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigSolicitud")
    private List<MvDatosSolicitud> mvDatosSolicitudList;
    @OneToMany(mappedBy = "idConfigSolicitud")
    private List<MvAdminNivelRiesgo> mvAdminNivelRiesgoList;
    @OneToMany(mappedBy = "idConfigSolicitud")
    private List<MvSolicitudProducto> mvSolicitudProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigSolicitud")
    private List<MvGuardadosProceso> mvGuardadosProcesoList;
    @OneToMany(mappedBy = "idConfigSolicitud")
    private List<MvConfigMensaje> mvConfigMensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigSolicitud")
    private List<MvConfigTiempoVida> mvConfigTiempoVidaList;

    public MvConfigSolicitudes() {
    }

    public MvConfigSolicitudes(Long idConfigSolicitud) {
        this.idConfigSolicitud = idConfigSolicitud;
    }

    public MvConfigSolicitudes(Long idConfigSolicitud, boolean status) {
        this.idConfigSolicitud = idConfigSolicitud;
        this.status = status;
    }

    public Long getIdConfigSolicitud() {
        return idConfigSolicitud;
    }

    public void setIdConfigSolicitud(Long idConfigSolicitud) {
        this.idConfigSolicitud = idConfigSolicitud;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<MvConfigRiesgo> getMvConfigRiesgoList() {
        return mvConfigRiesgoList;
    }

    public void setMvConfigRiesgoList(List<MvConfigRiesgo> mvConfigRiesgoList) {
        this.mvConfigRiesgoList = mvConfigRiesgoList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatProductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(CatProductos idProducto) {
        this.idProducto = idProducto;
    }

    public List<MvConfigNivelRiesgo> getMvConfigNivelRiesgoList() {
        return mvConfigNivelRiesgoList;
    }

    public void setMvConfigNivelRiesgoList(List<MvConfigNivelRiesgo> mvConfigNivelRiesgoList) {
        this.mvConfigNivelRiesgoList = mvConfigNivelRiesgoList;
    }

    public List<MvDatosSolicitud> getMvDatosSolicitudList() {
        return mvDatosSolicitudList;
    }

    public void setMvDatosSolicitudList(List<MvDatosSolicitud> mvDatosSolicitudList) {
        this.mvDatosSolicitudList = mvDatosSolicitudList;
    }

    public List<MvAdminNivelRiesgo> getMvAdminNivelRiesgoList() {
        return mvAdminNivelRiesgoList;
    }

    public void setMvAdminNivelRiesgoList(List<MvAdminNivelRiesgo> mvAdminNivelRiesgoList) {
        this.mvAdminNivelRiesgoList = mvAdminNivelRiesgoList;
    }

    public List<MvSolicitudProducto> getMvSolicitudProductoList() {
        return mvSolicitudProductoList;
    }

    public void setMvSolicitudProductoList(List<MvSolicitudProducto> mvSolicitudProductoList) {
        this.mvSolicitudProductoList = mvSolicitudProductoList;
    }

    public List<MvGuardadosProceso> getMvGuardadosProcesoList() {
        return mvGuardadosProcesoList;
    }

    public void setMvGuardadosProcesoList(List<MvGuardadosProceso> mvGuardadosProcesoList) {
        this.mvGuardadosProcesoList = mvGuardadosProcesoList;
    }

    public List<MvConfigMensaje> getMvConfigMensajeList() {
        return mvConfigMensajeList;
    }

    public void setMvConfigMensajeList(List<MvConfigMensaje> mvConfigMensajeList) {
        this.mvConfigMensajeList = mvConfigMensajeList;
    }

    public List<MvConfigTiempoVida> getMvConfigTiempoVidaList() {
        return mvConfigTiempoVidaList;
    }

    public void setMvConfigTiempoVidaList(List<MvConfigTiempoVida> mvConfigTiempoVidaList) {
        this.mvConfigTiempoVidaList = mvConfigTiempoVidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfigSolicitud != null ? idConfigSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvConfigSolicitudes)) {
            return false;
        }
        MvConfigSolicitudes other = (MvConfigSolicitudes) object;
        if ((this.idConfigSolicitud == null && other.idConfigSolicitud != null) || (this.idConfigSolicitud != null && !this.idConfigSolicitud.equals(other.idConfigSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvConfigSolicitudes[ idConfigSolicitud=" + idConfigSolicitud + " ]";
    }
    
}
