/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatColonias;
import com.mx.ca.viu.modelos.CatDestinoCreditos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatIdentificaciones;
import com.mx.ca.viu.modelos.CatMunicipios;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatParentescos;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatSucursales;
import com.mx.ca.viu.modelos.CatTiemposEmpleoActual;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposViviendas;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.modelos.DtEgresos;
import com.mx.ca.viu.modelos.DtIngresos;
import com.mx.ca.viu.modelos.DtPatrimonio;
import com.mx.ca.viu.modelos.DtReferenciasPersonales;
import com.mx.ca.viu.modelos.DtResultadosDatosValidados;
import com.mx.ca.viu.modelos.MvResultadosValidacionesDocumentos;
import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosAutoridades;
import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosBiometricos;
import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosDigital;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerTablero")
@javax.faces.bean.ViewScoped
public class ControllerTablero extends UtilServicios implements Serializable {

    private Date fechaInicio;
    private Date fechaFinal;
    private int recibidas, aprobadas, aprobadasPor, rechazadas, rechazadasPor, pendientes, pendientesPor;
    private List<MvSolicitudesAmextra> listaSolicitudes;
    private List<MvSolicitudesAmextra> filtro;
    private List<CatEstatus> listaEstatus;
    private List<CatNacionalidades> listaNacionalidades;
    private List<CatEstadosCiviles> listaEstadoCivil;
    private List<CatEstados> listaEstados;
    private List<CatMunicipios> listaMunicipios;
    private List<CatMunicipios> listaMunicipiosDatosLaborales;
    private List<CatMunicipios> listaMunicipiosRef;
    private List<CatColonias> listaColonias;
    private List<CatColonias> listaColoniasDatosLaborales;
    private List<CatColonias> listaColoniasRef;
    

    private List<CatIdentificaciones> listaIdentificaciones;
    private List<CatGradoMaximoEstudios> listaGradoMaximo;
    private List<CatTiposViviendas> listatipovivienda;
    private List<CatGirosNegociosEmpresas> listaGirosNegocio;
    private List<CatCaracteristicasNegocios> listacaracteristicasnegocio;
    private List<CatTiposResidencias> listatiporesidencia;
    private List<CatParentescos> listaParentescos;
    private List<CatProductosCredito> listaProductos;
    private List<CatDestinoCreditos> listaDestinosCredito;
    private List<CatFrecuenciaPago> listaFrecuenciasPago;
    private List<CatSucursales> listaSucursales;
    private List<CatUsuarios> listaAsesores;
    private List<CatTiemposEmpleoActual> listaTiempoEmpleoActual;
    private CatProductosCredito productosSelect;
    private CatEstatus estatusSelect;
    private MvSolicitudesAmextra solicitudEditar;
    private DtReferenciasPersonales referencia;
    private StreamedContent file;
    private MvSolicitudesAmextra cambioEstatus;
    private List<StreamedContent> streamListIne;
    private DtIngresos ingresosEditar;
    private DtEgresos egresosEditar;
    private DtComparacionFacial face;

    public ControllerTablero() {
    }

    @PostConstruct
    public void init() {
        fechaInicio = UtilGenerico.obtenerHoraMexico();
        fechaFinal = UtilGenerico.obtenerHoraMexico();
        listaSolicitudes = genericoService.findAll(MvSolicitudesAmextra.class);
        listaEstatus = genericoService.findAll(CatEstatus.class);
        listaProductos = genericoService.findAll(CatProductosCredito.class);
        conteo();
    }

    public void guaradarSolicitud() {

        if (egresosEditar != null) {
            solicitudEditar.getDtEgresosList().set(0, egresosEditar);
        }
        if (ingresosEditar != null) {
            solicitudEditar.getDtIngresosList().set(0, ingresosEditar);
        }

        if (genericoService.update(solicitudEditar)) {
            WebGenerico.menajeInformativo("Se guardaron los cambios correctamente");

        }
    }

    public void conteo() {
        recibidas = listaSolicitudes.size();
        aprobadas = listaSolicitudes.stream().filter(a -> a.getIdEstatus().getIdEstatus() == 1L).collect(Collectors.toList()).size();
        rechazadas = listaSolicitudes.stream().filter(a -> a.getIdEstatus().getIdEstatus() == 2L).collect(Collectors.toList()).size();
        pendientes = listaSolicitudes.stream().filter(a -> a.getIdEstatus().getIdEstatus() == 3L).collect(Collectors.toList()).size();
    }

