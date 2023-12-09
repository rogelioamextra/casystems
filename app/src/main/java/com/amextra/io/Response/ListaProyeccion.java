package com.amextra.io.Response;

import java.io.Serializable;

public class ListaProyeccion implements Serializable {
    public String n;
    public String capital;
    public String interes;
    public String iva;
    public String cuota;
    public String saldo;

    public String getN() { return n; }
    public void setN(String value) { this.n = value; }

    public String getCapital() { return capital; }
    public void setCapital(String value) { this.capital = value; }

    public String getInteres() { return interes; }
    public void setInteres(String value) { this.interes = value; }

    public String getIva() { return iva; }
    public void setIva(String value) { this.iva = value; }

    public String getCuota() { return cuota; }
    public void setCuota(String value) { this.cuota = value; }

    public String getSaldo() { return saldo; }
    public void setSaldo(String value) { this.saldo = value; }
}
