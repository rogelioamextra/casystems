package com.amextra.io.Response;

import java.io.Serializable;

public class DataLogIn implements Serializable {
    public InfoUSer infoUSer;
    public Producto[] productos;

    public InfoUSer getInfoUSer() {
        return infoUSer;
    }

    public void setInfoUSer(InfoUSer infoUSer) {
        this.infoUSer = infoUSer;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
}
