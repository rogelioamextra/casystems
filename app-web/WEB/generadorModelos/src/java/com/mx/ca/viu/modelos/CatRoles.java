/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_roles")
@NamedQueries({
    @NamedQuery(name = "CatRoles.findAll", query = "SELECT c FROM CatRoles c"),
    @NamedQuery(name = "CatRoles.findByIdRol", query = "SELECT c FROM CatRoles c WHERE c.idRol = :idRol"),
    @NamedQuery(name = "CatRoles.findByNombre", query = "SELECT c FROM CatRoles c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatRoles.findByDescripcion", query = "SELECT c FROM CatRoles c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatRoles.findByStatus", query = "SELECT c FROM CatRoles c WHERE c.status = :status"),
    @NamedQuery(name = "CatRoles.findBySupervisor", query = "SELECT c FROM CatRoles c WHERE c.supervisor = :supervisor"),
    @NamedQuery(name = "CatRoles.findByAdministrador", query = "SELECT c FROM CatRoles c WHERE c.administrador = :administrador")})
public class CatRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol")
    private Long idRol;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "supervisor")
    private Boolean supervisor;
    @Column(name = "administrador")
    private Boolean administrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private List<MenusRoles> menusRolesList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @OneToMany(mappedBy = "idRol")
    private List<CatUsuarios> catUsuariosList;

    public CatRoles() {
    }

    public CatRoles(Long idRol) {
        this.idRol = idRol;
    }

    public CatRoles(Long idRol, String nombre, boolean status) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Boolean getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Boolean supervisor) {
        this.supervisor = supervisor;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public List<MenusRoles> getMenusRolesList() {
        return menusRolesList;
    }

    public void setMenusRolesList(List<MenusRoles> menusRolesList) {
        this.menusRolesList = menusRolesList;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<CatUsuarios> getCatUsuariosList() {
        return catUsuariosList;
    }

    public void setCatUsuariosList(List<CatUsuarios> catUsuariosList) {
        this.catUsuariosList = catUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatRoles)) {
            return false;
        }
        CatRoles other = (CatRoles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatRoles[ idRol=" + idRol + " ]";
    }
    
}
