package com.amextra.io.Response;

import java.io.Serializable;

public class DataDestinoCredito implements Serializable {
    public DestinosCredito[] destinosCredito;

    public DestinosCredito[] getDestinosCredito() { return destinosCredito; }
    public void setDestinosCredito(DestinosCredito[] value) { this.destinosCredito = value; }
}
