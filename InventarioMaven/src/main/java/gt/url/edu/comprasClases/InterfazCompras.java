/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasClases;

import gt.url.edu.inventariomaven.FacturaCompra;
import gt.url.edu.inventariomaven.Lote;
import java.util.List;

/**
 *
 * @author sys515
 */
public interface InterfazCompras {
   
    public void crearFacturaCompra(FacturaCompra fc);
    public void crearLotes(List data);
}
