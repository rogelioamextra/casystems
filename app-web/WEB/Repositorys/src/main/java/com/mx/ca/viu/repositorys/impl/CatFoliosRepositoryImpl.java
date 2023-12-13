/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatFolio;
import com.mx.ca.viu.repositorys.CatFoliosRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Repository ("catFoliosRepository")
public class CatFoliosRepositoryImpl extends SimpleRepository implements CatFoliosRepository{
    private static final Logger logger = LogManager.getLogger(CatFoliosRepositoryImpl.class.getName());
        
    @Override
    @Transactional
    public List<CatFolio> buscarTodos(boolean activos) {
        List<CatFolio> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatFolio>) getSession().createQuery("select r from CatFolio r where r.status = true ORDER BY r.nombre").list();
            } else {
                respuesta = (List<CatFolio>) getSession().createQuery("select r from CatFolio r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatFolio> buscarNombre(String nombre, Long id, Long empresa) {
        List<CatFolio> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatFolio>) getSession().createQuery("select r from CatFolio r where r.nombre = :nombre and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatFolio>) getSession().createQuery("select r from CatFolio r where r.nombre = :nombre and r.idFolio !=: id and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatFolio> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatFolio> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatFolio>) getSession().createQuery("select r from CatFolio r where r.status=true and r.idEmpresa.idEmpresas =:empresa ORDER BY r.nombre")
                        .setParameter("empresa", IdEmpresa).list();
            } else {
                respuesta = (List<CatFolio>) getSession().createQuery("select r from CatFolio r where r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre")
                        .setParameter("empresa", IdEmpresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

}
