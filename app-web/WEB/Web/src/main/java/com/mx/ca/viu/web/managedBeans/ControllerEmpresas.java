/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatEmpresas;

import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerEmpresas")
@javax.faces.bean.ViewScoped
public class ControllerEmpresas extends UtilServicios implements Serializable {

    private CatEmpresas empresaNuevo;
    private boolean banderaEdicion;
    private boolean banderaDisable;
    private boolean banderaMuestraLogo;
    private List<CatEmpresas> listaEmpresas;
    private List<CatEmpresas> filtroEmpresas;
    private CatEmpresas empresaUsuarioSesion;
    private String selectColor;

    public ControllerEmpresas() {
    }

    @PostConstruct
    public void init() {
        banderaMuestraLogo = false;
        empresaNuevo = new CatEmpresas();
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
                    buscarTodosEmpresas(false);
                } else {
                    buscarTodosEmpresaLog(false, empresaUsuarioSesion.getIdEmpresas());
                }
            }
        }
    }

    public void buscarTodosEmpresaLog(boolean activos, Long IdEmpresa) {
        listaEmpresas = catEmpresasService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void buscarTodosEmpresas(boolean activos) {
        if (empresaUsuarioSesion.getIdEmpresas().equals(Constantes.ID_EMPRESA_VIU)) {
            listaEmpresas = catEmpresasService.buscarTodos(activos);
        } else {
            buscarTodosEmpresaLog(false, empresaUsuarioSesion.getIdEmpresas());
        }
    }

    public boolean disable(CatEmpresas empresa) {
        banderaDisable = false;
        if (Objects.equals(empresa.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            banderaDisable = true;
        }
        return banderaDisable;
    }

    public void validaEstatus() {
        if (empresaNuevo.getStatus() == false) {
            if (catUsuariosService.consultaUsuariosXEmpresa(true, empresaNuevo.getIdEmpresas()).isEmpty()) {
                PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
            } else {
                PrimeFaces.current().executeScript("PF('dlgUsuarioConfigurado').show();");
            }
        }
    }

    public void confirmaEstatus(boolean estatus) {
        empresaNuevo.setStatus(estatus);
        PrimeFaces.current().executeScript("PF('dlgEstatus').hide();");
    }

    public void guardar() {
        filtroEmpresas = null;
        if (catEmpresasService.buscarNombre(empresaNuevo.getNombre(), empresaNuevo.getIdEmpresas()).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
                if (!selectColor.isEmpty()) {
                    empresaNuevo.setColor(selectColor);
                }
                if (genericoService.guardar(empresaNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    buscarTodosEmpresas(false);
                    getAdministradorPaginas().setPagina("catalogos/empresas/bandejaEmpresas.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void editar() {
        if (!selectColor.isEmpty()) {
            empresaNuevo.setColor(selectColor);
        }
        if (genericoService.update(empresaNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            buscarTodosEmpresas(false);
            getAdministradorPaginas().setPagina("catalogos/empresas/bandejaEmpresas.xhtml");
            //buscarTodosEmpresaLog(false, empresaUsuarioSesion.getIdEmpresas());
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            try {
                final InputStream in = event.getFile().getInputStream();
                empresaNuevo.setLogo(IOUtils.toByteArray(event.getFile().getInputStream()));
                getAdministradorPaginas().setImagenMuestra(DefaultStreamedContent.builder().contentType(event.getFile().getContentType()).name(event.getFile().getFileName()).stream(() -> in).build());
                banderaMuestraLogo = true;
            } catch (IOException ex) {
                Logger.getLogger(ControllerEmpresas.class.getName()).log(Level.SEVERE, null, ex);
                banderaMuestraLogo = false;
            } finally {
                try {
                    event.getFile().getInputStream().close();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerEmpresas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void cambiaPaginaEdicion(CatEmpresas empresa) {
        if (empresa.getColor() != null) {
            selectColor = empresa.getColor();
        } else {
            selectColor = "";
        }
        if (empresa.getLogo() != null) {
            try {
                final InputStream in = new ByteArrayInputStream(empresa.getLogo());
                getAdministradorPaginas().setImagenMuestra(DefaultStreamedContent.builder().contentType("").name("").stream(() -> in).build());
                banderaMuestraLogo = true;
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ControllerEmpresas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            getAdministradorPaginas().setImagenMuestra(null);
            banderaMuestraLogo = false;
        }
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/empresas/empresas.xhtml");
        this.empresaNuevo = empresa;

    }

    public void cancelar() {
        filtroEmpresas = null;
        empresaNuevo = new CatEmpresas();
        getAdministradorPaginas().setPagina("catalogos/empresas/bandejaEmpresas.xhtml");
        init();
    }

    public void cambiaPaginaNuevo() {
        selectColor = "";
        banderaEdicion = false;
        banderaMuestraLogo = false;
        getAdministradorPaginas().setPagina("catalogos/empresas/empresas.xhtml");
        empresaNuevo = new CatEmpresas();
    }

    public boolean isBanderaMuestraLogo() {
        return banderaMuestraLogo;
    }

    public void setBanderaMuestraLogo(boolean banderaMuestraLogo) {
        this.banderaMuestraLogo = banderaMuestraLogo;
    }
    
    

    public CatEmpresas getEmpresaNuevo() {
        return empresaNuevo;
    }

    public void setEmpresaNuevo(CatEmpresas empresaNuevo) {
        this.empresaNuevo = empresaNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatEmpresas> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<CatEmpresas> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<CatEmpresas> getFiltroEmpresas() {
        return filtroEmpresas;
    }

    public void setFiltroEmpresas(List<CatEmpresas> filtroEmpresas) {
        this.filtroEmpresas = filtroEmpresas;
    }

    public boolean isBanderaDisable() {
        return banderaDisable;
    }

    public void setBanderaDisable(boolean banderaDisable) {
        this.banderaDisable = banderaDisable;
    }

    public String getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(String selectColor) {
        this.selectColor = selectColor;
    }

}
