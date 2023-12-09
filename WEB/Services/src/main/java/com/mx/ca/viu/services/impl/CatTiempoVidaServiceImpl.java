/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatValoresTiempoVida;
import com.mx.ca.viu.repositorys.CatTiempoVidaRepository;
import com.mx.ca.viu.services.CatTiempoVidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catTiempoDeVidaService")
public class CatTiempoVidaServiceImpl implements CatTiempoVidaService {

    @Autowired
    CatTiempoVidaRepository catTiempoVidaRepository;

    @Override
    public List<CatValoresTiempoVida> buscarTodos(boolean activos) {
        return catTiempoVidaRepository.buscarTodos(activos);
    }

    @Override
    public List<CatValoresTiempoVida> buscarNombre(String nombre, Long id, Long empresa) {
        return catTiempoVidaRepository.buscarNombre(nombre, id, empresa);
    }

    @Override
    public List<CatValoresTiempoVida> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        return catTiempoVidaRepository.buscarTodosXEmpresa(activos, IdEmpresa);
    }

}
