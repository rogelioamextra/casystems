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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_validacion_ine_vs_lista_nominal")
@NamedQueries({
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findAll", query = "SELECT d FROM DtValidacionIneVsListaNominal d"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByIdValidacionIne", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.idValidacionIne = :idValidacionIne"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByMensaje", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.mensaje = :mensaje"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByClaveMensaje", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.claveMensaje = :claveMensaje"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByCodigoValidacion", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.codigoValidacion = :codigoValidacion"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByEstatus", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByCic", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.cic = :cic"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByClaveElector", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.claveElector = :claveElector"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByNumeroEmision", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.numeroEmision = :numeroEmision"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByDistritoFederal", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.distritoFederal = :distritoFederal"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByOcr", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.ocr = :ocr"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByAnioRegistro", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.anioRegistro = :anioRegistro"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByAnioEmision", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.anioEmision = :anioEmision"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByVigencia", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.vigencia = :vigencia"),
    @NamedQuery(name = "DtValidacionIneVsListaNominal.findByInformacionAdicional", query = "SELECT d FROM DtValidacionIneVsListaNominal d WHERE d.informacionAdicional = :informacionAdicional")})
public class DtValidacionIneVsListaNominal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_validacion_ine")
    private Long idValidacionIne;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "clave_mensaje")
    private String claveMensaje;
    @Column(name = "codigo_validacion")
    private String codigoValidacion;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "cic")
    private String cic;
    @Column(name = "clave_elector")
    private String claveElector;
    @Column(name = "numero_emision")
    private String numeroEmision;
    @Column(name = "distrito_federal")
    private String distritoFederal;
    @Column(name = "ocr")
    private String ocr;
    @Column(name = "anio_registro")
    private String anioRegistro;
    @Column(name = "anio_emision")
    private String anioEmision;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "informacion_adicional")
    private String informacionAdicional;

    public DtValidacionIneVsListaNominal() {
    }

    public DtValidacionIneVsListaNominal(Long idValidacionIne) {
        this.idValidacionIne = idValidacionIne;
    }

    public Long getIdValidacionIne() {
        return idValidacionIne;
    }

    public void setIdValidacionIne(Long idValidacionIne) {
        this.idValidacionIne = idValidacionIne;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getClaveMensaje() {
        return claveMensaje;
    }

    public void setClaveMensaje(String claveMensaje) {
        this.claveMensaje = claveMensaje;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCic() {
        return cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }

    public String getClaveElector() {
        return claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public String getNumeroEmision() {
        return numeroEmision;
    }

    public void setNumeroEmision(String numeroEmision) {
        this.numeroEmision = numeroEmision;
    }

    public String getDistritoFederal() {
        return distritoFederal;
    }

    public void setDistritoFederal(String distritoFederal) {
        this.distritoFederal = distritoFederal;
    }

    public String getOcr() {
        return ocr;
    }

    public void setOcr(String ocr) {
        this.ocr = ocr;
    }

    public String getAnioRegistro() {
        return anioRegistro;
    }

    public void setAnioRegistro(String anioRegistro) {
        this.anioRegistro = anioRegistro;
    }

    public String getAnioEmision() {
        return anioEmision;
    }

    public void setAnioEmision(String anioEmision) {
        this.anioEmision = anioEmision;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidacionIne != null ? idValidacionIne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtValidacionIneVsListaNominal)) {
            return false;
        }
        DtValidacionIneVsListaNominal other = (DtValidacionIneVsListaNominal) object;
        if ((this.idValidacionIne == null && other.idValidacionIne != null) || (this.idValidacionIne != null && !this.idValidacionIne.equals(other.idValidacionIne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtValidacionIneVsListaNominal[ idValidacionIne=" + idValidacionIne + " ]";
    }
    
}
