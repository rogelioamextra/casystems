/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioConsultaCP;

import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoAvisos;
import com.mx.ca.viu.modelos.dtos.generico.InfoColonia;
import com.mx.ca.viu.modelos.dtos.response.AvisosResponse;
import com.mx.ca.viu.modelos.dtos.request.AvisosRequest;
import com.mx.ca.viu.modelos.dtos.request.ConsultaCPRequest;
import com.mx.ca.viu.modelos.dtos.response.ConsultaCPResponse;
import com.mx.ca.viu.services.CatAvisosService;
import com.mx.ca.viu.services.DtConsultaCPService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jbecerril
 */
@RestController
public class ControllerServicioConsultaCP {
    
    @Autowired
    DtConsultaCPService dtConsutlaCPSerive;
    
    private List<Colonia> listaColonias;
    private List<String> listaAsentamientos;
    private List<InfoColonia> listaNombreColonias;
    
    @PostMapping(path = "/servicio/consultaCP", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ConsultaCPResponse consultar(@RequestBody ConsultaCPRequest request) {
        ConsultaCPResponse response = new ConsultaCPResponse();
        response.setResponse(new GenericResponse.Response());
        try{
            //buscar colonias que tengan el mismo cp
            listaColonias = dtConsutlaCPSerive.buscarDatos(request.getCodigoPostal());
            listaAsentamientos = new ArrayList<>();
            listaNombreColonias = new ArrayList<>();
            if(listaColonias.isEmpty()){
                response.getResponse().setCodigo(400);
                response.getResponse().setMensaje("Codigo Postal no encontrado, ingrese uno valido");
                response.setData(null);
            }else{
                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");
                response.getData().setEstado(listaColonias.get(0).getEstadoId().getNombre());
                response.getData().setIdEstado(listaColonias.get(0).getEstadoId().getId());
                response.getData().setCiudad(listaColonias.get(0).getCiudadId().getNombre());
                response.getData().setIdCiudad(listaColonias.get(0).getCiudadId().getId());
                response.getData().setMunicipio(listaColonias.get(0).getMunicipioId().getNombre());
                response.getData().setIdMunicipio(listaColonias.get(0).getMunicipioId().getId());
                
                if(listaColonias.size()==1){
                    InfoColonia nuevo = new InfoColonia();
                    listaAsentamientos.add(listaColonias.get(0).getAsentamientoTipoId().getNombre());
                    response.getData().setTipoAsentamientos(listaAsentamientos);
                    nuevo.setIdColonia(listaColonias.get(0).getId());
                    nuevo.setNombreColonia(listaColonias.get(0).getNombre());
                    listaNombreColonias.add(nuevo);
                    response.getData().setColonia(listaNombreColonias);
                
                }else{
                    listaColonias.forEach((nombreColonia) -> {
                        InfoColonia nuevo = new InfoColonia();
                        nuevo.setIdColonia(nombreColonia.getId());
                        nuevo.setNombreColonia(nombreColonia.getNombre());    
                        listaNombreColonias.add(nuevo);
                    });
                    response.getData().setColonia(listaNombreColonias);
                    
                    boolean existe = false;
                    for(Colonia asentamiento : listaColonias){
                        if(listaAsentamientos.isEmpty()){
                            listaAsentamientos.add(asentamiento.getAsentamientoTipoId().getNombre());
                        }else{
                            for(String aux: listaAsentamientos){
                                existe = false;
                                if(aux.equals(asentamiento.getAsentamientoTipoId().getNombre())){
                                    existe = true;
                                    break;
                                }
                            }
                            if(!existe){
                                listaAsentamientos.add(asentamiento.getAsentamientoTipoId().getNombre());
                            }
                        }
                    }
                    response.getData().setTipoAsentamientos(listaAsentamientos);
                } 
            }
            
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.toString());
            response.setData(null);
        }

        return response;
    }

    public List<Colonia> getListaColonias() {
        return listaColonias;
    }

    public void setListaColonias(List<Colonia> listaColonias) {
        this.listaColonias = listaColonias;
    }

    public List<String> getListaAsentamientos() {
        return listaAsentamientos;
    }

    public void setListaAsentamientos(List<String> listaAsentamientos) {
        this.listaAsentamientos = listaAsentamientos;
    }

    public List<InfoColonia> getListaNombreColonias() {
        return listaNombreColonias;
    }

    public void setListaNombreColonias(List<InfoColonia> listaNombreColonias) {
        this.listaNombreColonias = listaNombreColonias;
    }

    
    
}
