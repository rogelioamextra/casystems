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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "mv_resultados_validaciones_documentos")
@NamedQueries({
    @NamedQuery(name = "MvResultadosValidacionesDocumentos.findAll", query = "SELECT m FROM MvResultadosValidacionesDocumentos m"),
    @NamedQuery(name = "MvResultadosValidacionesDocumentos.findByIdResultadosDocumentos", query = "SELECT m FROM MvResultadosValidacionesDocumentos m WHERE m.idResultadosDocumentos = :idResultadosDocumentos"),
    @NamedQuery(name = "MvResultadosValidacionesDocumentos.findByResultado", query = "SELECT m FROM MvResultadosValidacionesDocumentos m WHERE m.resultado = :resultado"),
    @NamedQuery(name = "MvResultadosValidacionesDocumentos.findByResultadoDataString", query = "SELECT m FROM MvResultadosValidacionesDocumentos m WHERE m.resultadoDataString = :resultadoDataString")})
public class MvResultadosValidacionesDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultados_documentos")
    private Long idResultadosDocumentos;
    @Basic(optional = false)
    @Column(name = "resultado")
    private boolean resultado;
    @Lob
    @Column(name = "resultado_data")
    private Object resultadoData;
    @Column(name = "resultado_data_string")
    private String resultadoDataString;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documentos")
    @ManyToOne(optional = false)
    private CatDocumentos idDocumento;
    @JoinColumn(name = "id_resultados_datos_validados", referencedColumnName = "id_datos_validados")
    @ManyToOne(optional = false)
    private DtResultadosDatosValidados idResultadosDatosValidados;

    public MvResultadosValidacionesDocumentos() {
    }

    public MvResultadosValidacionesDocumentos(Long idResultadosDocumentos) {
        this.idResultadosDocumentos = idResultadosDocumentos;
    }

    public MvResultadosValidacionesDocumentos(Long idResultadosDocumentos, boolean resultado) {
        this.idResultadosDocumentos = idResultadosDocumentos;
        this.resultado = resultado;
    }

    public Long getIdResultadosDocumentos() {
        return idResultadosDocumentos;
    }

    public void setIdResultadosDocumentos(Long idResultadosDocumentos) {
        this.idResultadosDocumentos = idResultadosDocumentos;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public Object getResultadoData() {
        return resultadoData;
    }

    public void setResultadoData(Object resultadoData) {
        this.resultadoData = resultadoData;
    }

    public String getResultadoDataString() {
        return resultadoDataString;
    }

    public void setResultadoDataString(String resultadoDataString) {
        this.resultadoDataString = resultadoDataString;
    }

    public CatDocumentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(CatDocumentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    public DtResultadosDatosValidados getIdResultadosDatosValidados() {
        return idResultadosDatosValidados;
    }

    public void setIdResultadosDatosValidados(DtResultadosDatosValidados idResultadosDatosValidados) {
        this.idResultadosDatosValidados = idResultadosDatosValidados;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadosDocumentos != null ? idResultadosDocumentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvResultadosValidacionesDocumentos)) {
            return false;
        }
        MvResultadosValidacionesDocumentos other = (MvResultadosValidacionesDocumentos) object;
        if ((this.idResultadosDocumentos == null && other.idResultadosDocumentos != null) || (this.idResultadosDocumentos != null && !this.idResultadosDocumentos.equals(other.idResultadosDocumentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.MvResultadosValidacionesDocumentos[ idResultadosDocumentos=" + idResultadosDocumentos + " ]";
    }
    
}
