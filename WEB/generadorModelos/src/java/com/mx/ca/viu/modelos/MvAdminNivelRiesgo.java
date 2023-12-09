/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_admin_nivel_riesgo")
@NamedQueries({
    @NamedQuery(name = "MvAdminNivelRiesgo.findAll", query = "SELECT m FROM MvAdminNivelRiesgo m"),
    @NamedQuery(name = "MvAdminNivelRiesgo.findByIdAdminNivelRiesgo", query = "SELECT m FROM MvAdminNivelRiesgo m WHERE m.idAdminNivelRiesgo = :idAdminNivelRiesgo"),
    @NamedQuery(name = "MvAdminNivelRiesgo.findByStatus", query = "SELECT m FROM MvAdminNivelRiesgo m WHERE m.status = :status"),
    @NamedQuery(name = "MvAdminNivelRiesgo.findByIndistinto", query = "SELECT m FROM MvAdminNivelRiesgo m WHERE m.indistinto = :indistinto"),
    @NamedQuery(name = "MvAdminNivelRiesgo.findByPorcentajeCoincidencia", query = "SELECT m FROM MvAdminNivelRiesgo m WHERE m.porcentajeCoincidencia = :porcentajeCoincidencia")})
public class MvAdminNivelRiesgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admin_nivel_riesgo")
    private Long idAdminNivelRiesgo;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "indistinto")
    private Boolean indistinto;
    @Column(name = "porcentaje_coincidencia")
    private BigInteger porcentajeCoincidencia;
    @JoinColumn(name = "id_servicio_validacion", referencedColumnName = "id_servicios_validaciones")
    @ManyToOne
    private CatServiciosValidacionesExternos idServicioValidacion;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne
    private MvConfigSolicitudes idConfigSolicitud;

    public MvAdminNivelRiesgo() {
    }

    public MvAdminNivelRiesgo(Long idAdminNivelRiesgo) {
        this.idAdminNivelRiesgo = idAdminNivelRiesgo;
    }

    public Long getIdAdminNivelRiesgo() {
        return idAdminNivelRiesgo;
    }

    public void setIdAdminNivelRiesgo(Long idAdminNivelRiesgo) {
        this.idAdminNivelRiesgo = idAdminNivelRiesgo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIndistinto() {
        return indistinto;
    }

    public void setIndistinto(Boolean indistinto) {
        this.indistinto = indistinto;
    }

    public BigInteger getPorcentajeCoincidencia() {
        return porcentajeCoincidencia;
    }

    public void setPorcentajeCoincidencia(BigInteger porcentajeCoincidencia) {
        this.porcentajeCoincidencia = porcentajeCoincidencia;
    }

    public CatServiciosValidacionesExternos getIdServicioValidacion() {
        return idServicioValidacion;
    }

    public void setIdServicioValidacion(CatServiciosValidacionesExternos idServicioValidacion) {
        this.idServicioValidacion = idServicioValidacion;
    }

    public MvConfigSolicitudes getIdConfigSolicitud() {
        return idConfigSolicitud;
    }

    public void setIdConfigSolicitud(MvConfigSolicitudes idConfigSolicitud) {
        this.idConfigSolicitud = idConfigSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdminNivelRiesgo != null ? idAdminNivelRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvAdminNivelRiesgo)) {
            return false;
        }
        MvAdminNivelRiesgo other = (MvAdminNivelRiesgo) object;
        if ((this.idAdminNivelRiesgo == null && other.idAdminNivelRiesgo != null) || (this.idAdminNivelRiesgo != null && !this.idAdminNivelRiesgo.equals(other.idAdminNivelRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvAdminNivelRiesgo[ idAdminNivelRiesgo=" + idAdminNivelRiesgo + " ]";
    }
    
}
