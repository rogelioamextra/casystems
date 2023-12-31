/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_solicitudes_amextra")
@NamedQueries({
    @NamedQuery(name = "MvSolicitudesAmextra.findAll", query = "SELECT m FROM MvSolicitudesAmextra m"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByIdSolicitud", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByPlazo", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.plazo = :plazo"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByRevolvente", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.revolvente = :revolvente"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByFechaSolicitud", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByMonto", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.monto = :monto"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByFechaAprobacion", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByLongitud", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.longitud = :longitud"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByLatitud", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.latitud = :latitud"),
    @NamedQuery(name = "MvSolicitudesAmextra.findByJson", query = "SELECT m FROM MvSolicitudesAmextra m WHERE m.json = :json")})
public class MvSolicitudesAmextra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private Long idSolicitud;
    @Column(name = "plazo")
    private String plazo;
    @Column(name = "revolvente")
    private Boolean revolvente;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "monto")
    private String monto;
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @Column(name = "longitud")
    private String longitud;
    @Column(name = "latitud")
    private String latitud;
    @Column(name = "json")
    private String json;
    @OneToMany(mappedBy = "idSolicitud")
    private List<DtEgresos> dtEgresosList;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private CatClientes idCliente;
    @JoinColumn(name = "id_destino_credito", referencedColumnName = "id_destino_credito")
    @ManyToOne
    private CatDestinoCreditos idDestinoCredito;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private CatEstatus idEstatus;
    @JoinColumn(name = "id_frecuencia", referencedColumnName = "id_frecuencia_pago")
    @ManyToOne
    private CatFrecuenciaPago idFrecuencia;
    @JoinColumn(name = "id_producto_credito", referencedColumnName = "id_productos_credito")
    @ManyToOne
    private CatProductosCredito idProductoCredito;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_usuario")
    @ManyToOne
    private CatUsuarios idAsesor;
    @OneToMany(mappedBy = "idSolicitud")
    private List<DtIngresos> dtIngresosList;
    @OneToMany(mappedBy = "idSolicitud")
    private List<DtPatrimonio> dtPatrimonioList;

    public MvSolicitudesAmextra() {
    }

    public MvSolicitudesAmextra(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public Boolean getRevolvente() {
        return revolvente;
    }

    public void setRevolvente(Boolean revolvente) {
        this.revolvente = revolvente;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public List<DtEgresos> getDtEgresosList() {
        return dtEgresosList;
    }

    public void setDtEgresosList(List<DtEgresos> dtEgresosList) {
        this.dtEgresosList = dtEgresosList;
    }

    public CatClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(CatClientes idCliente) {
        this.idCliente = idCliente;
    }

    public CatDestinoCreditos getIdDestinoCredito() {
        return idDestinoCredito;
    }

    public void setIdDestinoCredito(CatDestinoCreditos idDestinoCredito) {
        this.idDestinoCredito = idDestinoCredito;
    }

    public CatEstatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(CatEstatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public CatFrecuenciaPago getIdFrecuencia() {
        return idFrecuencia;
    }

    public void setIdFrecuencia(CatFrecuenciaPago idFrecuencia) {
        this.idFrecuencia = idFrecuencia;
    }

    public CatProductosCredito getIdProductoCredito() {
        return idProductoCredito;
    }

    public void setIdProductoCredito(CatProductosCredito idProductoCredito) {
        this.idProductoCredito = idProductoCredito;
    }

    public CatUsuarios getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(CatUsuarios idAsesor) {
        this.idAsesor = idAsesor;
    }

    public List<DtIngresos> getDtIngresosList() {
        return dtIngresosList;
    }

    public void setDtIngresosList(List<DtIngresos> dtIngresosList) {
        this.dtIngresosList = dtIngresosList;
    }

    public List<DtPatrimonio> getDtPatrimonioList() {
        return dtPatrimonioList;
    }

    public void setDtPatrimonioList(List<DtPatrimonio> dtPatrimonioList) {
        this.dtPatrimonioList = dtPatrimonioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvSolicitudesAmextra)) {
            return false;
        }
        MvSolicitudesAmextra other = (MvSolicitudesAmextra) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvSolicitudesAmextra[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
