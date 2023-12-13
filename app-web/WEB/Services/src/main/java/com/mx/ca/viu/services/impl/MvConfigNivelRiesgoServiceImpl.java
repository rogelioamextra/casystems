/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MvConfigNivelRiesgo;
import com.mx.ca.viu.repositorys.MvConfigNivelRiesgoRepository;
import com.mx.ca.viu.services.MvConfigNivelRiesgoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "mvConfigNivelRiesgoService")
public class MvConfigNivelRiesgoServiceImpl implements MvConfigNivelRiesgoService{
    
    @Autowired
    MvConfigNivelRiesgoRepository mvConfigNivelRiesgoRepository;

    @Override
    public List<MvConfigNivelRiesgo> buscarConfiguraciones(Long idSolicitud) {
        return mvConfigNivelRiesgoRepository.buscarConfiguraciones(idSolicitud);
    }
}
