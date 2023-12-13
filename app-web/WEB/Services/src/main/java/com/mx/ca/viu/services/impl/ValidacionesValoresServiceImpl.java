/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatServiciosValidacionesInternos;
import com.mx.ca.viu.modelos.CatValores;
import com.mx.ca.viu.modelos.ValidacionesValores;
import com.mx.ca.viu.repositorys.ValidacionesValoresRepository;
import com.mx.ca.viu.services.ValidacionesValoresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "validacionesValoresService")
public class ValidacionesValoresServiceImpl implements ValidacionesValoresService{

    @Autowired
    ValidacionesValoresRepository validacionesValoresRepository;
    
    @Override
    public List<ValidacionesValores> buscarValores(Long idValidacion) {
        return validacionesValoresRepository.buscarValores(idValidacion);
    }
    
    @Override
    public List<CatValores> buscarValoresCat(Long idValidacion) {
        return validacionesValoresRepository.buscarValoresCat(idValidacion);
    }
    
    @Override
    public CatServiciosValidacionesInternos buscarValorFinal(Long id) {
        return validacionesValoresRepository.buscarValorFinal(id);
    }
}
