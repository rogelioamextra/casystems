/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "bt_validacion_sms")
@NamedQueries({
    @NamedQuery(name = "BtValidacionSms.findAll", query = "SELECT b FROM BtValidacionSms b"),
    @NamedQuery(name = "BtValidacionSms.findByIdBitacoraSms", query = "SELECT b FROM BtValidacionSms b WHERE b.idBitacoraSms = :idBitacoraSms"),
    @NamedQuery(name = "BtValidacionSms.findByFechaEnvio", query = "SELECT b FROM BtValidacionSms b WHERE b.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "BtValidacionSms.findByNumeroEnvio", query = "SELECT b FROM BtValidacionSms b WHERE b.numeroEnvio = :numeroEnvio"),
    @NamedQuery(name = "BtValidacionSms.findByCodigoVerificacion", query = "SELECT b FROM BtValidacionSms b WHERE b.codigoVerificacion = :codigoVerificacion"),
    @NamedQuery(name = "BtValidacionSms.findByStatus", query = "SELECT b FROM BtValidacionSms b WHERE b.status = :status")})
public class BtValidacionSms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora_sms")
    private Long idBitacoraSms;
    @Basic(optional = false)
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Column(name = "numero_envio")
    private String numeroEnvio;
    @Column(name = "codigo_verificacion")
    private String codigoVerificacion;
    @Column(name = "status")
    private Boolean status;

    public BtValidacionSms() {
    }

    public BtValidacionSms(Long idBitacoraSms) {
        this.idBitacoraSms = idBitacoraSms;
    }

    public BtValidacionSms(Long idBitacoraSms, Date fechaEnvio) {
        this.idBitacoraSms = idBitacoraSms;
        this.fechaEnvio = fechaEnvio;
    }

    public Long getIdBitacoraSms() {
        return idBitacoraSms;
    }

    public void setIdBitacoraSms(Long idBitacoraSms) {
        this.idBitacoraSms = idBitacoraSms;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getNumeroEnvio() {
        return numeroEnvio;
    }

    public void setNumeroEnvio(String numeroEnvio) {
        this.numeroEnvio = numeroEnvio;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacoraSms != null ? idBitacoraSms.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BtValidacionSms)) {
            return false;
        }
        BtValidacionSms other = (BtValidacionSms) object;
        if ((this.idBitacoraSms == null && other.idBitacoraSms != null) || (this.idBitacoraSms != null && !this.idBitacoraSms.equals(other.idBitacoraSms))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.BtValidacionSms[ idBitacoraSms=" + idBitacoraSms + " ]";
    }
    
}