    public void buscarImagenesIne() {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP("anv", obtenpropiedades(solicitudEditar.getIdCliente().getIdPersona().getCurp() + "/iden"));

        if (aux2 != null) {
            aux2 = UtilGenerico.encriptarDesencriptarBytes(aux2);
            InputStream anv = new ByteArrayInputStream(aux2);
            String nombreRemision = "anverso.jpg";
            StreamedContent streamedContent = DefaultStreamedContent.builder()
                    .name(nombreRemision)
                    .contentType("image/jpeg")
                    .stream(() -> anv)
                    .build();
            streamListIne.add(streamedContent);
        }
        byte aux[] = FTPService.dowloadFileSFTP("rev", obtenpropiedades(solicitudEditar.getIdCliente().getIdPersona().getCurp() + "/iden"));

        if (aux != null) {
            aux = UtilGenerico.encriptarDesencriptarBytes(aux);
            InputStream rev = new ByteArrayInputStream(aux);
            String nombreRemisionrev = "reverso.jpg";
            StreamedContent streamedContentrev = DefaultStreamedContent.builder()
                    .name(nombreRemisionrev)
                    .contentType("image/jpeg")
                    .stream(() -> rev)
                    .build();
            streamListIne.add(streamedContentrev);
        }

    }

    public void buscarImagenesIdentificacion() {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP(solicitudEditar.getIdCliente().getIdDtIdentificacion().getNombreImagen(),
                obtenpropiedades(solicitudEditar.getIdCliente().getIdPersona().getCurp() + "/iden"));

        if (aux2 != null) {
            aux2 = UtilGenerico.encriptarDesencriptarBytes(aux2);
            InputStream anv = new ByteArrayInputStream(aux2);
            String nombreRemision = "identificacion.jpg";
            StreamedContent streamedContent = DefaultStreamedContent.builder()
                    .name(nombreRemision)
                    .contentType("image/jpeg")
                    .stream(() -> anv)
                    .build();
            streamListIne.add(streamedContent);
        }

    }

    public void buscarImagenesSelfie() {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP("selfie",
                obtenpropiedades(solicitudEditar.getIdCliente().getIdPersona().getCurp() + "/sel"));

        if (aux2 != null) {
            aux2 = UtilGenerico.encriptarDesencriptarBytes(aux2);
            InputStream anv = new ByteArrayInputStream(aux2);
            String nombreRemision = "identificacion.jpg";
            StreamedContent streamedContent = DefaultStreamedContent.builder()
                    .name(nombreRemision)
                    .contentType("image/jpeg")
                    .stream(() -> anv)
                    .build();
            streamListIne.add(streamedContent);
        }

    }

    public void buscarImagenesComprobanteDomicilio() {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP(solicitudEditar.getIdCliente().getIdDireccion().getNombreArchivo(),
                obtenpropiedades(solicitudEditar.getIdCliente().getIdPersona().getCurp() + "/comDir"));

        if (aux2 != null) {
            aux2 = UtilGenerico.encriptarDesencriptarBytes(aux2);
            InputStream anv = new ByteArrayInputStream(aux2);
            String nombreRemision = "comprobante.jpg";
            StreamedContent streamedContent = DefaultStreamedContent.builder()
                    .name(nombreRemision)
                    .contentType("image/jpeg")
                    .stream(() -> anv)
                    .build();
            streamListIne.add(streamedContent);
        }

    }

    public void buscarImagenesPatrimonios(DtPatrimonio patri) {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP(patri.getNombreImagen(),
                obtenpropiedades(solicitudEditar.getIdCliente().getIdPersona().getCurp() + "/pat" + patri.getIdPatrimonio()));

        if (aux2 != null) {
            aux2 = UtilGenerico.encriptarDesencriptarBytes(aux2);
           // System.out.println(new String(org.apache.commons.codec.binary.Base64.encodeBase64(aux2)));
            InputStream anv = new ByteArrayInputStream(aux2);
            String nombreRemision = "patrimonios.jpg";
            StreamedContent streamedContent = DefaultStreamedContent.builder()
                    .name(nombreRemision)
                    .contentType("image/jpeg")
                    .stream(() -> anv)
                    .build();
            streamListIne.add(streamedContent);
        }
    }

