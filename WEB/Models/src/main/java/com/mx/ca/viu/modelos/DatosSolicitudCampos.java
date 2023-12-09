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
@Table(name = "datos_solicitud_campos")
@NamedQueries({
    @NamedQuery(name = "DatosSolicitudCampos.findAll", query = "SELECT d FROM DatosSolicitudCampos d"),
    @NamedQuery(name = "DatosSolicitudCampos.findByIdDatosSolicitudCampos", query = "SELECT d FROM DatosSolicitudCampos d WHERE d.idDatosSolicitudCampos = :idDatosSolicitudCampos"),
    @NamedQuery(name = "DatosSolicitudCampos.findByStatus", query = "SELECT d FROM DatosSolicitudCampos d WHERE d.status = :status"),
    @NamedQuery(name = "DatosSolicitudCampos.findByOrden", query = "SELECT d FROM DatosSolicitudCampos d WHERE d.orden = :orden")})
public class DatosSolicitudCampos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_solicitud_campos")
    private Integer idDatosSolicitudCampos;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "orden")
    private int orden;
    @JoinColumn(name = "id_campo", referencedColumnName = "id_campos")
    @ManyToOne(optional = false)
    private CatCampos idCampo;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_datos_solicitud", referencedColumnName = "id_datos_solicitud")
    @ManyToOne(optional = false)
    private MvDatosSolicitud idDatosSolicitud;

    public DatosSolicitudCampos() {
    }

    public DatosSolicitudCampos(Integer idDatosSolicitudCampos) {
        this.idDatosSolicitudCampos = idDatosSolicitudCampos;
    }

    public DatosSolicitudCampos(Integer idDatosSolicitudCampos, boolean status, int orden) {
        this.idDatosSolicitudCampos = idDatosSolicitudCampos;
        this.status = status;
        this.orden = orden;
    }

    public Integer getIdDatosSolicitudCampos() {
        return idDatosSolicitudCampos;
    }

    public void setIdDatosSolicitudCampos(Integer idDatosSolicitudCampos) {
        this.idDatosSolicitudCampos = idDatosSolicitudCampos;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public CatCampos getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(CatCampos idCampo) {
        this.idCampo = idCampo;
    }

    public CatCategoriasCampos getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatCategoriasCampos idCategoria) {
        this.idCategoria = idCategoria;
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
        hash += (idDatosSolicitudCampos != null ? idDatosSolicitudCampos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosSolicitudCampos)) {
            return false;
        }
        DatosSolicitudCampos other = (DatosSolicitudCampos) object;
        if ((this.idDatosSolicitudCampos == null && other.idDatosSolicitudCampos != null) || (this.idDatosSolicitudCampos != null && !this.idDatosSolicitudCampos.equals(other.idDatosSolicitudCampos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosSolicitudCampos[ idDatosSolicitudCampos=" + idDatosSolicitudCampos + " ]";
    }
    
}
