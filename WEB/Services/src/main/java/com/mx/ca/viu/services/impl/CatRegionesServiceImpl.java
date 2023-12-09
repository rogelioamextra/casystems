/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatRegiones;
import com.mx.ca.viu.repositorys.CatRegionesRepository;
import com.mx.ca.viu.services.CatRegionesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catRegionesService")
public class CatRegionesServiceImpl implements CatRegionesService{
    @Autowired
    CatRegionesRepository catRegionesRepository;
    
    @Override
    public List<CatRegiones> buscarTodos(boolean activos) {
        return catRegionesRepository.buscarTodos(activos);
    }
    
    @Override
    public List<CatRegiones> buscarNombre(String nombre, Long id, Long empresa) {
        return catRegionesRepository.buscarNombre(nombre, id, empresa);
    }
    
    @Override
    public List<CatRegiones> buscarTodosXidEmpres(Long id) {
        return catRegionesRepository.buscarTodosXidEmpres(id);
    }
    
    @Override
    public List<CatRegiones> buscarTodosXidEmpresNA(boolean status ,Long id) {
        return catRegionesRepository.buscarTodosXidEmpresNA(status ,id);
    }
}
