/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatValoresTiempoVida;
import com.mx.ca.viu.repositorys.CatValoresTiempoVidaRepository;
import com.mx.ca.viu.services.CatValoresTiempoVidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "catTiempoVidaService")
public class CatValoresTiempoVidaServiceImpl implements CatValoresTiempoVidaService{

    @Autowired
    CatValoresTiempoVidaRepository catTiempoVidaRepository;
    
    @Override
    public List<CatValoresTiempoVida> buscarTodos(Long id) {
        return catTiempoVidaRepository.buscarTodos(id);
    }
    
    @Override
    public List<CatValoresTiempoVida> buscarNombre(String nombre, Long id, Long empresa) {
        return catTiempoVidaRepository.buscarNombre(nombre, id, empresa);
    }
}
