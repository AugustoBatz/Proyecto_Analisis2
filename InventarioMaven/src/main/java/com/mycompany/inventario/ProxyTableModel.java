/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventario;

import com.mycompany.inventariomaven.Producto;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sys515
 */
public class ProxyTableModel implements TableModel{

    private List<Producto> productos;
    private DefaultTableModel realSubject = new DefaultTableModel();
    public ProxyTableModel(List nombres,List productos){
        nombresDeColumnas(nombres);
        this.productos=productos;
        agregarFila();
        
        
    }
    private void nombresDeColumnas(List nombres)
    {
        for (Object objeto : nombres) {
            realSubject.addColumn(objeto);
            System.out.println(objeto);
        }
    }
    private void agregarFila()
    {
        String[] datosFilas =new String[8];
        
       
        for (Producto objeto : productos) 
        {
            datosFilas[0] = objeto.getCodigo();
            datosFilas[1] = objeto.getNombre();
            datosFilas[2] = objeto.getMarcaid().getMarca();
            datosFilas[3] = objeto.getPresentacionid().getPresentacion();
            datosFilas[4] = objeto.getUnidadid().getUnidad();
            datosFilas[5] = objeto.getCategoriaid().getCategoria();
            datosFilas[6] = String.valueOf(objeto.getExistencia());
            datosFilas[7] = String.valueOf(objeto.getStockMinimo());
            realSubject.addRow(datosFilas);
          
        }
       
    }
    @Override
    public int getRowCount() {
        return realSubject.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return realSubject.getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return realSubject.getColumnName(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return realSubject.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return realSubject.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return realSubject.getValueAt(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        realSubject.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        realSubject.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        realSubject.removeTableModelListener(l);
    }
    
}
