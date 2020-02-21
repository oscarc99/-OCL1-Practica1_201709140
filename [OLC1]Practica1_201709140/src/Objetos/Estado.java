package Objetos;

import EDD.Lista;
import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class Estado {

    String id;
    int identificador;
    LinkedList<Integer> transicion[];

    LinkedList<Integer> conjunto = new LinkedList<Integer>();//Terminales que le pertenecen

    public Estado() {
    }

    public LinkedList<Integer>[] getTransicion() {
        return transicion;
    }

    public LinkedList getT(int x) {
        return transicion[x];
    }

    public Estado(String id, int identificador) {
        this.id = id;
        this.identificador = identificador;
    }

    public String getId() {
        return id;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public LinkedList<Integer> getConjunto() {
        return conjunto;
    }

    public void setConjunto(LinkedList<Integer> conjunto) {
        this.conjunto = conjunto;
    }

    public void add(int a) {
        conjunto.add(a);
    }

    public void transiciones(int n) {
        transicion = new LinkedList[n];
    }

    public void setTransicion(int n, LinkedList<Integer> lista) {
        transicion[n] = lista;
    }

}
