/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosDigital;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesServiciosDRepository;
import com.mx.ca.viu.services.MvResultadosValidacionesServiciosDService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linzunza
 */
@Service(value = "mvResultadosValidacionesServiciosDServiceImpl")
public class MvResultadosValidacionesServiciosDServiceImpl implements MvResultadosValidacionesServiciosDService{

    @Autowired
    MvResultadosValidacionesServiciosDRepository mvResultadosValidacionesServiciosDRepository;
    
    @Override
    public List<MvResultadosValidacionesServiciosDigital> buscarPorServicioIdResultadosValidados(Long idServicio, Long idResultados) {
        return mvResultadosValidacionesServiciosDRepository.buscarPorServicioIdResultadosValidados(idServicio,idResultados);
    }
    
}
