/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_config_riesgo")
@NamedQueries({
    @NamedQuery(name = "MvConfigRiesgo.findAll", query = "SELECT m FROM MvConfigRiesgo m"),
    @NamedQuery(name = "MvConfigRiesgo.findByIdConfigNivelRiesgo", query = "SELECT m FROM MvConfigRiesgo m WHERE m.idConfigNivelRiesgo = :idConfigNivelRiesgo"),
    @NamedQuery(name = "MvConfigRiesgo.findByResultado", query = "SELECT m FROM MvConfigRiesgo m WHERE m.resultado = :resultado"),
    @NamedQuery(name = "MvConfigRiesgo.findByStatus", query = "SELECT m FROM MvConfigRiesgo m WHERE m.status = :status"),
    @NamedQuery(name = "MvConfigRiesgo.findByDetenerProceso", query = "SELECT m FROM MvConfigRiesgo m WHERE m.detenerProceso = :detenerProceso")})
public class MvConfigRiesgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config_nivel_riesgo")
    private Long idConfigNivelRiesgo;
    @Basic(optional = false)
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "detener_proceso")
    private Boolean detenerProceso;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne(optional = false)
    private CatDocumentos idDocumento;
    @JoinColumn(name = "id_rango_vencimiento", referencedColumnName = "id_rango_vencimiento")
    @ManyToOne
    private CatRangosVencimiento idRangoVencimiento;
    @JoinColumn(name = "id_validacion", referencedColumnName = "id_validaciones")
    @ManyToOne(optional = false)
    private CatValidaciones idValidacion;
    @JoinColumn(name = "id_valor", referencedColumnName = "id_valor")
    @ManyToOne(optional = false)
    private CatValores idValor;
    @JoinColumn(name = "id_config_solicitud", referencedColumnName = "id_config_solicitud")
    @ManyToOne(optional = false)
    private MvConfigSolicitudes idConfigSolicitud;
    
     @Transient
    private int idRandom;
    
    @Transient
    private List<CatValidaciones> listaValidacionesNueva;
    
    @Transient
    private List<CatValores> listaValoresNueva;

    public MvConfigRiesgo() {
    }

    public MvConfigRiesgo(Long idConfigNivelRiesgo) {
        this.idConfigNivelRiesgo = idConfigNivelRiesgo;
    }

    public MvConfigRiesgo(Long idConfigNivelRiesgo, String resultado) {
        this.idConfigNivelRiesgo = idConfigNivelRiesgo;
        this.resultado = resultado;
    }

    public Long getIdConfigNivelRiesgo() {
        return idConfigNivelRiesgo;
    }

    public void setIdConfigNivelRiesgo(Long idConfigNivelRiesgo) {
        this.idConfigNivelRiesgo = idConfigNivelRiesgo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDetenerProceso() {
        return detenerProceso;
    }

    public void setDetenerProceso(Boolean detenerProceso) {
        this.detenerProceso = detenerProceso;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    public CatRangosVencimiento getIdRangoVencimiento() {
        return idRangoVencimiento;
    }

    public void setIdRangoVencimiento(CatRangosVencimiento idRangoVencimiento) {
        this.idRangoVencimiento = idRangoVencimiento;
    }

    public CatValidaciones getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(CatValidaciones idValidacion) {
        this.idValidacion = idValidacion;
    }

    public CatValores getIdValor() {
        return idValor;
    }

    public void setIdValor(CatValores idValor) {
        this.idValor = idValor;
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
        hash += (idConfigNivelRiesgo != null ? idConfigNivelRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvConfigRiesgo)) {
            return false;
        }
        MvConfigRiesgo other = (MvConfigRiesgo) object;
        if ((this.idConfigNivelRiesgo == null && other.idConfigNivelRiesgo != null) || (this.idConfigNivelRiesgo != null && !this.idConfigNivelRiesgo.equals(other.idConfigNivelRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvConfigRiesgo[ idConfigNivelRiesgo=" + idConfigNivelRiesgo + " ]";
    }

    public int getIdRandom() {
        return idRandom;
    }

    public void setIdRandom(int idRandom) {
        this.idRandom = idRandom;
    }

    public List<CatValidaciones> getListaValidacionesNueva() {
        return listaValidacionesNueva;
    }

    public void setListaValidacionesNueva(List<CatValidaciones> listaValidacionesNueva) {
        this.listaValidacionesNueva = listaValidacionesNueva;
    }

    public List<CatValores> getListaValoresNueva() {
        return listaValoresNueva;
    }

    public void setListaValoresNueva(List<CatValores> listaValoresNueva) {
        this.listaValoresNueva = listaValoresNueva;
    }
    
    
    
}
