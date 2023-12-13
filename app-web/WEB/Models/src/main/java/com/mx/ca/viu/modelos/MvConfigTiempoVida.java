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
@Table(name = "mv_config_tiempo_vida")
@NamedQueries({
    @NamedQuery(name = "MvConfigTiempoVida.findAll", query = "SELECT m FROM MvConfigTiempoVida m"),
    @NamedQuery(name = "MvConfigTiempoVida.findByIdConfigTiempoVida", query = "SELECT m FROM MvConfigTiempoVida m WHERE m.idConfigTiempoVida = :idConfigTiempoVida"),
    @NamedQuery(name = "MvConfigTiempoVida.findByStatus", query = "SELECT m FROM MvConfigTiempoVida m WHERE m.status = :status")})
public class MvConfigTiempoVida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config_tiempo_vida")
    private Long idConfigTiempoVida;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "id_valores_tiempo_vida", referencedColumnName = "id_valores_tiempo_vida")
    @ManyToOne(optional = false)
    private CatValoresTiempoVida idValoresTiempoVida;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne(optional = false)
    private MvConfigSolicitudes idConfigSolicitud;

    public MvConfigTiempoVida() {
    }

    public MvConfigTiempoVida(Long idConfigTiempoVida) {
        this.idConfigTiempoVida = idConfigTiempoVida;
    }

    public MvConfigTiempoVida(Long idConfigTiempoVida, boolean status) {
        this.idConfigTiempoVida = idConfigTiempoVida;
        this.status = status;
    }

    public Long getIdConfigTiempoVida() {
        return idConfigTiempoVida;
    }

    public void setIdConfigTiempoVida(Long idConfigTiempoVida) {
        this.idConfigTiempoVida = idConfigTiempoVida;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CatValoresTiempoVida getIdValoresTiempoVida() {
        return idValoresTiempoVida;
    }

    public void setIdValoresTiempoVida(CatValoresTiempoVida idValoresTiempoVida) {
        this.idValoresTiempoVida = idValoresTiempoVida;
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
        hash += (idConfigTiempoVida != null ? idConfigTiempoVida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvConfigTiempoVida)) {
            return false;
        }
        MvConfigTiempoVida other = (MvConfigTiempoVida) object;
        if ((this.idConfigTiempoVida == null && other.idConfigTiempoVida != null) || (this.idConfigTiempoVida != null && !this.idConfigTiempoVida.equals(other.idConfigTiempoVida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvConfigTiempoVida[ idConfigTiempoVida=" + idConfigTiempoVida + " ]";
    }
    
}
