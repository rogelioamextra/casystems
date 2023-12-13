/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.repositorys.CatEmpresasRepository;
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
@Repository("catEmpresasRepository")
public class CatEmpresasRepositoryImpl extends SimpleRepository implements CatEmpresasRepository{
    private static final Logger logger = LogManager.getLogger(CatEmpresasRepositoryImpl.class.getName());
        
    @Override
    @Transactional
    public List<CatEmpresas> buscarTodos(boolean activos) {
        List<CatEmpresas> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatEmpresas>) getSession().createQuery("select r from CatEmpresas r where r.status=true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatEmpresas>) getSession().createQuery("select r from CatEmpresas r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatEmpresas> buscarNombre(String nombre, Long id) {
        List<CatEmpresas> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatEmpresas>) getSession().createQuery("select r from CatEmpresas r where r.nombre = :nombre ")
                    .setParameter("nombre", nombre).list();
            }else{
                respuesta = (List<CatEmpresas>) getSession().createQuery("select r from CatEmpresas r where r.nombre = :nombre and idEmpresas !=: id ")
                    .setParameter("nombre", nombre).setParameter("id", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatEmpresas> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatEmpresas> respuesta = new ArrayList<>();
        
        try {
            if(activos){
                respuesta = (List<CatEmpresas>) getSession().createQuery("select r from CatEmpresas r where r.status=true and r.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();
            
            }else{
                respuesta = (List<CatEmpresas>) getSession().createQuery("select r from CatEmpresas r where r.idEmpresas=:empresa ORDER BY r.nombre").setParameter("empresa", IdEmpresa).list();
            
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

}
