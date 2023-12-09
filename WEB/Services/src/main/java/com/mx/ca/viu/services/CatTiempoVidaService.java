/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;
import com.mx.ca.viu.modelos.CatValoresTiempoVida;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatTiempoVidaService {
    public List<CatValoresTiempoVida> buscarTodos(boolean activos);
    public List<CatValoresTiempoVida> buscarNombre(String nombre, Long id, Long empresa);
    public List<CatValoresTiempoVida> buscarTodosXEmpresa(boolean activos ,Long IdEmpresa);
}
