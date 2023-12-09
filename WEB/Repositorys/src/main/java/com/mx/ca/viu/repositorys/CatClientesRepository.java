/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatListaNegraAmextra;
import com.mx.ca.viu.modelos.CatPersonas;
import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.response.Cliente;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatClientesRepository {

    public CatPersonas buscarCurp(String curp);
    
    public CatClientes buscarClienteCurp(String curp);

    public List<Cliente> obtenerTodosClientesStatus(boolean activo);

    public List<CatListaNegraAmextra> buscarListaNegraCurp(String curp);
     public CatClientes buscarClienteTelefono(String telefono);
       public List<MvSolicitudesAmextra> buscarSolicitudCurp(String curp) ;
        public List<Cliente> obtenerTodosClientesAsesor(Long id);
        public CatPersonas buscarTelefono(String telefono);

}
