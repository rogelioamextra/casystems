/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.generico;

import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.MvDatosSolicitud;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.repositorys.impl.CatCategoriasCamposRepositoryImpl;
import com.mx.ca.viu.services.BuscarServiciosValidacionService;
import com.mx.ca.viu.services.CatAvisosService;
import com.mx.ca.viu.services.CatCamposService;
import com.mx.ca.viu.services.CatCategoriasCamposService;
import com.mx.ca.viu.services.CatDocumentosService;
import com.mx.ca.viu.services.CatEmpresasService;
import com.mx.ca.viu.services.CatEnrolamientoService;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.CatEstatusService;
import com.mx.ca.viu.services.CatFoliosService;
import com.mx.ca.viu.services.CatProductosService;
import com.mx.ca.viu.services.CatRegionesService;
import com.mx.ca.viu.services.CatRolesService;
import com.mx.ca.viu.services.CatSolicitudService;
import com.mx.ca.viu.services.CatSucursalesService;
import com.mx.ca.viu.services.CatSubdireccionesService;
import com.mx.ca.viu.services.CatTableroService;
import com.mx.ca.viu.services.CatTiempoVidaService;
import com.mx.ca.viu.services.CatUsuariosService;
import com.mx.ca.viu.services.CatValoresTiempoVidaService;
import com.mx.ca.viu.services.CatZonasService;
import com.mx.ca.viu.services.DtConfiguracionValidacionesService;
import com.mx.ca.viu.services.FTPService;
import com.mx.ca.viu.services.GenericoService;
import com.mx.ca.viu.services.MvConfigMensajesService;
import com.mx.ca.viu.services.MvConfigNivelRiesgoService;
import com.mx.ca.viu.services.MvConfigRiesgoService;
import com.mx.ca.viu.services.MvConfigSolicitudesService;
import com.mx.ca.viu.services.MvConfigTiempoVidaService;
import com.mx.ca.viu.services.MvDatosSolicitudService;
import com.mx.ca.viu.services.ReporteProyeccion;
import com.mx.ca.viu.services.SolicitudAmextraService;
import com.mx.ca.viu.services.ValidacionesValoresService;
import java.io.Serializable;
import java.util.List;
//import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author jbecerril
 */
//@Component
//@ComponentScan("com.mx.ca.viu")
public class UtilServicios extends SpringBeanAutowiringSupport implements Serializable {

    public static final Logger logger = LogManager.getLogger(UtilServicios.class.getName());

    @ManagedProperty("#{administradorPaginas}")
    private AdministradorPaginas administradorPaginas;
    @Autowired
    public GenericoService genericoService;
    @Autowired
    public CatUsuariosService catUsuariosService;
    @Autowired
    public CatRolesService catRolesService;
    @Autowired
    public CatEmpresasService catEmpresasService;
    @Autowired
    public CatSucursalesService catSucursalesService;
    @Autowired
    public CatZonasService catZonasService;
    @Autowired
    public CatSubdireccionesService catSubdireccionesService;
    @Autowired
    public CatProductosService catProductosService;
    @Autowired
    public CatRegionesService catRegionesService;
    @Autowired
    public CatDocumentosService catDocumentosService;
    @Autowired
    public CatCamposService catCamposService;
    @Autowired
    public CatEnrolamientoService catEnrolamientoService;
    @Autowired
    public CatFoliosService catFoliosService;
    @Autowired
    public CatAvisosService catAvisosService;
    @Autowired
    public MvConfigSolicitudesService mvConfigSolicitudService;
    @Autowired
    public DtConfiguracionValidacionesService dtConfiguracionValidacionesService;
    @Autowired
    public ValidacionesValoresService validacionesValoresService;
    @Autowired
    public MvConfigRiesgoService mvConfigRiesgoService;
    @Autowired
    public MvConfigMensajesService mvConfigMensajesService;
    @Autowired
    public CatEstatusService catEstatusService;
    @Autowired
    public BuscarServiciosValidacionService buscarServiciosValidacionService;
    @Autowired
    public FTPService FTPService;
    @Autowired
    public CatValoresTiempoVidaService catValoresTiempoVidaService;
    @Autowired
    public MvConfigTiempoVidaService mvTiempoVidaService;
    @Autowired
    public MvConfigNivelRiesgoService mvConfigNivelRiesgoService;
    @Autowired
    public CatCategoriasCamposService catCategoriasCamposService;
    @Autowired
    public CatTiempoVidaService catTiempoVidaService;
    @Autowired
    public MvDatosSolicitudService mvDatosSolicitudService;
    @Autowired
    public CatTableroService catTableroService;
    @Autowired
    public CatEstadoService demograficosService;
    @Autowired
    public ReporteProyeccion reporteService;
    @Autowired
    public SolicitudAmextraService solicitudService;
     @Autowired
    public CatSolicitudService solicitudServiceAmextra;

    @Autowired
    public CatServiciosValidacionesExternosRepository servicios;

    public AdministradorPaginas getAdministradorPaginas() {
        return administradorPaginas;
    }

    public void setAdministradorPaginas(AdministradorPaginas administradorPaginas) {
        this.administradorPaginas = administradorPaginas;
    }

    public List<CatEmpresas> llenarCombo(List<CatEmpresas> listaEmpresas) {
        listaEmpresas = catEmpresasService.buscarTodos(false);
        listaEmpresas.add(new CatEmpresas(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos")));
        desplazarDerecha(listaEmpresas);

        return listaEmpresas;
    }

    public static void desplazarDerecha(List<CatEmpresas> a) {
        CatEmpresas aux = a.get(a.size() - 1);  //guardar el último elemento en una variable                           
        a.add(0, aux);                 //insertar al principio el último valor
        a.remove(a.size() - 1);         //eliminar el último elemento
    }

}
