/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.repositorys.DtComparacionFacialRepository;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.services.DtcomparacionFacialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("dtcomparacionFacialService")

public class DtComparacionFacialServiceImpl implements DtcomparacionFacialService {

    @Autowired
    DtComparacionFacialRepository repository;
    @Autowired
    GenericoRepository generico;

    @Override
    public DtComparacionFacial buscarCurp(String curp) {
        return repository.buscarCurp(curp);
    }

    @Override
    public void save(DtComparacionFacial obj) {
        generico.guardar(obj);
    }

}
