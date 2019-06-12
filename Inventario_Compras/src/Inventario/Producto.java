/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;
/**
 *
 * @author sys515
 */
public class Producto {

    private String codigo;

    private String nombre;

    private String marca;

    private String unidad;

    private String presentacion;

    private String categoria;

    private int stock_minimo;

    private int existencia;
    public int crearProducto(String codigo,String nombre,String marca, String unidad,String presentacion,
            String categoria,int stock_m,int existencia){
        this.codigo=codigo;
        this.nombre=nombre;
        this.marca=marca;
        this.unidad=unidad;
        this.presentacion=presentacion;
        this.categoria=categoria;
        this.stock_minimo=stock_m;
        this.existencia=existencia;
        return 1;
    }
    public String getProducto(){
        return codigo+", "+nombre+", "+marca+", "+unidad+", "+presentacion+", "+categoria+", "+stock_minimo+", "+existencia;
    }
}
