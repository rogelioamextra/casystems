/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatZonas;
import com.mx.ca.viu.repositorys.CatZonasRepository;
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
@Repository("catZonasRepository")
public class CatZonasRepositoryImpl extends SimpleRepository implements CatZonasRepository{
    private static final Logger logger = LogManager.getLogger(CatEmpresasRepositoryImpl.class.getName());
        

    @Override
    @Transactional
    public List<CatZonas> buscarTodos(boolean activos) {
        List<CatZonas> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where  r.status=true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatZonas> buscarNombre(String nombre, Long id, Long empresa) {
        List<CatZonas> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where r.nombre = :nombre and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where r.nombre = :nombre and idZona !=: id and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatZonas> buscarTodosXidEmpres(Long id) {
        List<CatZonas> respuesta = new ArrayList<>();
        try {
                respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where  r.idEmpresa.idEmpresas=:empres ORDER BY r.nombre").setParameter("empres", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatZonas> buscarTodosXidRegiones(Long idRegion, Long idEmpresa) {
        List<CatZonas> respuesta = new ArrayList<>();
        try {
                respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where  r.idRegion.idRegiones=:region and r.idEmpresa.idEmpresas=:empresa and r.status=true ORDER BY r.nombre").setParameter("region", idRegion).setParameter("empresa", idEmpresa).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatZonas> buscarNumeroZona(String noZona, Long id, Long empresa) {
        List<CatZonas> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where r.noZona = :noZona and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("noZona", noZona).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatZonas>) getSession().createQuery("select r from CatZonas r where r.noZona = :noZona and idZona !=: id and r.idEmpresa.idEmpresas =:empresa")
                    .setParameter("noZona", noZona).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
