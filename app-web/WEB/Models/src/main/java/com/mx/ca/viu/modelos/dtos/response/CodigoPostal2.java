/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.mx.ca.viu.modelos.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jbecerril
 */

public class CodigoPostal2 implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private String nombre;
    
    
    private CatEstados estadoId;
    
    private CatMunicipios municipioId;
    
    
    private List<CatColonias> coloniasLista;

    public CodigoPostal2() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CatEstados getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(CatEstados estadoId) {
        this.estadoId = estadoId;
    }

    public CatMunicipios getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(CatMunicipios municipioId) {
        this.municipioId = municipioId;
    }

    public List<CatColonias> getColoniasLista() {
        return coloniasLista;
    }

    public void setColoniasLista(List<CatColonias> coloniasLista) {
        this.coloniasLista = coloniasLista;
    }

   

    

    
    
}
