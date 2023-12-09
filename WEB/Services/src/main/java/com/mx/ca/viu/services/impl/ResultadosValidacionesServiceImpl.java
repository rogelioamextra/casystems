/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.DtResultadosDatosValidados;
import com.mx.ca.viu.repositorys.ResultadosValidacionesRepository;
import com.mx.ca.viu.services.ResultadosValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mramirez
 */
@Service(value = "resultadosValidacionesService")
public class ResultadosValidacionesServiceImpl implements ResultadosValidacionesService{
    @Autowired
    ResultadosValidacionesRepository resultadosValidacionesRepository;
    
    @Override
    public DtResultadosDatosValidados buscaDatosValidados(Long id) {
        return resultadosValidacionesRepository.buscaDatosValidados(id);
    }
}
