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

    Token token;
    String identificador;

    public Lexema() {
    }

    public Lexema(Token token, String identificador) {
        this.token = token;
        this.identificador = identificador;
    }

    public Token getToken() {
        return token;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
}
