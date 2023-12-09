/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.MvResultadosValidacionesServiciosDigital;
import java.util.List;

/**
 *
 * @author linzunza
 */
public interface MvResultadosValidacionesServiciosDService {
    public List<MvResultadosValidacionesServiciosDigital> buscarPorServicioIdResultadosValidados(Long idServicio, Long idResultados);

}
