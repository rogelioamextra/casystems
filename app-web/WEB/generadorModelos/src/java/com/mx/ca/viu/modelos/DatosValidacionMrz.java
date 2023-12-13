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
@Table(name = "datos_validacion_mrz")
@NamedQueries({
    @NamedQuery(name = "DatosValidacionMrz.findAll", query = "SELECT d FROM DatosValidacionMrz d"),
    @NamedQuery(name = "DatosValidacionMrz.findByIdDatosValidacionMrz", query = "SELECT d FROM DatosValidacionMrz d WHERE d.idDatosValidacionMrz = :idDatosValidacionMrz"),
    @NamedQuery(name = "DatosValidacionMrz.findByDigitoValidador", query = "SELECT d FROM DatosValidacionMrz d WHERE d.digitoValidador = :digitoValidador"),
    @NamedQuery(name = "DatosValidacionMrz.findByPais", query = "SELECT d FROM DatosValidacionMrz d WHERE d.pais = :pais"),
    @NamedQuery(name = "DatosValidacionMrz.findByNumeroPasaporte", query = "SELECT d FROM DatosValidacionMrz d WHERE d.numeroPasaporte = :numeroPasaporte"),
    @NamedQuery(name = "DatosValidacionMrz.findByFechaNacimiento", query = "SELECT d FROM DatosValidacionMrz d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DatosValidacionMrz.findBySexo", query = "SELECT d FROM DatosValidacionMrz d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "DatosValidacionMrz.findByVigencia", query = "SELECT d FROM DatosValidacionMrz d WHERE d.vigencia = :vigencia"),
    @NamedQuery(name = "DatosValidacionMrz.findByFechaCaducidad", query = "SELECT d FROM DatosValidacionMrz d WHERE d.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "DatosValidacionMrz.findByEmision", query = "SELECT d FROM DatosValidacionMrz d WHERE d.emision = :emision"),
    @NamedQuery(name = "DatosValidacionMrz.findByCic", query = "SELECT d FROM DatosValidacionMrz d WHERE d.cic = :cic"),
    @NamedQuery(name = "DatosValidacionMrz.findByNombre", query = "SELECT d FROM DatosValidacionMrz d WHERE d.nombre = :nombre")})
public class DatosValidacionMrz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_validacion_mrz")
    private Long idDatosValidacionMrz;
    @Column(name = "digito_validador")
    private String digitoValidador;
    @Column(name = "pais")
    private String pais;
    @Column(name = "numero_pasaporte")
    private String numeroPasaporte;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "fecha_caducidad")
    private String fechaCaducidad;
    @Column(name = "emision")
    private String emision;
    @Column(name = "cic")
    private String cic;
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_datos_identificacion", referencedColumnName = "id_datos_identificacion")
    @ManyToOne
    private DtDatosOcrIdentificacion idDatosIdentificacion;

    public DatosValidacionMrz() {
    }

    public DatosValidacionMrz(Long idDatosValidacionMrz) {
        this.idDatosValidacionMrz = idDatosValidacionMrz;
    }

    public Long getIdDatosValidacionMrz() {
        return idDatosValidacionMrz;
    }

    public void setIdDatosValidacionMrz(Long idDatosValidacionMrz) {
        this.idDatosValidacionMrz = idDatosValidacionMrz;
    }

    public String getDigitoValidador() {
        return digitoValidador;
    }

    public void setDigitoValidador(String digitoValidador) {
        this.digitoValidador = digitoValidador;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
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

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getEmision() {
        return emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }

    public String getCic() {
        return cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtDatosOcrIdentificacion getIdDatosIdentificacion() {
        return idDatosIdentificacion;
    }

    public void setIdDatosIdentificacion(DtDatosOcrIdentificacion idDatosIdentificacion) {
        this.idDatosIdentificacion = idDatosIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosValidacionMrz != null ? idDatosValidacionMrz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosValidacionMrz)) {
            return false;
        }
        DatosValidacionMrz other = (DatosValidacionMrz) object;
        if ((this.idDatosValidacionMrz == null && other.idDatosValidacionMrz != null) || (this.idDatosValidacionMrz != null && !this.idDatosValidacionMrz.equals(other.idDatosValidacionMrz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosValidacionMrz[ idDatosValidacionMrz=" + idDatosValidacionMrz + " ]";
    }
    
}
