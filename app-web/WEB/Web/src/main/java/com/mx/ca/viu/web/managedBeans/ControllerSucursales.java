/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatSucursales;

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
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerSucursales")
@javax.faces.bean.ViewScoped
public class ControllerSucursales extends UtilServicios implements Serializable {

    private CatSucursales sucursalNuevo;
    private boolean banderaEdicion;
    private boolean pintarCombo;
    private List<CatSucursales> listaSucursales;
    private List<CatSucursales> filtroSucursales;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private Long empresa;

    public ControllerSucursales() {
    }

    @PostConstruct
    public void init() {
        sucursalNuevo = new CatSucursales();
        sucursalNuevo.setCodEmpresaAmextra(Constantes.CODIGO_EMPRESA_CORE_AMEXTRA);
        buscarTodosSucursales(false);
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosSucursales(false);
//                    pintarCombo = true;
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if(!listaEmpresas.isEmpty()){
//                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
                empresaSelect = empresaUsuarioSesion;
                buscarTodosSucursalesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void validaEstatus() {
        if (sucursalNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void confirmaEstatus(boolean estatus) {
        sucursalNuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void buscarTodosSucursales(boolean activos) {
        listaSucursales = catSucursalesService.buscarTodos(activos);
    }

    public void buscarTodosSucursalesXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            buscarTodosSucursales(false);
        } else {
            listaSucursales = catSucursalesService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void buscarTodosSucursalesEmpresa(boolean activos, Long IdEmpresa) {
        listaSucursales = catSucursalesService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void guardar() {
        filtroSucursales = null;

//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
        empresa = empresaUsuarioSesion.getIdEmpresas();
//        }

        if (catSucursalesService.buscarNombre(sucursalNuevo.getNombre(), sucursalNuevo.getIdSucursales(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    sucursalNuevo.setIdEmpresa(empresaSelect);
//                } else {
                sucursalNuevo.setIdEmpresa(empresaUsuarioSesion);
//                }
                if (genericoService.guardar(sucursalNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/sucursales/bandejaSucursales.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
//        empresaSelect = new CatEmpresas();
    }

    public void buscarSucursalesInterno() {
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosSucursales(false);
        } else {
            buscarTodosSucursalesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void editar() {
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            sucursalNuevo.setIdEmpresa(empresaSelect);
//        } else {
        sucursalNuevo.setIdEmpresa(empresaUsuarioSesion);
//        }
        if (genericoService.update(sucursalNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/sucursales/bandejaSucursales.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
//        empresaSelect = new CatEmpresas();
    }

    public void cambiaPaginaEdicion(CatSucursales sucursales) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/sucursales/sucursales.xhtml");
        this.sucursalNuevo = sucursales;
        empresaSelect = sucursales.getIdEmpresa();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public void cancelar() {
        filtroSucursales = null;
        sucursalNuevo = new CatSucursales();
        sucursalNuevo.setCodEmpresaAmextra(Constantes.CODIGO_EMPRESA_CORE_AMEXTRA);

        getAdministradorPaginas().setPagina("catalogos/sucursales/bandejaSucursales.xhtml");
        init();
        empresaSelect = new CatEmpresas();
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("catalogos/sucursales/sucursales.xhtml");
        sucursalNuevo = new CatSucursales();
        sucursalNuevo.setCodEmpresaAmextra(Constantes.CODIGO_EMPRESA_CORE_AMEXTRA);

//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public CatSucursales getSucursalNuevo() {
        return sucursalNuevo;
    }

    public void setSucursalNuevo(CatSucursales sucursalNuevo) {
        this.sucursalNuevo = sucursalNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatSucursales> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<CatSucursales> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    public List<CatSucursales> getFiltroSucursales() {
        return filtroSucursales;
    }

    public void setFiltroSucursales(List<CatSucursales> filtroSucursales) {
        this.filtroSucursales = filtroSucursales;
    }

    public boolean isPintarCombo() {
        return pintarCombo;
    }

    public void setPintarCombo(boolean pintarCombo) {
        this.pintarCombo = pintarCombo;
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

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

}
