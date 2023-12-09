/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MvConfigNivelRiesgo;
import com.mx.ca.viu.repositorys.MvConfigNivelRiesgoRepository;
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
@Repository("mvConfigNivelRiesgo")
public class MvConfigNivelRiesgoRepositoryImpl extends SimpleRepository implements MvConfigNivelRiesgoRepository{
    
    private static final Logger logger = LogManager.getLogger(MvConfigNivelRiesgoRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<MvConfigNivelRiesgo> buscarConfiguraciones(Long idSolicitud) {
        List<MvConfigNivelRiesgo> respuesta = new ArrayList<>();
        
        try{
            respuesta = (List<MvConfigNivelRiesgo>) getSession().createQuery("select r from MvConfigNivelRiesgo r WHERE r.idConfigSolicitud.idConfigSolicitud =: solicitud")
                    .setParameter("solicitud", idSolicitud).list();
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }
}
