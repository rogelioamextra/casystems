package com.amextra.io.Response;

import java.io.Serializable;

public class DataRespAddSolicitudCredito implements Serializable {

    public String solicitudId;

    public String getSolicitudID() { return solicitudId; }
    public void setSolicitudID(String value) { this.solicitudId = value; }
}
