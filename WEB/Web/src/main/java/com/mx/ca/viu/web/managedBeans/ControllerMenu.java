/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.modelos.CatMenus;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.MenusRoles;
import com.mx.ca.viu.web.generico.AdministradorPaginas;

import com.mx.ca.viu.web.generico.AuxMenu;
import com.mx.ca.viu.web.generico.UtilServicios;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author jbecerril
 */
@ManagedBean(name = "controllerMenu")
@javax.faces.bean.ViewScoped
public class ControllerMenu extends UtilServicios {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ControllerMenu.class.getName());

    private MenuModel model;
    private List<AuxMenu> menuOrdenado;
    

    /**
     * Creates a new instance of ControllerMenu
     */
    public ControllerMenu() {

    }

    public void ordenaMenu(CatRoles rol) {
//        menuOrdenado = new ArrayList<>();
//        List<MenusRoles> listaPadres = new ArrayList<>();
//        for (MenusRoles aux : rol.getMenusRolesList()) {
//            if (aux.getIdMenu().getNombreMenu() != null) {
//                if (listaPadres.isEmpty()) {
//                    listaPadres.add(new MenusRoles(catRolesService.buscarmenuPadre(aux.getIdMenu().getNivel()), new CatRoles()));
//
//                } else {
//                    Long id = catRolesService.buscarmenuPadre(aux.getIdMenu().getNivel()).getIdMenu();
//                    Optional<MenusRoles> menu = null;
//                    if (id != null) {
//                        menu = listaPadres.stream().filter(a -> a.getIdMenu().getIdMenu() == id).findFirst();
//                    }
//
//                    if (menu != null && !menu.isPresent()) {
//                        listaPadres.add(new MenusRoles(catRolesService.buscarmenuPadre(aux.getIdMenu().getNivel()), new CatRoles()));
//
//                    }
//                }
//            }
//
//        }
//        rol.getMenusRolesList().addAll(listaPadres);
//        for (MenusRoles aux : rol.getMenusRolesList()) {
//            if (aux.getIdMenu().getNivel() == 1L) {
//
//                menuOrdenado.add(new AuxMenu(aux.getIdMenu(), generahijos(aux.getIdMenu().getIdMenu(), rol.getMenusRolesList())));
//            }
//
//        }
////        menuOrdenado = new ArrayList<>();
////        for (MenusRoles aux : rol.getMenusRolesList()) {
////            if (aux.getIdMenu().getNivel() == 1L) {
////
////                menuOrdenado.add(new AuxMenu(aux.getIdMenu(), generahijos(aux.getIdMenu().getIdMenu(), rol.getMenusRolesList())));
////            }
////
////        }

    }

    public List<CatMenus> generahijos(Long idPadre, List<MenusRoles> listaMenus) {
        List<CatMenus> respuesta = new ArrayList<>();
        for (MenusRoles aux : listaMenus) {
            if (idPadre == aux.getIdMenu().getNivel()) {

                aux.getIdMenu().setSubmenus(generahijos(aux.getIdMenu().getIdMenu(), listaMenus));
                respuesta.add(aux.getIdMenu());
            }
        }

        return respuesta;

//        List<CatMenus> respuesta = new ArrayList<>();
//        for (MenusRoles aux : listaMenus) {
//            if (idPadre == aux.getIdMenu().getNivel()) {
//
//                aux.getIdMenu().setSubmenus(generahijos(aux.getIdMenu().getIdMenu(), listaMenus));
//                respuesta.add(aux.getIdMenu());
//            }
//        }

        //return respuesta;

    }

    public List<MenuElement> generaItemsMenu(List<CatMenus> listasubmenus) {
        List<MenuElement> respuesta = new ArrayList<>();

        for (CatMenus aux : listasubmenus) {

            if (!aux.getSubmenus().isEmpty()) {
                DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                        .label(getAdministradorPaginas().getBUNDLE().getString(aux.getNombreMenu()))
                        .icon(aux.getIcono())
                        .build();
                firstSubmenu.getElements().addAll(generaItemsMenu(aux.getSubmenus()));
                respuesta.add(firstSubmenu);

            } else {
                DefaultMenuItem item = DefaultMenuItem.builder()
                        .value(getAdministradorPaginas().getBUNDLE().getString(aux.getNombreMenu()))
                        .icon(aux.getIcono())
                        .command("#{" + aux.getAccionListener() + "}")
                        .update(aux.getUpdate())
                        .onclick(aux.getOnclick())
                        .build();
                respuesta.add(item);
            }

        }

        return respuesta;
//        List<MenuElement> respuesta = new ArrayList<>();
//
//        for (CatMenus aux : listasubmenus) {
//
//            if (!aux.getSubmenus().isEmpty()) {
//                DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
//                        .label(aux.getNombreMenu())
//                        .icon(aux.getIcono())
//                        .build();
//                firstSubmenu.getElements().addAll(generaItemsMenu(aux.getSubmenus()));
//                respuesta.add(firstSubmenu);
//
//            } else {
//                DefaultMenuItem item = DefaultMenuItem.builder()
//                        .value(aux.getNombreMenu())
//                        .icon(aux.getIcono())
//                        .command("#{" + aux.getAccionListener() + "}")
//                        .update(aux.getUpdate())
//                        .onclick(aux.getOnclick())
//                        .build();
//                respuesta.add(item);
//            }
//
//        }
//
//        return respuesta;
    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
                CatRoles rol = getAdministradorPaginas().getUsuarioLogueado().getIdRol();
                ordenaMenu(rol);

                for (AuxMenu aux : menuOrdenado) {

                    if (!aux.getMenusHijo().isEmpty()) {
                        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                                .label(getAdministradorPaginas().getBUNDLE().getString(aux.getMenuPadre().getNombreMenu()))
                                .icon(aux.getMenuPadre().getIcono())
                                .build();
                        firstSubmenu.getElements().addAll(generaItemsMenu(aux.getMenusHijo()));
                        model.getElements().add(firstSubmenu);

                    } else {
                        DefaultMenuItem item = DefaultMenuItem.builder()
                                .value(getAdministradorPaginas().getBUNDLE().getString(aux.getMenuPadre().getNombreMenu()))
                                .icon(aux.getMenuPadre().getIcono())
                                .command("#{" + aux.getMenuPadre().getAccionListener() + "}")
                                .update(aux.getMenuPadre().getUpdate())
                                .onclick(aux.getMenuPadre().getOnclick())
                                .build();

                        model.getElements().add(item);

                    }

                }
                PrimeFaces.current().ajax().update("menuform:panelMenu");

            }

        }

