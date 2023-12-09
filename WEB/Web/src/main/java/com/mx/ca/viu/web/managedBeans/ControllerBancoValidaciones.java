/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatTiposValidacionesDisponibles;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.CatValores;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.modelos.ValidacionesValores;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import com.mx.ca.viu.web.validaciones.Biometricos;
import com.mx.ca.viu.web.validaciones.Digital;
import com.mx.ca.viu.web.validaciones.Documentos;
import com.mx.ca.viu.web.validaciones.Gobierno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@ManagedBean(name = "controllerBancoValidaciones")
@javax.faces.bean.ViewScoped
public class ControllerBancoValidaciones extends UtilServicios implements Serializable {

    List<Documentos> listaDocumentos;
    private Documentos documentoNuevo;
    List<CatServiciosValidacionesExternos> listaBiometricos;
    List<CatServiciosValidacionesExternos> listaGobierno;
    List<CatServiciosValidacionesExternos> listaDigital;

    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;

    private CatDocumentos documentoSelect;
    private List<CatDocumentos> listaDoc;

    private CatValidaciones validacionSelect;
    private List<DtConfiguracionValidaciones> listaValidaciones;
    private List<DtConfiguracionValidaciones> listaAuxValidaciones;
    private List<DtConfiguracionValidaciones> filtroAuxValidaciones;
    private CatValores valorSelected;
    private boolean admin = false;
    boolean bandEmpresaBuscarTodos = false;
    
    private List<CatServiciosValidacionesExternos> listaCatSVE;
    private CatServiciosValidacionesExternos servicioSelect;

    private List<DtValidacionesServiciosEmpresa> listaDtVSE;
    private DtValidacionesServiciosEmpresa dtVSESelect;
    private DtValidacionesServiciosEmpresa dtAux;

    public ControllerBancoValidaciones() {
    }

