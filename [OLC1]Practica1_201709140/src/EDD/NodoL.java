/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Objetos.Token;
import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class NodoL {
    Token dato;
    private NodoL next;
    private NodoL before;
    

    public NodoL(Token dato) {
        this.dato = dato;
        next = null;
        before = null;
    }

    public Token getDato() {
        return dato;
    }

    public void setDato(Token dato) {
        this.dato = dato;
    }

    public void setNext(NodoL next) {
        this.next = next;
    }

    public void setBefore(NodoL before) {
        this.before = before;
    }

    public NodoL getNext() {
        return next;
    }

    public NodoL getBefore() {
        return before;
    }
    
    
    
}
