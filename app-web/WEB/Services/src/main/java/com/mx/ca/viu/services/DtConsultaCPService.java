/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.Colonia;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface DtConsultaCPService {
    public List<Colonia> buscarDatos(String cp);
}
