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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "datos_result_consulta_lista_negra_peps")
@NamedQueries({
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findAll", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByIdResultado", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.idResultado = :idResultado"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByLista", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.lista = :lista"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByTipoLista", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.tipoLista = :tipoLista"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findBySimilitud", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.similitud = :similitud"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByNombreCompleto", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByAlias", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.alias = :alias"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByTaxId", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.taxId = :taxId"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByCurp", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.curp = :curp"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByActivo", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.activo = :activo"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByPais", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.pais = :pais"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByPosicion", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.posicion = :posicion"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByDependencia", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.dependencia = :dependencia"),
    @NamedQuery(name = "DatosResultConsultaListaNegraPeps.findByFechaNacimiento", query = "SELECT d FROM DatosResultConsultaListaNegraPeps d WHERE d.fechaNacimiento = :fechaNacimiento")})
public class DatosResultConsultaListaNegraPeps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultado")
    private Long idResultado;
    @Column(name = "lista")
    private String lista;
    @Column(name = "tipo_lista")
    private String tipoLista;
    @Column(name = "similitud")
    private String similitud;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "alias")
    private String alias;
    @Column(name = "tax_id")
    private String taxId;
    @Column(name = "curp")
    private String curp;
    @Column(name = "activo")
    private String activo;
    @Column(name = "pais")
    private String pais;
    @Column(name = "posicion")
    private String posicion;
    @Column(name = "dependencia")
    private String dependencia;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @JoinColumn(name = "id_consulta_peps_listanegra", referencedColumnName = "id_consulta_peps_listanegra")
    @ManyToOne
    private DtPepsListasNegras idConsultaPepsListanegra;

    public DatosResultConsultaListaNegraPeps() {
    }

    public DatosResultConsultaListaNegraPeps(Long idResultado) {
        this.idResultado = idResultado;
    }

    public Long getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Long idResultado) {
        this.idResultado = idResultado;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(String tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getSimilitud() {
        return similitud;
    }

    public void setSimilitud(String similitud) {
        this.similitud = similitud;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public DtPepsListasNegras getIdConsultaPepsListanegra() {
        return idConsultaPepsListanegra;
    }

    public void setIdConsultaPepsListanegra(DtPepsListasNegras idConsultaPepsListanegra) {
        this.idConsultaPepsListanegra = idConsultaPepsListanegra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultado != null ? idResultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosResultConsultaListaNegraPeps)) {
            return false;
        }
        DatosResultConsultaListaNegraPeps other = (DatosResultConsultaListaNegraPeps) object;
        if ((this.idResultado == null && other.idResultado != null) || (this.idResultado != null && !this.idResultado.equals(other.idResultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosResultConsultaListaNegraPeps[ idResultado=" + idResultado + " ]";
    }
    
}
