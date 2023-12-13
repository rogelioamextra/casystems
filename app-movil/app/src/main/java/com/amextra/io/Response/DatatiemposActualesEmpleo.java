package com.amextra.io.Response;

import java.io.Serializable;

public class DatatiemposActualesEmpleo implements Serializable {

    public TieposActuale[] getTieposActuales() {
        return tieposActuales;
    }

    public void setTieposActuales(TieposActuale[] tieposActuales) {
        this.tieposActuales = tieposActuales;
    }

    public TieposActuale[] tieposActuales;
}
