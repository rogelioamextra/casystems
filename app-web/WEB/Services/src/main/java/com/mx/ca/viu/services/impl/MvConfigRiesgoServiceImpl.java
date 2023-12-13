/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.modelos.MvAdminNivelRiesgo;
import com.mx.ca.viu.modelos.MvConfigRiesgo;
import com.mx.ca.viu.repositorys.MvConfigRiesgoRepository;
import com.mx.ca.viu.services.MvConfigRiesgoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "mvConfigRiesgoService")
public class MvConfigRiesgoServiceImpl implements MvConfigRiesgoService{

    @Autowired
    MvConfigRiesgoRepository mvRiesgoRepository;
    
    @Override
    public List<MvConfigRiesgo> buscarConfiguraciones(Long idConfigSolicitud) {
        return mvRiesgoRepository.buscarConfiguraciones(idConfigSolicitud);
    }
    
    @Override
    public List<MvConfigRiesgo> buscarConfiguracionesXDocumento(Long idConfigSolicitud, Long doc) {
        return mvRiesgoRepository.buscarConfiguracionesXDocumento(idConfigSolicitud, doc);
    }
    
    @Override
    public boolean eliminarRegistro(Long idConfigMensaje) {
        return mvRiesgoRepository.eliminarRegistro(idConfigMensaje);
    }
    
    @Override
    public List<CatServiciosValidacionesExternos> buscarValidaciones() {
        return mvRiesgoRepository.buscarValidaciones();
    }
    
    @Override
    public List<MvAdminNivelRiesgo> buscarConfiguracion(Long idConfigSolicitud, boolean activos) {
        return mvRiesgoRepository.buscarConfiguracion(idConfigSolicitud, activos);
    }
    
    @Override
    public boolean eliminaConfiguracion(Long id) {
        return mvRiesgoRepository.eliminaConfiguracion(id);
    }

    @Override
    public List<DtValidacionesServiciosEmpresa> buscarValidacionesEmpresa(Long idEmpresas) {
       return mvRiesgoRepository.buscarValidacionesEmpresa(idEmpresas);
    }
}
