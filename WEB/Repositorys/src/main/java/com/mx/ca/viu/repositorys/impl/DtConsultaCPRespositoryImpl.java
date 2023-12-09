/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.repositorys.DtConsultaCPRepository;
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
@Repository("dtConsultaCPRepository")
public class DtConsultaCPRespositoryImpl extends SimpleRepository implements DtConsultaCPRepository{

    private static final Logger logger = LogManager.getLogger(DtConsultaCPRespositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<Colonia> buscarDatos(String cp) {
    
        List<Colonia> respuesta = new ArrayList<>();
        
        try{
            respuesta = (List<Colonia>) getSession().createQuery("select r from Colonia r where r.codigoPostalId.nombre =: cp").setParameter("cp", cp).list();
        
        }catch(Exception e){
            logger.error(e);
        }
        
        return respuesta;
    }
    
}
