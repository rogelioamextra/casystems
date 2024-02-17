/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatUsuarios;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface CatUsuariosRepository {
    public CatUsuarios findById(Long id);
    public List<CatUsuarios> buscarTodos(boolean activos);
    public CatUsuarios login(String usu, String pass);
    public boolean actualizarContrasena(String usu, String pass);
    public List<CatUsuarios> buscarTodosEmpresa(boolean activos,Long idempresa);
    public List<CatUsuarios> buscarNombre(String nombre, Long id);
    public List<CatUsuarios> buscarUsuario(String usuario, String pass);
    public List<CatUsuarios> consultaUsuariosXEmpresa(boolean activos,Long idempresa);
    public CatUsuarios searchByEmail(String email);
}
