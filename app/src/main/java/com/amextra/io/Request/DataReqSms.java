package com.amextra.io.Request;

import java.io.Serializable;

public class DataReqSms implements Serializable {

    public String numeroEnvio;
    public String curp;

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNumeroEnvio() { return numeroEnvio; }
    public void setNumeroEnvio(String value) { this.numeroEnvio = value; }
}
