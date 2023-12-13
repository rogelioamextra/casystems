/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosBiometricos;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesServiciosBRepository;
import com.mx.ca.viu.services.MvResultadosValidacionesServiciosBService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linzunza
 */
@Service(value = "mvResultadosValidacionesServiciosBServiceImpl")
public class MvResultadosValidacionesServiciosBServiceImpl implements MvResultadosValidacionesServiciosBService{

     @Autowired
    MvResultadosValidacionesServiciosBRepository mvResultadosValidacionesServiciosBRepository;
     
    @Override
    public List<MvResultadosValidacionesServiciosBiometricos> buscarPorServicioIdResultadosValidados(Long idServicio, Long idResultados) {
        return mvResultadosValidacionesServiciosBRepository.buscarPorServicioIdResultadosValidados(idServicio,idResultados);
    }
    
}
