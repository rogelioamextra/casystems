/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatRegiones;
import com.mx.ca.viu.modelos.CatZonas;

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
@ManagedBean(name = "controllerZonas")
@javax.faces.bean.ViewScoped
public class ControllerZonas extends UtilServicios implements Serializable {

    private CatZonas zonaNuevo;
    private boolean banderaEdicion;
    private List<CatZonas> listaZonas;
    private List<CatZonas> filtroZonas;
    private boolean pintarCombo;
    private CatEmpresas idEmpresaUsuario;
    private List<CatEmpresas> listaEmpresas;
    private CatRegiones regionSelect;
    private List<CatRegiones> listaRegiones;
    private CatEmpresas empresaSelect;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas auxSelect;
    private Long empresa;

    public ControllerZonas() {
    }

    @PostConstruct
    public void init() {
        zonaNuevo = new CatZonas();
        pintarCombo = false;

        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    buscarTodosZonas(false);
//                    pintarCombo = true;
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas);
//                    if (!listaEmpresas.isEmpty()) {
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosZonasXEmpresa(empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }

    }

    public void validaEstatus() {
        if (zonaNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void confirmaEstatus(boolean estatus) {
        zonaNuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void buscarTodosZonas(boolean activos) {
        listaZonas = catZonasService.buscarTodos(activos);
    }

    public void buscarTodosZonasXEmpresa(Long id) {
        listaZonas = catZonasService.buscarTodosXidEmpres(id);
    }

    public void buscarTodosZonasXEmpresaFiltro() {
        //auxSelect.geIdEmp == 1 buscarTodos!=null
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            buscarTodosZonas(false);
        } else {
            listaZonas = catZonasService.buscarTodosXidEmpres(empresaSelect.getIdEmpresas());
        }
    }

    public void buscarTodosRegionesXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaRegiones = catRegionesService.buscarTodos(true);

        } else {
            listaRegiones = catRegionesService.buscarTodosXidEmpresNA(true,empresaSelect.getIdEmpresas());
        }
    }

    public void buscarZonasInterno() {
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            buscarTodosZonas(false);
        } else {
            buscarTodosZonasXEmpresa(empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public void guardar() {
        filtroZonas = null;
        
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }

        if (catZonasService.buscarNombre(zonaNuevo.getNombre(), zonaNuevo.getIdZona(), empresa).isEmpty()) {
            if (catZonasService.buscarNumeroZona(zonaNuevo.getNoZona(), zonaNuevo.getIdZona(), empresa).isEmpty()) {
                if (banderaEdicion) {
                    editar();
                } else {
//                    if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                        zonaNuevo.setIdEmpresa(empresaSelect);
//                    } else {
                        zonaNuevo.setIdEmpresa(empresaUsuarioSesion);
//                    }
                    if (regionSelect != null) {
                        zonaNuevo.setIdRegion(regionSelect);
                    }
                    if (genericoService.guardar(zonaNuevo)) {
                        WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                        init();
                        getAdministradorPaginas().setPagina("catalogos/zonas/bandejaZonas.xhtml");
                    } else {
                        WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                    }
                }
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.zonas.existe.numero"));
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.zonas.existe.nombre"));
        }
    }

    public void editar() {
        if (regionSelect != null) {
//            if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                zonaNuevo.setIdEmpresa(empresaSelect);
//            } else {
                zonaNuevo.setIdEmpresa(empresaUsuarioSesion);
//            }
            zonaNuevo.setIdRegion(regionSelect);
            if (genericoService.update(zonaNuevo)) {
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
                init();
                getAdministradorPaginas().setPagina("catalogos/zonas/bandejaZonas.xhtml");
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.zonas.region.vacia"));
        }
    }

    public void cambiaPaginaEdicion(CatZonas zona) {

//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            listaRegiones = catRegionesService.buscarTodosXidEmpres(zona.getIdEmpresa().getIdEmpresas());
//        } else {
            listaRegiones = catRegionesService.buscarTodosXidEmpresNA(true, zona.getIdEmpresa().getIdEmpresas());
//        }
        regionSelect = zona.getIdRegion();
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/zonas/zonas.xhtml");
        this.zonaNuevo = zona;
        empresaSelect = zona.getIdEmpresa();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public void cancelar() {
        filtroZonas = null;
        getAdministradorPaginas().setPagina("catalogos/zonas/bandejaZonas.xhtml");
        init();
        regionSelect = null;
        empresaSelect = null;
    }

    public void cambiaPaginaNuevo() {
        listaEmpresas = catEmpresasService.buscarTodosXEmpresa(true, 1L);
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            //listaRegiones = catRegionesService.buscarTodos(true);
//            if (empresaSelect != null) {
//                listaRegiones = catRegionesService.buscarTodosXidEmpres(empresaSelect.getIdEmpresas());
//            }
//        } else {
            //listaRegiones = null;
            listaRegiones = catRegionesService.buscarTodosXidEmpres(empresaUsuarioSesion.getIdEmpresas());
//        }
        zonaNuevo = new CatZonas();
        zonaNuevo.setIdRegion(new CatRegiones());
        banderaEdicion = false;
        getAdministradorPaginas().setPagina("catalogos/zonas/zonas.xhtml");
        /*listaRegiones = new ArrayList<>();
        List<CatRegiones>listaAux=  catRegionesService.buscarTodosXidEmpres(idEmpresaUsuario.getIdEmpresas());
        if(!listaAux.isEmpty()){
        listaAux.stream().forEach(aux -> {
            //listaRegiones.add(aux.getIdRegiones());

        });
        
    
        }*/
        //  zonaSelect = listaZonas.stream().filter(zon->zon.getIdZona().getIdRegion().getIdRegiones() == zonaNuevo.getCatZonasEmpresasList().get(0).getIdZona().getIdRegion().getIdRegiones()).findFirst().get();
//        sucursalSelect = listaSucursales.stream().filter(sucursal->sucursal.getIdSucursal().getIdSucursales() == usuario.getCatUsuariosEmpresasList().get(0).getIdSucursal().getIdSucursales()).findFirst().get();
//        zonaNuevo.setIdRegion(zonaSelect.getIdZona().getIdRegion());
    }

    public CatZonas getZonaNuevo() {
        return zonaNuevo;
    }

    public void setZonaNuevo(CatZonas zonaNuevo) {
        this.zonaNuevo = zonaNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatZonas> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(List<CatZonas> listaZonas) {
        this.listaZonas = listaZonas;
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

    public List<CatZonas> getFiltroZonas() {
        return filtroZonas;
    }

    public void setFiltroZonas(List<CatZonas> filtroZonas) {
        this.filtroZonas = filtroZonas;
    }

    public boolean isPintarCombo() {
        return pintarCombo;
    }

    public void setPintarCombo(boolean pintarCombo) {
        this.pintarCombo = pintarCombo;
    }

    public CatRegiones getRegionSelect() {
        return regionSelect;
    }

    public void setRegionSelect(CatRegiones regionSelect) {
        this.regionSelect = regionSelect;
    }

    public List<CatRegiones> getListaRegiones() {
        return listaRegiones;
    }

    public void setListaRegiones(List<CatRegiones> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }

    public CatEmpresas getIdEmpresaUsuario() {
        return idEmpresaUsuario;
    }

    public void setIdEmpresaUsuario(CatEmpresas idEmpresaUsuario) {
        this.idEmpresaUsuario = idEmpresaUsuario;
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

    public CatEmpresas getAuxSelect() {
        return auxSelect;
    }

    public void setAuxSelect(CatEmpresas auxSelect) {
        this.auxSelect = auxSelect;
    }

}
