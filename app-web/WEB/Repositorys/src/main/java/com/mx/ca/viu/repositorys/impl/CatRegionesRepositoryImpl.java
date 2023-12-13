/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatRegiones;
import com.mx.ca.viu.repositorys.CatRegionesRepository;
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
@Service(value = "catRegionesRepository")
public class CatRegionesRepositoryImpl extends SimpleRepository implements CatRegionesRepository{
    private static final Logger logger = LogManager.getLogger(CatRegionesRepositoryImpl.class.getName());
        
    @Override
    @Transactional
    public List<CatRegiones> buscarTodos(boolean activos) {
        List<CatRegiones> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatRegiones>) getSession().createQuery("select r from CatRegiones r where r.status=true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatRegiones>) getSession().createQuery("select r from CatRegiones r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatRegiones> buscarNombre(String nombre, Long id, Long empresa) {
        List<CatRegiones> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatRegiones>) getSession().createQuery("select r from CatRegiones r where r.nombre = :nombre and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatRegiones>) getSession().createQuery("select r from CatRegiones r where r.nombre = :nombre and idRegiones !=: id and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatRegiones> buscarTodosXidEmpres(Long id) {
        List<CatRegiones> respuesta = new ArrayList<>();
        try {
                respuesta = (List<CatRegiones>) getSession().createQuery("select r from CatRegiones r where r.idEmpresa.idEmpresas=:empres ORDER BY r.nombre").setParameter("empres", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatRegiones> buscarTodosXidEmpresNA(boolean status ,Long id) {
        List<CatRegiones> respuesta = new ArrayList<>();
        try {
                respuesta = (List<CatRegiones>) getSession().createQuery("select r from CatRegiones r where r.idEmpresa.idEmpresas =:empresa AND r.status=true OR r.idEmpresa.idEmpresas=1 and r.status=true ORDER BY r.nombre").setParameter("empresa", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}



