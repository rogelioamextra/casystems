/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_usuarios")
@NamedQueries({
    @NamedQuery(name = "CatUsuarios.findAll", query = "SELECT c FROM CatUsuarios c"),
    @NamedQuery(name = "CatUsuarios.findByIdUsuario", query = "SELECT c FROM CatUsuarios c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "CatUsuarios.findByUsername", query = "SELECT c FROM CatUsuarios c WHERE c.username = :username"),
    @NamedQuery(name = "CatUsuarios.findByPassword", query = "SELECT c FROM CatUsuarios c WHERE c.password = :password"),
    @NamedQuery(name = "CatUsuarios.findByFechaCreacion", query = "SELECT c FROM CatUsuarios c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CatUsuarios.findByStatus", query = "SELECT c FROM CatUsuarios c WHERE c.status = :status"),
    @NamedQuery(name = "CatUsuarios.findByPrimerInicio", query = "SELECT c FROM CatUsuarios c WHERE c.primerInicio = :primerInicio")})
public class CatUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "primer_inicio")
    private boolean primerInicio;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private CatPersonas idPersona;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private CatRoles idRol;
    @JoinColumn(name = "id_configuracion_empresa", referencedColumnName = "id_configuracion_empresa")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private DtConfiguracionEmpresas idConfiguracionEmpresa;
    
    @OneToMany(mappedBy = "idAsesor")
    private List<MvSolicitudesAmextra> mvSolicitudesAmextraList;

    public CatUsuarios() {
        idConfiguracionEmpresa=new DtConfiguracionEmpresas();
    }

    public CatUsuarios(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CatUsuarios(Long idUsuario, String username, boolean status, boolean primerInicio) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.status = status;
        this.primerInicio = primerInicio;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getPrimerInicio() {
        return primerInicio;
    }

    public void setPrimerInicio(boolean primerInicio) {
        this.primerInicio = primerInicio;
    }

    public CatPersonas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(CatPersonas idPersona) {
        this.idPersona = idPersona;
    }

    public CatRoles getIdRol() {
        return idRol;
    }

    public void setIdRol(CatRoles idRol) {
        this.idRol = idRol;
    }

    public DtConfiguracionEmpresas getIdConfiguracionEmpresa() {
        return idConfiguracionEmpresa;
    }

    public void setIdConfiguracionEmpresa(DtConfiguracionEmpresas idConfiguracionEmpresa) {
        this.idConfiguracionEmpresa = idConfiguracionEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatUsuarios)) {
            return false;
        }
        CatUsuarios other = (CatUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatUsuarios[ idUsuario=" + idUsuario + " ]";
    }

    public List<MvSolicitudesAmextra> getMvSolicitudesAmextraList() {
        return mvSolicitudesAmextraList;
    }

    public void setMvSolicitudesAmextraList(List<MvSolicitudesAmextra> mvSolicitudesAmextraList) {
        this.mvSolicitudesAmextraList = mvSolicitudesAmextraList;
    }
    
    
}
