/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.DtResultadosDatosValidados;
import com.mx.ca.viu.repositorys.CatTableroRepository;
import com.mx.ca.viu.services.CatTableroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mramirez
 */
@Service(value = "catTableroService")
public class CatTableroServiceImpl implements CatTableroService {
    @Autowired
    CatTableroRepository catTableroRepository;
    
    @Override
    public List<DtResultadosDatosValidados> buscarTodos(boolean activos) {
        return catTableroRepository.buscarTodos(activos);
    }
    
    @Override
    public List<DtResultadosDatosValidados> buscarTodosPorEmpresa(boolean activos, Long empresa) {
        return catTableroRepository.buscarTodosPorEmpresa(activos, empresa);
    }
    
    @Override
    public List<CatProductos> buscarProductos(Long empresa) {
        return catTableroRepository.buscarProductos(empresa);
    }
    
    @Override
    public List<CatEstatus> buscarEstatus(boolean activos) {
        return catTableroRepository.buscarEstatus(activos);
    }
    
    @Override
    public List<DtResultadosDatosValidados> buscarTodosPorFiltro(Long empresa, Long estatus, Long producto, String fechaIni, String fechaFin){
        return catTableroRepository.buscarTodosPorFiltro(empresa, estatus, producto, fechaIni, fechaFin);
    }
}
