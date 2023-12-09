/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_valores_tiempo_vida")
@NamedQueries({
    @NamedQuery(name = "CatValoresTiempoVida.findAll", query = "SELECT c FROM CatValoresTiempoVida c"),
    @NamedQuery(name = "CatValoresTiempoVida.findByIdValoresTiempoVida", query = "SELECT c FROM CatValoresTiempoVida c WHERE c.idValoresTiempoVida = :idValoresTiempoVida"),
    @NamedQuery(name = "CatValoresTiempoVida.findByNombre", query = "SELECT c FROM CatValoresTiempoVida c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatValoresTiempoVida.findByDescripcion", query = "SELECT c FROM CatValoresTiempoVida c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatValoresTiempoVida.findByDias", query = "SELECT c FROM CatValoresTiempoVida c WHERE c.dias = :dias"),
    @NamedQuery(name = "CatValoresTiempoVida.findByStatus", query = "SELECT c FROM CatValoresTiempoVida c WHERE c.status = :status")})
public class CatValoresTiempoVida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_valores_tiempo_vida")
    private Long idValoresTiempoVida;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "dias")
    private short dias;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValoresTiempoVida")
    private List<MvConfigTiempoVida> mvConfigTiempoVidaList;

    public CatValoresTiempoVida() {
    }

    public CatValoresTiempoVida(Long idValoresTiempoVida) {
        this.idValoresTiempoVida = idValoresTiempoVida;
    }

    public CatValoresTiempoVida(Long idValoresTiempoVida, String nombre, short dias, boolean status) {
        this.idValoresTiempoVida = idValoresTiempoVida;
        this.nombre = nombre;
        this.dias = dias;
        this.status = status;
    }

    public Long getIdValoresTiempoVida() {
        return idValoresTiempoVida;
    }

    public void setIdValoresTiempoVida(Long idValoresTiempoVida) {
        this.idValoresTiempoVida = idValoresTiempoVida;
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

    public short getDias() {
        return dias;
    }

    public void setDias(short dias) {
        this.dias = dias;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<MvConfigTiempoVida> getMvConfigTiempoVidaList() {
        return mvConfigTiempoVidaList;
    }

    public void setMvConfigTiempoVidaList(List<MvConfigTiempoVida> mvConfigTiempoVidaList) {
        this.mvConfigTiempoVidaList = mvConfigTiempoVidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValoresTiempoVida != null ? idValoresTiempoVida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatValoresTiempoVida)) {
            return false;
        }
        CatValoresTiempoVida other = (CatValoresTiempoVida) object;
        if ((this.idValoresTiempoVida == null && other.idValoresTiempoVida != null) || (this.idValoresTiempoVida != null && !this.idValoresTiempoVida.equals(other.idValoresTiempoVida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatValoresTiempoVida[ idValoresTiempoVida=" + idValoresTiempoVida + " ]";
    }
    
}
