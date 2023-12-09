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
@Table(name = "mv_config_mensaje")
@NamedQueries({
    @NamedQuery(name = "MvConfigMensaje.findAll", query = "SELECT m FROM MvConfigMensaje m"),
    @NamedQuery(name = "MvConfigMensaje.findByIdMvConfigMensaje", query = "SELECT m FROM MvConfigMensaje m WHERE m.idMvConfigMensaje = :idMvConfigMensaje"),
    @NamedQuery(name = "MvConfigMensaje.findByMensaje", query = "SELECT m FROM MvConfigMensaje m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "MvConfigMensaje.findByStatus", query = "SELECT m FROM MvConfigMensaje m WHERE m.status = :status")})
public class MvConfigMensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mv_config_mensaje")
    private Long idMvConfigMensaje;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "idMvConfigMensaje")
    private List<MensajesValidaciones> mensajesValidacionesList;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne
    private CatEstatus idEstatus;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne
    private MvConfigSolicitudes idConfigSolicitud;

    public MvConfigMensaje() {
    }

    public MvConfigMensaje(Long idMvConfigMensaje) {
        this.idMvConfigMensaje = idMvConfigMensaje;
    }

    public Long getIdMvConfigMensaje() {
        return idMvConfigMensaje;
    }

    public void setIdMvConfigMensaje(Long idMvConfigMensaje) {
        this.idMvConfigMensaje = idMvConfigMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MensajesValidaciones> getMensajesValidacionesList() {
        return mensajesValidacionesList;
    }

    public void setMensajesValidacionesList(List<MensajesValidaciones> mensajesValidacionesList) {
        this.mensajesValidacionesList = mensajesValidacionesList;
    }

    public CatEstatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(CatEstatus idEstatus) {
        this.idEstatus = idEstatus;
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
        hash += (idMvConfigMensaje != null ? idMvConfigMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvConfigMensaje)) {
            return false;
        }
        MvConfigMensaje other = (MvConfigMensaje) object;
        if ((this.idMvConfigMensaje == null && other.idMvConfigMensaje != null) || (this.idMvConfigMensaje != null && !this.idMvConfigMensaje.equals(other.idMvConfigMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvConfigMensaje[ idMvConfigMensaje=" + idMvConfigMensaje + " ]";
    }
    
}
