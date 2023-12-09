/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatZonas;
import com.mx.ca.viu.repositorys.CatZonasRepository;
import com.mx.ca.viu.services.CatZonasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "catZonasService")
public class CatZonasServiceImpl implements CatZonasService{

    @Autowired
    CatZonasRepository catZonasRepository;
    
    @Override
    public List<CatZonas> buscarTodos(boolean activos) {
        return catZonasRepository.buscarTodos(activos);
    }

    @Override
    public List<CatZonas> buscarTodosXidEmpres(Long id) {
        return catZonasRepository.buscarTodosXidEmpres(id);
    }
    
    @Override
    public List<CatZonas> buscarNombre(String nombre, Long id, Long empresa) {
        return catZonasRepository.buscarNombre(nombre, id, empresa);
    }

    @Override
    public List<CatZonas> buscarTodosXidRegiones(Long idRegion, Long idEmpresa) {
        return catZonasRepository.buscarTodosXidRegiones(idRegion , idEmpresa);
    }
    
    @Override
    public List<CatZonas> buscarNumeroZona(String noZona, Long id, Long empresa) {
        return catZonasRepository.buscarNumeroZona(noZona, id, empresa);
    }
    
}
