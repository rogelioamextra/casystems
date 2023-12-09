/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.concultaCatalogos.services.impl;

import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.microservicios.concultaCatalogos.repository.CatAgendaRepository;
import com.mx.ca.viu.microservicios.concultaCatalogos.repository.MvConfigAvisosRepository;
import com.mx.ca.viu.microservicios.concultaCatalogos.services.CatAgendaService;
import com.mx.ca.viu.microservicios.concultaCatalogos.services.MvAvisosServices;
import com.mx.ca.viu.modelos.CatAgenda;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.dtos.response.AvisosDTO;
import com.mx.ca.viu.repositorys.CatAvisosRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Service("mvAvisosService")
public class MvAvisosServiceImpl implements MvAvisosServices {

    @Autowired
    private MvConfigAvisosRepository avisos;

    @Override
    public List<AvisosDTO> getAvisos() {
        List<AvisosDTO> tmp = new ArrayList<>();
        for (MvConfigAvisos aviso : avisos.getAvisos()) {
            AvisosDTO aux = new AvisosDTO();
            if (aviso.isExpira()) {
                if (aviso.getFechaExpiracion().after(UtilGenerico.obtenerHoraMexico())) {
                    aux.setContenidoAviso(aviso.getAviso().replace("<p>", "").replace("</p>", ""));
                    aux.setTituloAviso(aviso.getNombre());
                }

            } else {
                aux.setContenidoAviso(aviso.getAviso());
                aux.setTituloAviso(aviso.getNombre());
            }
            if(aux!=null){
            tmp.add(aux);
            }

        }
        return tmp;
    }

}
