/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import gt.url.edu.inventario.InventarioOperacionesBD;

/**
 *
 * @author sys515
 */
public class ComprasFrmController extends InventarioOperacionesBD{
    private ComprasFrm view;
    public ComprasFrmController(ComprasFrm view){
        this.view=view;
        this.view.getComboProductos().setModel(new ProxyModelProductos(listaProdcutos()));
    }
    
}
