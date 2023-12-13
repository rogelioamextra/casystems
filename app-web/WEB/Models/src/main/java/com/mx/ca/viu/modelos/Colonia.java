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
@Table(name = "colonia")
@NamedQueries({
    @NamedQuery(name = "Colonia.findAll", query = "SELECT c FROM Colonia c"),
    @NamedQuery(name = "Colonia.findById", query = "SELECT c FROM Colonia c WHERE c.id = :id"),
    @NamedQuery(name = "Colonia.findByIdentificadorMunicipal", query = "SELECT c FROM Colonia c WHERE c.identificadorMunicipal = :identificadorMunicipal"),
    @NamedQuery(name = "Colonia.findByNombre", query = "SELECT c FROM Colonia c WHERE c.nombre = :nombre")})
public class Colonia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "identificador_municipal")
    private String identificadorMunicipal;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColonia")
    private List<CatDirecciones> catDireccionesList;
    @JsonIgnore
    @JoinColumn(name = "asentamiento_tipo_id", referencedColumnName = "id")
    @ManyToOne
    private AsentamientoTipo asentamientoTipoId;
    @JsonIgnore
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    @ManyToOne
    private Ciudad ciudadId;
    @JsonIgnore
    @JoinColumn(name = "codigo_postal_administracion_asentamiento_id", referencedColumnName = "id")
    @ManyToOne
    private CodigoPostal codigoPostalAdministracionAsentamientoId;
    @JsonIgnore
    @JoinColumn(name = "codigo_postal_administracion_asentamiento_oficina_id", referencedColumnName = "id")
    @ManyToOne
    private CodigoPostal codigoPostalAdministracionAsentamientoOficinaId;
    @JsonIgnore
    @JoinColumn(name = "codigo_postal_id", referencedColumnName = "id")
    @ManyToOne
    private CodigoPostal codigoPostalId;
    @JsonIgnore
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    @ManyToOne
    private Estado estadoId;
    @JsonIgnore
    @JoinColumn(name = "inegi_clave_ciudad_id", referencedColumnName = "id")
    @ManyToOne
    private InegiClaveCiudad inegiClaveCiudadId;
    @JsonIgnore
    @JoinColumn(name = "inegi_clave_municipio_id", referencedColumnName = "id")
    @ManyToOne
    private InegiClaveMunicipio inegiClaveMunicipioId;
    @JsonIgnore
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    @ManyToOne
    private Municipio municipioId;
    @JsonIgnore
    @JoinColumn(name = "zona_tipo_id", referencedColumnName = "id")
    @ManyToOne
    private ZonaTipo zonaTipoId;

    public Colonia() {
    }

    public Colonia(Long id) {
        this.id = id;
    }

    public Colonia(Long id, String identificadorMunicipal, String nombre) {
        this.id = id;
        this.identificadorMunicipal = identificadorMunicipal;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificadorMunicipal() {
        return identificadorMunicipal;
    }

    public void setIdentificadorMunicipal(String identificadorMunicipal) {
        this.identificadorMunicipal = identificadorMunicipal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CatDirecciones> getCatDireccionesList() {
        return catDireccionesList;
    }

    public void setCatDireccionesList(List<CatDirecciones> catDireccionesList) {
        this.catDireccionesList = catDireccionesList;
    }

    public AsentamientoTipo getAsentamientoTipoId() {
        return asentamientoTipoId;
    }

    public void setAsentamientoTipoId(AsentamientoTipo asentamientoTipoId) {
        this.asentamientoTipoId = asentamientoTipoId;
    }

    public Ciudad getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Ciudad ciudadId) {
        this.ciudadId = ciudadId;
    }

    public CodigoPostal getCodigoPostalAdministracionAsentamientoId() {
        return codigoPostalAdministracionAsentamientoId;
    }

    public void setCodigoPostalAdministracionAsentamientoId(CodigoPostal codigoPostalAdministracionAsentamientoId) {
        this.codigoPostalAdministracionAsentamientoId = codigoPostalAdministracionAsentamientoId;
    }

    public CodigoPostal getCodigoPostalAdministracionAsentamientoOficinaId() {
        return codigoPostalAdministracionAsentamientoOficinaId;
    }

    public void setCodigoPostalAdministracionAsentamientoOficinaId(CodigoPostal codigoPostalAdministracionAsentamientoOficinaId) {
        this.codigoPostalAdministracionAsentamientoOficinaId = codigoPostalAdministracionAsentamientoOficinaId;
    }

    public CodigoPostal getCodigoPostalId() {
        return codigoPostalId;
    }

    public void setCodigoPostalId(CodigoPostal codigoPostalId) {
        this.codigoPostalId = codigoPostalId;
    }

    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
    }

    public InegiClaveCiudad getInegiClaveCiudadId() {
        return inegiClaveCiudadId;
    }

    public void setInegiClaveCiudadId(InegiClaveCiudad inegiClaveCiudadId) {
        this.inegiClaveCiudadId = inegiClaveCiudadId;
    }

    public InegiClaveMunicipio getInegiClaveMunicipioId() {
        return inegiClaveMunicipioId;
    }

    public void setInegiClaveMunicipioId(InegiClaveMunicipio inegiClaveMunicipioId) {
        this.inegiClaveMunicipioId = inegiClaveMunicipioId;
    }

    public Municipio getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Municipio municipioId) {
        this.municipioId = municipioId;
    }

    public ZonaTipo getZonaTipoId() {
        return zonaTipoId;
    }

    public void setZonaTipoId(ZonaTipo zonaTipoId) {
        this.zonaTipoId = zonaTipoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colonia)) {
            return false;
        }
        Colonia other = (Colonia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.Colonia[ id=" + id + " ]";
    }
    
}
