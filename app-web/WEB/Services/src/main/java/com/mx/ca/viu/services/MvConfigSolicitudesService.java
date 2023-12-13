/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface MvConfigSolicitudesService {

    public List<MvConfigSolicitudes> buscarTodos(boolean activos);

    public List<MvConfigSolicitudes> buscarTodosXEmpresa(boolean activos, Long idEmpresa);

    public MvConfigSolicitudes buscarTodosXProducto(Long id);

    public MvConfigSolicitudes buscarTodosXProductoList(Long id);

    public List<CatCategoriasCampos> buscarTodasCategoriasDatosSolicitud(Long id);


    public List<MvConfigSolicitudes>buscarConfigXEmpresaYProducto(boolean activos, Long idEmpresa, Long idProducto);



    //MICROSERVICIO DE GENERACION ID DE SOLICITUD
    public MvConfigSolicitudes buscaIdConfiguracionProducto(Long id);

    public Long obtieneSecuencia();

}
