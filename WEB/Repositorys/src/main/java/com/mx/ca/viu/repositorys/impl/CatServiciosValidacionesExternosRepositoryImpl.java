/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatModulo;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatTipoCampo;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.repositorys.CatCamposRepository;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
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
@Repository("catServiciosValidacionesExternosRepository")
public class CatServiciosValidacionesExternosRepositoryImpl extends SimpleRepository implements CatServiciosValidacionesExternosRepository {

    private static final Logger logger = LogManager.getLogger(CatServiciosValidacionesExternosRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<CatCampos> buscarTodos(boolean activos) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.status = true ORDER BY r.nombre").list();
            } else {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r ORDER BY r.nombre").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public CatServiciosValidacionesExternos buscarConfigFTP() {
        CatServiciosValidacionesExternos respuesta = null;
        try {

            respuesta = (CatServiciosValidacionesExternos) getSession().createQuery("select r from CatServiciosValidacionesExternos r where r.nombre ='FTP' and r.status=false ").uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public CatServiciosValidacionesExternos buscarServicioId(Long id) {
        CatServiciosValidacionesExternos respuesta = null;
        try {

            respuesta = (CatServiciosValidacionesExternos) getSession().createQuery("select r from CatServiciosValidacionesExternos r where r.idServiciosValidaciones=:id").setParameter("id", id).uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatServiciosValidacionesExternos> buscarServiciosXTipo(Long idTipo, CatEmpresas emp, boolean admin) {
        List<CatServiciosValidacionesExternos> respuesta = new ArrayList<>();
        try {

            if (admin) {
                respuesta = (List<CatServiciosValidacionesExternos>) getSession().createQuery("select val.idServicioValidacion from DtValidacionesServiciosEmpresa val where val.idServicioValidacion.idTiposValidacionesDispoibles.idTiposValidaciones=:tipo ").setParameter("tipo", idTipo).list();
                respuesta.addAll((List<CatServiciosValidacionesExternos>) getSession().createQuery("select r from CatServiciosValidacionesExternos r where r.idTiposValidacionesDispoibles.idTiposValidaciones=:tipo ").setParameter("tipo", idTipo).list());

            } else {
                respuesta = (List<CatServiciosValidacionesExternos>) getSession().createQuery("select val.idServicioValidacion from DtValidacionesServiciosEmpresa val where val.idEmpresa=:emp and val.idServicioValidacion.idTiposValidacionesDispoibles.idTiposValidaciones=:tipo ").setParameter("tipo", idTipo).setParameter("emp", emp).list();
                respuesta.addAll((List<CatServiciosValidacionesExternos>) getSession().createQuery("select r from CatServiciosValidacionesExternos r where r.idTiposValidacionesDispoibles.idTiposValidaciones=:tipo  ").setParameter("tipo", idTipo).list());

            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatServiciosValidacionesExternos> buscarTodosServicios(boolean admin, CatEmpresas emp) {
        List<CatServiciosValidacionesExternos> respuesta = new ArrayList<>();
        try {

            if (admin) {
                respuesta = ((List<CatServiciosValidacionesExternos>) getSession().createQuery("select r from CatServiciosValidacionesExternos r WHERE r.idServiciosValidaciones != 3 and r.status = true ORDER BY r.idTiposValidacionesDispoibles.nombre").list());

            } else {
                respuesta = ((List<CatServiciosValidacionesExternos>) getSession().createQuery("select r from CatServiciosValidacionesExternos r WHERE r.idServiciosValidaciones != 3 and r.status = true ORDER BY r.idTiposValidacionesDispoibles.nombre").list());

            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtValidacionesServiciosEmpresa> buscarTodosServiciosGeneral(boolean admin, CatEmpresas emp) {
        List<DtValidacionesServiciosEmpresa> respuesta = new ArrayList<>();
        try {

            if (admin) {
                respuesta = ((List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r WHERE r.idServicioValidacion.idServiciosValidaciones > 0 ORDER BY r.idServicioValidacion.idServiciosValidaciones").list());

            } else {
                respuesta = ((List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r ").list());

            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtValidacionesServiciosEmpresa> buscarTodosServiciosXEmpresa(boolean admin, CatEmpresas emp) {
        List<DtValidacionesServiciosEmpresa> respuesta = new ArrayList<>();
        try {

            if (admin) {
                respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r where r.idEmpresa.idEmpresas =: emp and r.idServicioValidacion.idServiciosValidaciones > 0 and r.idServicioValidacion.idServiciosValidaciones != 3 ORDER BY r.idServicioValidacion.idServiciosValidaciones").setParameter("emp", emp.getIdEmpresas()).list();

            } else {
                respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r where r.idEmpresa.idEmpresas =: emp and r.idServicioValidacion.idServiciosValidaciones > 0 and r.idServicioValidacion.idServiciosValidaciones != 3 ORDER BY r.idServicioValidacion.idServiciosValidaciones").setParameter("emp", emp.getIdEmpresas()).list();

            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtValidacionesServiciosEmpresa> buscarTodosValidacionesXDoc(boolean activos, CatDocumentos doc, CatEmpresas empresa) {
        List<DtValidacionesServiciosEmpresa> respuesta = new ArrayList<>();
        try {

            if (activos) {
                respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r where r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =:empresa ORDER BY r.idEmpresa.idEmpresas").setParameter("doc", doc.getIdDocumentos()).setParameter("empresa", empresa.getIdEmpresas()).list();

            } else {
                respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r where r.idConfigValidacion.idDocumentos.idDocumentos =: doc ORDER BY r.idEmpresa.idEmpresas").setParameter("doc", doc.getIdDocumentos()).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean guardarBancoValidaciones(DtValidacionesServiciosEmpresa servicio, CatEmpresas emp) {

        boolean respuesta = false;
        try {

            DtValidacionesServiciosEmpresa res = (DtValidacionesServiciosEmpresa) getSession().createQuery("select ser from DtValidacionesServiciosEmpresa ser where ser.idEmpresa=:emp and ser.idServicioValidacion.idServiciosValidaciones =: servicio ").setParameter("servicio", servicio.getIdServicioValidacion().getIdServiciosValidaciones()).setParameter("emp", emp).uniqueResult();

            if (res != null && res.getIdServicioValidacion() != null) {
                res.setStatus(servicio.getStatus());
                getSession().update(res);
            } else {
                DtValidacionesServiciosEmpresa nuevo = new DtValidacionesServiciosEmpresa();
                nuevo.setIdEmpresa(emp);
                nuevo.setIdServicioValidacion(servicio.getIdServicioValidacion());
                nuevo.setStatus(servicio.getStatus());
                getSession().save(nuevo);
            }
            respuesta = true;

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean guardarBancoValidaciones(CatServiciosValidacionesExternos servicio, CatEmpresas emp) {

        boolean respuesta = false;
        try {

            DtValidacionesServiciosEmpresa res = (DtValidacionesServiciosEmpresa) getSession().createQuery("select ser from DtValidacionesServiciosEmpresa ser where ser.idEmpresa=:emp and ser.idServicioValidacion=:servicio ").setParameter("servicio", servicio).setParameter("emp", emp).uniqueResult();

            if (res != null && res.getIdServicioValidacion() != null) {
                res.setStatus(servicio.getStatus());
                getSession().update(res);
            } else {
                DtValidacionesServiciosEmpresa nuevo = new DtValidacionesServiciosEmpresa();
                nuevo.setIdEmpresa(emp);
                nuevo.setIdServicioValidacion(servicio);
                nuevo.setStatus(servicio.getStatus());
                getSession().save(nuevo);
            }
            respuesta = true;

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean guardarBancoValidaciones(DtValidacionesServiciosEmpresa servicio, CatEmpresas emp, CatDocumentos doc) {

        boolean respuesta = false;
        try {
            DtValidacionesServiciosEmpresa res = (DtValidacionesServiciosEmpresa) getSession().createQuery("select ser from DtValidacionesServiciosEmpresa ser where ser.idEmpresa=:emp and ser.idConfigValidacion =: configVal ")
                    .setParameter("emp", emp).setParameter("configVal", servicio.getIdConfigValidacion()).uniqueResult();

            if (res != null && res.getIdValidacionServiciosBanco() != null) {
                res.setStatus(servicio.getStatus());
                getSession().update(res);
            } else {
                DtValidacionesServiciosEmpresa nuevo = new DtValidacionesServiciosEmpresa();
                nuevo.setIdEmpresa(emp);
                nuevo.setIdConfigValidacion(servicio.getIdConfigValidacion());
                nuevo.setStatus(servicio.getStatus());
                getSession().save(nuevo);
            }
            respuesta = true;

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtValidacionesServiciosEmpresa> buscarConfigDocXEmpresayDoc(boolean activos, CatDocumentos doc, CatEmpresas empresa) {
        List<DtValidacionesServiciosEmpresa> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r where r.status=true and r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =:empresa ORDER BY r.idEmpresa.idEmpresas").setParameter("doc", doc.getIdDocumentos()).setParameter("empresa", empresa.getIdEmpresas()).list();
            } else {
                respuesta = (List<DtValidacionesServiciosEmpresa>) getSession().createQuery("select r from DtValidacionesServiciosEmpresa r where r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =:empresa ORDER BY r.idEmpresa.idEmpresas").setParameter("doc", doc.getIdDocumentos()).setParameter("empresa", empresa.getIdEmpresas()).list();
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtConfiguracionValidaciones> buscarTodosValidacionesDocXDocyEmp(boolean activos, CatDocumentos doc, CatEmpresas emp) {
        List<DtConfiguracionValidaciones> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<DtConfiguracionValidaciones>) getSession().
                        createQuery("select r.idConfigValidacion from DtValidacionesServiciosEmpresa r WHERE r.status = true and r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =: emp")
                        .setParameter("doc", doc.getIdDocumentos()).setParameter("emp", emp.getIdEmpresas()).list();
            } else {
                respuesta = (List<DtConfiguracionValidaciones>) getSession().
                        createQuery("select r.idConfigValidacion from DtValidacionesServiciosEmpresa r WHERE r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =: emp")
                        .setParameter("doc", doc.getIdDocumentos()).setParameter("emp", emp.getIdEmpresas()).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DatosSolicitudCampos> buscaSolicitudCampos(String idProducto) {
        List<DatosSolicitudCampos> respuesta = new ArrayList<>();
        long number = Integer.parseInt(idProducto);
        try {
            respuesta = (List<DatosSolicitudCampos>) getSession().
                    createQuery("select r from DatosSolicitudCampos r where r.status=true and r.idDatosSolicitud.idConfigSolicitud.idProducto.idProductos=:idProducto ")
                    .setParameter("idProducto", number).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

}
