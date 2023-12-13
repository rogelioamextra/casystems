/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatPersonas;
import com.mx.ca.viu.modelos.CatRegiones;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.CatSubdirecciones;
import com.mx.ca.viu.modelos.CatSucursales;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.CatZonas;
import com.mx.ca.viu.modelos.DtConfiguracionEmpresas;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
//import static org.apache.logging.log4j.spi.ThreadContextMapFactory.init;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@ManagedBean(name = "controllerUsuarios")
@javax.faces.bean.ViewScoped
public class ControllerUsuarios extends UtilServicios implements Serializable {

    private CatUsuarios usuarioNuevo;
    private CatPersonas personaNuevo;
    private boolean banderaEdicion;
    private boolean banderaRestablecerPassword;
    private boolean primer_inicio;
    private List<CatUsuarios> listaUsuarios;
    private List<CatPersonas> listaPersonas;
    private List<CatUsuarios> filtroUsuarios;

    private List<CatZonas> listazonas;
    private List<CatRegiones> listaRegiones;
    private List<CatSucursales> listaSucursales;
    private List<CatSubdirecciones> listaSubdirecciones;
    private List<CatEmpresas> listaEmpresas;

    private List<CatRoles> listaRoles;

    private CatEmpresas empresaSelect;
    private CatEmpresas empresaRolSelect;
    private DtConfiguracionEmpresas configuracionEmpresa;
    private boolean banderaContraseña;

    public ControllerUsuarios() {
    }

