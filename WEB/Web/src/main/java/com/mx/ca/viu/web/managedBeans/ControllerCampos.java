/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatModulo;
import com.mx.ca.viu.modelos.CatTipoCampo;
import com.mx.ca.viu.modelos.CatTipoDato;
import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import com.mx.ca.viu.web.validaciones.Items;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@ManagedBean(name = "controllerCampos")
@javax.faces.bean.ViewScoped
public class ControllerCampos extends UtilServicios implements Serializable {

    private CatCampos campoNuevo;
    private boolean banderaEdicion;
    private List<CatCampos> listaCampos;
    private List<CatCampos> listaDetalle;
    private List<CatCampos> filtroDetalle;
    private List<CatCampos> filtroCampos;
    private CatEmpresas empresaUsuarioSesion;
    private CatEmpresas empresaSelect;
    private List<CatEmpresas> listaEmpresas;
    private List<CatModulo> listaModulos;
    private List<CatCategoriasCampos> listaCategorias;
    private CatCategoriasCampos categoriaSelect;
    private List<CatTipoCampo> listaTipoCampos;
    private List<CatTipoDato> listaTipoDato;
    private CatModulo moduloSelect;
    private CatTipoCampo tipoCampoSelect;
    private CatTipoDato tipoDatoSelect;
    private boolean verBotonDetalle;
    private boolean banderaDisable;
    private String radioB;
    private String longitudMax;
    private List<Items> listaItems;
    private List<Items> listaItemsAux;
    private Items item;
    private List<Items> selectedGridItem;
    private JSONObject json;
    private String jsonB;
    private HashMap<String, String> myMap;

    public ControllerCampos() {
    }

    @PostConstruct
    public void init() {
        campoNuevo = new CatCampos();
        verBotonDetalle = false;
        //myMap = new HashMap<Items,Integer>();

        if (getAdministradorPaginas() != null) {
            if (getAdministradorPaginas().getEmpresaUsuarioSesion() != null) {
                empresaUsuarioSesion = getAdministradorPaginas().getEmpresaUsuarioSesion();
//                if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//                    buscarTodos(false);
                buscarTodosPorEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                    listaEmpresas = llenarCombo(listaEmpresas);
                    listaEmpresas = new ArrayList<>();
                    listaEmpresas.add(empresaUsuarioSesion);
//                    if (!listaEmpresas.isEmpty()) {
                        empresaSelect = listaEmpresas.get(0);
//                    }
//                } else {
//                    empresaSelect = empresaUsuarioSesion;
//                    buscarTodosPorEmpresa(false, empresaUsuarioSesion.getIdEmpresas());
//                }
            }
        }
    }

