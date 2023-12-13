/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatMenus;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.MenusRoles;
import com.mx.ca.viu.repositorys.CatRolesRepository;
import com.mx.ca.viu.repositorys.CatUsuariosRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Repository("catRolesRepository")
public class CatRolesRepositoryImpl extends SimpleRepository implements CatRolesRepository {

    private static final Logger logger = LogManager.getLogger(CatRolesRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<CatRoles> buscarTodos(boolean activos) {
        List<CatRoles> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatRoles>) getSession().createQuery("select r from CatRoles r where r.status=true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatRoles>) getSession().createQuery("select r from CatRoles r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public CatMenus buscarmenuPadre(Long nivel, List<Long> ids) {

        CatMenus respuesta = null;

        try {
            if (ids.isEmpty()) {
                respuesta = (CatMenus) getSession().createQuery("select r from CatMenus r where r.status=true and r.idMenu=:nivel").setParameter("nivel", nivel).uniqueResult();
                if (respuesta.getNivel() == 1L) {
                    return respuesta;
                } else {
                    respuesta = buscarmenuPadre(respuesta.getNivel(), ids);
                }
            } else {
                respuesta = (CatMenus) getSession().createQuery("select r from CatMenus r where r.status=true and r.idMenu=:nivel and r.idMenu not in (:lista)").setParameter("nivel", nivel).setParameterList("lista", ids).uniqueResult();
                if (respuesta != null) {
                    if (respuesta.getNivel() == 1L) {
                        return respuesta;
                    } else {
                        respuesta = buscarmenuPadre(respuesta.getNivel(), ids);
                    }
                }

            }

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;

    }

    @Override
    @Transactional
    public List<CatRoles> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatRoles> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatRoles>) getSession().createQuery("select r from CatRoles r where r.status=true and r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();

            } else {
                respuesta = (List<CatRoles>) getSession().createQuery("select r from CatRoles r where r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatRoles> buscarNombre(String nombre, Long id, Long seleccion) {
        List<CatRoles> respuesta = new ArrayList<>();
        try {
            if (id == null) {
                respuesta = (List<CatRoles>) getSession().createQuery("select r from CatRoles r where r.nombre = :nombre and r.idEmpresa.idEmpresas=:seleccion ")
                        .setParameter("nombre", nombre).setParameter("seleccion", seleccion).list();
            } else {
                respuesta = (List<CatRoles>) getSession().createQuery("select r from CatRoles r where r.nombre =:nombre and idRol !=: id and r.idEmpresa.idEmpresas =:seleccion ")
                        .setParameter("nombre", nombre).setParameter("id", id).setParameter("seleccion", seleccion).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatMenus> buscarTodosMenusDisponibles() {
        List<CatMenus> respuesta = new ArrayList<>();

        try {

            respuesta = (List<CatMenus>) getSession().createQuery("select r from CatMenus r where r.status=true and r.banderaAdministracion=true order by r.orden ASC ").list();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatMenus> buscarTodosMenusDisponibles(List<Long> ids) {
        List<CatMenus> respuesta = new ArrayList<>();

        try {

            respuesta = (List<CatMenus>) getSession().createQuery("select r from CatMenus r where r.status=true and  r.banderaAdministracion=true and r.idMenu not in(:ids) order by r.orden ASC").setParameterList("ids", ids).list();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public boolean eliminarMenusXRol(Long idRol) {
        boolean respuesta = false;

        try {

            getSession().createSQLQuery("delete from menus_roles where id_rol=:rol").setParameter("rol", idRol).executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<MenusRoles> buscaMenuOrdenado(Long id) {
        List<MenusRoles> respuesta = new ArrayList<>();

        try {
            respuesta = (List<MenusRoles>) getSession().createQuery("select r from MenusRoles r where r.idRol.idRol=:id ORDER BY r.idMenu.orden ASC").setParameter("id", id).list();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

}
