/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatSubdirecciones;
import com.mx.ca.viu.repositorys.CatSubdireccionesRepository;
import com.mx.ca.viu.services.CatSubdireccionesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "catSubdireccionesService")
public class CatSubdireccionesServiceImpl implements CatSubdireccionesService{

    @Autowired
    CatSubdireccionesRepository catSubdireccionesRepository;
        
    @Override
    public List<CatSubdirecciones> buscarTodos(boolean activos) {
        return catSubdireccionesRepository.buscarTodos(activos);
    }
    
    @Override
    public List<CatSubdirecciones> buscarNombre(String nombre, Long id, Long empresa) {
        return catSubdireccionesRepository.buscarNombre(nombre, id, empresa);
    }

    @Override
    public List<CatSubdirecciones> buscarTodosXEmpresa(boolean activos ,Long IdEmpresa) {
        return catSubdireccionesRepository.buscarTodosXEmpresa(activos, IdEmpresa);
    }
        
}
