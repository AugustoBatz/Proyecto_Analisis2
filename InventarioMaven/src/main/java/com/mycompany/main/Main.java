/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.mycompany.inventariomaven.Categoria;
import com.mycompany.inventariomaven.CategoriaJpaController;
import com.mycompany.inventariomaven.Presentacion;
import com.mycompany.inventariomaven.Producto;
import com.mycompany.inventariomaven.Unidad;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.mycompany.almacen.Almacen;
import com.mycompany.almacen.ModeloProxyJList;

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
