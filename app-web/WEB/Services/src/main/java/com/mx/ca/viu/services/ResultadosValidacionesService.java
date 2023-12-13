/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.DtResultadosDatosValidados;

/**
 *
 * @author mramirez
 */
public interface ResultadosValidacionesService {

    public DtResultadosDatosValidados buscaDatosValidados(Long id);
}
