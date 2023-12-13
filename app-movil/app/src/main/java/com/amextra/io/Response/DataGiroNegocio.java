package com.amextra.io.Response;

import java.io.Serializable;

public class DataGiroNegocio implements Serializable {

    public GirosNegocio[] getGirosNegocio() {
        return girosNegocio;
    }

    public void setGirosNegocio(GirosNegocio[] girosNegocio) {
        this.girosNegocio = girosNegocio;
    }

    public GirosNegocio[] girosNegocio;
}
