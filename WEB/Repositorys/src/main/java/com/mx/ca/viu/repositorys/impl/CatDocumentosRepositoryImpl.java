/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatTiposDocumentos;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import com.mx.ca.viu.modelos.MvDocumentosTipos;
import com.mx.ca.viu.repositorys.CatDocumentosRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catDocumentosRepository")
public class CatDocumentosRepositoryImpl extends SimpleRepository implements CatDocumentosRepository {

    private static final Logger logger = LogManager.getLogger(CatDocumentosRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<CatDocumentos> buscarTodos(boolean activos) {
        List<CatDocumentos> respuesta = new ArrayList<>();

        try {
            if (activos) {
                respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r where r.status=true ORDER BY r.nombre").list();

            } else {
                respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r ORDER BY r.nombre").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatDocumentos> buscarNombre(String nombre, Long id, Long seleccion) {
        List<CatDocumentos> respuesta = new ArrayList<>();
        try {
            if (id == null) {
                respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r where r.nombre = :nombre and r.idEmpresa.idEmpresas=:seleccion")
                        .setParameter("nombre", nombre).setParameter("seleccion", seleccion).list();
            } else {
                respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r where r.nombre = :nombre and r.idDocumentos !=: id and r.idEmpresa.idEmpresas=:seleccion")
                        .setParameter("nombre", nombre).setParameter("id", id).setParameter("seleccion", seleccion).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatDocumentos> buscarTodosXidEmpres(Long id) {
        List<CatDocumentos> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r where r.idEmpresa.idEmpresas =: empres ORDER BY r.nombre").setParameter("empres", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatDocumentos> buscarTodosXidEmpresa(Long id) {
        List<CatDocumentos> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r where r.idEmpresa.idEmpresas in (1,:empres) ORDER BY r.nombre").setParameter("empres", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatDocumentos> buscarTodosXidEmpresaYGenerales(Long id) {
        List<CatDocumentos> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r where r.idEmpresa.idEmpresas =:empres OR r.idEmpresa.idEmpresas =1 ORDER BY r.nombre").setParameter("empres", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatValidaciones> buscarTodosValidacionesDisponibles() {
        List<CatValidaciones> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatValidaciones>) getSession().createQuery("select r from CatValidaciones r where r.status=true ").list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatValidaciones> buscarTodosValidacionesDisponibles(List<Long> ids) {
        List<CatValidaciones> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatValidaciones>) getSession().createQuery("select r from CatValidaciones r where r.status=true and r.idValidaciones not in(:ids) ").setParameterList("ids", ids).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean eliminarValidacionesXDocumento(Long id) {
        boolean respuesta = false;
        try {
            getSession().createSQLQuery("delete from dt_configuracion_validaciones where id_documentos=:id").setParameter("id", id).executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatValidaciones> buscarValidacionDocCargado() {
        List<CatValidaciones> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatValidaciones>) getSession().createQuery("select r from CatValidaciones r where r.status=true and r.idValidaciones = 6").list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean actualizaEstatus(Long id, boolean estatus) {
        boolean respuesta = false;
        try {
            getSession().createQuery(" update DtConfiguracionValidaciones r SET r.status = false where r.idDocumentos.idDocumentos = :id ").setParameter("id", id).executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public DtConfiguracionValidaciones validacionesPorDocumentos(Long id, Long val) {
        DtConfiguracionValidaciones respuesta = new DtConfiguracionValidaciones();
        try {
            respuesta = (DtConfiguracionValidaciones) getSession().createQuery("select r from DtConfiguracionValidaciones r where r.idValidaciones.idValidaciones=:val and r.idDocumentos.idDocumentos=:id")
                    .setParameter("val", val).setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public MvDocumentosCategorias buscarDocumentoCategoria(Long documento, Long empresa) {
        MvDocumentosCategorias respuesta = new MvDocumentosCategorias();
        try {
            respuesta = (MvDocumentosCategorias) getSession()
                    .createQuery("select r from MvDocumentosCategorias r where r.idDocumentos.idDocumentos =:documento and r.idDocumentos.idEmpresa.idEmpresas =: empresa")
                    .setParameter("documento", documento).setParameter("empresa", empresa).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public MvDocumentosTipos buscarTipoDocumento(Long documento, Long empresa) {
        MvDocumentosTipos respuesta = new MvDocumentosTipos();
        try {
            respuesta = (MvDocumentosTipos) getSession()
                    .createQuery("select r from MvDocumentosTipos r where r.idDocumentos.idDocumentos =:documento and r.idDocumentos.idEmpresa.idEmpresas =: empresa")
                    .setParameter("documento", documento).setParameter("empresa", empresa).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatTiposDocumentos> buscarTiposDocumentos(boolean activos, Long id) {
        List<CatTiposDocumentos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatTiposDocumentos>) getSession().createQuery("select r from CatTiposDocumentos r where r.status=true and r.idEmpresa.idEmpresas =:id ORDER BY r.nombre")
                        .setParameter("id", id).list();
            } else {
                respuesta = (List<CatTiposDocumentos>) getSession().createQuery("select r from CatTiposDocumentos r where r.idEmpresa.idEmpresas =:id ORDER BY r.nombre").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
