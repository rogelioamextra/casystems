/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatSubdirecciones;
import com.mx.ca.viu.modelos.CatSubdirecciones;

import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@ManagedBean(name = "controllerSubdirecciones")
@javax.faces.bean.ViewScoped
public class ControllerSubdirecciones extends UtilServicios implements Serializable {

    private CatSubdirecciones subdireccionNuevo;
    private boolean banderaEdicion;
    private List<CatSubdirecciones> listaSubdirecciones;
    private List<CatSubdirecciones> filtrolistaSubdirecciones;
    private boolean banderaRepetido;
    private boolean pintarCombo = false;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private Long empresa;

    public ControllerSubdirecciones() {
    }

    @PostConstruct
    public void init() {
        subdireccionNuevo = new CatSubdirecciones();
        pintarCombo = false;
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosSubdirecciones(false);
//                    pintarCombo = true;
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if (!listaEmpresas.isEmpty()) {
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosSubdireccionesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void validaEstatus() {
        if (subdireccionNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void confirmaEstatus(boolean estatus) {
        subdireccionNuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void buscarTodosSubdireccionesXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            buscarTodosSubdirecciones(false);
        } else {
            listaSubdirecciones = catSubdireccionesService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void buscarTodosSubdirecciones(boolean activos) {
        listaSubdirecciones = catSubdireccionesService.buscarTodos(activos);
    }

    public void buscarTodosSubdireccionesEmpresa(boolean activos, Long IdEmpresa) {
        listaSubdirecciones = catSubdireccionesService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void guardar() {
        filtrolistaSubdirecciones = null;

//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }

        if (catSubdireccionesService.buscarNombre(subdireccionNuevo.getNombre(), subdireccionNuevo.getIdSubdireccion(), empresa).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    subdireccionNuevo.setIdEmpresa(empresaSelect);
//                } else {
                    subdireccionNuevo.setIdEmpresa(empresaUsuarioSesion);
//                }
                if (genericoService.guardar(subdireccionNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/subdirecciones/bandejaSubdirecciones.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.subdirecciones.mensaje.campo.repetido"));
        }
        //empresaSelect = new CatEmpresas();
    }

    public void buscarSubdireccionesInterno() {
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosSubdirecciones(false);
        } else {
            buscarTodosSubdireccionesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void editar() {
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            subdireccionNuevo.setIdEmpresa(empresaSelect);
//        } else {
            subdireccionNuevo.setIdEmpresa(empresaUsuarioSesion);
//        }
        if (genericoService.update(subdireccionNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/subdirecciones/bandejaSubdirecciones.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cambiaPaginaEdicion(CatSubdirecciones subdireccion) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/subdirecciones/subdirecciones.xhtml");
        this.subdireccionNuevo = subdireccion;
        empresaSelect = subdireccion.getIdEmpresa();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public void cancelar() {
        filtrolistaSubdirecciones = null;
        subdireccionNuevo = new CatSubdirecciones();
        getAdministradorPaginas().setPagina("catalogos/subdirecciones/bandejaSubdirecciones.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {

        banderaEdicion = false;
        getAdministradorPaginas().setPagina("catalogos/subdirecciones/subdirecciones.xhtml");
        subdireccionNuevo = new CatSubdirecciones();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public CatSubdirecciones getSubdireccionNuevo() {
        return subdireccionNuevo;
    }

    public void setSubdireccionNuevo(CatSubdirecciones subdireccionNuevo) {
        this.subdireccionNuevo = subdireccionNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatSubdirecciones> getListaSubdirecciones() {
        return listaSubdirecciones;
    }

    public void setListaSubdirecciones(List<CatSubdirecciones> listaSubdirecciones) {
        this.listaSubdirecciones = listaSubdirecciones;
    }

    public List<CatSubdirecciones> getFiltrolistaSubdirecciones() {
        return filtrolistaSubdirecciones;
    }

    public void setFiltrolistaSubdirecciones(List<CatSubdirecciones> filtrolistaSubdirecciones) {
        this.filtrolistaSubdirecciones = filtrolistaSubdirecciones;
    }

    public CatEmpresas getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(CatEmpresas empresaSelect) {
        this.empresaSelect = empresaSelect;
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

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

}
