/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatSubdirecciones;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface CatSubdireccionesService {

    public List<CatSubdirecciones> buscarTodos(boolean activos);
    public List<CatSubdirecciones> buscarNombre(String nombre, Long id, Long empresa);
    public List<CatSubdirecciones> buscarTodosXEmpresa(boolean activos, Long IdEmpresa);
}
