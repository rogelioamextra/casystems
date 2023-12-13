/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.repositorys.CatEstatusRepository;
import com.mx.ca.viu.services.CatEstatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "catEstatusService")
public class CatEstatusServiceImpl implements CatEstatusService{

    @Autowired
    CatEstatusRepository catEstatusRepository;

    @Override
    public List<CatEstatus> buscarTodos() {
        return catEstatusRepository.buscarTodos();
    }
    
}
