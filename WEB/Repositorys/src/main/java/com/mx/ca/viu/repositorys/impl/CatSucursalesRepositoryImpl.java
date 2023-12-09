/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatSucursales;
import com.mx.ca.viu.repositorys.CatSucursalesRepository;
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
@Service(value = "catSucursalesRepository")
public class CatSucursalesRepositoryImpl extends SimpleRepository implements CatSucursalesRepository{
    private static final Logger logger = LogManager.getLogger(CatSucursalesRepositoryImpl.class.getName());
        
    @Override
    @Transactional
    public List<CatSucursales> buscarTodos(boolean activos) {
        List<CatSucursales> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatSucursales>) getSession().createQuery("select r from CatSucursales r where r.status=true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatSucursales>) getSession().createQuery("select r from CatSucursales r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatSucursales> buscarNombre(String nombre, Long id, Long empresa) {
        List<CatSucursales> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatSucursales>) getSession().createQuery("select r from CatSucursales r where r.nombre = :nombre and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatSucursales>) getSession().createQuery("select r from CatSucursales r where r.nombre =:nombre and idSucursales !=: id and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatSucursales> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatSucursales> respuesta = new ArrayList<>();
        
        try {
            if(activos){
                respuesta = (List<CatSucursales>) getSession().createQuery("select r from CatSucursales r where r.status=true and r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();
            }else{
                respuesta = (List<CatSucursales>) getSession().createQuery("select r from CatSucursales r where r.idEmpresa.idEmpresas =:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();
            }
            
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
