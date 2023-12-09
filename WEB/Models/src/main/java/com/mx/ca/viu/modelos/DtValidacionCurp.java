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
@Table(name = "dt_validacion_curp")
@NamedQueries({
    @NamedQuery(name = "DtValidacionCurp.findAll", query = "SELECT d FROM DtValidacionCurp d"),
    @NamedQuery(name = "DtValidacionCurp.findByIdValidacionCurp", query = "SELECT d FROM DtValidacionCurp d WHERE d.idValidacionCurp = :idValidacionCurp"),
    @NamedQuery(name = "DtValidacionCurp.findByEstatus", query = "SELECT d FROM DtValidacionCurp d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DtValidacionCurp.findByCodigoValidacion", query = "SELECT d FROM DtValidacionCurp d WHERE d.codigoValidacion = :codigoValidacion"),
    @NamedQuery(name = "DtValidacionCurp.findByCurp", query = "SELECT d FROM DtValidacionCurp d WHERE d.curp = :curp"),
    @NamedQuery(name = "DtValidacionCurp.findByNombre", query = "SELECT d FROM DtValidacionCurp d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DtValidacionCurp.findByApellidoPaterno", query = "SELECT d FROM DtValidacionCurp d WHERE d.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "DtValidacionCurp.findByApellidoMaterno", query = "SELECT d FROM DtValidacionCurp d WHERE d.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "DtValidacionCurp.findBySexo", query = "SELECT d FROM DtValidacionCurp d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "DtValidacionCurp.findByFechaNacimiento", query = "SELECT d FROM DtValidacionCurp d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DtValidacionCurp.findByPaisNacimiento", query = "SELECT d FROM DtValidacionCurp d WHERE d.paisNacimiento = :paisNacimiento"),
    @NamedQuery(name = "DtValidacionCurp.findByEstadoNacimiento", query = "SELECT d FROM DtValidacionCurp d WHERE d.estadoNacimiento = :estadoNacimiento"),
    @NamedQuery(name = "DtValidacionCurp.findByDocProbatorio", query = "SELECT d FROM DtValidacionCurp d WHERE d.docProbatorio = :docProbatorio"),
    @NamedQuery(name = "DtValidacionCurp.findByEstatusCurp", query = "SELECT d FROM DtValidacionCurp d WHERE d.estatusCurp = :estatusCurp"),
    @NamedQuery(name = "DtValidacionCurp.findByCodigoMensaje", query = "SELECT d FROM DtValidacionCurp d WHERE d.codigoMensaje = :codigoMensaje"),
    @NamedQuery(name = "DtValidacionCurp.findByDocumento", query = "SELECT d FROM DtValidacionCurp d WHERE d.documento = :documento"),
    @NamedQuery(name = "DtValidacionCurp.findByMensaje", query = "SELECT d FROM DtValidacionCurp d WHERE d.mensaje = :mensaje")})
public class DtValidacionCurp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_validacion_curp")
    private Long idValidacionCurp;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "codigo_validacion")
    private String codigoValidacion;
    @Column(name = "curp")
    private String curp;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Column(name = "pais_nacimiento")
    private String paisNacimiento;
    @Column(name = "estado_nacimiento")
    private String estadoNacimiento;
    @Column(name = "doc_probatorio")
    private Short docProbatorio;
    @Column(name = "estatus_curp")
    private String estatusCurp;
    @Column(name = "codigo_mensaje")
    private String codigoMensaje;
    @Column(name = "documento")
    private String documento;
    @Column(name = "mensaje")
    private String mensaje;
    @OneToMany(mappedBy = "idValidacionCurp")
    private List<DatosDocProbatorio> datosDocProbatorioList;

    public DtValidacionCurp() {
    }

    public DtValidacionCurp(Long idValidacionCurp) {
        this.idValidacionCurp = idValidacionCurp;
    }

    public Long getIdValidacionCurp() {
        return idValidacionCurp;
    }

    public void setIdValidacionCurp(Long idValidacionCurp) {
        this.idValidacionCurp = idValidacionCurp;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getEstadoNacimiento() {
        return estadoNacimiento;
    }

    public void setEstadoNacimiento(String estadoNacimiento) {
        this.estadoNacimiento = estadoNacimiento;
    }

    public Short getDocProbatorio() {
        return docProbatorio;
    }

    public void setDocProbatorio(Short docProbatorio) {
        this.docProbatorio = docProbatorio;
    }

    public String getEstatusCurp() {
        return estatusCurp;
    }

    public void setEstatusCurp(String estatusCurp) {
        this.estatusCurp = estatusCurp;
    }

    public String getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<DatosDocProbatorio> getDatosDocProbatorioList() {
        return datosDocProbatorioList;
    }

    public void setDatosDocProbatorioList(List<DatosDocProbatorio> datosDocProbatorioList) {
        this.datosDocProbatorioList = datosDocProbatorioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidacionCurp != null ? idValidacionCurp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtValidacionCurp)) {
            return false;
        }
        DtValidacionCurp other = (DtValidacionCurp) object;
        if ((this.idValidacionCurp == null && other.idValidacionCurp != null) || (this.idValidacionCurp != null && !this.idValidacionCurp.equals(other.idValidacionCurp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtValidacionCurp[ idValidacionCurp=" + idValidacionCurp + " ]";
    }
    
}
