/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatFolio;
import com.mx.ca.viu.repositorys.CatFoliosRepository;
import com.mx.ca.viu.services.CatFoliosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service (value = "catFoliosService")
public class CatFoliosServiceImpl implements CatFoliosService{
    @Autowired
    CatFoliosRepository catFoliosRepository;

    @Override
    public List<CatFolio> buscarTodos(boolean activos) {
        return catFoliosRepository.buscarTodos(activos);
    }

    @Override
    public List<CatFolio> buscarNombre(String nombre, Long id, Long empresa) {
        return catFoliosRepository.buscarNombre(nombre, id, empresa);
    }

    @Override
    public List<CatFolio> buscarTodosXEmpresa(boolean activos ,Long id) {
        return catFoliosRepository.buscarTodosXEmpresa(activos, id);
    }
}
