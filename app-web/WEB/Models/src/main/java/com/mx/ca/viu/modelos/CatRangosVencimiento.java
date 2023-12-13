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
@Table(name = "cat_rangos_vencimiento")
@NamedQueries({
    @NamedQuery(name = "CatRangosVencimiento.findAll", query = "SELECT c FROM CatRangosVencimiento c"),
    @NamedQuery(name = "CatRangosVencimiento.findByIdRangoVencimiento", query = "SELECT c FROM CatRangosVencimiento c WHERE c.idRangoVencimiento = :idRangoVencimiento"),
    @NamedQuery(name = "CatRangosVencimiento.findByNombre", query = "SELECT c FROM CatRangosVencimiento c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatRangosVencimiento.findByDescripcion", query = "SELECT c FROM CatRangosVencimiento c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatRangosVencimiento.findByCantidadDias", query = "SELECT c FROM CatRangosVencimiento c WHERE c.cantidadDias = :cantidadDias"),
    @NamedQuery(name = "CatRangosVencimiento.findByStatus", query = "SELECT c FROM CatRangosVencimiento c WHERE c.status = :status")})
public class CatRangosVencimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rango_vencimiento")
    private Long idRangoVencimiento;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad_dias")
    private short cantidadDias;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "idRangoVencimiento")
    private List<MvConfigRiesgo> mvConfigRiesgoList;

    public CatRangosVencimiento() {
    }

    public CatRangosVencimiento(Long idRangoVencimiento) {
        this.idRangoVencimiento = idRangoVencimiento;
    }

    public CatRangosVencimiento(Long idRangoVencimiento, String nombre, short cantidadDias, boolean status) {
        this.idRangoVencimiento = idRangoVencimiento;
        this.nombre = nombre;
        this.cantidadDias = cantidadDias;
        this.status = status;
    }

    public Long getIdRangoVencimiento() {
        return idRangoVencimiento;
    }

    public void setIdRangoVencimiento(Long idRangoVencimiento) {
        this.idRangoVencimiento = idRangoVencimiento;
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

    public short getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(short cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<MvConfigRiesgo> getMvConfigRiesgoList() {
        return mvConfigRiesgoList;
    }

    public void setMvConfigRiesgoList(List<MvConfigRiesgo> mvConfigRiesgoList) {
        this.mvConfigRiesgoList = mvConfigRiesgoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRangoVencimiento != null ? idRangoVencimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatRangosVencimiento)) {
            return false;
        }
        CatRangosVencimiento other = (CatRangosVencimiento) object;
        if ((this.idRangoVencimiento == null && other.idRangoVencimiento != null) || (this.idRangoVencimiento != null && !this.idRangoVencimiento.equals(other.idRangoVencimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatRangosVencimiento[ idRangoVencimiento=" + idRangoVencimiento + " ]";
    }
    
}
