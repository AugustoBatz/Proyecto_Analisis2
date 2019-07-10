/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sys515
 */
public class ProveedoresController implements ActionListener{

    private VentanaProveedores view;
    public ProveedoresController(VentanaProveedores view)  {
        this.view=view;
        this.view.getBtnagregar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==view.getBtnagregar()){
            
        }
    }
    
}
