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
@Table(name = "dt_referencias_personales")
@NamedQueries({
    @NamedQuery(name = "DtReferenciasPersonales.findAll", query = "SELECT d FROM DtReferenciasPersonales d"),
    @NamedQuery(name = "DtReferenciasPersonales.findByIdReferencia", query = "SELECT d FROM DtReferenciasPersonales d WHERE d.idReferencia = :idReferencia"),
    @NamedQuery(name = "DtReferenciasPersonales.findByNombreCompleto", query = "SELECT d FROM DtReferenciasPersonales d WHERE d.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "DtReferenciasPersonales.findByTelefono", query = "SELECT d FROM DtReferenciasPersonales d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DtReferenciasPersonales.findByStatus", query = "SELECT d FROM DtReferenciasPersonales d WHERE d.status = :status"),
    @NamedQuery(name = "DtReferenciasPersonales.findByApellidoPaterno", query = "SELECT d FROM DtReferenciasPersonales d WHERE d.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "DtReferenciasPersonales.findByApellidoMaterno", query = "SELECT d FROM DtReferenciasPersonales d WHERE d.apellidoMaterno = :apellidoMaterno")})
public class DtReferenciasPersonales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_referencia")
    private Long idReferencia;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private CatClientes idCliente;
    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne
    private CatDirecciones idDireccion;
    @JoinColumn(name = "id_parentesco", referencedColumnName = "id_parentesco")
    @ManyToOne
    private CatParentescos idParentesco;

    public DtReferenciasPersonales() {
    }

    public DtReferenciasPersonales(Long idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Long getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Long idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public CatClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(CatClientes idCliente) {
        this.idCliente = idCliente;
    }

    public CatDirecciones getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(CatDirecciones idDireccion) {
        this.idDireccion = idDireccion;
    }

    public CatParentescos getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(CatParentescos idParentesco) {
        this.idParentesco = idParentesco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReferencia != null ? idReferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtReferenciasPersonales)) {
            return false;
        }
        DtReferenciasPersonales other = (DtReferenciasPersonales) object;
        if ((this.idReferencia == null && other.idReferencia != null) || (this.idReferencia != null && !this.idReferencia.equals(other.idReferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtReferenciasPersonales[ idReferencia=" + idReferencia + " ]";
    }
    
}
