/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.main;

import gt.url.edu.inventariomaven.Categoria;
import gt.url.edu.inventariomaven.CategoriaJpaController;
import gt.url.edu.inventariomaven.Presentacion;
import gt.url.edu.inventariomaven.Producto;
import gt.url.edu.inventariomaven.Unidad;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import gt.url.edu.almacen.Almacen;
import gt.url.edu.almacen.ModeloProxyJList;
import static gt.url.edu.factorybd.FactoryBaseDeDatos.getInstancia;

/**
 *
 * @author sys515
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MainFrm frm=new MainFrm();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        
    }
    
}
