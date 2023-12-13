/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.modelos.MvAdminNivelRiesgo;
import com.mx.ca.viu.modelos.MvConfigRiesgo;
import com.mx.ca.viu.repositorys.MvConfigRiesgoRepository;
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
@Repository("mvConfigRiesgoRepository")
public class MvConfigRiesgoRepositoryImpl extends SimpleRepository implements MvConfigRiesgoRepository{
    
    private static final Logger logger = LogManager.getLogger(MvConfigRiesgoRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<MvConfigRiesgo> buscarConfiguraciones(Long idConfigSolicitud) {
        List<MvConfigRiesgo> respuesta = new ArrayList<>();
        try { 
//            respuesta = (List<MvConfigRiesgo>)getSession().createQuery("select r from MvConfigRiesgo r ").list();
            respuesta = (List<MvConfigRiesgo>) getSession().createQuery("select r from MvConfigRiesgo r where r.idConfigSolicitud.idConfigSolicitud =:config").setParameter("config", idConfigSolicitud).list();           

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvConfigRiesgo> buscarConfiguracionesXDocumento(Long idConfigSolicitud, Long doc) {
        List<MvConfigRiesgo> respuesta = new ArrayList<>();
        try { 
//            respuesta = (List<MvConfigRiesgo>)getSession().createQuery("select r from MvConfigRiesgo r ").list();
            respuesta = (List<MvConfigRiesgo>) getSession().createQuery("select r from MvConfigRiesgo r where r.status = true and r.idConfigSolicitud.idConfigSolicitud =: config and r.idDocumento.idDocumentos =:doc")
                    .setParameter("config", idConfigSolicitud).setParameter("doc", doc).list();           

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public boolean eliminarRegistro(Long id) {
        boolean respuesta=false;
        try{
            getSession().createQuery("DELETE FROM MvConfigRiesgo m WHERE m.idConfigNivelRiesgo =:id").setParameter("id", id).executeUpdate();
            respuesta = true;
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatServiciosValidacionesExternos> buscarValidaciones() {
        List<CatServiciosValidacionesExternos> respuesta = new ArrayList<>();
        try { 
            respuesta = (List<CatServiciosValidacionesExternos>) getSession().createQuery("select r from CatServiciosValidacionesExternos r where r.status=true")
                    .list();           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvAdminNivelRiesgo> buscarConfiguracion(Long idConfigSolicitud, boolean activos) {
        List<MvAdminNivelRiesgo> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvAdminNivelRiesgo>) getSession().createQuery("select r from MvAdminNivelRiesgo r where r.idConfigSolicitud.idConfigSolicitud= :config and r.status=true")
                    .setParameter("config", idConfigSolicitud).list();
            } else{
                respuesta = (List<MvAdminNivelRiesgo>) getSession().createQuery("select r from MvAdminNivelRiesgo r where r.idConfigSolicitud.idConfigSolicitud= :config")
                    .setParameter("config", idConfigSolicitud).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public boolean eliminaConfiguracion(Long id) {
        boolean respuesta=false;
        try{
            getSession().createQuery("DELETE FROM MvAdminNivelRiesgo m WHERE m.idConfigSolicitud.idConfigSolicitud=:id").setParameter("id", id).executeUpdate();
            respuesta = true;
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtValidacionesServiciosEmpresa> buscarValidacionesEmpresa(Long idEmpresas) {
        List<DtValidacionesServiciosEmpresa> respuesta = new ArrayList<>();
        try { 
            respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r inner join r.idServicioValidacion s where r.idEmpresa.idEmpresas= :idEmpresas order by r.idServicioValidacion.idServiciosValidaciones")
                    .setParameter("idEmpresas", idEmpresas).list();           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
