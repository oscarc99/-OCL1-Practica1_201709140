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
public class Lista {

    private NodoL first;
    private NodoL last;
    private int size;
    

    public Lista() {
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

    public void setFirst(NodoL first) {
        this.first = first;
    }

    public NodoL getFirst() {
        return first;
    }

    public NodoL getLast() {
        return last;
    }

    public int size() {
        return size;
    }

    public void setLast(NodoL last) {
        this.last = last;
    }

    public Token get(int index) {
        if (index >= 0 && index < size) {
            NodoL aux = this.getFirst();
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    return aux.getDato();
                }
                aux = aux.getNext();
                x++;
            }

            

        }
        return null;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add_first(Token dato) {
        NodoL n = new NodoL(dato);
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

    public void add(Token dato) {
        if (isEmpty()) {
            add_first(dato);

        } else {
            NodoL n = new NodoL(dato);
            this.last.setNext(n);
            n.setBefore(last);
            this.last = n;
            size++;

        }
    }

    public void add_at(Token dato, int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add_first(dato);
                return;
            }
            if (index == size) {
                add(dato);
                return;
            }
            NodoL aux = first;
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }
            NodoL n = new NodoL(dato);
            aux.getBefore().setNext(n);
            n.setBefore(aux.getBefore());
            n.setNext(aux);
            aux.setBefore(n);
            this.size++;

        }
    }

    public void remove_at(int index) {
        if (index >= 0 && index < size) {
            NodoL aux = this.getFirst();
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

}
