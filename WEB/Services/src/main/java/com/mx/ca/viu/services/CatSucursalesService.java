/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;
import com.mx.ca.viu.modelos.CatSucursales;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatSucursalesService {
    public List<CatSucursales> buscarTodos(boolean activos);
    public List<CatSucursales> buscarNombre(String nombre, Long id, Long empresa);
    public List<CatSucursales> buscarTodosXEmpresa(boolean activos ,Long IdEmpresa);
}
