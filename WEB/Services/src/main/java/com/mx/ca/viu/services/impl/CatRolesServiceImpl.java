/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatMenus;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.MenusRoles;
import com.mx.ca.viu.repositorys.CatRolesRepository;
import com.mx.ca.viu.repositorys.CatUsuariosRepository;
import com.mx.ca.viu.services.CatRolesService;
import com.mx.ca.viu.services.CatUsuariosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "catRolesService")
public class CatRolesServiceImpl implements CatRolesService {

    @Autowired
    CatRolesRepository catRolesRepository;

    @Override
    public List<CatRoles> buscarTodos(boolean activos) {
        return catRolesRepository.buscarTodos(activos);
    }

    @Override
    public List<CatMenus> buscarTodosMenusDisponibles() {
        return catRolesRepository.buscarTodosMenusDisponibles();
    }

    @Override
    public boolean eliminarMenusXRol(Long idRol) {
        return catRolesRepository.eliminarMenusXRol(idRol);
    }

    @Override
    public List<CatRoles> buscarNombre(String nombre, Long id, Long seleccion) {
        return catRolesRepository.buscarNombre(nombre, id, seleccion);
    }

    @Override
    public List<CatRoles> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        return catRolesRepository.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    @Override
    public List<CatMenus> buscarTodosMenusDisponibles(List<Long> ids) {
        return catRolesRepository.buscarTodosMenusDisponibles(ids);
    }

    @Override
    public CatMenus buscarmenuPadre(Long nivel,List<Long>ids) {
        return catRolesRepository.buscarmenuPadre(nivel,ids);
    }
    
    @Override
    public List<MenusRoles> buscaMenuOrdenado(Long id){
        return catRolesRepository.buscaMenuOrdenado(id);
    }
}
