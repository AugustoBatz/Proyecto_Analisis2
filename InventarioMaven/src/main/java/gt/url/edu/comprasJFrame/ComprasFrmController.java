/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import gt.url.edu.inventario.InventarioOperacionesBD;
import gt.url.edu.inventariomaven.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sys515
 */
public class ComprasFrmController extends InventarioOperacionesBD implements ActionListener{
    private ComprasFrm view;
    private String idProducto;
    private Producto productoSeleccioando;
    private DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    
    private InterfazLlenarDetalle interfaz=new ProductoParadetalle();
    
    public ComprasFrmController(ComprasFrm view){
        this.view=view;
        this.view.getComboProductos().setModel(new ProxyModelProductos(listProductos()));
        idProducto=getIdProducto(view.getComboProductos().getSelectedItem().toString());
        addActionListener();
        modelo.addColumn("id");
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Marca");
        modelo.addColumn("Presentacion");
        modelo.addColumn("Unidad");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Costo");
        modelo.addColumn("Costo Total");
        this.view.getTablaProductos().setModel(modelo);
    }

    public void addActionListener() {
        this.view.getBtnagregar().addActionListener(this);
        this.view.getComboProductos().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == view.getBtnagregar()) {
            detalle();
            
        }
        if (ae.getSource() == view.getComboProductos()) {
            idProducto = getIdProducto(view.getComboProductos().getSelectedItem().toString());
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdProducto(String seleccion) {
        String[] parts = seleccion.split(",");
        System.out.println(parts[0]);
        view.getLabelProducto().setText(seleccion);
        return parts[0];
    }
    public void detalle(){
         productoSeleccioando= interfaz.getProducto(idProducto);
          modelo.addRow(new Object[]{
              productoSeleccioando.getId(),
              productoSeleccioando.getCodigo(),
              productoSeleccioando.getNombre(),
              productoSeleccioando.getMarcaid().getMarca(),
              productoSeleccioando.getPresentacionid().getPresentacion(),
              productoSeleccioando.getUnidadid().getUnidad(),
              view.getTextCantidad().getText(),
              view.getTextCU().getText(),
              String.valueOf(Double.valueOf(view.getTextCantidad().getText())*Double.valueOf( view.getTextCU().getText()))
          });
    }
    
    
}
