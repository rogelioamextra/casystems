/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatValoresTiempoVida;
import com.mx.ca.viu.repositorys.CatValoresTiempoVidaRepository;
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
@Repository("catValoresTiempoVidaRepository")
public class CatValoresTiempoVidaRepositoryImpl extends SimpleRepository implements CatValoresTiempoVidaRepository{

    private static final Logger logger = LogManager.getLogger(CatValoresTiempoVidaRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<CatValoresTiempoVida> buscarTodos(Long id) {
        
        List<CatValoresTiempoVida> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatValoresTiempoVida>) getSession().createQuery("select r from CatValoresTiempoVida r where r.idEmpresa.idEmpresas in (1,:id) ").setParameter("id", id).list();    
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatValoresTiempoVida> buscarNombre(String nombre, Long id, Long empresa) {
        List<CatValoresTiempoVida> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatValoresTiempoVida>) getSession().createQuery("select r from CatValoresTiempoVida r where r.nombre =:nombre and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("empresa", empresa).list();
            }else{
                respuesta = (List<CatValoresTiempoVida>) getSession().createQuery("select r from CatValoresTiempoVida r where r.nombre =:nombre and r.idValoresTiempoVida !=:id and r.idEmpresa.idEmpresas =:empresa ")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    
    
}
