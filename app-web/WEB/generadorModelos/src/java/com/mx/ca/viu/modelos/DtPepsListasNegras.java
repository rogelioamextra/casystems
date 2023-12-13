/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_peps_listas_negras")
@NamedQueries({
    @NamedQuery(name = "DtPepsListasNegras.findAll", query = "SELECT d FROM DtPepsListasNegras d"),
    @NamedQuery(name = "DtPepsListasNegras.findByIdConsultaPepsListanegra", query = "SELECT d FROM DtPepsListasNegras d WHERE d.idConsultaPepsListanegra = :idConsultaPepsListanegra"),
    @NamedQuery(name = "DtPepsListasNegras.findByEstatus", query = "SELECT d FROM DtPepsListasNegras d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DtPepsListasNegras.findByClaveMensaje", query = "SELECT d FROM DtPepsListasNegras d WHERE d.claveMensaje = :claveMensaje"),
    @NamedQuery(name = "DtPepsListasNegras.findByCodigoValidacion", query = "SELECT d FROM DtPepsListasNegras d WHERE d.codigoValidacion = :codigoValidacion"),
    @NamedQuery(name = "DtPepsListasNegras.findByConteoResultados", query = "SELECT d FROM DtPepsListasNegras d WHERE d.conteoResultados = :conteoResultados"),
    @NamedQuery(name = "DtPepsListasNegras.findByMensaje", query = "SELECT d FROM DtPepsListasNegras d WHERE d.mensaje = :mensaje")})
public class DtPepsListasNegras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_consulta_peps_listanegra")
    private Long idConsultaPepsListanegra;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "clave_mensaje")
    private Short claveMensaje;
    @Column(name = "codigo_validacion")
    private String codigoValidacion;
    @Column(name = "conteo_resultados")
    private BigInteger conteoResultados;
    @Column(name = "mensaje")
    private String mensaje;
    @OneToMany(mappedBy = "idConsultaPepsListanegra")
    private List<DatosResultConsultaListaNegraPeps> datosResultConsultaListaNegraPepsList;

    public DtPepsListasNegras() {
    }

    public DtPepsListasNegras(Long idConsultaPepsListanegra) {
        this.idConsultaPepsListanegra = idConsultaPepsListanegra;
    }

    public Long getIdConsultaPepsListanegra() {
        return idConsultaPepsListanegra;
    }

    public void setIdConsultaPepsListanegra(Long idConsultaPepsListanegra) {
        this.idConsultaPepsListanegra = idConsultaPepsListanegra;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Short getClaveMensaje() {
        return claveMensaje;
    }

    public void setClaveMensaje(Short claveMensaje) {
        this.claveMensaje = claveMensaje;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public BigInteger getConteoResultados() {
        return conteoResultados;
    }

    public void setConteoResultados(BigInteger conteoResultados) {
        this.conteoResultados = conteoResultados;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<DatosResultConsultaListaNegraPeps> getDatosResultConsultaListaNegraPepsList() {
        return datosResultConsultaListaNegraPepsList;
    }

    public void setDatosResultConsultaListaNegraPepsList(List<DatosResultConsultaListaNegraPeps> datosResultConsultaListaNegraPepsList) {
        this.datosResultConsultaListaNegraPepsList = datosResultConsultaListaNegraPepsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultaPepsListanegra != null ? idConsultaPepsListanegra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtPepsListasNegras)) {
            return false;
        }
        DtPepsListasNegras other = (DtPepsListasNegras) object;
        if ((this.idConsultaPepsListanegra == null && other.idConsultaPepsListanegra != null) || (this.idConsultaPepsListanegra != null && !this.idConsultaPepsListanegra.equals(other.idConsultaPepsListanegra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtPepsListasNegras[ idConsultaPepsListanegra=" + idConsultaPepsListanegra + " ]";
    }
    
}
