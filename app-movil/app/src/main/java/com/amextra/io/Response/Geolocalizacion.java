package com.amextra.io.Response;

import java.io.Serializable;

public class Geolocalizacion implements Serializable {

    String latitude;
    String longitud;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
