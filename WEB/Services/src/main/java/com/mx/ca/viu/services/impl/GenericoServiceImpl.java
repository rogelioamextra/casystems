/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatProperties;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.services.GenericoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "genericoService")
public class GenericoServiceImpl implements GenericoService {

    @Autowired
    GenericoRepository genericoRepository;

    @Override
    public boolean guardar(Object cliente) {
        return genericoRepository.guardar(cliente);
    }

    @Override
    public boolean update(Object cliente) {
        return genericoRepository.update(cliente);
    }

    @Override
    public boolean delete(Object cliente) {
        return genericoRepository.delete(cliente);
    }
    @Override
    public boolean deleteTiposIdentificacion(String  curp) {
        return genericoRepository.deleteTiposIdentificacion(curp);
    }

    @Override
    public <T> T findByID(Class<T> type, Long id) {
        return genericoRepository.findByID(type, id);
    }

    @Override
    public <T> List<T> findAll(Class<T> type, boolean activos) {
        return genericoRepository.findAll(type, activos);
    }

    @Override
    public CatProperties getPropertie(Long contante) {
        return genericoRepository.getPropertie(contante);
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        return genericoRepository.findAll(type);
    }

}
