/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatProperties;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface GenericoService {

    public boolean guardar(Object cliente);

    public boolean update(Object cliente);

    public boolean delete(Object cliente);

    public <T> T findByID(Class<T> type, Long id);

    public <T> List<T> findAll(Class<T> type, boolean activos);
    public <T> List<T> findAll(Class<T> type);

    public CatProperties getPropertie(Long contante);
    
     public boolean deleteTiposIdentificacion(String  curp);

}
