/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.producto;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author sys515
 */
public class ModeloProxyComboBox implements ComboBoxModel {
    DefaultComboBoxModel realSubject =new DefaultComboBoxModel<>(); 
    
    public ModeloProxyComboBox(List ls){
        for (Object objeto: ls) {
            realSubject.addElement(objeto);
            
        }
    }
    @Override
    public void setSelectedItem(Object o) {
        realSubject.setSelectedItem(o);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getSelectedItem() {
        return realSubject.getSelectedItem();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSize() {
        return realSubject.getSize();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getElementAt(int i) {
        return realSubject.getElementAt(i);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        realSubject.addListDataListener(ll);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        realSubject.removeListDataListener(ll);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
