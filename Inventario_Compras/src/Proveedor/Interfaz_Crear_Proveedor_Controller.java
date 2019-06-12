/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sys515
 */
public class Interfaz_Crear_Proveedor_Controller implements ActionListener {

    private Interfaz_Crear_Proveedor_View view;
    private String insertquery;
    private Proveedor proveedor=new Proveedor();
    public Interfaz_Crear_Proveedor_Controller(Interfaz_Crear_Proveedor_View view) {
        this.view = view;
        addListener();

    }
    public void addListener(){
        this.view.getEnviar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==view.getEnviar()){
            insertquery = "insert into Proveedor(NombreV,Representante,Nit,Correo,Direccion,Numero) "
                    + "Values('" + view.getEmpresa().getText() + "','" + view.getRepresentante().getText() + "',"
                    + "'"+view.getNit().getText()+"','"+view.getCorreo().getText()+"','"+view.getDireccion().getText()+"',"
                    + "'"+view.getTelefono().getText()+"')";
            System.out.println(insertquery);
            try {
                proveedor.setQuery(insertquery);
            } catch (SQLException ex) {
                
                
                Logger.getLogger(Interfaz_Crear_Proveedor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
