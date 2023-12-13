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
@Table(name = "validaciones_valores")
@NamedQueries({
    @NamedQuery(name = "ValidacionesValores.findAll", query = "SELECT v FROM ValidacionesValores v"),
    @NamedQuery(name = "ValidacionesValores.findByIdValoresValidaciones", query = "SELECT v FROM ValidacionesValores v WHERE v.idValoresValidaciones = :idValoresValidaciones")})
public class ValidacionesValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_valores_validaciones")
    private Long idValoresValidaciones;
    @JoinColumn(name = "id_validacion", referencedColumnName = "id_validaciones")
    @ManyToOne(optional = false)
    private CatValidaciones idValidacion;
    @JoinColumn(name = "id_valor", referencedColumnName = "id_valor")
    @ManyToOne(optional = false)
    private CatValores idValor;

    public ValidacionesValores() {
    }

    public ValidacionesValores(Long idValoresValidaciones) {
        this.idValoresValidaciones = idValoresValidaciones;
    }

    public Long getIdValoresValidaciones() {
        return idValoresValidaciones;
    }

    public void setIdValoresValidaciones(Long idValoresValidaciones) {
        this.idValoresValidaciones = idValoresValidaciones;
    }

    public CatValidaciones getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(CatValidaciones idValidacion) {
        this.idValidacion = idValidacion;
    }

    public CatValores getIdValor() {
        return idValor;
    }

    public void setIdValor(CatValores idValor) {
        this.idValor = idValor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValoresValidaciones != null ? idValoresValidaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidacionesValores)) {
            return false;
        }
        ValidacionesValores other = (ValidacionesValores) object;
        if ((this.idValoresValidaciones == null && other.idValoresValidaciones != null) || (this.idValoresValidaciones != null && !this.idValoresValidaciones.equals(other.idValoresValidaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.ValidacionesValores[ idValoresValidaciones=" + idValoresValidaciones + " ]";
    }
    
}