    public void verBotonDetalleCambio() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            verBotonDetalle = false;
        } else {
            verBotonDetalle = true;
        }
    }

    public void tipoCampoCaptura() {
//        if(radioB != null) 
//        {
//            if(radioB.equals("corto")){
//                verBotonDetalle = false;
//            }else{
//                verBotonDetalle = true;
//            }
//        }
    }

    public void cambiaPaginaNuevo() {
        banderaEdicion = false;
        radioB = "corto";
        longitudMax = "10";
        listaItems = null;
        getAdministradorPaginas().setPagina("catalogos/campos/campos.xhtml");
        campoNuevo = new CatCampos();
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU) && empresaSelect.getIdEmpresas() != null) {
//            listaCategorias = catCamposService.buscarCategoriasMasViu(true, empresaSelect);
//        } else {
            listaCategorias = catCamposService.buscarCategoriasMasViu(true, empresaUsuarioSesion);
//        }
        //CONSULTA TIPO DE CAMPO
        listaTipoCampos = catCamposService.buscarTipoCampos(true);
        //tipoCampoSelect = listaTipoCampos.get(0);
        //CONSULTA TIPO DE DATO
        listaTipoDato = catCamposService.buscarTipoDato(true);
        //tipoDatoSelect = listaTipoDato.get(0);
        //MUESTRA BOTON DE DETALLE
        verBotonDetalleCambio();
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
//        }
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaUsuarioSesion);
    }

    public void cambiaPaginaEdicion(CatCampos valor) throws JsonProcessingException {
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            listaCategorias = catCamposService.buscarCategoriasMasViu(true, valor.getIdEmpresa());
//        } else {
            listaCategorias = catCamposService.buscarCategoriasMasViu(true, valor.getIdEmpresa());
//        }
        //CONSULTA TIPO DE CAMPO
        listaTipoCampos = catCamposService.buscarTipoCampos(true);
        //CONSULTA TIPO DE DATO
        listaTipoDato = catCamposService.buscarTipoDato(true);
        banderaEdicion = true;
        getAdministradorPaginas().setPagina("catalogos/campos/campos.xhtml");
        this.campoNuevo = valor;
        this.categoriaSelect = valor.getIdCategoria();
        this.tipoCampoSelect = valor.getIdTipoCampo();
        tipoDatoSelect = new CatTipoDato();
        empresaSelect = valor.getIdEmpresa();
        verBotonDetalle = true;
        listaItems = null;
        radioB = null;
        listaItemsAux = null;
        item = null;

        HashMap<String, Object> cabeceras = new HashMap<>();
        if (valor.getJson() != null) {
            cabeceras = (HashMap<String, Object>) valor.getJson();
            if (!cabeceras.isEmpty()) {
                for (Map.Entry<String, Object> aux : cabeceras.entrySet()) {
                    if (!aux.getKey().equals("nombreCampo")) {
                        if (aux.getKey().equals("longitudCampo")) {
                            radioB = (String) aux.getValue();
                        } else if (aux.getKey().equals("tipoDato")) {
                            for (CatTipoDato lista : listaTipoDato) {
                                if (getAdministradorPaginas().getBUNDLE().getString(lista.getNombre()).equals((String) aux.getValue())) {
                                    tipoDatoSelect = lista;
                                }
                            }
                        } else if (aux.getKey().equals("longitudMaxima")) {
                            longitudMax = (String) aux.getValue();
                        } else {
                            Items n = new Items();
                            if (this.listaItems == null) {
                                this.listaItems = new ArrayList<>();
                                listaItemsAux = (List<Items>) aux.getValue();
                                if (listaItemsAux != null) {
                                    for (int x = 0; x < listaItemsAux.size(); x++) {
                                        HashMap<String, Object> myMapAux = new HashMap<>();
                                        myMapAux.put("objeto", listaItemsAux.get(x));
                                        for (Map.Entry<String, Object> auxiliar : myMapAux.entrySet()) {
                                            Items s = new Items();
                                            HashMap<String, Object> itemx = new HashMap<>();
                                            itemx = (HashMap<String, Object>) auxiliar.getValue();
                                            for (Map.Entry<String, Object> auxiliarx : itemx.entrySet()) {
                                                System.out.println(auxiliarx);
                                                if (auxiliarx.getKey().equals("item")) {
                                                    s.setItem((String) auxiliarx.getValue());
                                                } else {
                                                    s.setLabel((String) auxiliarx.getValue());
                                                }
                                            }
                                            listaItems.add(s);
                                            break;
                                        }

                                        //n.setItem((String) listaItemsAux.get(x).getItem());
                                        //n.setLabel((String) listaItemsAux.get(x).getLabel());
                                        //listaItems.add(n);
                                    }
                                }
                            } else {
                                n.setItem(aux.getKey());
                                n.setLabel((String) aux.getValue());
                                listaItems.add(n);
                            }
                        }
                    }
                }
            }
        }

//        String n = valor.getJson().toString();
//        HashMap<String,String> jsonMap = new ObjectMapper().readValue(n, HashMap.class);
//        if (Objects.equals(empresaUsuarioSesion.getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
//            listaEmpresas = catEmpresasService.buscarTodos(true);
//        }
        listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresaSelect);
    }

    public void buscarTodos(boolean activos) {
        listaCampos = catCamposService.buscarTodos(activos);
    }

    public void buscarTodosPorEmpresa(boolean activos, Long IdEmpresa) {
        listaCampos = catCamposService.buscarTodosXEmpresa(activos, IdEmpresa);
    }

    public void buscarTodosPorEmpresaFiltro() {
        if (empresaSelect.getNombre().equals(getAdministradorPaginas().getBUNDLE().getString("viu.generico.buscarTodos"))) {
            listaCampos = catCamposService.buscarTodos(false);
        } else {
            listaCampos = catCamposService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        }
    }

    public void buscarCategoriasPorEmpresaFiltro() {
        if (empresaSelect != null) {
            listaCategorias = catCamposService.buscarCategoriasMasViu(true, empresaSelect);
        }
    }

    public void validaEstatus() {
        if (campoNuevo.getStatus() == false) {
            PrimeFaces.current().executeScript("PF('dlgEstatus').show();");
        }
    }

    public void convertListToJson() throws JsonProcessingException {
        //myMap = new HashMap<>();
        HashMap<String, Object> myMapAux = new HashMap<>();
        myMapAux.put("nombreCampo", campoNuevo.getNombre());
        myMapAux.put("DataInformacionDetalle", listaItems);
        /*
        
        listaItems.forEach((aux) -> {
            configuracionCampo = new HashMap<>();
            configuracionCampo.put("valor", aux.getItem());
            configuracionCampo.put("campo", aux.getLabel());
            myMapAux.putAll(configuracionCampo);
        });*/
        jsonB = new ObjectMapper().writeValueAsString(myMapAux);

    }

    public void convertLongCampoJson() throws JsonProcessingException {
        myMap = new HashMap<>();
        myMap.put("nombreCampo", campoNuevo.getNombre());
        if (tipoCampoSelect.getIdTipo() == 1) {
            myMap.put("longitudCampo", radioB);
            myMap.put("longitudMaxima", longitudMax);
            myMap.put("tipoDato", getAdministradorPaginas().getBUNDLE().getString(tipoDatoSelect.getNombre()));
        }
        jsonB = new ObjectMapper().writeValueAsString(myMap);
    }

    public void guardar() throws SQLException, IOException {
        filtroCampos = null;
        if (catCamposService.buscarNombre(campoNuevo.getNombre(), campoNuevo.getIdCampos(), empresaSelect.getIdEmpresas()).isEmpty()) {
            if (banderaEdicion) {
                editar();
            } else {
                if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
                    campoNuevo.setIdEmpresa(empresaSelect);
                } else {
                    campoNuevo.setIdEmpresa(empresaUsuarioSesion);
                }
                if (categoriaSelect != null) {
                    campoNuevo.setIdCategoria(categoriaSelect);
                }
                if (tipoCampoSelect != null) {
                    if (tipoCampoSelect.getIdTipo() == 1 || tipoCampoSelect.getIdTipo() == 5) {
                        campoNuevo.setIdTipoCampo(tipoCampoSelect);
                        convertLongCampoJson();
                        campoNuevo.setJson(jsonB);

                    } else {
                        //guardar la lista como json
                        campoNuevo.setIdTipoCampo(tipoCampoSelect);
                        convertListToJson();
                        campoNuevo.setJson(jsonB);
                    }

                }
                if (genericoService.guardar(campoNuevo)) {
                    WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.guardar"));
                    init();
                    getAdministradorPaginas().setPagina("catalogos/campos/bandejaCampos.xhtml");
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
                }
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.duplicado"));
        }
    }

    public void editar() throws JsonProcessingException {
//        if (Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas())) {
//            campoNuevo.setIdEmpresa(empresaSelect);
//        } else {
            campoNuevo.setIdEmpresa(empresaUsuarioSesion);
//        }
        if (categoriaSelect != null) {
            campoNuevo.setIdCategoria(categoriaSelect);
        }
        if (tipoCampoSelect != null) {
            if (tipoCampoSelect.getIdTipo() == 1 || tipoCampoSelect.getIdTipo() == 5) {
                campoNuevo.setIdTipoCampo(tipoCampoSelect);
                convertLongCampoJson();
                campoNuevo.setJson(jsonB);
            } else {
                //guardar la lista como json
                campoNuevo.setIdTipoCampo(tipoCampoSelect);
                convertListToJson();
                campoNuevo.setJson(jsonB);
            }

            //campoNuevo.setIdTipoCampo(tipoCampoSelect);        
        }
        if (genericoService.update(campoNuevo)) {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.exito.modificar"));
            init();
            getAdministradorPaginas().setPagina("catalogos/campos/bandejaCampos.xhtml");
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
        }
    }

    public void cancelar() {
        filtroCampos = null;
        getAdministradorPaginas().setPagina("catalogos/campos/bandejaCampos.xhtml");
        init();
    }

    public void verDetalle() {
        listaDetalle = catCamposService.buscarTodosXEmpresa(false, empresaSelect.getIdEmpresas());
        if (listaDetalle.isEmpty()) {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.campos.vacio"));
        } else {
            PrimeFaces.current().executeScript("PF('dlgDetalle').show();");
        }
    }

    public boolean disable(CatCampos valor) {
        banderaDisable = false;
        if (Objects.equals(valor.getIdEmpresa().getIdEmpresas(), Constantes.ID_EMPRESA_VIU)) {
            banderaDisable = !Objects.equals(Constantes.ID_EMPRESA_VIU, empresaUsuarioSesion.getIdEmpresas());
        }
        return banderaDisable;
    }

    public void agregaRegistro() {
        Items n = new Items();
        if (this.listaItems == null) {
            this.listaItems = new ArrayList<>();
            listaItems.add(n);
        } else {
            listaItems.add(n);
        }
    }

    public void eliminaRegistro() {
        if (selectedGridItem != null) {
            listaItems.removeAll(selectedGridItem);
//            if(listaConfigRiesgo!=null && filaSelected!=null){
//                for(MvConfigRiesgo nivel : filaSelected){
//                    mvConfigRiesgoService.eliminarRegistro(nivel.getIdConfigNivelRiesgo());
//                }
//            }
        } else {
            WebGenerico.menajeInformativo(getAdministradorPaginas().getBUNDLE().getString("viu.generico.seleccionar"));
        }
        selectedGridItem = null;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formContenido:filas");
        dataTable.resetValue();
    }

    public CatCampos getCampoNuevo() {
        return campoNuevo;
    }

    public void setCampoNuevo(CatCampos campoNuevo) {
        this.campoNuevo = campoNuevo;
    }

    public boolean isBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public List<CatCampos> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<CatCampos> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public CatEmpresas getEmpresaUsuarioSesion() {
        return empresaUsuarioSesion;
    }

    public void setEmpresaUsuarioSesion(CatEmpresas empresaUsuarioSesion) {
        this.empresaUsuarioSesion = empresaUsuarioSesion;
    }

    public CatEmpresas getEmpresaSelect() {
        return empresaSelect;
    }

    public void setEmpresaSelect(CatEmpresas empresaSelect) {
        this.empresaSelect = empresaSelect;
    }

    public List<CatEmpresas> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<CatEmpresas> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<CatCampos> getFiltroCampos() {
        return filtroCampos;
    }

    public void setFiltroCampos(List<CatCampos> filtroCampos) {
        this.filtroCampos = filtroCampos;
    }

    public List<CatModulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<CatModulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public List<CatTipoCampo> getListaTipoCampos() {
        return listaTipoCampos;
    }

    public void setListaTipoCampos(List<CatTipoCampo> listaTipoCampos) {
        this.listaTipoCampos = listaTipoCampos;
    }

    public CatModulo getModuloSelect() {
        return moduloSelect;
    }

    public void setModuloSelect(CatModulo moduloSelect) {
        this.moduloSelect = moduloSelect;
    }

    public CatTipoCampo getTipoCampoSelect() {
        return tipoCampoSelect;
    }

    public void setTipoCampoSelect(CatTipoCampo tipoCampoSelect) {
        this.tipoCampoSelect = tipoCampoSelect;
    }

    public List<CatCampos> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<CatCampos> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public List<CatCampos> getFiltroDetalle() {
        return filtroDetalle;
    }

    public void setFiltroDetalle(List<CatCampos> filtroDetalle) {
        this.filtroDetalle = filtroDetalle;
    }

    public boolean isVerBotonDetalle() {
        return verBotonDetalle;
    }

    public void setVerBotonDetalle(boolean verBotonDetalle) {
        this.verBotonDetalle = verBotonDetalle;
    }

    public boolean isBanderaDisable() {
        return banderaDisable;
    }

    public void setBanderaDisable(boolean banderaDisable) {
        this.banderaDisable = banderaDisable;
    }

    public List<CatCategoriasCampos> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CatCategoriasCampos> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public CatCategoriasCampos getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(CatCategoriasCampos categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public String getRadioB() {
        return radioB;
    }

    public void setRadioB(String radioB) {
        this.radioB = radioB;
    }

    public List<Items> getListaItems() {
        return listaItems;
    }

    public void setListaItems(List<Items> listaItems) {
        this.listaItems = listaItems;
    }

    public List<Items> getSelectedGridItem() {
        return selectedGridItem;
    }

    public void setSelectedGridItem(List<Items> selectedGridItem) {
        this.selectedGridItem = selectedGridItem;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public String getJsonB() {
        return jsonB;
    }

    public void setJsonB(String jsonB) {
        this.jsonB = jsonB;
    }

    public HashMap<String, String> getMyMap() {
        return myMap;
    }

    public void setMyMap(HashMap<String, String> myMap) {
        this.myMap = myMap;
    }

    public List<CatTipoDato> getListaTipoDato() {
        return listaTipoDato;
    }

    public void setListaTipoDato(List<CatTipoDato> listaTipoDato) {
        this.listaTipoDato = listaTipoDato;
    }

    public CatTipoDato getTipoDatoSelect() {
        return tipoDatoSelect;
    }

    public void setTipoDatoSelect(CatTipoDato tipoDatoSelect) {
        this.tipoDatoSelect = tipoDatoSelect;
    }

    public String getLongitudMax() {
        return longitudMax;
    }

    public void setLongitudMax(String longitudMax) {
        this.longitudMax = longitudMax;
    }

}
