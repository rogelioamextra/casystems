/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_folio")
@NamedQueries({
    @NamedQuery(name = "CatFolio.findAll", query = "SELECT c FROM CatFolio c"),
    @NamedQuery(name = "CatFolio.findByIdFolio", query = "SELECT c FROM CatFolio c WHERE c.idFolio = :idFolio"),
    @NamedQuery(name = "CatFolio.findByNombre", query = "SELECT c FROM CatFolio c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatFolio.findByFechaGeneracion", query = "SELECT c FROM CatFolio c WHERE c.fechaGeneracion = :fechaGeneracion"),
    @NamedQuery(name = "CatFolio.findByFechaVigencia", query = "SELECT c FROM CatFolio c WHERE c.fechaVigencia = :fechaVigencia"),
    @NamedQuery(name = "CatFolio.findByStatus", query = "SELECT c FROM CatFolio c WHERE c.status = :status"),
    @NamedQuery(name = "CatFolio.findByFolio", query = "SELECT c FROM CatFolio c WHERE c.folio = :folio"),
    @NamedQuery(name = "CatFolio.findByNoConsumoQr", query = "SELECT c FROM CatFolio c WHERE c.noConsumoQr = :noConsumoQr"),
    @NamedQuery(name = "CatFolio.findByContNoConsumoQr", query = "SELECT c FROM CatFolio c WHERE c.contNoConsumoQr = :contNoConsumoQr")})
public class CatFolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_folio")
    private Long idFolio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_generacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Column(name = "fecha_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVigencia;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "folio")
    private String folio;
    @Column(name = "no_consumo_qr")
    private Integer noConsumoQr;
    @Column(name = "cont_no_consumo_qr")
    private Integer contNoConsumoQr;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_productos")
    @ManyToOne
    private CatProductos idProducto;

    public CatFolio() {
    }

    public CatFolio(Long idFolio) {
        this.idFolio = idFolio;
    }

    public Long getIdFolio() {
        return idFolio;
    }

    public void setIdFolio(Long idFolio) {
        this.idFolio = idFolio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getNoConsumoQr() {
        return noConsumoQr;
    }

    public void setNoConsumoQr(Integer noConsumoQr) {
        this.noConsumoQr = noConsumoQr;
    }

    public Integer getContNoConsumoQr() {
        return contNoConsumoQr;
    }

    public void setContNoConsumoQr(Integer contNoConsumoQr) {
        this.contNoConsumoQr = contNoConsumoQr;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatProductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(CatProductos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFolio != null ? idFolio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatFolio)) {
            return false;
        }
        CatFolio other = (CatFolio) object;
        if ((this.idFolio == null && other.idFolio != null) || (this.idFolio != null && !this.idFolio.equals(other.idFolio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatFolio[ idFolio=" + idFolio + " ]";
    }
    
}
