/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sys515
 */
public class InventarioFrmController extends InventarioOperacionesBD {

    private InventarioFrm view;
   

    public InventarioFrmController(InventarioFrm view) {
        this.view = view;
         RenderCellRojo renderer=new RenderCellRojo(0);
        this.view.getTablaInventario().setDefaultRenderer(Object.class, renderer);
        this.view.getTablaInventario().setModel(new ProxyTableModel(nombreDeColumnas(),listaProdcutos()));
        
    }
    
}
