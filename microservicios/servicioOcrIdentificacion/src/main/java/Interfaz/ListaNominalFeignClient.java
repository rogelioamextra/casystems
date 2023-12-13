/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author mramirez
 */

//@FeignClient(name = "servicio-lista-nominal")
public interface ListaNominalFeignClient {

    @GetMapping("/servicios/lista/nominal")
    public GenericResponse consultar(@RequestParam("request") String request);
}
