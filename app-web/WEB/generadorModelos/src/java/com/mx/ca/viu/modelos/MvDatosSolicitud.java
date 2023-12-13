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
@Table(name = "mv_datos_solicitud")
@NamedQueries({
    @NamedQuery(name = "MvDatosSolicitud.findAll", query = "SELECT m FROM MvDatosSolicitud m"),
    @NamedQuery(name = "MvDatosSolicitud.findByIdDatosSolicitud", query = "SELECT m FROM MvDatosSolicitud m WHERE m.idDatosSolicitud = :idDatosSolicitud")})
public class MvDatosSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_solicitud")
    private Long idDatosSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosSolicitud")
    private List<DatosSolicitudDocumentos> datosSolicitudDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosSolicitud")
    private List<DatosSolicitudCategorias> datosSolicitudCategoriasList;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne(optional = false)
    private MvConfigSolicitudes idConfigSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosSolicitud")
    private List<DatosSolicitudAvisos> datosSolicitudAvisosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosSolicitud")
    private List<DatosSolicitudCampos> datosSolicitudCamposList;

    public MvDatosSolicitud() {
    }

    public MvDatosSolicitud(Long idDatosSolicitud) {
        this.idDatosSolicitud = idDatosSolicitud;
    }

    public Long getIdDatosSolicitud() {
        return idDatosSolicitud;
    }

    public void setIdDatosSolicitud(Long idDatosSolicitud) {
        this.idDatosSolicitud = idDatosSolicitud;
    }

    public List<DatosSolicitudDocumentos> getDatosSolicitudDocumentosList() {
        return datosSolicitudDocumentosList;
    }

    public void setDatosSolicitudDocumentosList(List<DatosSolicitudDocumentos> datosSolicitudDocumentosList) {
        this.datosSolicitudDocumentosList = datosSolicitudDocumentosList;
    }

    public List<DatosSolicitudCategorias> getDatosSolicitudCategoriasList() {
        return datosSolicitudCategoriasList;
    }

    public void setDatosSolicitudCategoriasList(List<DatosSolicitudCategorias> datosSolicitudCategoriasList) {
        this.datosSolicitudCategoriasList = datosSolicitudCategoriasList;
    }

    public MvConfigSolicitudes getIdConfigSolicitud() {
        return idConfigSolicitud;
    }

    public void setIdConfigSolicitud(MvConfigSolicitudes idConfigSolicitud) {
        this.idConfigSolicitud = idConfigSolicitud;
    }

    public List<DatosSolicitudAvisos> getDatosSolicitudAvisosList() {
        return datosSolicitudAvisosList;
    }

    public void setDatosSolicitudAvisosList(List<DatosSolicitudAvisos> datosSolicitudAvisosList) {
        this.datosSolicitudAvisosList = datosSolicitudAvisosList;
    }

    public List<DatosSolicitudCampos> getDatosSolicitudCamposList() {
        return datosSolicitudCamposList;
    }

    public void setDatosSolicitudCamposList(List<DatosSolicitudCampos> datosSolicitudCamposList) {
        this.datosSolicitudCamposList = datosSolicitudCamposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosSolicitud != null ? idDatosSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvDatosSolicitud)) {
            return false;
        }
        MvDatosSolicitud other = (MvDatosSolicitud) object;
        if ((this.idDatosSolicitud == null && other.idDatosSolicitud != null) || (this.idDatosSolicitud != null && !this.idDatosSolicitud.equals(other.idDatosSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvDatosSolicitud[ idDatosSolicitud=" + idDatosSolicitud + " ]";
    }
    
}
