/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.generico;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jbecerril
 */
public class WebGenerico {

    public static String contexto() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public static void menajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + mensaje, null));
    }

    public static void mensajeWarning(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + mensaje, null));
    }

    public static void menajeInformativo(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info: " + mensaje, null));

    }

  

}