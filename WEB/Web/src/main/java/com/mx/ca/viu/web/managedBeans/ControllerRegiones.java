/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatRegiones;

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
@ManagedBean(name = "controllerRegiones")
@javax.faces.bean.ViewScoped
public class ControllerRegiones extends UtilServicios implements Serializable {

    private CatRegiones regionNuevo;
    private boolean banderaEdicion;
    private List<CatRegiones> listaRegiones;
    private List<CatRegiones> filtroRegiones;
    private boolean pintarCombo;
    private List<CatEmpresas> listaEmpresas;
    private CatEmpresas empresaSelect;
    private CatEmpresas empresaUsuarioSesion;
    private Long empresa;

    public ControllerRegiones() {
    }

    @PostConstruct
    public void init() {
        regionNuevo = new CatRegiones();
        pintarCombo = false;
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    buscarTodosRegiones(false);
//                    pintarCombo = true;
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if(!listaEmpresas.isEmpty()){
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosRegionesXEmpresa(empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void validaEstatus() {
        if (regionNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void confirmaEstatus(boolean estatus) {
        regionNuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void buscarTodosRegiones(boolean activos) {
        listaRegiones = catRegionesService.buscarTodos(activos);
    }

    public void buscarTodosRegionesXEmpresa(Long id) {
        listaRegiones = catRegionesService.buscarTodosXidEmpres(id);
    }

    public void buscarTodosRegionesXEmpresaFiltro() {
        if( empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))){
            buscarTodosRegiones(false);
        
        } else {
            listaRegiones = catRegionesService.buscarTodosXidEmpres(empresaSelect.getIdEmpresas());
        } 
    }
    
    public void buscarRegionesInterno(){
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosRegiones(false);
        } else {
            buscarTodosRegionesXEmpresa(empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void guardar() {
        filtroRegiones = null;
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }
        
        if (catRegionesService.buscarNombre(regionNuevo.getNombre(), regionNuevo.getIdRegiones(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    regionNuevo.setIdEmpresa(empresaSelect);
//                } else {
                    regionNuevo.setIdEmpresa(empresaUsuarioSesion);
//                }
                if (genericoService.guardar(regionNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/regiones/bandejaRegiones.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void editar() {
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            regionNuevo.setIdEmpresa(empresaSelect);
//        } else {
            regionNuevo.setIdEmpresa(empresaUsuarioSesion);
//        }
        if (genericoService.update(regionNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/regiones/bandejaRegiones.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cambiaPaginaEdicion(CatRegiones regiones) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/regiones/regiones.xhtml");
        this.regionNuevo = regiones;
        empresaSelect = regiones.getIdEmpresa();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public void cancelar() {
        filtroRegiones = null;
        getAdministradorPaginas().setPagina("catalogos/regiones/bandejaRegiones.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("catalogos/regiones/regiones.xhtml");
        regionNuevo = new CatRegiones();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
    }

    public CatRegiones getRegionNuevo() {
        return regionNuevo;
    }

    public void setRegionNuevo(CatRegiones regionNuevo) {
        this.regionNuevo = regionNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatRegiones> getListaRegiones() {
        return listaRegiones;
    }

    public void setListaRegiones(List<CatRegiones> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }

    public List<CatRegiones> getFiltroRegiones() {
        return filtroRegiones;
    }

    public void setFiltroRegiones(List<CatRegiones> filtroRegiones) {
        this.filtroRegiones = filtroRegiones;
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

}
