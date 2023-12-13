package com.amextra.io.Request;

import java.io.Serializable;

public class DataResValidaSMS implements Serializable {
    public String numeroEnvio;
    public String codigo;
    public String nip;
    public String curp;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNumeroEnvio() { return numeroEnvio; }
    public void setNumeroEnvio(String value) { this.numeroEnvio = value; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String value) { this.codigo = value; }

}
