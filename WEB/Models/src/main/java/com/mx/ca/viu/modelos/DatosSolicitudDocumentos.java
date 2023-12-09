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
@Table(name = "datos_solicitud_documentos")
@NamedQueries({
    @NamedQuery(name = "DatosSolicitudDocumentos.findAll", query = "SELECT d FROM DatosSolicitudDocumentos d"),
    @NamedQuery(name = "DatosSolicitudDocumentos.findByIdDatosSolicitudDocumentos", query = "SELECT d FROM DatosSolicitudDocumentos d WHERE d.idDatosSolicitudDocumentos = :idDatosSolicitudDocumentos"),
    @NamedQuery(name = "DatosSolicitudDocumentos.findByOrden", query = "SELECT d FROM DatosSolicitudDocumentos d WHERE d.orden = :orden"),
    @NamedQuery(name = "DatosSolicitudDocumentos.findByStatus", query = "SELECT d FROM DatosSolicitudDocumentos d WHERE d.status = :status")})
public class DatosSolicitudDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_solicitud_documentos")
    private Long idDatosSolicitudDocumentos;
    @Basic(optional = false)
    @Column(name = "orden")
    private int orden;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne(optional = false)
    private CatDocumentos idDocumento;
    @JoinColumn(name = "id_datos_solicitud", referencedColumnName = "id_datos_solicitud")
    @ManyToOne(optional = false)
    private MvDatosSolicitud idDatosSolicitud;

    public DatosSolicitudDocumentos() {
    }

    public DatosSolicitudDocumentos(Long idDatosSolicitudDocumentos) {
        this.idDatosSolicitudDocumentos = idDatosSolicitudDocumentos;
    }

    public DatosSolicitudDocumentos(Long idDatosSolicitudDocumentos, int orden) {
        this.idDatosSolicitudDocumentos = idDatosSolicitudDocumentos;
        this.orden = orden;
    }

    public Long getIdDatosSolicitudDocumentos() {
        return idDatosSolicitudDocumentos;
    }

    public void setIdDatosSolicitudDocumentos(Long idDatosSolicitudDocumentos) {
        this.idDatosSolicitudDocumentos = idDatosSolicitudDocumentos;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CatCategoriasCampos getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatCategoriasCampos idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    public MvDatosSolicitud getIdDatosSolicitud() {
        return idDatosSolicitud;
    }

    public void setIdDatosSolicitud(MvDatosSolicitud idDatosSolicitud) {
        this.idDatosSolicitud = idDatosSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosSolicitudDocumentos != null ? idDatosSolicitudDocumentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosSolicitudDocumentos)) {
            return false;
        }
        DatosSolicitudDocumentos other = (DatosSolicitudDocumentos) object;
        if ((this.idDatosSolicitudDocumentos == null && other.idDatosSolicitudDocumentos != null) || (this.idDatosSolicitudDocumentos != null && !this.idDatosSolicitudDocumentos.equals(other.idDatosSolicitudDocumentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosSolicitudDocumentos[ idDatosSolicitudDocumentos=" + idDatosSolicitudDocumentos + " ]";
    }
    
}
