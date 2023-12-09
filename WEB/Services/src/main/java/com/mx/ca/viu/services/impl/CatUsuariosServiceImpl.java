/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.repositorys.CatUsuariosRepository;
import com.mx.ca.viu.services.CatUsuariosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "catUsuariosService")
public class CatUsuariosServiceImpl implements CatUsuariosService {

    @Autowired
    CatUsuariosRepository usuariosRepository;
    @Override
    public List<CatUsuarios> buscarTodos(boolean activos) {
        return usuariosRepository.buscarTodos(activos);
    }
    @Override
    public CatUsuarios login(String usuario, String pass) {
        return usuariosRepository.login(usuario, pass);
    }

    @Override
    public boolean actualizarContrasena(String usuario, String pass) {
        return usuariosRepository.actualizarContrasena(usuario, pass);
    }

    @Override
    public List<CatUsuarios> buscarTodosEmpresa(boolean activos, Long idempresa) {
        return usuariosRepository.buscarTodosEmpresa(activos, idempresa);
    }
    
    @Override
    public List<CatUsuarios> buscarNombre(String nombre, Long id) {
        return usuariosRepository.buscarNombre(nombre, id);
    }
    
    @Override
    public List<CatUsuarios> buscarUsuario(String usuario, String pass) {
        return usuariosRepository.buscarUsuario(usuario, pass);
    }
    
    @Override
    public List<CatUsuarios> consultaUsuariosXEmpresa(boolean activos, Long idempresa) {
        return usuariosRepository.consultaUsuariosXEmpresa(activos, idempresa);
    }
}
