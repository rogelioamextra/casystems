/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatProductosCredito;
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
@ManagedBean(name = "controllerProductos")
@javax.faces.bean.ViewScoped
public class ControllerProductos extends UtilServicios implements Serializable, GenericoCatalogosBeans { 

    private CatProductosCredito nuevo;
    private List<CatProductosCredito> listaNacionalidades;
    private List<CatProductosCredito> filtroNacionalidades;
    private boolean banderaEdicion;

    @PostConstruct
    public void init() {
        listaNacionalidades = genericoService.findAll(CatProductosCredito.class);
    }

    @Override
    public void editar() {
        if (genericoService.update(nuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/productos/bandeja.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

        }

    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatProductosCredito) obj;
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/productos/productos.xhtml");

    }

    @Override
    public void cancelar() {
        filtroNacionalidades = null;
        getAdministradorPaginas().setPagina("catalogos/productos/bandeja.xhtml");
        init();
    }

    @Override
    public void cambiaPaginaNuevo() {
        getAdministradorPaginas().setPagina("catalogos/productos/productos.xhtml");
        banderaEdicion = false;
        nuevo = new CatProductosCredito();
    }

    @Override
    public void guardar() {

        if (banderaEdicion) {
            editar();
        } else {
            if (genericoService.guardar(nuevo)) {
                getAdministradorPaginas().setPagina("catalogos/productos/bandeja.xhtml");
                init();
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        }

    }

    public CatProductosCredito getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatProductosCredito nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatProductosCredito> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<CatProductosCredito> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<CatProductosCredito> getFiltroNacionalidades() {
        return filtroNacionalidades;
    }

    public void setFiltroNacionalidades(List<CatProductosCredito> filtroNacionalidades) {
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
