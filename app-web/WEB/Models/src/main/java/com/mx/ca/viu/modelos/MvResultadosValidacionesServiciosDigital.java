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
@Table(name = "mv_resultados_validaciones_servicios_digital")
@NamedQueries({
    @NamedQuery(name = "MvResultadosValidacionesServiciosDigital.findAll", query = "SELECT m FROM MvResultadosValidacionesServiciosDigital m"),
    @NamedQuery(name = "MvResultadosValidacionesServiciosDigital.findByIdResultadoValidacionServicioDigital", query = "SELECT m FROM MvResultadosValidacionesServiciosDigital m WHERE m.idResultadoValidacionServicioDigital = :idResultadoValidacionServicioDigital"),
    @NamedQuery(name = "MvResultadosValidacionesServiciosDigital.findByResultado", query = "SELECT m FROM MvResultadosValidacionesServiciosDigital m WHERE m.resultado = :resultado"),
    @NamedQuery(name = "MvResultadosValidacionesServiciosDigital.findByResultadoDataString", query = "SELECT m FROM MvResultadosValidacionesServiciosDigital m WHERE m.resultadoDataString = :resultadoDataString")})
public class MvResultadosValidacionesServiciosDigital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultado_validacion_servicio_digital")
    private Long idResultadoValidacionServicioDigital;
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

    public MvResultadosValidacionesServiciosDigital() {
    }

    public MvResultadosValidacionesServiciosDigital(Long idResultadoValidacionServicioDigital) {
        this.idResultadoValidacionServicioDigital = idResultadoValidacionServicioDigital;
    }

    public Long getIdResultadoValidacionServicioDigital() {
        return idResultadoValidacionServicioDigital;
    }

    public void setIdResultadoValidacionServicioDigital(Long idResultadoValidacionServicioDigital) {
        this.idResultadoValidacionServicioDigital = idResultadoValidacionServicioDigital;
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
        hash += (idResultadoValidacionServicioDigital != null ? idResultadoValidacionServicioDigital.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvResultadosValidacionesServiciosDigital)) {
            return false;
        }
        MvResultadosValidacionesServiciosDigital other = (MvResultadosValidacionesServiciosDigital) object;
        if ((this.idResultadoValidacionServicioDigital == null && other.idResultadoValidacionServicioDigital != null) || (this.idResultadoValidacionServicioDigital != null && !this.idResultadoValidacionServicioDigital.equals(other.idResultadoValidacionServicioDigital))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosDigital[ idResultadoValidacionServicioDigital=" + idResultadoValidacionServicioDigital + " ]";
    }
    
}
