/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.repositorys.SolicitudAmextraRepository;
import com.mx.ca.viu.services.SolicitudAmextraService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("solicitudAmextraService")
public class SolicitudAmextraServiceImpl implements SolicitudAmextraService {

    @Autowired
    SolicitudAmextraRepository solicitudRepository;

    @Override
    public List<MvSolicitudesAmextra> buscarTodos(Date inicio, Date fin, CatEstatus estatus, CatProductosCredito producto) {
        return solicitudRepository.buscarTodos(inicio, fin, estatus, producto);
    }

}
