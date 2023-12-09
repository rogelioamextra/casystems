/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "cat_personas")
@NamedQueries({
    @NamedQuery(name = "CatPersonas.findAll", query = "SELECT c FROM CatPersonas c"),
    @NamedQuery(name = "CatPersonas.findByIdPersona", query = "SELECT c FROM CatPersonas c WHERE c.idPersona = :idPersona"),
    @NamedQuery(name = "CatPersonas.findByNombres", query = "SELECT c FROM CatPersonas c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "CatPersonas.findByApellidoPaterno", query = "SELECT c FROM CatPersonas c WHERE c.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "CatPersonas.findByApellidoMaterno", query = "SELECT c FROM CatPersonas c WHERE c.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "CatPersonas.findByEmail", query = "SELECT c FROM CatPersonas c WHERE c.email = :email"),
    @NamedQuery(name = "CatPersonas.findByTelefono", query = "SELECT c FROM CatPersonas c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "CatPersonas.findByCurp", query = "SELECT c FROM CatPersonas c WHERE c.curp = :curp"),
    @NamedQuery(name = "CatPersonas.findByGenero", query = "SELECT c FROM CatPersonas c WHERE c.genero = :genero"),
    @NamedQuery(name = "CatPersonas.findByFechaNacimiento", query = "SELECT c FROM CatPersonas c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "CatPersonas.findByDependientesEconomicos", query = "SELECT c FROM CatPersonas c WHERE c.dependientesEconomicos = :dependientesEconomicos"),
    @NamedQuery(name = "CatPersonas.findByRfc", query = "SELECT c FROM CatPersonas c WHERE c.rfc = :rfc")})
public class CatPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Long idPersona;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "curp")
    private String curp;
    @Column(name = "genero")
    private Short genero;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "dependientes_economicos")
    private Short dependientesEconomicos;
    @Column(name = "rfc")
    private String rfc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<CatClientes> catClientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<CatUsuarios> catUsuariosList;
    @JoinColumn(name = "lugar_nacimiento", referencedColumnName = "id_estado")
    @ManyToOne
    private CatEstados lugarNacimiento;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne
    private CatEstadosCiviles idEstadoCivil;
    @JoinColumn(name = "id_grado_maximo_estudios", referencedColumnName = "id_grado_estudios")
    @ManyToOne
    private CatGradoMaximoEstudios idGradoMaximoEstudios;
    @JoinColumn(name = "id_nacionalidad", referencedColumnName = "id_nacionalidad")
    @ManyToOne
    private CatNacionalidades idNacionalidad;

    public CatPersonas() {
    }

    public CatPersonas(Long idPersona) {
        this.idPersona = idPersona;
    }

    public CatPersonas(Long idPersona, String nombres, String apellidoPaterno, String apellidoMaterno, String email) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Short getGenero() {
        return genero;
    }

    public void setGenero(Short genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Short getDependientesEconomicos() {
        return dependientesEconomicos;
    }

    public void setDependientesEconomicos(Short dependientesEconomicos) {
        this.dependientesEconomicos = dependientesEconomicos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public List<CatClientes> getCatClientesList() {
        return catClientesList;
    }

    public void setCatClientesList(List<CatClientes> catClientesList) {
        this.catClientesList = catClientesList;
    }

    public List<CatUsuarios> getCatUsuariosList() {
        return catUsuariosList;
    }

    public void setCatUsuariosList(List<CatUsuarios> catUsuariosList) {
        this.catUsuariosList = catUsuariosList;
    }

    public CatEstados getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(CatEstados lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public CatEstadosCiviles getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(CatEstadosCiviles idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public CatGradoMaximoEstudios getIdGradoMaximoEstudios() {
        return idGradoMaximoEstudios;
    }

    public void setIdGradoMaximoEstudios(CatGradoMaximoEstudios idGradoMaximoEstudios) {
        this.idGradoMaximoEstudios = idGradoMaximoEstudios;
    }

    public CatNacionalidades getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(CatNacionalidades idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatPersonas)) {
            return false;
        }
        CatPersonas other = (CatPersonas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.CatPersonas[ idPersona=" + idPersona + " ]";
    }
    
}
