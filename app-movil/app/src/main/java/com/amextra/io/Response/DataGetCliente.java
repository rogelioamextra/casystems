package com.amextra.io.Response;

import java.io.Serializable;

public class DataGetCliente implements Serializable {
    public Cliente cliente;

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente value) { this.cliente = value; }
}
