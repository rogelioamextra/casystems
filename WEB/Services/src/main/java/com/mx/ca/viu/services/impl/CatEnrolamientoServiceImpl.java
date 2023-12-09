/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.MvDatosSolicitud;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import com.mx.ca.viu.repositorys.CatEnrolamientoRepository;
import com.mx.ca.viu.services.CatEnrolamientoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catEnrolamientoService")
public class CatEnrolamientoServiceImpl implements CatEnrolamientoService{
    
    @Autowired
    CatEnrolamientoRepository catEnrolamientoRepository;
    
    @Override
    public List<MvConfigSolicitudes> buscarTodos(boolean activos) {
        return catEnrolamientoRepository.buscarTodos(activos);
    }
    
    @Override
    public List<MvConfigSolicitudes> buscarTodosXEmpresa(boolean activos, Long id) {
        return catEnrolamientoRepository.buscarTodosXEmpresa(activos, id);
    }
    
    @Override
    public List<CatCampos> buscarCampos(boolean activos) {
        return catEnrolamientoRepository.buscarCampos(activos);
    }
    
    @Override
    public List<CatCampos> buscarCamposPorEmpresa(boolean activos, Long id) {
        return catEnrolamientoRepository.buscarCamposPorEmpresa(activos, id);
    }
    
    @Override
    public MvConfigSolicitudes buscarConfigSol(Long id, Long idProd) {
        return catEnrolamientoRepository.buscarConfigSol(id, idProd);
    }
    
    @Override
    public boolean eliminaConfig(Long id, Long idProd) {
        return catEnrolamientoRepository.eliminaConfig(id, idProd);
    }
    
    @Override
    public boolean actualizaEstatus(boolean estatus, Long idProd, Long id) {
        return catEnrolamientoRepository.actualizaEstatus(estatus, idProd, id);
    }
    
    @Override
    public List<MvDatosSolicitud> buscarMvDatosSolicitudXCat(boolean activos, Long id, Long cat) {
        return catEnrolamientoRepository.buscarMvDatosSolicitudXCat(activos, id, cat);
    }

    @Override
    public List<MvDatosSolicitud> buscarRegistrosMvDatosSolicitud(boolean activos, Long id) {
        return catEnrolamientoRepository.buscarRegistrosMvDatosSolicitud(activos, id);
    }

    @Override
    public boolean deleteConfig(Long idProd) {
        return catEnrolamientoRepository.deleteConfig(idProd);
    }

    @Override
    public List<MvDocumentosCategorias> buscarDocCatPorEmpresaMasAdmin(boolean activos, Long id) {
        return catEnrolamientoRepository.buscarDocCatPorEmpresaMasAdmin(activos, id);
    }
}
