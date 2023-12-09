package com.amextra.io.Response;

import java.io.Serializable;

public class DatacoloniaPorMunicipio implements Serializable {

    public Colonia[] getColonias() {
        return colonias;
    }

    public void setColonias(Colonia[] colonias) {
        this.colonias = colonias;
    }

    public Colonia[] colonias;
}
