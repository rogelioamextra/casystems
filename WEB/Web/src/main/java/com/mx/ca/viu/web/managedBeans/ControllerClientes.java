/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatColonias;
import com.mx.ca.viu.modelos.CatDestinoCreditos;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatIdentificaciones;
import com.mx.ca.viu.modelos.CatMunicipios;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatParentescos;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatSucursales;
import com.mx.ca.viu.modelos.CatTiemposEmpleoActual;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposViviendas;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.modelos.DtReferenciasPersonales;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author jbecerril
 */
@ManagedBean(name = "controllerClientes")
@javax.faces.bean.ViewScoped
public class ControllerClientes extends UtilServicios implements Serializable, GenericoCatalogosBeans {

    private CatClientes nuevo;
    private List<CatClientes> listaClientes;
    private List<CatClientes> filtroNacionalidades;
    private boolean banderaEdicion;
    private List<CatNacionalidades> listaNacionalidades;
    private List<CatEstadosCiviles> listaEstadoCivil;
    private List<CatEstados> listaEstados;
    private List<CatMunicipios> listaMunicipiosDireccion;
    private List<CatMunicipios> listaMunicipiosLaboral;
    private List<CatMunicipios> listaMunicipiosRef1;
    private List<CatMunicipios> listaMunicipiosRef2;
    private List<CatColonias> listaColoniasDireccion;
    private List<CatColonias> listaColoniasLAboral;
    private List<CatColonias> listaColoniasRef1;
    private List<CatColonias> listaColoniasRef2;

    private List<CatIdentificaciones> listaIdentificaciones;
    private List<CatGradoMaximoEstudios> listaGradoMaximo;
    private List<CatTiposViviendas> listatipovivienda;
    private List<CatTiposResidencias> listatiporesidencia;
    private List<StreamedContent> streamListIne;
    private List<CatGirosNegociosEmpresas> listaGirosNegocio;
    private List<CatCaracteristicasNegocios> listaCaracteristicaNegocios;
    private List<CatTiemposEmpleoActual> listaTiemposEmpleoActaul;
    private List<CatSucursales> listaSucursales;
    private List<CatUsuarios> listaAsesores;
    private DtComparacionFacial face;
    private DtReferenciasPersonales referencia;
    private List<CatParentescos> listaParentescos;

    @PostConstruct
    public void init() {
        listaClientes = genericoService.findAll(CatClientes.class);

        if (getAdministradorPaginas().getPagina().equals("catalogos/clientes/clientes.xhtml")) {
            nuevo = getAdministradorPaginas().getClienteRespaldo();
            cargaCatalogos();
        }
    }

    @Override
    public void editar() {
        if (genericoService.update(nuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/clientes/bandeja.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

        }

    }

    public boolean verificarIdentificacion() {

        return nuevo.getIdDtIdentificacion().getIdTipoIdentificacion().getIdIdentificaciones() == 1L;
    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatClientes) obj;

        cargaCatalogos();
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/clientes/clientes.xhtml");
        getAdministradorPaginas().setClienteRespaldo(nuevo);

    }

    @Override
    public void cancelar() {
        filtroNacionalidades = null;
        getAdministradorPaginas().setPagina("catalogos/clientes/bandeja.xhtml");
        getAdministradorPaginas().setClienteRespaldo(null);
        init();
    }

    @Override
    public void cambiaPaginaNuevo() {
        getAdministradorPaginas().setPagina("catalogos/clientes/clientes.xhtml");
        banderaEdicion = false;
        nuevo = new CatClientes();
    }

