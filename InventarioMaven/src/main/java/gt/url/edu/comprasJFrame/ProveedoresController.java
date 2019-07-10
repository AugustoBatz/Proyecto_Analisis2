/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import gt.url.edu.factorybd.FactoryBaseDeDatos;
import gt.url.edu.inventariomaven.Proveedor;
import gt.url.edu.inventariomaven.ProveedorJpaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sys515
 */
public class ProveedoresController implements ActionListener{

    private VentanaProveedores view;
    private FactoryBaseDeDatos conexion=FactoryBaseDeDatos.getInstancia();
    private ProveedorJpaController proveedorController;
    public ProveedoresController(VentanaProveedores view)  {
        this.view=view;
        this.view.getBtnagregar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==view.getBtnagregar()){
            proveedorController= new ProveedorJpaController(conexion.getEntityManager());
            proveedorController.create(crearProveedor());
            
        }
    }
    private Proveedor crearProveedor(){
        Proveedor p=new Proveedor();
        p.setRepresentante(view.getRepresentante().getText());
        p.setNombreV(view.getEmpresa().getText());
        p.setNit(view.getNit().getText());
        p.setNumero(Integer.valueOf(view.getTelefono().getText()));
        p.setDireccion(view.getDireccion().getText());
        return p;
    }
    
}
