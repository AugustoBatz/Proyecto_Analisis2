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
    private List<String> nombresDeLasColumnas = new ArrayList<String>();

    public InventarioFrmController(InventarioFrm view) {
        this.view = view;
        nombresDeLasColumnas.add("Codigo");
        nombresDeLasColumnas.add("Nombre");
        nombresDeLasColumnas.add("Marca");
        nombresDeLasColumnas.add("Presentacion");
        nombresDeLasColumnas.add("Unidad");
        nombresDeLasColumnas.add("Categoria");
        ProxyTableModel model=new ProxyTableModel(nombresDeLasColumnas,listaProdcutos());
        this.view.getTablaInventario().setModel(model);
    }
    
}
