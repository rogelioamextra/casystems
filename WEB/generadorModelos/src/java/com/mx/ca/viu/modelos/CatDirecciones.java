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
@Table(name = "cat_direcciones")
@NamedQueries({
    @NamedQuery(name = "CatDirecciones.findAll", query = "SELECT c FROM CatDirecciones c"),
    @NamedQuery(name = "CatDirecciones.findByIdDireccion", query = "SELECT c FROM CatDirecciones c WHERE c.idDireccion = :idDireccion"),
    @NamedQuery(name = "CatDirecciones.findByCalle", query = "SELECT c FROM CatDirecciones c WHERE c.calle = :calle"),
    @NamedQuery(name = "CatDirecciones.findByNumeroInterior", query = "SELECT c FROM CatDirecciones c WHERE c.numeroInterior = :numeroInterior"),
    @NamedQuery(name = "CatDirecciones.findByNumeroExterior", query = "SELECT c FROM CatDirecciones c WHERE c.numeroExterior = :numeroExterior"),
    @NamedQuery(name = "CatDirecciones.findByCp", query = "SELECT c FROM CatDirecciones c WHERE c.cp = :cp"),
    @NamedQuery(name = "CatDirecciones.findByTiempoResidencia", query = "SELECT c FROM CatDirecciones c WHERE c.tiempoResidencia = :tiempoResidencia"),
    @NamedQuery(name = "CatDirecciones.findByComprobante", query = "SELECT c FROM CatDirecciones c WHERE c.comprobante = :comprobante"),
    @NamedQuery(name = "CatDirecciones.findByGeolocalizacionLatitud", query = "SELECT c FROM CatDirecciones c WHERE c.geolocalizacionLatitud = :geolocalizacionLatitud"),
    @NamedQuery(name = "CatDirecciones.findByGeolocalizacionLongitud", query = "SELECT c FROM CatDirecciones c WHERE c.geolocalizacionLongitud = :geolocalizacionLongitud"),
    @NamedQuery(name = "CatDirecciones.findByNombreArchivo", query = "SELECT c FROM CatDirecciones c WHERE c.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "CatDirecciones.findByTiempoResidenciaMeses", query = "SELECT c FROM CatDirecciones c WHERE c.tiempoResidenciaMeses = :tiempoResidenciaMeses")})
public class CatDirecciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion")
    private Long idDireccion;
    @Basic(optional = false)
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero_interior")
    private String numeroInterior;
    @Basic(optional = false)
    @Column(name = "numero_exterior")
    private String numeroExterior;
    @Basic(optional = false)
    @Column(name = "cp")
    private String cp;
    @Column(name = "tiempo_residencia")
    private Short tiempoResidencia;
    @Column(name = "comprobante")
    private String comprobante;
    @Column(name = "geolocalizacion_latitud")
    private String geolocalizacionLatitud;
    @Column(name = "geolocalizacion_longitud")
    private String geolocalizacionLongitud;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "tiempo_residencia_meses")
    private Short tiempoResidenciaMeses;
    @JoinColumn(name = "id_colonia", referencedColumnName = "id_colonia")
    @ManyToOne(optional = false)
    private CatColonias idColonia;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private CatEstados idEstado;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private CatMunicipios idMunicipio;
    @JoinColumn(name = "id_tipo_residencia", referencedColumnName = "id_tipo_residencia")
    @ManyToOne
    private CatTiposResidencias idTipoResidencia;
    @JoinColumn(name = "id_tipo_vivienda", referencedColumnName = "id_vivienda")
    @ManyToOne
    private CatTiposViviendas idTipoVivienda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDireccion")
    private List<CatClientes> catClientesList;
    @OneToMany(mappedBy = "idDireccion")
    private List<DtReferenciasPersonales> dtReferenciasPersonalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDireccion")
    private List<DtDatosLaborales> dtDatosLaboralesList;

    public CatDirecciones() {
    }

    public CatDirecciones(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public CatDirecciones(Long idDireccion, String calle, String numeroExterior, String cp) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.cp = cp;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Short getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(Short tiempoResidencia) {
        this.tiempoResidencia = tiempoResidencia;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getGeolocalizacionLatitud() {
        return geolocalizacionLatitud;
    }

    public void setGeolocalizacionLatitud(String geolocalizacionLatitud) {
        this.geolocalizacionLatitud = geolocalizacionLatitud;
    }

    public String getGeolocalizacionLongitud() {
        return geolocalizacionLongitud;
    }

    public void setGeolocalizacionLongitud(String geolocalizacionLongitud) {
        this.geolocalizacionLongitud = geolocalizacionLongitud;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Short getTiempoResidenciaMeses() {
        return tiempoResidenciaMeses;
    }

    public void setTiempoResidenciaMeses(Short tiempoResidenciaMeses) {
        this.tiempoResidenciaMeses = tiempoResidenciaMeses;
    }

    public CatColonias getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(CatColonias idColonia) {
        this.idColonia = idColonia;
    }

    public CatEstados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(CatEstados idEstado) {
        this.idEstado = idEstado;
    }

    public CatMunicipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(CatMunicipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public CatTiposResidencias getIdTipoResidencia() {
        return idTipoResidencia;
    }

    public void setIdTipoResidencia(CatTiposResidencias idTipoResidencia) {
        this.idTipoResidencia = idTipoResidencia;
    }

    public CatTiposViviendas getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(CatTiposViviendas idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    public List<CatClientes> getCatClientesList() {
        return catClientesList;
    }

    public void setCatClientesList(List<CatClientes> catClientesList) {
        this.catClientesList = catClientesList;
    }

    public List<DtReferenciasPersonales> getDtReferenciasPersonalesList() {
        return dtReferenciasPersonalesList;
    }

    public void setDtReferenciasPersonalesList(List<DtReferenciasPersonales> dtReferenciasPersonalesList) {
        this.dtReferenciasPersonalesList = dtReferenciasPersonalesList;
    }

    public List<DtDatosLaborales> getDtDatosLaboralesList() {
        return dtDatosLaboralesList;
    }

    public void setDtDatosLaboralesList(List<DtDatosLaborales> dtDatosLaboralesList) {
        this.dtDatosLaboralesList = dtDatosLaboralesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDirecciones)) {
            return false;
        }
        CatDirecciones other = (CatDirecciones) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatDirecciones[ idDireccion=" + idDireccion + " ]";
    }
    
}
