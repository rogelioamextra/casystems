/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatColonias;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatMunicipios;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.CodigoPostal;
import com.mx.ca.viu.repositorys.CatEstadoRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mramirez
 */
@Repository("catEstadoRepository")
public class CatEstadoRepositoryImpl extends SimpleRepository implements CatEstadoRepository {

    private static final Logger logger = LogManager.getLogger(CatEstadoRepositoryImpl.class.getName());

    @Override
    @Transactional
    public Estado buscarEstado(String id) {
        Estado respuesta = null;
        try {
            respuesta = (Estado) getSession().createQuery("select r from Estado r where r.inegiClave=:id")
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public CatEstados buscarEstadoCodigo(String codigo) {
        CatEstados respuesta = null;
        try {
            codigo = codigo.length() == 1 ? "0" + codigo : codigo;
            respuesta = (CatEstados) getSession().createQuery("select est from CatEstados est where est.codigoEstado=:codigo ")
                    .setParameter("codigo", codigo).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public Estado buscarEstadoPorSigla(String id) {
        Estado respuesta = null;
        try {
            respuesta = (Estado) getSession().createQuery("select r from Estado r where r.estadoClave=:id")
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<Municipio> buscarMunicipio(Long id) {
        List<Municipio> respuesta = new ArrayList<>();
        try {
            respuesta = (List<Municipio>) getSession().createQuery("select r from Municipio r where r.estadoId.id=:id order by r.nombre")
                    .setParameter("id", id.intValue()).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatMunicipios> buscarCatMunicipio(Long id) {
        List<CatMunicipios> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatMunicipios>) getSession().createQuery("select r from CatMunicipios r where r.idEstado.idEstado=:id order by r.nombre")
                    .setParameter("id", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<Colonia> buscarLocalidad(Long id) {
        List<Colonia> respuesta = new ArrayList<>();
        try {
            respuesta = (List<Colonia>) getSession().createQuery("select r from Colonia r where r.municipioId.id=:id order by r.nombre")
                    .setParameter("id", id.intValue()).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatColonias> buscarCatLocalidad(Long id) {
        List<CatColonias> respuesta = new ArrayList<>();
        try {
            respuesta = (List<CatColonias>) getSession().createQuery("select r from CatColonias r where r.idMunicipio.idMunicipio=:id order by r.nombre")
                    .setParameter("id", id).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public CodigoPostal consultaCP(String id) {
        CodigoPostal respuesta = null;
        try {
            respuesta = (CodigoPostal) getSession().createQuery("select r from CodigoPostal r where r.nombre=:id ")
                    .setParameter("id", id).uniqueResult();

            List<Colonia> lista = (List<Colonia>) getSession().createQuery("select col from Colonia col where col.estadoId.id=:idEstado and col.municipioId.id=:idMunicipio and col.ciudadId.id=:idCiudad ").setParameter("idEstado", respuesta.getEstadoId().getId()).setParameter("idMunicipio", respuesta.getMunicipioId().getId()).setParameter("idCiudad", respuesta.getCiudadId().getId()).list();

            respuesta.setColoniasLista(lista);

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatColonias> consultaCPSepomex(String cp) {
        List<CatColonias> respuesta = null;
        try {
            respuesta = (List<CatColonias>) getSession().createQuery("select r from CatColonias r where r.cp=:cp ")
                    .setParameter("cp", cp).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<Ciudad> consultaCiudadesEstado(Long idEstado) {
        List<Ciudad> respuesta = null;
        try {
            respuesta = (List<Ciudad>) getSession().createQuery("select m from Ciudad m where m.estadoId.id=:id order by m.nombre ")
                    .setParameter("id", idEstado.intValue()).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

}
