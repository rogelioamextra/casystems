/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "dt_datos_laborales")
@NamedQueries({
    @NamedQuery(name = "DtDatosLaborales.findAll", query = "SELECT d FROM DtDatosLaborales d"),
    @NamedQuery(name = "DtDatosLaborales.findByIdDatosLaborales", query = "SELECT d FROM DtDatosLaborales d WHERE d.idDatosLaborales = :idDatosLaborales"),
    @NamedQuery(name = "DtDatosLaborales.findByPuesto", query = "SELECT d FROM DtDatosLaborales d WHERE d.puesto = :puesto"),
    @NamedQuery(name = "DtDatosLaborales.findByTelefono", query = "SELECT d FROM DtDatosLaborales d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DtDatosLaborales.findByRecibosNomina", query = "SELECT d FROM DtDatosLaborales d WHERE d.recibosNomina = :recibosNomina"),
    @NamedQuery(name = "DtDatosLaborales.findByIngresoMensual", query = "SELECT d FROM DtDatosLaborales d WHERE d.ingresoMensual = :ingresoMensual"),
    @NamedQuery(name = "DtDatosLaborales.findByFechaIngreso", query = "SELECT d FROM DtDatosLaborales d WHERE d.fechaIngreso = :fechaIngreso")})
public class DtDatosLaborales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_laborales")
    private Long idDatosLaborales;
    @Basic(optional = false)
    @Column(name = "puesto")
    private String puesto;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "recibos_nomina")
    private boolean recibosNomina;
    @Basic(optional = false)
    @Column(name = "ingreso_mensual")
    private float ingresoMensual;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDatosLaborales")
    private List<CatClientes> catClientesList;
    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CatDirecciones idDireccion;
    @JoinColumn(name = "id_giro_negocio", referencedColumnName = "id_giros_negocio")
    @ManyToOne(optional = false)
    private CatGirosNegociosEmpresas idGiroNegocio;
    @JoinColumn(name = "id_caracteristicas_negocio", referencedColumnName = "id_caracteristica_negocio")
    @ManyToOne(optional = false)
    private CatCaracteristicasNegocios idCaracteristicaNegocio;
    @JoinColumn(name = "id_tiempo_empleo_actual", referencedColumnName = "id_tiempo_empleo_actual")
    @ManyToOne(optional = false)
    private CatTiemposEmpleoActual idTiempoEmpleoActual;

    public DtDatosLaborales() {
        this.idDireccion = new CatDirecciones();
        this.idGiroNegocio = new CatGirosNegociosEmpresas();
    }

    public DtDatosLaborales(Long idDatosLaborales) {
        this.idDatosLaborales = idDatosLaborales;
    }

    public Long getIdDatosLaborales() {
        return idDatosLaborales;
    }

    public void setIdDatosLaborales(Long idDatosLaborales) {
        this.idDatosLaborales = idDatosLaborales;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getRecibosNomina() {
        return recibosNomina;
    }

    public void setRecibosNomina(boolean recibosNomina) {
        this.recibosNomina = recibosNomina;
    }

    public float getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(float ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<CatClientes> getCatClientesList() {
        return catClientesList;
    }

    public void setCatClientesList(List<CatClientes> catClientesList) {
        this.catClientesList = catClientesList;
    }

    public CatDirecciones getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(CatDirecciones idDireccion) {
        this.idDireccion = idDireccion;
    }

    public CatGirosNegociosEmpresas getIdGiroNegocio() {
        return idGiroNegocio;
    }

    public void setIdGiroNegocio(CatGirosNegociosEmpresas idGiroNegocio) {
        this.idGiroNegocio = idGiroNegocio;
    }

    public CatCaracteristicasNegocios getIdCaracteristicaNegocio() {
        return idCaracteristicaNegocio;
    }

    public void setIdCaracteristicaNegocio(CatCaracteristicasNegocios idCaracteristicaNegocio) {
        this.idCaracteristicaNegocio = idCaracteristicaNegocio;
    }

    public CatTiemposEmpleoActual getIdTiempoEmpleoActual() {
        return idTiempoEmpleoActual;
    }

    public void setIdTiempoEmpleoActual(CatTiemposEmpleoActual idTiempoEmpleoActual) {
        this.idTiempoEmpleoActual = idTiempoEmpleoActual;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosLaborales != null ? idDatosLaborales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDatosLaborales)) {
            return false;
        }
        DtDatosLaborales other = (DtDatosLaborales) object;
        if ((this.idDatosLaborales == null && other.idDatosLaborales != null) || (this.idDatosLaborales != null && !this.idDatosLaborales.equals(other.idDatosLaborales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtDatosLaborales[ idDatosLaborales=" + idDatosLaborales + " ]";
    }

}
