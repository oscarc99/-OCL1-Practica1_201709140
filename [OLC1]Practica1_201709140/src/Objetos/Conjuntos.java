/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class Conjuntos {
    String Nombre;
    LinkedList<Integer> conjunto = new LinkedList<Integer>();

    public Conjuntos() {
    }

    public Conjuntos(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public LinkedList<Integer> getConjunt() {
        return conjunto;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setConjunt(LinkedList<Integer> conjunt) {
        this.conjunto = conjunt;
    }
    
    public void llenar(char inicio, char fin){
        for (int i = inicio; i < fin+1; i++) {
            conjunto.add(i);
        }
    } 
    
    
    public void agregar(char c){
        int ca = c;
        conjunto.add(ca);
    } 
    
    
    
    
    
    
    
    
    
    
}
