/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatServiciosValidacionesInternos;
import com.mx.ca.viu.modelos.CatValores;
import com.mx.ca.viu.modelos.ValidacionesValores;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface ValidacionesValoresRepository {

    public List<ValidacionesValores> buscarValores(Long idValidacion);

    public List<CatValores> buscarValoresCat(Long idValidacion);

    public CatServiciosValidacionesInternos buscarValorFinal(Long id);
}
