/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosBiometricos;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesServiciosBRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author linzunza
 */
@Repository("mvResultadosValidacionesServiciosBRepositoryImpl")
public class MvResultadosValidacionesServiciosBRepositoryImpl extends SimpleRepository implements MvResultadosValidacionesServiciosBRepository{

    private static final Logger logger = LogManager.getLogger(MvResultadosValidacionesServiciosBRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<MvResultadosValidacionesServiciosBiometricos> buscarPorServicioIdResultadosValidados(Long idServicio, Long idResultados) {
        List<MvResultadosValidacionesServiciosBiometricos> respuesta = new ArrayList<>();
        try {

            respuesta = (List<MvResultadosValidacionesServiciosBiometricos>) getSession().createQuery("select r from MvResultadosValidacionesServiciosBiometricos r WHERE r.idServicioValidacion.idServiciosValidaciones=:idServicio and r.idDatosResultadoValidacion.idDatosValidados=:idResultados")
                    .setParameter("idServicio", idServicio).setParameter("idResultados", idResultados).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
}
