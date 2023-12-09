package com.amextra.io.Response;

import java.io.Serializable;

public class DatatiposResidencias implements Serializable {

    public TiposResidencia[] getTiposResidencias() {
        return tiposResidencias;
    }

    public void setTiposResidencias(TiposResidencia[] tiposResidencias) {
        this.tiposResidencias = tiposResidencias;
    }

    public TiposResidencia[] tiposResidencias;
}
