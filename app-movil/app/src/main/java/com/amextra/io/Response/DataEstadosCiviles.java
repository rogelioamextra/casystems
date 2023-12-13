package com.amextra.io.Response;

import java.io.Serializable;

public class DataEstadosCiviles implements Serializable {

   public GradosEscolare[] estadosCiviles;

    public GradosEscolare[] getEstadosCiviles() {
        return estadosCiviles;
    }

    public void setEstadosCiviles(GradosEscolare[] estadosCiviles) {
        this.estadosCiviles = estadosCiviles;
    }
}
