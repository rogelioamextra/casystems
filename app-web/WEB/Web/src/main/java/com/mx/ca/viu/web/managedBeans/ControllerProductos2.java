/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;

import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@ManagedBean(name = "controllerProductos2")
@javax.faces.bean.ViewScoped
public class ControllerProductos2 extends UtilServicios implements Serializable {

    private CatProductos productoNuevo;
    private MvConfigSolicitudes configuracionSol;
    private boolean banderaEdicion;
    private List<CatProductos> listaProductos;
    private List<CatProductos> filtroProductos;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private Long empresa;

    public ControllerProductos2() {
    }

    @PostConstruct
    public void init() {
        productoNuevo = new CatProductos();
        configuracionSol = new MvConfigSolicitudes();
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosProductos(false);
                buscarTodosProductosEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                    listaEmpresas = llenarCombo(listaEmpresas);
                    listaEmpresas = new ArrayList<>();
                    listaEmpresas.add(empresaUsuarioSesion);
//                    if(!listaEmpresas.isEmpty()){
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
//                    buscarTodosProductosEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void buscarTodosProductosXEmpresaFiltro() {
        filtroProductos = null;
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaProductos = catProductosService.buscarTodos(false);
        } else {
            listaProductos = catProductosService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void buscarTodosProductos(boolean activos) {
        listaProductos = catProductosService.buscarTodos(activos);
    }

    public void buscarTodosProductosEmpresa(boolean activos, Long IdEmpresa) {
        listaProductos = catProductosService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void validaEstatus() {
        if (productoNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgAdvertenciaProducto').show();");
        }
    }

    public void guardar() {
        filtroProductos = null;
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }
        if (catProductosService.buscarNombre(productoNuevo.getNombre(), productoNuevo.getIdProductos(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    productoNuevo.setIdEmpresa(empresaSelect);
//                    configuracionSol.setIdEmpresa(empresaSelect);
//                } else {
                    productoNuevo.setIdEmpresa(empresaUsuarioSesion);
                    configuracionSol.setIdEmpresa(empresaUsuarioSesion);
//                }
                if (genericoService.guardar(productoNuevo)) {
                    configuracionSol.setIdProducto(productoNuevo);
                    configuracionSol.setStatus(productoNuevo.getStatus());
                    genericoService.guardar(configuracionSol);
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/productos/bandejaProductos.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.productos.nombre.duplicado"));
        }
    }

    public void buscarProductosInterno() {
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosProductos(false);
        } else {
            buscarTodosProductosEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void editar() {
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            productoNuevo.setIdEmpresa(empresaSelect);
//            configuracionSol.setIdEmpresa(empresaSelect);
//        } else {
            productoNuevo.setIdEmpresa(empresaUsuarioSesion);
            configuracionSol.setIdEmpresa(empresaUsuarioSesion);
//        }
        
        if (genericoService.update(productoNuevo)) {
            configuracionSol.setStatus(productoNuevo.getStatus());
            genericoService.update(configuracionSol);
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/productos/bandejaProductos.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cambiaPaginaEdicion(CatProductos producto) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/productos/productos.xhtml");
        this.productoNuevo = producto;
        empresaSelect = producto.getIdEmpresa();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public void cancelar() {
        filtroProductos = null;
        productoNuevo = new CatProductos();
        getAdministradorPaginas().setPagina("catalogos/productos/bandejaProductos.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("catalogos/productos/productos.xhtml");
        productoNuevo = new CatProductos();
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
//        }
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
    }

    public CatProductos getProductoNuevo() {
        return productoNuevo;
    }

    public void setProductoNuevo(CatProductos productoNuevo) {
        this.productoNuevo = productoNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<CatProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<CatProductos> getFiltroProductos() {
        return filtroProductos;
    }

    public void setFiltroProductos(List<CatProductos> filtroProductos) {
        this.filtroProductos = filtroProductos;
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

    public MvConfigSolicitudes getConfiguracionSol() {
        return configuracionSol;
    }

    public void setConfiguracionSol(MvConfigSolicitudes configuracionSol) {
        this.configuracionSol = configuracionSol;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }
    
    
}
