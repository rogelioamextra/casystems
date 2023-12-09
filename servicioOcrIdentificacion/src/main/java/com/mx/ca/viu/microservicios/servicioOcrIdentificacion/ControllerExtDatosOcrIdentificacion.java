/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioOcrIdentificacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;

import static com.mx.ca.viu.commons.Constantes.mapper;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.IneSelfieNubarimRequest;
import com.mx.ca.viu.modelos.dtos.request.IneVsSelfieRequest;

import com.mx.ca.viu.modelos.dtos.request.OCRNubarimRequest;
import com.mx.ca.viu.modelos.dtos.response.IdentificacionOcrResponse;
import com.mx.ca.viu.modelos.dtos.request.OcrIdentificacionRequest;
import com.mx.ca.viu.modelos.dtos.request.OcrIdentificacionRequest2;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.CatServiciosValidacionesExternosServices;
import com.mx.ca.viu.services.DtcomparacionFacialService;
import com.mx.ca.viu.services.FTPService;
import com.mx.ca.viu.services.GenericoService;
import com.mx.ca.viu.services.MvConfigRiesgoService;
import com.mx.ca.viu.services.ResultadosValidacionesService;

import java.security.Principal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@RestController
public class ControllerExtDatosOcrIdentificacion {

    private static final Logger logger = LogManager.getLogger(ControllerExtDatosOcrIdentificacion.class.getName());

    @Autowired
    CatServiciosValidacionesExternosServices service;
    @Autowired
    public GenericoService genericoService;
    @Autowired
    CatEstadoService catEstadoService;
    @Autowired
    MvConfigRiesgoService mvConfigRiesgoService;
    @Autowired
    ResultadosValidacionesService resultadosValidacionService;

    @Autowired
    CatServiciosValidacionesExternosRepository servicios;

    @Autowired
    FTPService ftp;
    @Autowired
    DtcomparacionFacialService comparacionService;

    ResponseOCRIneNubarim obj = null;

    @PostMapping("ocr/ine/")
    public ResponseEntity<IdentificacionOcrResponse> extraccionDatos(@RequestBody OcrIdentificacionRequest request, Principal principal) throws JsonProcessingException {
        IdentificacionOcrResponse response = new IdentificacionOcrResponse();
        response.setResponse(new IdentificacionOcrResponse.Response());

        try {
            CatServiciosValidacionesExternos servicio = service.buscarServicioId(12L);
            OCRNubarimRequest body = new OCRNubarimRequest();
            body.setId(request.getData().getAnverso());
            // System.out.println("anverso: " + request.getData().getAnverso());
            //System.out.println("reverso: " + request.getData().getReverso());
            body.setIdReverso(request.getData().getReverso());
            String resultado = UtilGenerico.sendRequest(UtilGenerico.ObjectToJson(body), servicio.getCatConfiguracionesServiciosExternosList().get(0));
            // try {
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            resultado = resultado.replace("validacionesMRZ", "validacionMRZ");
            if (resultado.isEmpty()) {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("Servicio de OCR INE no se encuentra disponible, por favor intentelo mas tarde");
                return ResponseEntity.ok(response);
            }
            obj = mapper.readValue(resultado, ResponseOCRIneNubarim.class);
            if (obj.getMensaje() == null || obj.getMensaje().isEmpty()) {
                if (obj.getClaveElector() != null) {
                    response.getData().setClaveElector(obj.getClaveElector());
                }
                if (obj.getEmision() != null) {
                    response.getData().setEmision(obj.getEmision());
                }
                if (obj.getVigencia() != null) {
                    response.getData().setVigencia(obj.getVigencia());
                    int vigenciaIne = Integer.parseInt(response.getData().getVigencia());
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(UtilGenerico.obtenerHoraMexico());

                    int anoActual = cal.get(Calendar.YEAR);
                    if (vigenciaIne < anoActual) {
                        throw new Exception("La identificacion INE/IFE ya no es valida, por favor ingrese un documento valido");
                    }
                }

                if (obj.getMrz() != null && !obj.getMrz().isEmpty() && obj.getMrz().contains("<<")) {
                    response.getData().setNoIdentificacion(obj.getMrz().substring(0, obj.getMrz().indexOf("<<")));
                }

                byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(request.getData().getAnverso());
                img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
                byte[] img2 = org.apache.commons.codec.binary.Base64.decodeBase64(request.getData().getReverso());
                img2 = UtilGenerico.encriptarDesencriptarBytes(img2);
//                ftp.connect();
//                InputStream is = new ByteArrayInputStream(img1);
//                InputStream is2 = new ByteArrayInputStream(img2);
//
//                ftp.uploadFiles(request.getData().getCurp() + "/iden", "anv", is);
//                ftp.connect();
//
//                ftp.uploadFiles(request.getData().getCurp() + "/iden", "rev", is2);

                ftp.deleteFileSFTP("anv", obtenpropiedades(request.getData().getCurp() + "/iden"));
                ftp.deleteFileSFTP("rev", obtenpropiedades(request.getData().getCurp() + "/iden"));

                ftp.sendFileSFTP(img1, "anv", obtenpropiedades(request.getData().getCurp() + "/iden"));
                ftp.sendFileSFTP(img2, "rev", obtenpropiedades(request.getData().getCurp() + "/iden"));

                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");

            } else {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("No se pueden extraer los datos, favor de revisar la calidad de la imagen (enfocar bien y no debe tener sombras sobre la imagen)");
            }

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());

        }

