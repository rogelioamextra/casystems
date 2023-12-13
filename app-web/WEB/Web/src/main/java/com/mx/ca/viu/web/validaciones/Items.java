/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.validaciones;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class Items {
    
    private String Item;
    private String Label;

    public Items(){}
    
    public Items(String item, String label){
        this.Item = item;
        this.Label = label;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

}
