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
@Table(name = "dt_datos_ocr_identificacion")
@NamedQueries({
    @NamedQuery(name = "DtDatosOcrIdentificacion.findAll", query = "SELECT d FROM DtDatosOcrIdentificacion d"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByIdDatosIdentificacion", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.idDatosIdentificacion = :idDatosIdentificacion"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByEstatus", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByMensaje", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.mensaje = :mensaje"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByPrimerApellido", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.primerApellido = :primerApellido"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findBySegundoApellido", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByApellido", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.apellido = :apellido"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByNombres", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.nombres = :nombres"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByCalle", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.calle = :calle"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByColonia", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.colonia = :colonia"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByCiudad", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.ciudad = :ciudad"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByEdad", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.edad = :edad"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByFechaNacimiento", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findBySexo", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByFolio", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.folio = :folio"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByRegistro", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.registro = :registro"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByNumeroPasaporte", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.numeroPasaporte = :numeroPasaporte"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByClaveElector", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.claveElector = :claveElector"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByCurp", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.curp = :curp"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByEstado", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.estado = :estado"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByLugarNacimiento", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByMunicipio", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.municipio = :municipio"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByDistrito", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.distrito = :distrito"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByLocalidad", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.localidad = :localidad"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findBySeccion", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.seccion = :seccion"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByEmision", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.emision = :emision"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByVigencia", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.vigencia = :vigencia"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByTipo", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByFechaExpedicion", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.fechaExpedicion = :fechaExpedicion"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByFechaCaducidad", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findBySubtipo", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.subtipo = :subtipo"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByOcr", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.ocr = :ocr"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByCic", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.cic = :cic"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByIdentificadorCiudadano", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.identificadorCiudadano = :identificadorCiudadano"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByQr", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.qr = :qr"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByCodigoBarras", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.codigoBarras = :codigoBarras"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByMrz", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.mrz = :mrz"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByCodigoValidacion", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.codigoValidacion = :codigoValidacion"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByPais", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.pais = :pais"),
    @NamedQuery(name = "DtDatosOcrIdentificacion.findByNombre", query = "SELECT d FROM DtDatosOcrIdentificacion d WHERE d.nombre = :nombre")})
public class DtDatosOcrIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos_identificacion")
    private Long idDatosIdentificacion;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "calle")
    private String calle;
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "edad")
    private String edad;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "folio")
    private String folio;
    @Column(name = "registro")
    private String registro;
    @Column(name = "numero_pasaporte")
    private String numeroPasaporte;
    @Column(name = "clave_elector")
    private String claveElector;
    @Column(name = "curp")
    private String curp;
    @Column(name = "estado")
    private String estado;
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "distrito")
    private String distrito;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "seccion")
    private String seccion;
    @Column(name = "emision")
    private String emision;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fecha_expedicion")
    private String fechaExpedicion;
    @Column(name = "fecha_caducidad")
    private String fechaCaducidad;
    @Column(name = "subtipo")
    private String subtipo;
    @Column(name = "ocr")
    private String ocr;
    @Column(name = "cic")
    private String cic;
    @Column(name = "identificador_ciudadano")
    private String identificadorCiudadano;
    @Column(name = "qr")
    private String qr;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "mrz")
    private String mrz;
    @Column(name = "codigo_validacion")
    private String codigoValidacion;
    @Column(name = "pais")
    private String pais;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idDatosIdentificacion")
    private List<DatosValidacionMrz> datosValidacionMrzList;

    public DtDatosOcrIdentificacion() {
    }

    public DtDatosOcrIdentificacion(Long idDatosIdentificacion) {
        this.idDatosIdentificacion = idDatosIdentificacion;
    }

    public Long getIdDatosIdentificacion() {
        return idDatosIdentificacion;
    }

    public void setIdDatosIdentificacion(Long idDatosIdentificacion) {
        this.idDatosIdentificacion = idDatosIdentificacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
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

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public String getClaveElector() {
        return claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getEmision() {
        return emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getOcr() {
        return ocr;
    }

    public void setOcr(String ocr) {
        this.ocr = ocr;
    }

    public String getCic() {
        return cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }

    public String getIdentificadorCiudadano() {
        return identificadorCiudadano;
    }

    public void setIdentificadorCiudadano(String identificadorCiudadano) {
        this.identificadorCiudadano = identificadorCiudadano;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getMrz() {
        return mrz;
    }

    public void setMrz(String mrz) {
        this.mrz = mrz;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DatosValidacionMrz> getDatosValidacionMrzList() {
        return datosValidacionMrzList;
    }

    public void setDatosValidacionMrzList(List<DatosValidacionMrz> datosValidacionMrzList) {
        this.datosValidacionMrzList = datosValidacionMrzList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosIdentificacion != null ? idDatosIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDatosOcrIdentificacion)) {
            return false;
        }
        DtDatosOcrIdentificacion other = (DtDatosOcrIdentificacion) object;
        if ((this.idDatosIdentificacion == null && other.idDatosIdentificacion != null) || (this.idDatosIdentificacion != null && !this.idDatosIdentificacion.equals(other.idDatosIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtDatosOcrIdentificacion[ idDatosIdentificacion=" + idDatosIdentificacion + " ]";
    }
    
}
