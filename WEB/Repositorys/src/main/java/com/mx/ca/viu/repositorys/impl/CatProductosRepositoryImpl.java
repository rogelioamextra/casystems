/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.repositorys.CatProductosRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Repository ("catProductosRepository")
public class CatProductosRepositoryImpl extends SimpleRepository implements CatProductosRepository{

    private static final Logger logger = LogManager.getLogger(CatRegionesRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<CatProductos> buscarTodos(boolean activos) {

        List<CatProductos> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r where r.status = true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
        
    }

    @Override
    @Transactional
    public List<CatProductos> buscarNombre(String nombre, Long id, Long seleccion) {
    
        List<CatProductos> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r where r.nombre = :nombre and r.idEmpresa.idEmpresas=:seleccion ")
                    .setParameter("nombre", nombre).setParameter("seleccion", seleccion).list();
            }else{
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r where r.nombre = :nombre and idProductos !=: id and r.idEmpresa.idEmpresas=:seleccion")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("seleccion", seleccion).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
        
    }

    @Override
    @Transactional
    public List<CatProductos> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatProductos> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r where r.status=true and r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();

            } else {
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r where r.idEmpresa.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    
    
}
