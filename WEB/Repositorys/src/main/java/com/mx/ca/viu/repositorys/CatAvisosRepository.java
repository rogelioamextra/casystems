/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatAvisosRepository {
    public List<MvConfigAvisos> buscarNombre(String nombre, Long id, Long empresa);
    public List<MvConfigAvisos> buscarTodos(boolean activos);
    public List<MvConfigAvisos> buscarTodosXidEmpres(boolean activos, Long id);
    public List<CatTiposAvisos> buscarTiposAvisos(boolean activos);
}
