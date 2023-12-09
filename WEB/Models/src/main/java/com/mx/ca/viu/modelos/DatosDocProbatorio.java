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
@Table(name = "datos_doc_probatorio")
@NamedQueries({
    @NamedQuery(name = "DatosDocProbatorio.findAll", query = "SELECT d FROM DatosDocProbatorio d"),
    @NamedQuery(name = "DatosDocProbatorio.findByIdDatosDocProbatorio", query = "SELECT d FROM DatosDocProbatorio d WHERE d.idDatosDocProbatorio = :idDatosDocProbatorio"),
    @NamedQuery(name = "DatosDocProbatorio.findByAnioReg", query = "SELECT d FROM DatosDocProbatorio d WHERE d.anioReg = :anioReg"),
    @NamedQuery(name = "DatosDocProbatorio.findByClaveEntidadRegistro", query = "SELECT d FROM DatosDocProbatorio d WHERE d.claveEntidadRegistro = :claveEntidadRegistro"),
    @NamedQuery(name = "DatosDocProbatorio.findByEntidadRegistro", query = "SELECT d FROM DatosDocProbatorio d WHERE d.entidadRegistro = :entidadRegistro"),
    @NamedQuery(name = "DatosDocProbatorio.findByTomo", query = "SELECT d FROM DatosDocProbatorio d WHERE d.tomo = :tomo"),
    @NamedQuery(name = "DatosDocProbatorio.findByFoja", query = "SELECT d FROM DatosDocProbatorio d WHERE d.foja = :foja"),
    @NamedQuery(name = "DatosDocProbatorio.findByNumActa", query = "SELECT d FROM DatosDocProbatorio d WHERE d.numActa = :numActa"),
    @NamedQuery(name = "DatosDocProbatorio.findByLibro", query = "SELECT d FROM DatosDocProbatorio d WHERE d.libro = :libro"),
    @NamedQuery(name = "DatosDocProbatorio.findByClaveMunicipioRegistro", query = "SELECT d FROM DatosDocProbatorio d WHERE d.claveMunicipioRegistro = :claveMunicipioRegistro"),
    @NamedQuery(name = "DatosDocProbatorio.findByMunicReg", query = "SELECT d FROM DatosDocProbatorio d WHERE d.municReg = :municReg"),
    @NamedQuery(name = "DatosDocProbatorio.findByNumRegExt", query = "SELECT d FROM DatosDocProbatorio d WHERE d.numRegExt = :numRegExt"),
    @NamedQuery(name = "DatosDocProbatorio.findByFolio", query = "SELECT d FROM DatosDocProbatorio d WHERE d.folio = :folio")})
public class DatosDocProbatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_doc_probatorio")
    private Long idDatosDocProbatorio;
    @Column(name = "anio_reg")
    private String anioReg;
    @Column(name = "clave_entidad_registro")
    private String claveEntidadRegistro;
    @Column(name = "entidad_registro")
    private String entidadRegistro;
    @Column(name = "tomo")
    private String tomo;
    @Column(name = "foja")
    private String foja;
    @Column(name = "num_acta")
    private String numActa;
    @Column(name = "libro")
    private String libro;
    @Column(name = "clave_municipio_registro")
    private String claveMunicipioRegistro;
    @Column(name = "munic_reg")
    private String municReg;
    @Column(name = "num_reg_ext")
    private String numRegExt;
    @Column(name = "folio")
    private String folio;
    @JoinColumn(name = "id_validacion_curp", referencedColumnName = "id_validacion_curp")
    @ManyToOne
    private DtValidacionCurp idValidacionCurp;

    public DatosDocProbatorio() {
    }

    public DatosDocProbatorio(Long idDatosDocProbatorio) {
        this.idDatosDocProbatorio = idDatosDocProbatorio;
    }

    public Long getIdDatosDocProbatorio() {
        return idDatosDocProbatorio;
    }

    public void setIdDatosDocProbatorio(Long idDatosDocProbatorio) {
        this.idDatosDocProbatorio = idDatosDocProbatorio;
    }

    public String getAnioReg() {
        return anioReg;
    }

    public void setAnioReg(String anioReg) {
        this.anioReg = anioReg;
    }

    public String getClaveEntidadRegistro() {
        return claveEntidadRegistro;
    }

    public void setClaveEntidadRegistro(String claveEntidadRegistro) {
        this.claveEntidadRegistro = claveEntidadRegistro;
    }

    public String getEntidadRegistro() {
        return entidadRegistro;
    }

    public void setEntidadRegistro(String entidadRegistro) {
        this.entidadRegistro = entidadRegistro;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public String getFoja() {
        return foja;
    }

    public void setFoja(String foja) {
        this.foja = foja;
    }

    public String getNumActa() {
        return numActa;
    }

    public void setNumActa(String numActa) {
        this.numActa = numActa;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getClaveMunicipioRegistro() {
        return claveMunicipioRegistro;
    }

    public void setClaveMunicipioRegistro(String claveMunicipioRegistro) {
        this.claveMunicipioRegistro = claveMunicipioRegistro;
    }

    public String getMunicReg() {
        return municReg;
    }

    public void setMunicReg(String municReg) {
        this.municReg = municReg;
    }

    public String getNumRegExt() {
        return numRegExt;
    }

    public void setNumRegExt(String numRegExt) {
        this.numRegExt = numRegExt;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public DtValidacionCurp getIdValidacionCurp() {
        return idValidacionCurp;
    }

    public void setIdValidacionCurp(DtValidacionCurp idValidacionCurp) {
        this.idValidacionCurp = idValidacionCurp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosDocProbatorio != null ? idDatosDocProbatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosDocProbatorio)) {
            return false;
        }
        DatosDocProbatorio other = (DatosDocProbatorio) object;
        if ((this.idDatosDocProbatorio == null && other.idDatosDocProbatorio != null) || (this.idDatosDocProbatorio != null && !this.idDatosDocProbatorio.equals(other.idDatosDocProbatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DatosDocProbatorio[ idDatosDocProbatorio=" + idDatosDocProbatorio + " ]";
    }
    
}
