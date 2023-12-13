/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.repositorys.CatProductosRepository;
import com.mx.ca.viu.services.CatProductosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service (value = "catProductosService")
public class CatProductosServiceImpl implements CatProductosService{

    @Autowired
    CatProductosRepository catProductosRepository;

    @Override
    public List<CatProductos> buscarTodos(boolean activos) {
        return catProductosRepository.buscarTodos(activos);
    }

    @Override
    public List<CatProductos> buscarNombre(String nombre, Long id, Long seleccion) {
        return catProductosRepository.buscarNombre(nombre, id, seleccion);
    }

    @Override
    public List<CatProductos> buscarTodosXEmpresa(boolean activos ,Long id) {
        return catProductosRepository.buscarTodosXEmpresa(activos, id);
    }
    
    
    
}
