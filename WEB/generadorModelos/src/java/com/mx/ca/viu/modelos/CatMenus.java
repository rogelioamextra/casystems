/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "cat_menus")
@NamedQueries({
    @NamedQuery(name = "CatMenus.findAll", query = "SELECT c FROM CatMenus c"),
    @NamedQuery(name = "CatMenus.findByIdMenu", query = "SELECT c FROM CatMenus c WHERE c.idMenu = :idMenu"),
    @NamedQuery(name = "CatMenus.findByNombreMenu", query = "SELECT c FROM CatMenus c WHERE c.nombreMenu = :nombreMenu"),
    @NamedQuery(name = "CatMenus.findBySeguridad", query = "SELECT c FROM CatMenus c WHERE c.seguridad = :seguridad"),
    @NamedQuery(name = "CatMenus.findByStatus", query = "SELECT c FROM CatMenus c WHERE c.status = :status"),
    @NamedQuery(name = "CatMenus.findByBanderaAdministracion", query = "SELECT c FROM CatMenus c WHERE c.banderaAdministracion = :banderaAdministracion"),
    @NamedQuery(name = "CatMenus.findByIcono", query = "SELECT c FROM CatMenus c WHERE c.icono = :icono"),
    @NamedQuery(name = "CatMenus.findByAccionListener", query = "SELECT c FROM CatMenus c WHERE c.accionListener = :accionListener"),
    @NamedQuery(name = "CatMenus.findByUpdate", query = "SELECT c FROM CatMenus c WHERE c.update = :update"),
    @NamedQuery(name = "CatMenus.findByOnclick", query = "SELECT c FROM CatMenus c WHERE c.onclick = :onclick"),
    @NamedQuery(name = "CatMenus.findByNivel", query = "SELECT c FROM CatMenus c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "CatMenus.findByOrden", query = "SELECT c FROM CatMenus c WHERE c.orden = :orden")})
public class CatMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu")
    private Long idMenu;
    @Basic(optional = false)
    @Column(name = "nombre_menu")
    private String nombreMenu;
    @Basic(optional = false)
    @Column(name = "seguridad")
    private String seguridad;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "bandera_administracion")
    private Boolean banderaAdministracion;
    @Column(name = "icono")
    private String icono;
    @Column(name = "accion_listener")
    private String accionListener;
    @Column(name = "update")
    private String update;
    @Column(name = "onclick")
    private String onclick;
    @Basic(optional = false)
    @Column(name = "nivel")
    private long nivel;
    @Column(name = "orden")
    private BigInteger orden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu")
    private List<MenusRoles> menusRolesList;

    public CatMenus() {
    }

    public CatMenus(Long idMenu) {
        this.idMenu = idMenu;
    }

    public CatMenus(Long idMenu, String nombreMenu, String seguridad, boolean status, long nivel) {
        this.idMenu = idMenu;
        this.nombreMenu = nombreMenu;
        this.seguridad = seguridad;
        this.status = status;
        this.nivel = nivel;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Boolean getBanderaAdministracion() {
        return banderaAdministracion;
    }

    public void setBanderaAdministracion(Boolean banderaAdministracion) {
        this.banderaAdministracion = banderaAdministracion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getAccionListener() {
        return accionListener;
    }

    public void setAccionListener(String accionListener) {
        this.accionListener = accionListener;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public long getNivel() {
        return nivel;
    }

    public void setNivel(long nivel) {
        this.nivel = nivel;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public List<MenusRoles> getMenusRolesList() {
        return menusRolesList;
    }

    public void setMenusRolesList(List<MenusRoles> menusRolesList) {
        this.menusRolesList = menusRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMenus)) {
            return false;
        }
        CatMenus other = (CatMenus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatMenus[ idMenu=" + idMenu + " ]";
    }
    
}
