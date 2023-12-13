/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.repositorys.DtComparacionFacialRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
public interface DtcomparacionFacialService {
    
    
    public DtComparacionFacial buscarCurp(String curp);
    
    public void save(DtComparacionFacial obj);
    
}
