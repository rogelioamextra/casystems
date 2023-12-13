/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatMenus;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.MenusRoles;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface CatRolesService {

    public List<CatRoles> buscarTodos(boolean activos);

    public List<CatRoles> buscarTodosXEmpresa(boolean activos, Long IdEmpresa);

    public List<CatMenus> buscarTodosMenusDisponibles();

    public List<CatRoles> buscarNombre(String nombre, Long id, Long seleccion);

    public boolean eliminarMenusXRol(Long idRol);

    public List<CatMenus> buscarTodosMenusDisponibles(List<Long> ids);

    public CatMenus buscarmenuPadre(Long nivel, List<Long> ids);

    public List<MenusRoles> buscaMenuOrdenado(Long id);
}
