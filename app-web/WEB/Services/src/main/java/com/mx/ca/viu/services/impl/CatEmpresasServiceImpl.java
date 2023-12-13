/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.repositorys.CatEmpresasRepository;
import com.mx.ca.viu.services.CatEmpresasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catEmpresasService")
public class CatEmpresasServiceImpl implements CatEmpresasService{
    @Autowired
    CatEmpresasRepository catEmpresasRepository;
    
    @Override
    public List<CatEmpresas> buscarTodos(boolean activos) {
        return catEmpresasRepository.buscarTodos(activos);
    }
    
    @Override
    public List<CatEmpresas> buscarNombre(String nombre, Long id) {
        return catEmpresasRepository.buscarNombre(nombre, id);
    }

    @Override
    public List<CatEmpresas> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        return catEmpresasRepository.buscarTodosXEmpresa(activos, IdEmpresa);
    }
    
}
