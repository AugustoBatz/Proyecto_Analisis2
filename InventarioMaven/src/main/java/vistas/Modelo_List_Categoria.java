/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.mycompany.inventariomaven.Categoria;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author sys515
 */
public class Modelo_List_Categoria<Categoria> implements ListModel {
    
    
    List<Categoria> obj; 
    public Modelo_List_Categoria(List<Categoria> ls_categorias){
        this.obj=ls_categorias;
    }
    @Override
    public int getSize() {
        return obj.size();
    }

    @Override
    public Object getElementAt(int i) {
        return obj.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
       
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
