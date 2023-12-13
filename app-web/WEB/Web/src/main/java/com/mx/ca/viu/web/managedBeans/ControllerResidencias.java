/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jbecerril
 */
@ManagedBean(name = "controllerResidencias")
@javax.faces.bean.ViewScoped
public class ControllerResidencias extends UtilServicios implements Serializable, GenericoCatalogosBeans { 

    private CatTiposResidencias nuevo;
    private List<CatTiposResidencias> listaNacionalidades;
    private List<CatTiposResidencias> filtroNacionalidades;
    private boolean banderaEdicion;

    @PostConstruct
    public void init() {
        listaNacionalidades = genericoService.findAll(CatTiposResidencias.class);
    }

    @Override
    public void editar() {
        if (genericoService.update(nuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/residencias/bandeja.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

        }

    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatTiposResidencias) obj;
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/residencias/residencias.xhtml");

    }

    @Override
    public void cancelar() {
        filtroNacionalidades = null;
        getAdministradorPaginas().setPagina("catalogos/residencias/bandeja.xhtml");
        init();
    }

    @Override
    public void cambiaPaginaNuevo() {
        getAdministradorPaginas().setPagina("catalogos/residencias/residencias.xhtml");
        banderaEdicion = false;
        nuevo = new CatTiposResidencias();
    }

    @Override
    public void guardar() {

        if (banderaEdicion) {
            editar();
        } else {
            if (genericoService.guardar(nuevo)) {
                getAdministradorPaginas().setPagina("catalogos/residencias/bandeja.xhtml");
                init();
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        }

    }

    public CatTiposResidencias getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatTiposResidencias nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatTiposResidencias> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<CatTiposResidencias> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<CatTiposResidencias> getFiltroNacionalidades() {
        return filtroNacionalidades;
    }

    public void setFiltroNacionalidades(List<CatTiposResidencias> filtroNacionalidades) {
        this.filtroNacionalidades = filtroNacionalidades;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    @Override
    public void validaEstatus() {
        if (nuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    @Override
    public void confirmaEstatus(boolean estatus) {
        nuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

}
