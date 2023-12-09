/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.repositorys.CatCategoriasCamposRepository;
import com.mx.ca.viu.services.CatCategoriasCamposService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "catCategoriasCamposService")
public class CatCategoriasCamposServiceImpl implements CatCategoriasCamposService{
    
    @Autowired
    CatCategoriasCamposRepository catCategoriasCamposRepository;

    @Override
    public List<CatCategoriasCampos> buscarTodos(boolean activos) {
        return catCategoriasCamposRepository.buscarTodos(activos);
    }

    @Override
    public List<CatCategoriasCampos> buscarNombre(String nombre, Long id, Long seleccion) {
        return catCategoriasCamposRepository.buscarNombre(nombre, id, seleccion);
    }

    @Override
    public List<CatCategoriasCampos> buscarTodosXEmpresa(boolean activos, Long idEmpresa) {
        return catCategoriasCamposRepository.buscarTodosXEmpresa(activos, idEmpresa);
    }
    
    @Override
    public List<CatCategoriasCampos> buscarTodosXEmpresaYAdmin(boolean activos, Long idEmpresa) {
        return catCategoriasCamposRepository.buscarTodosXEmpresaYAdmin(activos, idEmpresa);
    }
    
}
