/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.MvSolicitudProducto;

/**
 *
 * @author linzunza
 */
public interface MvSolicitudProductoService {
    
    public MvSolicitudProducto buscaBySolicitudProducto(Long idProducto, Long idSolicitud);
}