        return ResponseEntity.ok(response);
//        
    }

    @PostMapping("ocr/ine2/")
    public ResponseEntity<IdentificacionOcrResponse> extraccionDatos2(@RequestParam("anverso") MultipartFile anverso,
            @RequestParam("reverso") MultipartFile reverso, @RequestParam("curp") String curp, Principal principal) throws JsonProcessingException {
        IdentificacionOcrResponse response = new IdentificacionOcrResponse();
        response.setResponse(new IdentificacionOcrResponse.Response());

        try {
            CatServiciosValidacionesExternos servicio = service.buscarServicioId(12L);
            OCRNubarimRequest body = new OCRNubarimRequest();
//            MultipartFile anverso=request.getData().getAnverso();
//            MultipartFile reverso=request.getData().getReverso();
            System.out.println("bytes: " + anverso.getBytes());
            String base64anverso = new String(org.apache.commons.codec.binary.Base64.encodeBase64(anverso.getBytes()));
            String base64reverso = new String(org.apache.commons.codec.binary.Base64.encodeBase64(reverso.getBytes()));
            body.setId(base64anverso);
//            System.out.println("anverso: "+request.getData().getAnverso());
//            System.out.println("reverso: "+request.getData().getReverso());
            body.setIdReverso(base64reverso);
            String resultado = UtilGenerico.sendRequest(UtilGenerico.ObjectToJson(body), servicio.getCatConfiguracionesServiciosExternosList().get(0));
            // try {
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            resultado = resultado.replace("validacionesMRZ", "validacionMRZ");
            obj = mapper.readValue(resultado, ResponseOCRIneNubarim.class);
            if (obj.getMensaje() == null || obj.getMensaje().isEmpty()) {
                if (obj.getClaveElector() != null) {
                    response.getData().setClaveElector(obj.getClaveElector());
                }
                if (obj.getEmision() != null) {
                    response.getData().setEmision(obj.getEmision());
                }
                if (obj.getVigencia() != null) {
                    response.getData().setVigencia(obj.getVigencia());

                }

                if (obj.getMrz() != null && !obj.getMrz().isEmpty() && obj.getMrz().contains("<<")) {
                    response.getData().setNoIdentificacion(obj.getMrz().substring(0, obj.getMrz().indexOf("<<")));
                }

                byte[] img1 = anverso.getBytes();
                img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
                byte[] img2 = reverso.getBytes();
                img2 = UtilGenerico.encriptarDesencriptarBytes(img2);
//                ftp.connect();
//                InputStream is = new ByteArrayInputStream(img1);
//                InputStream is2 = new ByteArrayInputStream(img2);
//
//                ftp.uploadFiles(request.getData().getCurp() + "/iden", "anv", is);
//                ftp.connect();
//
//                ftp.uploadFiles(request.getData().getCurp() + "/iden", "rev", is2);

                if (!ftp.sendFileSFTP(img1, "anv", obtenpropiedades(curp + "/iden"))
                        && !ftp.sendFileSFTP(img2, "rev", obtenpropiedades(curp + "/iden"))) {
                    System.out.println("no se pudo cargar las imagene de identificacion al sftp ");
                }

                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK:" + anverso.getBytes());

            } else {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("No se pueden extraer los datos, favor de revisar la calidad de la imagen (enfocar bien y no debe tener sombras sobre la imagen)");
            }

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());

        }

        return ResponseEntity.ok(response);
