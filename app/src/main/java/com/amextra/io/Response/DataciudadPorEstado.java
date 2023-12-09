package com.amextra.io.Response;

import java.io.Serializable;

public class DataciudadPorEstado implements Serializable {
    public Ciudade[] getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudade[] ciudades) {
        this.ciudades = ciudades;
    }

    public Ciudade[] ciudades;
}
