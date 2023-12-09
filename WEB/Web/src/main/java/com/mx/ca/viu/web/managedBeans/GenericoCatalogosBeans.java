/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.web.managedBeans;

/**
 *
 * @author jbecerril
 */
public interface GenericoCatalogosBeans {

    public void guardar();

    public void editar();

    public void cambiaPaginaEdicion(Object obj);

    public void cancelar();

    public void cambiaPaginaNuevo();

    public void validaEstatus();

    public void confirmaEstatus(boolean estatus);
}
