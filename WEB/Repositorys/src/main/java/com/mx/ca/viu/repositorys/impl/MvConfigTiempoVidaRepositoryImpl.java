/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MvConfigTiempoVida;
import com.mx.ca.viu.repositorys.MvConfigTiempoVidaRepository;
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
@Repository("catMvConfigTiempoVidaRepository")
public class MvConfigTiempoVidaRepositoryImpl extends SimpleRepository implements MvConfigTiempoVidaRepository{

    private static final Logger logger = LogManager.getLogger(MvConfigTiempoVidaRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<MvConfigTiempoVida> buscarRegistro(Long idConfigSolicitud) {
        
        List<MvConfigTiempoVida> respuesta = new ArrayList<>();
        try {
            respuesta = (List<MvConfigTiempoVida>) getSession().createQuery("select r from MvConfigTiempoVida r WHERE r.idConfigSolicitud.idConfigSolicitud =: idConfigSolicitud")
                    .setParameter("idConfigSolicitud", idConfigSolicitud).list();    
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
}
