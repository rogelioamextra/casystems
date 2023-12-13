/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatSubdirecciones;
import com.mx.ca.viu.repositorys.CatSubdireccionesRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Repository("catSubdireccionesRepository")
public class CatSubdireccionesRepositoryImpl extends SimpleRepository implements CatSubdireccionesRepository{

    private static final Logger logger = LogManager.getLogger(CatRolesRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<CatSubdirecciones> buscarTodos(boolean activos) {
        List<CatSubdirecciones> respuesta = new ArrayList<>();
        try {
            if(activos){
                respuesta = (List<CatSubdirecciones>) getSession().createQuery("select r from CatSubdirecciones r where r.status = true ORDER BY r.nombre").list();
                
            }else{
                respuesta = (List<CatSubdirecciones>) getSession().createQuery("select r from CatSubdirecciones r ORDER BY r.nombre").list();
                
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatSubdirecciones> buscarNombre(String nombre, Long id, Long empresa) {
        List<CatSubdirecciones> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatSubdirecciones>) getSession().createQuery("select r from CatSubdirecciones r where r.nombre =:nombre and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatSubdirecciones>) getSession().createQuery("select r from CatSubdirecciones r where r.nombre =:nombre and r.idSubdireccion !=:id and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatSubdirecciones> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatSubdirecciones> respuesta = new ArrayList<>();
        
        try{
            if(activos){
                respuesta = (List<CatSubdirecciones>) getSession().createQuery("select r from CatSubdirecciones r where r.status=true and r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter(   "empresa", IdEmpresa).list();
                
            }else{
                respuesta = (List<CatSubdirecciones>) getSession().createQuery("select r from CatSubdirecciones r where r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();
          
            }
        } catch(Exception e){
            logger.error(e);
        }
        
        return respuesta;
    }
   
}
