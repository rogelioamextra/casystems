/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_campos")
@NamedQueries({
    @NamedQuery(name = "CatCampos.findAll", query = "SELECT c FROM CatCampos c"),
    @NamedQuery(name = "CatCampos.findByIdCampos", query = "SELECT c FROM CatCampos c WHERE c.idCampos = :idCampos"),
    @NamedQuery(name = "CatCampos.findByNombre", query = "SELECT c FROM CatCampos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatCampos.findByRequerido", query = "SELECT c FROM CatCampos c WHERE c.requerido = :requerido"),
    @NamedQuery(name = "CatCampos.findByStatus", query = "SELECT c FROM CatCampos c WHERE c.status = :status"),
    @NamedQuery(name = "CatCampos.findByCampoServicio", query = "SELECT c FROM CatCampos c WHERE c.campoServicio = :campoServicio")})
public class CatCampos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_campos")
    private Long idCampos;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "requerido")
    private Boolean requerido;
    @Column(name = "status")
    private Boolean status;
    @Lob
    @Column(name = "json")
    private Object json;
    @Column(name = "campo_servicio")
    private String campoServicio;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria_campo")
    @ManyToOne
    private CatCategoriasCampos idCategoria;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresas")
    @ManyToOne
    private CatEmpresas idEmpresa;
    @JoinColumn(name = "id_tipo_campo", referencedColumnName = "id_tipo")
    @ManyToOne
    private CatTipoCampo idTipoCampo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampo")
    private List<DatosSolicitudCampos> datosSolicitudCamposList;

    public CatCampos() {
    }

    public CatCampos(Long idCampos) {
        this.idCampos = idCampos;
    }

    public Long getIdCampos() {
        return idCampos;
    }

    public void setIdCampos(Long idCampos) {
        this.idCampos = idCampos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public String getCampoServicio() {
        return campoServicio;
    }

    public void setCampoServicio(String campoServicio) {
        this.campoServicio = campoServicio;
    }

    public CatCategoriasCampos getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatCategoriasCampos idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CatEmpresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(CatEmpresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public CatTipoCampo getIdTipoCampo() {
        return idTipoCampo;
    }

    public void setIdTipoCampo(CatTipoCampo idTipoCampo) {
        this.idTipoCampo = idTipoCampo;
    }

    public List<DatosSolicitudCampos> getDatosSolicitudCamposList() {
        return datosSolicitudCamposList;
    }

    public void setDatosSolicitudCamposList(List<DatosSolicitudCampos> datosSolicitudCamposList) {
        this.datosSolicitudCamposList = datosSolicitudCamposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampos != null ? idCampos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCampos)) {
            return false;
        }
        CatCampos other = (CatCampos) object;
        if ((this.idCampos == null && other.idCampos != null) || (this.idCampos != null && !this.idCampos.equals(other.idCampos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatCampos[ idCampos=" + idCampos + " ]";
    }
    
}
