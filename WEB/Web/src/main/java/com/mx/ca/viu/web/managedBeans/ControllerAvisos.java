/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerAvisos")
@javax.faces.bean.ViewScoped
public class ControllerAvisos extends UtilServicios implements Serializable {

    private boolean banderaEdicion;
    private List<CatEmpresas> listaEmpresas;
    private CatEmpresas empresaSelect;
    private CatTiposAvisos tipoAvisoSelect;
    private CatEmpresas empresaUsuarioSesion;
    private List<MvConfigAvisos> listaConfiguracionAvisos;
    private List<CatTiposAvisos> listaTiposAvisos;
    private List<MvConfigAvisos> filtroConfiguracionAvisos;
    private MvConfigAvisos avisoNuevo;
    private boolean pintarCombo;
    private String texto;
    private String nombreArchivo;
    private InputStream file;
    private boolean pintarEditor;
    private Long empresa;
    private CatCategoriasCampos categoriaSelect;
    private List<CatCategoriasCampos> listaCategorias;
    private Date FechaMinima;

    public ControllerAvisos() {
    }

    @PostConstruct
    public void init() {
        avisoNuevo = new MvConfigAvisos();
        pintarCombo = false;
        categoriaSelect = null;
        file = null;
        FechaMinima = UtilGenerico.obtenerHoraMexico();
        pintarEditor = true;
        getAdministradorPaginas().setNombreArchivoDocumento("");
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
                listaTiposAvisos = catAvisosService.buscarTiposAvisos(false);
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    buscarTodos(false);
//                    pintarCombo = true;
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
                empresaSelect = empresaUsuarioSesion;
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if (!listaEmpresas.isEmpty()) {
//                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                buscarTodosXEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
                buscarCategoriasPorEmpresaFiltro();
//                }
            }
        }
    }

    public void buscarCategoriasPorEmpresaFiltro() {
        if (empresaSelect != null) {
            listaCategorias = catCamposService.buscarCategoriasMasViu(true, empresaSelect);
        }
    }

    public void buscarTodosXEmpresaFiltro() {
        filtroConfiguracionAvisos = null;
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            buscarTodos(false);

        } else {
            buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void guardar() {

        empresa = empresaUsuarioSesion.getIdEmpresas();

        if (catAvisosService.buscarNombre(avisoNuevo.getNombre(), avisoNuevo.getIdConfigAvisos(), empresa).isEmpty()) {
            // guardaArchivo();
            filtroConfiguracionAvisos = null;
            if (banderaEdicion) {
                editar();
            } else {

                avisoNuevo.setIdEmpresa(empresaUsuarioSesion);

                if (genericoService.guardar(avisoNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("avisos/bandejaAvisos.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            try {
                file = event.getFile().getInputStream();
                nombreArchivo = event.getFile().getFileName();
                getAdministradorPaginas().setNombreArchivoDocumento(nombreArchivo);
            } catch (IOException ex) {
                Logger.getLogger(ControllerEmpresas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void guardaArchivo() {
        if (file != null) {
            String empresa;
//            if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                empresa = empresaSelect.getNombre();
//            } else {
            empresa = empresaUsuarioSesion.getNombre();
//            }
            FTPService.uploadFiles(empresa, nombreArchivo, file);
        }
    }

    public void editar() {

        avisoNuevo.setIdEmpresa(empresaUsuarioSesion);

      
     

   
        if (genericoService.update(avisoNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("avisos/bandejaAvisos.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cancelar() {
        filtroConfiguracionAvisos = null;
        getAdministradorPaginas().setPagina("avisos/bandejaAvisos.xhtml");
        init();
        empresaSelect = new CatEmpresas();
    }

    public void buscarTodos(boolean activos) {
        listaConfiguracionAvisos = catAvisosService.buscarTodos(activos);
    }

    public void buscarTodosXEmpresa(boolean activos, Long id) {
        listaConfiguracionAvisos = catAvisosService.buscarTodosXidEmpres(activos, id);
    }

    public void cambiaPaginaNuevo() {
        pintarEditor = false;
        categoriaSelect = null;
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("avisos/avisos.xhtml");
        avisoNuevo = new MvConfigAvisos();
        texto = null;
        tipoAvisoSelect = null;
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
//        }
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
    }

    public void cambiaPaginaEdicion(MvConfigAvisos aviso) {
        banderaEdicion = true;
        this.avisoNuevo = aviso;
        empresaSelect = aviso.getIdEmpresa();
        texto = aviso.getAviso();


        getAdministradorPaginas().setPagina("avisos/avisos.xhtml");
    }

    public void validaEstatus() {
        if (avisoNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void validaEstatusDocumento() {
        if (avisoNuevo.getDocumento() == true) {
            pintarEditor = true;
        } else {
            pintarEditor = false;
        }
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatEmpresas> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<CatEmpresas> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public CatEmpresas getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(CatEmpresas empresaSelect) {
        this.empresaSelect = empresaSelect;
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

    public List<MvConfigAvisos> getListaConfiguracionAvisos() {
        return listaConfiguracionAvisos;
    }

    public void setListaConfiguracionAvisos(List<MvConfigAvisos> listaConfiguracionAvisos) {
        this.listaConfiguracionAvisos = listaConfiguracionAvisos;
    }

    public boolean isPintarCombo() {
        return pintarCombo;
    }

    public void setPintarCombo(boolean pintarCombo) {
        this.pintarCombo = pintarCombo;
    }

    public List<MvConfigAvisos> getFiltroConfiguracionAvisos() {
        return filtroConfiguracionAvisos;
    }

    public void setFiltroConfiguracionAvisos(List<MvConfigAvisos> filtroConfiguracionAvisos) {
        this.filtroConfiguracionAvisos = filtroConfiguracionAvisos;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<CatTiposAvisos> getListaTiposAvisos() {
        return listaTiposAvisos;
    }

    public void setListaTiposAvisos(List<CatTiposAvisos> listaTiposAvisos) {
        this.listaTiposAvisos = listaTiposAvisos;
    }

    public CatTiposAvisos getTipoAvisoSelect() {
        return tipoAvisoSelect;
    }

    public void setTipoAvisoSelect(CatTiposAvisos tipoAvisoSelect) {
        this.tipoAvisoSelect = tipoAvisoSelect;
    }

    public MvConfigAvisos getAvisoNuevo() {
        return avisoNuevo;
    }

    public void setAvisoNuevo(MvConfigAvisos avisoNuevo) {
        this.avisoNuevo = avisoNuevo;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean isPintarEditor() {
        return pintarEditor;
    }

    public void setPintarEditor(boolean pintarEditor) {
        this.pintarEditor = pintarEditor;
    }

    public CatCategoriasCampos getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(CatCategoriasCampos categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public List<CatCategoriasCampos> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CatCategoriasCampos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Date getFechaMinima() {
        return FechaMinima;
    }

    public void setFechaMinima(Date FechaMinima) {
        this.FechaMinima = FechaMinima;
    }

}
