/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.repositorys.DtComparacionFacialRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbecerril
 */
@Repository("dtComparacionFacialRepository")
public class DtComparacionFacialRepositoryImpl extends SimpleRepository implements DtComparacionFacialRepository {
    
    @Override
    @Transactional
    public DtComparacionFacial buscarCurp(String curp) {
        DtComparacionFacial resultado = null;
        
        try {
            resultado = (DtComparacionFacial) getSession().createQuery("SELECT  com FROM  DtComparacionFacial com where com.curp=:curp").setParameter("curp", curp).uniqueResult();
        } catch (Exception e) {
            logger.error(e);
        }
        
        return resultado;
    }
    
}
