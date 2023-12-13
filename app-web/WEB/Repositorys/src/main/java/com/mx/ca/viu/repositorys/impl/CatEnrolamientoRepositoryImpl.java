/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.MvDatosSolicitud;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import com.mx.ca.viu.repositorys.CatEnrolamientoRepository;
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
@Repository("catEnrolamientoRepository")
public class CatEnrolamientoRepositoryImpl extends SimpleRepository implements CatEnrolamientoRepository{
    private static final Logger logger = LogManager.getLogger(CatEnrolamientoRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<MvConfigSolicitudes> buscarTodos(boolean activos) {
        List<MvConfigSolicitudes> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.status = true AND r.idProducto.status=true ORDER BY r.idProducto.nombre").list();
            } else {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r ORDER BY r.idProducto.nombre").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvConfigSolicitudes> buscarTodosXEmpresa(boolean activos, Long id) {
        List<MvConfigSolicitudes> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.status = true and r.idProducto.status = true and r.idEmpresa.idEmpresas = :id ")
                        .setParameter("id", id).list();
            } else {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idEmpresa.idEmpresas  = :id ")
                        .setParameter("id", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatCampos> buscarCampos(boolean activos) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCampos>) getSession().createQuery(" select DISTINCT r from CatCampos r left join r.datosSolicitudCamposList where r.status = true ").list();
            } else {
                respuesta = (List<CatCampos>) getSession().createQuery(" select DISTINCT r from CatCampos r left join r.datosSolicitudCamposList ").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatCampos> buscarCamposPorEmpresa(boolean activos, Long id) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
            respuesta = (List<CatCampos>) getSession().createQuery(" select DISTINCT r from CatCampos r left join r.datosSolicitudCamposList where r.status = true and r.idEmpresa.idEmpresas in (1,:id) ")
                    .setParameter("id", id).list();
            } else {
                respuesta = (List<CatCampos>) getSession().createQuery(" select DISTINCT r from CatCampos r left join r.datosSolicitudCamposList where r.idEmpresa.idEmpresas in (1,:id) ")
                    .setParameter("id", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public MvConfigSolicitudes buscarConfigSol(Long id, Long idProd) {
        MvConfigSolicitudes respuesta = new MvConfigSolicitudes();
        try {
            respuesta = (MvConfigSolicitudes) getSession().createQuery(" select DISTINCT r from MvConfigSolicitudes r where r.idEmpresa.idEmpresas in (1,:id) and r.idProducto.idProductos = :idProd")
                    .setParameter("id", id).setParameter("idProd", idProd).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public boolean eliminaConfig(Long id, Long idProd) {
        boolean respuesta = false;
        try {
            getSession().createQuery(" delete from MvDatosSolicitud r where r.idDatosSolicitud = :id and r.idConfigSolicitud.idConfigSolicitud = :idProd")
                    .setParameter("id", id).setParameter("idProd", idProd).executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public boolean actualizaEstatus(boolean estatus, Long idProd, Long id) {
        boolean respuesta = false;
        try {
            if (estatus) {
            getSession().createQuery(" update MvConfigSolicitudes r SET r.status = true where r.idProducto.idProductos = :idProd and r.idEmpresa.idEmpresas = :id ")
                    .setParameter("idProd", idProd).setParameter("id", id).executeUpdate();
            } else{
            getSession().createQuery(" update MvConfigSolicitudes r SET r.status = false where r.idProducto.idProductos = :idProd and r.idEmpresa.idEmpresas = :id ")
                    .setParameter("idProd", idProd).setParameter("id", id).executeUpdate();
            }
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<MvDatosSolicitud> buscarRegistrosMvDatosSolicitud(boolean activos, Long id) {
        List<MvDatosSolicitud> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvDatosSolicitud>) getSession().createQuery("select r from MvDatosSolicitud r where r.idConfigSolicitud.status = true AND r.idConfigSolicitud.idConfigSolicitud =: solicitud")
                        .setParameter("solicitud", id).list();
            } else {
                respuesta = (List<MvDatosSolicitud>) getSession().createQuery("select r from MvDatosSolicitud r where r.idConfigSolicitud.idConfigSolicitud =: solicitud")
                        .setParameter("solicitud", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvDatosSolicitud> buscarMvDatosSolicitudXCat(boolean activos, Long id, Long cat) {
        List<MvDatosSolicitud> respuesta = new ArrayList<>();
        try {
            if (activos) {
//                respuesta = (List<MvDatosSolicitud>) getSession().createQuery("select r from MvDatosSolicitud r where r.idConfigSolicitud.status = true AND r.idConfigSolicitud.idConfigSolicitud =: solicitud and r.idCampo.idCategoria.idCategoriaCampo =:cat")
//                        .setParameter("solicitud", id).setParameter("cat", cat).list();
//            } else {
//                respuesta = (List<MvDatosSolicitud>) getSession().createQuery("select r from MvDatosSolicitud r where r.status = true AND r.idConfigSolicitud.idConfigSolicitud =: solicitud and r.idDocumentoCategoria.idCategoriaCampo.idCategoriaCampo =:cat")
//                        .setParameter("solicitud", id).setParameter("cat", cat).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public boolean deleteConfig(Long idConfig) {
        boolean respuesta = false;
        try {
            getSession().createQuery(" delete from MvDatosSolicitud r where r.idDatosSolicitud =: id")
                    .setParameter("id", idConfig).executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<MvDocumentosCategorias> buscarDocCatPorEmpresaMasAdmin(boolean activos, Long id) {
        List<MvDocumentosCategorias> respuesta = new ArrayList<>();
        try {
            if (activos) {
//            respuesta = (List<MvDocumentosCategorias>) getSession().createQuery("select r from MvDocumentosCategorias r where r.status = true and r.idEmpresa.idEmpresas=:id OR r.idEmpresa.nombre='VIU' ")
//                    .setParameter("id", id).list();
//            } else {
//                respuesta = (List<MvDocumentosCategorias>) getSession().createQuery("select r from MvDocumentosCategorias r where r.idEmpresa.idEmpresas =: id OR r.idEmpresa.nombre = 'VIU' ")
//                    .setParameter("id", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
}
