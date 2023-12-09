/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.generico;

import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatMenus;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.MenusRoles;
import com.mx.ca.viu.services.CatRolesService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author jbecerril
 */
@ManagedBean(name = "administradorPaginas")
@SessionScoped
public class AdministradorPaginas extends SpringBeanAutowiringSupport implements Serializable {

    private String pagina;
    private CatUsuarios usuarioLogueado;
    private String idioma = "es_MX";
    private ResourceBundle BUNDLE;
    private CatEmpresas empresaUsuarioSesion;
    private List<AuxMenu> menuOrdenado;
    private List<MenusRoles> listaMenuOr;
    private MenuModel model;
    @Autowired
    public CatRolesService catRolesService;
    private StreamedContent imagenMuestra;
    private String nombreArchivoDocumento;
    private StreamedContent file;
    
    private CatClientes clienteRespaldo;

    /**
     * Creates a new instance of AdministradorPaginas2
     */
    public void reiniciar() {
        init();
        generaMenu();

    }

    public void generaMenu() {
        model = new DefaultMenuModel();

        if (this.usuarioLogueado != null) {
            CatRoles rol = this.usuarioLogueado.getIdRol();
            ordenaMenu(rol);

            for (AuxMenu aux : menuOrdenado) {
                if (aux.getMenuPadre().getStatus()) {
                    if (!aux.getMenusHijo().isEmpty()) {
                        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                                .label(getBUNDLE().getString(aux.getMenuPadre().getNombreMenu()))
                                .icon(aux.getMenuPadre().getIcono())
                                .build();
                        firstSubmenu.getElements().addAll(generaItemsMenu(aux.getMenusHijo()));
                        model.getElements().add(firstSubmenu);

                    } else {
                        DefaultMenuItem item = DefaultMenuItem.builder()
                                .value(getBUNDLE().getString(aux.getMenuPadre().getNombreMenu()))
                                .icon(aux.getMenuPadre().getIcono())
                                .command("#{" + aux.getMenuPadre().getAccionListener() + "}")
                                .update(aux.getMenuPadre().getUpdate())
                                .onclick(aux.getMenuPadre().getOnclick())
                                .build();

                        model.getElements().add(item);

                    }
                }

            }
            PrimeFaces.current().ajax().update("menuform:panelMenu");

        }

    }

    public void ordenaMenu(CatRoles rol) {
        menuOrdenado = new ArrayList<>();
        listaMenuOr = new ArrayList<>();
        listaMenuOr = catRolesService.buscaMenuOrdenado(this.usuarioLogueado.getIdRol().getIdRol());
        List<MenusRoles> listaPadres = new ArrayList<>();
        for (MenusRoles aux : listaMenuOr) {
            if (aux.getIdMenu().getNombreMenu() != null) {
                if (aux.getIdMenu().getNivel() == 1L) {
                    if (listaPadres.isEmpty()) {
                        listaPadres.add(new MenusRoles(aux.getIdMenu(), new CatRoles()));
                    } else {
                        Optional<MenusRoles> resultado = listaPadres.stream().filter(id -> id.getIdMenu().getIdMenu() == aux.getIdMenu().getIdMenu()).findFirst();
                        if (!resultado.isPresent()) {
                            listaPadres.add(new MenusRoles(aux.getIdMenu(), new CatRoles()));
                        }
                    }
                } else {
                    List<Long> ids = new ArrayList<>();
                    for (MenusRoles aux2 : listaPadres) {
                        ids.add(aux2.getIdMenu().getIdMenu());
                    }
                    CatMenus aux2 = catRolesService.buscarmenuPadre(aux.getIdMenu().getNivel(), ids);
                    if (aux2 != null) {
                        listaPadres.add(new MenusRoles(aux2, new CatRoles()));
                    }
                }
            }
        }
        listaMenuOr.addAll(listaPadres);
        HashMap<Long, MenusRoles> filtro = new HashMap<>();

        for (MenusRoles aux : listaMenuOr) {
            filtro.put(aux.getIdMenu().getIdMenu(), aux);
        }

        for (Map.Entry<Long, MenusRoles> aux : filtro.entrySet()) {
            if (aux.getValue().getIdMenu().getNivel() == 1L) {
                menuOrdenado.add(new AuxMenu(aux.getValue().getIdMenu(), generahijos(aux.getValue().getIdMenu().getIdMenu(), listaMenuOr)));
            }
        }
    }