//        
    }

    @PostMapping("ocr/ine3/")
    public ResponseEntity<IdentificacionOcrResponse> extraccionDatos3(@RequestBody OcrIdentificacionRequest2 request, Principal principal) throws JsonProcessingException {
        IdentificacionOcrResponse response = new IdentificacionOcrResponse();
        response.setResponse(new IdentificacionOcrResponse.Response());

        try {
            CatServiciosValidacionesExternos servicio = service.buscarServicioId(12L);
            OCRNubarimRequest body = new OCRNubarimRequest();
//            MultipartFile anverso=request.getData().getAnverso();
//            MultipartFile reverso=request.getData().getReverso();

            String base64anverso = new String(org.apache.commons.codec.binary.Base64.encodeBase64(request.getData().getAnverso()));
            String base64reverso = new String(org.apache.commons.codec.binary.Base64.encodeBase64(request.getData().getReverso()));
            body.setId(base64anverso);
//            System.out.println("anverso: "+request.getData().getAnverso());
//            System.out.println("reverso: "+request.getData().getReverso());
            body.setIdReverso(base64reverso);
            String resultado = UtilGenerico.sendRequest(UtilGenerico.ObjectToJson(body), servicio.getCatConfiguracionesServiciosExternosList().get(0));
            // try {
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            resultado = resultado.replace("validacionesMRZ", "validacionMRZ");
            obj = mapper.readValue(resultado, ResponseOCRIneNubarim.class);
            if (obj.getMensaje() == null || obj.getMensaje().isEmpty()) {
                if (obj.getClaveElector() != null) {
                    response.getData().setClaveElector(obj.getClaveElector());
                }
                if (obj.getEmision() != null) {
                    response.getData().setEmision(obj.getEmision());
                }
                if (obj.getVigencia() != null) {
                    response.getData().setVigencia(obj.getVigencia());
                }

                if (obj.getMrz() != null && !obj.getMrz().isEmpty() && obj.getMrz().contains("<<")) {
                    response.getData().setNoIdentificacion(obj.getMrz().substring(0, obj.getMrz().indexOf("<<")));
                }

                byte[] img1 = request.getData().getAnverso();
                img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
                byte[] img2 = request.getData().getReverso();
                img2 = UtilGenerico.encriptarDesencriptarBytes(img2);
//                ftp.connect();
//                InputStream is = new ByteArrayInputStream(img1);
//                InputStream is2 = new ByteArrayInputStream(img2);
//
//                ftp.uploadFiles(request.getData().getCurp() + "/iden", "anv", is);
//                ftp.connect();
//
//                ftp.uploadFiles(request.getData().getCurp() + "/iden", "rev", is2);

                if (!ftp.sendFileSFTP(img1, "anv", obtenpropiedades(request.getData().getCurp() + "/iden"))
                        && !ftp.sendFileSFTP(img2, "rev", obtenpropiedades(request.getData().getCurp() + "/iden"))) {
                    System.out.println("no se pudo cargar las imagene de identificacion al sftp ");
                }

                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");

            } else {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("No se pueden extraer los datos, favor de revisar la calidad de la imagen (enfocar bien y no debe tener sombras sobre la imagen)");
            }

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());

        }

        return ResponseEntity.ok(response);
//        
    }

    private Map<String, String> obtenpropiedades(String path) {
        CatServiciosValidacionesExternos credenciales = servicios.buscarConfigFTP();
        Map<String, String> propiedades = new HashMap<>();
        propiedades.put("ftp.sender.user", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUser());
        propiedades.put("ftp.sender.password", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getPass());
        propiedades.put("ftp.sender.host", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUrl());
        propiedades.put("ftp.sender.port", "22");
        propiedades.put("ftp.sender.path", path);

        return propiedades;

    }

    @PostMapping(path = "ocr/reconocimientofacial", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> extraccionDatos(@RequestBody IneVsSelfieRequest request) {
        GenericResponse response = new GenericResponse();

        try {
            response.setResponse(new GenericResponse.Response());

            CatServiciosValidacionesExternos servicio = service.buscarServicioId(8L);
            IneSelfieNubarimRequest body = new IneSelfieNubarimRequest();
            body.setCredencial(request.getData().getIne());
            body.setCaptura(request.getData().getSelfie());
            body.setTipo("imagen");

            String resultado = UtilGenerico.sendRequest(UtilGenerico.ObjectToJson(body), servicio.getCatConfiguracionesServiciosExternosList().get(0));

            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            DtComparacionFacial obj = mapper.readValue(resultado, DtComparacionFacial.class);
            obj.setCurp(request.getData().getCurp());

            //      System.out.println("selfie:" + request.getData().getSelfie());
            byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(request.getData().getSelfie());
            img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
            //  System.out.println("tamaño de bytes de imagend e selfie: " + img1.length);
            ftp.deleteFileSFTP("selfie", obtenpropiedades(request.getData().getCurp() + "/sel"));
            if (!ftp.sendFileSFTP(img1, "selfie", obtenpropiedades(request.getData().getCurp() + "/sel"))) {
                System.out.println("no se pudo cargar las imagene de selfie al sftp ");
            }
            DtComparacionFacial previo = comparacionService.buscarCurp(request.getData().getCurp());
            if (previo != null) {
                previo.setCodigoValidacion(obj.getCodigoValidacion());
                previo.setEstatus(obj.getEstatus());
                previo.setMensaje(obj.getMensaje());
                previo.setSimilitud(obj.getSimilitud());
                previo.setFechaComparacion(UtilGenerico.obtenerHoraMexico());
                comparacionService.save(previo);

            } else {
                obj.setFechaComparacion(UtilGenerico.obtenerHoraMexico());
                genericoService.guardar(obj);
            }
            
         //   genericoService.deleteTiposIdentificacion(request.getData().getCurp());

            if (!obj.getEstatus().equals("ERROR")) {
                response.getResponse().setMensaje(obj.getMensaje());
                response.getResponse().setCodigo(200);
            } else {
                response.getResponse().setMensaje(obj.getMensaje());
                response.getResponse().setCodigo(500);

            }

        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(ControllerExtDatosOcrIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok(response);

    }

}
