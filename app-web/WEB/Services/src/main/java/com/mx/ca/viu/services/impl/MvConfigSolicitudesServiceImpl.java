/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.repositorys.MvConfigSolicitudesRepository;
import com.mx.ca.viu.services.MvConfigSolicitudesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "mvConfigSolicitudesService")
public class MvConfigSolicitudesServiceImpl implements MvConfigSolicitudesService {

    @Autowired
    MvConfigSolicitudesRepository mvConfigSolicituesRepository;

    @Override
    public List<MvConfigSolicitudes> buscarTodos(boolean activos) {
        return mvConfigSolicituesRepository.buscarTodos(activos);
    }

    @Override
    public List<MvConfigSolicitudes> buscarTodosXEmpresa(boolean activos, Long id) {
        return mvConfigSolicituesRepository.buscarTodosXEmpresa(activos, id);
    }

    @Override
    public MvConfigSolicitudes buscarTodosXProducto(Long id) {
        return mvConfigSolicituesRepository.buscarTodosXProducto(id);
    }

    @Override
    public MvConfigSolicitudes buscarTodosXProductoList(Long id) {
        return mvConfigSolicituesRepository.buscarTodosXProductoList(id);
    }

    @Override
    public List<MvConfigSolicitudes> buscarConfigXEmpresaYProducto(boolean activos, Long idEmpresa, Long idProducto) {
        return mvConfigSolicituesRepository.buscarConfigXEmpresaYProducto(activos, idEmpresa, idProducto);
    }

    @Override
    public List<CatCategoriasCampos> buscarTodasCategoriasDatosSolicitud(Long id) {
        return mvConfigSolicituesRepository.buscarTodasCategoriasDatosSolicitud(id);
    }
    


    @Override
    public MvConfigSolicitudes buscaIdConfiguracionProducto(Long id) {
        return mvConfigSolicituesRepository.buscaIdConfiguracionProducto(id);
    }

    @Override
    public Long obtieneSecuencia() {
        return mvConfigSolicituesRepository.obtieneSecuencia();
    }


}
