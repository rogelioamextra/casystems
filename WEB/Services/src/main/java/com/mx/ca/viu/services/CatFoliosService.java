/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatFolio;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatFoliosService {
    public List<CatFolio> buscarTodos(boolean activos);
    public List<CatFolio> buscarNombre(String nombre, Long id, Long empresa);
    public List<CatFolio> buscarTodosXEmpresa(boolean activos ,Long idEmpresa);
}
