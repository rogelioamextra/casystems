/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosAutoridades;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesServiciosARepository;
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
@Repository("mvResultadosValidacionesServiciosARepository")
public class MvResultadosValidacionesServiciosARepositoryImpl extends SimpleRepository implements MvResultadosValidacionesServiciosARepository{
      
    private static final Logger logger = LogManager.getLogger(MvResultadosValidacionesServiciosARepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<MvResultadosValidacionesServiciosAutoridades> buscarPorServicioIdResultadosValidados(Long idServicio, Long idResultados) {
        List<MvResultadosValidacionesServiciosAutoridades> respuesta = new ArrayList<>();
        try {

            respuesta = (List<MvResultadosValidacionesServiciosAutoridades>) getSession().createQuery("select r from MvResultadosValidacionesServiciosAutoridades r WHERE r.idServicioValidacion.idServiciosValidaciones=:idServicio and r.idDatosResultadoValidacion.idDatosValidados=:idResultados")
                    .setParameter("idServicio", idServicio).setParameter("idResultados", idResultados).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    } 
}
