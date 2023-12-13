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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_config_nivel_riesgo")
@NamedQueries({
    @NamedQuery(name = "MvConfigNivelRiesgo.findAll", query = "SELECT m FROM MvConfigNivelRiesgo m"),
    @NamedQuery(name = "MvConfigNivelRiesgo.findByIdConfigNivelRiesgo", query = "SELECT m FROM MvConfigNivelRiesgo m WHERE m.idConfigNivelRiesgo = :idConfigNivelRiesgo"),
    @NamedQuery(name = "MvConfigNivelRiesgo.findByStatus", query = "SELECT m FROM MvConfigNivelRiesgo m WHERE m.status = :status")})
public class MvConfigNivelRiesgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config_nivel_riesgo")
    private Long idConfigNivelRiesgo;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne(optional = false)
    private CatDocumentos idDocumento;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne(optional = false)
    private MvConfigSolicitudes idConfigSolicitud;

    public MvConfigNivelRiesgo() {
    }

    public MvConfigNivelRiesgo(Long idConfigNivelRiesgo) {
        this.idConfigNivelRiesgo = idConfigNivelRiesgo;
    }

    public MvConfigNivelRiesgo(Long idConfigNivelRiesgo, boolean status) {
        this.idConfigNivelRiesgo = idConfigNivelRiesgo;
        this.status = status;
    }

    public Long getIdConfigNivelRiesgo() {
        return idConfigNivelRiesgo;
    }

    public void setIdConfigNivelRiesgo(Long idConfigNivelRiesgo) {
        this.idConfigNivelRiesgo = idConfigNivelRiesgo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
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
        hash += (idConfigNivelRiesgo != null ? idConfigNivelRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvConfigNivelRiesgo)) {
            return false;
        }
        MvConfigNivelRiesgo other = (MvConfigNivelRiesgo) object;
        if ((this.idConfigNivelRiesgo == null && other.idConfigNivelRiesgo != null) || (this.idConfigNivelRiesgo != null && !this.idConfigNivelRiesgo.equals(other.idConfigNivelRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvConfigNivelRiesgo[ idConfigNivelRiesgo=" + idConfigNivelRiesgo + " ]";
    }
    
}
