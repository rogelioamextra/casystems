/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.login.Login;

import com.mx.ca.viu.commons.JwtUtil;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatProductos;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.dtos.generico.InfoUsuarioDTO;
import com.mx.ca.viu.modelos.dtos.generico.infoProducto;
import com.mx.ca.viu.modelos.dtos.request.ActualizaPassRequest;
import com.mx.ca.viu.modelos.dtos.request.ExistePassRequest;
import com.mx.ca.viu.modelos.dtos.request.LoginEntrarRequest;
import com.mx.ca.viu.modelos.dtos.response.ActualizaPassResponse;
import com.mx.ca.viu.modelos.dtos.response.ExistePassResponse;
import com.mx.ca.viu.modelos.dtos.response.LoginEntrarResponse;
import com.mx.ca.viu.services.CatProductosService;
import com.mx.ca.viu.services.CatUsuariosService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jbecerril
 */
@RestController
public class ControllerServicioLogin {
    
    @Autowired
    CatUsuariosService catUsuariosService;
    
    @Autowired
    CatProductosService catProductosService;
    
    private List<CatUsuarios> empresa;
    
    private InfoUsuarioDTO usuario;
    
    private infoProducto producto;    
    
    private List<CatProductos> listaProductos;        
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping(path = "/login/entrar", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public LoginEntrarResponse login(@RequestBody LoginEntrarRequest request) {
        LoginEntrarResponse response = new LoginEntrarResponse();
        usuario = new InfoUsuarioDTO();
        List<infoProducto> aux = new ArrayList<>();
        try {
            //OBTIENE LA EMPRESA
            empresa = catUsuariosService.buscarUsuario(request.getData().getUsuario(), UtilGenerico.Encriptar(request.getData().getPassword()));
            if (empresa.isEmpty()) {
                response.getResponse().setCodigo(400);
                response.getResponse().setMensaje("Usuario y/o contraseña no coinciden");
                response.setData(null);
            } else if (empresa.get(0).getStatus() == false) {
                response.getResponse().setCodigo(100);
                response.getResponse().setMensaje("Usuario inactivo");
                response.setData(null);
            } else {
                String token = jwtUtil.generateToken(empresa.get(0).getIdUsuario().toString());
                usuario.setToken(token);
                usuario.setUsuarioId(empresa.get(0).getIdUsuario().toString());
                if (empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas() != null) {
                    usuario.setIdempresa(empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getIdEmpresas());
                }
                if (empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getLogo() != null) {
                    usuario.setLogoEmpresa(empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getLogo());
                }
                if (empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getColor() != null) {
                    usuario.setColor(empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getColor());
                }
                if (empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getNombre() != null) {
                    usuario.setNombreEmpresa(empresa.get(0).getIdConfiguracionEmpresa().getIdEmpresa().getNombre());
                }
                
                usuario.setNombreUsuario(empresa.get(0).getIdPersona().getNombres() + " " + empresa.get(0).getIdPersona().getApellidoPaterno() + " " + empresa.get(0).getIdPersona().getApellidoMaterno());
                
                usuario.setUsername(empresa.get(0).getUsername());
                
                usuario.setEmail(empresa.get(0).getIdPersona().getEmail());
                
                response.getData().setInfoUSer(usuario);
                //response.getData().setInfoProductos(aux);
                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");
            }
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.toString());
            response.setData(null);
        }
        return response;
    }
    
    @PostMapping(path = "/login/existecorreo", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ExistePassResponse existeCorreo (@RequestBody ExistePassRequest request) {
        ExistePassResponse response = new ExistePassResponse();
        
        CatUsuarios catUsuario = catUsuariosService.searchByEmail(request.getData().getEmail());
        
        if (Objects.isNull(catUsuario)) {
            response.getResponse().setCodigo(404);
            response.getResponse().setMensaje("Correo no encontrado");
            response.setData(null);
        }
        
        else {
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");            
            response.getData().setIdUsuario(String.valueOf(catUsuario.getIdUsuario()));
            response.getData().setEmail(catUsuario.getIdPersona().getEmail());
            response.getData().setNombreUsuario(catUsuario.getIdPersona().getNombres());
            response.getData().setToken(jwtUtil.generateToken(catUsuario.getIdUsuario().toString()));
        }
        
        return response;
    }
    
    @PostMapping(path = "/login/actualizapass", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ActualizaPassResponse actualizaPass (@RequestBody ActualizaPassRequest request) {
        ActualizaPassResponse response = new ActualizaPassResponse();
        
        Long idUsuario = Long.valueOf(request.getData().getIdUsuario().trim());
        String email = request.getData().getEmail().trim();
        String newPass = request.getData().getNewPass();
        String newPassEncriptado = UtilGenerico.Encriptar(newPass);
        
        CatUsuarios catUsuario = catUsuariosService.findById(idUsuario);
        
        if (Objects.equals(catUsuario.getIdUsuario(), idUsuario) && Objects.equals(catUsuario.getIdPersona().getEmail(), email)) {
            catUsuariosService.actualizarContrasena(catUsuario.getUsername(), newPassEncriptado);
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("Contraseña actualizada correctamente, inicia sesión con tu nueva contraseña");
            response.getData().setEmail(email);
            response.getData().setNombreUsuario(catUsuario.getIdPersona().getNombres());
        }
        
        else {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje("Ocurrió un error, por favor vuelva a intentarlo");
            response.setData(null);
        }
        
       return response;
    }
    
    
    
    public InfoUsuarioDTO getUsuario() {
        return usuario;
    }
    
    public void setUsuario(InfoUsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    public CatUsuariosService getCatUsuariosService() {
        return catUsuariosService;
    }
    
    public void setCatUsuariosService(CatUsuariosService catUsuariosService) {
        this.catUsuariosService = catUsuariosService;
    }
    
    public List<CatProductos> getListaProductos() {
        return listaProductos;
    }
    
    public void setListaProductos(List<CatProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    public CatProductosService getCatProductosService() {
        return catProductosService;
    }
    
    public void setCatProductosService(CatProductosService catProductosService) {
        this.catProductosService = catProductosService;
    }
    
    public infoProducto getProducto() {
        return producto;
    }
    
    public void setProducto(infoProducto producto) {
        this.producto = producto;
    }
    
}