    public void buscarTodos() {

        listaSolicitudes = solicitudService.buscarTodos(fechaInicio, fechaFinal, estatusSelect, productosSelect);

    }

    public void cambiaEstatus(MvSolicitudesAmextra sol) {
        if (sol.getIdEstatus().getIdEstatus() != 3L) {
            cambioEstatus = sol;
            PrimeFaces.current().executeInitScript("PF('dlgEstatus').show();");
        }

    }

    public void guardarCambioEstatus() {

        cambioEstatus.setFechaAprobacion(UtilGenerico.obtenerHoraMexico());
        if (genericoService.guardar(cambioEstatus)) {
            WebGenerico.menajeInformativo("La solicitud se guardo correctamente");
        }
    }

    public void generaProyeccion(MvSolicitudesAmextra sol) {
        boolean bandera = false;
        if (sol.getFechaAprobacion() != null && sol.getIdEstatus().getIdEstatus() == 1L) {
            bandera = true;
        }
        InputStream is = new ByteArrayInputStream(reporteService.generaReporte(sol, bandera));

        String nombreRemision = "PlanPagos.pdf";
        StreamedContent streamedContent = DefaultStreamedContent.builder()
                .name(nombreRemision)
                .contentType("application/pdf")
                .stream(() -> is)
                .build();

        this.file = streamedContent;
    }

    public void cambiaPaginaEdicion(MvSolicitudesAmextra solicitud) {
        solicitudEditar = solicitud;
        getAdministradorPaginas().setPagina("tablero/edicionSolicitud.xhtml");
        listaColonias = demograficosService.buscarCatLocalidad(solicitud.getIdCliente().getIdDireccion().getIdMunicipio().getIdMunicipio());
        listaColoniasDatosLaborales = demograficosService.buscarCatLocalidad(solicitud.getIdCliente().getIdDatosLaborales().getIdDireccion().getIdMunicipio().getIdMunicipio());
        listaMunicipios = demograficosService.buscarCatMunicipio(solicitud.getIdCliente().getIdDireccion().getIdEstado().getIdEstado());
        listaMunicipiosDatosLaborales = demograficosService.buscarCatMunicipio(solicitud.getIdCliente().getIdDatosLaborales().getIdDireccion().getIdEstado().getIdEstado());
        cargaCatalogos();
        if (solicitud.getDtIngresosList() != null && !solicitud.getDtIngresosList().isEmpty()) {
            ingresosEditar = solicitud.getDtIngresosList().get(0);
        }
        if (solicitud.getDtEgresosList() != null && !solicitud.getDtEgresosList().isEmpty()) {
            egresosEditar = solicitud.getDtEgresosList().get(0);
        }

    }

    private void cargaCatalogos() {
        listaNacionalidades = genericoService.findAll(CatNacionalidades.class);
        listaEstadoCivil = genericoService.findAll(CatEstadosCiviles.class);
        listaEstados = genericoService.findAll(CatEstados.class);
        listaGradoMaximo = genericoService.findAll(CatGradoMaximoEstudios.class);
        listaIdentificaciones = genericoService.findAll(CatIdentificaciones.class);

        listatiporesidencia = genericoService.findAll(CatTiposResidencias.class);
        listatipovivienda = genericoService.findAll(CatTiposViviendas.class);
        listaGirosNegocio = genericoService.findAll(CatGirosNegociosEmpresas.class);
        listacaracteristicasnegocio = genericoService.findAll(CatCaracteristicasNegocios.class);
        listaParentescos = genericoService.findAll(CatParentescos.class);
        listaProductos = genericoService.findAll(CatProductosCredito.class);
        listaDestinosCredito = genericoService.findAll(CatDestinoCreditos.class);
        listaFrecuenciasPago = genericoService.findAll(CatFrecuenciaPago.class);
        listaSucursales = genericoService.findAll(CatSucursales.class);
        listaAsesores = genericoService.findAll(CatUsuarios.class,true);
        face = solicitudServiceAmextra.obtenerFacematch(solicitudEditar.getIdCliente().getIdPersona().getCurp());
        listaTiempoEmpleoActual = genericoService.findAll(CatTiemposEmpleoActual.class);
    }

    private Map<String, String> obtenpropiedades(String path) {
        CatServiciosValidacionesExternos credenciales = servicios.buscarConfigFTP();
        Map<String, String> propiedades = new HashMap<>();
        propiedades.put("ftp.sender.user", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUser());
        propiedades.put("ftp.sender.password", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getPass());
        propiedades.put("ftp.sender.host", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUrl());
        propiedades.put("ftp.sender.port", "22");
        propiedades.put("ftp.sender.path", path);

        return propiedades;

    }

