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
@Table(name = "cat_agenda")
@NamedQueries({
    @NamedQuery(name = "CatAgenda.findAll", query = "SELECT c FROM CatAgenda c"),
    @NamedQuery(name = "CatAgenda.findByIdAgenda", query = "SELECT c FROM CatAgenda c WHERE c.idAgenda = :idAgenda"),
    @NamedQuery(name = "CatAgenda.findByCodEmpresa", query = "SELECT c FROM CatAgenda c WHERE c.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "CatAgenda.findByCodAgencia", query = "SELECT c FROM CatAgenda c WHERE c.codAgencia = :codAgencia"),
    @NamedQuery(name = "CatAgenda.findByCodAsesor", query = "SELECT c FROM CatAgenda c WHERE c.codAsesor = :codAsesor"),
    @NamedQuery(name = "CatAgenda.findByCodCliente", query = "SELECT c FROM CatAgenda c WHERE c.codCliente = :codCliente"),
    @NamedQuery(name = "CatAgenda.findByCodGrupo", query = "SELECT c FROM CatAgenda c WHERE c.codGrupo = :codGrupo"),
    @NamedQuery(name = "CatAgenda.findByFecha", query = "SELECT c FROM CatAgenda c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CatAgenda.findByEstatus", query = "SELECT c FROM CatAgenda c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "CatAgenda.findByComentario", query = "SELECT c FROM CatAgenda c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "CatAgenda.findByLatitud", query = "SELECT c FROM CatAgenda c WHERE c.latitud = :latitud"),
    @NamedQuery(name = "CatAgenda.findByLongitud", query = "SELECT c FROM CatAgenda c WHERE c.longitud = :longitud")})
public class CatAgenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agenda")
    private Long idAgenda;
    @Column(name = "cod_empresa")
    private String codEmpresa;
    @Column(name = "cod_agencia")
    private String codAgencia;
    @Column(name = "cod_asesor")
    private String codAsesor;
    @Column(name = "cod_cliente")
    private String codCliente;
    @Column(name = "cod_grupo")
    private String codGrupo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "estatus")
    private Short estatus;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "latitud")
    private String latitud;
    @Column(name = "longitud")
    private String longitud;

    public CatAgenda() {
    }

    public CatAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
    }

    public String getCodAsesor() {
        return codAsesor;
    }

    public void setCodAsesor(String codAsesor) {
        this.codAsesor = codAsesor;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getEstatus() {
        return estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenda != null ? idAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatAgenda)) {
            return false;
        }
        CatAgenda other = (CatAgenda) object;
        if ((this.idAgenda == null && other.idAgenda != null) || (this.idAgenda != null && !this.idAgenda.equals(other.idAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatAgenda[ idAgenda=" + idAgenda + " ]";
    }
    
}
