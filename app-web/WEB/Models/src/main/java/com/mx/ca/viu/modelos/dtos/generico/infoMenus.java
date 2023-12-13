/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service
public class infoMenus implements Serializable {
    @JsonProperty("idMenu")
    private Long idMenu;
    @JsonProperty("nombreMenu")
    private String nombreMenu;
    
    public infoMenus() {
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

  
    
}
