/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatProductos;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface CatProductosService {

    public List<CatProductos> buscarTodos(boolean activos);
    public List<CatProductos> buscarNombre(String nombre, Long id, Long seleccion);
    public List<CatProductos> buscarTodosXEmpresa(boolean activos ,Long idEmpresa);
    
}
