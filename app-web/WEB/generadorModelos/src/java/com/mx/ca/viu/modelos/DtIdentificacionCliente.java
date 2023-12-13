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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "dt_identificacion_cliente")
@NamedQueries({
    @NamedQuery(name = "DtIdentificacionCliente.findAll", query = "SELECT d FROM DtIdentificacionCliente d"),
    @NamedQuery(name = "DtIdentificacionCliente.findByIdDatosIdentificacionCliente", query = "SELECT d FROM DtIdentificacionCliente d WHERE d.idDatosIdentificacionCliente = :idDatosIdentificacionCliente"),
    @NamedQuery(name = "DtIdentificacionCliente.findByIdCliente", query = "SELECT d FROM DtIdentificacionCliente d WHERE d.idCliente = :idCliente"),
    @NamedQuery(name = "DtIdentificacionCliente.findByNoIdentificacion", query = "SELECT d FROM DtIdentificacionCliente d WHERE d.noIdentificacion = :noIdentificacion"),
    @NamedQuery(name = "DtIdentificacionCliente.findByClaveElector", query = "SELECT d FROM DtIdentificacionCliente d WHERE d.claveElector = :claveElector"),
    @NamedQuery(name = "DtIdentificacionCliente.findByEmision", query = "SELECT d FROM DtIdentificacionCliente d WHERE d.emision = :emision"),
    @NamedQuery(name = "DtIdentificacionCliente.findByViencia", query = "SELECT d FROM DtIdentificacionCliente d WHERE d.viencia = :viencia")})
public class DtIdentificacionCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_identificacion_cliente")
    private Long idDatosIdentificacionCliente;
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private long idCliente;
    @Column(name = "no_identificacion")
    private String noIdentificacion;
    @Column(name = "clave_elector")
    private String claveElector;
    @Column(name = "emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emision;
    @Column(name = "viencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viencia;
    @Lob
    @Column(name = "foto_anverso")
    private byte[] fotoAnverso;
    @Lob
    @Column(name = "foto_reverso")
    private byte[] fotoReverso;
    @Lob
    @Column(name = "otros")
    private Object otros;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne
    private CatDocumentos idDocumento;

    public DtIdentificacionCliente() {
    }

    public DtIdentificacionCliente(Long idDatosIdentificacionCliente) {
        this.idDatosIdentificacionCliente = idDatosIdentificacionCliente;
    }

    public DtIdentificacionCliente(Long idDatosIdentificacionCliente, long idCliente) {
        this.idDatosIdentificacionCliente = idDatosIdentificacionCliente;
        this.idCliente = idCliente;
    }

    public Long getIdDatosIdentificacionCliente() {
        return idDatosIdentificacionCliente;
    }

    public void setIdDatosIdentificacionCliente(Long idDatosIdentificacionCliente) {
        this.idDatosIdentificacionCliente = idDatosIdentificacionCliente;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getClaveElector() {
        return claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public Date getEmision() {
        return emision;
    }

    public void setEmision(Date emision) {
        this.emision = emision;
    }

    public Date getViencia() {
        return viencia;
    }

    public void setViencia(Date viencia) {
        this.viencia = viencia;
    }

    public byte[] getFotoAnverso() {
        return fotoAnverso;
    }

    public void setFotoAnverso(byte[] fotoAnverso) {
        this.fotoAnverso = fotoAnverso;
    }

    public byte[] getFotoReverso() {
        return fotoReverso;
    }

    public void setFotoReverso(byte[] fotoReverso) {
        this.fotoReverso = fotoReverso;
    }

    public Object getOtros() {
        return otros;
    }

    public void setOtros(Object otros) {
        this.otros = otros;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosIdentificacionCliente != null ? idDatosIdentificacionCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtIdentificacionCliente)) {
            return false;
        }
        DtIdentificacionCliente other = (DtIdentificacionCliente) object;
        if ((this.idDatosIdentificacionCliente == null && other.idDatosIdentificacionCliente != null) || (this.idDatosIdentificacionCliente != null && !this.idDatosIdentificacionCliente.equals(other.idDatosIdentificacionCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtIdentificacionCliente[ idDatosIdentificacionCliente=" + idDatosIdentificacionCliente + " ]";
    }
    
}
