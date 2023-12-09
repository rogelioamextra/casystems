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
@Table(name = "datos_solicitud_categorias")
@NamedQueries({
    @NamedQuery(name = "DatosSolicitudCategorias.findAll", query = "SELECT d FROM DatosSolicitudCategorias d"),
    @NamedQuery(name = "DatosSolicitudCategorias.findByIdDatosSolicitudCategorias", query = "SELECT d FROM DatosSolicitudCategorias d WHERE d.idDatosSolicitudCategorias = :idDatosSolicitudCategorias"),
    @NamedQuery(name = "DatosSolicitudCategorias.findByOrden", query = "SELECT d FROM DatosSolicitudCategorias d WHERE d.orden = :orden"),
    @NamedQuery(name = "DatosSolicitudCategorias.findByStatus", query = "SELECT d FROM DatosSolicitudCategorias d WHERE d.status = :status")})
public class DatosSolicitudCategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_solicitud_categorias")
    private Long idDatosSolicitudCategorias;
    @Basic(optional = false)
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne(optional = false)
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_datos_solicitud", referencedColumnName = "id_datos_solicitud")
    @ManyToOne(optional = false)
    private MvDatosSolicitud idDatosSolicitud;

    public DatosSolicitudCategorias() {
    }

    public DatosSolicitudCategorias(Long idDatosSolicitudCategorias) {
        this.idDatosSolicitudCategorias = idDatosSolicitudCategorias;
    }

    public DatosSolicitudCategorias(Long idDatosSolicitudCategorias, int orden, boolean status) {
        this.idDatosSolicitudCategorias = idDatosSolicitudCategorias;
        this.orden = orden;
        this.status = status;
    }

    public Long getIdDatosSolicitudCategorias() {
        return idDatosSolicitudCategorias;
    }

    public void setIdDatosSolicitudCategorias(Long idDatosSolicitudCategorias) {
        this.idDatosSolicitudCategorias = idDatosSolicitudCategorias;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        hash += (idDatosSolicitudCategorias != null ? idDatosSolicitudCategorias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosSolicitudCategorias)) {
            return false;
        }
        DatosSolicitudCategorias other = (DatosSolicitudCategorias) object;
        if ((this.idDatosSolicitudCategorias == null && other.idDatosSolicitudCategorias != null) || (this.idDatosSolicitudCategorias != null && !this.idDatosSolicitudCategorias.equals(other.idDatosSolicitudCategorias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosSolicitudCategorias[ idDatosSolicitudCategorias=" + idDatosSolicitudCategorias + " ]";
    }
    
}
