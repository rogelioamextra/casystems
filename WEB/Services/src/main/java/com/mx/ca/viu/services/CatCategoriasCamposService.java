/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface CatCategoriasCamposService {
    
    public List<CatCategoriasCampos> buscarTodos(boolean activos);
    public List<CatCategoriasCampos> buscarNombre(String nombre, Long id, Long seleccion);
    public List<CatCategoriasCampos> buscarTodosXEmpresa(boolean activos, Long idEmpresa);
    public List<CatCategoriasCampos> buscarTodosXEmpresaYAdmin(boolean activos, Long idEmpresa);
}
