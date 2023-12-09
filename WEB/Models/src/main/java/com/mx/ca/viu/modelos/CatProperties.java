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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_properties")

public class CatProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_properties")
    private Integer idProperties;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "valor")
    private String valor;
    @Column(name = "constante")
    private Long constante;
    @Column(name = "status")
    private Boolean status;

    public CatProperties() {
    }

    public CatProperties(Integer idProperties) {
        this.idProperties = idProperties;
    }

    public Integer getIdProperties() {
        return idProperties;
    }

    public void setIdProperties(Integer idProperties) {
        this.idProperties = idProperties;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getConstante() {
        return constante;
    }

    public void setConstante(Long constante) {
        this.constante = constante;
    }



    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProperties != null ? idProperties.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatProperties)) {
            return false;
        }
        CatProperties other = (CatProperties) object;
        if ((this.idProperties == null && other.idProperties != null) || (this.idProperties != null && !this.idProperties.equals(other.idProperties))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatProperties[ idProperties=" + idProperties + " ]";
    }
    
}
