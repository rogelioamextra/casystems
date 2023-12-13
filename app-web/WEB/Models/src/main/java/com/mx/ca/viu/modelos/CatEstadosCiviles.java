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
@Table(name = "cat_estados_civiles")
@NamedQueries({
    @NamedQuery(name = "CatEstadosCiviles.findAll", query = "SELECT c FROM CatEstadosCiviles c"),
    @NamedQuery(name = "CatEstadosCiviles.findByIdEstadoCivil", query = "SELECT c FROM CatEstadosCiviles c WHERE c.idEstadoCivil = :idEstadoCivil"),
    @NamedQuery(name = "CatEstadosCiviles.findByNombre", query = "SELECT c FROM CatEstadosCiviles c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatEstadosCiviles.findByDescripcion", query = "SELECT c FROM CatEstadosCiviles c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatEstadosCiviles.findByStatus", query = "SELECT c FROM CatEstadosCiviles c WHERE c.status = :status"),
    @NamedQuery(name = "CatEstadosCiviles.findByIdAmextra", query = "SELECT c FROM CatEstadosCiviles c WHERE c.idAmextra = :idAmextra")})
public class CatEstadosCiviles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_civil")
    private Long idEstadoCivil;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "id_amextra")
    private String idAmextra;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCivil")
    private List<CatPersonas> catPersonasList;

    public CatEstadosCiviles() {
    }

    public CatEstadosCiviles(Long idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Long getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Long idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
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

    public String getIdAmextra() {
        return idAmextra;
    }

    public void setIdAmextra(String idAmextra) {
        this.idAmextra = idAmextra;
    }

    public List<CatPersonas> getCatPersonasList() {
        return catPersonasList;
    }

    public void setCatPersonasList(List<CatPersonas> catPersonasList) {
        this.catPersonasList = catPersonasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCivil != null ? idEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstadosCiviles)) {
            return false;
        }
        CatEstadosCiviles other = (CatEstadosCiviles) object;
        if ((this.idEstadoCivil == null && other.idEstadoCivil != null) || (this.idEstadoCivil != null && !this.idEstadoCivil.equals(other.idEstadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatEstadosCiviles[ idEstadoCivil=" + idEstadoCivil + " ]";
    }
    
}
