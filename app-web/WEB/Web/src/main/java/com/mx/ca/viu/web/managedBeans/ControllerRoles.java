/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatMenus;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.MenusRoles;

import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author jbecerril
 */
@ManagedBean(name = "controllerRoles")
@javax.faces.bean.ViewScoped
public class ControllerRoles extends UtilServicios implements Serializable {

    private CatRoles rolNuevo;
    private boolean banderaEdicion;
    private boolean banderaRepetido;
    private List<CatRoles> listaRoles;
    private List<CatRoles> filtroRoles;
//    private List<CatMenus> listaMenusNuevos;
//    private List<CatMenus> listaMenusDisponibles;

    private List<CatEmpresas> listaEmpresas;
    private CatEmpresas empresaSelect;
    private CatEmpresas empresaUsuarioSesion;
    private Long empresa;

    private DualListModel<CatMenus> listaMenus;

    /**
     * Creates a new instance of ControllerRoles
     */
    public ControllerRoles() {
    }

    @PostConstruct
    public void init() {
        rolNuevo = new CatRoles();
        buscarTodosRoles(false);
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosRoles(false);
                buscarTodosRolesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                    listaEmpresas = llenarCombo(listaEmpresas);
                listaEmpresas = new ArrayList<>();
                    listaEmpresas.add(empresaUsuarioSesion);
//                    if(!listaEmpresas.isEmpty()){
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
//                    buscarTodosRolesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void buscaEmpresas() {
        listaEmpresas = catEmpresasService.buscarTodos(true);
    }
    public void validaEstatus() {
        if (rolNuevo.getStatus() == false) {
            if(rolNuevo.getCatUsuariosList().size() <= 0 ){
                PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
            }else{
                rolNuevo.setStatus(true);
                WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.roles.error.inactivar"));
            }
        }
            
    }

    public void buscarTodosRolesXEmpresaFiltro() {
        if(empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))){
            buscarTodosRoles(false);
        }else{
            listaRoles = catRolesService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void buscarTodosRoles(boolean activos) {
        listaRoles = catRolesService.buscarTodos(activos);
    }

    public void buscarTodosRolesEmpresa(boolean activos, Long IdEmpresa) {
        listaRoles = catRolesService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void buscarMenusDisponibles() {
        List<CatMenus> base = new ArrayList<>();
        base = catRolesService.buscarTodosMenusDisponibles();
        if (listaMenus.getTarget() != null && !listaMenus.getTarget().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            listaMenus.getTarget().stream().forEach(aux -> {
                ids.add(aux.getIdMenu());
            });
            base = catRolesService.buscarTodosMenusDisponibles(ids);
            listaMenus.setSource(base);
            ///  listaMenus = new DualListModel<>(base, new ArrayList<>());
        } else {
            listaMenus = new DualListModel<>(base, new ArrayList<>());
        }
    }

    public void guardar() {
        filtroRoles = null;
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }
        if (catRolesService.buscarNombre(rolNuevo.getNombre(), rolNuevo.getIdRol(), rolNuevo.getIdEmpresa().getIdEmpresas()).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
                llenadoMenusRoles();
                if (genericoService.guardar(rolNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("admnistracion/roles/bandejaRoles.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado.rol"));
        }
        //empresaSelect = new CatEmpresas();
    }

    public void llenadoMenusRoles() {
        if (!listaMenus.getTarget().isEmpty()) {
            rolNuevo.setMenusRolesList(new ArrayList<>());
            listaMenus.getTarget().stream().forEach(aux -> {
                MenusRoles r = new MenusRoles();
                r.setIdMenu(aux);
                r.setIdRol(rolNuevo);
                rolNuevo.getMenusRolesList().add(r);
            });

        }
    }

    public void buscarRolesInterno() {
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosRoles(false);
        } else {
            buscarTodosRolesEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void editar() {

        if (catRolesService.eliminarMenusXRol(rolNuevo.getIdRol())) {
            llenadoMenusRoles();
            if (genericoService.update(rolNuevo)) {
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
                init();
                getAdministradorPaginas().setPagina("admnistracion/roles/bandejaRoles.xhtml");
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

        }
     //   empresaSelect = new CatEmpresas();

    }

    public void cambiaPaginaEdicion(CatRoles rol) {

        banderaEdicion = true;
        getAdministradorPaginas().setPagina("admnistracion/roles/roles.xhtml");
        this.rolNuevo = rol;
        //buscarMenusDisponibles();
        listaMenus = new DualListModel<>();

        rol.getMenusRolesList().stream().forEach(aux -> {
            listaMenus.getTarget().add(aux.getIdMenu());

        });
        buscarMenusDisponibles();
//        if (empresaUsuarioSesion.getIdEmpresas() == Constantes.ID_EMPRESA_VIU) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
            listaEmpresas.add(rol.getIdEmpresa());
//        }
        empresaSelect = rol.getIdEmpresa();
    }

    public void cancelar() {
        filtroRoles = null;
        listaMenus = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
        rolNuevo = new CatRoles();
        getAdministradorPaginas().setPagina("admnistracion/roles/bandejaRoles.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        listaMenus = new DualListModel<>();

        buscarMenusDisponibles();
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("admnistracion/roles/roles.xhtml");
        rolNuevo = new CatRoles();

//        if (empresaUsuarioSesion.getIdEmpresas() == Constantes.ID_EMPRESA_VIU) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
//        }else{
            rolNuevo.setIdEmpresa(empresaSelect);
//        }

    }

    public CatRoles getRolNuevo() {
        return rolNuevo;
    }

    public void setRolNuevo(CatRoles rolNuevo) {
        this.rolNuevo = rolNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatRoles> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<CatRoles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<CatRoles> getFiltroRoles() {
        return filtroRoles;
    }

    public void setFiltroRoles(List<CatRoles> filtroRoles) {
        this.filtroRoles = filtroRoles;
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

    public DualListModel<CatMenus> getListaMenus() {
        return listaMenus;
    }

    public void setListaMenus(DualListModel<CatMenus> listaMenus) {
        this.listaMenus = listaMenus;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }
    
    
}
