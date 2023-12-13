/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MvSolicitudProducto;
import com.mx.ca.viu.repositorys.MvSolicitudProductoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author linzunza
 */
@Repository("mvSolicitudProductoRepository")
public class MvSolicitudProductoRepositoryImpl extends SimpleRepository implements MvSolicitudProductoRepository{
       
    private static final Logger logger = LogManager.getLogger(MvSolicitudProductoRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public MvSolicitudProducto buscaBySolicitudProducto(Long idProducto, Long idSolicitud) {
        MvSolicitudProducto respuesta = null;
        try {
            respuesta = (MvSolicitudProducto) getSession().createQuery("select r from MvSolicitudProducto r WHERE r.idConfigSolicitud.idProducto.idProductos =:idProducto and r.idSolicitud=:idSolicitud")
                    .setParameter("idProducto", idProducto).setParameter("idSolicitud", idSolicitud).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
}
