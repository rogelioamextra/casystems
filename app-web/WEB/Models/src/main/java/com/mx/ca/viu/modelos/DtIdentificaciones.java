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
@Table(name = "dt_identificaciones")
@NamedQueries({
    @NamedQuery(name = "DtIdentificaciones.findAll", query = "SELECT d FROM DtIdentificaciones d"),
    @NamedQuery(name = "DtIdentificaciones.findByIdDtIdentificacion", query = "SELECT d FROM DtIdentificaciones d WHERE d.idDtIdentificacion = :idDtIdentificacion"),
    @NamedQuery(name = "DtIdentificaciones.findByNoIdentificacion", query = "SELECT d FROM DtIdentificaciones d WHERE d.noIdentificacion = :noIdentificacion"),
    @NamedQuery(name = "DtIdentificaciones.findByClaveElector", query = "SELECT d FROM DtIdentificaciones d WHERE d.claveElector = :claveElector"),
    @NamedQuery(name = "DtIdentificaciones.findByNombreImagen", query = "SELECT d FROM DtIdentificaciones d WHERE d.nombreImagen = :nombreImagen")})
public class DtIdentificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dt_identificacion")
    private Long idDtIdentificacion;
    @Column(name = "no_identificacion")
    private String noIdentificacion;
    @Column(name = "clave_elector")
    private String claveElector;
    @Column(name = "nombre_imagen")
    @JsonIgnore
    private String nombreImagen;
    @Transient
    private String imagen;
    @Transient
    private String anverso;
    @Transient
    private String reverso;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "emision")
    private String emision;
    @OneToMany(mappedBy = "idDtIdentificacion")
    @JsonIgnore
    private List<CatClientes> catClientesList;
    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_identificaciones")
    @ManyToOne(optional = false)
    private CatIdentificaciones idTipoIdentificacion;

    public DtIdentificaciones() {
    }

    public DtIdentificaciones(Long idDtIdentificacion) {
        this.idDtIdentificacion = idDtIdentificacion;
    }

    public Long getIdDtIdentificacion() {
        return idDtIdentificacion;
    }

    public void setIdDtIdentificacion(Long idDtIdentificacion) {
        this.idDtIdentificacion = idDtIdentificacion;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getClaveElector() {
        return claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public List<CatClientes> getCatClientesList() {
        return catClientesList;
    }

    public void setCatClientesList(List<CatClientes> catClientesList) {
        this.catClientesList = catClientesList;
    }

    public CatIdentificaciones getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(CatIdentificaciones idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDtIdentificacion != null ? idDtIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtIdentificaciones)) {
            return false;
        }
        DtIdentificaciones other = (DtIdentificaciones) object;
        if ((this.idDtIdentificacion == null && other.idDtIdentificacion != null) || (this.idDtIdentificacion != null && !this.idDtIdentificacion.equals(other.idDtIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtIdentificaciones[ idDtIdentificacion=" + idDtIdentificacion + " ]";
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getEmision() {
        return emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAnverso() {
        return anverso;
    }

    public void setAnverso(String anverso) {
        this.anverso = anverso;
    }

    public String getReverso() {
        return reverso;
    }

    public void setReverso(String reverso) {
        this.reverso = reverso;
    }
    
    
    
    
}
