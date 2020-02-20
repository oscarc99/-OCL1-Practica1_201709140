/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Objetos.Estado;
import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class List {

    private NodoT first;
    private NodoT last;
    private int size;

    public List() {
        first = null;
        last = null;
        size = 0;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public void setFirst(NodoT first) {
        this.first = first;
    }

    public NodoT getFirst() {
        return first;
    }

    public NodoT getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void setLast(NodoT last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add_first(Estado dato) {
        NodoT n = new NodoT(dato);
        if (isEmpty()) {
            first = n;
            last = n;
            size++;

        } else {
            n.setNext(first);
            first.setBefore(n);
            first = n;
            size++;

        }
    }

    

    public void add_last(Estado dato) {
        if (isEmpty()) {
            add_first(dato);

        } else {
            NodoT n = new NodoT(dato);
            this.last.setNext(n);
            n.setBefore(last);
            this.last = n;
            size++;

        }
    }

    public void add_at(Estado dato, int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add_first(dato);
                return;
            }
            if (index == size) {
                add_last(dato);
                return;
            }
            NodoT aux = first;
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }
            NodoT n = new NodoT(dato);
            aux.getBefore().setNext(n);
            n.setBefore(aux.getBefore());
            n.setNext(aux);
            aux.setBefore(n);
            this.size++;

        }
    }

    public Estado get_element_at(int index) {
        if (index >= 0 && index < size) {
            NodoT iterador = getFirst();
            int x = 0;
            while (iterador != null) {
                if (index == x) {
                    return iterador.getDato();
                }
                iterador = iterador.getNext();
                x++;
            }
        }
        return null;

    }

    public void remove_at(int index) {
        if (index >= 0 && index < size) {
            NodoT aux = this.getFirst();
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }

            if (index == 0) {

                this.first = aux.getNext();
                this.first.setBefore(null);
                this.size--;

            } else if (index == this.size - 1) {
                this.last = aux.getBefore();
                this.last.setNext(null);
                this.size--;
            } else {
                aux.getBefore().setNext(aux.getNext());
                aux.getNext().setBefore(aux.getBefore());
                this.size--;
            }

        }
    }
    
    public boolean existe(LinkedList<Integer> a){
        for (int i = 0; i < size; i++) {
            if(compare(get_element_at(i).getConjunto(), a)){
                return true;
            }
            
        }
        return false;
    }
    
    public int estado(LinkedList<Integer> a){
        for (int i = 0; i < size; i++) {
            if(compare(get_element_at(i).getConjunto(), a)){
                return i;
            }
            
        }
        return size;
    }
    
    
    
    
    public boolean compare(LinkedList<Integer> a, LinkedList<Integer> b) {
        if(a.size()==0 || b.size()==0) return true;
        if (a.size() == b.size()) {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) != b.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }
    
    
    public void trans(int n){
        for (int i = 0; i < size; i++) {
            get_element_at(i).transiciones(n); 
        }
    }
    
    public void transicion(int posicion,LinkedList<Integer> a ){
        get_element_at(posicion).setTransicion(posicion, a); 
    }
    
    
}
