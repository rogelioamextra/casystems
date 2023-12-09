/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.repositorys.CatAvisosRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catAvisosRepository")
public class CatAvisosRepositoryImpl extends SimpleRepository implements CatAvisosRepository{
    private static final Logger logger = LogManager.getLogger(CatAvisosRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<MvConfigAvisos> buscarNombre(String nombre, Long id, Long empresa) {
        List<MvConfigAvisos> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.nombre = :nombre and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.nombre = :nombre and r.idConfigAvisos !=: id and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvConfigAvisos> buscarTodos(boolean activos) {
        List<MvConfigAvisos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.status=true ORDER BY r.nombreArchivo").list();
            } else {
                respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r ORDER BY r.nombreArchivo").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvConfigAvisos> buscarTodosXidEmpres(boolean activos, Long id) {
        List<MvConfigAvisos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.idEmpresa.idEmpresas=:empres and r.status=true ORDER BY r.nombreArchivo")
                        .setParameter("empres", id).list();
            } else {
                respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.idEmpresa.idEmpresas=:empres ORDER BY r.nombreArchivo")
                        .setParameter("empres", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatTiposAvisos> buscarTiposAvisos(boolean activos) {
        List<CatTiposAvisos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatTiposAvisos>) getSession().createQuery("select r from CatTiposAvisos r where r.status=true ORDER BY r.nombre ").list();
            } else {
                respuesta = (List<CatTiposAvisos>) getSession().createQuery("select r from CatTiposAvisos r ORDER BY r.nombre").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
