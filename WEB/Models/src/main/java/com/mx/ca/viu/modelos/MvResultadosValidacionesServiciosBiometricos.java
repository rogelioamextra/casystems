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
@Table(name = "mv_resultados_validaciones_servicios_biometricos")
@NamedQueries({
    @NamedQuery(name = "MvResultadosValidacionesServiciosBiometricos.findAll", query = "SELECT m FROM MvResultadosValidacionesServiciosBiometricos m"),
    @NamedQuery(name = "MvResultadosValidacionesServiciosBiometricos.findByIdResultadoValidacionServiciosBiometrico", query = "SELECT m FROM MvResultadosValidacionesServiciosBiometricos m WHERE m.idResultadoValidacionServiciosBiometrico = :idResultadoValidacionServiciosBiometrico"),
    @NamedQuery(name = "MvResultadosValidacionesServiciosBiometricos.findByResultado", query = "SELECT m FROM MvResultadosValidacionesServiciosBiometricos m WHERE m.resultado = :resultado"),
    @NamedQuery(name = "MvResultadosValidacionesServiciosBiometricos.findByResultadoDataString", query = "SELECT m FROM MvResultadosValidacionesServiciosBiometricos m WHERE m.resultadoDataString = :resultadoDataString")})
public class MvResultadosValidacionesServiciosBiometricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultado_validacion_servicios_biometrico")
    private Long idResultadoValidacionServiciosBiometrico;
    @Column(name = "resultado")
    private Boolean resultado;
    @Lob
    @Column(name = "resultado_data")
    private Object resultadoData;
    @Column(name = "resultado_data_string")
    private String resultadoDataString;
    @JoinColumn(name = "id_servicio_validacion", referencedColumnName = "id_servicios_validaciones")
    @ManyToOne(optional = false)
    private CatServiciosValidacionesExternos idServicioValidacion;
    @JoinColumn(name = "id_datos_resultado_validacion", referencedColumnName = "id_datos_validados")
    @ManyToOne(optional = false)
    private DtResultadosDatosValidados idDatosResultadoValidacion;

    public MvResultadosValidacionesServiciosBiometricos() {
    }

    public MvResultadosValidacionesServiciosBiometricos(Long idResultadoValidacionServiciosBiometrico) {
        this.idResultadoValidacionServiciosBiometrico = idResultadoValidacionServiciosBiometrico;
    }

    public Long getIdResultadoValidacionServiciosBiometrico() {
        return idResultadoValidacionServiciosBiometrico;
    }

    public void setIdResultadoValidacionServiciosBiometrico(Long idResultadoValidacionServiciosBiometrico) {
        this.idResultadoValidacionServiciosBiometrico = idResultadoValidacionServiciosBiometrico;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public Object getResultadoData() {
        return resultadoData;
    }

    public void setResultadoData(Object resultadoData) {
        this.resultadoData = resultadoData;
    }

    public String getResultadoDataString() {
        return resultadoDataString;
    }

    public void setResultadoDataString(String resultadoDataString) {
        this.resultadoDataString = resultadoDataString;
    }

    public CatServiciosValidacionesExternos getIdServicioValidacion() {
        return idServicioValidacion;
    }

    public void setIdServicioValidacion(CatServiciosValidacionesExternos idServicioValidacion) {
        this.idServicioValidacion = idServicioValidacion;
    }

    public DtResultadosDatosValidados getIdDatosResultadoValidacion() {
        return idDatosResultadoValidacion;
    }

    public void setIdDatosResultadoValidacion(DtResultadosDatosValidados idDatosResultadoValidacion) {
        this.idDatosResultadoValidacion = idDatosResultadoValidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoValidacionServiciosBiometrico != null ? idResultadoValidacionServiciosBiometrico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvResultadosValidacionesServiciosBiometricos)) {
            return false;
        }
        MvResultadosValidacionesServiciosBiometricos other = (MvResultadosValidacionesServiciosBiometricos) object;
        if ((this.idResultadoValidacionServiciosBiometrico == null && other.idResultadoValidacionServiciosBiometrico != null) || (this.idResultadoValidacionServiciosBiometrico != null && !this.idResultadoValidacionServiciosBiometrico.equals(other.idResultadoValidacionServiciosBiometrico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosBiometricos[ idResultadoValidacionServiciosBiometrico=" + idResultadoValidacionServiciosBiometrico + " ]";
    }
    
}
