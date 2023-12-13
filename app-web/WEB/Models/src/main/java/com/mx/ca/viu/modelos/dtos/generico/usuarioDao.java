/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.mx.ca.viu.modelos.CatUsuarios;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@RepositoryRestResource(path="usuarios")
public interface usuarioDao extends PagingAndSortingRepository<CatUsuarios, Long>{
    @RestResource(path="buscar-username")
    public CatUsuarios findByUsername(@Param("usuario") String usuario);
}
