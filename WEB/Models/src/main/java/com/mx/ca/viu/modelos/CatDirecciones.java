/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_direcciones")

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
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Basic()
    @Column(name = "tiempo_residencia")
    private short tiempoResidencia;
    @Basic
    @Column(name = "tiempo_residencia_meses")
    private short tiempoResidenciaMeses;
 
    private String comprobante;
    @Basic()
    @Column(name = "geolocalizacion_latitud")
    private String geolocalizacionLatitud;
    @Basic()
    @Column(name = "geolocalizacion_longitud")
    private String geolocalizacionLongitud;
    @JoinColumn(name = "id_tipo_residencia", referencedColumnName = "id_tipo_residencia")
    @ManyToOne()
    private CatTiposResidencias idTipoResidencia;
    @JoinColumn(name = "id_tipo_vivienda", referencedColumnName = "id_vivienda")
    @ManyToOne()
    private CatTiposViviendas idTipoVivienda;
   @JoinColumn(name = "id_colonia", referencedColumnName = "id_colonia")
    @ManyToOne(optional = false)
    private CatColonias idColonia;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private CatEstados idEstado;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private CatMunicipios idMunicipio;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDireccion")
    @JsonIgnore

    private List<CatClientes> catClientesList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDireccion")
    private List<DtDatosLaborales> dtDatosLaboralesList;
    
    @Transient
    private boolean banderaCambioImagen;

    public CatDirecciones() {
    }

    
    
  

    public boolean isBanderaCambioImagen() {
        return banderaCambioImagen;
    }

    public void setBanderaCambioImagen(boolean banderaCambioImagen) {
        this.banderaCambioImagen = banderaCambioImagen;
    }
    
    

    public CatDirecciones(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public short getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(short tiempoResidencia) {
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


    public List<CatClientes> getCatClientesList() {
        return catClientesList;
    }

    public void setCatClientesList(List<CatClientes> catClientesList) {
        this.catClientesList = catClientesList;
    }

    public List<DtDatosLaborales> getDtDatosLaboralesList() {
        return dtDatosLaboralesList;
    }

    public void setDtDatosLaboralesList(List<DtDatosLaborales> dtDatosLaboralesList) {
        this.dtDatosLaboralesList = dtDatosLaboralesList;
    }

    public short getTiempoResidenciaMeses() {
        return tiempoResidenciaMeses;
    }

    public void setTiempoResidenciaMeses(short tiempoResidenciaMeses) {
        this.tiempoResidenciaMeses = tiempoResidenciaMeses;
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
