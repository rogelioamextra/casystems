/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.CatValores;
import com.mx.ca.viu.modelos.CatValoresTiempoVida;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.modelos.MensajesValidaciones;
import com.mx.ca.viu.modelos.MvAdminNivelRiesgo;
import com.mx.ca.viu.modelos.MvConfigMensaje;
import com.mx.ca.viu.modelos.MvConfigNivelRiesgo;
import com.mx.ca.viu.modelos.MvConfigRiesgo;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.MvConfigTiempoVida;
import com.mx.ca.viu.modelos.ValidacionesValores;
import com.mx.ca.viu.modelos.dtos.CatConfiguracionSolicitud;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerConfiguracionSolicitud")
@javax.faces.bean.ViewScoped
public class ControllerConfiguracionSolicitud extends UtilServicios implements Serializable {

    private List<MvConfigSolicitudes> products;
    //private List<MvConfigMensajes> mensaje;
    private List<MvConfigSolicitudes> filtroConfiguracionGeneral;
    private MvConfigSolicitudes selectedProducts;

    private MvConfigNivelRiesgo selectedProductsNR;

    private List<MensajesValidaciones> listaConfiguracionMensaje;
    private List<MensajesValidaciones> listaConfiguracionMensajeAprob;
    private List<MensajesValidaciones> listaConfiguracionMensajeRech;
    private List<MensajesValidaciones> listaConfiguracionMensajePen;
    private List<MvConfigRiesgo> selectedProductsGrid;
    //private List<MvConfigMensajes> selectedProductsGridMensajes;
    private CatConfiguracionSolicitud checkProducts;

    String siguiente;
    String estiloCNR = "tarjetaConfiguracionRiesgo";
    String estiloANR = "tarjetaSinColor";
    String estiloCM = "tarjetaSinColor";
    String estiloCTV = "tarjetaSinColor";

    private String aprobada = "APROBADA";
    private String rechazada = "RECHAZADA";

    private boolean skip;
    private CatEmpresas empresaUsuarioSesion;

    private List<MvConfigSolicitudes> listaConfigSolicitudes;
    private List<MvConfigSolicitudes> filtroConfigSolicitudes;
    private MvConfigSolicitudes configSolicitudNuevo;

    private MvConfigRiesgo configNivelRiesgoNuevo;
    private List<MvConfigRiesgo> listaConfigRiesgo;
    private List<CatServiciosValidacionesExternos> listaConfigVal;
    private List<DtValidacionesServiciosEmpresa> listaConfigValEmpresas;
    private List<MvAdminNivelRiesgo> listaConfiguracion;
    private List<MvAdminNivelRiesgo> listaValidacionesAprob;
    private List<MvAdminNivelRiesgo> listaValidacionesPen;
    private List<MvAdminNivelRiesgo> listaValidacionesRech;

    private List<MvAdminNivelRiesgo> listaValidacionesActivasAprob;
    private List<MvAdminNivelRiesgo> listaValidacionesActivasPen;
    private List<MvAdminNivelRiesgo> listaValidacionesActivasRech;
    private List<MvConfigMensaje> mensajes;
    private MvConfigMensaje mensajeAprobado;
    private MvConfigMensaje mensajePendiente;
    private MvConfigMensaje mensajeRechazado;

    private CatProductos productoSelect;
    private CatDocumentos documentoSelect;
    private List<CatDocumentos> listaDocumentos;
    private String resultadoSelect;
    private List<CatValores> listaResultados;
    //***
    private CatValidaciones validacionSelect;
    private List<CatValidaciones> listaValidaciones;

    private CatValores valor2Select;
    private List<CatValores> listaValores;
    private ValidacionesValores valorValidacionSelected;
    private CatValidaciones validacionS;

    //private List<MvConfigMensajes> listaConfigMensajes;
    //private List<MvConfigMensajes> listaConfigMensajesAux;
    //private MvConfigMensajes configMensajesSelected;
    //private MvConfigMensajes nuevoCM;
    private List<CatEstatus> listaEstatus;
    private CatEstatus estatusSelected;

    private List<CatValoresTiempoVida> listaValoresTiempoVida;
    private CatValoresTiempoVida tiempoVidaSelected;

    private List<MvConfigTiempoVida> listaMvConfigTiempoVida;
    private MvConfigTiempoVida mvConfigTiempoVida;
    private CatValoresTiempoVida selectedTiempoVida;

    private List<CatEmpresas> listaEmpresas;
    private CatEmpresas empresaSelect;

    private List<MvConfigNivelRiesgo> listaMvConfigNivelRiesgo;
    private MvConfigNivelRiesgo nuevoConfigNivelRiesgo;

    int dim = 0;
    private Boolean configMensajeValido;

    public ControllerConfiguracionSolicitud() {
    }

