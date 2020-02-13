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
public class Token {
    String lexema;
    int token;
    String desc;

    public Token() {
    }

    public Token(String lexema, int token, String desc) {
        this.lexema = lexema;
        this.token = token;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getLexema() {
        return lexema;
    }

    public int getToken() {
        return token;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setToken(int token) {
        this.token = token;
    }
    
    
    
}
