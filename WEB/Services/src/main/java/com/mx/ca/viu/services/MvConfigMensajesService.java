/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.MensajesValidaciones;
import com.mx.ca.viu.modelos.MvConfigMensaje;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface MvConfigMensajesService {

    List<MensajesValidaciones> buscarConfigMensajes(Long idSolicitud, Long estatus);
    public boolean eliminarRegistro(Long idConfigMensaje);
    List<MvConfigMensaje> buscaMensaje(Long idSolicitud);
    public boolean deleteMensajesValidacionesList(List<Long> idsMensajes);
}
