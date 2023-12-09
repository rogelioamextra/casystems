/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_estatus")
@NamedQueries({
    @NamedQuery(name = "CatEstatus.findAll", query = "SELECT c FROM CatEstatus c"),
    @NamedQuery(name = "CatEstatus.findByIdEstatus", query = "SELECT c FROM CatEstatus c WHERE c.idEstatus = :idEstatus"),
    @NamedQuery(name = "CatEstatus.findByNombre", query = "SELECT c FROM CatEstatus c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatEstatus.findByDescripcion", query = "SELECT c FROM CatEstatus c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatEstatus.findByStatus", query = "SELECT c FROM CatEstatus c WHERE c.status = :status")})
public class CatEstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estatus")
    private Long idEstatus;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "idEstatusSolicitud")
    @JsonIgnore

    private List<DtResultadosDatosValidados> dtResultadosDatosValidadosList;
    @OneToMany(mappedBy = "idEstatus")
    @JsonIgnore

    private List<MvConfigMensaje> mvConfigMensajeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    @JsonIgnore
    private List<MvSolicitudesAmextra> mvSolicitudesAmextraList;

    public CatEstatus() {
    }

    public CatEstatus(Long idEstatus) {
        this.idEstatus = idEstatus;
    }

    public CatEstatus(Long idEstatus, String nombre, boolean status) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Long idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DtResultadosDatosValidados> getDtResultadosDatosValidadosList() {
        return dtResultadosDatosValidadosList;
    }

    public void setDtResultadosDatosValidadosList(List<DtResultadosDatosValidados> dtResultadosDatosValidadosList) {
        this.dtResultadosDatosValidadosList = dtResultadosDatosValidadosList;
    }

    public List<MvConfigMensaje> getMvConfigMensajeList() {
        return mvConfigMensajeList;
    }

    public void setMvConfigMensajeList(List<MvConfigMensaje> mvConfigMensajeList) {
        this.mvConfigMensajeList = mvConfigMensajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatus != null ? idEstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstatus)) {
            return false;
        }
        CatEstatus other = (CatEstatus) object;
        if ((this.idEstatus == null && other.idEstatus != null) || (this.idEstatus != null && !this.idEstatus.equals(other.idEstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatEstatus[ idEstatus=" + idEstatus + " ]";
    }

    public List<MvSolicitudesAmextra> getMvSolicitudesAmextraList() {
        return mvSolicitudesAmextraList;
    }

    public void setMvSolicitudesAmextraList(List<MvSolicitudesAmextra> mvSolicitudesAmextraList) {
        this.mvSolicitudesAmextraList = mvSolicitudesAmextraList;
    }

}
