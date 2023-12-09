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
import javax.persistence.Transient;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_config_avisos")
@NamedQueries({
    @NamedQuery(name = "MvConfigAvisos.findAll", query = "SELECT m FROM MvConfigAvisos m"),
    @NamedQuery(name = "MvConfigAvisos.findByIdConfigAvisos", query = "SELECT m FROM MvConfigAvisos m WHERE m.idConfigAvisos = :idConfigAvisos"),
    @NamedQuery(name = "MvConfigAvisos.findByNombreArchivo", query = "SELECT m FROM MvConfigAvisos m WHERE m.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "MvConfigAvisos.findByAviso", query = "SELECT m FROM MvConfigAvisos m WHERE m.aviso = :aviso"),
    @NamedQuery(name = "MvConfigAvisos.findByStatus", query = "SELECT m FROM MvConfigAvisos m WHERE m.status = :status"),
    @NamedQuery(name = "MvConfigAvisos.findByNombre", query = "SELECT m FROM MvConfigAvisos m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MvConfigAvisos.findByDocumento", query = "SELECT m FROM MvConfigAvisos m WHERE m.documento = :documento")})
public class MvConfigAvisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config_avisos")
    private Long idConfigAvisos;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "aviso")
    private String aviso;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "expira")
    private boolean expira;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "documento")
    private Boolean documento;
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;

    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne(optional = false)
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_tipo_aviso", referencedColumnName = "id_tipo_aviso")
    @ManyToOne(optional = false)
    private CatTiposAvisos idTipoAviso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAviso")
    private List<DatosSolicitudAvisos> datosSolicitudAvisosList;

    @Transient
    private boolean banderaStatus;

    public MvConfigAvisos() {
    }

    public MvConfigAvisos(Long idConfigAvisos) {
        this.idConfigAvisos = idConfigAvisos;
    }

    public MvConfigAvisos(Long idConfigAvisos, boolean status) {
        this.idConfigAvisos = idConfigAvisos;
        this.status = status;
    }

    public Long getIdConfigAvisos() {
        return idConfigAvisos;
    }

    public void setIdConfigAvisos(Long idConfigAvisos) {
        this.idConfigAvisos = idConfigAvisos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getDocumento() {
        return documento;
    }

    public void setDocumento(Boolean documento) {
        this.documento = documento;
    }

    public CatCategoriasCampos getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatCategoriasCampos idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatTiposAvisos getIdTipoAviso() {
        return idTipoAviso;
    }

    public void setIdTipoAviso(CatTiposAvisos idTipoAviso) {
        this.idTipoAviso = idTipoAviso;
    }

    public List<DatosSolicitudAvisos> getDatosSolicitudAvisosList() {
        return datosSolicitudAvisosList;
    }

    public void setDatosSolicitudAvisosList(List<DatosSolicitudAvisos> datosSolicitudAvisosList) {
        this.datosSolicitudAvisosList = datosSolicitudAvisosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfigAvisos != null ? idConfigAvisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvConfigAvisos)) {
            return false;
        }
        MvConfigAvisos other = (MvConfigAvisos) object;
        if ((this.idConfigAvisos == null && other.idConfigAvisos != null) || (this.idConfigAvisos != null && !this.idConfigAvisos.equals(other.idConfigAvisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvConfigAvisos[ idConfigAvisos=" + idConfigAvisos + " ]";
    }

    public boolean isBanderaStatus() {
        return banderaStatus;
    }

    public void setBanderaStatus(boolean banderaStatus) {
        this.banderaStatus = banderaStatus;
    }

    public boolean isExpira() {
        return expira;
    }

    public void setExpira(boolean expira) {
        this.expira = expira;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    
    

}
