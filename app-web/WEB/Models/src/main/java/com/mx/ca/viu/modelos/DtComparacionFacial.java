/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_comparacion_facial")

public class DtComparacionFacial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comparacion_facial")
    private Long idComparacionFacial;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "similitud")
    private String similitud;
    @Column(name = "codigo_validacion")
    private String codigoValidacion;
    @Column(name = "curp")
    private String curp;
    
    @Column(name = "fecha_comparacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComparacion;

    public DtComparacionFacial() {
    }

    public DtComparacionFacial(Long idComparacionFacial) {
        this.idComparacionFacial = idComparacionFacial;
    }

    public Long getIdComparacionFacial() {
        return idComparacionFacial;
    }

    public void setIdComparacionFacial(Long idComparacionFacial) {
        this.idComparacionFacial = idComparacionFacial;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getSimilitud() {
        return similitud;
    }

    public void setSimilitud(String similitud) {
        this.similitud = similitud;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComparacionFacial != null ? idComparacionFacial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtComparacionFacial)) {
            return false;
        }
        DtComparacionFacial other = (DtComparacionFacial) object;
        if ((this.idComparacionFacial == null && other.idComparacionFacial != null) || (this.idComparacionFacial != null && !this.idComparacionFacial.equals(other.idComparacionFacial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtComparacionFacial[ idComparacionFacial=" + idComparacionFacial + " ]";
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Date getFechaComparacion() {
        return fechaComparacion;
    }

    public void setFechaComparacion(Date fechaComparacion) {
        this.fechaComparacion = fechaComparacion;
    }
    
    
    
}
