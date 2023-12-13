/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cat_frecuencia_pago")

public class CatFrecuenciaPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_frecuencia_pago")
    private Long idFrecuenciaPago;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "clave")
    private String clave;
    @Column(name = "id_amextra")
    private Short idAmextra;
    @Column(name = "total_dias")
    private Integer totalDias;
    @OneToMany(mappedBy = "idFrecuencia")
    @JsonIgnore
    private List<MvSolicitudesAmextra> mvSolicitudesAmextraList;

    public CatFrecuenciaPago() {
    }

    public Long getIdFrecuenciaPago() {
        return idFrecuenciaPago;
    }

    public void setIdFrecuenciaPago(Long idFrecuenciaPago) {
        this.idFrecuenciaPago = idFrecuenciaPago;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Short getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(Short idAmextra) {
        this.idAmextra = idAmextra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFrecuenciaPago != null ? idFrecuenciaPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatFrecuenciaPago)) {
            return false;
        }
        CatFrecuenciaPago other = (CatFrecuenciaPago) object;
        if ((this.idFrecuenciaPago == null && other.idFrecuenciaPago != null) || (this.idFrecuenciaPago != null && !this.idFrecuenciaPago.equals(other.idFrecuenciaPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatFrecuenciaPago[ idFrecuenciaPago=" + idFrecuenciaPago + " ]";
    }

    public List<MvSolicitudesAmextra> getMvSolicitudesAmextraList() {
        return mvSolicitudesAmextraList;
    }

    public void setMvSolicitudesAmextraList(List<MvSolicitudesAmextra> mvSolicitudesAmextraList) {
        this.mvSolicitudesAmextraList = mvSolicitudesAmextraList;
    }

    public Integer getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(Integer totalDias) {
        this.totalDias = totalDias;
    }
    
    

}
