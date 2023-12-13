/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "dt_validacion_pin_cliente")
@NamedQueries({
    @NamedQuery(name = "DtValidacionPinCliente.findAll", query = "SELECT d FROM DtValidacionPinCliente d"),
    @NamedQuery(name = "DtValidacionPinCliente.findByIdPin", query = "SELECT d FROM DtValidacionPinCliente d WHERE d.idPin = :idPin"),
    @NamedQuery(name = "DtValidacionPinCliente.findByCurp", query = "SELECT d FROM DtValidacionPinCliente d WHERE d.curp = :curp"),
    @NamedQuery(name = "DtValidacionPinCliente.findByTelefono", query = "SELECT d FROM DtValidacionPinCliente d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DtValidacionPinCliente.findByPin", query = "SELECT d FROM DtValidacionPinCliente d WHERE d.pin = :pin"),
    @NamedQuery(name = "DtValidacionPinCliente.findByStatusPin", query = "SELECT d FROM DtValidacionPinCliente d WHERE d.statusPin = :statusPin")})
public class DtValidacionPinCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pin")
    private Integer idPin;
    @Column(name = "curp")
    private String curp;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "pin")
    private String pin;
    @Column(name = "status_pin")
    private Boolean statusPin;

    public DtValidacionPinCliente() {
    }

    public DtValidacionPinCliente(Integer idPin) {
        this.idPin = idPin;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPin != null ? idPin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtValidacionPinCliente)) {
            return false;
        }
        DtValidacionPinCliente other = (DtValidacionPinCliente) object;
        if ((this.idPin == null && other.idPin != null) || (this.idPin != null && !this.idPin.equals(other.idPin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.viu.modelos.DtValidacionPinCliente[ idPin=" + idPin + " ]";
    }
    
}
