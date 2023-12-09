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
@Table(name = "cat_lista_negra_amextra")
@NamedQueries({
    @NamedQuery(name = "CatListaNegraAmextra.findAll", query = "SELECT c FROM CatListaNegraAmextra c"),
    @NamedQuery(name = "CatListaNegraAmextra.findByCodEmpresa", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "CatListaNegraAmextra.findByCodAgencia", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.codAgencia = :codAgencia"),
    @NamedQuery(name = "CatListaNegraAmextra.findByCodCliente", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.codCliente = :codCliente"),
    @NamedQuery(name = "CatListaNegraAmextra.findByConsecutivo", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.consecutivo = :consecutivo"),
    @NamedQuery(name = "CatListaNegraAmextra.findByDesAgencia", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.desAgencia = :desAgencia"),
    @NamedQuery(name = "CatListaNegraAmextra.findByNombres", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "CatListaNegraAmextra.findByApellidoPaterno", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "CatListaNegraAmextra.findByApellidoMaterno", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "CatListaNegraAmextra.findByFechaNacimiento", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "CatListaNegraAmextra.findBySexo", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.sexo = :sexo"),
    @NamedQuery(name = "CatListaNegraAmextra.findByRfc", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.rfc = :rfc"),
    @NamedQuery(name = "CatListaNegraAmextra.findByCurp", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.curp = :curp"),
    @NamedQuery(name = "CatListaNegraAmextra.findByTipoId", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.tipoId = :tipoId"),
    @NamedQuery(name = "CatListaNegraAmextra.findByNoIdentificacion", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.noIdentificacion = :noIdentificacion"),
    @NamedQuery(name = "CatListaNegraAmextra.findByTelefono", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "CatListaNegraAmextra.findByCelular", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.celular = :celular"),
    @NamedQuery(name = "CatListaNegraAmextra.findByDescripcionMotivo", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.descripcionMotivo = :descripcionMotivo"),
    @NamedQuery(name = "CatListaNegraAmextra.findByObservaciones", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CatListaNegraAmextra.findByIdListaNegra", query = "SELECT c FROM CatListaNegraAmextra c WHERE c.idListaNegra = :idListaNegra")})
public class CatListaNegraAmextra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "cod_empresa")
    private String codEmpresa;
    @Column(name = "cod_agencia")
    private String codAgencia;
    @Column(name = "cod_cliente")
    private String codCliente;
    @Column(name = "consecutivo")
    private Integer consecutivo;
    @Column(name = "des_agencia")
    private String desAgencia;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "curp")
    private String curp;
    @Column(name = "tipo_id")
    private String tipoId;
    @Column(name = "no_identificacion")
    private String noIdentificacion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "celular")
    private String celular;
    @Column(name = "descripcion_motivo")
    private String descripcionMotivo;
    @Column(name = "observaciones")
    private String observaciones;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lista_negra")
    private Long idListaNegra;

    public CatListaNegraAmextra() {
    }

    public CatListaNegraAmextra(Long idListaNegra) {
        this.idListaNegra = idListaNegra;
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

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getDesAgencia() {
        return desAgencia;
    }

    public void setDesAgencia(String desAgencia) {
        this.desAgencia = desAgencia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDescripcionMotivo() {
        return descripcionMotivo;
    }

    public void setDescripcionMotivo(String descripcionMotivo) {
        this.descripcionMotivo = descripcionMotivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdListaNegra() {
        return idListaNegra;
    }

    public void setIdListaNegra(Long idListaNegra) {
        this.idListaNegra = idListaNegra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idListaNegra != null ? idListaNegra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatListaNegraAmextra)) {
            return false;
        }
        CatListaNegraAmextra other = (CatListaNegraAmextra) object;
        if ((this.idListaNegra == null && other.idListaNegra != null) || (this.idListaNegra != null && !this.idListaNegra.equals(other.idListaNegra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatListaNegraAmextra[ idListaNegra=" + idListaNegra + " ]";
    }
    
}
