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
public class Lexema {

    String cadena;
    String identificador;
    boolean estado=false;

    public Lexema() {
    }

    public Lexema(String token, String identificador) {
        this.cadena = token;
        this.identificador = identificador;
    }

    public String getToken() {
        return cadena;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setToken(String token) {
        this.cadena = token;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
}
