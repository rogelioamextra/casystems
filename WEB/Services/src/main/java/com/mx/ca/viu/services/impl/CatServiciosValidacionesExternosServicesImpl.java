/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.services.CatServiciosValidacionesExternosServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "catServiciosValidacionesExternosServices")

public class CatServiciosValidacionesExternosServicesImpl implements CatServiciosValidacionesExternosServices {

    @Autowired
    CatServiciosValidacionesExternosRepository repository;

    @Override
    public CatServiciosValidacionesExternos buscarServicioId(Long id) {
        return repository.buscarServicioId(id);
    }
    
    @Override
    public List<DatosSolicitudCampos> buscaSolicitudCampos(String idProducto) {
        return repository.buscaSolicitudCampos(idProducto);
    }
}
