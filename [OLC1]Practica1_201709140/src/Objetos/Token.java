/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class Token {
    String lexema;
    int token;
    String desc;
    int id;
    LinkedList<Integer> siguientes = new LinkedList<Integer>();
    String estado;
    int state;

    public LinkedList<Integer> getSiguientes() {
        return siguientes;
    }
    
    public void addSiguiente(int i){
        boolean existe=false;
        for (int j = 0; j < siguientes.size(); j++) {
            if(siguientes.get(j)==i){
                existe = true;
                break;
            }
        }
        if(!existe){
            siguientes.add(i);
        }
        
    }
    
    
    public Token() {
    }

    public Token(String lexema, int token, String desc) {
        this.lexema = lexema;
        this.token = token;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
