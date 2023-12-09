package com.amextra.io.Response;

import java.io.Serializable;

public class DatamunicipiosPorEstado implements Serializable {
    public Municipio[] municipios;

    public Municipio[] getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Municipio[] municipios) {
        this.municipios = municipios;
    }
}
