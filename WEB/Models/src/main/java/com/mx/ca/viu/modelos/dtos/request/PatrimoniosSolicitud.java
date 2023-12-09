/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

/**
 *
 * @author jbecerril
 */
public class PatrimoniosSolicitud {
    
    private String tipoPatrimonioId;
    private String precio;
    private String imagen;
    private boolean cambioImagen;

    public PatrimoniosSolicitud() {
    }

  
    public String getTipoPatrimonioId() {
        return tipoPatrimonioId;
    }

    public void setTipoPatrimonioId(String tipoPatrimonioId) {
        this.tipoPatrimonioId = tipoPatrimonioId;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isCambioImagen() {
        return cambioImagen;
    }

    public void setCambioImagen(boolean cambioImagen) {
        this.cambioImagen = cambioImagen;
    }
    
    
    
    
}
