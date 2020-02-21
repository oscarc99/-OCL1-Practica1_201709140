/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import EDD.Arbol;
import EDD.Lista;
import EDD.Nodo;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author loosc
 */
public class ExpresionesRegulares {

    String nombre;
    Lista tokens = new Lista();

    Arbol tree;

    public ExpresionesRegulares(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Lista getTokens() {
        return tokens;
    }

    public Arbol getTree() {
        return tree;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTokens(Lista salida) {
        this.tokens = salida;
    }

    public void setTree(Arbol tree) {
        this.tree = tree;
    }

    public void addToken(Token n) {
        tokens.add(n);
    }

    public void llenarTree() {

        Nodo nuevo = new Nodo(new Token(".", 16, "Punto"));
        Nodo n1 = new Nodo(new Token("#", 22, "Numeral"));
        tree.setRoot(nuevo);
        tree.getRoot().setRight(n1);
        tree.getRoot().setLeft(tree.add(tokens.getFirst()));

    }

    public void reportSiguientes() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\siguientes_" + nombre + ".dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            pw.println(graficarSiguientes());
            pw.println("}\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
             ProcessBuilder pbuild = new ProcessBuilder("dot", "-Tpng", "-o", "src\\siguientes_"+nombre+".png", "src\\siguientes_"+nombre+".dot");
            pbuild.redirectErrorStream(true);
            pbuild.start();
            
            
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private String graficarSiguientes() {
        String dot = "";
        String hojas = "";
        String siguientes = "";
        dot += "node [fontname=\"Arial\"]; \n";
        dot += "node_A [shape=record  label=\n";

        //Ciclo solo para colocar los terminales
        hojas += "\" {Hoja ";

        //Ciclo para siguientes 
        siguientes += "{Siguientes";
        for (int i = 0; i < tree.getSiguientes().size(); i++) {
            //code hojas
            hojas += "|{" + tree.getSiguientes().get(i).getLexema() + "|" + tree.getSiguientes().get(i).getId() + "}";
            //Code siguientes
            siguientes += "|";
            for (int j = 0; j < tree.getSiguientes().get(i).getSiguientes().size(); j++) {
                if (j == 0) {
                    siguientes += tree.getSiguientes().get(i).getSiguientes().get(j);
                } else {
                    siguientes += "," + tree.getSiguientes().get(i).getSiguientes().get(j);
                }

            }
        }
        hojas += "}|";
        siguientes += "} \"";
        dot += hojas;
        dot += siguientes;
        dot += "]; \n";

        return dot;
    }

    public void reportTransicion() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\transicion_" + nombre + ".dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            pw.println(graficarTransiciones());
            pw.println("}\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            ProcessBuilder pbuild = new ProcessBuilder("dot", "-Tpng", "-o", "src\\transicion_"+nombre+".png", "src\\transicion_"+nombre+".dot");
            pbuild.redirectErrorStream(true);
            pbuild.start();
            

            
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String graficarTransiciones() {
        String dot = "";
        String estados = "";
        String[] estado = new String[tree.getTransicion().getSize()];
        dot += "node [fontname=\"Arial\"]; \n";
        dot += "node_A [shape=record  label=\n \"";
        estados += "{Estados";
        for (int i = 0; i < tree.getTransicion().getSize(); i++) {
            estados += "| " + tree.getTransicion().get_element_at(i).getId() + " \\{ ";
            for (int j = 0; j < tree.getTransicion().get_element_at(i).getConjunto().size(); j++) {
                if (j == 0) {
                    estados += tree.getTransicion().get_element_at(i).getConjunto().get(j);
                } else {
                    estados += "," + tree.getTransicion().get_element_at(i).getConjunto().get(j);
                }

            }
            estados += "\\}";
        }
        estados += "}";
        //Recorre el arreglo para agregar 
        System.out.println("cambio");
        for (int i = 0; i < tree.getTransicion().getSize(); i++) {
            estado[i] = "| { " + tree.getTransicion().get_element_at(i).getId();
        }
        for (int i = 0; i < tree.getTransicion().getSize(); i++) {
            for (int j = 0; j < tree.getTransicion().get_element_at(i).getTransicion().length; j++) {

                if (tree.getTransicion().get_element_at(i).getTransicion()[j] == null) {
                    estado[j] += "| ";
                } else {

                    estado[j] += "| ";
                    for (int k = 0; k < tree.getTransicion().get_element_at(i).getTransicion()[j].size(); k++) {
                        if (i == tree.getTransicion().getSize() - 1 && j == 0) {

                        } else {
                            if (k == 0) {
                                estado[j] += tree.lexema(tree.getTransicion().get_element_at(i).getTransicion()[j].get(k));
                            } else {
                                estado[j] += "," +tree.lexema(tree.getTransicion().get_element_at(i).getTransicion()[j].get(k));
                            }
                        }

                    }
                }
            }

        }

        for (int i = 0; i < tree.getTransicion().getSize(); i++) {
            estado[i] += "}";
        }
        for (int i = 0; i < tree.getTransicion().getSize(); i++) {
            estados += estado[i];
        }
        estados += "}";
        dot += estados;
        dot += " \"]; \n";
        return dot;
    }

}