    public void regresar() {
         referencia = null;
        getAdministradorPaginas().setPagina("tablero/bandejaTablero.xhtml");

    }

    public void verificaReferencia(DtReferenciasPersonales ref) {
         listaColoniasRef= demograficosService.buscarCatLocalidad(ref.getIdDireccion().getIdMunicipio().getIdMunicipio());
        listaMunicipiosRef = demograficosService.buscarCatMunicipio(ref.getIdDireccion().getIdEstado().getIdEstado());
        referencia = ref;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getRecibidas() {
        return recibidas;
    }

    public void setRecibidas(int recibidas) {
        this.recibidas = recibidas;
    }

    public int getAprobadas() {
        return aprobadas;
    }

    public void setAprobadas(int aprobadas) {
        this.aprobadas = aprobadas;
    }

    public int getAprobadasPor() {
        return aprobadasPor;
    }

    public void setAprobadasPor(int aprobadasPor) {
        this.aprobadasPor = aprobadasPor;
    }

    public int getRechazadas() {
        return rechazadas;
    }

    public void setRechazadas(int rechazadas) {
        this.rechazadas = rechazadas;
    }

    public int getRechazadasPor() {
        return rechazadasPor;
    }

    public void setRechazadasPor(int rechazadasPor) {
        this.rechazadasPor = rechazadasPor;
    }

    public int getPendientes() {
        return pendientes;
    }

    public void setPendientes(int pendientes) {
        this.pendientes = pendientes;
    }

    public int getPendientesPor() {
        return pendientesPor;
    }

    public void setPendientesPor(int pendientesPor) {
        this.pendientesPor = pendientesPor;
    }

    public List<MvSolicitudesAmextra> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<MvSolicitudesAmextra> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public List<CatEstatus> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<CatEstatus> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public CatEstatus getEstatusSelect() {
        return estatusSelect;
    }

    public void setEstatusSelect(CatEstatus estatusSelect) {
        this.estatusSelect = estatusSelect;
    }

    public List<MvSolicitudesAmextra> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<MvSolicitudesAmextra> filtro) {
        this.filtro = filtro;
    }

    public CatProductosCredito getProductosSelect() {
        return productosSelect;
    }

    public void setProductosSelect(CatProductosCredito productosSelect) {
        this.productosSelect = productosSelect;
    }

    public MvSolicitudesAmextra getSolicitudEditar() {
        return solicitudEditar;
    }

    public void setSolicitudEditar(MvSolicitudesAmextra solicitudEditar) {
        this.solicitudEditar = solicitudEditar;
    }

