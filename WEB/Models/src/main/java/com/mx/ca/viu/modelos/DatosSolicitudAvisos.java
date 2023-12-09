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
@Table(name = "datos_solicitud_avisos")
@NamedQueries({
    @NamedQuery(name = "DatosSolicitudAvisos.findAll", query = "SELECT d FROM DatosSolicitudAvisos d"),
    @NamedQuery(name = "DatosSolicitudAvisos.findByIdDatosSolicitudAvisos", query = "SELECT d FROM DatosSolicitudAvisos d WHERE d.idDatosSolicitudAvisos = :idDatosSolicitudAvisos"),
    @NamedQuery(name = "DatosSolicitudAvisos.findByStatus", query = "SELECT d FROM DatosSolicitudAvisos d WHERE d.status = :status"),
    @NamedQuery(name = "DatosSolicitudAvisos.findByOrden", query = "SELECT d FROM DatosSolicitudAvisos d WHERE d.orden = :orden")})
public class DatosSolicitudAvisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_solicitud_avisos")
    private Integer idDatosSolicitudAvisos;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "orden")
    private int orden;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_aviso", referencedColumnName = "id_config_avisos")
    @ManyToOne(optional = false)
    private MvConfigAvisos idAviso;
    @JoinColumn(name = "id_datos_solicitud", referencedColumnName = "id_datos_solicitud")
    @ManyToOne(optional = false)
    private MvDatosSolicitud idDatosSolicitud;

    public DatosSolicitudAvisos() {
    }

    public DatosSolicitudAvisos(Integer idDatosSolicitudAvisos) {
        this.idDatosSolicitudAvisos = idDatosSolicitudAvisos;
    }

    public DatosSolicitudAvisos(Integer idDatosSolicitudAvisos, boolean status, int orden) {
        this.idDatosSolicitudAvisos = idDatosSolicitudAvisos;
        this.status = status;
        this.orden = orden;
    }

    public Integer getIdDatosSolicitudAvisos() {
        return idDatosSolicitudAvisos;
    }

    public void setIdDatosSolicitudAvisos(Integer idDatosSolicitudAvisos) {
        this.idDatosSolicitudAvisos = idDatosSolicitudAvisos;
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

    public CatCategoriasCampos getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatCategoriasCampos idCategoria) {
        this.idCategoria = idCategoria;
    }

    public MvConfigAvisos getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(MvConfigAvisos idAviso) {
        this.idAviso = idAviso;
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
        hash += (idDatosSolicitudAvisos != null ? idDatosSolicitudAvisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosSolicitudAvisos)) {
            return false;
        }
        DatosSolicitudAvisos other = (DatosSolicitudAvisos) object;
        if ((this.idDatosSolicitudAvisos == null && other.idDatosSolicitudAvisos != null) || (this.idDatosSolicitudAvisos != null && !this.idDatosSolicitudAvisos.equals(other.idDatosSolicitudAvisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosSolicitudAvisos[ idDatosSolicitudAvisos=" + idDatosSolicitudAvisos + " ]";
    }
    
}
