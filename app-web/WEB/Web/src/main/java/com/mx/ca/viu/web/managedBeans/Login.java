/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.managedBeans;

import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.commons.ViuProperties;
import com.mx.ca.viu.modelos.CatRoles;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.MenusRoles;

import com.mx.ca.viu.web.generico.UtilServicios;
import com.mx.ca.viu.web.generico.WebGenerico;
import com.mx.ca.viu.web.security.RoleSecurity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.primefaces.PrimeFaces;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jbecerril
 */
@ManagedBean(name = "login")
@javax.faces.bean.ViewScoped
public class Login extends UtilServicios {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Login.class.getName());

    private String usuario;

    private String password;
    private String password1;
    private String password2;

    public static final String COMPILACION;

    /**
     * Creates a new instance of Login
     */
    public Login() {

    }

    static {

        COMPILACION = ViuProperties.AMBIENTE + "-" + ViuProperties.COMPILACION;

    }

    public void cierreSesion() {
        PrimeFaces.current().executeScript("PF('WV_SinSesion').show();");
    }

    public void consultaNuevo() {
        try {
            if (getAdministradorPaginas().getUsuarioLogueado().getPrimerInicio()) {
                PrimeFaces.current().executeScript("PF('dlgContrasena').show();");
            }
        } catch (Exception ex) {
            logger.fatal("Error : ", ex);
        }
    }

    public String compilacion() {
        return COMPILACION;
    }

    public void actualizaContrasena() {
        if (UtilGenerico.validaContrasena(password1) == true) {

            //  boolean usu = catUsuariosService.actualizarContrasena(this.usuario, UtilGenerico.Encriptar(this.password1));
            CatUsuarios usuario = getAdministradorPaginas().getUsuarioLogueado();
            usuario.setPassword(UtilGenerico.Encriptar(this.password1));
            usuario.setPrimerInicio(false);
            if (genericoService.update(usuario)) {
                salir();
                //FacesContext.getCurrentInstance().getExternalContext().redirect(WebGenerico.contexto() + "/login.viu?faces-redirect=true");
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.generico.mensaje.error"));
            }
        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.contrasena.nueva.incorrecto"));
        }
    }

    public void entrar() {
        try {

            if (!usuario.isEmpty() && !password.isEmpty()) {
                
                CatUsuarios usuarioBase = catUsuariosService.login(this.usuario, UtilGenerico.Encriptar(this.password));
                if (usuarioBase != null) {
                    if(usuarioBase.getStatus()){
                        if(usuarioBase.getIdRol().getStatus()){
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, password, obtenerGrantedAuthorityDeFuncionalidad(usuarioBase.getIdRol()));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            FacesContext.getCurrentInstance().getExternalContext().redirect(WebGenerico.contexto() + "/auth/contenido.viu");
                            getAdministradorPaginas().setUsuarioLogueado(usuarioBase);
                            getAdministradorPaginas().asignarEmpresa();
                            getAdministradorPaginas().generaMenu();
                            getAdministradorPaginas().setPagina("tablero/bandejaTablero.xhtml");
                        }else{ 
                            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.login.perfil.inactivo"));                            
                        }
                    }else{
                        WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.login.inactivo"));
                    }
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("viu.login.incorrecto"));
                }
            }

        } catch (IOException ex) {
            logger.fatal("Error : ", ex);

        }
    }

    public void salir() {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(false);

            SecurityContextHolder.clearContext();
            session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            for (Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
            }
            //embarquesServices.limpiaSesion();
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/login.viu?faces-redirect=true");
            getAdministradorPaginas().setUsuarioLogueado(null);
            getAdministradorPaginas().setPagina(null);
            getAdministradorPaginas().setEmpresaUsuarioSesion(null);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("getgetAdministradorPaginas()()");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        } catch (IOException ex) {
            logger.fatal("Error : ", ex);
        }
    }
    
    public List<GrantedAuthority> obtenerGrantedAuthorityDeFuncionalidad(CatRoles roles) {
        final List<GrantedAuthority> permisos = new ArrayList<GrantedAuthority>();
        RoleSecurity rolSecurity = null;
        for (MenusRoles mr : roles.getMenusRolesList()) {
            rolSecurity = new RoleSecurity();
            rolSecurity.setNombre(mr.getIdMenu().getSeguridad());
            permisos.add(rolSecurity);
        }
        return permisos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}
