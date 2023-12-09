/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.microservicios.concultaCatalogos.repository;

import com.mx.ca.viu.modelos.CatAgenda;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author jbecerril
 */
@Repository("mvConfigAvisosRepository")
public interface MvConfigAvisosRepository extends CrudRepository<MvConfigAvisos, Long>{
    
    
    @Query("SELECT mv FROM MvConfigAvisos mv where mv.status=true")
    List<MvConfigAvisos> getAvisos();
    
}
