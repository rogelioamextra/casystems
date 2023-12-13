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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "codigo_postal")
@NamedQueries({
    @NamedQuery(name = "CodigoPostal.findAll", query = "SELECT c FROM CodigoPostal c"),
    @NamedQuery(name = "CodigoPostal.findById", query = "SELECT c FROM CodigoPostal c WHERE c.id = :id"),
    @NamedQuery(name = "CodigoPostal.findByNombre", query = "SELECT c FROM CodigoPostal c WHERE c.nombre = :nombre")})
public class CodigoPostal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    @ManyToOne
    private Ciudad ciudadId;
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    @ManyToOne
    private Estado estadoId;
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    @ManyToOne
    private Municipio municipioId;
    @JsonIgnore
    @OneToMany(mappedBy = "codigoPostalAdministracionAsentamientoId")
    private List<Colonia> coloniaList;
    @JsonIgnore
    @OneToMany(mappedBy = "codigoPostalAdministracionAsentamientoOficinaId")
    private List<Colonia> coloniaList1;
    @JsonIgnore
    @OneToMany(mappedBy = "codigoPostalId")
    private List<Colonia> coloniaList2;
    @Transient
    private List<Colonia> coloniasLista;

    public CodigoPostal() {
    }

    public CodigoPostal(Long id) {
        this.id = id;
    }

    public CodigoPostal(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Ciudad ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
    }

    public Municipio getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Municipio municipioId) {
        this.municipioId = municipioId;
    }

    public List<Colonia> getColoniaList() {
        return coloniaList;
    }

    public void setColoniaList(List<Colonia> coloniaList) {
        this.coloniaList = coloniaList;
    }

    public List<Colonia> getColoniaList1() {
        return coloniaList1;
    }

    public void setColoniaList1(List<Colonia> coloniaList1) {
        this.coloniaList1 = coloniaList1;
    }

    public List<Colonia> getColoniaList2() {
        return coloniaList2;
    }

    public void setColoniaList2(List<Colonia> coloniaList2) {
        this.coloniaList2 = coloniaList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodigoPostal)) {
            return false;
        }
        CodigoPostal other = (CodigoPostal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CodigoPostal[ id=" + id + " ]";
    }

    public List<Colonia> getColoniasLista() {
        return coloniasLista;
    }

    public void setColoniasLista(List<Colonia> coloniasLista) {
        this.coloniasLista = coloniasLista;
    }


    
    
}
