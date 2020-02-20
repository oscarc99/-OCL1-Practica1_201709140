package EDD;

import Objetos.Estado;

/**
 *
 * @author loosc
 */
public class NodoT {

    Estado dato;
    private NodoT next;
    private NodoT before;

    public NodoT(Estado dato) {
        this.dato = dato;
        next = null;
        before = null;
    }

    public Estado getDato() {
        return dato;
    }

    public void setDato(Estado dato) {
        this.dato = dato;
    }

    public void setNext(NodoT next) {
        this.next = next;
    }

    public void setBefore(NodoT before) {
        this.before = before;
    }

    public NodoT getNext() {
        return next;
    }

    public NodoT getBefore() {
        return before;
    }

}
