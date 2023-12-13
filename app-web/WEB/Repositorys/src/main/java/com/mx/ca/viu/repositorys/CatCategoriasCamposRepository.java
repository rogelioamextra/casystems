/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.DatosSolicitudCategorias;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface CatCategoriasCamposRepository {

    public List<CatCategoriasCampos> buscarTodos(boolean activos);

    public List<CatCategoriasCampos> buscarNombre(String nombre, Long id, Long seleccion);

    public List<CatCategoriasCampos> buscarTodosXEmpresa(boolean activos, Long idEmpresa);

    public List<CatCategoriasCampos> buscarTodosXEmpresaYAdmin(boolean activos, Long idEmpresa);

    public List<DatosSolicitudCategorias> buscarCategoriasXproducto(Long idProducto);

    public List<CatCategoriasCampos> buscarRestantes(List<Long> ids, Long idEmpresa);

    public boolean deleteCategoriasDatosSolicitud(Long idDatosSolicitud);

    public boolean deleteCamposDocumentosDatosSolicitud(Long idDatosSolicitud, Long idCategoria);
    
    public List<MvConfigSolicitudes> consultaCategoriasActivas(Long idProducto, Long idEmpresa);
}
