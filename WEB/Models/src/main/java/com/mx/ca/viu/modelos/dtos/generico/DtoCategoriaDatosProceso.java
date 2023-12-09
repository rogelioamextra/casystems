/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.mx.ca.viu.modelos.CatCategoriasCampos;

/**
 *
 * @author jbecerril
 */
public class DtoCategoriaDatosProceso {
    private String nombre;
    private CatCategoriasCampos categoria;

    public DtoCategoriaDatosProceso() {
    }

    public DtoCategoriaDatosProceso(String nombre, CatCategoriasCampos categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CatCategoriasCampos getCategoria() {
        return categoria;
    }

    public void setCategoria(CatCategoriasCampos categoria) {
        this.categoria = categoria;
    }

}
