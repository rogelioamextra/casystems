/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.commons.MatrixToImageWriter;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatFolio;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerFolios")
@javax.faces.bean.ViewScoped
public class ControllerFolios extends UtilServicios implements Serializable {

    private CatFolio folioNuevo;
    private List<CatFolio> listaFolios;
    private List<CatFolio> filtroFolios;
    private boolean banderaEdicion;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private List<CatProductos> listaProductos;
    private CatProductos productoSelect;
    private StreamedContent file;
    private Long empresa;

    public ControllerFolios() {
    }

    @PostConstruct
    public void init() {
        folioNuevo = new CatFolio();
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodos(false);
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if (!listaEmpresas.isEmpty()) {
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosPorEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void guardar() {
        filtroFolios = null;
        
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }
        
        if (catFoliosService.buscarNombre(folioNuevo.getNombre(), folioNuevo.getIdFolio(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    folioNuevo.setIdEmpresa(empresaSelect);
//                } else {
                    folioNuevo.setIdEmpresa(empresaUsuarioSesion);
//                }
                folioNuevo.setIdProducto(productoSelect);
                folioNuevo.setFolio(UtilGenerico.generaFolioQR(folioNuevo));
                folioNuevo.setFechaGeneracion(UtilGenerico.obtenerHoraMexico());
                if (genericoService.guardar(folioNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("admnistracion/folios/bandejaFolios.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void editar() {
        folioNuevo.setIdProducto(productoSelect);
        //if(folioNuevo.getContNoConsumoQr() < folioNuevo.getNoConsumoQr()){
            if (genericoService.update(folioNuevo)) {
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
                init();
                getAdministradorPaginas().setPagina("admnistracion/folios/bandejaFolios.xhtml");
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        //}else{
        //    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error.qr"));
        //}
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("admnistracion/folios/folios.xhtml");
        folioNuevo = new CatFolio();
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
//        }
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
        empresaSelect = empresaUsuarioSesion;
        buscarTodosProductosXEmpresaFiltro();
        folioNuevo.setContNoConsumoQr(0);
        folioNuevo.setStatus(true);
    }

    public void cambiaPaginaEdicion(CatFolio data) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("admnistracion/folios/folios.xhtml");
        this.folioNuevo = data;
        empresaSelect = data.getIdEmpresa();
        productoSelect = data.getIdProducto();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
        buscarTodosProductosXEmpresaFiltro();
    }

    public void buscarTodosProductosXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaProductos = catProductosService.buscarTodos(false);
        } else {
            listaProductos = catProductosService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void verQR(CatFolio data) {
        try {
           final InputStream inputStream = MatrixToImageWriter.creatImage(data.getFolio());
            file=DefaultStreamedContent.builder()
                .name("QR.png")
                .contentType("image/png")
                .stream(() -> inputStream)
                .build();
            this.folioNuevo = data;
             inputStream.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerFolios.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void cancelar() {
        filtroFolios = null;
        folioNuevo = new CatFolio();
        getAdministradorPaginas().setPagina("admnistracion/folios/bandejaFolios.xhtml");
        init();
        empresaSelect = new CatEmpresas();
    }

    public void validaEstatus() {
        if (folioNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void buscarTodos(boolean activos) {
        listaFolios = catFoliosService.buscarTodos(activos);
    }

    public void buscarTodosPorEmpresa(boolean activos, Long IdEmpresa) {
        listaFolios = catFoliosService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void buscarTodosXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaFolios = catFoliosService.buscarTodos(false);
        } else {
            listaFolios = catFoliosService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public CatFolio getFolioNuevo() {
        return folioNuevo;
    }

    public void setFolioNuevo(CatFolio folioNuevo) {
        this.folioNuevo = folioNuevo;
    }

    public List<CatFolio> getListaFolios() {
        return listaFolios;
    }

    public void setListaFolios(List<CatFolio> listaFolios) {
        this.listaFolios = listaFolios;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
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

    public List<CatFolio> getFiltroFolios() {
        return filtroFolios;
    }

    public void setFiltroFolios(List<CatFolio> filtroFolios) {
        this.filtroFolios = filtroFolios;
    }

    public List<CatProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<CatProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public CatProductos getProductoSelect() {
        return productoSelect;
    }

    public void setProductoSelect(CatProductos productoSelect) {
        this.productoSelect = productoSelect;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    

}
