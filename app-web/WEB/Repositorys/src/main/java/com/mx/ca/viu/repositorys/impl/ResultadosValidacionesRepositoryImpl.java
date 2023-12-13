/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.DtResultadosDatosValidados;
import com.mx.ca.viu.repositorys.ResultadosValidacionesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mramirez
 */
@Service(value = "resultadosValidacionesRepository")
public class ResultadosValidacionesRepositoryImpl extends SimpleRepository implements ResultadosValidacionesRepository {
    private static final Logger logger = LogManager.getLogger(ResultadosValidacionesRepositoryImpl.class.getName());

    @Override
    @Transactional
    public DtResultadosDatosValidados buscaDatosValidados(Long id) {
        DtResultadosDatosValidados respuesta = null;
        try {
            respuesta = (DtResultadosDatosValidados) getSession().createQuery("select r from DtResultadosDatosValidados r where r.idSolicitudProducto.idSolicitudProducto=:id ")
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
}
