package com.amextra.io.Request;

import java.io.Serializable;

public class DataCurp implements Serializable {
    private String curp;


    private boolean posibleCliente = true;

    public String getCurp() {
        return curp;
    }


    public void setCurp(String value) {
        this.curp = value;
    }


    public boolean isPosibleCliente() {
        return posibleCliente;
    }

    public void setPosibleCliente(boolean posibleCliente) {
        this.posibleCliente = posibleCliente;
    }
}
