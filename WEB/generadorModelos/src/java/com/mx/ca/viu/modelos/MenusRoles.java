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
@Table(name = "menus_roles")
@NamedQueries({
    @NamedQuery(name = "MenusRoles.findAll", query = "SELECT m FROM MenusRoles m"),
    @NamedQuery(name = "MenusRoles.findByIdMenuRol", query = "SELECT m FROM MenusRoles m WHERE m.idMenuRol = :idMenuRol")})
public class MenusRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu_rol")
    private Long idMenuRol;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    @ManyToOne(optional = false)
    private CatMenus idMenu;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private CatRoles idRol;

    public MenusRoles() {
    }

    public MenusRoles(Long idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Long getIdMenuRol() {
        return idMenuRol;
    }

    public void setIdMenuRol(Long idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public CatMenus getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(CatMenus idMenu) {
        this.idMenu = idMenu;
    }

    public CatRoles getIdRol() {
        return idRol;
    }

    public void setIdRol(CatRoles idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuRol != null ? idMenuRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenusRoles)) {
            return false;
        }
        MenusRoles other = (MenusRoles) object;
        if ((this.idMenuRol == null && other.idMenuRol != null) || (this.idMenuRol != null && !this.idMenuRol.equals(other.idMenuRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MenusRoles[ idMenuRol=" + idMenuRol + " ]";
    }
    
}
