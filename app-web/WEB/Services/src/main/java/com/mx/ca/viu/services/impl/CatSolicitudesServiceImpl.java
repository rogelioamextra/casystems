/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.commons.Transform;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatDestinoCreditos;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;

import com.mx.ca.viu.modelos.CatPatrimonios;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatProperties;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatSucursales;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.DtComparacionFacial;

import com.mx.ca.viu.modelos.DtEgresos;
import com.mx.ca.viu.modelos.DtIngresos;
import com.mx.ca.viu.modelos.DtPatrimonio;

import com.mx.ca.viu.modelos.MvSolicitudesAmextra;

import com.mx.ca.viu.modelos.dtos.request.PatrimoniosSolicitud;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionRequest;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionResponse;
import com.mx.ca.viu.modelos.dtos.request.SolicitudRequest;
import com.mx.ca.viu.modelos.dtos.response.DTOMvsolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.response.Proyeccion;
import com.mx.ca.viu.modelos.dtos.response.SolicitudIdClienteResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudIdResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudListaResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudTodosResponse;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.repositorys.MvSolicitudesAmextraRepository;
import com.mx.ca.viu.services.CatSolicitudService;
import com.mx.ca.viu.services.FTPService;
import com.mx.ca.viu.services.ReporteProyeccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("CatSolicitudesService")
public class CatSolicitudesServiceImpl implements CatSolicitudService {

    @Autowired
    GenericoRepository genericoRepository;

    @Autowired
    CatServiciosValidacionesExternosRepository servicios;
    @Autowired
    ReporteProyeccion reporte;
    @Autowired
    FTPService ftp;
    @Autowired
    MvSolicitudesAmextraRepository mvsolicitudes;

