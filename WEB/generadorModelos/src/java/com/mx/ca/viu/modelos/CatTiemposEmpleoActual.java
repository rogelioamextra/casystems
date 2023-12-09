/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "cat_tiempos_empleo_actual")
@NamedQueries({
    @NamedQuery(name = "CatTiemposEmpleoActual.findAll", query = "SELECT c FROM CatTiemposEmpleoActual c"),
    @NamedQuery(name = "CatTiemposEmpleoActual.findByIdTiempoEmpleoActual", query = "SELECT c FROM CatTiemposEmpleoActual c WHERE c.idTiempoEmpleoActual = :idTiempoEmpleoActual"),
    @NamedQuery(name = "CatTiemposEmpleoActual.findByNombre", query = "SELECT c FROM CatTiemposEmpleoActual c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatTiemposEmpleoActual.findByDescripcion", query = "SELECT c FROM CatTiemposEmpleoActual c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTiemposEmpleoActual.findByStatus", query = "SELECT c FROM CatTiemposEmpleoActual c WHERE c.status = :status"),
    @NamedQuery(name = "CatTiemposEmpleoActual.findByIdAmextra", query = "SELECT c FROM CatTiemposEmpleoActual c WHERE c.idAmextra = :idAmextra")})
public class CatTiemposEmpleoActual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiempo_empleo_actual")
    private Long idTiempoEmpleoActual;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private BigInteger idAmextra;
    @OneToMany(mappedBy = "idTiempoEmpleoActual")
    private List<DtDatosLaborales> dtDatosLaboralesList;

    public CatTiemposEmpleoActual() {
    }

    public CatTiemposEmpleoActual(Long idTiempoEmpleoActual) {
        this.idTiempoEmpleoActual = idTiempoEmpleoActual;
    }

    public Long getIdTiempoEmpleoActual() {
        return idTiempoEmpleoActual;
    }

    public void setIdTiempoEmpleoActual(Long idTiempoEmpleoActual) {
        this.idTiempoEmpleoActual = idTiempoEmpleoActual;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigInteger getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(BigInteger idAmextra) {
        this.idAmextra = idAmextra;
    }

    public List<DtDatosLaborales> getDtDatosLaboralesList() {
        return dtDatosLaboralesList;
    }

    public void setDtDatosLaboralesList(List<DtDatosLaborales> dtDatosLaboralesList) {
        this.dtDatosLaboralesList = dtDatosLaboralesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiempoEmpleoActual != null ? idTiempoEmpleoActual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTiemposEmpleoActual)) {
            return false;
        }
        CatTiemposEmpleoActual other = (CatTiemposEmpleoActual) object;
        if ((this.idTiempoEmpleoActual == null && other.idTiempoEmpleoActual != null) || (this.idTiempoEmpleoActual != null && !this.idTiempoEmpleoActual.equals(other.idTiempoEmpleoActual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatTiemposEmpleoActual[ idTiempoEmpleoActual=" + idTiempoEmpleoActual + " ]";
    }
    
}
