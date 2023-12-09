/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MensajesValidaciones;
import com.mx.ca.viu.modelos.MvConfigMensaje;
import com.mx.ca.viu.repositorys.MvConfigMensajesRepository;
import com.mx.ca.viu.services.MvConfigMensajesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "mvConfigMensajesService")
public class MvConfigMensajesServiceImpl implements MvConfigMensajesService{
    
    @Autowired
    MvConfigMensajesRepository mvConfigMensajesRepository;

    @Override
    public List<MensajesValidaciones> buscarConfigMensajes(Long idSolicitud, Long estatus) {
        return mvConfigMensajesRepository.buscarConfigMensajes(idSolicitud, estatus);
    }

    @Override
    public boolean eliminarRegistro(Long idConfigMensaje) {
        return mvConfigMensajesRepository.eliminarRegistro(idConfigMensaje);
    }
    
    @Override
    public List<MvConfigMensaje> buscaMensaje(Long idSolicitud) {
        return mvConfigMensajesRepository.buscaMensaje(idSolicitud);
    }

    @Override
    public boolean deleteMensajesValidacionesList(List<Long> idsMensajes) {
        return mvConfigMensajesRepository.deleteMensajesValidacionesList(idsMensajes);
    }
    
}
