/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioConsultaCP;

import com.mx.ca.viu.microservicios.concultaCatalogos.services.MvAvisosServices;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatDestinoCreditos;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatIdentificaciones;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatParentescos;
import com.mx.ca.viu.modelos.CatPatrimonios;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatTiemposEmpleoActual;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposViviendas;
import com.mx.ca.viu.modelos.dtos.response.CatAvisosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatCaracteristicasNegocioResponse;
import com.mx.ca.viu.modelos.dtos.response.CatColoniasResponse;
import com.mx.ca.viu.modelos.dtos.response.CatCuidadResponse;
import com.mx.ca.viu.modelos.dtos.response.CatDestinosCreditosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatEstadosCivilesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatEstadosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatFrecuenciasPagoResponse;
import com.mx.ca.viu.modelos.dtos.response.CatGirosNegocioResponse;
import com.mx.ca.viu.modelos.dtos.response.CatGradoEscolaresResponse;
import com.mx.ca.viu.modelos.dtos.response.CatMunicipiosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatNacionalidadesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatParentescosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatPatrimoniosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatProductosCreditoResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiemposActualesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiposIdentificacionesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiposResidenciasResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiposViviendasResponse;
import com.mx.ca.viu.modelos.dtos.response.CodigoPostal2;
import com.mx.ca.viu.modelos.dtos.response.CodigoPostalSepomexResponse;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.GenericoService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jbecerril
 */
@RestController
@RequestMapping("catalogos")

public class ControllerServicioConsultaCatalogos {

    @Autowired
    GenericoService genericoService;
    @Autowired
    CatEstadoService catEstadoService;
    @Autowired
    MvAvisosServices avisosServices;

    @GetMapping(value = "estadosCiviles")
    public ResponseEntity<CatEstadosCivilesResponse> consultar(Principal principal) {
        CatEstadosCivilesResponse response = new CatEstadosCivilesResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatEstadosCiviles.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "nacionalidades")
    public ResponseEntity<CatNacionalidadesResponse> consultarnacionalidades(Principal principal) {
        CatNacionalidadesResponse response = new CatNacionalidadesResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatNacionalidades.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "gradosEscolares")
    public ResponseEntity<CatGradoEscolaresResponse> consultarGrados(Principal principal) {

        CatGradoEscolaresResponse response = new CatGradoEscolaresResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatGradoMaximoEstudios.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "tiposResidencias")
    public ResponseEntity<CatTiposResidenciasResponse> consultaTiposResidencias(Principal principal) {

        CatTiposResidenciasResponse response = new CatTiposResidenciasResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatTiposResidencias.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "girosNegocioEmpresas")
    public ResponseEntity<CatGirosNegocioResponse> consultaGirosNegocio(Principal principal) {

        CatGirosNegocioResponse response = new CatGirosNegocioResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatGirosNegociosEmpresas.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "caracteristicasNegocios")
    public ResponseEntity<CatCaracteristicasNegocioResponse> consultaCaracteristicasNegocio(Principal principal) {

        CatCaracteristicasNegocioResponse response = new CatCaracteristicasNegocioResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatCaracteristicasNegocios.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "tiemposActualesEmpleo")
    public ResponseEntity<CatTiemposActualesResponse> consultaTiemposActuales(Principal principal) {

        CatTiemposActualesResponse response = new CatTiemposActualesResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatTiemposEmpleoActual.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "tiposViviendas")
    public ResponseEntity<CatTiposViviendasResponse> consultaTiposViviendas(Principal principal) {
        CatTiposViviendasResponse response = new CatTiposViviendasResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatTiposViviendas.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "estados")
    public ResponseEntity<CatEstadosResponse> consultaEstado(Principal principal) {

        CatEstadosResponse response = new CatEstadosResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatEstados.class, false));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "municipiosPorEstado/{id}")
    public ResponseEntity<CatMunicipiosResponse> consultaMunicipio(@PathVariable(value = "id") Long id, Principal principal) {
        CatMunicipiosResponse response = new CatMunicipiosResponse();
        try {
            response.getData().setLista(catEstadoService.buscarCatMunicipio(id));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "ciudadPorEstado/{id}")
    public ResponseEntity<CatCuidadResponse> consultaCiudad(@PathVariable(value = "id") Long id, Principal principal) {

        CatCuidadResponse response = new CatCuidadResponse();
        try {
            response.getData().setLista(catEstadoService.consultaCiudadesEstado(id));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "coloniaPorMunicipio/{id}")
    public ResponseEntity<CatColoniasResponse> consultaColonia(@PathVariable(value = "id") Long id, Principal principal) {

        CatColoniasResponse response = new CatColoniasResponse();
        try {
            response.getData().setLista(catEstadoService.buscarCatLocalidad(id));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "codigoPostal/{cp}")
    public ResponseEntity<CodigoPostalSepomexResponse> consultaCodigoPostal(@PathVariable(value = "cp") String cp, Principal principal) {

        CodigoPostalSepomexResponse response = new CodigoPostalSepomexResponse();
        try {
            CodigoPostal2 cop=new CodigoPostal2();
            cop.setColoniasLista(catEstadoService.consultaCPSepomex((cp)));
            if(cop.getColoniasLista()==null ||cop.getColoniasLista().isEmpty()){
             throw new  Exception("C.P. no encontrado, por favor intente con uno valido");
            
            }
            cop.setNombre(cp);
            cop.setMunicipioId(cop.getColoniasLista().get(0).getIdMunicipio());
            cop.setEstadoId(cop.getColoniasLista().get(0).getIdMunicipio().getIdEstado());
            
            response.getData().setInfo(cop);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "destinoCreditos")
    public ResponseEntity<CatDestinosCreditosResponse> consultaDestinoCreditos( Principal principal) {

        CatDestinosCreditosResponse response = new CatDestinosCreditosResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatDestinoCreditos.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "frecuenciasPago")
    public ResponseEntity<CatFrecuenciasPagoResponse> consultaFrecuenciasPago( Principal principal) {

        CatFrecuenciasPagoResponse response = new CatFrecuenciasPagoResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatFrecuenciaPago.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "parentescos")
    public ResponseEntity<CatParentescosResponse> consultaParentescos( Principal principal) {

        CatParentescosResponse response = new CatParentescosResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatParentescos.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "patrimonios")
    public ResponseEntity<CatPatrimoniosResponse> consultaPatrimonios( Principal principal) {

        CatPatrimoniosResponse response = new CatPatrimoniosResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatPatrimonios.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "tiposIdentificaciones")
    public ResponseEntity<CatTiposIdentificacionesResponse> consultaIdentificaciones( Principal principal) {

        CatTiposIdentificacionesResponse response = new CatTiposIdentificacionesResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatIdentificaciones.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    
    @GetMapping(value = "productos")
    public ResponseEntity<CatProductosCreditoResponse> consultaProductos( Principal principal) {

        CatProductosCreditoResponse response = new CatProductosCreditoResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatProductosCredito.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "plazos")
    public ResponseEntity<CatProductosCreditoResponse> consultaPlazos( Principal principal) {

        CatProductosCreditoResponse response = new CatProductosCreditoResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatProductosCredito.class, true));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping(value = "avisos")
    public ResponseEntity<CatAvisosResponse> consultaAvisos( Principal principal) {

        CatAvisosResponse response = new CatAvisosResponse();
        try {
            response.getData().setLista(avisosServices.getAvisos());
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

}
