/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.DatosSolicitudCategorias;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.repositorys.MvConfigSolicitudesRepository;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Repository("mvConfigRepository")
public class MvConfigSolicitudesRepositoryImpl extends SimpleRepository implements MvConfigSolicitudesRepository {

    private static final Logger logger = LogManager.getLogger(MvConfigSolicitudesRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<MvConfigSolicitudes> buscarTodos(boolean activos) {
        List<MvConfigSolicitudes> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idProducto.status = true ORDER BY r.idProducto.nombre").list();
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
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idProducto.status=true and r.idEmpresa.idEmpresas =: empresa ORDER BY r.idProducto.nombre").setParameter("empresa", id).list();
            } else {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idEmpresa.idEmpresas =: empresa ORDER BY r.idProducto.nombre").setParameter("empresa", id).list();
                //respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idEmpresas=:empresas").setParameter("empresa", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public MvConfigSolicitudes buscarTodosXProducto(Long id) {
        MvConfigSolicitudes respuesta = null;
        try {
            respuesta = (MvConfigSolicitudes) getSession().createQuery("select r from MvConfigSolicitudes r where r.status=true and r.idProducto.idProductos =:producto ").setParameter("producto", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public MvConfigSolicitudes buscarTodosXProductoList(Long id) {
        MvConfigSolicitudes respuesta = null;
        try {
            respuesta = (MvConfigSolicitudes) getSession().createQuery("select r from MvConfigSolicitudes r where r.idProducto.status=true and r.idProducto.idProductos =:producto ")
                    .setParameter("producto", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarTodasCategoriasDatosSolicitud(Long id) {
         List<CatCategoriasCampos> respuesta = new ArrayList<>();
        try {
            respuesta = ( List<CatCategoriasCampos>) getSession().createQuery("select cat.idCategoria from MvDatosSolicitud r inner join r.datosSolicitudCategoriasList cat where r.idDatosSolicitud =:id ")
                    .setParameter("id", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<MvConfigSolicitudes> buscarConfigXEmpresaYProducto(boolean activos, Long idEmpresa, Long idProducto) {
        List<MvConfigSolicitudes> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idProducto.status=true and r.idEmpresa.idEmpresas =: empresa AND r.idProducto.idProductos =: producto")
                        .setParameter("empresa", idEmpresa).setParameter("producto", idProducto).list();
            } else {
                respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idEmpresa.idEmpresas =: empresa AND r.idProducto.idProductos =: producto")
                        .setParameter("empresa", idEmpresa).setParameter("producto", idProducto).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public MvConfigSolicitudes buscaIdConfiguracionProducto(Long id) {
        MvConfigSolicitudes respuesta = null;
        try {
            respuesta = (MvConfigSolicitudes) getSession().createQuery("select r from MvConfigSolicitudes r where r.idProducto.idProductos=:id ")
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public Long obtieneSecuencia() {
        Long respuesta = 0L;
        try {
            Query query = getSession().createSQLQuery("SELECT nextval('mv_solicitud_producto_id_solicitud_seq')");
            respuesta = ((BigInteger) query.uniqueResult()).longValue();;
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

}
