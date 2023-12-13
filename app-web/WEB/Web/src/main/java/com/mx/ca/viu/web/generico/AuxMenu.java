/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.generico;

import com.mx.ca.viu.modelos.CatMenus;
import java.util.List;

/**
 *
 * @author jbecerril
 */
 public class AuxMenu {

        private CatMenus menuPadre;
        private List<CatMenus> menusHijo; 

        public AuxMenu(CatMenus menuPadre, List<CatMenus> menusHijo) { 
            this.menuPadre = menuPadre;
            this.menusHijo = menusHijo;
        }

        public AuxMenu(CatMenus menuPadre) {
            this.menuPadre = menuPadre;
        }

        public CatMenus getMenuPadre() {
            return menuPadre;
        }

        public void setMenuPadre(CatMenus menuPadre) {
            this.menuPadre = menuPadre;
        }

        public List<CatMenus> getMenusHijo() {
            return menusHijo;
        }

        public void setMenusHijo(List<CatMenus> menusHijo) {
            this.menusHijo = menusHijo;
        }

    }