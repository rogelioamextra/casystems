/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.DtResultadosDatosValidados;
import java.util.List;

/**
 *
 * @author mramirez
 */
public interface CatTableroService {

    public List<DtResultadosDatosValidados> buscarTodos(boolean activos);

    public List<DtResultadosDatosValidados> buscarTodosPorEmpresa(boolean activos, Long empresa);
    
    public List<DtResultadosDatosValidados> buscarTodosPorFiltro(Long empresa, Long estatus, Long producto, String fechaIni, String fechaFin);
    
    public List<CatProductos> buscarProductos(Long empresa);
    
    public List<CatEstatus> buscarEstatus(boolean activos);
}
