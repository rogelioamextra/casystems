/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.repositorys.CatEstatusRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Repository("catEstatusRepository")
public class CatEstatusRepositoryImpl extends SimpleRepository implements  CatEstatusRepository{
    
    private static final Logger logger = LogManager.getLogger(CatEstatusRepositoryImpl.class.getName());

    @Override
    @Transactional
    public List<CatEstatus> buscarTodos() {
    
        List<CatEstatus> respuesta = new ArrayList<>();
        try {
                respuesta = (List<CatEstatus>) getSession().createQuery("select r from CatEstatus r").list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
