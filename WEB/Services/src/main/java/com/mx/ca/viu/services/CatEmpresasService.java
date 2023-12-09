/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;
import com.mx.ca.viu.modelos.CatEmpresas;
import java.util.List;
/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatEmpresasService {
    public List<CatEmpresas> buscarTodos(boolean activos);
    public List<CatEmpresas> buscarNombre(String nombre, Long IdEmpresa);
    public List<CatEmpresas> buscarTodosXEmpresa(boolean activos, Long IdEmpresa);
}
