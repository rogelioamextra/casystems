/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;


import com.mx.ca.viu.modelos.MvSolicitudProducto;
import com.mx.ca.viu.repositorys.MvSolicitudProductoRepository;
import com.mx.ca.viu.services.MvSolicitudProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linzunza
 */
@Service(value = "mvSolicitudProductoService")
public class MvSolicitudProductoServiceImp implements MvSolicitudProductoService{

    @Autowired
    MvSolicitudProductoRepository mvSolicitudProductoRepository;
      
    @Override
    public MvSolicitudProducto buscaBySolicitudProducto(Long idProducto, Long idSolicitud) {
        return mvSolicitudProductoRepository.buscaBySolicitudProducto(idProducto,idSolicitud);
    }
    
}
