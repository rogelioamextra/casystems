/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.CatTipoCampo;
import com.mx.ca.viu.modelos.DatosSolicitudAvisos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.modelos.DatosSolicitudCategorias;
import com.mx.ca.viu.modelos.DatosSolicitudDocumentos;

import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.MvDatosSolicitud;
import com.mx.ca.viu.modelos.dtos.ModDatosProceso;
import com.mx.ca.viu.modelos.dtos.generico.DtoCamposDocumentos;
import com.mx.ca.viu.modelos.dtos.generico.DtoCategoriaDatosProceso;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DualListModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerEnrolamiento")
@javax.faces.bean.ViewScoped
public class ControllerEnrolamiento extends UtilServicios implements Serializable {

    private String lblProducto;
    private String lblEmpresa;
    private String lblTipoDatoCaptura;
    private Long idProductos;
    private Long idEmpresa;
    private boolean banderaEdicion;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private List<MvConfigSolicitudes> listaProductos;
    private List<CatProductos> filtroProductos;
    private CatProductos productoNuevo;
    private boolean estatus;

    private List<CatCampos> listaCampos;
    private List<CatCampos> listaDatosGeneral;
    private List<CatCampos> listaDatosContacto;
    private List<CatCampos> listaDatosAdicional;
    private List<CatCategoriasCampos> listaCategorias;
    private List<CatCategoriasCampos> listaCategoriasGuardar;
    private List<CatCategoriasCampos> listaCategoriasDisponibles;
    private DualListModel<CatCategoriasCampos> listaCategoriasOrden;

    private List<MvDatosSolicitud> listaDatosSolicitud;
    private List<MvDatosSolicitud> auxListaDatosSolicitud;
    private List<MvDatosSolicitud> auxiliar;

    private MvDatosSolicitud nuevoDS;
    private List<ModDatosProceso> listaModeloDatosProceso;
    private List<CatTipoCampo> listaTipoCampos;
    private ModDatosProceso modelo;
    private String palabra;
    private List<DtoCamposDocumentos> listaCamposDocumentos;
    private List<DtoCamposDocumentos> listaCamposDocumentosFiltro;

    private List<DtoCategoriaDatosProceso> listaCategoriaDto;
    private List<DtoCategoriaDatosProceso> listaCategoriaDtoCombo;
    private List<DtoCategoriaDatosProceso> listaCategoriaDtoFiltro;
    private List<MvConfigSolicitudes> listaCategoriasActivas;

    private MvConfigSolicitudes mvConfigSolicitudesSelect;
    private CatCategoriasCampos categoriaSelect;
    private List<CatCategoriasCampos> listaCategoriasCombo;
    private CatCategoriasCampos listaCategoriasSelect;
    
   // private DualListModel<DatosSolicitudCampos> listaCamposCategorias; sera un dto
    private DualListModel<DtoCamposDocumentos> listaCamposCategorias;

    int contDP = 0;
    int contDC;
    private DashboardModel model;
    private List<String> listaDasboard;
    private int activeIndex;

    public ControllerEnrolamiento() {
    }

    public void creaDasboard() {

    }