    @PostConstruct
    public void init() {
        usuarioNuevo = new CatUsuarios();
        banderaContraseña = false;
        buscarTodosUsuarios(false);
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
//                if (Objects.equals(getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodosUsuarios(false);
//                    listaEmpresas = llenarCombo(listaEmpresas);
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(getAdministradorPaginas().getEmpresaUsuarioSesion());
//                    if(!listaEmpresas.isEmpty()){
                empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = getAdministradorPaginas().getEmpresaUsuarioSesion();
                buscarUsuariosPorEmpresa(false, getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas());
//                }
            }
        }
    }

    public void validaEstatus() {
        if (usuarioNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void reestablecer() {
        this.banderaContraseña = false;
        usuarioNuevo.setPassword("");
        usuarioNuevo.setPrimerInicio(true);

    }

    public void buscaEmpresas() {
        listaEmpresas = catEmpresasService.buscarTodos(true);
    }

    public void buscarUsuariosPorEmpresa(boolean activos, Long Idempresa) {
        listaUsuarios = catUsuariosService.buscarTodosEmpresa(activos, Idempresa);
    }

    public void buscarPorUsuarioFiltro() {

        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            buscarTodosUsuarios(false);
        } else {
            listaUsuarios = catUsuariosService.buscarTodosEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void buscarTodosUsuarios(boolean activos) {
        listaUsuarios = catUsuariosService.buscarTodos(activos);
    }

    public void bucarUsuariosInterno() {
        if (Objects.equals(getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosUsuarios(false);
        } else {
            buscarUsuariosPorEmpresa(banderaEdicion, Long.MIN_VALUE);
        }
    }

    public void guardar() {
        filtroUsuarios = null;
        if (catUsuariosService.buscarNombre(usuarioNuevo.getUsername(), usuarioNuevo.getIdUsuario()).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas())) {
//                    usuarioNuevo.getIdConfiguracionEmpresa().setIdEmpresa(empresaRolSelect);
//                } else {
                CatEmpresas emp = genericoService.findByID(CatEmpresas.class, 1L);
                usuarioNuevo.getIdConfiguracionEmpresa().setIdEmpresa(emp);
//                }
                usuarioNuevo.setPassword(UtilGenerico.Encriptar(usuarioNuevo.getPassword()));
                usuarioNuevo.setStatus(true);
                usuarioNuevo.setPrimerInicio(true);
                usuarioNuevo.getIdPersona().setIdNacionalidad(null);
                usuarioNuevo.getIdPersona().setIdEstadoCivil(null);
                usuarioNuevo.getIdPersona().setIdGradoMaximoEstudios(null);

                if (genericoService.guardar(usuarioNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("admnistracion/usuarios/bandejaUsuarios.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
                //}
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.usuario.repetido"));
        }
    }

    public void editar() {

        if (!banderaContraseña) {
            usuarioNuevo.setPassword(UtilGenerico.Encriptar(usuarioNuevo.getPassword()));
        }
        if (genericoService.update(usuarioNuevo)) {
            configuracionEmpresa = new DtConfiguracionEmpresas();
            configuracionEmpresa.setIdConfiguracionEmpresa(usuarioNuevo.getIdConfiguracionEmpresa().getIdConfiguracionEmpresa());
            configuracionEmpresa.setIdEmpresa(empresaRolSelect);
            configuracionEmpresa.setIdRegion(usuarioNuevo.getIdConfiguracionEmpresa().getIdRegion());
            configuracionEmpresa.setIdSubdireccion(usuarioNuevo.getIdConfiguracionEmpresa().getIdSubdireccion());
            configuracionEmpresa.setIdSucursal(usuarioNuevo.getIdConfiguracionEmpresa().getIdSucursal());
            configuracionEmpresa.setIdZona(usuarioNuevo.getIdConfiguracionEmpresa().getIdZona());
            genericoService.update(configuracionEmpresa);
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("admnistracion/usuarios/bandejaUsuarios.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cambiaPaginaEdicion(CatUsuarios usuario) {
        personaNuevo = usuario.getIdPersona();
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("admnistracion/usuarios/usuarios.xhtml");
        this.usuarioNuevo = usuario;
        empresaSelect = usuario.getIdConfiguracionEmpresa().getIdEmpresa();
        empresaRolSelect = usuario.getIdConfiguracionEmpresa().getIdEmpresa();
        llenaCombosEmpresa();
        banderaContraseña = true;
//        if (Objects.equals(getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            buscaEmpresas();
//        }
    }

    public void cancelar() {
        filtroUsuarios = null;
        getAdministradorPaginas().setPagina("admnistracion/usuarios/bandejaUsuarios.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        usuarioNuevo.setPrimerInicio(true);
        banderaEdicion = false;
        primer_inicio = true;
        getAdministradorPaginas().setPagina("admnistracion/usuarios/usuarios.xhtml");
        usuarioNuevo = new CatUsuarios();
        personaNuevo = new CatPersonas();
        usuarioNuevo.setIdPersona(personaNuevo);
//        if (Objects.equals(getAdministradorPaginas().getEmpresaUsuarioSesion().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            buscaEmpresas();
//            empresaRolSelect = null;
//        } else {
        empresaRolSelect = getAdministradorPaginas().getEmpresaUsuarioSesion();
        llenaCombosEmpresaSelect();
//        }

    }

    public void llenaCombosEmpresaSelect() {
        listaSucursales = genericoService.findAll(CatSucursales.class);
        listaRoles = catRolesService.buscarTodosXEmpresa(true, empresaRolSelect.getIdEmpresas());
//        listaRegiones = catRegionesService.buscarTodosXidEmpres(empresaRolSelect.getIdEmpresas());
//        listaSubdirecciones = catSubdireccionesService.buscarTodosXEmpresa(true, empresaRolSelect.getIdEmpresas());
//        listazonas = catZonasService.buscarTodosXidEmpres(empresaRolSelect.getIdEmpresas());

    }

    public void llenaCombosEmpresa() {

        listaRoles = catRolesService.buscarTodosXEmpresa(true, this.usuarioNuevo.getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas());
//        listaRegiones = catRegionesService.buscarTodosXidEmpres(this.usuarioNuevo.getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas());
//        listaSubdirecciones = catSubdireccionesService.buscarTodosXEmpresa(true, this.usuarioNuevo.getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas());
//        listazonas = catZonasService.buscarTodosXidEmpres(this.usuarioNuevo.getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas());
        listaSucursales = genericoService.findAll(CatSucursales.class);

    }

    public void buscarZonasRegion() {
        listazonas = catZonasService.buscarTodosXidRegiones(this.usuarioNuevo.getIdConfiguracionEmpresa().getIdRegion().getIdRegiones(), this.usuarioNuevo.getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas());
    }

    public CatUsuarios getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuariosNuevo(CatUsuarios usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public CatPersonas getPersonasNuevo() {
        return personaNuevo;
    }

    public void setPersonasNuevo(CatPersonas personaNuevo) {
        this.personaNuevo = personaNuevo;
    }

    public boolean isBanderaRestablecerPassword() {
        return banderaRestablecerPassword;
    }

    public void setBanderaRestablecerPassword(boolean banderaRestablecerPassword) {
        this.banderaRestablecerPassword = banderaRestablecerPassword;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public boolean isPrimerInicio() {
        return primer_inicio;
    }

    public void setPrimerInicio(boolean primer_inicio) {
        this.primer_inicio = primer_inicio;
    }

    public List<CatUsuarios> getlistaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<CatUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<CatPersonas> getlistaPersonas() {
        return listaPersonas;
    }

    public void setListaPErsona(List<CatPersonas> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<CatUsuarios> getFiltroUsuarios() {
        return filtroUsuarios;
    }

    public void setFiltroUsuarios(List<CatUsuarios> filtroUsuarios) {
        this.filtroUsuarios = filtroUsuarios;
    }

    public List<CatZonas> getListazonas() {
        return listazonas;
    }

    public void setListazonas(List<CatZonas> listazonas) {
        this.listazonas = listazonas;
    }

    public List<CatRegiones> getListaRegiones() {
        return listaRegiones;
    }

    public void setListaRegiones(List<CatRegiones> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }

    public List<CatSucursales> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<CatSucursales> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }

    public List<CatSubdirecciones> getListaSubdirecciones() {
        return listaSubdirecciones;
    }

    public void setListaSubdirecciones(List<CatSubdirecciones> listaSubdirecciones) {
        this.listaSubdirecciones = listaSubdirecciones;
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

    public List<CatRoles> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<CatRoles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public boolean isBanderaContraseña() {
        return banderaContraseña;
    }

    public void setBanderaContraseña(boolean banderaContraseña) {
        this.banderaContraseña = banderaContraseña;
    }

    public CatEmpresas getEmpresaRolSelect() {
        return empresaRolSelect;
    }

    public void setEmpresaRolSelect(CatEmpresas empresaRolSelect) {
        this.empresaRolSelect = empresaRolSelect;
    }
}
