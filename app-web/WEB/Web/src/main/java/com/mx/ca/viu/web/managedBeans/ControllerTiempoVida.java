/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatValoresTiempoVida;

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
@ManagedBean(name = "controllerTiempoVida")
@javax.faces.bean.ViewScoped
public class ControllerTiempoVida extends UtilServicios implements Serializable {

    private CatValoresTiempoVida tiempoVidaNuevo;
    private boolean banderaEdicion;
    private boolean pintarCombo;
    private List<CatValoresTiempoVida> listaTiempoVida;
    private List<CatValoresTiempoVida> filtroTiempoVida;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private Long empresa;
   
    public ControllerTiempoVida() {
    }

    @PostConstruct
    public void init() {
        tiempoVidaNuevo = new CatValoresTiempoVida();
        buscarTodosTiempoVida(false);
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosTiempoVida(false);
//                    pintarCombo = true;
//                    listaEmpresas = llenarCombo(listaEmpresas);
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
                empresaSelect = listaEmpresas.get(0);
                buscarTodosTiempoVidaEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                    if(!listaEmpresas.isEmpty()){

//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
//                    buscarTodosTiempoVidaEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }
    
    public void validaEstatus() {
        if (tiempoVidaNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void confirmaEstatus(boolean estatus) {
        tiempoVidaNuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void buscarTodosTiempoVida(boolean activos) {
        listaTiempoVida = catTiempoVidaService.buscarTodos(activos);
    }

    public void buscarTodosTiempoVidaXEmpresaFiltro() {
//        if(empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))){
//            buscarTodosTiempoVida(false);
//        }else{
            listaTiempoVida = catTiempoVidaService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
//        }
    }

    public void buscarTodosTiempoVidaEmpresa(boolean activos, Long IdEmpresa) {
        listaTiempoVida = catTiempoVidaService.buscarTodosXEmpresa(activos, IdEmpresa);
    } 
    
    public void guardar() {
        filtroTiempoVida = null;
        
        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
            empresa = empresaSelect.getIdEmpresas();
        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
        }
        
        if (catTiempoVidaService.buscarNombre(tiempoVidaNuevo.getNombre(), tiempoVidaNuevo.getIdValoresTiempoVida(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
                    tiempoVidaNuevo.setIdEmpresa(empresaSelect);
                } else {
                    tiempoVidaNuevo.setIdEmpresa(empresaUsuarioSesion);
                }
                if (genericoService.guardar(tiempoVidaNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/tiempoVida/bandejaTiempoVida.xhtml");
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
            buscarTodosTiempoVida(false);
        } else {
            buscarTodosTiempoVidaEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void editar() {
        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
            tiempoVidaNuevo.setIdEmpresa(empresaSelect);
        } else {
            tiempoVidaNuevo.setIdEmpresa(empresaUsuarioSesion);
        }
        if (genericoService.update(tiempoVidaNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/tiempoVida/bandejaTiempoVida.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cambiaPaginaEdicion(CatValoresTiempoVida sucursales) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/tiempoVida/tiempoVida.xhtml");
        this.tiempoVidaNuevo = sucursales;
        empresaSelect = sucursales.getIdEmpresa();
        listaEmpresas = catEmpresasService.buscarTodos(true);
    }

    public void cancelar() {
        filtroTiempoVida = null;
        tiempoVidaNuevo = new CatValoresTiempoVida();
        getAdministradorPaginas().setPagina("catalogos/tiempoVida/bandejaTiempoVida.xhtml");
        init();
        empresaSelect = new CatEmpresas();
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("catalogos/tiempoVida/tiempoVida.xhtml");
        tiempoVidaNuevo = new CatValoresTiempoVida();
        listaEmpresas = catEmpresasService.buscarTodos(true);
    }

    public CatValoresTiempoVida getTiempoVidaNuevo() {
        return tiempoVidaNuevo;
    }

    public void setTiempoVidaNuevo(CatValoresTiempoVida tiempoVidaNuevo) {
        this.tiempoVidaNuevo = tiempoVidaNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public boolean isPintarCombo() {
        return pintarCombo;
    }

    public void setPintarCombo(boolean pintarCombo) {
        this.pintarCombo = pintarCombo;
    }

    public List<CatValoresTiempoVida> getListaTiempoVida() {
        return listaTiempoVida;
    }

    public void setListaTiempoVida(List<CatValoresTiempoVida> listaTiempoVida) {
        this.listaTiempoVida = listaTiempoVida;
    }

    public List<CatValoresTiempoVida> getFiltroTiempoVida() {
        return filtroTiempoVida;
    }

    public void setFiltroTiempoVida(List<CatValoresTiempoVida> filtroTiempoVida) {
        this.filtroTiempoVida = filtroTiempoVida;
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

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }

    

}
