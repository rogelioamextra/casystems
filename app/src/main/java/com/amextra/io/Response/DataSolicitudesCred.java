package com.amextra.io.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class DataSolicitudesCred implements Serializable {

    public ArrayList<SolicitudesCredito> solicitudes;

    public ArrayList<SolicitudesCredito> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(ArrayList<SolicitudesCredito> value) { this.solicitudes = value; }
}
