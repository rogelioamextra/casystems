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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_datos_comprobante_dom")
@NamedQueries({
    @NamedQuery(name = "DtDatosComprobanteDom.findAll", query = "SELECT d FROM DtDatosComprobanteDom d"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByIdDatosComprob", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.idDatosComprob = :idDatosComprob"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByNombre", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCalle", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.calle = :calle"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByReferencia", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.referencia = :referencia"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByColonia", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.colonia = :colonia"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCiudad", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.ciudad = :ciudad"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByFecha", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByTotalPagar", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.totalPagar = :totalPagar"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByNumeroServicio", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.numeroServicio = :numeroServicio"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByRmu", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.rmu = :rmu"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByRmu2", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.rmu2 = :rmu2"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCodigoBarras", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.codigoBarras = :codigoBarras"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByTarifa", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.tarifa = :tarifa"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByTotalPagar2", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.totalPagar2 = :totalPagar2"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByMesFacturacion", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.mesFacturacion = :mesFacturacion"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByMesFacturacion2", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.mesFacturacion2 = :mesFacturacion2"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCodigoBarras2", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.codigoBarras2 = :codigoBarras2"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCp", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.cp = :cp"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByPagarAntesDe", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.pagarAntesDe = :pagarAntesDe"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByPagarAntesDe2", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.pagarAntesDe2 = :pagarAntesDe2"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByTelefono", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByTelefono2", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.telefono2 = :telefono2"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByNumeroFactura", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCodigoValidacion", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.codigoValidacion = :codigoValidacion"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByEstatus", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByClaveMensaje", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.claveMensaje = :claveMensaje"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByCodigoNumero", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.codigoNumero = :codigoNumero"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByQr", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.qr = :qr"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByMensaje", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.mensaje = :mensaje"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByAdeudoanterior", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.adeudoanterior = :adeudoanterior"),
    @NamedQuery(name = "DtDatosComprobanteDom.findByPeriodoFacturado", query = "SELECT d FROM DtDatosComprobanteDom d WHERE d.periodoFacturado = :periodoFacturado")})
public class DtDatosComprobanteDom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_comprob")
    private Long idDatosComprob;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "calle")
    private String calle;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "total_pagar")
    private String totalPagar;
    @Column(name = "numero_servicio")
    private String numeroServicio;
    @Column(name = "rmu")
    private String rmu;
    @Column(name = "rmu2")
    private String rmu2;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "tarifa")
    private String tarifa;
    @Column(name = "total_pagar2")
    private String totalPagar2;
    @Column(name = "mes_facturacion")
    private String mesFacturacion;
    @Column(name = "mes_facturacion2")
    private String mesFacturacion2;
    @Column(name = "codigo_barras2")
    private String codigoBarras2;
    @Column(name = "cp")
    private String cp;
    @Column(name = "pagar_antes_de")
    private String pagarAntesDe;
    @Column(name = "pagar_antes_de2")
    private String pagarAntesDe2;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "telefono2")
    private String telefono2;
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Column(name = "codigo_validacion")
    private String codigoValidacion;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "clave_mensaje")
    private String claveMensaje;
    @Column(name = "codigo_numero")
    private String codigoNumero;
    @Column(name = "qr")
    private String qr;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "adeudoanterior")
    private String adeudoanterior;
    @Column(name = "periodo_facturado")
    private String periodoFacturado;
    @OneToMany(mappedBy = "idDatosComprob")
    private List<DatosComprobanteExtras> datosComprobanteExtrasList;

    public DtDatosComprobanteDom() {
    }

    public DtDatosComprobanteDom(Long idDatosComprob) {
        this.idDatosComprob = idDatosComprob;
    }

    public Long getIdDatosComprob() {
        return idDatosComprob;
    }

    public void setIdDatosComprob(Long idDatosComprob) {
        this.idDatosComprob = idDatosComprob;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(String numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public String getRmu() {
        return rmu;
    }

    public void setRmu(String rmu) {
        this.rmu = rmu;
    }

    public String getRmu2() {
        return rmu2;
    }

    public void setRmu2(String rmu2) {
        this.rmu2 = rmu2;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getTotalPagar2() {
        return totalPagar2;
    }

    public void setTotalPagar2(String totalPagar2) {
        this.totalPagar2 = totalPagar2;
    }

    public String getMesFacturacion() {
        return mesFacturacion;
    }

    public void setMesFacturacion(String mesFacturacion) {
        this.mesFacturacion = mesFacturacion;
    }

    public String getMesFacturacion2() {
        return mesFacturacion2;
    }

    public void setMesFacturacion2(String mesFacturacion2) {
        this.mesFacturacion2 = mesFacturacion2;
    }

    public String getCodigoBarras2() {
        return codigoBarras2;
    }

    public void setCodigoBarras2(String codigoBarras2) {
        this.codigoBarras2 = codigoBarras2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPagarAntesDe() {
        return pagarAntesDe;
    }

    public void setPagarAntesDe(String pagarAntesDe) {
        this.pagarAntesDe = pagarAntesDe;
    }

    public String getPagarAntesDe2() {
        return pagarAntesDe2;
    }

    public void setPagarAntesDe2(String pagarAntesDe2) {
        this.pagarAntesDe2 = pagarAntesDe2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
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

    public String getClaveMensaje() {
        return claveMensaje;
    }

    public void setClaveMensaje(String claveMensaje) {
        this.claveMensaje = claveMensaje;
    }

    public String getCodigoNumero() {
        return codigoNumero;
    }

    public void setCodigoNumero(String codigoNumero) {
        this.codigoNumero = codigoNumero;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAdeudoanterior() {
        return adeudoanterior;
    }

    public void setAdeudoanterior(String adeudoanterior) {
        this.adeudoanterior = adeudoanterior;
    }

    public String getPeriodoFacturado() {
        return periodoFacturado;
    }

    public void setPeriodoFacturado(String periodoFacturado) {
        this.periodoFacturado = periodoFacturado;
    }

    public List<DatosComprobanteExtras> getDatosComprobanteExtrasList() {
        return datosComprobanteExtrasList;
    }

    public void setDatosComprobanteExtrasList(List<DatosComprobanteExtras> datosComprobanteExtrasList) {
        this.datosComprobanteExtrasList = datosComprobanteExtrasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosComprob != null ? idDatosComprob.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDatosComprobanteDom)) {
            return false;
        }
        DtDatosComprobanteDom other = (DtDatosComprobanteDom) object;
        if ((this.idDatosComprob == null && other.idDatosComprob != null) || (this.idDatosComprob != null && !this.idDatosComprob.equals(other.idDatosComprob))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtDatosComprobanteDom[ idDatosComprob=" + idDatosComprob + " ]";
    }
    
}