    @Override
    public void guardar() {

        if (banderaEdicion) {
            editar();
        } else {
            if (genericoService.guardar(nuevo)) {
                getAdministradorPaginas().setPagina("catalogos/clientes/bandeja.xhtml");
                init();
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        }
        getAdministradorPaginas().setClienteRespaldo(null);

    }

    private void cargaCatalogos() {
        listaNacionalidades = genericoService.findAll(CatNacionalidades.class);
        listaEstadoCivil = genericoService.findAll(CatEstadosCiviles.class);
        listaEstados = genericoService.findAll(CatEstados.class);
        listaGradoMaximo = genericoService.findAll(CatGradoMaximoEstudios.class);
        listaIdentificaciones = genericoService.findAll(CatIdentificaciones.class);
        listaGirosNegocio = genericoService.findAll(CatGirosNegociosEmpresas.class);

        listatiporesidencia = genericoService.findAll(CatTiposResidencias.class);
        listatipovivienda = genericoService.findAll(CatTiposViviendas.class);
        listaColoniasDireccion = demograficosService.buscarCatLocalidad(nuevo.getIdDireccion().getIdMunicipio().getIdMunicipio());
        listaColoniasLAboral = demograficosService.buscarCatLocalidad(nuevo.getIdDatosLaborales().getIdDireccion().getIdMunicipio().getIdMunicipio());

        listaMunicipiosDireccion = demograficosService.buscarCatMunicipio(nuevo.getIdDireccion().getIdEstado().getIdEstado());
        listaMunicipiosLaboral = demograficosService.buscarCatMunicipio(nuevo.getIdDatosLaborales().getIdDireccion().getIdEstado().getIdEstado());

        listaCaracteristicaNegocios = genericoService.findAll(CatCaracteristicasNegocios.class);
        listaTiemposEmpleoActaul = genericoService.findAll(CatTiemposEmpleoActual.class);
        listaSucursales = genericoService.findAll(CatSucursales.class);
        listaAsesores = genericoService.findAll(CatUsuarios.class, true);
        listaParentescos = genericoService.findAll(CatParentescos.class);

        face = solicitudServiceAmextra.obtenerFacematch(nuevo.getIdPersona().getCurp());

    }

    public CatClientes getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatClientes nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatClientes> getFiltroNacionalidades() {
        return filtroNacionalidades;
    }

    public void setFiltroNacionalidades(List<CatClientes> filtroNacionalidades) {
        this.filtroNacionalidades = filtroNacionalidades;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    @Override
    public void validaEstatus() {
        if (nuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    @Override
    public void confirmaEstatus(boolean estatus) {
        nuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void buscarImagenesIne() {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP("anv", obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"));

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
        byte aux[] = FTPService.dowloadFileSFTP("rev", obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"));

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
        byte aux2[] = FTPService.dowloadFileSFTP(nuevo.getIdDtIdentificacion().getNombreImagen(),
                obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"));

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
        byte aux2[] = FTPService.dowloadFileSFTP(nuevo.getIdDireccion().getNombreArchivo(),
                obtenpropiedades(nuevo.getIdPersona().getCurp() + "/comDir"));

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

    public void buscarImagenesSelfie() {
        streamListIne = new ArrayList<>();
        byte aux2[] = FTPService.dowloadFileSFTP("selfie",
                obtenpropiedades(nuevo.getIdPersona().getCurp() + "/sel"));

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

    public void verificaReferencia(DtReferenciasPersonales ref) {
        listaMunicipiosRef1 = demograficosService.buscarCatMunicipio(ref.getIdDireccion().getIdEstado().getIdEstado());
        listaColoniasRef1 = demograficosService.buscarCatLocalidad(ref.getIdDireccion().getIdMunicipio().getIdMunicipio());
        referencia = ref;
    }

    public List<CatClientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<CatClientes> listaClientes) {
        this.listaClientes = listaClientes;
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

    public List<CatMunicipios> getListaMunicipiosDireccion() {
        return listaMunicipiosDireccion;
    }

    public void setListaMunicipiosDireccion(List<CatMunicipios> listaMunicipiosDireccion) {
        this.listaMunicipiosDireccion = listaMunicipiosDireccion;
    }

    public List<CatMunicipios> getListaMunicipiosLaboral() {
        return listaMunicipiosLaboral;
    }

    public void setListaMunicipiosLaboral(List<CatMunicipios> listaMunicipiosLaboral) {
        this.listaMunicipiosLaboral = listaMunicipiosLaboral;
    }

    public List<CatMunicipios> getListaMunicipiosRef1() {
        return listaMunicipiosRef1;
    }

    public void setListaMunicipiosRef1(List<CatMunicipios> listaMunicipiosRef1) {
        this.listaMunicipiosRef1 = listaMunicipiosRef1;
    }

    public List<CatMunicipios> getListaMunicipiosRef2() {
        return listaMunicipiosRef2;
    }

    public void setListaMunicipiosRef2(List<CatMunicipios> listaMunicipiosRef2) {
        this.listaMunicipiosRef2 = listaMunicipiosRef2;
    }

    public List<CatColonias> getListaColoniasDireccion() {
        return listaColoniasDireccion;
    }

    public void setListaColoniasDireccion(List<CatColonias> listaColoniasDireccion) {
        this.listaColoniasDireccion = listaColoniasDireccion;
    }

    public List<CatColonias> getListaColoniasLAboral() {
        return listaColoniasLAboral;
    }

    public void setListaColoniasLAboral(List<CatColonias> listaColoniasLAboral) {
        this.listaColoniasLAboral = listaColoniasLAboral;
    }

    public List<CatColonias> getListaColoniasRef1() {
        return listaColoniasRef1;
    }

    public void setListaColoniasRef1(List<CatColonias> listaColoniasRef1) {
        this.listaColoniasRef1 = listaColoniasRef1;
    }

    public List<CatColonias> getListaColoniasRef2() {
        return listaColoniasRef2;
    }

    public void setListaColoniasRef2(List<CatColonias> listaColoniasRef2) {
        this.listaColoniasRef2 = listaColoniasRef2;
    }

    public List<CatIdentificaciones> getListaIdentificaciones() {
        return listaIdentificaciones;
    }

    public void setListaIdentificaciones(List<CatIdentificaciones> listaIdentificaciones) {
        this.listaIdentificaciones = listaIdentificaciones;
    }

    public List<CatGradoMaximoEstudios> getListaGradoMaximo() {
        return listaGradoMaximo;
    }

    public void setListaGradoMaximo(List<CatGradoMaximoEstudios> listaGradoMaximo) {
        this.listaGradoMaximo = listaGradoMaximo;
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

    public List<CatEstados> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<CatEstados> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<StreamedContent> getStreamListIne() {
        return streamListIne;
    }

    public void setStreamListIne(List<StreamedContent> streamListIne) {
        this.streamListIne = streamListIne;
    }

    public List<CatGirosNegociosEmpresas> getListaGirosNegocio() {
        return listaGirosNegocio;
    }

    public void setListaGirosNegocio(List<CatGirosNegociosEmpresas> listaGirosNegocio) {
        this.listaGirosNegocio = listaGirosNegocio;
    }

    public List<CatCaracteristicasNegocios> getListaCaracteristicaNegocios() {
        return listaCaracteristicaNegocios;
    }

    public void setListaCaracteristicaNegocios(List<CatCaracteristicasNegocios> listaCaracteristicaNegocios) {
        this.listaCaracteristicaNegocios = listaCaracteristicaNegocios;
    }

    public List<CatTiemposEmpleoActual> getListaTiemposEmpleoActaul() {
        return listaTiemposEmpleoActaul;
    }

    public void setListaTiemposEmpleoActaul(List<CatTiemposEmpleoActual> listaTiemposEmpleoActaul) {
        this.listaTiemposEmpleoActaul = listaTiemposEmpleoActaul;
    }

    public List<CatSucursales> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<CatSucursales> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    public List<CatUsuarios> getListaAsesores() {
        return listaAsesores;
    }

    public void setListaAsesores(List<CatUsuarios> listaAsesores) {
        this.listaAsesores = listaAsesores;
    }

    public DtComparacionFacial getFace() {
        return face;
    }

    public void setFace(DtComparacionFacial face) {
        this.face = face;
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

}
