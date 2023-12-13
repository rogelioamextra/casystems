/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MensajesValidaciones;
import com.mx.ca.viu.modelos.MvConfigMensaje;
import com.mx.ca.viu.repositorys.MvConfigMensajesRepository;
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
@Repository("mvConfigMensjaesRepository")
public class MvConfigMensajesRepositoryImpl extends SimpleRepository implements MvConfigMensajesRepository{

        private static final Logger logger = LogManager.getLogger(MvConfigMensajesRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<MensajesValidaciones> buscarConfigMensajes(Long idSolicitud, Long estatus) {
        List<MensajesValidaciones> respuesta = new ArrayList<>();
        try {
            respuesta = (List<MensajesValidaciones>) getSession().createQuery("select r from MensajesValidaciones r WHERE r.idMvConfigMensaje.idConfigSolicitud.idConfigSolicitud =: idConfiguracion and r.idMvConfigMensaje.idEstatus.idEstatus=:estatus")
                        .setParameter("idConfiguracion", idSolicitud).setParameter("estatus", estatus).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean eliminarRegistro(Long idConfigMensaje) {
        boolean respuesta=false;
        try{
            getSession().createQuery("DELETE FROM MvConfigMensaje m WHERE m.idMvConfigMensaje =:idConfigMensaje").setParameter("idConfigMensaje", idConfigMensaje).executeUpdate();
            respuesta = true;
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvConfigMensaje> buscaMensaje(Long idSolicitud) {
        List<MvConfigMensaje> respuesta = new ArrayList<>();
        try {
            respuesta = (List<MvConfigMensaje>) getSession().createQuery("select r from MvConfigMensaje r WHERE r.idConfigSolicitud.idConfigSolicitud=: idConfiguracion")
                        .setParameter("idConfiguracion", idSolicitud).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean deleteMensajesValidacionesList(List<Long> idsMensajes) {
        boolean respuesta = false;
        try {

            getSession().createSQLQuery("delete from mensajes_validaciones where id_mensajes_validaciones in(:idsMensajes)").setParameter("idsMensajes", idsMensajes).executeUpdate();
            respuesta = true;

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
}
