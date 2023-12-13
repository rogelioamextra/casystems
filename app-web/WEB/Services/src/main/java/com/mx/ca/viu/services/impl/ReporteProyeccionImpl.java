/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatProperties;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.MvSolicitudProducto;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.reporte.ContenidoReporte;
import com.mx.ca.viu.modelos.dtos.reporte.DetalleReporte;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionRequest;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionResponse;
import com.mx.ca.viu.modelos.dtos.response.Proyeccion;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.services.CatSolicitudService;
import com.mx.ca.viu.services.FTPService;
import com.mx.ca.viu.services.ReporteProyeccion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("reporteProyeccion")
public class ReporteProyeccionImpl implements ReporteProyeccion {

    @Autowired
    FTPService ftp;
    @Autowired
    CatServiciosValidacionesExternosRepository servicios;
    @Autowired
    CatSolicitudService solicitudservice;
    @Autowired
    GenericoRepository genericoRepository;

    private String nameJasperFile = "amextra.jrxml";
    private InputStream stream;
    private JasperReport report;

    private String nameJasperFileSub = "detalleProyeccion.jrxml";
    private InputStream streamSub;
    private JasperReport reportSub;
    private List<Double> totalCapital = new ArrayList<>();
    private List<Double> totalInteres = new ArrayList<>();
    private List<Double> totalIva = new ArrayList<>();
    private List<Double> totalCuota = new ArrayList<>();

