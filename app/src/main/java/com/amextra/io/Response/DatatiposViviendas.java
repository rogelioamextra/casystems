package com.amextra.io.Response;

import java.io.Serializable;

public class DatatiposViviendas implements Serializable {
    public TiposVivienda[] getTiposViviendas() {
        return tiposViviendas;
    }

    public void setTiposViviendas(TiposVivienda[] tiposViviendas) {
        this.tiposViviendas = tiposViviendas;
    }

    public TiposVivienda[] tiposViviendas;
}
