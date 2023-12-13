/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.repositorys.DtConsultaCPRepository;
import com.mx.ca.viu.services.DtConsultaCPService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "dtConsultaCPService")
public class DtConsultaCPServiceImpl implements DtConsultaCPService{

    @Autowired
    DtConsultaCPRepository dtConsultaCPRepository;
    
    @Override
    public List<Colonia> buscarDatos(String cp) {
        return dtConsultaCPRepository.buscarDatos(cp);
    }
    
}