    @PostConstruct
    public void init() {

        productoNuevo = new CatProductos();
        listaCamposDocumentos = null;
        listaProductos = new ArrayList<>();
        mvConfigSolicitudesSelect = new MvConfigSolicitudes();
        listaCategoriasOrden = new DualListModel<>();
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosProductos(false);
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if (!listaEmpresas.isEmpty()) {
                        empresaSelect = listaEmpresas.get(0);
//
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosProductosEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
        activeIndex = 0;
        
    }

    public void buscarTodosProductos(boolean activos) {
        listaProductos = catEnrolamientoService.buscarTodos(activos);//llena una lista de tipo MvConfigSolicitudes la cual trae producto,empresa y estatus
    }

    public void buscarTodosProductosEmpresa(boolean activos, Long IdEmpresa) {//llena una lista de tipo MvConfigSolicitudes la cual trae producto,empresa y estatus
        listaProductos = catEnrolamientoService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void cambiaCampos(TabChangeEvent event) {
        if ("campos".equals(event.getTab().getId())) {
            consultaCategoriasActivas();
            //tengo que limpiar los campos
            listaCamposCategorias = new DualListModel<>();
            listaCategoriasSelect = null;
        } else {
            consultaCategorias();
        }
    }

    public void consultaCategoriasActivas() {
        listaCategorias = new ArrayList<>();
        listaCategoriasActivas = new ArrayList<>();
        listaCategoriasCombo = new ArrayList<>();
        listaCategoriaDtoCombo = new ArrayList<>();
        listaCategoriaDtoCombo = mvDatosSolicitudService.generaDtoCategorias(idProductos, mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());
        
        for (DtoCategoriaDatosProceso aux : listaCategoriaDtoCombo) {
            listaCategorias.add(aux.getCategoria());
        }
        
        for (int x = 0; x < listaCategorias.size(); x++) {
            if (listaCategorias.get(x).isBanderaStatus() == true) {
                listaCategoriasCombo.add(listaCategorias.get(x));
            }
        }
        System.out.println("########################### entro a reordenar");
    }
    
    public void cambiaCategoriaCampos(){
        System.out.println("metodo cambia categoria campos");

        listaCamposCategorias = new DualListModel<>();
        if(listaCategoriasSelect==null){
            return;
        }
        listaCategoriasSelect.getDatosSolicitudCamposList();
        //listaCamposCategorias
        List<DtoCamposDocumentos> camposDocsDisponibles = new ArrayList<>(); 
        List<DtoCamposDocumentos> camposDocsUsados = new ArrayList<>(); 


        this.categoriaSelect = listaCategoriasSelect;
        System.out.println(categoriaSelect+","+idProductos+" ,"+mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());
        listaCamposDocumentos = mvDatosSolicitudService.generaDtoCamposDocumentos(categoriaSelect, idProductos, mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());
        
        if(listaCamposDocumentos != null && !listaCamposDocumentos.isEmpty()){//si no está vacio pasa a buscar

        //CICLO PARA SEPARAR CAMPOS 
        listaCamposDocumentos.stream().forEach(aux -> {
            //veremos sí es campo o es documento o aviso
            if(aux.getCampo()!=null){//SI ES UN CAMPO VERIFICARA EN SUS DATOS
                if(aux.getCampo().isBanderaStatus()){
                 //  LLENA EL CAMPO COMO SELECCIONADO
                 camposDocsUsados.add(aux);
                    //listaCamposCategorias.getTarget().add(aux);
                }else{
                 // LLENA EL CAMPO COMO DISPONIBLE
                    camposDocsDisponibles.add(aux);
                }
                
            }else if( aux.getDocumento()!=null){// SI ES DOCUMENTO VERIFICA AHÍ
                if(aux.getDocumento().isBanderaStatus()){
                   //LLENA EL DOCUMENTO COMO SELECCIONADO
                   camposDocsUsados.add(aux);
                  // listaCamposCategorias.getTarget().add(aux);
                }else{
                    //LLENA EL DOCUMENTO COMO DISPONIBLE
                    camposDocsDisponibles.add(aux);
                }
            }else if( aux.getAviso()!=null){//verifica en avisos
                if(aux.getAviso().isBanderaStatus()){
                   //LLENA EL DOCUMENTO COMO SELECCIONADO
                   camposDocsUsados.add(aux);
                  // listaCamposCategorias.getTarget().add(aux);
                }else{
                    //LLENA EL DOCUMENTO COMO DISPONIBLE
                    camposDocsDisponibles.add(aux);
                }
            }
        });
            //se ordena la lista
            if (camposDocsUsados != null && !camposDocsUsados.isEmpty()) {
                //vamos a ordenar la lista por cantidad, empezara descotando al que más compro
                Comparator<DtoCamposDocumentos> compareById
                        = (DtoCamposDocumentos o1, DtoCamposDocumentos o2) -> o1.getOrden().compareTo(o2.getOrden());
                
                Collections.sort(camposDocsUsados, compareById);
                //una vez ordenados los metemos al target
                listaCamposCategorias.setTarget(camposDocsUsados);
            }
        //PUSH DE CAMPOS SIN ORDENAR A LISTA FINAL
        if (camposDocsDisponibles != null && !camposDocsDisponibles.isEmpty()) {
            listaCamposCategorias.setSource(camposDocsDisponibles);
        }
            
        }
    }

    public void consultaCategorias() {
        listaCategorias = new ArrayList<>();
        listaCategoriasDisponibles = new ArrayList<>();
        listaCategoriasOrden = new DualListModel<>();
        //CONSULTA CATEGORIAS CAMPOS
        listaCategoriaDto = mvDatosSolicitudService.generaDtoCategorias(idProductos, mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());
        //OBTIENE EL ARRAY DE CATEGORIAS Y LO PONE EN UN LIST
        for (DtoCategoriaDatosProceso aux : listaCategoriaDto) {
            listaCategorias.add(aux.getCategoria());
        }
        //CICLO PARA SEPARAR CATEGORIAS
        listaCategorias.stream().forEach(aux -> {
            if (aux.isBanderaStatus() == true) {
                //LLENA CATEGORIAS DISPONIBLES
                listaCategoriasOrden.getTarget().add(aux);
            } else {
                //LLENA CATEGORIAS SIN ACTIVAR
                listaCategoriasDisponibles.add(aux);
            }
        });
        //PUSH DE CATEGORIAS SIN ORDENAR A LISTA FINAL
        if (listaCategoriasDisponibles != null && !listaCategoriasDisponibles.isEmpty()) {
            listaCategoriasOrden.setSource(listaCategoriasDisponibles);
        }
    }

    public void cambiaPaginaConfigurar(MvConfigSolicitudes producto) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("enrolamiento/enrolamiento.xhtml");
        idProductos = producto.getIdProducto().getIdProductos();
        mvConfigSolicitudesSelect = producto;
        //BUSCA LAS CATEGORIAS
        consultaCategorias();

        lblProducto = producto.getIdProducto().getNombre();
        lblEmpresa = producto.getIdEmpresa().getNombre();

        // listaCategorias = catCategoriasCamposService.buscarTodos(true);
//        lblProducto = producto.getIdProducto().getNombre();
//        lblEmpresa = producto.getIdEmpresa().getNombre();
//        idProductos = producto.getIdProducto().getIdProductos();
//        idEmpresa = producto.getIdEmpresa().getIdEmpresas();
//        estatus = producto.getStatus();
//        
//        auxListaDatosSolicitud = new ArrayList<>();
//        listaModeloDatosProceso = new ArrayList<>();        
//        
//        listaTipoCampos = catCamposService.buscarTipoCampos(true);
//        listaTipoCampos.stream().filter((aux) -> (aux.getIdTipo().equals(1L))).forEachOrdered((aux) -> {
//            lblTipoDatoCaptura = aux.getNombre();
//        });    
//        
//        listaDatosSolicitud = catEnrolamientoService.buscarRegistrosMvDatosSolicitud(false, producto.getIdConfigSolicitud());
//        if(listaDatosSolicitud.size()>0){
//            for (MvDatosSolicitud aux : listaDatosSolicitud) {
//                modelo = new ModDatosProceso();
//                modelo.setIdCampo(aux.getIdCampo());
//                if(aux.getIdCampo()!=null){
//                    modelo.setCategoriaCampos(aux.getIdCampo().getIdCategoria());
//                }
//                modelo.setIdConfigSolicitud(aux.getIdConfigSolicitud());
//                modelo.setIdDocumentoCategoria(aux.getIdDocumentoCategoria());
//                if(aux.getIdDocumentoCategoria()!=null){
//                    modelo.setCategoriaCampos(aux.getIdDocumentoCategoria().getIdCategoriaCampo());
//                }
//                modelo.setStatus(aux.getStatus());
//                listaModeloDatosProceso.add(modelo);
//            }
//        }
//        
//        //vamos a agregar a nuestra lista modelo los campos que no tengamos agregados
//        listaCampos = catCamposService.buscarTodosXEmpresa(false, producto.getIdEmpresa().getIdEmpresas());
//        int auxListaModelo = listaModeloDatosProceso.size();
//        if(listaCampos.size()>0){
//            boolean encontrado = false;
//            for(int x=0; x<listaCampos.size(); x++){
//                if(auxListaModelo>0){
//                    for(int y=0; y<listaModeloDatosProceso.size(); y++){
//                        encontrado = false;
//                        if(Objects.equals(listaModeloDatosProceso.get(y).getIdCampo(), listaCampos.get(x))){
//                           encontrado = true;
//                           break;
//                       }
//                    }
//                    if(!encontrado){
//                        modelo = new ModDatosProceso();
//                        modelo.setIdCampo(listaCampos.get(x));
//                        modelo.setCategoriaCampos(listaCampos.get(x).getIdCategoria());
//                        modelo.setIdConfigSolicitud(producto);
//                        modelo.setStatus(false);
//                        listaModeloDatosProceso.add(modelo);
//                    }
//                }else{
//                    modelo = new ModDatosProceso();
//                    modelo.setIdCampo(listaCampos.get(x));
//                    modelo.setCategoriaCampos(listaCampos.get(x).getIdCategoria());
//                    modelo.setIdConfigSolicitud(producto);
//                    modelo.setStatus(false);
//                    listaModeloDatosProceso.add(modelo);
//                }
//            }    
//        }
//        
//        
//        listaDocCat = catEnrolamientoService.buscarDocCatPorEmpresaMasAdmin(false, producto.getIdEmpresa().getIdEmpresas());
//       // int auxListaDocCat = listaDocCat.size();
//        if(listaDocCat.size()>0){
//            boolean encontrado = false;
//            for(int x=0; x<listaDocCat.size(); x++){
//                if(auxListaModelo>0){
//                    for(int y=0; y<listaModeloDatosProceso.size(); y++){
//                        encontrado = false;
//                        if(Objects.equals(listaModeloDatosProceso.get(y).getIdDocumentoCategoria(), listaDocCat.get(x))){
//                           encontrado = true;
//                           break;
//                       }
//                    }
//                    if(!encontrado){
//                        modelo = new ModDatosProceso();
//                        modelo.setIdConfigSolicitud(producto);
//                        modelo.setIdDocumentoCategoria(listaDocCat.get(x));
//                        modelo.setCategoriaCampos(listaDocCat.get(x).getIdCategoriaCampo());
//                        modelo.setStatus(false);
//                        listaModeloDatosProceso.add(modelo);
//                    }
//                }else{
//                    modelo = new ModDatosProceso();
//                    modelo.setIdConfigSolicitud(producto);
//                    modelo.setIdDocumentoCategoria(listaDocCat.get(x));
//                    modelo.setCategoriaCampos(listaDocCat.get(x).getIdCategoriaCampo());
//                    modelo.setStatus(false);
//                    listaModeloDatosProceso.add(modelo);
//                }
//            }    
//        }
    }

    public void cancelar() {
        filtroProductos = null;
        productoNuevo = new CatProductos();
        getAdministradorPaginas().setPagina("enrolamiento/bandejaEnrolamiento.xhtml");
        init();
        empresaSelect = new CatEmpresas();
    }

    public void validaEstatus(DtoCamposDocumentos datos) {
        System.out.println("###########################entro areordenar");
    }

    public void onTransferCategorias(TransferEvent event) {
        guardarCategorias();
    }

    public void onReorderCategorias() {
        guardarCategorias();
    }
    
    public void onTransferCampos(TransferEvent event) {
        System.out.println("###########################entro transfiere campos");
        guardarCamposDocumentosAvisos();
    }

    public void onReorderCampos() {
        System.out.println("###########################entro ordena campos");
        guardarCamposDocumentosAvisos();
    }
    
    public void guardarCategorias() {
        filtroProductos = null;
        try {
            int cont = 0;
            MvDatosSolicitud ds = new MvDatosSolicitud();
            if (listaCategoriasOrden.getTarget()!= null && !listaCategoriasOrden.getTarget().isEmpty()) {
                if (!mvConfigSolicitudesSelect.getMvDatosSolicitudList().isEmpty()) {
                    ds = mvConfigSolicitudesSelect.getMvDatosSolicitudList().get(0);
                    mvDatosSolicitudService.deleteCategoriasDatosSolicitud(ds.getIdDatosSolicitud());
                } else {
                    ds.setIdConfigSolicitud(mvConfigSolicitudesSelect);
                    if(genericoService.guardar(ds)){
                        if(mvConfigSolicitudesSelect.getMvDatosSolicitudList().isEmpty())
                            mvConfigSolicitudesSelect.getMvDatosSolicitudList().add(ds);
                        else
                            mvConfigSolicitudesSelect.getMvDatosSolicitudList().set(0, ds);
                    }

                }
            } else {
                //ds.setIdConfigSolicitud(mvConfigSolicitudesSelect);
                if (!mvConfigSolicitudesSelect.getMvDatosSolicitudList().isEmpty()) {
                    ds = mvConfigSolicitudesSelect.getMvDatosSolicitudList().get(0);
                    mvDatosSolicitudService.deleteCategoriasDatosSolicitud(ds.getIdDatosSolicitud());
                    genericoService.guardar(ds);
                    mvConfigSolicitudesSelect.getMvDatosSolicitudList().set(0, ds);
                }
                //
            }
            for (CatCategoriasCampos aux : listaCategoriasOrden.getTarget()) {
                DatosSolicitudCategorias cat = new DatosSolicitudCategorias();
                cat.setIdCategoria(aux);
                cat.setOrden(cont++);
                cat.setStatus(aux.getStatus());
                cat.setIdDatosSolicitud(ds);
                genericoService.guardar(cat);
            }
            WebGenerico.menajeInformativo("Cambios guardados con exito");
        } catch (Exception e) {
            logger.error(e);
            WebGenerico.menajeError("Ocurrio un error inesperado, intente nuevamente");
        }
    }
    
    public void guardarCamposDocumentosAvisos() {
        int cont = 0;
        MvDatosSolicitud ds = new MvDatosSolicitud();
        if (mvConfigSolicitudesSelect.getMvDatosSolicitudList() != null) {
            if (!mvConfigSolicitudesSelect.getMvDatosSolicitudList().isEmpty()) {
                ds = mvConfigSolicitudesSelect.getMvDatosSolicitudList().get(0);
                
                mvDatosSolicitudService.deleteCamposDocumentosDatosSolicitud(ds.getIdDatosSolicitud(), categoriaSelect.getIdCategoriaCampo());
           
        for (DtoCamposDocumentos aux : listaCamposCategorias.getTarget()) {
           if (aux.getCampo() != null) {
                    DatosSolicitudCampos cam = new DatosSolicitudCampos();
                    cam.setIdCampo(aux.getCampo());
                    cam.setStatus(true);
                    cam.setOrden(cont++);
                    cam.setIdDatosSolicitud(ds);
                    cam.setIdCategoria(categoriaSelect);
                    genericoService.guardar(cam);
                }
                if (aux.getDocumento() != null) {
                    DatosSolicitudDocumentos cat = new DatosSolicitudDocumentos();
                    cat.setIdDocumento(aux.getDocumento());
                    cat.setOrden(cont++);
                    cat.setStatus(true);
                    cat.setIdDatosSolicitud(ds);
                    cat.setIdCategoria(categoriaSelect);
                    genericoService.guardar(cat);
                }
                if (aux.getAviso() != null) {
                    DatosSolicitudAvisos cat = new DatosSolicitudAvisos();
                    cat.setIdAviso(aux.getAviso());
                    cat.setOrden(cont++);
                    cat.setStatus(true);
                    cat.setIdDatosSolicitud(ds);
                    cat.setIdCategoria(categoriaSelect);
                    genericoService.guardar(cat);
                }
                 }
        }
            }
    }

    public void onSelect(SelectEvent<DtoCategoriaDatosProceso> event) {
        System.out.println("###########################entro a seleccionar ");

        this.categoriaSelect = event.getObject().getCategoria();
        listaCamposDocumentos = mvDatosSolicitudService.generaDtoCamposDocumentos(categoriaSelect, idProductos, mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());
        
    }

    public void selectCategoria(CatCategoriasCampos categoria) {
        this.categoriaSelect = categoria;
        listaCamposDocumentos = mvDatosSolicitudService.generaDtoCamposDocumentos(categoria, idProductos, mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());

    }

//    public void selectCategoria2() {
//        model = new DefaultDashboardModel();
//        listaDasboard = new ArrayList<>();
//
//        listaCamposDocumentos = mvDatosSolicitudService.generaDtoCamposDocumentos(categoriaSelect, idProductos, mvConfigSolicitudesSelect.getIdEmpresa().getIdEmpresas());
//        for (DtoCamposDocumentos aux : listaCamposDocumentos) {
//            DashboardColumn column1 = new DefaultDashboardColumn();
//
//            column1.addWidget(aux.getNombre());
//            listaDasboard.add(aux.getNombre());
//            model.addColumn(column1);
//
//        }
//
//    }
//    public void valueChangeMethod(ValueChangeEvent e){
//		categoriaSelect=(CatCategoriasCampos)e.getNewValue();
//                selectCategoria2();
//	}
    public void handleReorder(DashboardReorderEvent event) {
        WebGenerico.menajeInformativo("se movio el componente:" + event.getWidgetId() + " a la posiscion:" + (event.getItemIndex() + 1));

    }

    /*public void guardarCategorias() {
        List<DatosSolicitudCategorias> lista = new ArrayList<>();
        int contador = 0;
        MvConfigSolicitudes buscar = mvConfigSolicitudService.buscarTodosXProducto(idProductos);
        if (buscar == null) {
            buscar = mvConfigSolicitudesSelect;
        }
        for (DtoCategoriaDatosProceso aux : listaCategoriaDto) {
            DatosSolicitudCategorias nuevo = new DatosSolicitudCategorias();
            nuevo.setIdCategoria(aux.getCategoria());
            nuevo.setOrden(contador++);
            nuevo.setStatus(aux.getCategoria().getStatus());
            nuevo.setIdDatosSolicitud(nuevoDS);
            lista.add(nuevo);
        }
    }*/
    public void guardarCamposDocumentos() {

    }

    public void guardarDatosDocumentos() {
        try {
            guardarCategorias();
            int cont = 0;
            MvDatosSolicitud ds = new MvDatosSolicitud();
            //mvConfigSolicitudesSelect.getMvDatosSolicitudList()
            if (mvConfigSolicitudesSelect.getMvDatosSolicitudList() != null) {
                if (!mvConfigSolicitudesSelect.getMvDatosSolicitudList().isEmpty()) {
                    ds = mvConfigSolicitudesSelect.getMvDatosSolicitudList().get(0);
                    mvDatosSolicitudService.deleteCamposDocumentosDatosSolicitud(ds.getIdDatosSolicitud(), categoriaSelect.getIdCategoriaCampo());
                }
                /*else {
                    ds.setIdConfigSolicitud(mvConfigSolicitudesSelect);
                    genericoService.guardar(ds);
                }*/
            }
            /*else {
                ds.setIdConfigSolicitud(mvConfigSolicitudesSelect);
                genericoService.guardar(ds);
            }*/
            for (DtoCamposDocumentos aux : listaCamposDocumentos) {
                if (aux.getCampo() != null) {
                    DatosSolicitudCampos cam = new DatosSolicitudCampos();
                    cam.setIdCampo(aux.getCampo());
                    cam.setStatus(aux.getCampo().isBanderaStatus());
                    cam.setOrden(cont++);
                    cam.setIdDatosSolicitud(ds);
                    cam.setIdCategoria(categoriaSelect);
                    genericoService.guardar(cam);
                }
                if (aux.getDocumento() != null) {
                    DatosSolicitudDocumentos cat = new DatosSolicitudDocumentos();
                    cat.setIdDocumento(aux.getDocumento());
                    cat.setOrden(cont++);
                    cat.setStatus(aux.getDocumento().isBanderaStatus());
                    cat.setIdDatosSolicitud(ds);
                    cat.setIdCategoria(categoriaSelect);
                    genericoService.guardar(cat);
                }
            }

            //WebGenerico.menajeInformativo("Cambios guardados con exito");
        } catch (Exception e) {
            logger.error(e);
            WebGenerico.menajeError("Ocurrio un error inesperado, intente nuevamente");
        }
    }

    public void buscarProductosInterno() {
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosProductos(false);
        } else {
            buscarTodosProductosEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void buscarTodosXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaProductos = catEnrolamientoService.buscarTodos(false);
        } else {
            listaProductos = catEnrolamientoService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void validaEstatus() {
        if (estatus == false) {
            PrimeFaces.current().executeScript("PF('dlgAdvertencia').show();");
        }
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

    public CatEmpresas getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(CatEmpresas empresaSelect) {
        this.empresaSelect = empresaSelect;
    }

    public List<CatEmpresas> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<CatEmpresas> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<MvConfigSolicitudes> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<MvConfigSolicitudes> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<CatProductos> getFiltroProductos() {
        return filtroProductos;
    }

    public void setFiltroProductos(List<CatProductos> filtroProductos) {
        this.filtroProductos = filtroProductos;
    }

    public CatProductos getProductoNuevo() {
        return productoNuevo;
    }

    public void setProductoNuevo(CatProductos productoNuevo) {
        this.productoNuevo = productoNuevo;
    }

    public List<CatCampos> getListaDatosGeneral() {
        return listaDatosGeneral;
    }

    public void setListaDatosGeneral(List<CatCampos> listaDatosGeneral) {
        this.listaDatosGeneral = listaDatosGeneral;
    }

    public List<CatCampos> getListaDatosContacto() {
        return listaDatosContacto;
    }

    public void setListaDatosContacto(List<CatCampos> listaDatosContacto) {
        this.listaDatosContacto = listaDatosContacto;
    }

    public List<CatCampos> getListaDatosAdicional() {
        return listaDatosAdicional;
    }

    public void setListaDatosAdicional(List<CatCampos> listaDatosAdicional) {
        this.listaDatosAdicional = listaDatosAdicional;
    }

    public String getLblProducto() {
        return lblProducto;
    }

    public void setLblProducto(String lblProducto) {
        this.lblProducto = lblProducto;
    }

    public String getLblEmpresa() {
        return lblEmpresa;
    }

    public void setLblEmpresa(String lblEmpresa) {
        this.lblEmpresa = lblEmpresa;
    }

    public List<CatCampos> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<CatCampos> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public Long getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Long idProductos) {
        this.idProductos = idProductos;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatCategoriasCampos> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CatCategoriasCampos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<MvDatosSolicitud> getListaDatosSolicitud() {
        return listaDatosSolicitud;
    }

    public void setListaDatosSolicitud(List<MvDatosSolicitud> listaDatosSolicitud) {
        this.listaDatosSolicitud = listaDatosSolicitud;
    }

    public List<MvDatosSolicitud> getAuxListaDatosSolicitud() {
        return auxListaDatosSolicitud;
    }

    public void setAuxListaDatosSolicitud(List<MvDatosSolicitud> auxListaDatosSolicitud) {
        this.auxListaDatosSolicitud = auxListaDatosSolicitud;
    }

    public List<ModDatosProceso> getListaModeloDatosProceso() {
        return listaModeloDatosProceso;
    }

    public void setListaModeloDatosProceso(List<ModDatosProceso> listaModeloDatosProceso) {
        this.listaModeloDatosProceso = listaModeloDatosProceso;
    }

    public ModDatosProceso getModelo() {
        return modelo;
    }

    public void setModelo(ModDatosProceso modelo) {
        this.modelo = modelo;
    }

    public String getLblTipoDatoCaptura() {
        return lblTipoDatoCaptura;
    }

    public void setLblTipoDatoCaptura(String lblTipoDatoCaptura) {
        this.lblTipoDatoCaptura = lblTipoDatoCaptura;
    }

    public MvDatosSolicitud getNuevoDS() {
        return nuevoDS;
    }

    public void setNuevoDS(MvDatosSolicitud nuevoDS) {
        this.nuevoDS = nuevoDS;
    }

    public List<CatTipoCampo> getListaTipoCampos() {
        return listaTipoCampos;
    }

    public void setListaTipoCampos(List<CatTipoCampo> listaTipoCampos) {
        this.listaTipoCampos = listaTipoCampos;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public List<MvDatosSolicitud> getAux() {
        return auxiliar;
    }

    public void setAux(List<MvDatosSolicitud> aux) {
        this.auxiliar = aux;
    }

    public List<DtoCamposDocumentos> getListaCamposDocumentos() {
        return listaCamposDocumentos;
    }

    public void setListaCamposDocumentos(List<DtoCamposDocumentos> listaCamposDocumentos) {
        this.listaCamposDocumentos = listaCamposDocumentos;
    }

    public List<DtoCategoriaDatosProceso> getListaCategoriaDto() {
        return listaCategoriaDto;
    }

    public void setListaCategoriaDto(List<DtoCategoriaDatosProceso> listaCategoriaDto) {
        this.listaCategoriaDto = listaCategoriaDto;
    }

    public List<DtoCamposDocumentos> getListaCamposDocumentosFiltro() {
        return listaCamposDocumentosFiltro;
    }

    public void setListaCamposDocumentosFiltro(List<DtoCamposDocumentos> listaCamposDocumentosFiltro) {
        this.listaCamposDocumentosFiltro = listaCamposDocumentosFiltro;
    }

    public List<DtoCategoriaDatosProceso> getListaCategoriaDtoFiltro() {
        return listaCategoriaDtoFiltro;
    }

    public void setListaCategoriaDtoFiltro(List<DtoCategoriaDatosProceso> listaCategoriaDtoFiltro) {
        this.listaCategoriaDtoFiltro = listaCategoriaDtoFiltro;
    }

    public CatCategoriasCampos getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(CatCategoriasCampos categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public DashboardModel getModel() {
        return model;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }

    public List<String> getListaDasboard() {
        return listaDasboard;
    }

    public void setListaDasboard(List<String> listaDasboard) {
        this.listaDasboard = listaDasboard;
    }

    public DualListModel<CatCategoriasCampos> getListaCategoriasOrden() {
        return listaCategoriasOrden;
    }

    public void setListaCategoriasOrden(DualListModel<CatCategoriasCampos> listaCategoriasOrden) {
        this.listaCategoriasOrden = listaCategoriasOrden;
    }

    public List<MvConfigSolicitudes> getListaCategoriasActivas() {
        return listaCategoriasActivas;
    }

    public void setListaCategoriasActivas(List<MvConfigSolicitudes> listaCategoriasActivas) {
        this.listaCategoriasActivas = listaCategoriasActivas;
    }

    public List<CatCategoriasCampos> getListaCategoriasCombo() {
        return listaCategoriasCombo;
    }

    public void setListaCategoriasCombo(List<CatCategoriasCampos> listaCategoriasCombo) {
        this.listaCategoriasCombo = listaCategoriasCombo;
    }

    public List<CatCategoriasCampos> getListaCategoriasGuardar() {
        return listaCategoriasGuardar;
    }

    public void setListaCategoriasGuardar(List<CatCategoriasCampos> listaCategoriasGuardar) {
        this.listaCategoriasGuardar = listaCategoriasGuardar;
    }

    public CatCategoriasCampos getListaCategoriasSelect() {
        return listaCategoriasSelect;
    }

    public void setListaCategoriasSelect(CatCategoriasCampos listaCategoriasSelect) {
        this.listaCategoriasSelect = listaCategoriasSelect;
    }

    public DualListModel<DtoCamposDocumentos> getListaCamposCategorias() {
        return listaCamposCategorias;
    }

    public void setListaCamposCategorias(DualListModel<DtoCamposDocumentos> listaCamposCategorias) {
        this.listaCamposCategorias = listaCamposCategorias;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    
}
