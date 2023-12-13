package com.amextra.io.Request;

import java.io.Serializable;

public class Egresos_ implements Serializable {

    public String alimentos;
    public String renta;
    public String gastosEscolares;
    public String telefono;
    public String luz;
    public String agua;
    public String gas;
    public String transporteGasolina;
    public String vestido;
    public String esparcimiento;
    public String mantenimientoReparaciones;
    public String totalMensual;

    public String getAlimentos() { return alimentos; }
    public void setAlimentos(String value) { this.alimentos = value; }

    public String getRenta() { return renta; }
    public void setRenta(String value) { this.renta = value; }

    public String getGastosEscolares() { return gastosEscolares; }
    public void setGastosEscolares(String value) { this.gastosEscolares = value; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String value) { this.telefono = value; }

    public String getLuz() { return luz; }
    public void setLuz(String value) { this.luz = value; }

    public String getAgua() { return agua; }
    public void setAgua(String value) { this.agua = value; }

    public String getGas() { return gas; }
    public void setGas(String value) { this.gas = value; }

    public String getTransporteGasolina() { return transporteGasolina; }
    public void setTransporteGasolina(String value) { this.transporteGasolina = value; }

    public String getVestido() { return vestido; }
    public void setVestido(String value) { this.vestido = value; }

    public String getEsparcimiento() { return esparcimiento; }
    public void setEsparcimiento(String value) { this.esparcimiento = value; }

    public String getMantenimientoReparaciones() { return mantenimientoReparaciones; }
    public void setMantenimientoReparaciones(String value) { this.mantenimientoReparaciones = value; }

    public String getTotalMensual() { return totalMensual; }
    public void setTotalMensual(String value) { this.totalMensual = value; }
}
