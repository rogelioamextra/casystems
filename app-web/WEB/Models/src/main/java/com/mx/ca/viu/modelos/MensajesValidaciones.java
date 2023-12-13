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
@Table(name = "mensajes_validaciones")
@NamedQueries({
    @NamedQuery(name = "MensajesValidaciones.findAll", query = "SELECT m FROM MensajesValidaciones m"),
    @NamedQuery(name = "MensajesValidaciones.findByIdMensajesValidaciones", query = "SELECT m FROM MensajesValidaciones m WHERE m.idMensajesValidaciones = :idMensajesValidaciones"),
    @NamedQuery(name = "MensajesValidaciones.findByAprobado", query = "SELECT m FROM MensajesValidaciones m WHERE m.aprobado = :aprobado"),
    @NamedQuery(name = "MensajesValidaciones.findByIndistinto", query = "SELECT m FROM MensajesValidaciones m WHERE m.indistinto = :indistinto")})
public class MensajesValidaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mensajes_validaciones")
    private Long idMensajesValidaciones;
    @Column(name = "aprobado")
    private Boolean aprobado;
    @Column(name = "indistinto")
    private Boolean indistinto;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne
    private CatDocumentos idDocumento;
    @JoinColumn(name = "id_servicio_validacion", referencedColumnName = "id_servicios_validaciones")
    @ManyToOne
    private CatServiciosValidacionesExternos idServicioValidacion;
    @JoinColumn(name = "id_mv_config_mensaje", referencedColumnName = "id_mv_config_mensaje")
    @ManyToOne
    private MvConfigMensaje idMvConfigMensaje;

    public MensajesValidaciones() {
    }

    public MensajesValidaciones(Long idMensajesValidaciones) {
        this.idMensajesValidaciones = idMensajesValidaciones;
    }

    public Long getIdMensajesValidaciones() {
        return idMensajesValidaciones;
    }

    public void setIdMensajesValidaciones(Long idMensajesValidaciones) {
        this.idMensajesValidaciones = idMensajesValidaciones;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Boolean getIndistinto() {
        return indistinto;
    }

    public void setIndistinto(Boolean indistinto) {
        this.indistinto = indistinto;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    public CatServiciosValidacionesExternos getIdServicioValidacion() {
        return idServicioValidacion;
    }

    public void setIdServicioValidacion(CatServiciosValidacionesExternos idServicioValidacion) {
        this.idServicioValidacion = idServicioValidacion;
    }

    public MvConfigMensaje getIdMvConfigMensaje() {
        return idMvConfigMensaje;
    }

    public void setIdMvConfigMensaje(MvConfigMensaje idMvConfigMensaje) {
        this.idMvConfigMensaje = idMvConfigMensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensajesValidaciones != null ? idMensajesValidaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajesValidaciones)) {
            return false;
        }
        MensajesValidaciones other = (MensajesValidaciones) object;
        if ((this.idMensajesValidaciones == null && other.idMensajesValidaciones != null) || (this.idMensajesValidaciones != null && !this.idMensajesValidaciones.equals(other.idMensajesValidaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MensajesValidaciones[ idMensajesValidaciones=" + idMensajesValidaciones + " ]";
    }
    
}
