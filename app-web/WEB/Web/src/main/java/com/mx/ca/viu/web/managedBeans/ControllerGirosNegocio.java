/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
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
@ManagedBean(name = "controllerGirosNegocio")
@javax.faces.bean.ViewScoped
public class ControllerGirosNegocio extends UtilServicios implements Serializable, GenericoCatalogosBeans { 

    private CatGirosNegociosEmpresas nuevo;
    private List<CatGirosNegociosEmpresas> listaNacionalidades;
    private List<CatGirosNegociosEmpresas> filtroNacionalidades;
    private boolean banderaEdicion;

    @PostConstruct
    public void init() {
        listaNacionalidades = genericoService.findAll(CatGirosNegociosEmpresas.class);
    }

    @Override
    public void editar() {
        if (genericoService.update(nuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/giros/bandeja.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

        }

    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatGirosNegociosEmpresas) obj;
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/giros/giros.xhtml");

    }

    @Override
    public void cancelar() {
        filtroNacionalidades = null;
        getAdministradorPaginas().setPagina("catalogos/giros/bandeja.xhtml");
        init();
    }

    @Override
    public void cambiaPaginaNuevo() {
        getAdministradorPaginas().setPagina("catalogos/giros/giros.xhtml");
        banderaEdicion = false;
        nuevo = new CatGirosNegociosEmpresas();
    }

    @Override
    public void guardar() {

        if (banderaEdicion) {
            editar();
        } else {
            if (genericoService.guardar(nuevo)) {
                getAdministradorPaginas().setPagina("catalogos/giros/bandeja.xhtml");
                init();
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        }

    }

    public CatGirosNegociosEmpresas getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatGirosNegociosEmpresas nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatGirosNegociosEmpresas> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<CatGirosNegociosEmpresas> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<CatGirosNegociosEmpresas> getFiltroNacionalidades() {
        return filtroNacionalidades;
    }

    public void setFiltroNacionalidades(List<CatGirosNegociosEmpresas> filtroNacionalidades) {
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
