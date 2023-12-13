/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatColonias;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatMunicipios;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.CodigoPostal;
import com.mx.ca.viu.repositorys.CatEstadoRepository;
import com.mx.ca.viu.services.CatEstadoService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mramirez
 */
@Service(value = "catEstadoService")
public class CatEstadoServiceImpl implements CatEstadoService {

    @Autowired
    CatEstadoRepository catEstadoRepository;

    @Override
    public Estado buscarEstado(String id) {
        return catEstadoRepository.buscarEstado(id);
    }

    @Override
    public List<Municipio> buscarMunicipio(Long id) {
        return catEstadoRepository.buscarMunicipio(id);
    }

    @Override
    public List<Colonia> buscarLocalidad(Long id) {
        return catEstadoRepository.buscarLocalidad(id);
    }

    @Override
    public List<CatColonias> buscarCatLocalidad(Long id) {
        return catEstadoRepository.buscarCatLocalidad(id);
    }

    @Override
    public Estado buscarEstadoPorSigla(String id) {
        return catEstadoRepository.buscarEstadoPorSigla(id);
    }

    @Override
    public CodigoPostal consultaCP(String id) {
        return catEstadoRepository.consultaCP(id);
    }

    @Override
    public List<Ciudad> consultaCiudadesEstado(Long idEstado) {
        return catEstadoRepository.consultaCiudadesEstado(idEstado);
    }

    @Override
    public List<CatMunicipios> buscarCatMunicipio(Long id) {
        return catEstadoRepository.buscarCatMunicipio(id);
    }

    @Override
    public List<CatColonias> consultaCPSepomex(String cp) {
        return catEstadoRepository.consultaCPSepomex(cp);
    }

    @Override
    public CatEstados buscarEstadoCodigo(String codigo) {
        return catEstadoRepository.buscarEstadoCodigo(codigo);
    }

}
