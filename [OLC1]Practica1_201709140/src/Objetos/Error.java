/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author loosc
 */
public class Error {
    String error;
    int linea;
    int fila;

    public Error() {
    }

    public Error(String error, int linea) {
        this.error = error;
        this.linea = linea;
    }
    public Error(String error){
        this.error = error;
    } 

    public String getError() {
        return error;
    }

    public int getLinea() {
        return linea;
    }

    public int getFila() {
        return fila;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    
    
    
}
