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
@Table(name = "dt_resultados_datos_validados")
@NamedQueries({
    @NamedQuery(name = "DtResultadosDatosValidados.findAll", query = "SELECT d FROM DtResultadosDatosValidados d"),
    @NamedQuery(name = "DtResultadosDatosValidados.findByIdDatosValidados", query = "SELECT d FROM DtResultadosDatosValidados d WHERE d.idDatosValidados = :idDatosValidados"),
    @NamedQuery(name = "DtResultadosDatosValidados.findByVerificacionDocumentos", query = "SELECT d FROM DtResultadosDatosValidados d WHERE d.verificacionDocumentos = :verificacionDocumentos"),
    @NamedQuery(name = "DtResultadosDatosValidados.findByVerificacionBiometrica", query = "SELECT d FROM DtResultadosDatosValidados d WHERE d.verificacionBiometrica = :verificacionBiometrica"),
    @NamedQuery(name = "DtResultadosDatosValidados.findByVerificacionDigital", query = "SELECT d FROM DtResultadosDatosValidados d WHERE d.verificacionDigital = :verificacionDigital"),
    @NamedQuery(name = "DtResultadosDatosValidados.findByVerificacionAutoridades", query = "SELECT d FROM DtResultadosDatosValidados d WHERE d.verificacionAutoridades = :verificacionAutoridades")})
public class DtResultadosDatosValidados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_validados")
    private Long idDatosValidados;
    @Column(name = "verificacion_documentos")
    private Boolean verificacionDocumentos;
    @Column(name = "verificacion_biometrica")
    private Boolean verificacionBiometrica;
    @Column(name = "verificacion_digital")
    private Boolean verificacionDigital;
    @Column(name = "verificacion_autoridades")
    private Boolean verificacionAutoridades;
    @JoinColumn(name = "id_estatus_solicitud", referencedColumnName = "id_estatus")
    @ManyToOne
    private CatEstatus idEstatusSolicitud;
    @JoinColumn(name = "id_solicitud_producto", referencedColumnName = "id_solicitud_producto")
    @ManyToOne
    private MvSolicitudProducto idSolicitudProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosResultadoValidacion")
    private List<MvResultadosValidacionesServiciosDigital> mvResultadosValidacionesServiciosDigitalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosResultadoValidacion")
    private List<MvResultadosValidacionesServiciosBiometricos> mvResultadosValidacionesServiciosBiometricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResultadosDatosValidados")
    private List<MvResultadosValidacionesDocumentos> mvResultadosValidacionesDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosResultadoValidacion")
    private List<MvResultadosValidacionesServiciosAutoridades> mvResultadosValidacionesServiciosAutoridadesList;

    public DtResultadosDatosValidados() {
    }

    public DtResultadosDatosValidados(Long idDatosValidados) {
        this.idDatosValidados = idDatosValidados;
    }

    public Long getIdDatosValidados() {
        return idDatosValidados;
    }

    public void setIdDatosValidados(Long idDatosValidados) {
        this.idDatosValidados = idDatosValidados;
    }

    public Boolean getVerificacionDocumentos() {
        return verificacionDocumentos;
    }

    public void setVerificacionDocumentos(Boolean verificacionDocumentos) {
        this.verificacionDocumentos = verificacionDocumentos;
    }

    public Boolean getVerificacionBiometrica() {
        return verificacionBiometrica;
    }

    public void setVerificacionBiometrica(Boolean verificacionBiometrica) {
        this.verificacionBiometrica = verificacionBiometrica;
    }

    public Boolean getVerificacionDigital() {
        return verificacionDigital;
    }

    public void setVerificacionDigital(Boolean verificacionDigital) {
        this.verificacionDigital = verificacionDigital;
    }

    public Boolean getVerificacionAutoridades() {
        return verificacionAutoridades;
    }

    public void setVerificacionAutoridades(Boolean verificacionAutoridades) {
        this.verificacionAutoridades = verificacionAutoridades;
    }

    public CatEstatus getIdEstatusSolicitud() {
        return idEstatusSolicitud;
    }

    public void setIdEstatusSolicitud(CatEstatus idEstatusSolicitud) {
        this.idEstatusSolicitud = idEstatusSolicitud;
    }

    public MvSolicitudProducto getIdSolicitudProducto() {
        return idSolicitudProducto;
    }

    public void setIdSolicitudProducto(MvSolicitudProducto idSolicitudProducto) {
        this.idSolicitudProducto = idSolicitudProducto;
    }

    public List<MvResultadosValidacionesServiciosDigital> getMvResultadosValidacionesServiciosDigitalList() {
        return mvResultadosValidacionesServiciosDigitalList;
    }

    public void setMvResultadosValidacionesServiciosDigitalList(List<MvResultadosValidacionesServiciosDigital> mvResultadosValidacionesServiciosDigitalList) {
        this.mvResultadosValidacionesServiciosDigitalList = mvResultadosValidacionesServiciosDigitalList;
    }

    public List<MvResultadosValidacionesServiciosBiometricos> getMvResultadosValidacionesServiciosBiometricosList() {
        return mvResultadosValidacionesServiciosBiometricosList;
    }

    public void setMvResultadosValidacionesServiciosBiometricosList(List<MvResultadosValidacionesServiciosBiometricos> mvResultadosValidacionesServiciosBiometricosList) {
        this.mvResultadosValidacionesServiciosBiometricosList = mvResultadosValidacionesServiciosBiometricosList;
    }

    public List<MvResultadosValidacionesDocumentos> getMvResultadosValidacionesDocumentosList() {
        return mvResultadosValidacionesDocumentosList;
    }

    public void setMvResultadosValidacionesDocumentosList(List<MvResultadosValidacionesDocumentos> mvResultadosValidacionesDocumentosList) {
        this.mvResultadosValidacionesDocumentosList = mvResultadosValidacionesDocumentosList;
    }

    public List<MvResultadosValidacionesServiciosAutoridades> getMvResultadosValidacionesServiciosAutoridadesList() {
        return mvResultadosValidacionesServiciosAutoridadesList;
    }

    public void setMvResultadosValidacionesServiciosAutoridadesList(List<MvResultadosValidacionesServiciosAutoridades> mvResultadosValidacionesServiciosAutoridadesList) {
        this.mvResultadosValidacionesServiciosAutoridadesList = mvResultadosValidacionesServiciosAutoridadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosValidados != null ? idDatosValidados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtResultadosDatosValidados)) {
            return false;
        }
        DtResultadosDatosValidados other = (DtResultadosDatosValidados) object;
        if ((this.idDatosValidados == null && other.idDatosValidados != null) || (this.idDatosValidados != null && !this.idDatosValidados.equals(other.idDatosValidados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtResultadosDatosValidados[ idDatosValidados=" + idDatosValidados + " ]";
    }
    
}
