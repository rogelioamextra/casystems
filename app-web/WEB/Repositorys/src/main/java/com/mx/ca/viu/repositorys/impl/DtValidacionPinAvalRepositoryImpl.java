/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.repositorys.DtValidacionPinAvalRepository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rogel
 */

@Repository("validacionPinAvalRepository")
public class DtValidacionPinAvalRepositoryImpl extends SimpleRepository implements DtValidacionPinAvalRepository{
   
    
            private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DtValidacionPinAvalRepositoryImpl.class);

    
    
    @Override
    @Transactional
    public String getPinAval(String curp){
    
        String response ="";
        
        try{
            
            Query qr = getSession().createQuery("select dta.pin from DtValidacionPinAval dta  where dta.curp='GACG910329HMCRRN04' order by dta.fechaValidacion desc");
            qr.setMaxResults(1);
            response = (String) qr.getSingleResult();
            logger.debug("registro "+ curp+" pin "+response);
        
        }catch(NoResultException nr ){
                  
               logger.error(nr.getMessage());
                return  response;
        }catch(Exception ex){
                logger.error(ex.getMessage());
        
        }
        
        
        return  response;
    
    }
}
