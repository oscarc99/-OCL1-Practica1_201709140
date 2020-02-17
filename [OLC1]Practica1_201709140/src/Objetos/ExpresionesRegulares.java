/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import EDD.Arbol;
import EDD.Lista;
import EDD.Nodo;



/**
 *
 * @author loosc
 */
public class ExpresionesRegulares {
    String nombre;
    Lista tokens = new Lista();
    
    Arbol tree;

    public ExpresionesRegulares(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Lista getTokens() {
        return tokens;
    }

    public Arbol getTree() {
        return tree;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTokens(Lista salida) {
        this.tokens = salida;
    }

    public void setTree(Arbol tree) {
        this.tree = tree;
    }
    
    public void addToken(Token n){
        tokens.add(n);
    }
    
    public void llenarTree(){
        
        Nodo nuevo = new Nodo(new Token(".", 16,"Punto"));
        Nodo n1 = new Nodo(new Token("#", 22,"Numeral"));
        tree.setRoot(nuevo); 
        tree.getRoot().setRight(n1); 
        tree.getRoot().setLeft(tree.add(tokens.getFirst()));
        
    }
    
    
}