    public List<CatMenus> generahijos(Long idPadre, List<MenusRoles> listaMenus) {
        List<CatMenus> respuesta = new ArrayList<>();
        listaMenus.removeIf(m-> m.getIdMenu().getNombreMenu().equalsIgnoreCase("viu.menu.empresas"));
        for (MenusRoles aux : listaMenus) {
            if (idPadre == aux.getIdMenu().getNivel()) {

                aux.getIdMenu().setSubmenus(generahijos(aux.getIdMenu().getIdMenu(), listaMenus));
                respuesta.add(aux.getIdMenu());
            }
        }

        return respuesta;

    }

    public List<MenuElement> generaItemsMenu(List<CatMenus> listasubmenus) {
        List<MenuElement> respuesta = new ArrayList<>();

        for (CatMenus aux : listasubmenus) {

            if (aux.getStatus()) {
                if (!aux.getSubmenus().isEmpty()) {
                    DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                            .label(getBUNDLE().getString(aux.getNombreMenu()))
                            .icon(aux.getIcono())
                            .build();
                    firstSubmenu.getElements().addAll(generaItemsMenu(aux.getSubmenus()));
                    respuesta.add(firstSubmenu);

                } else {
                    DefaultMenuItem item = DefaultMenuItem.builder()
                            .value(getBUNDLE().getString(aux.getNombreMenu()))
                            .icon(aux.getIcono())
                            .command("#{" + aux.getAccionListener() + "}")
                            .update(aux.getUpdate())
                            .onclick(aux.getOnclick())
                            .build();
                    respuesta.add(item);
                }
            }

        }

        return respuesta;

    }

    public void asignarEmpresa() {
        if (this.usuarioLogueado != null) {
            this.empresaUsuarioSesion = usuarioLogueado.getIdConfiguracionEmpresa().getIdEmpresa();
        }
    }

    public String nombreCompleto() {
        return this.usuarioLogueado.getIdPersona().getApellidoPaterno() + " " + ((this.usuarioLogueado.getIdPersona().getApellidoMaterno() != null) ? this.usuarioLogueado.getIdPersona().getApellidoMaterno() : "") + " " + this.usuarioLogueado.getIdPersona().getNombres();
    }

    public String infoEmpresa() {
        return this.usuarioLogueado.getIdConfiguracionEmpresa().getIdEmpresa().getNombre() + "(" + this.getUsuarioLogueado().getIdRol().getNombre() + ")";
    }

    public void init() {
        //  idioma = "es_MX";
        BUNDLE = ResourceBundle.getBundle("messages.messages_" + idioma);
    }

    public AdministradorPaginas() {
        init();
    }

    public Locale locale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public void changeLang(ValueChangeEvent e) {
        Object newValue = e.getNewValue();
        this.idioma = newValue.toString();
        BUNDLE = ResourceBundle.getBundle("messages.messages_" + idioma);
        // UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        // viewRoot.setLocale(new Locale(newValue.toString()));
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale(idioma));
        // RequestContext.getCurrentInstance().execute("location.reload();"); 
        reiniciar();
        PrimeFaces.current().executeScript("location.reload();");

    }



  
    public void cambioPagina(String pag) {
        pagina = pag + ".xhtml";
    }

    public void cambioPaginaInicio(String pag) {
        pagina = pag + ".xhtml";
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public CatUsuarios getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(CatUsuarios usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ResourceBundle getBUNDLE() {
        return BUNDLE;
    }

    public void setBUNDLE(ResourceBundle BUNDLE) {
        this.BUNDLE = BUNDLE;
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public StreamedContent getImagenMuestra() {
        return imagenMuestra;
    }

    public void setImagenMuestra(StreamedContent imagenMuestra) {
        this.imagenMuestra = imagenMuestra;
    }

    public String getNombreArchivoDocumento() {
        return nombreArchivoDocumento;
    }

    public void setNombreArchivoDocumento(String nombreArchivoDocumento) {
        this.nombreArchivoDocumento = nombreArchivoDocumento;
    }

    public CatClientes getClienteRespaldo() {
        return clienteRespaldo;
    }

    public void setClienteRespaldo(CatClientes clienteRespaldo) {
        this.clienteRespaldo = clienteRespaldo;
    }
    
    

}
