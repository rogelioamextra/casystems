/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.BtValidacionSms;
import com.mx.ca.viu.modelos.CatClientes;
import java.util.Date;

/**
 *
 * @author jbecerril
 */
public interface ValidacionSmsRepository {
    
    public void desabilitaTodos(String telefono);
    public BtValidacionSms validarCodigo(String telefono, String codigo);
     public void desabilitaTodos5Minutos(Date fecha);
     public CatClientes buscarClienteTelefono(String telefono) ;
     public String obtenPin(String curp);
       public String validarPin(String curp,String nip);
    
}
