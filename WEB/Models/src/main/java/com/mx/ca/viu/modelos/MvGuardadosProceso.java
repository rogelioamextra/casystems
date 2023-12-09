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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_guardados_proceso")
@NamedQueries({
    @NamedQuery(name = "MvGuardadosProceso.findAll", query = "SELECT m FROM MvGuardadosProceso m"),
    @NamedQuery(name = "MvGuardadosProceso.findByIdGuardadoProceso", query = "SELECT m FROM MvGuardadosProceso m WHERE m.idGuardadoProceso = :idGuardadoProceso")})
public class MvGuardadosProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_guardado_proceso")
    private Long idGuardadoProceso;
    @Basic(optional = false)
    @Lob
    @Column(name = "datos_guardados")
    private Object datosGuardados;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne(optional = false)
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne(optional = false)
    private MvConfigSolicitudes idConfigSolicitud;

    public MvGuardadosProceso() {
    }

    public MvGuardadosProceso(Long idGuardadoProceso) {
        this.idGuardadoProceso = idGuardadoProceso;
    }

    public MvGuardadosProceso(Long idGuardadoProceso, Object datosGuardados) {
        this.idGuardadoProceso = idGuardadoProceso;
        this.datosGuardados = datosGuardados;
    }

    public Long getIdGuardadoProceso() {
        return idGuardadoProceso;
    }

    public void setIdGuardadoProceso(Long idGuardadoProceso) {
        this.idGuardadoProceso = idGuardadoProceso;
    }

    public Object getDatosGuardados() {
        return datosGuardados;
    }

    public void setDatosGuardados(Object datosGuardados) {
        this.datosGuardados = datosGuardados;
    }

    public CatCategoriasCampos getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatCategoriasCampos idCategoria) {
        this.idCategoria = idCategoria;
    }

    public MvConfigSolicitudes getIdConfigSolicitud() {
        return idConfigSolicitud;
    }

    public void setIdConfigSolicitud(MvConfigSolicitudes idConfigSolicitud) {
        this.idConfigSolicitud = idConfigSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuardadoProceso != null ? idGuardadoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvGuardadosProceso)) {
            return false;
        }
        MvGuardadosProceso other = (MvGuardadosProceso) object;
        if ((this.idGuardadoProceso == null && other.idGuardadoProceso != null) || (this.idGuardadoProceso != null && !this.idGuardadoProceso.equals(other.idGuardadoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvGuardadosProceso[ idGuardadoProceso=" + idGuardadoProceso + " ]";
    }
    
}