    public List<CatNacionalidades> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<CatNacionalidades> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<CatEstadosCiviles> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public void setListaEstadoCivil(List<CatEstadosCiviles> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    public List<CatEstados> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<CatEstados> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<CatGradoMaximoEstudios> getListaGradoMaximo() {
        return listaGradoMaximo;
    }

    public void setListaGradoMaximo(List<CatGradoMaximoEstudios> listaGradoMaximo) {
        this.listaGradoMaximo = listaGradoMaximo;
    }

    public List<CatIdentificaciones> getListaIdentificaciones() {
        return listaIdentificaciones;
    }

    public void setListaIdentificaciones(List<CatIdentificaciones> listaIdentificaciones) {
        this.listaIdentificaciones = listaIdentificaciones;
    }

    public List<CatMunicipios> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<CatMunicipios> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List<CatColonias> getListaColonias() {
        return listaColonias;
    }

    public void setListaColonias(List<CatColonias> listaColonias) {
        this.listaColonias = listaColonias;
    }

    public List<CatTiposViviendas> getListatipovivienda() {
        return listatipovivienda;
    }

    public void setListatipovivienda(List<CatTiposViviendas> listatipovivienda) {
        this.listatipovivienda = listatipovivienda;
    }

    public List<CatTiposResidencias> getListatiporesidencia() {
        return listatiporesidencia;
    }

    public void setListatiporesidencia(List<CatTiposResidencias> listatiporesidencia) {
        this.listatiporesidencia = listatiporesidencia;
    }

    public List<CatGirosNegociosEmpresas> getListaGirosNegocio() {
        return listaGirosNegocio;
    }

    public void setListaGirosNegocio(List<CatGirosNegociosEmpresas> listaGirosNegocio) {
        this.listaGirosNegocio = listaGirosNegocio;
    }

    public List<CatCaracteristicasNegocios> getListacaracteristicasnegocio() {
        return listacaracteristicasnegocio;
    }

    public void setListacaracteristicasnegocio(List<CatCaracteristicasNegocios> listacaracteristicasnegocio) {
        this.listacaracteristicasnegocio = listacaracteristicasnegocio;
    }

    public DtReferenciasPersonales getReferencia() {
        return referencia;
    }

    public void setReferencia(DtReferenciasPersonales referencia) {
        this.referencia = referencia;
    }

    public List<CatParentescos> getListaParentescos() {
        return listaParentescos;
    }

    public void setListaParentescos(List<CatParentescos> listaParentescos) {
        this.listaParentescos = listaParentescos;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public MvSolicitudesAmextra getCambioEstatus() {
        return cambioEstatus;
    }

    public void setCambioEstatus(MvSolicitudesAmextra cambioEstatus) {
        this.cambioEstatus = cambioEstatus;
    }

    public List<CatProductosCredito> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<CatProductosCredito> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<CatDestinoCreditos> getListaDestinosCredito() {
        return listaDestinosCredito;
    }

    public void setListaDestinosCredito(List<CatDestinoCreditos> listaDestinosCredito) {
        this.listaDestinosCredito = listaDestinosCredito;
    }

    public List<CatFrecuenciaPago> getListaFrecuenciasPago() {
        return listaFrecuenciasPago;
    }

    public void setListaFrecuenciasPago(List<CatFrecuenciaPago> listaFrecuenciasPago) {
        this.listaFrecuenciasPago = listaFrecuenciasPago;
    }

    public List<StreamedContent> getStreamListIne() {
        return streamListIne;
    }

    public void setStreamListIne(List<StreamedContent> streamListIne) {
        this.streamListIne = streamListIne;
    }

    public DtIngresos getIngresosEditar() {
        return ingresosEditar;
    }

    public void setIngresosEditar(DtIngresos ingresosEditar) {
        this.ingresosEditar = ingresosEditar;
    }

    public DtEgresos getEgresosEditar() {
        return egresosEditar;
    }

    public void setEgresosEditar(DtEgresos egresosEditar) {
        this.egresosEditar = egresosEditar;
    }

    public List<CatSucursales> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<CatSucursales> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    public DtComparacionFacial getFace() {
        return face;
    }

    public void setFace(DtComparacionFacial face) {
        this.face = face;
    }

    public List<CatTiemposEmpleoActual> getListaTiempoEmpleoActual() {
        return listaTiempoEmpleoActual;
    }

    public void setListaTiempoEmpleoActual(List<CatTiemposEmpleoActual> listaTiempoEmpleoActual) {
        this.listaTiempoEmpleoActual = listaTiempoEmpleoActual;
    }

    public List<CatUsuarios> getListaAsesores() {
        return listaAsesores;
    }

    public void setListaAsesores(List<CatUsuarios> listaAsesores) {
        this.listaAsesores = listaAsesores;
    }

    public List<CatMunicipios> getListaMunicipiosDatosLaborales() {
        return listaMunicipiosDatosLaborales;
    }

    public void setListaMunicipiosDatosLaborales(List<CatMunicipios> listaMunicipiosDatosLaborales) {
        this.listaMunicipiosDatosLaborales = listaMunicipiosDatosLaborales;
    }

    public List<CatMunicipios> getListaMunicipiosRef() {
        return listaMunicipiosRef;
    }

    public void setListaMunicipiosRef(List<CatMunicipios> listaMunicipiosRef) {
        this.listaMunicipiosRef = listaMunicipiosRef;
    }

    public List<CatColonias> getListaColoniasDatosLaborales() {
        return listaColoniasDatosLaborales;
    }

    public void setListaColoniasDatosLaborales(List<CatColonias> listaColoniasDatosLaborales) {
        this.listaColoniasDatosLaborales = listaColoniasDatosLaborales;
    }

    public List<CatColonias> getListaColoniasRef() {
        return listaColoniasRef;
    }

    public void setListaColoniasRef(List<CatColonias> listaColoniasRef) {
        this.listaColoniasRef = listaColoniasRef;
    }


    
    
    
    
}
