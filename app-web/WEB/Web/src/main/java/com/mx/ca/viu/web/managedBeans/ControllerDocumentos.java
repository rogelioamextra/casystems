/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatTiposDocumentos;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import com.mx.ca.viu.modelos.MvDocumentosTipos;

import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerDocumentos")
@javax.faces.bean.ViewScoped
public class ControllerDocumentos extends UtilServicios implements Serializable {

    private CatDocumentos documentoNuevo;
    private boolean banderaEdicion;
    private List<CatDocumentos> listaDocumentos;
    private List<CatDocumentos> filtroDocumentos;
    private boolean pintarCombo;
    private List<CatEmpresas> listaEmpresas;
    private DualListModel<CatValidaciones> listaValidaciones;
    private CatEmpresas empresaSelect;
    private CatCategoriasCampos categoriaSelect;
    private CatTiposDocumentos tipoDocumentoSelect;
    private CatEmpresas empresaUsuarioSesion;
    private boolean banderaDisable;
    private DtConfiguracionValidaciones validaciones;
    private List<CatCategoriasCampos> listaCategorias;
    private List<CatTiposDocumentos> listaTipoDocumento;
    private MvDocumentosCategorias documentoCategoria;
    private MvDocumentosTipos documentoTipo;
    private Long empresa;
    public ControllerDocumentos() {
    }

