/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MvConfigTiempoVida;
import com.mx.ca.viu.repositorys.MvConfigTiempoVidaRepository;
import com.mx.ca.viu.services.MvConfigTiempoVidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "mvConfigTiempoVidaService")
public class MvConfigTiempoVidaServiceImpl implements MvConfigTiempoVidaService{

    @Autowired
    MvConfigTiempoVidaRepository mvConfigTiempoVidaRepository;
    
    @Override
    public List<MvConfigTiempoVida> buscarRegistro(Long idConfigTiempoVida) {
        return mvConfigTiempoVidaRepository.buscarRegistro(idConfigTiempoVida);
    }
    
}
