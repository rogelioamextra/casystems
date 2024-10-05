/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rogel
 */

@Entity
@Table(name ="dt_valida_pin_aval")
public class DtValidacionPinAval implements Serializable{
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pin")
    private Integer idPin;
    
    
    @Column(name = "curp")
    private String curp;
    
        
    @Column(name = "pin")
    private String pin;
    

    @Column(name ="telefono")
    private String telefono;
    
    
    @Column(name = "status_pin")
    private Boolean statusPin;
    
    
    
     @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_validacion")
    private Date fechaValidacion;

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }


 


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Integer getIdPin() {
        return idPin;
    }

    public void setIdPin(Integer idPin) {
        this.idPin = idPin;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Boolean getStatusPin() {
        return statusPin;
    }

    public void setStatusPin(Boolean statusPin) {
        this.statusPin = statusPin;
    }
    
    
    
    
}
