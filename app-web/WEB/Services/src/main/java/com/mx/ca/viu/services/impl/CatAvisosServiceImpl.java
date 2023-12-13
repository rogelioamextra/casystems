/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.repositorys.CatAvisosRepository;
import com.mx.ca.viu.services.CatAvisosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catAvisosService")
public class CatAvisosServiceImpl implements CatAvisosService{
    
    @Autowired
    CatAvisosRepository catAvisosRepository;
    
    @Override
    public List<MvConfigAvisos> buscarNombre(String nombre, Long id, Long empresa) {
        return catAvisosRepository.buscarNombre(nombre, id, empresa);
    }
    
    @Override
    public List<MvConfigAvisos> buscarTodos(boolean activos) {
        return catAvisosRepository.buscarTodos(activos);
    }
    
    @Override
    public List<MvConfigAvisos> buscarTodosXidEmpres(boolean activos, Long id) {
        return catAvisosRepository.buscarTodosXidEmpres(activos, id);
    }
    
    @Override
    public List<CatTiposAvisos> buscarTiposAvisos(boolean activos) {
        return catAvisosRepository.buscarTiposAvisos(activos);
    }
}
