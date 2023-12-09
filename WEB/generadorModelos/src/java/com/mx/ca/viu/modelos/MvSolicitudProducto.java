/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_solicitud_producto")
@NamedQueries({
    @NamedQuery(name = "MvSolicitudProducto.findAll", query = "SELECT m FROM MvSolicitudProducto m"),
    @NamedQuery(name = "MvSolicitudProducto.findByIdSolicitudProducto", query = "SELECT m FROM MvSolicitudProducto m WHERE m.idSolicitudProducto = :idSolicitudProducto"),
    @NamedQuery(name = "MvSolicitudProducto.findByStatus", query = "SELECT m FROM MvSolicitudProducto m WHERE m.status = :status"),
    @NamedQuery(name = "MvSolicitudProducto.findByIdSolicitud", query = "SELECT m FROM MvSolicitudProducto m WHERE m.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "MvSolicitudProducto.findByFecha", query = "SELECT m FROM MvSolicitudProducto m WHERE m.fecha = :fecha")})
public class MvSolicitudProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud_producto")
    private Long idSolicitudProducto;
    @Column(name = "status")
    private Boolean status;
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private long idSolicitud;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(mappedBy = "idSolicitudProducto")
    private List<DtResultadosDatosValidados> dtResultadosDatosValidadosList;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne
    private MvConfigSolicitudes idConfigSolicitud;

    public MvSolicitudProducto() {
    }

    public MvSolicitudProducto(Long idSolicitudProducto) {
        this.idSolicitudProducto = idSolicitudProducto;
    }

    public MvSolicitudProducto(Long idSolicitudProducto, long idSolicitud) {
        this.idSolicitudProducto = idSolicitudProducto;
        this.idSolicitud = idSolicitud;
    }

    public Long getIdSolicitudProducto() {
        return idSolicitudProducto;
    }

    public void setIdSolicitudProducto(Long idSolicitudProducto) {
        this.idSolicitudProducto = idSolicitudProducto;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DtResultadosDatosValidados> getDtResultadosDatosValidadosList() {
        return dtResultadosDatosValidadosList;
    }

    public void setDtResultadosDatosValidadosList(List<DtResultadosDatosValidados> dtResultadosDatosValidadosList) {
        this.dtResultadosDatosValidadosList = dtResultadosDatosValidadosList;
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
        hash += (idSolicitudProducto != null ? idSolicitudProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvSolicitudProducto)) {
            return false;
        }
        MvSolicitudProducto other = (MvSolicitudProducto) object;
        if ((this.idSolicitudProducto == null && other.idSolicitudProducto != null) || (this.idSolicitudProducto != null && !this.idSolicitudProducto.equals(other.idSolicitudProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvSolicitudProducto[ idSolicitudProducto=" + idSolicitudProducto + " ]";
    }
    
}