    @PostConstruct
    public void init() {
        //nuevoCM = new MvConfigMensajes();
        filtroConfigSolicitudes = null;
        listaConfigRiesgo = new ArrayList<MvConfigRiesgo>();
        configNivelRiesgoNuevo = new MvConfigRiesgo();
        selectedProductsGrid= new ArrayList<>();
        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodasSolicitudes(false);//crear metodo y query
//                    listaEmpresas = llenarCombo(listaEmpresas);
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//
//                } else {
                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosProductosSolicitudesXEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    //BANDEJA DE CONFIGURACION DE SOLICITUDES
    public void buscarTodasSolicitudes(boolean activos) {
        listaConfigSolicitudes = mvConfigSolicitudService.buscarTodos(activos);
    }

    public void buscarTodosProductosSolicitudesXEmpresa(boolean activos, Long IdEmpresa) {
        listaConfigSolicitudes = mvConfigSolicitudService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void buscarTodosProductosXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaConfigSolicitudes = mvConfigSolicitudService.buscarTodos(false);
        } else {
            listaConfigSolicitudes = mvConfigSolicitudService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formContenido:tablaBandejaConfigSol");
        dataTable.resetValue();
    }

    public void cambiaPaginaEdicion(MvConfigSolicitudes producto) {
        filtroConfigSolicitudes = null;
        estiloCNR = "tarjetaConfiguracionRiesgo";
        estiloANR = "tarjetaSinColor";
        estiloCM = "tarjetaSinColor";
        estiloCTV = "tarjetaSinColor";
        selectedProducts = producto;
        getAdministradorPaginas().setPagina("admnistracion/configuracionSolicitud/configuracionSolicitud.xhtml");
        PrimeFaces.current().ajax().update("formContenido:tarjetaWizard");
        init();
        cargarListas();
    }

    public void validaEstatus(MvConfigSolicitudes producto) {
        this.configSolicitudNuevo = producto;

        if (genericoService.update(producto)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    //WIZARD
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            siguiente = event.getNewStep();
            switch (siguiente) {
                case "CNR"://configuracion nivel riesgo
                    estiloCNR = "tarjetaConfiguracionRiesgo";
                    estiloANR = "tarjetaSinColor";
                    estiloCM = "tarjetaSinColor";
                    estiloCTV = "tarjetaSinColor";
                    break;
                case "ANR"://administracion nivel riesgo
                    if (!event.getOldStep().equals("CM")) {
                        if(validarConfigNivelRiesgoCapturado()){
                        guardarRegistrosConfigNivelRiesgo();
                        }else{
                             WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.validacion.cnr.captura"));
                            PrimeFaces.current().ajax().update("formContenido:tarjetaWizard");
                            PrimeFaces.current().ajax().update("formContenido:msgCS");
                            return event.getOldStep();
                        }
                    }
                    buscarDatosAdminNivelRiesgo(selectedProducts.getIdConfigSolicitud(),selectedProducts.getIdEmpresa().getIdEmpresas());
                    estiloCNR = "tarjetaSinColor";
                    estiloANR = "tarjetaAdministracionRiesgo";
                    estiloCM = "tarjetaSinColor";
                    estiloCTV = "tarjetaSinColor";
                    break;
                case "CM"://configuracion de mensajes
                    if (!event.getOldStep().equals("CTV")) {
                        guardarAdminNivelRiesgo();
                    }
                    buscaConfiguracionValidacion();
                    estiloCNR = "tarjetaSinColor";
                    estiloANR = "tarjetaSinColor";
                    estiloCM = "tarjetaConfiguracionMensajes";
                    estiloCTV = "tarjetaSinColor";
                    //llenarComboConfigMensajes();
                    break;
                case "CTV"://configuracion de tiempo de vida
                    if (!event.getOldStep().equals("CTV")) {
                        configMensajeValido =false;
                        guardarConfigMensajes();
                    }else{
                        configMensajeValido=true;
                    }
                    //llenarComboConfigMensajes();
                    if(configMensajeValido){
                    llenarComboTiempoVida();
                    estiloCNR = "tarjetaSinColor";
                    estiloANR = "tarjetaSinColor";
                    estiloCM = "tarjetaSinColor";
                    estiloCTV = "tarjetaConfiguracionTiempo";
                    }else{
                        //return null;
                        PrimeFaces.current().ajax().update("formContenido:tarjetaWizard");
                        PrimeFaces.current().ajax().update("formContenido:msgCS");
                        return event.getOldStep();
                    }
                    break;
                default:
                    break;
            }
            PrimeFaces.current().ajax().update("formContenido:tarjetaWizard");
            return event.getNewStep();
        }
    }

    public void volverInicio() {
        getAdministradorPaginas().setPagina("admnistracion/configuracionSolicitud/bandejaConfiguracionSolicitud.xhtml");
    }

    public void finaliza() {
        //guardar config timepo de vida 
        guardarConfigTiempoVida();
        getAdministradorPaginas().setPagina("admnistracion/configuracionSolicitud/bandejaConfiguracionSolicitud.xhtml");
    }

    //METODOS DE CONFIGURACION DE NIVEL DE RIESGO ***********************************************
    public void guardarRegistrosConfigNivelRiesgo() {
        for (int i = 0; i < listaConfigRiesgo.size(); i++) {
            if (listaConfigRiesgo.get(i).getIdDocumento() != null && listaConfigRiesgo.get(i).getIdValidacion() != null && listaConfigRiesgo.get(i).getIdValor() != null
                    && listaConfigRiesgo.get(i).getResultado() != null && listaConfigRiesgo.get(i).getStatus() != null) {
                if (listaConfigRiesgo.get(i).getIdConfigNivelRiesgo() != null) {
                    listaConfigRiesgo.get(i).setIdConfigSolicitud(selectedProducts);
                    genericoService.update(listaConfigRiesgo.get(i));
                } else {
                    listaConfigRiesgo.get(i).setIdConfigSolicitud(selectedProducts);
                    genericoService.guardar(listaConfigRiesgo.get(i));
                }
            }
        }
    }

    //GUARDA ADMINISTRACION DE NIVEL DE RIESGO
    public void guardarAdminNivelRiesgo() {
        if (listaConfiguracion.size() > 0) {
            //mvConfigRiesgoService.eliminaConfiguracion(selectedProducts.getIdConfigSolicitud());
            for (int j = 0; j < listaConfigVal.size(); j++) {
                boolean banderaEncontro = false;
                for (int h = 0; h < listaConfiguracion.size(); h++) {
                    if (listaConfigVal.get(j).getIdServiciosValidaciones().equals(listaConfiguracion.get(h).getIdServicioValidacion().getIdServiciosValidaciones())) {
                        MvAdminNivelRiesgo listaConfiguracionAux = new MvAdminNivelRiesgo();
                        listaConfiguracionAux.setIdAdminNivelRiesgo(listaConfiguracion.get(h).getIdAdminNivelRiesgo());
                        listaConfiguracionAux.setIdConfigSolicitud(selectedProducts);
                        listaConfiguracionAux.setIdServicioValidacion(listaConfigVal.get(j));
                        listaConfiguracionAux.setStatus(listaConfigVal.get(j).getStatus());
                        if (listaConfigVal.get(j).getCoincidencia() == true) {
                            listaConfiguracionAux.setPorcentajeCoincidencia(listaConfigVal.get(j).getPorcentaje());
                        }
                        genericoService.update(listaConfiguracionAux);
                        banderaEncontro = true;
                        break;
                    }
                }
                if (banderaEncontro == false) {
                    MvAdminNivelRiesgo listaConfiguracionAux = new MvAdminNivelRiesgo();
                    listaConfiguracionAux.setIdConfigSolicitud(selectedProducts);
                    listaConfiguracionAux.setIdServicioValidacion(listaConfigVal.get(j));
                    listaConfiguracionAux.setStatus(listaConfigVal.get(j).getStatus());
                    if (listaConfigVal.get(j).getCoincidencia() == true) {
                        listaConfiguracionAux.setPorcentajeCoincidencia(listaConfigVal.get(j).getPorcentaje());
                    }
                    genericoService.guardar(listaConfiguracionAux);
                }
            }
        } else {
            for (int j = 0; j < listaConfigVal.size(); j++) {
                MvAdminNivelRiesgo listaConfiguracionAux = new MvAdminNivelRiesgo();
                listaConfiguracionAux.setIdConfigSolicitud(selectedProducts);
                listaConfiguracionAux.setIdServicioValidacion(listaConfigVal.get(j));
                listaConfiguracionAux.setStatus(listaConfigVal.get(j).getStatus());
                if (listaConfigVal.get(j).getCoincidencia() == true) {
                    listaConfiguracionAux.setPorcentajeCoincidencia(listaConfigVal.get(j).getPorcentaje());
                }
                genericoService.guardar(listaConfiguracionAux);
            }
        }
    }

    public void cargarListas() {
        buscarDatosConfigNivelRiesgo(selectedProducts.getIdConfigSolicitud());
        llenarComboDocumentos();
        //llenarComboValidaciones();
        llenarComboEstatus();
    }

    public void buscarDatosConfigNivelRiesgo(Long IdConfigSolicitud) {
        listaConfigRiesgo = mvConfigRiesgoService.buscarConfiguraciones(IdConfigSolicitud);
        if (listaConfigRiesgo != null) {
            for (int i = 0; i < listaConfigRiesgo.size(); i++) {
                listaConfigRiesgo.get(i).setListaValidacionesNueva(dtConfiguracionValidacionesService.buscarTodosValidaciones(false, listaConfigRiesgo.get(i).getIdDocumento(), listaConfigRiesgo.get(i).getIdConfigSolicitud().getIdEmpresa()));
                listaConfigRiesgo.get(i).setListaValoresNueva(validacionesValoresService.buscarValoresCat(listaConfigRiesgo.get(i).getIdValidacion().getIdValidaciones()));
            }
        }
    }

    public void buscaConfiguracionValidacion() {
        mensajes = mvConfigMensajesService.buscaMensaje(selectedProducts.getIdConfigSolicitud());
        if (mensajes.size() > 0) {
            for (int j = 0; j < mensajes.size(); j++) {
                if (mensajes.get(j).getIdEstatus().getIdEstatus() == 1) {
                    mensajeAprobado = mensajes.get(j);
                } else if (mensajes.get(j).getIdEstatus().getIdEstatus() == 2) {
                    mensajeRechazado = mensajes.get(j);
                } else {
                    mensajePendiente = mensajes.get(j);
                }
            }
        } else {
            mensajeAprobado = new MvConfigMensaje();
            mensajePendiente = new MvConfigMensaje();
            mensajeRechazado = new MvConfigMensaje();
        }
        listaValidacionesAprob = mvConfigRiesgoService.buscarConfiguracion(selectedProducts.getIdConfigSolicitud(), true);
        listaValidacionesRech = mvConfigRiesgoService.buscarConfiguracion(selectedProducts.getIdConfigSolicitud(), true);
        listaValidacionesPen = mvConfigRiesgoService.buscarConfiguracion(selectedProducts.getIdConfigSolicitud(), true);

        listaValidacionesActivasAprob = recorreListaMensajes(listaValidacionesAprob, 1L);
        listaValidacionesActivasRech = recorreListaMensajes(listaValidacionesRech, 2L);
        listaValidacionesActivasPen = recorreListaMensajes(listaValidacionesPen, 3L);
    }

    public List<MvAdminNivelRiesgo> recorreListaMensajes(List<MvAdminNivelRiesgo> validaciones, Long estatus) {
        listaConfiguracionMensaje = mvConfigMensajesService.buscarConfigMensajes(selectedProducts.getIdConfigSolicitud(), estatus);
        if (validaciones != null) {
            for (int j = 0; j < validaciones.size(); j++) {
                if (listaConfiguracionMensaje.size() > 0) {
                    for (int h = 0; h < listaConfiguracionMensaje.size(); h++) {
                        if (validaciones.get(j).getIdServicioValidacion().getIdServiciosValidaciones().equals(listaConfiguracionMensaje.get(h).getIdServicioValidacion().getIdServiciosValidaciones())) {
                            validaciones.get(j).setStatus(listaConfiguracionMensaje.get(h).getAprobado());
                            validaciones.get(j).setIndistinto(listaConfiguracionMensaje.get(h).getIndistinto());
                            break;
                        } else {
                            validaciones.get(j).setStatus(false);
                            validaciones.get(j).setIndistinto(false);
                        }

                    }
                } else {
                    validaciones.get(j).setStatus(false);
                    validaciones.get(j).setIndistinto(false);
                }
            }
        }
        return validaciones;
    }

    public void buscarDatosAdminNivelRiesgo(Long IdConfigSolicitud, Long idEmpresa) {
        listaConfigVal = mvConfigRiesgoService.buscarValidaciones();
        listaConfigValEmpresas = mvConfigRiesgoService.buscarValidacionesEmpresa(idEmpresa);
        listaConfiguracion = mvConfigRiesgoService.buscarConfiguracion(IdConfigSolicitud, false);
        if (listaConfigVal != null) {
            for (int j = 0; j < listaConfigVal.size(); j++) {
                if (listaConfiguracion.size() > 0) {
                    for (int h = 0; h < listaConfiguracion.size(); h++) {
                        if (listaConfigVal.get(j).getIdServiciosValidaciones().equals(listaConfiguracion.get(h).getIdServicioValidacion().getIdServiciosValidaciones())) {
                            listaConfigVal.get(j).setStatus(listaConfiguracion.get(h).getStatus());
                            if (listaConfiguracion.get(h).getPorcentajeCoincidencia() != null) {
                                listaConfigVal.get(j).setPorcentaje(listaConfiguracion.get(h).getPorcentajeCoincidencia());
                            } else {
                                listaConfigVal.get(j).setPorcentaje(0L);
                            }
                            break;
                        } else {
                            listaConfigVal.get(j).setStatus(false);
                        }
                    }
                } else {
                    listaConfigVal.get(j).setStatus(false);
                }
                 listaConfigVal.get(j).setSiempreActivo(false);
                //ahora validamos que la lista de la configuración esté en la configuración de la empresa si no le desactivamos esa selección
                if(listaConfigValEmpresas!=null &&!listaConfigValEmpresas.isEmpty()){
                    //buscamos si existe
                    CatServiciosValidacionesExternos buscando = listaConfigVal.get(j);
                    Optional<DtValidacionesServiciosEmpresa> existe
                        = listaConfigValEmpresas.stream()
                                .filter(e -> Objects.equals(e.getIdServicioValidacion().getIdServiciosValidaciones(), buscando.getIdServiciosValidaciones()))
                                .findFirst();
                if (existe.isPresent()) {
                    
                    if(existe.get().getStatus()==false){
                        listaConfigVal.get(j).setStatus(false);
                     listaConfigVal.get(j).setPuedeActivar(false);
                    }else{
                        listaConfigVal.get(j).setPuedeActivar(true);
                    }
                     //vamos a hacer las validaciones de que se haya seleccionado en la Configuración del nivel de riesgo
                     
                     DtValidacionesServiciosEmpresa validacionEmpresa = existe.get();
                    if (validacionEmpresa.getIdConfigValidacion() != null) {
                        Optional<MvConfigRiesgo> existeServicioActivo
                                = listaConfigRiesgo.stream()
                                        .filter(e -> e.getIdValidacion() != null && Objects.equals(e.getIdValidacion().getIdValidaciones(), validacionEmpresa.getIdConfigValidacion().getIdValidaciones().getIdValidaciones()))
                                        .findFirst();
                        if (existeServicioActivo.isPresent()) {
                            MvConfigRiesgo validacion = existeServicioActivo.get();
                            if (validacion.getStatus()) {//si está activo entonces es obligatorio que lo tenga en el siguiente paso
                                listaConfigVal.get(j).setSiempreActivo(true);
                                listaConfigVal.get(j).setStatus(true);
                            }
                        }
                    }
                }else{
                    listaConfigVal.get(j).setPuedeActivar(true);
                }
                }else{
                    listaConfigVal.get(j).setPuedeActivar(true);
                }
                
                //vamos a validar que haya puesto Validacion de la lista nominal
                //primero validamos que este en la validacion correcta
               
               
                /*if(listaConfigVal.get(j).getIdServiciosValidaciones()==4){//viu.servicios.externos.lista.nominal
                 Optional<MvConfigRiesgo> existeServicioActivo//la validación 9 es la de la lista nóminal
                        = listaConfigRiesgo.stream()
                                .filter(e -> e.getIdValidacion()!=null && e.getIdValidacion().getIdValidaciones()== 9L)
                                .findFirst();
                 if(existeServicioActivo.isPresent()){
                     MvConfigRiesgo  nominal = existeServicioActivo.get();
                     if(nominal.getStatus()){//si está activo entonces es obligatorio que lo tenga en el siguiente paso
                         listaConfigVal.get(j).setSiempreActivo(true);
                         listaConfigVal.get(j).setStatus(true);
                     }
                 }
                }else if(listaConfigVal.get(j).getIdServiciosValidaciones()==1){//viu.servicios.externos.curp
                 Optional<MvConfigRiesgo> existeServicioActivo//la validación 5 es la curp
                        = listaConfigRiesgo.stream()
                                .filter(e -> e.getIdValidacion()!=null && e.getIdValidacion().getIdValidaciones()== 5L)
                                .findFirst();
                 if(existeServicioActivo.isPresent()){
                     MvConfigRiesgo  nominal = existeServicioActivo.get();
                     if(nominal.getStatus()){//si está activo entonces es obligatorio que lo tenga en el siguiente paso
                         listaConfigVal.get(j).setSiempreActivo(true);
                         listaConfigVal.get(j).setStatus(true);
                     }
                 }
                }*/
            }
        }
    }

    public void llenarComboDocumentos() {
        //llenar el combo con los documentos de la empresa logueada y con los de la empresa VIU
        listaDocumentos = catDocumentosService.buscarTodosXidEmpresaYGenerales(selectedProducts.getIdEmpresa().getIdEmpresas());
    }
    
    public void llenaSeleccion(MvConfigRiesgo solicitud, int valor) {

    }

    public void llenarComboValidaciones(MvConfigRiesgo solicitud) {
        solicitud.setListaValidacionesNueva(dtConfiguracionValidacionesService.buscarTodosValidaciones(true, solicitud.getIdDocumento(), selectedProducts.getIdEmpresa()));
        /*
        listaValidaciones = dtConfiguracionValidacionesService.buscarTodosValidaciones(true, solicitud.getIdDocumento().getIdDocumentos());
        listaValidaciones = dtConfiguracionValidacionesService.buscarTodosValidaciones(true, solicitud.getIdDocumento(), selectedProducts.getIdEmpresa());
         */
        //si sigue mandando null idValidacion desde metodo llenar valores, inicializar las validaciones aqui
        validacionS = solicitud.getIdValidacion();
    }

    public void llenarComboValores(MvConfigRiesgo solicitud) {
        if(solicitud.getIdValidacion()==null){
            solicitud.setListaValoresNueva(null);
            return;
        }
        //veremos si no hay otro renglon con la misma validación
               /* int index = listaConfigRiesgo.indexOf(solicitud);
                for(MvConfigRiesgo i:listaConfigRiesgo){
                    if( i.getIdValidacion()!=null && i!=solicitud
                            && i.getIdValor()!=null && solicitud.getIdValor()!=null){
                        if(Objects.equals(i.getIdValidacion().getIdValidaciones(), solicitud.getIdValidacion().getIdValidaciones())
                                &&Objects.equals(i.getIdValor().getIdValor(), solicitud.getIdValor().getIdValor())){
                            listaConfigRiesgo.get(index).setIdValidacion(null);
                            solicitud.setListaValoresNueva(null);
                             WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.validacion.seleccionada"));
                          return;
                        }
                    }
                }*/
        
        solicitud.setListaValoresNueva(validacionesValoresService.buscarValoresCat(solicitud.getIdValidacion().getIdValidaciones()));
        solicitud.setIdValor(null);
    }
    public void validarComboValores(MvConfigRiesgo solicitud){
        //veremos si no hay otro renglon con la misma validación
                int index = listaConfigRiesgo.indexOf(solicitud);
                for(MvConfigRiesgo i:listaConfigRiesgo){
                    if( i.getIdValidacion()!=null && i!=solicitud
                            && i.getIdValor()!=null && solicitud.getIdValor()!=null){
                        if( Objects.equals(i.getIdDocumento().getIdDocumentos(),solicitud.getIdDocumento().getIdDocumentos()) &&
                                Objects.equals(i.getIdValidacion().getIdValidaciones(), solicitud.getIdValidacion().getIdValidaciones())
                                &&Objects.equals(i.getIdValor().getIdValor(), solicitud.getIdValor().getIdValor())){
                            solicitud.setIdValor(null);
                             WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.validacion.seleccionada"));
                          return;
                        }
                    }
                }
        
    }

    public void asignaValidacion(MvConfigRiesgo solicitud, MvConfigRiesgo val) {
        solicitud.setIdValidacion(val.getIdValidacion());
    }

    /*
    public void llenarComboConfigMensajes() {
        if (selectedProducts.getIdConfigSolicitud() != null) {
            listaConfigMensajes = mvConfigMensajesService.buscarConfigMensajes(selectedProducts.getIdConfigSolicitud());
        }
    }
     */
    public void llenarComboEstatus() {
        listaEstatus = catEstatusService.buscarTodos();
    }

    public void validaEstatus() {
        if (configNivelRiesgoNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void validaPorcentaje(CatServiciosValidacionesExternos producto) {
        if (producto.getCoincidencia() == true) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    //ADMINISTRACION DE NIVEL DE RIESGO
    public void llenarListaNivelRiesgo(Long idSolicitud) {
        listaMvConfigNivelRiesgo = mvConfigNivelRiesgoService.buscarConfiguraciones(idSolicitud);
    }

    public void llenarListaAdminNivelRiesgo() {

        llenarListaNivelRiesgo(selectedProducts.getIdProducto().getIdProductos());
//      nuevoConfigNivelRiesgo.getIdConfigSolicitud().getMvConfigRiesgoList().get(0).getIdValidacion().getNombre();
        if (listaConfigRiesgo != null && listaConfigRiesgo.size() > 0) {
            for (int i = 0; i < listaConfigRiesgo.size(); i++) {
                if (listaMvConfigNivelRiesgo.size() > 0 && listaMvConfigNivelRiesgo.size() > 0) {

                    if (Objects.equals(listaMvConfigNivelRiesgo.get(i).getIdConfigSolicitud().getIdConfigSolicitud(), listaConfigRiesgo.get(i).getIdConfigSolicitud().getIdConfigSolicitud())
                            && Objects.equals(listaMvConfigNivelRiesgo.get(i).getIdDocumento().getIdDocumentos(), listaConfigRiesgo.get(i).getIdDocumento().getIdDocumentos())) {
                        nuevoConfigNivelRiesgo.setIdConfigSolicitud(listaConfigRiesgo.get(i).getIdConfigSolicitud());
                        nuevoConfigNivelRiesgo.setIdDocumento(listaConfigRiesgo.get(i).getIdDocumento());
                        nuevoConfigNivelRiesgo.setStatus(listaConfigRiesgo.get(i).getStatus());
                        listaMvConfigNivelRiesgo.add(nuevoConfigNivelRiesgo);

                    }
                } else {
                    nuevoConfigNivelRiesgo.setIdConfigSolicitud(listaConfigRiesgo.get(i).getIdConfigSolicitud());
                    nuevoConfigNivelRiesgo.setIdDocumento(listaConfigRiesgo.get(i).getIdDocumento());
                    nuevoConfigNivelRiesgo.setStatus(listaConfigRiesgo.get(i).getStatus());
                    listaMvConfigNivelRiesgo.add(nuevoConfigNivelRiesgo);
                }
            }
        }
    }

    public void guardarConfigMensajes() {
        
        //GUADRA LOS MENSAJES POR CADA SECCION
        if (mensajeAprobado.getIdMvConfigMensaje() == null) {
            mensajeAprobado.setIdConfigSolicitud(selectedProducts);
            mensajeAprobado.setIdEstatus(listaEstatus.get(0));
            genericoService.guardar(mensajeAprobado);
        } else {
            genericoService.update(mensajeAprobado);
        }

        if (mensajePendiente.getIdMvConfigMensaje() == null) {
            mensajePendiente.setIdConfigSolicitud(selectedProducts);
            mensajePendiente.setIdEstatus(listaEstatus.get(2));
            genericoService.guardar(mensajePendiente);
        } else {
            genericoService.update(mensajePendiente);
        }

        if (mensajeRechazado.getIdMvConfigMensaje() == null) {
            mensajeRechazado.setIdConfigSolicitud(selectedProducts);
            mensajeRechazado.setIdEstatus(listaEstatus.get(1));
            genericoService.guardar(mensajeRechazado);
        } else {
            genericoService.update(mensajeRechazado);
        }

        //GUARDA CONFIGURACION DE MENSAJE
       if(validarConfigMensajesRepetidos()){
         configMensajeValido =true;
       }else{
           configMensajeValido =false;
           return;
       }                   
        listaConfiguracionMensajeAprob = mvConfigMensajesService.buscarConfigMensajes(selectedProducts.getIdConfigSolicitud(), 1L);
        List<MensajesValidaciones> mensajesNoExisten = new ArrayList<>();
        if(listaConfiguracionMensajeAprob!=null && !listaConfiguracionMensajeAprob.isEmpty()){
       mensajesNoExisten.addAll(listaConfiguracionMensajeAprob);
        }
        for (int i = 0; i < listaValidacionesActivasAprob.size(); i++) {
            boolean bandera = false;
            if (listaConfiguracionMensajeAprob.size() > 0) {

                for (int j = 0; j < listaConfiguracionMensajeAprob.size(); j++) {
                    if (Objects.equals(listaValidacionesActivasAprob.get(i).getIdServicioValidacion().getIdServiciosValidaciones(), listaConfiguracionMensajeAprob.get(j).getIdServicioValidacion().getIdServiciosValidaciones())) {
                        listaConfiguracionMensajeAprob.get(j).setAprobado(listaValidacionesActivasAprob.get(i).getStatus());
                        listaConfiguracionMensajeAprob.get(j).setIndistinto(listaValidacionesActivasAprob.get(i).getIndistinto());
                        genericoService.update(listaConfiguracionMensajeAprob.get(j));
                        mensajesNoExisten.remove(listaConfiguracionMensajeAprob.get(j));
                        bandera = true;
                        break;
                    }
                }
                if (bandera == false) {
                    MensajesValidaciones nuevo = new MensajesValidaciones();
                    nuevo.setIdServicioValidacion(listaValidacionesActivasAprob.get(i).getIdServicioValidacion());
                    nuevo.setAprobado(listaValidacionesActivasAprob.get(i).getStatus());
                    nuevo.setIndistinto(listaValidacionesActivasAprob.get(i).getIndistinto());
                    nuevo.setIdMvConfigMensaje(mensajeAprobado);
                    genericoService.guardar(nuevo);
                }
            } else {
                MensajesValidaciones nuevo = new MensajesValidaciones();
                nuevo.setIdServicioValidacion(listaValidacionesActivasAprob.get(i).getIdServicioValidacion());
                nuevo.setAprobado(listaValidacionesActivasAprob.get(i).getStatus());
                nuevo.setIndistinto(listaValidacionesActivasAprob.get(i).getIndistinto());
                nuevo.setIdMvConfigMensaje(mensajeAprobado);
                genericoService.guardar(nuevo);
            }
        }
        
        listaConfiguracionMensajeRech = mvConfigMensajesService.buscarConfigMensajes(selectedProducts.getIdConfigSolicitud(), 2L);
        if(listaConfiguracionMensajeRech!=null && !listaConfiguracionMensajeRech.isEmpty()){
             mensajesNoExisten.addAll(listaConfiguracionMensajeRech);
        }
        for (int i = 0; i < listaValidacionesActivasRech.size(); i++) {
            boolean bandera = false;
            if (listaConfiguracionMensajeRech.size() > 0) {
                for (int j = 0; j < listaConfiguracionMensajeRech.size(); j++) {
                    if (Objects.equals(listaValidacionesActivasRech.get(i).getIdServicioValidacion().getIdServiciosValidaciones(), listaConfiguracionMensajeRech.get(j).getIdServicioValidacion().getIdServiciosValidaciones())) {
                        listaConfiguracionMensajeRech.get(j).setAprobado(listaValidacionesActivasRech.get(i).getStatus());
                        listaConfiguracionMensajeRech.get(j).setIndistinto(listaValidacionesActivasRech.get(i).getIndistinto());
                        genericoService.update(listaConfiguracionMensajeRech.get(j));
                        mensajesNoExisten.remove(listaConfiguracionMensajeRech.get(j));
                        bandera = true;
                        break;
                    }
                }
                if (bandera == false) {
                    MensajesValidaciones nuevo = new MensajesValidaciones();
                    nuevo.setIdServicioValidacion(listaValidacionesActivasRech.get(i).getIdServicioValidacion());
                    nuevo.setAprobado(listaValidacionesActivasRech.get(i).getStatus());
                    nuevo.setIndistinto(listaValidacionesActivasRech.get(i).getIndistinto());
                    nuevo.setIdMvConfigMensaje(mensajeRechazado);
                    genericoService.guardar(nuevo);
                }
            } else {
                MensajesValidaciones nuevo = new MensajesValidaciones();
                nuevo.setIdServicioValidacion(listaValidacionesActivasRech.get(i).getIdServicioValidacion());
                nuevo.setAprobado(listaValidacionesActivasRech.get(i).getStatus());
                nuevo.setIndistinto(listaValidacionesActivasRech.get(i).getIndistinto());
                nuevo.setIdMvConfigMensaje(mensajeRechazado);
                genericoService.guardar(nuevo);
            }
        }
        listaConfiguracionMensajePen = mvConfigMensajesService.buscarConfigMensajes(selectedProducts.getIdConfigSolicitud(), 3L);
        if(listaConfiguracionMensajePen!=null && !listaConfiguracionMensajePen.isEmpty()){
             mensajesNoExisten.addAll(listaConfiguracionMensajePen);
        }
        for (int i = 0; i < listaValidacionesActivasPen.size(); i++) {
            boolean bandera = false;
            if (listaConfiguracionMensajePen.size() > 0) {
                for (int j = 0; j < listaConfiguracionMensajePen.size(); j++) {
                    if (Objects.equals(listaValidacionesActivasPen.get(i).getIdServicioValidacion().getIdServiciosValidaciones(), listaConfiguracionMensajePen.get(j).getIdServicioValidacion().getIdServiciosValidaciones())) {
                        listaConfiguracionMensajePen.get(j).setAprobado(listaValidacionesActivasPen.get(i).getStatus());
                        listaConfiguracionMensajePen.get(j).setIndistinto(listaValidacionesActivasPen.get(i).getIndistinto());
                        genericoService.update(listaConfiguracionMensajePen.get(j));
                         mensajesNoExisten.remove(listaConfiguracionMensajePen.get(j));
                        bandera = true;
                        break;
                    }
                }
                if (bandera == false) {
                    MensajesValidaciones nuevo = new MensajesValidaciones();
                    nuevo.setIdServicioValidacion(listaValidacionesActivasPen.get(i).getIdServicioValidacion());
                    nuevo.setAprobado(listaValidacionesActivasPen.get(i).getStatus());
                    nuevo.setIndistinto(listaValidacionesActivasPen.get(i).getIndistinto());
                    nuevo.setIdMvConfigMensaje(mensajePendiente);
                    genericoService.guardar(nuevo);
                }
            } else {
                MensajesValidaciones nuevo = new MensajesValidaciones();
                nuevo.setIdServicioValidacion(listaValidacionesActivasPen.get(i).getIdServicioValidacion());
                nuevo.setAprobado(listaValidacionesActivasPen.get(i).getStatus());
                nuevo.setIndistinto(listaValidacionesActivasPen.get(i).getIndistinto());
                nuevo.setIdMvConfigMensaje(mensajePendiente);
                genericoService.guardar(nuevo);
            }
        }
        
        if(mensajesNoExisten!=null && !mensajesNoExisten.isEmpty()){
            List<Long> idsBorrar= new ArrayList<>();
            for(MensajesValidaciones ne:mensajesNoExisten){
                idsBorrar.add(ne.getIdMensajesValidaciones());
               // genericoService.delete(ne);
            }
            if(idsBorrar!=null && !idsBorrar.isEmpty()){//se borran los ids
                mvConfigMensajesService.deleteMensajesValidacionesList(idsBorrar);
            }
        }
    }

    //public void onCellEdit(CellEditEvent<MvConfigMensajes> event) {
    //}
    //AGREGAR REGISTROS A TABLAS ******************************************************* 
    public void agregaRegistro(int valor) {

        if (valor == 1) {
            MvConfigRiesgo n = new MvConfigRiesgo();
            n.setStatus(true);
            if (this.listaConfigRiesgo == null) {
                this.listaConfigRiesgo = new ArrayList<>();
                listaConfigRiesgo.add(n);
            } else {
                listaConfigRiesgo.add(n);
            }
        } else if (valor == 2) {
//            if (this.listaConfigMensajes == null) {
//                this.listaConfigMensajes = new ArrayList<>();
//                listaConfigMensajes.add(new MvConfigMensajes());
//            } else {
//               listaConfigMensajes.add(new MvConfigMensajes());
//
//            }
            short a = 0;
            short b = 0;
            Long id = 10000L;
            //listaConfigMensajes.add(new MvConfigMensajes(id, a, b, "Mensaje", true));
        }
        //PrimeFaces.current().ajax().update("formContenido:dt-products ");
    }

    public void eliminaRegistro(int valor) {
        if (valor == 1) {
            if (selectedProductsGrid != null && !selectedProductsGrid.isEmpty()) {
                for(MvConfigRiesgo i:selectedProductsGrid){
                    listaConfigRiesgo.remove(i);
                }
                if (listaConfigRiesgo != null && selectedProductsGrid != null) {
                    for (MvConfigRiesgo nivel : selectedProductsGrid) {
                        mvConfigRiesgoService.eliminarRegistro(nivel.getIdConfigNivelRiesgo());
                    }
                }
            } else {
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.seleccionar"));
            }
            selectedProductsGrid = new ArrayList<MvConfigRiesgo>();
            //PrimeFaces.current().ajax().update("formContenido:dt-products ");
            
        } else if (valor == 2) {
            /*if(selectedProductsGridMensajes != null){
                listaConfigMensajes.removeAll(selectedProductsGridMensajes);
                if(listaConfigMensajes != null && selectedProductsGridMensajes!= null){
                    for (MvConfigMensajes mensaje : selectedProductsGridMensajes) {
                        mvConfigMensajesService.eliminarRegistro(mensaje.getIdConfigMensajes());
                    }
                }
            } else {
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.seleccionar"));
            }
            selectedProductsGridMensajes = null;*/
        }
    }

    /*public void eliminarConfigMensajes(MvConfigMensajes mensaje){
    }*/
//  CONFIG TIEMPO DE VIDA   *******************************************************************************************************************************
    public void guardarConfigTiempoVida() {
        if (!listaMvConfigTiempoVida.isEmpty() && listaMvConfigTiempoVida.get(0).getIdConfigTiempoVida() != null) {
            genericoService.update(mvConfigTiempoVida);
        } else {
            if (mvConfigTiempoVida.getIdValoresTiempoVida() != null) {
                mvConfigTiempoVida.setIdConfigTiempoVida(listaMvConfigTiempoVida.get(0).getIdValoresTiempoVida().getIdValoresTiempoVida());
                mvConfigTiempoVida.setIdConfigSolicitud(selectedProducts);
                genericoService.guardar(mvConfigTiempoVida);
            }
        }
        mvConfigTiempoVida = new MvConfigTiempoVida();
    }

    public void llenarComboTiempoVida() {
        listaMvConfigTiempoVida = mvTiempoVidaService.buscarRegistro(selectedProducts.getIdConfigSolicitud());
        if (!listaMvConfigTiempoVida.isEmpty()) {
            mvConfigTiempoVida = listaMvConfigTiempoVida.get(0);
            selectedTiempoVida = listaMvConfigTiempoVida.get(0).getIdValoresTiempoVida();
        } else {
            mvConfigTiempoVida = new MvConfigTiempoVida();
            listaMvConfigTiempoVida.add(mvConfigTiempoVida);
        }

        listaValoresTiempoVida = catValoresTiempoVidaService.buscarTodos(empresaUsuarioSesion.getIdEmpresas());
    }
    //método que valida que la configuración de los mensajes para aprobado, rechazado y pendiente no sea la misma
    public boolean validarConfigMensajesRepetidos(){
        boolean respuesta = true;
        //primero validaremos los mensajes de aprobado contra pendiente
        if(mensajeAprobado.getStatus() && mensajePendiente.getStatus()){
            int listasActivasTotal= listaValidacionesActivasAprob.size();
            int contadorCoiciden = 0;
            for (MvAdminNivelRiesgo ac : listaValidacionesActivasAprob) {
                //vamos a buscar la configuracion en la lista de pendientes que sea de la misma validacion
                Optional<MvAdminNivelRiesgo> existe
                        = listaValidacionesActivasPen.stream()
                                .filter(e -> Objects.equals(e.getIdServicioValidacion().getIdServiciosValidaciones(), ac.getIdServicioValidacion().getIdServiciosValidaciones()))
                                .findFirst();
                if (existe.isPresent()) {
                    MvAdminNivelRiesgo objetoExiste = existe.get();
                    if(Objects.equals(ac.getIndistinto(), objetoExiste.getIndistinto())
                            && Objects.equals(ac.getStatus(), objetoExiste.getStatus())){
                        contadorCoiciden++;
                    }
                }
                
            }
            if(contadorCoiciden ==listasActivasTotal){
                     WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.mensajes.repetidos"));
                    return false;
                }
        }
        //luego validamos aprobado contra rechazado
        if(mensajeAprobado.getStatus() && mensajeRechazado.getStatus()){
            int listasActivasTotal= listaValidacionesActivasAprob.size();
            int contadorCoiciden = 0;
            for (MvAdminNivelRiesgo ac : listaValidacionesActivasAprob) {
                //vamos a buscar la configuracion en la lista de pendientes que sea de la misma validacion
                Optional<MvAdminNivelRiesgo> existe
                        = listaValidacionesActivasRech.stream()
                                .filter(e -> Objects.equals(e.getIdServicioValidacion().getIdServiciosValidaciones(), ac.getIdServicioValidacion().getIdServiciosValidaciones()))
                                .findFirst();
                if (existe.isPresent()) {
                    MvAdminNivelRiesgo objetoExiste = existe.get();
                    if(Objects.equals(ac.getIndistinto(), objetoExiste.getIndistinto())
                            && Objects.equals(ac.getStatus(), objetoExiste.getStatus())){
                        contadorCoiciden++;
                    }
                }
                
            }
            if(contadorCoiciden ==listasActivasTotal){
                     WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.mensajes.repetidos"));
                    return false;
                }
        }
        //finamente validamos los pendientes contra rechazado
        if(mensajePendiente.getStatus() && mensajeRechazado.getStatus()){
            int listasActivasTotal= listaValidacionesActivasPen.size();
            int contadorCoiciden = 0;
            for (MvAdminNivelRiesgo ac : listaValidacionesActivasPen) {
                //vamos a buscar la configuracion en la lista de pendientes que sea de la misma validacion
                Optional<MvAdminNivelRiesgo> existe
                        = listaValidacionesActivasRech.stream()
                                .filter(e -> Objects.equals(e.getIdServicioValidacion().getIdServiciosValidaciones(), ac.getIdServicioValidacion().getIdServiciosValidaciones()))
                                .findFirst();
                if (existe.isPresent()) {
                    MvAdminNivelRiesgo objetoExiste = existe.get();
                    if(Objects.equals(ac.getIndistinto(), objetoExiste.getIndistinto())
                            && Objects.equals(ac.getStatus(), objetoExiste.getStatus())){
                        contadorCoiciden++;
                    }
                }
                
            }
            if(contadorCoiciden ==listasActivasTotal){
                     WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.mensajes.repetidos"));
                    return false;
                }
        }
        
        return respuesta;
    }
    public void validaEstatusConfigVal(CatServiciosValidacionesExternos configuracionCambiar){
        if(configuracionCambiar!=null){
           
                //no se puede activar entonces la regresara a como estaba
                //vamos a buscarla en la lista
                Optional<CatServiciosValidacionesExternos> existe
                        = listaConfigVal.stream()
                                .filter(e -> Objects.equals(e.getIdServiciosValidaciones(), configuracionCambiar.getIdServiciosValidaciones()))
                                .findFirst();
                if (existe.isPresent()) {
                    CatServiciosValidacionesExternos existeConfig = existe.get();
                     int index=listaConfigVal.indexOf(existeConfig);
                      if(!configuracionCambiar.isPuedeActivar()){
                     listaConfigVal.get(index).setStatus(false);
                     WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.inactivo.validacionesExternos"));
                }
                      if(configuracionCambiar.isSiempreActivo()){
                          listaConfigVal.get(index).setStatus(true);
                     WebGenerico.mensajeWarning(getAdministradorPaginas().getBUNDLE().getString("viu.generico.siempreActivo.validacionesExternos"));

                      }
                
            }
        }
    }
    
    public boolean validarConfigNivelRiesgoCapturado(){
        for (int i = 0; i < listaConfigRiesgo.size(); i++) {
            if (listaConfigRiesgo.get(i).getIdDocumento() != null && listaConfigRiesgo.get(i).getIdValidacion() != null && listaConfigRiesgo.get(i).getIdValor() != null
                    && listaConfigRiesgo.get(i).getResultado() != null && !"".equals(listaConfigRiesgo.get(i).getResultado()) && listaConfigRiesgo.get(i).getStatus() != null) {
                //todo ok
            }else{
                return false;
            }
        }
        return true;
    }

        public void toggleSelectAll(ToggleSelectEvent evt) {
        if (evt.isSelected()) {
            this.selectedProductsGrid = (List<MvConfigRiesgo>) listaConfigRiesgo;
        } else {
            this.selectedProductsGrid = new ArrayList<MvConfigRiesgo>();
        }
    }
    public void seleccionarProducto(SelectEvent evt) {
        /*if(selectedProductsGrid==null){
            selectedProductsGrid= new ArrayList<>();
        }
        MvConfigRiesgo producto = (MvConfigRiesgo) evt.getObject();
        selectedProductsGrid.add(producto);*/
    }

    public void deseleccionarProducto(UnselectEvent evt) {
       /* MvConfigRiesgo producto = (MvConfigRiesgo) evt.getObject();
        if(selectedProductsGrid!=null){
           selectedProductsGrid.remove(producto);
        }
        */
    }
    
    //METODOS GETER Y SETER *******************************************************************************
    public List<MvConfigSolicitudes> getProducts() {
        return products;
    }

    public void setProducts(List<MvConfigSolicitudes> products) {
        this.products = products;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(String siguiente) {
        this.siguiente = siguiente;
    }

    public String getEstiloCNR() {
        return estiloCNR;
    }

    public void setEstiloCNR(String estiloCNR) {
        this.estiloCNR = estiloCNR;
    }

    public String getEstiloANR() {
        return estiloANR;
    }

    public void setEstiloANR(String estiloANR) {
        this.estiloANR = estiloANR;
    }

    public String getEstiloCM() {
        return estiloCM;
    }

    public void setEstiloCM(String estiloCM) {
        this.estiloCM = estiloCM;
    }

    public String getEstiloCTV() {
        return estiloCTV;
    }

    public void setEstiloCTV(String estiloCTV) {
        this.estiloCTV = estiloCTV;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public MvConfigSolicitudes getSelectedProducts() {
        return selectedProducts;
    }

    public void setMvConfigSolicitudes(MvConfigSolicitudes selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public CatConfiguracionSolicitud getCheckProducts() {
        return checkProducts;
    }

    public void setCheckProducts(CatConfiguracionSolicitud checkProducts) {
        this.checkProducts = checkProducts;
    }

    public List<MvConfigRiesgo> getSelectedProductsGrid() {
        return selectedProductsGrid;
    }

    public void setSelectedProductsGrid(List<MvConfigRiesgo> selectedProductsGrid) {
        this.selectedProductsGrid = selectedProductsGrid;
    }

    public List<MvConfigSolicitudes> getFiltroConfiguracionGeneral() {
        return filtroConfiguracionGeneral;
    }

    public void setFiltroConfiguracionGeneral(List<MvConfigSolicitudes> filtroConfiguracionGeneral) {
        this.filtroConfiguracionGeneral = filtroConfiguracionGeneral;
    }

    public List<MvConfigSolicitudes> getListaSolicitudes() {
        return listaConfigSolicitudes;
    }

    public void setListaSolicitudes(List<MvConfigSolicitudes> listaConfigSolicitudes) {
        this.listaConfigSolicitudes = listaConfigSolicitudes;
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

    public MvConfigNivelRiesgo getSelectedProductsNR() {
        return selectedProductsNR;
    }

    public void setSelectedProductsNR(MvConfigNivelRiesgo selectedProductsNR) {
        this.selectedProductsNR = selectedProductsNR;
    }

    public List<MvConfigRiesgo> getListaConfigRiesgo() {
        return listaConfigRiesgo;
    }

    public void setListaConfigRiesgo(List<MvConfigRiesgo> listaConfigRiesgo) {
        this.listaConfigRiesgo = listaConfigRiesgo;
    }

    public CatProductos getProductoSelect() {
        return productoSelect;
    }

    public void setProductoSelect(CatProductos productoSelect) {
        this.productoSelect = productoSelect;
    }

    public CatDocumentos getDocumentoSelect() {
        return documentoSelect;
    }

    public void setDocumentoSelect(CatDocumentos documentoSelect) {
        this.documentoSelect = documentoSelect;
    }

    public List<CatDocumentos> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CatDocumentos> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public String getResultadoSelect() {
        return resultadoSelect;
    }

    public void setResultadoSelect(String resultadoSelect) {
        this.resultadoSelect = resultadoSelect;
    }

    public List<CatValores> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<CatValores> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public CatValidaciones getValidacionSelect() {
        return validacionSelect;
    }

    public void setValidacionSelect(CatValidaciones validacionSelect) {
        this.validacionSelect = validacionSelect;
    }

    public List<CatValidaciones> getListaValidaciones() {
        return listaValidaciones;
    }

    public void setListaValidaciones(List<CatValidaciones> listaValidaciones) {
        this.listaValidaciones = listaValidaciones;
    }

    public List<MvConfigSolicitudes> getListaConfigSolicitudes() {
        return listaConfigSolicitudes;
    }

    public void setListaConfigSolicitudes(List<MvConfigSolicitudes> listaConfigSolicitudes) {
        this.listaConfigSolicitudes = listaConfigSolicitudes;
    }

    public MvConfigSolicitudes getConfigSolicitudNuevo() {
        return configSolicitudNuevo;
    }

    public void setConfigSolicitudNuevo(MvConfigSolicitudes configSolicitudNuevo) {
        this.configSolicitudNuevo = configSolicitudNuevo;
    }

    public MvConfigRiesgo getConfigNivelRiesgoNuevo() {
        return configNivelRiesgoNuevo;
    }

    public void setConfigNivelRiesgoNuevo(MvConfigRiesgo configNivelRiesgoNuevo) {
        this.configNivelRiesgoNuevo = configNivelRiesgoNuevo;
    }

    public CatValores getValor2Select() {
        return valor2Select;
    }

    public void setValor2Select(CatValores valor2Select) {
        this.valor2Select = valor2Select;
    }

    public List<CatValores> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<CatValores> listaValores) {
        this.listaValores = listaValores;
    }

    public String getAprobada() {
        return aprobada;
    }

    public void setAprobada(String aprobada) {
        this.aprobada = "APROBADA";
    }

    public String getRechazada() {
        return rechazada;
    }

    public void setRechazada(String rechazada) {
        this.rechazada = "RECHAZADA";
    }

    /*public List<MvConfigMensajes> getListaConfigMensajes() {
        return listaConfigMensajes;
    }

    public void setListaConfigMensajes(List<MvConfigMensajes> listaConfigMensajes) {
        this.listaConfigMensajes = listaConfigMensajes;
    }

    public MvConfigMensajes getConfigMensajesSelected() {
        return configMensajesSelected;
    }

    public void setConfigMensajesSelected(MvConfigMensajes configMensajesSelected) {
        this.configMensajesSelected = configMensajesSelected;
    }

    public MvConfigMensajes getNuevoCM() {
        return nuevoCM;
    }

    public void setNuevoCM(MvConfigMensajes nuevoCM) {
        this.nuevoCM = nuevoCM;
    }*/
    public List<CatEstatus> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<CatEstatus> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public CatEstatus getEstatusSelected() {
        return estatusSelected;
    }

    public void setEstatusSelected(CatEstatus estatusSelected) {
        this.estatusSelected = estatusSelected;
    }

    /*public List<MvConfigMensajes> getListaConfigMensajesAux() {
        return listaConfigMensajesAux;
    }

    public void setListaConfigMensajesAux(List<MvConfigMensajes> listaConfigMensajesAux) {
        this.listaConfigMensajesAux = listaConfigMensajesAux;
    }*/
    public CatValidaciones getValidacionS() {
        return validacionS;
    }

    public void setValidacionS(CatValidaciones validacionS) {
        this.validacionS = validacionS;
    }

    public ValidacionesValores getValorValidacionSelected() {
        return valorValidacionSelected;
    }

    public void setValorValidacionSelected(ValidacionesValores valorValidacionSelected) {
        this.valorValidacionSelected = valorValidacionSelected;
    }

    public List<CatValoresTiempoVida> getListaValoresTiempoVida() {
        return listaValoresTiempoVida;
    }

    public void setListaValoresTiempoVida(List<CatValoresTiempoVida> listaValoresTiempoVida) {
        this.listaValoresTiempoVida = listaValoresTiempoVida;
    }

    public CatValoresTiempoVida getTiempoVidaSelected() {
        return tiempoVidaSelected;
    }

    public void setTiempoVidaSelected(CatValoresTiempoVida tiempoVidaSelected) {
        this.tiempoVidaSelected = tiempoVidaSelected;
    }

    public List<MvConfigTiempoVida> getListaMvConfigTiempoVida() {
        return listaMvConfigTiempoVida;
    }

    public void setListaMvConfigTiempoVida(List<MvConfigTiempoVida> listaMvConfigTiempoVida) {
        this.listaMvConfigTiempoVida = listaMvConfigTiempoVida;
    }

    public MvConfigTiempoVida getMvConfigTiempoVida() {
        return mvConfigTiempoVida;
    }

    public void setMvConfigTiempoVida(MvConfigTiempoVida mvConfigTiempoVida) {
        this.mvConfigTiempoVida = mvConfigTiempoVida;
    }

    public CatValoresTiempoVida getSelectedTiempoVida() {
        return selectedTiempoVida;
    }

    public void setSelectedTiempoVida(CatValoresTiempoVida selectedTiempoVida) {
        this.selectedTiempoVida = selectedTiempoVida;
    }

    public List<CatEmpresas> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<CatEmpresas> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public CatEmpresas getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(CatEmpresas empresaSelect) {
        this.empresaSelect = empresaSelect;
    }

    public List<MvConfigNivelRiesgo> getListaMvConfigNivelRiesgo() {
        return listaMvConfigNivelRiesgo;
    }

    public void setListaMvConfigNivelRiesgo(List<MvConfigNivelRiesgo> listaMvConfigNivelRiesgo) {
        this.listaMvConfigNivelRiesgo = listaMvConfigNivelRiesgo;
    }

    public MvConfigNivelRiesgo getNuevoConfigNivelRiesgo() {
        return nuevoConfigNivelRiesgo;
    }

    public void setNuevoConfigNivelRiesgo(MvConfigNivelRiesgo nuevoConfigNivelRiesgo) {
        this.nuevoConfigNivelRiesgo = nuevoConfigNivelRiesgo;
    }

    /*public List<MvConfigMensajes> getSelectedProductsGridMensajes() {
        return selectedProductsGridMensajes;
    }

    public void setSelectedProductsGridMensajes(List<MvConfigMensajes> selectedProductsGridMensajes) {
        this.selectedProductsGridMensajes = selectedProductsGridMensajes;
    }

    public List<MvConfigMensajes> getMensaje() {
        return mensaje;
    }

    public void setMensaje(List<MvConfigMensajes> mensaje) {
        this.mensaje = mensaje;
    }*/
    public List<CatServiciosValidacionesExternos> getListaConfigVal() {
        return listaConfigVal;
    }

    public void setListaConfigVal(List<CatServiciosValidacionesExternos> listaConfigVal) {
        this.listaConfigVal = listaConfigVal;
    }

    public List<MvAdminNivelRiesgo> getListaConfiguracion() {
        return listaConfiguracion;
    }

    public void setListaConfiguracion(List<MvAdminNivelRiesgo> listaConfiguracion) {
        this.listaConfiguracion = listaConfiguracion;
    }

    public List<MvAdminNivelRiesgo> getListaValidacionesAprob() {
        return listaValidacionesAprob;
    }

    public void setListaValidacionesAprob(List<MvAdminNivelRiesgo> listaValidacionesAprob) {
        this.listaValidacionesAprob = listaValidacionesAprob;
    }

    public List<MvAdminNivelRiesgo> getListaValidacionesPen() {
        return listaValidacionesPen;
    }

    public void setListaValidacionesPen(List<MvAdminNivelRiesgo> listaValidacionesPen) {
        this.listaValidacionesPen = listaValidacionesPen;
    }

    public List<MvAdminNivelRiesgo> getListaValidacionesRech() {
        return listaValidacionesRech;
    }

    public void setListaValidacionesRech(List<MvAdminNivelRiesgo> listaValidacionesRech) {
        this.listaValidacionesRech = listaValidacionesRech;
    }

    public List<MvConfigMensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MvConfigMensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<MensajesValidaciones> getListaConfiguracionMensaje() {
        return listaConfiguracionMensaje;
    }

    public void setListaConfiguracionMensaje(List<MensajesValidaciones> listaConfiguracionMensaje) {
        this.listaConfiguracionMensaje = listaConfiguracionMensaje;
    }

    public List<MvAdminNivelRiesgo> getListaValidacionesActivasAprob() {
        return listaValidacionesActivasAprob;
    }

    public void setListaValidacionesActivasAprob(List<MvAdminNivelRiesgo> listaValidacionesActivasAprob) {
        this.listaValidacionesActivasAprob = listaValidacionesActivasAprob;
    }

    public List<MvAdminNivelRiesgo> getListaValidacionesActivasPen() {
        return listaValidacionesActivasPen;
    }

    public void setListaValidacionesActivasPen(List<MvAdminNivelRiesgo> listaValidacionesActivasPen) {
        this.listaValidacionesActivasPen = listaValidacionesActivasPen;
    }

    public List<MvAdminNivelRiesgo> getListaValidacionesActivasRech() {
        return listaValidacionesActivasRech;
    }

    public void setListaValidacionesActivasRech(List<MvAdminNivelRiesgo> listaValidacionesActivasRech) {
        this.listaValidacionesActivasRech = listaValidacionesActivasRech;
    }

    public MvConfigMensaje getMensajeAprobado() {
        return mensajeAprobado;
    }

    public void setMensajeAprobado(MvConfigMensaje mensajeAprobado) {
        this.mensajeAprobado = mensajeAprobado;
    }

    public MvConfigMensaje getMensajePendiente() {
        return mensajePendiente;
    }

    public void setMensajePendiente(MvConfigMensaje mensajePendiente) {
        this.mensajePendiente = mensajePendiente;
    }

    public MvConfigMensaje getMensajeRechazado() {
        return mensajeRechazado;
    }

    public void setMensajeRechazado(MvConfigMensaje mensajeRechazado) {
        this.mensajeRechazado = mensajeRechazado;
    }

    public List<MvConfigSolicitudes> getFiltroConfigSolicitudes() {
        return filtroConfigSolicitudes;
    }

    public void setFiltroConfigSolicitudes(List<MvConfigSolicitudes> filtroConfigSolicitudes) {
        this.filtroConfigSolicitudes = filtroConfigSolicitudes;
    }
    
    

}
