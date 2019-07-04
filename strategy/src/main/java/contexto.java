/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sys515
 */
public class contexto {
    private stategy estrategia;
    public contexto(stategy estrategia){
        this.estrategia=estrategia;
    }
    public int procesar(int a, int b) {
         return estrategia.realizarOperacion(a, b);
    }
}
