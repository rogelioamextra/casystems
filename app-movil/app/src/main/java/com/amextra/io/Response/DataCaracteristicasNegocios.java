package com.amextra.io.Response;

import java.io.Serializable;

public class DataCaracteristicasNegocios implements Serializable {
    public CaracteristicasNegocio[] getCaracteristicasNegocio() {
        return caracteristicasNegocio;
    }

    public void setCaracteristicasNegocio(CaracteristicasNegocio[] caracteristicasNegocio) {
        this.caracteristicasNegocio = caracteristicasNegocio;
    }

    public CaracteristicasNegocio[] caracteristicasNegocio;
}
