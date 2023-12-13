/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatEmpresas;
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
@ManagedBean(name = "controllerCategoriasCampos")
@javax.faces.bean.ViewScoped
public class ControllerCategoriasCampos extends UtilServicios implements Serializable {

    private boolean banderaEdicion;
    private CatCategoriasCampos categoriaNuevo;
    private List<CatCategoriasCampos> listaCategorias;
    private List<CatCategoriasCampos> filtroCategorias;
    private List<CatEmpresas> listaEmpresas;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private boolean banderaDisable;
    private Long empresa;

    @PostConstruct
    public void init() {
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    buscarTodasCategorias(false);
                buscatTodasCategoriasXEmpresa(empresaUsuarioSesion.getIdEmpresas());
//                    listaEmpresas = llenarCombo(listaEmpresas);
                    listaEmpresas = new ArrayList<>();
                    listaEmpresas.add(empresaUsuarioSesion);
//                    if (!listaEmpresas.isEmpty()) {
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
//                    buscatTodasCategoriasXEmpresa(empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void buscarTodasCategorias(boolean activos) {
        listaCategorias = catCategoriasCamposService.buscarTodos(activos);
    }

    public void buscatTodasCategoriasXEmpresa(Long idEmpresa) {
        listaCategorias = catCategoriasCamposService.buscarTodosXEmpresa(false, idEmpresa);
    }

    public void buscarTodasCategoriasXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            buscarTodasCategorias(false);
        } else {
            listaCategorias = catCategoriasCamposService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void validaEstatus() {
        if (categoriaNuevo.getStatus() == false) {
            if(categoriaNuevo.getCatCamposList().size() <= 0){
                PrimeFaces.current().executeScript("PF('dlgAdvertenciaProducto').show();");
            }else {
                categoriaNuevo.setStatus(true);
                WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.categorias.campos.error.inactivar"));
            }
        }
    }

    public void guardar() {
        filtroCategorias = null;
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            empresa = empresaSelect.getIdEmpresas();
//        } else {
            empresa = empresaUsuarioSesion.getIdEmpresas();
//        }
        if (catCategoriasCamposService.buscarNombre(categoriaNuevo.getNombre(), categoriaNuevo.getIdCategoriaCampo(), empresaSelect.getIdEmpresas()).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
//                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//                    categoriaNuevo.setIdEmpresa(empresaSelect);
//                } else {
                    categoriaNuevo.setIdEmpresa(empresaUsuarioSesion);
//                }

                if (genericoService.guardar(categoriaNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/categoriasCampos/bandejaCategoriasCampos.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void editar() {
        categoriaNuevo.setIdEmpresa(empresaSelect);
        if (genericoService.update(categoriaNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/categoriasCampos/bandejaCategoriasCampos.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cambiaPaginaEdicion(CatCategoriasCampos categoria) {
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/categoriasCampos/categoriasCampos.xhtml");
        this.categoriaNuevo = categoria;
        empresaSelect = categoria.getIdEmpresa();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public boolean disable(CatCategoriasCampos valor) {
        banderaDisable = false;
        if (Objects.equals(valor.getIdEmpresa().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            banderaDisable = !Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas());
        }
        return banderaDisable;
    }

    public void cancelar() {
        filtroCategorias = null;
        categoriaNuevo = new CatCategoriasCampos();
        getAdministradorPaginas().setPagina("catalogos/categoriasCampos/bandejaCategoriasCampos.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        empresaSelect = empresaUsuarioSesion;
        getAdministradorPaginas().setPagina("catalogos/categoriasCampos/categoriasCampos.xhtml");
        categoriaNuevo = new CatCategoriasCampos();
//        listaEmpresas = catEmpresasService.buscarTodos(true);
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public CatCategoriasCampos getCategoriaNuevo() {
        return categoriaNuevo;
    }

    public void setCategoriaNuevo(CatCategoriasCampos categoriaNuevo) {
        this.categoriaNuevo = categoriaNuevo;
    }

    public List<CatCategoriasCampos> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CatCategoriasCampos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<CatCategoriasCampos> getFiltroCategorias() {
        return filtroCategorias;
    }

    public void setFiltroCategorias(List<CatCategoriasCampos> filtroCategorias) {
        this.filtroCategorias = filtroCategorias;
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

    public CatEmpresas getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(CatEmpresas empresaSelect) {
        this.empresaSelect = empresaSelect;
    }

    public boolean isBanderaDisable() {
        return banderaDisable;
    }

    public void setBanderaDisable(boolean banderaDisable) {
        this.banderaDisable = banderaDisable;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }
    
     
}
