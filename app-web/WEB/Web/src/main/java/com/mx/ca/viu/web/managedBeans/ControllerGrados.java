/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
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
@ManagedBean(name = "controllerGrados")
@javax.faces.bean.ViewScoped
public class ControllerGrados extends UtilServicios implements Serializable, GenericoCatalogosBeans { 

    private CatGradoMaximoEstudios nuevo;
    private List<CatGradoMaximoEstudios> listaNacionalidades;
    private List<CatGradoMaximoEstudios> filtroNacionalidades;
    private boolean banderaEdicion;

    @PostConstruct
    public void init() {
        listaNacionalidades = genericoService.findAll(CatGradoMaximoEstudios.class);
    }

    @Override
    public void editar() {
        if (genericoService.update(nuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/grados/bandeja.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));

        }

    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatGradoMaximoEstudios) obj;
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/grados/grados.xhtml");

    }

    @Override
    public void cancelar() {
        filtroNacionalidades = null;
        getAdministradorPaginas().setPagina("catalogos/grados/bandeja.xhtml");
        init();
    }

    @Override
    public void cambiaPaginaNuevo() {
        getAdministradorPaginas().setPagina("catalogos/grados/grados.xhtml");
        banderaEdicion = false;
        nuevo = new CatGradoMaximoEstudios();
    }

    @Override
    public void guardar() {

        if (banderaEdicion) {
            editar();
        } else {
            if (genericoService.guardar(nuevo)) {
                getAdministradorPaginas().setPagina("catalogos/grados/bandeja.xhtml");
                init();
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        }

    }

    public CatGradoMaximoEstudios getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatGradoMaximoEstudios nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatGradoMaximoEstudios> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<CatGradoMaximoEstudios> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<CatGradoMaximoEstudios> getFiltroNacionalidades() {
        return filtroNacionalidades;
    }

    public void setFiltroNacionalidades(List<CatGradoMaximoEstudios> filtroNacionalidades) {
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
