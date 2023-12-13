/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatServiciosValidacionesInternos;
import com.mx.ca.viu.modelos.CatValores;
import com.mx.ca.viu.modelos.ValidacionesValores;
import com.mx.ca.viu.repositorys.ValidacionesValoresRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.TransactionScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Repository("validacionesValoresRepository")
public class ValidacionesValoresRepositoryImpl extends SimpleRepository implements ValidacionesValoresRepository {

    private static final Logger logger = LogManager.getLogger(ValidacionesValoresRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<ValidacionesValores> buscarValores(Long idValidacion) {
        List<ValidacionesValores> respuesta = new ArrayList<>();
        try {

            respuesta = (List<ValidacionesValores>) getSession().createQuery("select r from ValidacionesValores r WHERE r.idValidacion.idValidaciones =: validacion").setParameter("validacion", idValidacion).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatValores> buscarValoresCat(Long idValidacion) {
        List<CatValores> respuesta = new ArrayList<>();
        try {

            respuesta = (List<CatValores>) getSession().createQuery("select r.idValor from ValidacionesValores r WHERE r.idValidacion.idValidaciones =: validacion").setParameter("validacion", idValidacion).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public CatServiciosValidacionesInternos buscarValorFinal(Long id) {
        CatServiciosValidacionesInternos respuesta = null;
        try {
            respuesta = (CatServiciosValidacionesInternos) getSession().createQuery("select r from CatServiciosValidacionesInternos r WHERE r.idServiciosValidacionesInternos=:id")
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

}
