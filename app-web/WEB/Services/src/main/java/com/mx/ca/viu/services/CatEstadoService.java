/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatColonias;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatMunicipios;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.CodigoPostal;
import java.util.List;

/**
 *
 * @author mramirez
 */
public interface CatEstadoService {
    
      public CatEstados buscarEstadoCodigo(String codigo);

    Estado buscarEstado(String id);

    List<Municipio> buscarMunicipio(Long id);

    List<Colonia> buscarLocalidad(Long id);

    Estado buscarEstadoPorSigla(String id);

    CodigoPostal consultaCP(String id);

    public List<Ciudad> consultaCiudadesEstado(Long idEstado);
     public List<CatMunicipios> buscarCatMunicipio(Long id);
      public List<CatColonias> buscarCatLocalidad(Long id);
        public List<CatColonias> consultaCPSepomex(String cp) ;

}
