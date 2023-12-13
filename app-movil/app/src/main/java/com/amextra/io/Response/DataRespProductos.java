package com.amextra.io.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class DataRespProductos implements Serializable {

    public ArrayList<Producto> productos;

    public ArrayList<Producto> getProductos() { return productos; }
    public void setProductos(ArrayList<Producto> value) { this.productos = value; }
}
