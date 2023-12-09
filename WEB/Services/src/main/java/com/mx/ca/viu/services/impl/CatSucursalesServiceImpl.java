/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatSucursales;
import com.mx.ca.viu.repositorys.CatSucursalesRepository;
import com.mx.ca.viu.services.CatSucursalesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catSucursalesService")
public class CatSucursalesServiceImpl implements CatSucursalesService{
    @Autowired
    CatSucursalesRepository catSucursalesRepository;
    
    @Override
    public List<CatSucursales> buscarTodos(boolean activos) {
        return catSucursalesRepository.buscarTodos(activos);
    }
    
    @Override
    public List<CatSucursales> buscarNombre(String nombre, Long id, Long empresa) {
        return catSucursalesRepository.buscarNombre(nombre, id, empresa);
    }

    @Override
    public List<CatSucursales> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        return catSucursalesRepository.buscarTodosXEmpresa(activos, IdEmpresa);
    }
    
    
}
