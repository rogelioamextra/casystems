/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatZonas;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface CatZonasRepository {
    public List<CatZonas> buscarTodos(boolean activos);
    public List<CatZonas> buscarNombre(String nombre, Long id, Long empresa);
    public List<CatZonas> buscarTodosXidEmpres(Long id);
    public List<CatZonas> buscarTodosXidRegiones(Long idRegion,Long idEmpresa);
    public List<CatZonas> buscarNumeroZona(String noZona, Long id, Long empresa);
}