    @Override
    public SolicitudResponse solicitudNuevoTransform(SolicitudRequest request) {

        SolicitudResponse response = new SolicitudResponse();

        try {
                   
            if (request.getData().getClienteId() == null || request.getData().getClienteId().isEmpty()) {
                throw new Exception("El campo clienteId es requerido");

            }
            List<MvSolicitudesAmextra> solicitudesPrevias =mvsolicitudes.obtenerSolicitudesClienteActivas(Long.valueOf(request.getData().getClienteId()));
            if (solicitudesPrevias != null && solicitudesPrevias.size()>=2) {
                throw new Exception("El Cliente ya cuenta con 2 solicitudes de credito activas");

            }

            MvSolicitudesAmextra solicitud = new MvSolicitudesAmextra();
            solicitud.setJson(Transform.toJSON(request));
            solicitud.setIdCliente(genericoRepository.findByID(CatClientes.class,
                    Long.valueOf(request.getData().getClienteId())));
            solicitud
                    .setIdFrecuencia(genericoRepository.findByID(CatFrecuenciaPago.class, Long.valueOf(request.getData().getFrecuenciaPagoId())));
            solicitud.setIdDestinoCredito(genericoRepository.findByID(CatDestinoCreditos.class, Long.valueOf(request.getData().getDestinoCreditoId())));
            solicitud.setIdProductoCredito(genericoRepository.findByID(CatProductosCredito.class, Long.valueOf(request.getData().getProductoCreditoId())));
            solicitud.setPlazo(request.getData().getPlazo());
            solicitud.setRevolvente(Boolean.valueOf(request.getData().getRevolvente()));
            solicitud.setFechaSolicitud(UtilGenerico.parseStringToDate(request.getData().getFechaSolicitud(), "yyyy-MM-dd"));
            solicitud.setMonto(request.getData().getMonto());
            solicitud.setLatitud(request.getData().getLatitud());
            solicitud.setLongitud(request.getData().getLongitud());
            DtEgresos egreso = new DtEgresos();
            egreso = Transform.toObject(request.getData().getEgresos(), DtEgresos.class);
            
            egreso.setIdSolicitud(solicitud);
            DtIngresos ingreso = Transform.toObject(request.getData().getIngresos(), DtIngresos.class);
            List<DtEgresos> listaEgreso = new ArrayList<>();
            listaEgreso.add(egreso);
            ingreso.setIdSolicitud(solicitud);
            List<DtIngresos> listaIngresos = new ArrayList<>();
            listaIngresos.add(ingreso);
            List<DtPatrimonio> patri = new ArrayList<>();
            for (PatrimoniosSolicitud sol : request.getData().getPatrimonios()) {
                DtPatrimonio pat = new DtPatrimonio();
                //if (!sol.getImagen().isEmpty() && sol.isCambioImagen()) {
                pat.setIdSolicitud(solicitud);
                pat.setIdTipoPatrimonio(genericoRepository.findByID(CatPatrimonios.class, Long.valueOf(sol.getTipoPatrimonioId())));
                pat.setPrecio(sol.getPrecio());
                pat.setNombreImagen(sol.getImagen());

                patri.add(pat);

            }
            solicitud.setDtPatrimonioList(patri);
            solicitud.setDtIngresosList(listaIngresos);
            solicitud.setDtEgresosList(listaEgreso);
            CatEstatus estatus = genericoRepository.findByID(CatEstatus.class, 3L);
            solicitud.setIdEstatus(estatus);

            CatUsuarios asesor = genericoRepository.findByID(CatUsuarios.class, Long.valueOf(request.getData().getAsesorId()));
            solicitud.setIdAsesor(asesor);
            if (genericoRepository.guardar(solicitud)) {
//                CatClientes cli = genericoRepository.findByID(CatClientes.class, solicitud.getIdCliente().getIdCliente());
//                if (cli != null) {
//                    cli.setIdSucursal(solicitud.getIdAsesor().getIdConfiguracionEmpresa().getIdSucursal());
//                    genericoRepository.update(cli);
//                }
                for (DtPatrimonio sol : solicitud.getDtPatrimonioList()) {
                    if (!sol.getNombreImagen().isEmpty()) {
                        byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(sol.getNombreImagen());
                        img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
                        // ftp.connect();
//                        InputStream is = new ByteArrayInputStream(img1);
//                        if (ftp.uploadFiles((solicitud.getIdCliente().getIdPersona().getCurp() + "/pat" + solicitud.getDtPatrimonioList().get(0).getIdPatrimonio().toString()),
//                                solicitud.getIdCliente().getIdPersona().getCurp() + "_" + solicitud.getDtPatrimonioList().get(0).getIdTipoPatrimonio().getIdPatrimonio().toString(), is)) {
//                            sol.setNombreImagen(solicitud.getIdCliente().getIdPersona().getCurp() + "_" + solicitud.getDtPatrimonioList().get(0).getIdTipoPatrimonio().getIdPatrimonio().toString());
//                            genericoRepository.guardar(sol);
//                        }

                        if (ftp.sendFileSFTP(img1, solicitud.getIdCliente().getIdPersona().getCurp() + "_" + sol.getIdTipoPatrimonio().getIdPatrimonio().toString(),
                                obtenpropiedades((solicitud.getIdCliente().getIdPersona().getCurp() + "/pat" + sol.getIdPatrimonio().toString())))) {
                            sol.setNombreImagen(solicitud.getIdCliente().getIdPersona().getCurp() + "_" + sol.getIdTipoPatrimonio().getIdPatrimonio().toString());
                            genericoRepository.guardar(sol);
                        }

                    }

                }
                response.getData().setSolicitudId(solicitud.getIdSolicitud().toString());
                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");

            }

        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }

        return response;

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

    @Override
    public SolicitudTodosResponse obtenerTodosSolicitudes() {
        SolicitudTodosResponse response = new SolicitudTodosResponse();

        try {
            response.setData(new SolicitudTodosResponse.Data());
            List<MvSolicitudesAmextra> lista = genericoRepository.findAll(MvSolicitudesAmextra.class);
            for (MvSolicitudesAmextra sol : lista) {
                if (!sol.getDtPatrimonioList().isEmpty() && !sol.getDtPatrimonioList().get(0).getNombreImagen().isEmpty()) {
//                    if (ftp.connect()) {
//                        if (sol.getIdCliente() != null) {
//                            InputStream ip = ftp.dowloadFiles((sol.getIdCliente().getIdPersona().getCurp()
//                                    + "/pat"
//                                    + sol.getDtPatrimonioList().get(0).getIdPatrimonio().toString()),
//                                    sol.getDtPatrimonioList().get(0).getNombreImagen()
//                            );
//                            if (ip != null) {
//                                byte[] img1 = UtilGenerico.encriptarDesencriptarBytes(IOUtils.toByteArray(ip));
////                            byte[] img1 = IOUtils.toByteArray(ip);
//                                img1 = org.apache.commons.codec.binary.Base64.encodeBase64(img1);
//                                sol.getDtPatrimonioList().get(0).setNombreImagen(new String(img1));
//
//                            }
//                        }
//
//                    } 
                    byte[] img1 = ftp.dowloadFileSFTP(sol.getDtPatrimonioList().get(0).getNombreImagen(), obtenpropiedades((sol.getIdCliente().getIdPersona().getCurp() + "/pat" + sol.getDtPatrimonioList().get(0).getIdPatrimonio().toString())));
                    if (img1 != null) {
                        img1 = UtilGenerico.encriptarDesencriptarBytes(img1);

                        img1 = org.apache.commons.codec.binary.Base64.encodeBase64(img1);
                        sol.getDtPatrimonioList().get(0).setNombreImagen(new String(img1));
                    } else {
                        response.getResponse().setCodigo(500);
                        response.getResponse().setMensaje("no se puede conectar al file system");
                        return response;
                    }
                }

            }
            response.getData().setSolicitudes(lista);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        } finally {

        }
        return response;

    }

    @Override
    public SolicitudIdResponse obtenerSolicitudId(Long id) {
        SolicitudIdResponse response = new SolicitudIdResponse();

        try {
            response.setData(new SolicitudIdResponse.Data());

            MvSolicitudesAmextra sol = genericoRepository.findByID(MvSolicitudesAmextra.class, id);
            if (!sol.getDtPatrimonioList().isEmpty() && !sol.getDtPatrimonioList().get(0).getNombreImagen().isEmpty()) {
//                if (ftp.connect()) {
//
//                    InputStream ip = ftp.dowloadFiles((sol.getIdCliente().getIdPersona().getCurp()
//                            + "/pat"
//                            + sol.getDtPatrimonioList().get(0).getIdPatrimonio().toString()),
//                            sol.getDtPatrimonioList().get(0).getNombreImagen()
//                    );
//                    if (ip != null) {
//                        byte[] img1 = UtilGenerico.encriptarDesencriptarBytes(IOUtils.toByteArray(ip));
////                            byte[] img1 = IOUtils.toByteArray(ip);
//                        img1 = org.apache.commons.codec.binary.Base64.encodeBase64(img1);
//                        sol.getDtPatrimonioList().get(0).setNombreImagen(new String(img1));
//
//                    }
//
//                } 
                byte[] ip = ftp.dowloadFileSFTP(sol.getDtPatrimonioList().get(0).getNombreImagen(),
                        obtenpropiedades((sol.getIdCliente().getIdPersona().getCurp() + "/pat" + sol.getDtPatrimonioList().get(0).getIdPatrimonio().toString())));
                if (ip != null) {
                    byte[] img1 = UtilGenerico.encriptarDesencriptarBytes(ip);
                    //byte[] img1 = IOUtils.toByteArray(ip);
                    img1 = org.apache.commons.codec.binary.Base64.encodeBase64(img1);
                    sol.getDtPatrimonioList().get(0).setNombreImagen(new String(img1));
                } else {
                    response.getResponse().setCodigo(500);
                    response.getResponse().setMensaje("no se puede conectar al file system");
                    return response;
                }
            }

            response.getData().setSolicitud(sol);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        } finally {

        }
        return response;
    }

    @Override
    public SolicitudIdClienteResponse obtenerSolicitudIdCliente(Long id) {
        SolicitudIdClienteResponse response = new SolicitudIdClienteResponse();

        try {
            response.setData(new SolicitudIdClienteResponse.Data());

            List<MvSolicitudesAmextra> tmp = mvsolicitudes.obtenerSolicitudesCliente(id);
            List<DTOMvsolicitudesAmextra> lista = new ArrayList<>();

            for (MvSolicitudesAmextra aux : tmp) {
                DTOMvsolicitudesAmextra nuevo = new DTOMvsolicitudesAmextra();
                nuevo.setProducto(aux.getIdProductoCredito().getNombre());
                nuevo.setCliente(aux.getIdCliente().getIdPersona().getNombres() + " " + aux.getIdCliente().getIdPersona().getApellidoPaterno() + " " + aux.getIdCliente().getIdPersona().getApellidoMaterno());
                nuevo.setEstatus(aux.getIdEstatus().getNombre());
                nuevo.setFecha(aux.getFechaSolicitud());
                nuevo.setMonto(aux.getMonto());
                nuevo.setLatitud(aux.getLatitud());
                nuevo.setLongitud(aux.getLongitud());
                nuevo.setIdSolicitud(aux.getIdSolicitud());
                lista.add(nuevo);
            }

            response.getData().setSolicitudes(lista);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        } finally {

        }
        return response;
    }

    @Override
    public SolicitudIdClienteResponse obtenerSolicitudIdAsesor(Long id) {
        SolicitudIdClienteResponse response = new SolicitudIdClienteResponse();

        try {
            response.setData(new SolicitudIdClienteResponse.Data());

            List<MvSolicitudesAmextra> tmp = mvsolicitudes.obtenerSolicitudesAsesor(id);
            List<DTOMvsolicitudesAmextra> lista = new ArrayList<>();

            for (MvSolicitudesAmextra aux : tmp) {
                DTOMvsolicitudesAmextra nuevo = new DTOMvsolicitudesAmextra();
                nuevo.setProducto(aux.getIdProductoCredito().getNombre());
                nuevo.setCliente(aux.getIdCliente().getIdPersona().getNombres() + " " + aux.getIdCliente().getIdPersona().getApellidoPaterno() + " " + aux.getIdCliente().getIdPersona().getApellidoMaterno());
                nuevo.setEstatus(aux.getIdEstatus().getNombre());
                nuevo.setFecha(aux.getFechaSolicitud());
                nuevo.setMonto(aux.getMonto());
                nuevo.setLatitud(aux.getLatitud());
                nuevo.setLongitud(aux.getLongitud());
                nuevo.setIdSolicitud(aux.getIdSolicitud());
                lista.add(nuevo);
            }

            response.getData().setSolicitudes(lista);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        } finally {

        }
        return response;
    }

    @Override
    public SolicitudResponse solicitudUpdateTransform(SolicitudRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProyeccionResponse generaProyeccion(ProyeccionRequest request, boolean banderaWeb) {
        System.out.println("com.mx.ca.viu.services.impl.CatSolicitudesServiceImpl.generaProyeccion()");
        ProyeccionResponse response = new ProyeccionResponse();

        try {
            CatClientes cliente = genericoRepository.findByID(CatClientes.class, Long.valueOf(request.getData().getClienteId())); 
            CatProperties iva = genericoRepository.findByID(CatProperties.class, 2);
            CatProductosCredito tasa = genericoRepository.findByID(CatProductosCredito.class, Long.valueOf(request.getData().getProductoId()));
            CatFrecuenciaPago frec = genericoRepository.findByID(CatFrecuenciaPago.class, Long.valueOf(request.getData().getFrecuenciaId()));
            List<Proyeccion> listaProyeccion = new ArrayList<>();
            int cuotas = Integer.parseInt(request.getData().getNumeroCuotas());
            Double tasaMensual = Double.valueOf(tasa.getTasaInteres()) / 12;
            Double tasaDiaria = Double.valueOf(tasa.getTasaInteres()) / 360;
            Double factor = tasaDiaria / 100;
            Double iva2 = Double.valueOf(iva.getValor()) * factor;
            Double tasaIva2 = iva2 + factor;
            Double valorI = tasaIva2 * frec.getTotalDias();
            Double valorR = calculoR(Double.valueOf(request.getData().getMontoCredito()), valorI, Double.valueOf(cuotas));
            for (int i = 0; i < cuotas; i++) {
                Proyeccion p = new Proyeccion();
                p.setMonto(request.getData().getMontoCredito());
                p.setPlazo(request.getData().getNumeroCuotas());
                p.setCuota(UtilGenerico.redondear2decimales(valorR));
                Double saldo = 0.0D;
                if (listaProyeccion.isEmpty()) {
                    saldo = Double.valueOf(request.getData().getMontoCredito());
                } else {
                    saldo = Double.valueOf(listaProyeccion.get((listaProyeccion.size() - 1)).getSaldo());
                }

                Double interes = (saldo * valorI) / (1 + Double.valueOf(iva.getValor()));
                p.setInteres(UtilGenerico.redondear2decimales(interes));
                Double ivalista = interes * Double.valueOf(iva.getValor());
                p.setIva(UtilGenerico.redondear2decimales(ivalista));
                Double capital = valorR - interes - ivalista;
                p.setCapital(UtilGenerico.redondear2decimales(capital));
                p.setN(String.valueOf((i + 1)));
                Double sal = saldo - capital;
                System.out.println("este es el saldo antes del redondeo:" + sal);
                p.setSaldo(UtilGenerico.redondear2decimales(sal));
                listaProyeccion.add(p);

            }
            System.out.println("termino de hacer la proyeccion");
            if (!banderaWeb) {
                byte[] pdf = reporte.generaReporte(listaProyeccion,cliente, tasa, frec);
                pdf = org.apache.commons.codec.binary.Base64.encodeBase64(pdf);

                response.getData().setPdf(new String(pdf));
            }

            response.getData().setListaProyeccion(listaProyeccion);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");

        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }

        return response;

    }

    public Double calculoR(Double monto, Double i, Double n) {
        Double resultado = 0.0D;

        resultado = monto * (i * Math.pow((1 + i), n) / (Math.pow((1 + i), n) - 1));

        return resultado;
    }

    @Override
    public DtComparacionFacial obtenerFacematch(String curp) {
        return mvsolicitudes.obtenerFacematch(curp);
    }

}
