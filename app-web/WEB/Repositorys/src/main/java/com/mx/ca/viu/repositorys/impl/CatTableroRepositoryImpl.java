/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.DtResultadosDatosValidados;
import com.mx.ca.viu.repositorys.CatTableroRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mramirez
 */
@Service(value = "catTableroRepository")
public class CatTableroRepositoryImpl extends SimpleRepository implements CatTableroRepository {

    private static final Logger logger = LogManager.getLogger(CatTableroRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<DtResultadosDatosValidados> buscarTodos(boolean activos) {
        List<DtResultadosDatosValidados> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<DtResultadosDatosValidados>) getSession().createQuery("select r from DtResultadosDatosValidados r ORDER BY r.idDatosValidados DESC").list();
            } else {
                respuesta = (List<DtResultadosDatosValidados>) getSession().createQuery("select r from DtResultadosDatosValidados r ORDER BY r.idDatosValidados DESC").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtResultadosDatosValidados> buscarTodosPorEmpresa(boolean activos, Long empresa) {
        List<DtResultadosDatosValidados> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<DtResultadosDatosValidados>) getSession().createQuery("select r from DtResultadosDatosValidados r where r.idSolicitudProducto.idConfigSolicitud.idEmpresa.idEmpresas =:empresa ORDER BY r.idDatosValidados DESC")
                        .setParameter("empresa", empresa).list();
            } else {
                respuesta = (List<DtResultadosDatosValidados>) getSession().createQuery("select r from DtResultadosDatosValidados r where r.idSolicitudProducto.idConfigSolicitud.idEmpresa.idEmpresas =:empresa ORDER BY r.idDatosValidados DESC")
                        .setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatProductos> buscarProductos(Long empresa) {
        List<CatProductos> respuesta = new ArrayList<>();
        try {
            if (empresa == 1) {
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r order by r.nombre ASC ").list();
            } else {
                respuesta = (List<CatProductos>) getSession().createQuery("select r from CatProductos r where r.idEmpresa.idEmpresas=:empresa order by r.nombre ASC ")
                        .setParameter("empresa", empresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatEstatus> buscarEstatus(boolean activos) {
        List<CatEstatus> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatEstatus>) getSession().createQuery("select r from CatEstatus r where r.status=true ORDER BY r.idEstatus ASC ").list();
            } else {
                respuesta = (List<CatEstatus>) getSession().createQuery("select r from CatEstatus r ORDER BY r.idEstatus ASC").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtResultadosDatosValidados> buscarTodosPorFiltro(Long empresa, Long estatus, Long producto, String fechaIni, String fechaFin) {
        List<DtResultadosDatosValidados> respuesta = new ArrayList<>();
        try {
            String filtro = "";
            Query query;
            if (empresa != null && empresa != 0) {
                filtro += " and r.idSolicitudProducto.idConfigSolicitud.idEmpresa.idEmpresas =:empresa ";
            }
            if (estatus != null && estatus != 0) {
                filtro += " and r.idEstatusSolicitud.idEstatus=:estatus ";
            }
            if (producto != null && producto != 0) {
                filtro += " and r.idSolicitudProducto.idConfigSolicitud.idProducto.idProductos=:producto ";
            }
            if (!"".equals(fechaIni) && fechaIni != null) {
                filtro += " and r.idSolicitudProducto.fecha >= DATE(:fechaIni) ";
            }
            if (!"".equals(fechaFin) && fechaFin != null) {
                filtro += " and r.idSolicitudProducto.fecha <= DATE(:fechaFin) ";
            }
            query = getSession().createQuery(" select r from DtResultadosDatosValidados r where 1=1 " + filtro);
            if (empresa != null && empresa != 0) {
                query.setParameter("empresa", empresa);
            }
            if (estatus != null && estatus != 0) {
                query.setParameter("estatus", estatus);
            }
            if (producto != null && producto != 0) {
                query.setParameter("producto", producto);
            }
            if (!"".equals(fechaIni) && fechaIni != null) {
                query.setParameter("fechaIni", fechaIni);
            }
            if (!"".equals(fechaFin) && fechaFin != null) {
                query.setParameter("fechaFin", fechaFin);
            }
            respuesta = query.list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