    @PostConstruct
    public void init() {
        documentoNuevo = new CatDocumentos();
        pintarCombo = false;

        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    buscarTodosDocumentos(false);
                buscarTodosDocumentosXEmpresa(empresaUsuarioSesion.getIdEmpresas());
//                    pintarCombo = true;
//                    listaEmpresas = llenarCombo(listaEmpresas);
                    listaEmpresas = new ArrayList<>();
                    listaEmpresas.add(empresaUsuarioSesion);
//                    if(!listaEmpresas.isEmpty()){
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosDocumentosXEmpresa(empresaUsuarioSesion.getIdEmpresas());
//                }
                
            }
        }

    }

    public void buscarTodosDocumentos(boolean activos) {
        listaDocumentos = catDocumentosService.buscarTodos(activos);
    }

    public void buscarTodosDocumentosXEmpresa(Long id) {
        listaDocumentos = catDocumentosService.buscarTodosXidEmpresa(id);
    }

    public void buscarTodosDocumentosXEmpresaFiltro() {
//        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
//            buscarTodosDocumentos(false);
//        } else{
            listaDocumentos = catDocumentosService.buscarTodosXidEmpres(empresaSelect.getIdEmpresas());
//        }
    }
    
    public boolean disable(CatDocumentos documentos) {
        banderaDisable = false;
        if (Objects.equals(documentos.getIdEmpresa().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            banderaDisable = !Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas());
        }
        return banderaDisable;
    }
    
    public void buscarDocumentosInterno(){
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosDocumentos(false);
        } else {
            buscarTodosDocumentosXEmpresa(empresaUsuarioSesion.getIdEmpresas());
        }
    }
    

    public void guardar() {
        filtroDocumentos = null;
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }
        if (catDocumentosService.buscarNombre(documentoNuevo.getNombre(), documentoNuevo.getIdDocumentos(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
                llenadoDocumentosValidaciones();
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    documentoNuevo.setIdEmpresa(empresaSelect);
//                } else {
                    documentoNuevo.setIdEmpresa(empresaUsuarioSesion);
//                }
                if (genericoService.guardar(documentoNuevo)) {
                    if(documentoNuevo.getDtConfiguracionValidacionesList()!=null){
                        for (int j = 0; j < documentoNuevo.getDtConfiguracionValidacionesList().size(); j++) {
                            genericoService.guardar(documentoNuevo.getDtConfiguracionValidacionesList().get(j));
                        }
                    }
                    MvDocumentosCategorias n = new MvDocumentosCategorias();
                    n.setIdDocumentos(documentoNuevo);
                    n.setIdCategoriaCampo(categoriaSelect);
                    n.setIdEmpresa(documentoNuevo.getIdEmpresa());
                    genericoService.guardar(n);
                    
                    MvDocumentosTipos t = new MvDocumentosTipos();
                    t.setIdDocumentos(documentoNuevo);
                    t.setIdTiposDocumentos(tipoDocumentoSelect);
                    t.setIdEmpresa(documentoNuevo.getIdEmpresa());
                    genericoService.guardar(t);
                    
                    documentoGenerico();
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/documentos/bandejaDocumentos.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void editar() {
        llenadoDocumentosValidaciones();
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, documentoNuevo.getIdEmpresa().getIdEmpresas())) {
            documentoNuevo.setIdEmpresa(empresaSelect);
//        } else {
//            if(!Objects.equals(empresaSelect.getIdEmpresas(), empresaUsuarioSesion.getIdEmpresas())){
//                documentoNuevo.setIdEmpresa(empresaSelect);
//            }else{
//                documentoNuevo.setIdEmpresa(empresaUsuarioSesion);
//            }
//
//        }
        if (genericoService.update(documentoNuevo)) {
            
            MvDocumentosCategorias n = new MvDocumentosCategorias();
            n.setIdDocumentos(documentoNuevo);
            n.setIdCategoriaCampo(categoriaSelect);
           
            n.setIdDocumentosCategorias(documentoCategoria.getIdDocumentosCategorias());
            n.setIdEmpresa(documentoNuevo.getIdEmpresa());
            genericoService.update(n);
            
            MvDocumentosTipos t = new MvDocumentosTipos();
            t.setIdDocumentos(documentoNuevo);
            t.setIdTiposDocumentos(tipoDocumentoSelect);
            t.setIdDocumentosTipos(documentoTipo.getIdDocumentosTipos());
            t.setIdEmpresa(documentoNuevo.getIdEmpresa());
            genericoService.update(t);
            
            catDocumentosService.actualizaEstatus(documentoNuevo.getIdDocumentos(), false);
            for (int j = 0; j < documentoNuevo.getDtConfiguracionValidacionesList().size(); j++) {
                validaciones = catDocumentosService.validacionesPorDocumentos(documentoNuevo.getIdDocumentos(), documentoNuevo.getDtConfiguracionValidacionesList().get(j).getIdValidaciones().getIdValidaciones());
                if (validaciones != null) {
                    documentoNuevo.getDtConfiguracionValidacionesList().get(j).setStatus(true);
                    documentoNuevo.getDtConfiguracionValidacionesList().get(j).setIdConfiguracionValidacion(validaciones.getIdConfiguracionValidacion());
                    genericoService.update(documentoNuevo.getDtConfiguracionValidacionesList().get(j));
                } else {
                    genericoService.guardar(documentoNuevo.getDtConfiguracionValidacionesList().get(j));
                }
            }
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/documentos/bandejaDocumentos.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void llenadoDocumentosValidaciones() {
        if(empresaUsuarioSesion.getIdEmpresas()==1){
            if (!listaValidaciones.getTarget().isEmpty()) {
                documentoNuevo.setDtConfiguracionValidacionesList(new ArrayList<>());
                listaValidaciones.getTarget().stream().forEach(aux -> {
                    DtConfiguracionValidaciones r = new DtConfiguracionValidaciones();
                    r.setIdValidaciones(aux);
                    r.setIdDocumentos(documentoNuevo);
                    r.setStatus(true);
//                    if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                        r.setIdEmpresas(empresaSelect);
//                    } else {
                        r.setIdEmpresas(empresaUsuarioSesion);
//                    }
                    documentoNuevo.getDtConfiguracionValidacionesList().add(r);
                });
            }
        }
    }
    
    public void documentoGenerico(){
        if(!Objects.equals(empresaUsuarioSesion.getIdEmpresas(),Constantes.ID_EMPRESA_VIU) && documentoNuevo.getIdEmpresa()!=null){
            DtConfiguracionValidaciones r = new DtConfiguracionValidaciones();
            List<CatValidaciones> validacion = catDocumentosService.buscarValidacionDocCargado();
            r.setIdValidaciones(validacion.get(0));
            r.setIdDocumentos(documentoNuevo);
            r.setIdEmpresas(empresaUsuarioSesion);
            r.setStatus(true);
            genericoService.guardar(r);
        }
    }

    public void cambiaPaginaEdicion(CatDocumentos documentos) {
        banderaEdicion = true;
        llenarComboCategoria(documentos.getIdEmpresa().getIdEmpresas());
        llenarComboTipoDocumento(documentos.getIdEmpresa().getIdEmpresas());
        //BUSCA LA CATEGORIA CONFIGURADA
        documentoCategoria = catDocumentosService.buscarDocumentoCategoria(documentos.getIdDocumentos(), documentos.getIdEmpresa().getIdEmpresas());
        if(documentoCategoria!=null)
        {
            categoriaSelect = documentoCategoria.getIdCategoriaCampo();
        }
        //BUSCA EL TIPO DE DOCUMENTO CONFIGURADO
        documentoTipo = catDocumentosService.buscarTipoDocumento(documentos.getIdDocumentos(), documentos.getIdEmpresa().getIdEmpresas());
        if(documentoTipo!=null)
        {
            tipoDocumentoSelect = documentoTipo.getIdTiposDocumentos();
        }
        
        getAdministradorPaginas().setPagina("catalogos/documentos/documentos.xhtml");
        this.documentoNuevo = documentos;
        empresaSelect = documentos.getIdEmpresa();
        listaValidaciones = new DualListModel<>();
        documentos.getDtConfiguracionValidacionesList().stream().forEach(aux -> {
            if(aux.getStatus()==true){
                listaValidaciones.getTarget().add(aux.getIdValidaciones());
            }
        });
        buscarValidacionesDisponibles();
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
//        listaEmpresas = catEmpresasService.buscarTodos(true);
    }
    
    public void llenarComboCategoria(long empresa){
        listaCategorias = catCategoriasCamposService.buscarTodosXEmpresaYAdmin(true, empresa);
    }
    
    public void llenarComboTipoDocumento(long empresa){
        listaTipoDocumento = catDocumentosService.buscarTiposDocumentos(true, 1L);
    }

    public void buscarValidacionesDisponibles() {
        List<CatValidaciones> base = new ArrayList<>();
        base = catDocumentosService.buscarTodosValidacionesDisponibles();
        if (listaValidaciones.getTarget() != null && !listaValidaciones.getTarget().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            listaValidaciones.getTarget().stream().forEach(aux -> {
                ids.add(aux.getIdValidaciones());
            });
            base = catDocumentosService.buscarTodosValidacionesDisponibles(ids);
            listaValidaciones.setSource(base);
        } else {
            listaValidaciones = new DualListModel<>(base, new ArrayList<>());
        }
    }

    public void cancelar() {
        filtroDocumentos = null;
        listaValidaciones = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
        documentoNuevo = new CatDocumentos();
        getAdministradorPaginas().setPagina("catalogos/documentos/bandejaDocumentos.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        listaValidaciones = new DualListModel<>();
        buscarValidacionesDisponibles();
        banderaEdicion = false;
        empresaSelect = empresaUsuarioSesion;
        categoriaSelect = null;
        tipoDocumentoSelect = null;
        llenarComboCategoria(empresaUsuarioSesion.getIdEmpresas());
        llenarComboTipoDocumento(empresaUsuarioSesion.getIdEmpresas());
        getAdministradorPaginas().setPagina("catalogos/documentos/documentos.xhtml");
        documentoNuevo = new CatDocumentos();

        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
//        listaEmpresas = catEmpresasService.buscarTodos(true);

    }

    public void validaEstatus() {
        if (documentoNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public CatDocumentos getDocumentoNuevo() {
        return documentoNuevo;
    }

    public void setDocumentoNuevo(CatDocumentos documentoNuevo) {
        this.documentoNuevo = documentoNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatDocumentos> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CatDocumentos> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<CatDocumentos> getFiltroDocumentos() {
        return filtroDocumentos;
    }

    public void setFiltroDocumentos(List<CatDocumentos> filtroDocumentos) {
        this.filtroDocumentos = filtroDocumentos;
    }

    public boolean isPintarCombo() {
        return pintarCombo;
    }

    public void setPintarCombo(boolean pintarCombo) {
        this.pintarCombo = pintarCombo;
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

    public DualListModel<CatValidaciones> getListaValidaciones() {
        return listaValidaciones;
    }

    public void setListaValidaciones(DualListModel<CatValidaciones> listaValidaciones) {
        this.listaValidaciones = listaValidaciones;
    }

    public boolean isBanderaDisable() {
        return banderaDisable;
    }

    public void setBanderaDisable(boolean banderaDisable) {
        this.banderaDisable = banderaDisable;
    }

    public List<CatCategoriasCampos> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CatCategoriasCampos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public CatCategoriasCampos getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(CatCategoriasCampos categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public List<CatTiposDocumentos> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<CatTiposDocumentos> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public CatTiposDocumentos getTipoDocumentoSelect() {
        return tipoDocumentoSelect;
    }

    public void setTipoDocumentoSelect(CatTiposDocumentos tipoDocumentoSelect) {
        this.tipoDocumentoSelect = tipoDocumentoSelect;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }
    
    
}
