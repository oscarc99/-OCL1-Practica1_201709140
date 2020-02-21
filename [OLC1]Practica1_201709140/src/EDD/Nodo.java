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
public class Nodo {

    boolean anulable;
    int id;
    LinkedList<Integer> primeros = new LinkedList<Integer>();
    LinkedList<Integer> ultimos = new LinkedList<Integer>();
    Token tok;
    String identificador; //lexema del token
    Nodo left;
    Nodo right;
    String graficar;

    public String getGraficar() {
        return graficar;
    }

    public void setGraficar(String graficar) {
        this.graficar = graficar;
    }
    

    public Nodo(Token tok) {
        this.tok = tok;
    }

    public Nodo(String identificador) {
        this.identificador = identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Nodo getLeft() {
        return left;
    }

    public Nodo getRight() {
        return right;
    }

    public Nodo() {
    }

    public boolean lleno(){
        return left != null &&  right != null;
    }
    
    
    
    public boolean isAnulable() {
        return anulable;
    }

    public int getId() {
        return id;
    }

    public LinkedList<Integer> getPrimeros() {
        return primeros;
    }

    public LinkedList<Integer> getUltimos() {
        return ultimos;
    }

    public Token getTok() {
        return tok;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrimeros(LinkedList<Integer> primeros) {
        this.primeros = primeros;
    }

    public void setUltimos(LinkedList<Integer> ultimos) {
        this.ultimos = ultimos;
    }

    public void setTok(Token tok) {
        this.tok = tok;
    }

}
