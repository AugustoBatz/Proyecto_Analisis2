/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.almacen;

import gt.url.edu.inventariomaven.Categoria;
import gt.url.edu.inventariomaven.CategoriaJpaController;
import gt.url.edu.inventariomaven.Marca;
import gt.url.edu.inventariomaven.MarcaJpaController;
import gt.url.edu.inventariomaven.Presentacion;
import gt.url.edu.inventariomaven.PresentacionJpaController;
import gt.url.edu.inventariomaven.Unidad;
import gt.url.edu.inventariomaven.UnidadJpaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import gt.url.edu.main.Main;

/**
 *
 * @author sys515
 */
public class AlmacenController extends AlmacenOperacionesBD implements ActionListener {
    private Almacen view;
    private List querylist = new ArrayList();

    public AlmacenController(Almacen view) {
        this.querylist = listasDelAlmacen();
        this.view = view;

        addActionListener();
        setModels();

    }

    public void addActionListener() {
        view.getBnt_cat().addActionListener(this);
        view.getBnt_pres().addActionListener(this);
        view.getBnt_uni().addActionListener(this);
        view.getBtn_marca().addActionListener(this);

    }

    public void setModels() {

        querylist.clear();
        querylist = listasDelAlmacen();
        view.getLs_categoria().setModel(new ModeloProxyJList((List) querylist.get(0)));
        view.getLs_presentacion().setModel(new ModeloProxyJList((List) querylist.get(1)));
        view.getLs_unidad_medida().setModel(new ModeloProxyJList((List) querylist.get(2)));
        view.getLs_marca().setModel(new ModeloProxyJList((List) querylist.get(3)));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == view.getBnt_cat()) {
            if (view.getCn().equals("")) {
            } else {
                create(1, view.getCn().getText());

            }

            setModels();
        }
        if (ae.getSource() == view.getBnt_pres()) {
            if (view.getPn().equals("")) {
            } else {
                create(2, view.getPn().getText());
                setModels();
            }
        }
        if (ae.getSource() == view.getBnt_uni()) {
            if (view.getUn().equals("")) {
            } else {
                create(3, view.getUn().getText());
                setModels();
            }
        }
        if (ae.getSource() == view.getBtn_marca()) {
            if (view.getMn().equals("")) {
            } else {
                create(4, view.getMn().getText());
                setModels();
            }
        }

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
 