/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.practica1_201709140;

import Analizadores.AnalizadorLexico;
import GUI.Principal;
import Objetos.Token;
import Objetos.Error;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class OLC1Practica1_201709140 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        AnalizadorLexico a = new AnalizadorLexico();
        LinkedList<Token> salida = new LinkedList<Token>();
        
        salida = a.escanear("C:\\Users\\loosc\\OneDrive\\Escritorio\\USAC\\1S2020\\Compi\\Practica 1\\entrada.txt");
        LinkedList<Error> errores = new LinkedList<Error>();
        errores= a.getErrores();
        System.out.println("---SALIDA TOKEN---");
        for (int i = 0; i < salida.size(); i++) {
            System.out.println(salida.get(i).getLexema());
        }
        System.out.println("---ERRORES---");
        for (int i = 0; i < errores.size(); i++) {
            System.out.println(errores.get(i).getError());
        }
       
        a.tablaErrores("C:\\Users\\loosc\\OneDrive\\Escritorio\\Errores.pdf");
        Principal v = new Principal();
        v.setVisible(true);
    }
    
}