//        model = new DefaultMenuModel();
//
//        if (getAdministradorPaginas() != null) {
//            if (getAdministradorPaginas().getUsuarioLogueado() != null) {
//                CatRoles rol = getAdministradorPaginas().getUsuarioLogueado().getIdRol();
//                ordenaMenu(rol);
//
//                for (AuxMenu aux : menuOrdenado) {
//
//                    if (!aux.getMenusHijo().isEmpty()) {
//                        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
//                                .label(aux.getMenuPadre().getNombreMenu())
//                                .icon(aux.getMenuPadre().getIcono())
//                                .build();
//                        firstSubmenu.getElements().addAll(generaItemsMenu(aux.getMenusHijo()));
//                        model.getElements().add(firstSubmenu);
//
//                    } else {
//                        DefaultMenuItem item = DefaultMenuItem.builder()
//                                .value(aux.getMenuPadre().getNombreMenu())
//                                .icon(aux.getMenuPadre().getIcono())
//                                .command("#{" + aux.getMenuPadre().getAccionListener() + "}")
//                                .update(aux.getMenuPadre().getUpdate())
//                                .onclick(aux.getMenuPadre().getOnclick())
//                                .build();
//                        model.getElements().add(item);
//
//                    }
//
//                }
//                PrimeFaces.current().ajax().update("menuform:panelMenu");
//
//            }
//        }

    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
}
