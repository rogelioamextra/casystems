/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.DatosSolicitudCategorias;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.repositorys.CatCategoriasCamposRepository;
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
@Repository("catCategoriasCamposRepository")
public class CatCategoriasCamposRepositoryImpl extends SimpleRepository implements CatCategoriasCamposRepository {

    private static final Logger logger = LogManager.getLogger(CatCategoriasCamposRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarTodos(boolean activos) {

        List<CatCategoriasCampos> respuesta = new ArrayList<>();

        try {

            if (activos) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.status = true ORDER BY r.nombre").list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r ORDER BY r.nombre").list();
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarRestantes(List<Long> ids, Long idEmpresa) {

        List<CatCategoriasCampos> respuesta = new ArrayList<>();

        try {
            if (ids.isEmpty()) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r  where r.status = true and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu)").setParameter("emp", idEmpresa).setParameter("viu", Constantes.ID_EMPRESA_VIU).list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.status = true and r.idCategoriaCampo not in (:ids) and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu)").setParameterList("ids", ids).setParameter("emp", idEmpresa).setParameter("viu", Constantes.ID_EMPRESA_VIU).list();
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarNombre(String nombre, Long idEmpresa, Long seleccion) {
        List<CatCategoriasCampos> respuesta = new ArrayList<>();

        try {

            if (idEmpresa == null) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.nombre = :nombre and r.idEmpresa.idEmpresas=:seleccion ")
                        .setParameter("nombre", nombre).setParameter("seleccion", seleccion).list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.nombre =: nombre and r.idCategoriaCampo != :id and r.idEmpresa.idEmpresas=:seleccion")
                        .setParameter("nombre", nombre).setParameter("id", idEmpresa).setParameter("seleccion", seleccion).list();
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DatosSolicitudCategorias> buscarCategoriasXproducto(Long idProducto) {
        List<DatosSolicitudCategorias> respuesta = new ArrayList<>();

        try {

            respuesta = (List<DatosSolicitudCategorias>) getSession().createQuery("select r from MvDatosSolicitud sol inner join sol.datosSolicitudCategoriasList r where sol.idConfigSolicitud.idProducto.idProductos=:prod ").setParameter("prod", idProducto).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean deleteCategoriasDatosSolicitud(Long idDatosSolicitud) {
        boolean respuesta = false;
        try {

            getSession().createSQLQuery("delete from datos_solicitud_categorias where id_datos_solicitud=:id").setParameter("id", idDatosSolicitud).executeUpdate();
            respuesta = true;

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean deleteCamposDocumentosDatosSolicitud(Long idDatosSolicitud, Long idCategoria) {
        boolean respuesta = false;
        System.out.println("pasó deleteCamposDocumentosDatosSolicitud, idDatosSolicitud: "+idDatosSolicitud+", idCategoria: "+idCategoria);
        try {

            getSession().createSQLQuery("delete from datos_solicitud_campos where id_datos_solicitud=:id and id_categoria=:cat").setParameter("id", idDatosSolicitud).setParameter("cat", idCategoria).executeUpdate();
            getSession().createSQLQuery("delete from datos_solicitud_documentos where id_datos_solicitud=:id and id_categoria=:cat").setParameter("id", idDatosSolicitud).setParameter("cat", idCategoria).executeUpdate();
             getSession().createSQLQuery("delete from datos_solicitud_avisos where id_datos_solicitud=:id and id_categoria=:cat").setParameter("id", idDatosSolicitud).setParameter("cat", idCategoria).executeUpdate();
            respuesta = true;

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarTodosXEmpresa(boolean activos, Long idEmpresa) {
        List<CatCategoriasCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.status = true and r.idEmpresa.idEmpresas in (1,:id) ORDER BY r.idEmpresa.idEmpresas DESC")
                        .setParameter("id", idEmpresa).list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.idEmpresa.idEmpresas in (1,:id) ORDER BY r.idEmpresa.idEmpresas DESC")
                        .setParameter("id", idEmpresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarTodosXEmpresaYAdmin(boolean activos, Long idEmpresa) {
        List<CatCategoriasCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.status = true and r.idEmpresa.idEmpresas in (:id,1) ORDER BY r.nombre")
                        .setParameter("id", idEmpresa).list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.idEmpresa.idEmpresas in (:id,1) ORDER BY r.nombre")
                        .setParameter("id", idEmpresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<MvConfigSolicitudes> consultaCategoriasActivas(Long idProducto, Long idEmpresa) {
        List<MvConfigSolicitudes> respuesta = new ArrayList<>();
        try {
            respuesta = (List<MvConfigSolicitudes>) getSession().createQuery("select r from MvConfigSolicitudes r where r.idProducto.idProductos=:idProducto and r.idEmpresa.idEmpresas=:idEmpresa")
                    .setParameter("idProducto", idProducto).setParameter("idEmpresa", idEmpresa).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}