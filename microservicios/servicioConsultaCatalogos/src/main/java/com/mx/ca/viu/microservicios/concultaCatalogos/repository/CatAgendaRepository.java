/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.microservicios.concultaCatalogos.repository;

import com.mx.ca.viu.modelos.CatAgenda;
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
@Repository("catAgendaRepository")
public interface CatAgendaRepository extends CrudRepository<CatAgenda, Long> {

    @Query("UPDATE CatAgenda ag set ag.estatus=1 where ag.idAgenda=:id")
    int cambioEstatusAsistio(@Param("id") Long id);

    @Query("select ag from  CatAgenda ag  where ag.estatus=0 and ag.fecha=:fecha")
    List<CatAgenda> getAgendaFecha(@Param("fecha") Date fecha);

    @Query("select ag from  CatAgenda ag  where ag.estatus=0 and ag.codAsesor=:asesor")
    List<CatAgenda> getAgendaAsesor(@Param("asesor") String asesor);

    @Query("select ag from  CatAgenda ag  where ag.estatus=0 and ag.codAsesor=:asesor  and ag.fecha=:fecha ")
    List<CatAgenda> getAgendaAsesorFecha(@Param("asesor") String asesor, @Param("fecha") Date fecha);

}
