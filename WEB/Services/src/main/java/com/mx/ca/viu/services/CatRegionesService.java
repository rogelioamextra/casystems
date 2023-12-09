/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatRegiones;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatRegionesService {
    public List<CatRegiones> buscarTodos(boolean activos);
    public List<CatRegiones> buscarNombre(String nombre, Long id, Long empresa);
    public List<CatRegiones> buscarTodosXidEmpres(Long id);
    public List<CatRegiones> buscarTodosXidEmpresNA(boolean status ,Long id);
}
