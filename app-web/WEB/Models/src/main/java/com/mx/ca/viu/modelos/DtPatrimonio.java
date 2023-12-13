/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "dt_patrimonio")

public class DtPatrimonio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_patrimonio")
    private Long idPatrimonio;
    @Column(name = "precio")
    private String precio;
    @Column(name = "nombre_imagen")
    private String nombreImagen;
    @JoinColumn(name = "id_tipo_patrimonio", referencedColumnName = "id_patrimonio")
    @ManyToOne
    private CatPatrimonios idTipoPatrimonio;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne@JsonIgnore
    
    private MvSolicitudesAmextra idSolicitud;

    public DtPatrimonio() {
    }

    public DtPatrimonio(Long idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public Long getIdPatrimonio() {
        return idPatrimonio;
    }

    public void setIdPatrimonio(Long idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public CatPatrimonios getIdTipoPatrimonio() {
        return idTipoPatrimonio;
    }

    public void setIdTipoPatrimonio(CatPatrimonios idTipoPatrimonio) {
        this.idTipoPatrimonio = idTipoPatrimonio;
    }

    public MvSolicitudesAmextra getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(MvSolicitudesAmextra idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatrimonio != null ? idPatrimonio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtPatrimonio)) {
            return false;
        }
        DtPatrimonio other = (DtPatrimonio) object;
        if ((this.idPatrimonio == null && other.idPatrimonio != null) || (this.idPatrimonio != null && !this.idPatrimonio.equals(other.idPatrimonio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtPatrimonio[ idPatrimonio=" + idPatrimonio + " ]";
    }
    
}