    private void cargarReportes() {
        try {
            //stream = this.getClass().getResourceAsStream(nameJasperFile);
//            report = (JasperReport) JRLoader.loadObject(stream);
            report = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(nameJasperFile));

            //  stream.close();
            //
            // streamSub = this.getClass().getResourceAsStream(nameJasperFileSub);
//            reportSub = (JasperReport) JRLoader.loadObject(streamSub);
            reportSub = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(nameJasperFileSub));
            //streamSub.close();

        } catch (JRException ex) {
            Logger.getLogger(ReporteProyeccionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public byte[] generaReporte(MvSolicitudesAmextra solicitud, boolean banderaFinal) {

        cargarReportes();
        ContenidoReporte contenido = new ContenidoReporte();
        contenido = cargaContenido(solicitud, banderaFinal);
        JasperPrint print = null;
        byte[] resultado = null;

        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("logo", contenido.getLogo());
            map.put("sucursal", contenido.getSucursal());
            map.put("nocredito", contenido.getNocredito());
            map.put("nocliente", contenido.getNocliente());
            map.put("codigoProducto", contenido.getCodigoProducto());
            map.put("tipoOperacion", contenido.getTipoOperacion());
            map.put("montoCredito", contenido.getMontoCredito());
            map.put("tipoPlan", contenido.getTipoPlan());
            map.put("tasaInteres", contenido.getTasaInteres());
            map.put("fechaDesembolso", contenido.getFechaDesembolso());
            map.put("plazo", contenido.getPlazo());
            map.put("frecuenciaPago", contenido.getFrecuenciaPago());
            map.put("estado", contenido.getEstado());
            map.put("firma", contenido.getFirma());
            map.put("list", contenido.getList());
            map.put("totalCapital", contenido.getTotalCapital());
            map.put("totalInteres", contenido.getTotalInteres());
            map.put("totalIva", contenido.getTotalIva());
            map.put("totalCuota", contenido.getTotalCuota());
            map.put("nombreCliente", contenido.getNombreCliente());
            map.put("calle", contenido.getCalle());
            map.put("colonia", contenido.getColonia());
            map.put("municipio", contenido.getMunicipio());
            map.put("ciudad", contenido.getCiudad());
            map.put("estado2", contenido.getEstado2());

            map.put("subreport", reportSub);

            List<ContenidoReporte> listaData = new ArrayList<>();
            listaData.add(contenido);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaData);
            print = JasperFillManager.fillReport(report, map, beanColDataSource);
            ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream(print, outPutStream);
            outPutStream.flush();
            outPutStream.close();

            resultado = outPutStream.toByteArray();
            return resultado;

        } catch (Exception e) {
            System.out.println(e);

        }
        return null;

    }

    @Override
    public byte[] generaReporte(List<Proyeccion> proyeccion, CatClientes cliente, CatProductosCredito tasa, CatFrecuenciaPago frec) {

        cargarReportes();
        ContenidoReporte contenido = new ContenidoReporte();

        contenido = cargaContenido(proyeccion, cliente, tasa, frec);
        JasperPrint print = null;
        byte[] resultado = null;

        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("logo", contenido.getLogo());
            map.put("sucursal", contenido.getSucursal());
            map.put("nocredito", contenido.getNocredito());
            map.put("nocliente", contenido.getNocliente());
            map.put("codigoProducto", contenido.getCodigoProducto());
            map.put("tipoOperacion", contenido.getTipoOperacion());
            map.put("montoCredito", contenido.getMontoCredito());
            map.put("tipoPlan", contenido.getTipoPlan());
            map.put("tasaInteres", contenido.getTasaInteres());
            map.put("fechaDesembolso", contenido.getFechaDesembolso());
            map.put("plazo", contenido.getPlazo());
            map.put("frecuenciaPago", contenido.getFrecuenciaPago());
            map.put("estado", contenido.getEstado());
            map.put("firma", contenido.getFirma());
            map.put("list", contenido.getList());
            map.put("totalCapital", contenido.getTotalCapital());
            map.put("totalInteres", contenido.getTotalInteres());
            map.put("totalIva", contenido.getTotalIva());
            map.put("totalCuota", contenido.getTotalCuota());
            map.put("nombreCliente", contenido.getNombreCliente());
            map.put("calle", contenido.getCalle());
            map.put("colonia", contenido.getColonia());
            map.put("municipio", contenido.getMunicipio());
            map.put("ciudad", contenido.getCiudad());
            map.put("estado2", contenido.getEstado2());

            map.put("subreport", reportSub);

            List<ContenidoReporte> listaData = new ArrayList<>();
            listaData.add(contenido);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaData);
            print = JasperFillManager.fillReport(report, map, beanColDataSource);
            ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();

            JasperExportManager.exportReportToPdfStream(print, outPutStream);
            outPutStream.flush();
            outPutStream.close();

            resultado = outPutStream.toByteArray();
            return resultado;

        } catch (Exception e) {
            System.out.println(e);

        }
        return null;

    }

    private ContenidoReporte cargaContenido(MvSolicitudesAmextra sol, boolean banderaFinal) {
        totalCapital = new ArrayList<>();
        totalInteres = new ArrayList<>();
        totalIva = new ArrayList<>();
        totalCuota = new ArrayList<>();
        //   CatProductosCredito tasa = genericoRepository.findByID(CatProductosCredito.class, 1);

        ContenidoReporte resultado = new ContenidoReporte();
        resultado.setLogo(obtenerLogo());
        resultado.setCalle(sol.getIdCliente().getIdDireccion().getCalle());
        resultado.setCodigoProducto(sol.getIdProductoCredito().getNombre());
        resultado.setColonia(sol.getIdCliente().getIdDireccion().getIdColonia().getNombre());
        if (banderaFinal) {
            resultado.setEstado("VIGENTE");
        } else {
            resultado.setEstado("PENDIENTE");
        }

        resultado.setEstado2(sol.getIdCliente().getIdDireccion().getIdEstado().getNombre());
        if (banderaFinal) {
            resultado.setFechaDesembolso(UtilGenerico.stringToDateString(sol.getFechaAprobacion(), "dd/MM/yyyy"));
        } else {
            resultado.setFechaDesembolso("");
        }

        resultado.setFirma("nombre del firmante");
        resultado.setFrecuenciaPago(sol.getIdFrecuencia().getNombre());
        resultado.setList(generaContenido(sol, banderaFinal));
        resultado.setMontoCredito("$" + sol.getMonto());
        resultado.setMunicipio(sol.getIdCliente().getIdDireccion().getIdMunicipio().getNombre());
        resultado.setNocliente("");
        resultado.setNombreCliente(sol.getIdCliente().getIdPersona().getNombres() + " " + sol.getIdCliente().getIdPersona().getApellidoPaterno() + " " + sol.getIdCliente().getIdPersona().getApellidoMaterno());
        resultado.setPlazo(sol.getPlazo());
        resultado.setSucursal("");
        resultado.setCiudad("");
        resultado.setTasaInteres(sol.getIdProductoCredito().getTasaInteres() + "%(más IVA)");
        resultado.setTipoOperacion("Individual");
        resultado.setTipoPlan("CUOTA NIVELADA");
        resultado.setTotalCapital("$" + convieteString2Cifras(totalCapital.stream().mapToDouble(a -> a).sum()));
        resultado.setTotalInteres("$" + convieteString2Cifras(totalInteres.stream().mapToDouble(a -> a).sum()));
        resultado.setTotalIva("$" + convieteString2Cifras(totalIva.stream().mapToDouble(a -> a).sum()));
        resultado.setTotalCuota("$" + convieteString2Cifras(totalCuota.stream().mapToDouble(a -> a).sum()));

        return resultado;
    }

    private ContenidoReporte cargaContenido(List<Proyeccion> listaProyeccion, CatClientes cliente, CatProductosCredito tasa, CatFrecuenciaPago frec) {
        totalCapital = new ArrayList<>();
        totalInteres = new ArrayList<>();
        totalIva = new ArrayList<>();
        totalCuota = new ArrayList<>();

        ContenidoReporte resultado = new ContenidoReporte();
        resultado.setLogo(obtenerLogo());

        resultado.setCalle(cliente.getIdDireccion().getCalle());
        resultado.setCodigoProducto(tasa.getNombre());
        resultado.setColonia(cliente.getIdDireccion().getIdColonia().getNombre());

        resultado.setEstado("PENDIENTE");

        resultado.setEstado2(cliente.getIdDireccion().getIdEstado().getNombre());
        resultado.setFirma("nombre del firmante");
        resultado.setFrecuenciaPago(frec.getNombre());
        resultado.setMontoCredito(listaProyeccion.get(0).getMonto());
        resultado.setMunicipio(cliente.getIdDireccion().getIdMunicipio().getNombre());
        resultado.setNocliente("");
        resultado.setNombreCliente(cliente.getIdPersona().getNombres() + " " + cliente.getIdPersona().getApellidoPaterno() + " " + cliente.getIdPersona().getApellidoMaterno());
        resultado.setPlazo(listaProyeccion.get(0).getPlazo());
        resultado.setSucursal("");
        resultado.setCiudad("");
        resultado.setList(generaContenido(listaProyeccion));
        resultado.setTasaInteres(tasa.getTasaInteres() + "%(más IVA)");
        resultado.setTipoOperacion("Individual");
        resultado.setTipoPlan("CUOTA NIVELADA");
        resultado.setTotalCapital("$" + convieteString2Cifras(totalCapital.stream().mapToDouble(a -> a).sum()));
        resultado.setTotalInteres("$" + convieteString2Cifras(totalInteres.stream().mapToDouble(a -> a).sum()));
        resultado.setTotalIva("$" + convieteString2Cifras(totalIva.stream().mapToDouble(a -> a).sum()));
        resultado.setTotalCuota("$" + convieteString2Cifras(totalCuota.stream().mapToDouble(a -> a).sum()));

        return resultado;
    }

    private String convieteString2Cifras(Double aux) {
        BigDecimal bd = new BigDecimal(aux).setScale(2, RoundingMode.HALF_DOWN);
        //DecimalFormat formato = new DecimalFormat("#.00");
        //return formato.format(aux);
        return String.valueOf(bd.doubleValue());

    }

    private List<DetalleReporte> generaContenido(MvSolicitudesAmextra sol, boolean banderaFinal) {
        List<DetalleReporte> resultado = new ArrayList<>();

        ProyeccionRequest request = new ProyeccionRequest();
        request.setData(new ProyeccionRequest.Data());
        request.getData().setFrecuenciaId(sol.getIdFrecuencia().getIdFrecuenciaPago().toString());
        request.getData().setMontoCredito(sol.getMonto());
        request.getData().setNumeroCuotas(sol.getPlazo());
        request.getData().setClienteId(sol.getIdCliente().getIdCliente().toString());
        request.getData().setProductoId(sol.getIdProductoCredito().getIdProductosCredito().toString());
        ProyeccionResponse response = solicitudservice.generaProyeccion(request, banderaFinal);
        System.out.println("esta es la proyeccion del web :" + response);
        totalCapital = new ArrayList<>();
        totalCuota = new ArrayList<>();
        totalInteres = new ArrayList<>();
        totalIva = new ArrayList<>();
        if (response != null && response.getData() != null) {
            for (Proyeccion p : response.getData().getListaProyeccion()) {
                DetalleReporte r = new DetalleReporte();
                totalCapital.add(Double.valueOf(p.getCapital()));
                r.setCapital("$" + p.getCapital());
                totalCuota.add(Double.valueOf(p.getCuota()));
                r.setCuota("$" + p.getCuota());
                if (banderaFinal) {
                    r.setFecha(calculoFecha(p.getN(), sol.getIdFrecuencia().getTotalDias(), sol.getFechaAprobacion()));
                } else {
                    r.setFecha("");
                }

                totalInteres.add(Double.valueOf(p.getInteres()));
                r.setInteres("$" + p.getInteres());
                totalIva.add(Double.valueOf(p.getIva()));
                r.setIva("$" + p.getIva());
                r.setNo(p.getN());
                if (!p.getSaldo().contains("-")) {
                    r.setSaldo("$" + p.getSaldo());
                } else {
                    r.setSaldo("");
                }

                resultado.add(r);
            }

        }

        return resultado;

    }

    private List<DetalleReporte> generaContenido(List<Proyeccion> listaProyeccion) {
        List<DetalleReporte> resultado = new ArrayList<>();
        totalCapital = new ArrayList<>();
        totalInteres = new ArrayList<>();
        totalIva = new ArrayList<>();
        totalCuota = new ArrayList<>();
        for (Proyeccion p : listaProyeccion) {
            DetalleReporte r = new DetalleReporte();
            totalCapital.add(Double.valueOf(p.getCapital()));
            r.setCapital("$" + p.getCapital());
            totalCuota.add(Double.valueOf(p.getCuota()));
            r.setCuota("$" + p.getCuota());

            r.setFecha("");

            totalInteres.add(Double.valueOf(p.getInteres()));
            r.setInteres("$" + p.getInteres());
            totalIva.add(Double.valueOf(p.getIva()));
            r.setIva("$" + p.getIva());
            r.setNo(p.getN());
            if (!p.getSaldo().contains("-")) {
                r.setSaldo("$" + p.getSaldo());
            } else {
                r.setSaldo("");
            }

            resultado.add(r);
        }

        return resultado;

    }

    private InputStream obtenerLogo() {

        InputStream tmp = null;
        byte[] ip = ftp.dowloadFileSFTP("logo.png", obtenpropiedades("logo"));
        tmp = new ByteArrayInputStream(ip);
        return tmp;

    }

    private String calculoFecha(String N, Integer dias, Date fechaAprobacion) {
        int n = Integer.parseInt(N);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaAprobacion);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + (n * dias));

        return UtilGenerico.stringToDateString(cal.getTime(), "dd/MM/yyyy");

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

}
