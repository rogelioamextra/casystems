package com.amextra.io.Response;

import java.io.Serializable;

public class Dataestados implements Serializable {
    public Estado[] getEstados() {
        return estados;
    }

    public void setEstados(Estado[] estados) {
        this.estados = estados;
    }

    public Estado[] estados;
}
