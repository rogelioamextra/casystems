/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosAutoridades;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesServiciosARepository;
import com.mx.ca.viu.services.MvResultadosValidacionesServiciosAService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linzunza
 */
@Service(value = "mvResultadosValidacionesServiciosAServiceImpl")
public class MvResultadosValidacionesServiciosAServiceImpl implements MvResultadosValidacionesServiciosAService{

    @Autowired
    MvResultadosValidacionesServiciosARepository mvResultadosValidacionesServiciosARepository;
    
    @Override
    public List<MvResultadosValidacionesServiciosAutoridades> buscarPorServicioIdResultadosValidados(Long idServicio, Long idResultados) {
        return mvResultadosValidacionesServiciosARepository.buscarPorServicioIdResultadosValidados(idServicio,idResultados);
    }
    
}
