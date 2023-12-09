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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_tipos_avisos")
@NamedQueries({
    @NamedQuery(name = "CatTiposAvisos.findAll", query = "SELECT c FROM CatTiposAvisos c"),
    @NamedQuery(name = "CatTiposAvisos.findByIdTipoAviso", query = "SELECT c FROM CatTiposAvisos c WHERE c.idTipoAviso = :idTipoAviso"),
    @NamedQuery(name = "CatTiposAvisos.findByNombre", query = "SELECT c FROM CatTiposAvisos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTiposAvisos.findByDescripcion", query = "SELECT c FROM CatTiposAvisos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTiposAvisos.findByStatus", query = "SELECT c FROM CatTiposAvisos c WHERE c.status = :status")})
public class CatTiposAvisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_aviso")
    private Long idTipoAviso;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "idTipoAviso")
    private List<MvConfigAvisos> mvConfigAvisosList;

    public CatTiposAvisos() {
    }

    public CatTiposAvisos(Long idTipoAviso) {
        this.idTipoAviso = idTipoAviso;
    }

    public CatTiposAvisos(Long idTipoAviso, String nombre, boolean status) {
        this.idTipoAviso = idTipoAviso;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdTipoAviso() {
        return idTipoAviso;
    }

    public void setIdTipoAviso(Long idTipoAviso) {
        this.idTipoAviso = idTipoAviso;
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

    public List<MvConfigAvisos> getMvConfigAvisosList() {
        return mvConfigAvisosList;
    }

    public void setMvConfigAvisosList(List<MvConfigAvisos> mvConfigAvisosList) {
        this.mvConfigAvisosList = mvConfigAvisosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAviso != null ? idTipoAviso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTiposAvisos)) {
            return false;
        }
        CatTiposAvisos other = (CatTiposAvisos) object;
        if ((this.idTipoAviso == null && other.idTipoAviso != null) || (this.idTipoAviso != null && !this.idTipoAviso.equals(other.idTipoAviso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTiposAvisos[ idTipoAviso=" + idTipoAviso + " ]";
    }
    
}
