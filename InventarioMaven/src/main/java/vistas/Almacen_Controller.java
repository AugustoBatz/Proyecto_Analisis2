/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.mycompany.inventariomaven.Categoria;
import com.mycompany.inventariomaven.CategoriaJpaController;
import com.mycompany.inventariomaven.Presentacion;
import com.mycompany.inventariomaven.PresentacionJpaController;
import com.mycompany.inventariomaven.Unidad;
import com.mycompany.inventariomaven.UnidadJpaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import main.Main;

/**
 *
 * @author sys515
 */
public class Almacen_Controller implements ActionListener{
    private Almacen view;
    public Almacen_Controller(Almacen view)
    {
        this.view=view;
        setModels();
        addActionListener();
    }
    public void addActionListener(){
        view.getBnt_cat().addActionListener(this);
        view.getBnt_pres().addActionListener(this);
        view.getBnt_uni().addActionListener(this);
    }
    public void setModels()
    {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("proyecto");        
        EntityManager em = emf.createEntityManager();//
        TypedQuery<Categoria> query_cat = em.createNamedQuery("Categoria.findAll", Categoria.class);
        List<Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        TypedQuery<Presentacion> query_pres = em.createNamedQuery("Presentacion.findAll", Presentacion.class);
        List<Presentacion> listaPresentacion = query_pres.getResultList();//List es una interfaz
        TypedQuery<Unidad> query_unidad = em.createNamedQuery("Unidad.findAll", Unidad.class);
        List<Unidad> listaUnidad = query_unidad.getResultList();//List es una interfaz
        view.getLs_categoria().setModel(new modelo_proxy_jl(listaCategoria));
        view.getLs_presentacion().setModel(new modelo_proxy_jl(listaPresentacion));
        view.getLs_unidad_medida().setModel(new modelo_proxy_jl(listaUnidad));
        //modelo_proxy_jl mc=new modelo_proxy_jl(listaCategoria);
        
        em.close();
        emf.close();
        
        
       
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==view.getBnt_cat()){
            System.out.println("Presionando ");
            if(view.getCn().equals("")){
                System.out.println("Vacio");
            }
            else{
                create(1);
            }
        }
        if(ae.getSource()==view.getBnt_pres()){
            System.out.println("Presionando ");
            if(view.getPn().equals("")){
                System.out.println("Vacio");
            }
            else{
                create(2);
            }
        }
        if(ae.getSource()==view.getBnt_uni()){
            System.out.println("Presionando ");
            if(view.getUn().equals("")){
                System.out.println("Vacio");
            }
            else{
                create(3);
            }
        }
    }
    private void create(int tipo){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("proyecto");        
        EntityManager em = emf.createEntityManager();//
        
        if(tipo == 1) {
            CategoriaJpaController categoriaController = new CategoriaJpaController(em);
            Categoria c = new Categoria();
            
            c.setCategoria(view.getCn().getText());
            try {
                categoriaController.create(c);
                System.out.println("Crea Categoria");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                em.close();
            }
            
        }
        if(tipo == 2) {
            PresentacionJpaController presetnacioncontroller=new PresentacionJpaController(em);
            Presentacion p = new Presentacion();
            
            p.setPresentacion(view.getPn().getText());
            try {
                presetnacioncontroller.create(p);
                System.out.println("Crea Categoria");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                em.close();
            }
            
        }
        if(tipo == 3) {
            UnidadJpaController unidadcontroller=new UnidadJpaController(em);
            Unidad u = new Unidad();
            u.setUnidad(view.getUn().getText());
            try {
                unidadcontroller.create(u);
                System.out.println("Crea Categoria");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                em.close();
            }
            
        }
        //em.close();
        emf.close();
        setModels();
    
        
    }
    
}
/*
 EntityManagerFactory emf= Persistence.createEntityManagerFactory("proyecto");//la unidad de persostencia decide que base de datos usar
        ///EntityMagegerFactory fabrica de adminsitradores de entidades,
        EntityManager em = emf.createEntityManager();//
        //crear consultas
        TypedQuery<Categoria> query_cat = em.createNamedQuery("Categoria.findAll", Categoria.class);
       // TypedQuery<Complemento> query = em.createNamedQuery("Complemento.findByCostoandNombre", Complemento.class);
        //query.setParameter("nombre", "Ron");
       // query.setParameter("costo", 1.5f);
        List<Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        /*for (int i = 0; i < listaComplementos.size(); i++) {
            System.out.println(listaComplementos.get(i));
        }*/
        /*TypedQuery<Presentacion> query_pres = em.createNamedQuery("Presentacion.findAll", Presentacion.class);
        List<Presentacion> listaPresentacion = query_pres.getResultList();//List es una interfaz
        TypedQuery<Unidad> query_unidad = em.createNamedQuery("Unidad.findAll", Unidad.class);
        List<Unidad> listaUnidad = query_unidad.getResultList();//List es una interfaz
        modelo_proxy_jl mc=new modelo_proxy_jl(listaCategoria);
        modelo_proxy_jl mp=new modelo_proxy_jl(listaPresentacion);
        modelo_proxy_jl mm=new modelo_proxy_jl(listaUnidad);
        
        Almacen view=new Almacen(mc,mp,mm);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        Categoria c1 = new Categoria();
      
        c1.setCategoria("Alimentos");
        
        //c1.setCosto(1.5f);
        CategoriaJpaController categoriaController = new CategoriaJpaController(em);
        try {
            categoriaController.create(c1);
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            em.close();
        }/*
*/
 