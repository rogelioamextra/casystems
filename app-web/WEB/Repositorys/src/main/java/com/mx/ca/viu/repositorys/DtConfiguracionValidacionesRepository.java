/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.ValidacionesValores;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface DtConfiguracionValidacionesRepository {
    
    public List<DtConfiguracionValidaciones> buscarValidacionesXEmpresaYDocumento(boolean activos, Long idEmpresa, Long idDocumento);
    
    public List<DtConfiguracionValidaciones> buscarTodos(boolean activos, Long idDoc);
    public List<CatValidaciones> buscarTodosValidaciones(boolean activos, CatDocumentos doc, CatEmpresas emp);
    
}