    @PostConstruct
    public void init() {

        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
                    admin = true;
//                    buscarTodosDocumentos(false);
                listaEmpresas = new ArrayList<>();
                listaEmpresas.add(empresaUsuarioSesion);
//                    listaEmpresas = llenarCombo(listaEmpresas); //cuando se abra la vista validaciones mostrar todas las validacionesSolicitudes
//                    //documentoSelect = null agregar al query cuando docSelect sea null traer todas las validaciones
                    empresaSelect = empresaUsuarioSesion;
//                    mostrarConfigValidaciones();
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
                    buscarTodosXEmpresaLogueada(false, empresaUsuarioSesion.getIdEmpresas());
                    mostrarConfigValidaciones();
//                }
            }
        }
        
        listaGobierno = buscarServiciosValidacionService.buscarServiciosXTipo(3L, empresaSelect, true);
        //llenar lista digital
        listaDigital = buscarServiciosValidacionService.buscarServiciosXTipo(4L, empresaSelect, true);

    }
    //CONFIG VALIDACIONES BIOMETRICAS, GOBIERNO Y DIGITALES
    public void mostrarConfigValidaciones(){
        //si hay una config. guardada muestra los servicios guardados        
        listaDtVSE = buscarServiciosValidacionService.buscarTodosServiciosXEmpresa(false, empresaSelect);
        if(listaDtVSE == null || listaDtVSE.size() <= 0){
        //mostrar todos los servicios disponibles a configurar
            listaCatSVE = buscarServiciosValidacionService.buscarTodosServicios(admin, empresaSelect);
            for(int i=0; i<listaCatSVE.size(); i++){
                DtValidacionesServiciosEmpresa ve = new DtValidacionesServiciosEmpresa();
                ve.setIdServicioValidacion(listaCatSVE.get(i));
                ve.setStatus(false);
                ve.setIdEmpresa(empresaSelect);
                listaDtVSE.add(ve);
            }
            //guardar();
            //listaDtVSE = buscarServiciosValidacionService.buscarTodosServiciosXEmpresa(false, empresaSelect);
        }
        //     listaCSVE.add(new CatServiciosValidacionesExternos("DOCUMENTOS", admin, doc));
        
        //  listaCSVE.get(0).getDtValidacionesServiciosEmpresaList().get(0).getIdEmpresa().getNombre();
        //  listaCSVE.get(0).getDtValidacionesServiciosEmpresaList().size();
    }
    
    public void buscarTodosServiciosValidacionesXEmpresaFiltro(){
        listaDtVSE = new ArrayList<>();
        //mostrar los servicios guardados por empresa seleccionada como admin
        if(empresaSelect.getIdEmpresas()!= null){
            listaDtVSE = buscarServiciosValidacionService.buscarTodosServiciosXEmpresa(false, empresaSelect);
        }else{
        //se valida la opcion de buscar todos
            listaDtVSE = buscarServiciosValidacionService.buscarTodosServiciosGeneral(admin, empresaSelect);
        }
        
        
        if(listaDtVSE == null || listaDtVSE.size() <= 0){
        //mostrar todos los servicios disponibles
            listaCatSVE = buscarServiciosValidacionService.buscarTodosServicios(admin, empresaSelect);
            for(int i=0; i<listaCatSVE.size(); i++){
                DtValidacionesServiciosEmpresa ve = new DtValidacionesServiciosEmpresa();
                ve.setIdServicioValidacion(listaCatSVE.get(i));
                ve.setStatus(listaCatSVE.get(i).getStatus());
                ve.setIdEmpresa(empresaSelect);
                listaDtVSE.add(ve);
            }   
        }
        
        Collections.sort(listaDtVSE, new Comparator<DtValidacionesServiciosEmpresa>() {
            @Override
            public int compare(DtValidacionesServiciosEmpresa p1, DtValidacionesServiciosEmpresa p2) {
                    return new Integer(Math.toIntExact(p1.getIdServicioValidacion().getIdServiciosValidaciones() )).compareTo(new Integer(Math.toIntExact( p2.getIdServicioValidacion().getIdServiciosValidaciones() )));
            }
        });
                
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formContenido:tablaValidaciones");
        dataTable.resetValue();
    }
    
    public void guardar() {
        if (listaDtVSE != null && empresaSelect.getIdEmpresas()!= null) {
            for (DtValidacionesServiciosEmpresa aux : listaDtVSE) {       
                buscarServiciosValidacionService.guardarBancoValidaciones(aux, empresaSelect);
            }
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
        }else{
            for (DtValidacionesServiciosEmpresa aux : listaDtVSE) {
                buscarServiciosValidacionService.guardarBancoValidaciones(aux, aux.getIdEmpresa());
            }
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
        }   

    }
    
    
    
    
    
    //VALIDACIONES DOCUMENTOS
    public void guardar_valDoc() {
        boolean correcto = false;
        if (listaAuxValidaciones != null) {
            if(listaDoc != null || listaDoc.size()>0){
                if(!bandEmpresaBuscarTodos){
                    for (DtConfiguracionValidaciones aux : listaAuxValidaciones) {
                        DtValidacionesServiciosEmpresa serv = new DtValidacionesServiciosEmpresa();
                        serv.setStatus(aux.getStatus());
                        serv.setIdEmpresa(empresaSelect);
                        serv.setIdConfigValidacion(aux);
                        correcto = buscarServiciosValidacionService.guardarBancoValidaciones(serv, empresaSelect, documentoSelect);

                    }
                }else{            
                    for (DtConfiguracionValidaciones aux : listaAuxValidaciones) {
                        DtValidacionesServiciosEmpresa serv = new DtValidacionesServiciosEmpresa();
                        serv.setStatus(aux.getStatus());
                        serv.setIdEmpresa(aux.getIdEmpresas());
                        serv.setIdConfigValidacion(aux);
                        correcto = buscarServiciosValidacionService.guardarBancoValidaciones(serv, aux.getIdEmpresas(), documentoSelect);
                    }
                }
            }            
            bandEmpresaBuscarTodos = false;
            if(correcto)
                WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
            else
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }   

    }

    public void buscarTodosDocumentos(boolean activos) {
        listaDoc = catDocumentosService.buscarTodos(false);
    }

    public void buscarTodosXEmpresaLogueada(boolean activos, Long idEmpresa) {
        listaDoc = catDocumentosService.buscarTodosXidEmpresaYGenerales(idEmpresa);
    }

    public void buscarTodosDocumentosXEmpresaFiltro() {
        listaAuxValidaciones = new ArrayList<>();
        documentoSelect = null;
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaDoc = catDocumentosService.buscarTodos(false);
        } else {
            listaDoc = catDocumentosService.buscarTodosXidEmpresaYGenerales(empresaSelect.getIdEmpresas());
        }
    }
    //metodo que obtiene los valores del valor de la validacion
    public void recorrerLista(){
        for(int i=0; i<listaValidaciones.size(); i++){
            if(listaValidaciones.get(i).getIdValidaciones().getValidacionesValoresList().get(0).getIdValor()!= null){
                valorSelected = listaValidaciones.get(i).getIdValidaciones().getValidacionesValoresList().get(0).getIdValor();
                //valores+= valorSelected.toString() + "/n
            }
        }
    }
    
    public void llenarStatusValidacionesXDocumentoTodos(CatDocumentos doc){//mostrar todos los registros de validaciones del documento seleccionado
        listaAuxValidaciones = new ArrayList<>();
        listaDtVSE = buscarServiciosValidacionService.buscarTodosValidacionesXDoc(false, documentoSelect, empresaSelect);
        for(int i =0; i<listaDtVSE.size(); i++){
            DtConfiguracionValidaciones cv = new DtConfiguracionValidaciones();   
            cv.setIdDocumentos(listaDtVSE.get(i).getIdConfigValidacion().getIdDocumentos());            
            cv.setIdEmpresas(listaDtVSE.get(i).getIdEmpresa());
            cv.setIdValidaciones(listaDtVSE.get(i).getIdConfigValidacion().getIdValidaciones());
            cv.setStatus(listaDtVSE.get(i).getStatus());
            cv.setIdConfiguracionValidacion(listaDtVSE.get(i).getIdConfigValidacion().getIdConfiguracionValidacion());
            listaAuxValidaciones.add(cv);
        }
        bandEmpresaBuscarTodos = true;
    }

    List<DtConfiguracionValidaciones> listaAuxDtSVE;
    List<ValidacionesValores> valor;
    String valores="";
    public void llenarStatusValidacionesXEmpresayDoc(CatDocumentos doc, CatEmpresas emp){
        listaDtVSE = buscarServiciosValidacionService.buscarConfigDocXEmpresayDoc(false, doc, emp);
        //String valores="";
        //valor = new ArrayList<>(listaDtVSE.get(0).getIdConfigValidacion().getIdValidaciones().getValidacionesValoresList());
        //valores = valor.get(0).getIdValor().getNombre();
        
//        listaAuxDtSVE = buscarServiciosValidacionService.buscarTodosValidacionesDocXDocyEmp(false, doc, emp);
        listaAuxValidaciones = new ArrayList<>();
        boolean existe=false;
        if(listaValidaciones.size()>0){
            for(DtConfiguracionValidaciones val1 : listaValidaciones){
                if(listaDtVSE!=null || listaDtVSE.size()>0){
                    for(DtValidacionesServiciosEmpresa val2 : listaDtVSE){    
                        existe= false;
                        if(val1.equals(val2.getIdConfigValidacion())){
                            //DtConfiguracionValidaciones config = new DtConfiguracionValidaciones();
                            //config.setIdDocumentos(documentoSelect);
                            existe = true;
                            val2.getIdConfigValidacion().setIdEmpresas(emp);
                            val2.getIdConfigValidacion().setStatus(val2.getStatus());
                            listaAuxValidaciones.add(val2.getIdConfigValidacion());
                            break;
                        }
                    }
                    if(!existe){
                        val1.setIdEmpresas(emp);
                        val1.setStatus(false);
                        listaAuxValidaciones.add(val1);
                    }  
                }
            }   
        }
    }
    
    public void buscarTodasConfigValidacionXEmpresaYDocumentoFiltro() {
        listaValidaciones = new ArrayList<>();
        valorSelected = new CatValores();
        String valores = "";
        //empresaSelect y documentoSelect
        if (documentoSelect != null) {
            //buscar dtValidacionesServicios por empresa y documento cuando sea VIU
            if(empresaUsuarioSesion.getIdEmpresas()==1){
                if (empresaSelect != null) {
                    if(empresaSelect.getIdEmpresas() != null){//validar opcion de buscar todos
                        if(empresaSelect.getIdEmpresas()!=1 && documentoSelect.getIdEmpresa().getIdEmpresas() == 1){
                            listaValidaciones = dtConfiguracionValidacionesService.buscarValidacionesXEmpresaYDocumento(false,empresaUsuarioSesion.getIdEmpresas(), documentoSelect.getIdDocumentos()); 
                            recorrerLista();
                            llenarStatusValidacionesXEmpresayDoc(documentoSelect, empresaSelect);
                            //consulta que nos traiga los registros guardados con el valor del documento seleccionado
                        }else{
                            listaValidaciones = dtConfiguracionValidacionesService.buscarValidacionesXEmpresaYDocumento(false,empresaSelect.getIdEmpresas(), documentoSelect.getIdDocumentos()); 
                            recorrerLista();
                            //consulta que nos traiga los registros guardados con el valor del documento seleccionado, donde llenamos nuestra lista DTValidServEmp para despues guardar o actualizar los valores de las validaciones
                            llenarStatusValidacionesXEmpresayDoc(documentoSelect, empresaSelect);
                        }
                    }else{
                        //listaValidaciones = dtConfiguracionValidacionesService.buscarTodos(false, documentoSelect.getIdDocumentos());
                        //recorrerLista();
                        //consulta que nos traiga todos los registros guardados con el valor del documento seleccionado cuando la empresa sea buscar todos
                        llenarStatusValidacionesXDocumentoTodos(documentoSelect);
                    }
                }else{//se valida la primera vez que se entra al modulo de validacion de documentos con la empresaSelect null
                    listaValidaciones = dtConfiguracionValidacionesService.buscarTodos(false, documentoSelect.getIdDocumentos());
                    recorrerLista();    
                }
   
            }else{//llena la lista con las validaciones almacenadas como empresa
                //listaValidaciones = dtConfiguracionValidacionesService.buscarValidacionesXEmpresaYDocumento(false,empresaSelect.getIdEmpresas(), documentoSelect.getIdDocumentos());
                listaValidaciones = dtConfiguracionValidacionesService.buscarTodos(false, documentoSelect.getIdDocumentos());
                recorrerLista();    
                llenarStatusValidacionesXEmpresayDoc(documentoSelect, empresaUsuarioSesion);
            }
            
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formContenido:tablaValidacionDocumentos");
            dataTable.resetValue();
        }
    }
    

    public void llenarStatusValidacionesXEmpresayDoc(){
    //    listaDtVSE = buscarServiciosValidacionService.buscarTodosServiciosXEmpresaYDoc(true, empresaSelect, documentoSelect);
        if(listaDtVSE != null || listaDtVSE.size() > 0){
            for(int i=0; i<listaDtVSE.size(); i++){

                listaValidaciones.get(i).setStatus(listaDtVSE.get(i).getStatus());
            }   
        }
    }

    public void llenarStatusValidacionesXEmpresayDocBuscarTodos(){
    //    listaDtVSE = buscarServiciosValidacionService.buscarTodosServiciosXEmpresaYDoc(true, empresaSelect, documentoSelect);
        if(listaDtVSE != null || listaDtVSE.size() > 0){
            for(int i=0; i<listaDtVSE.size(); i++){

                listaValidaciones.get(i).setStatus(listaDtVSE.get(i).getStatus());
            }   
        }
    }


    public void validaEstatus(DtConfiguracionValidaciones validacion) {
        if (validacion.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }
    
    public void validaEstatusDoc() {
        if (documentoNuevo.isActivo() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void validaEstatusBio(CatServiciosValidacionesExternos bio) {
        if (bio.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void validaEstatusGob(CatServiciosValidacionesExternos gob) {

        if (gob.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void validaEstatusDig(CatServiciosValidacionesExternos dig) {

        if (dig.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }
    
    public void buscarTodosBiometricosXEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaBiometricos = buscarServiciosValidacionService.buscarServiciosXTipo(2L, empresaSelect, true);
        } else {
            listaBiometricos = buscarServiciosValidacionService.buscarServiciosXTipo(2L, empresaSelect, false);
        }
    }
    
    public void guardarBiometricos() {

        for (CatServiciosValidacionesExternos aux : listaBiometricos) {

            buscarServiciosValidacionService.guardarBancoValidaciones(aux, empresaSelect);
        }
    }

    public List<Documentos> getDocumentos() {
        return listaDocumentos;
    }

    public void setDocumentos(List<Documentos> listaSocumentos) {
        this.listaDocumentos = listaSocumentos;
    }

    public void cambiaPaginaBandeja() {
        getAdministradorPaginas().setPagina("bancoValidaciones/bandejaBancoValidaciones.xhtml");
        documentoSelect = null;
        listaAuxValidaciones = new ArrayList<>();
    }

    public void cambiaPaginaDocumentos() {
        getAdministradorPaginas().setPagina("bancoValidaciones/validacionDocumentos.xhtml");
        init();
        if(empresaUsuarioSesion.getIdEmpresas().equals(Constantes.ID_EMPRESA_VIU)){
            if(empresaSelect!=null){
                buscarTodosDocumentosXEmpresaFiltro();
            }
        } else {
        }
    }

    public void cambiaPaginaBiometricos() {
        getAdministradorPaginas().setPagina("bancoValidaciones/configValidaciones.xhtml");
        init();
        /*
        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            listaBiometricos = buscarServiciosValidacionService.buscarServiciosXTipo(2L, empresaSelect, true);
        } else {
            listaBiometricos = buscarServiciosValidacionService.buscarServiciosXTipo(2L, empresaSelect, false);
        }*/
    }

    ///public void cambiaPaginaGobierno() {
     //   getAdministradorPaginas().setPagina("bancoValidaciones/configValidaciones.xhtml");
    //}

    //public void cambiaPaginaDigital() {
    //    getAdministradorPaginas().setPagina("bancoValidaciones/validacionDigital.xhtml");
    //}

    public List<Documentos> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documentos> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public Documentos getDocumentoNuevo() {
        return documentoNuevo;
    }

    public void setDocumentoNuevo(Documentos documentoNuevo) {
        this.documentoNuevo = documentoNuevo;
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
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

    public CatDocumentos getDocumentoSelect() {
        return documentoSelect;
    }

    public void setDocumentoSelect(CatDocumentos documentoSelect) {
        this.documentoSelect = documentoSelect;
    }

    public List<CatDocumentos> getListaDoc() {
        return listaDoc;
    }

    public void setListaDoc(List<CatDocumentos> listaDoc) {
        this.listaDoc = listaDoc;
    }

    public CatValidaciones getValidacionSelect() {
        return validacionSelect;
    }

    public void setValidacionSelect(CatValidaciones validacionSelect) {
        this.validacionSelect = validacionSelect;
    }

    public List<DtConfiguracionValidaciones> getListaValidaciones() {
        return listaValidaciones;
    }

    public void setListaValidaciones(List<DtConfiguracionValidaciones> listaValidaciones) {
        this.listaValidaciones = listaValidaciones;
    }

    public CatValores getValorSelected() {
        return valorSelected;
    }

    public void setValorSelected(CatValores valorSelected) {
        this.valorSelected = valorSelected;
    }

    public List<CatServiciosValidacionesExternos> getListaBiometricos() {
        return listaBiometricos;
    }

    public void setListaBiometricos(List<CatServiciosValidacionesExternos> listaBiometricos) {
        this.listaBiometricos = listaBiometricos;
    }

    public List<CatServiciosValidacionesExternos> getListaGobierno() {
        return listaGobierno;
    }

    public void setListaGobierno(List<CatServiciosValidacionesExternos> listaGobierno) {
        this.listaGobierno = listaGobierno;
    }

    public List<CatServiciosValidacionesExternos> getListaDigital() {
        return listaDigital;
    }

    public void setListaDigital(List<CatServiciosValidacionesExternos> listaDigital) {
        this.listaDigital = listaDigital;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<CatServiciosValidacionesExternos> getListaCSVE() {
        return listaCatSVE;
    }

    public void setListaCSVE(List<CatServiciosValidacionesExternos> listaCatSVE) {
        this.listaCatSVE = listaCatSVE;
    }

    public CatServiciosValidacionesExternos getServicioSelect() {
        return servicioSelect;
    }

    public void setServicioSelect(CatServiciosValidacionesExternos servicioSelect) {
        this.servicioSelect = servicioSelect;
    }

    public List<DtValidacionesServiciosEmpresa> getListaDVSE() {
        return listaDtVSE;
    }

    public void setListaDVSE(List<DtValidacionesServiciosEmpresa> listaDtVSE) {
        this.listaDtVSE = listaDtVSE;
    }

    public DtValidacionesServiciosEmpresa getDtVSESelect() {
        return dtVSESelect;
    }

    public void setDtVSESelect(DtValidacionesServiciosEmpresa dtVSESelect) {
        this.dtVSESelect = dtVSESelect;
    }

    public List<DtConfiguracionValidaciones> getListaAuxValidaciones() {
        return listaAuxValidaciones;
    }

    public void setListaAuxValidaciones(List<DtConfiguracionValidaciones> listaAuxValidaciones) {
        this.listaAuxValidaciones = listaAuxValidaciones;
    }

    public List<DtConfiguracionValidaciones> getFiltroAuxValidaciones() {
        return filtroAuxValidaciones;
    }

    public void setFiltroAuxValidaciones(List<DtConfiguracionValidaciones> filtroAuxValidaciones) {
        this.filtroAuxValidaciones = filtroAuxValidaciones;
    }

    
}
