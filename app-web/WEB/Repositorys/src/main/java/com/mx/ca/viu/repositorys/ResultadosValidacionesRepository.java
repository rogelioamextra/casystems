/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.DtResultadosDatosValidados;

/**
 *
 * @author mramirez
 */
public interface ResultadosValidacionesRepository {

    public DtResultadosDatosValidados buscaDatosValidados(Long id);
}
