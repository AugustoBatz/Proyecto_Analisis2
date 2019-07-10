/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.inventario;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author sys515
 */
public class RenderCellRojo extends DefaultTableCellRenderer {
    private int columnaPatron;

    public RenderCellRojo(int columnaPatron) {
        this.columnaPatron = columnaPatron;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if (Integer.parseInt(String.valueOf(table.getValueAt(row, 6))) <= Integer.parseInt(String.valueOf(table.getValueAt(row, 7)))) {
            setBackground(Color.red);
        }

         super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
