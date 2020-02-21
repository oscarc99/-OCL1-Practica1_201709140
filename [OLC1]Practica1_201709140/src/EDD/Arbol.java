package EDD;

import EDD.Nodo;
import Objetos.Estado;
import Objetos.Token;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author loosc
 */
public class Arbol {

    Lista siguientes = new Lista();
    List transicion = new List();
    Nodo root;
    
    Lista lista;//Tokens del arbol
    private int estados = 0;
    private int contador = 1;
    private int ayuda=0;

    public Lista getSiguientes() {
        return siguientes;
        
    }

    public List getTransicion() {
        return transicion;
    }

    public Arbol() {

    }

    public int getEstados() {
        return estados;
    }

    public void setRoot(Nodo root) {
        this.root = root;
    }

    public Nodo getRoot() {
        return root;
    }

    public Arbol(Lista lista) {
        this.lista = lista;
    }

    public boolean isLeaf(Nodo nod) {
        return nod.getLeft() == null && nod.getRight() == null;
    }

    public Nodo add(NodoL abb) {
        NodoL aux = abb;
        if (abb.dato.getToken() == 16) {//concatenacion .
            Nodo n = new Nodo(aux.dato);
            n.setLeft(add(abb.getNext()));
            n.setRight(add(abb.getNext().getNext()));
            return n;

        }
        if (abb.dato.getToken() == 17) {  //Si es una | (pleca)
            Nodo n = new Nodo(abb.getDato());
            n.setLeft(add(abb.getNext()));
            n.setRight(add(abb.getNext().getNext()));
            return n;

        }
        if (abb.dato.getToken() == 15 || abb.dato.getToken() == 18 || abb.dato.getToken() == 19) {//Cerraduras
            Nodo n = new Nodo(abb.getDato());
            n.setLeft(add(abb.getNext()));
            return n;

        }

        Nodo n = new Nodo(abb.getDato());
        return n;

    }

    private String Graph(Nodo Raiz) {

        String Retorno = "";
        if (Raiz == null) {
            return Retorno;
        }

        if (Raiz.getLeft() != null) {
            Retorno += Graph(Raiz.getLeft());
            Retorno += Raiz.getGraficar()+ "->" + Raiz.getLeft().getGraficar()+ "; \n";
        }
        if (Raiz.getRight() != null) {
            Retorno += Graph(Raiz.getRight());
            Retorno += Raiz.getGraficar()+ "->" + Raiz.getRight().getGraficar() + "; \n";
        }
        String nodo ="";
        //Agrego primeros
        for (int i = 0; i < Raiz.getPrimeros().size(); i++) {
            if(i == 0){
                nodo +=  Raiz.getPrimeros().get(i); 
            }else{
                nodo +=","+  Raiz.getPrimeros().get(i); 
            }
            
        }
        if(Raiz.isAnulable()){
            nodo+="| { A";
        }else{
            nodo+="| { N";
        }
        nodo+= "| "+Raiz.getTok().getLexema();
        nodo += "|"+ Raiz.getId()+" } |";
        for (int i = 0; i < Raiz.getUltimos().size(); i++) {
            if(i == 0){
                nodo +=  Raiz.getUltimos().get(i); 
            }else{
                nodo +=","+  Raiz.getUltimos().get(i); 
            }
        }
        return Retorno + Raiz.getGraficar() + "[shape=record label=\" " +nodo+" \" ];\n";

    }

    private String graficar() {
        if (root == null) {
            return "\n\n";
        } else {
            return "\n  \n" + Graph(root) + "\n";
        }
    }

    public void report(String i) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\arbol_" + i + ".dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            g();
            pw.println(graficar());
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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\arbol_" + i + ".dot -o src\\arbol_" + i + ".png");
//            Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    public void identificar(Nodo root) {

        if (root != null) {

            identificar(root.getLeft());
            if (isLeaf(root)) {
                root.setAnulable(false);
                root.setId(contador);
                root.getTok().setId(root.getId());
                siguientes.add(root.getTok());
                contador++;
            }
            identificar(root.getRight());

        }
    }

    public void identificar() {
        if (root != null) {
            contador = 1;
            identificar(root);
        }
    }

    public void setAnulable() {
        if (root != null) {

            anulable(root);
        }
    }

    public void anulable(Nodo root) {
        if (root != null) {
            anulable(root.getLeft());
            anulable(root.getRight());
            //Condiciones de anulabilidad
            if (isLeaf(root) || root.getTok().getToken() == 18) {
                root.setAnulable(false);
            } else if (root.getTok().getToken() == 17) { //Si hay un anulable en sus hijos se vuelve anulable
                if (root.getRight().isAnulable() || root.getLeft().isAnulable()) {
                    root.setAnulable(true);
                } else {
                    root.setAnulable(false);
                }
            } else if (root.getTok().getToken() == 16) { //Ambos hijos deben ser anulables para que sea anulable
                if (root.getRight().isAnulable() && root.getLeft().isAnulable()) {
                    root.setAnulable(true);
                } else {
                    root.setAnulable(false);
                }
            } else if (root.getTok().getToken() == 19 || root.getTok().getToken() == 15) {
                root.setAnulable(true);
            }

        }
    }

    public void setPrimUlt() {//Primeros y ultimos
        if (root != null) {

            pyu(root);
        }
    }

    public void pyu(Nodo root) {
        System.out.println("aaaaaaaaa");
        if (root != null) {
            pyu(root.getLeft());
            pyu(root.getRight());
            //Para los primeros y ultimos
            if (isLeaf(root)) {
                root.primeros.add(root.id);
                root.ultimos.add(root.id);
            } else if (root.getTok().getToken() == 16) { //CONCATENACION

                if (root.getLeft().isAnulable()) {
                    root.setPrimeros(root.getLeft().getPrimeros());
                    for (int i = 0; i < root.getRight().getPrimeros().size(); i++) {
                        root.primeros.add(root.getRight().getPrimeros().get(i));
                    }
                } else {
                    root.setPrimeros(root.getLeft().getPrimeros());
                }

                if (root.getRight().isAnulable()) {
                    root.setUltimos(root.getRight().getUltimos());
                    for (int i = 0; i < root.getLeft().getUltimos().size(); i++) {
                        root.ultimos.add(root.getLeft().getUltimos().get(i));
                    }
                } else {
                    root.setUltimos(root.getRight().getUltimos());
                }

            } else if (root.getTok().getToken() == 17) { //OR | Union de primeros y ultimos
                root.setPrimeros(root.getLeft().getPrimeros());
                for (int i = 0; i < root.getRight().getPrimeros().size(); i++) {
                    root.primeros.add(root.getRight().getPrimeros().get(i));
                }
                root.setUltimos(root.getLeft().getUltimos());
                for (int i = 0; i < root.getRight().getUltimos().size(); i++) {
                    root.ultimos.add(root.getRight().getUltimos().get(i));
                }
            } else if (root.getTok().getToken() == 19 || root.getTok().getToken() == 15 || root.getTok().getToken() == 18) {//CICLOS (*, ?, +)
                root.setPrimeros(root.getLeft().getPrimeros());
                root.setUltimos(root.getLeft().getUltimos());
            }

        }
    }

    public void setSiguientes() {

        if (root != null) {

            siguientes(root);

        }
    }

    private void siguientes(Nodo root) {
        if (root != null) {
            siguientes(root.getLeft());
            siguientes(root.getRight());
            if (root.getTok().getToken() == 15 || root.getTok().getToken() == 18) {//Si son ciclos (*, +)
                //A los ultimos de este nodo les siguie los primeros de este mismo
                for (int i = 0; i < siguientes.size(); i++) {
                    for (int j = 0; j < root.getUltimos().size(); j++) {
                        if (siguientes.get(i).getId() == root.getUltimos().get(j)) {
                            for (int k = 0; k < root.getPrimeros().size(); k++) {

                                siguientes.get(i).addSiguiente(root.getPrimeros().get(k));
                            }

                        }

                    }

                }
            } else if (root.getTok().getToken() == 16) {//Concatenacion
                for (int i = 0; i < siguientes.size(); i++) {
                    for (int j = 0; j < root.getLeft().getUltimos().size(); j++) {
                        if (siguientes.get(i).getId() == root.getLeft().getUltimos().get(j)) {
                            for (int k = 0; k < root.getRight().getPrimeros().size(); k++) {
                                siguientes.get(i).addSiguiente(root.getRight().getPrimeros().get(k));
                            }

                        }

                    }

                }
            } else {
                //Ignora
                System.out.println("Ignore a:" + root.getId() + " con " + root.getTok().getLexema());
            }

        }
    }

    public void setTransiciones() {
        if (root != null) {
            estados = 0;
            setEstados();
            transicion.trans(transicion.getSize());
            transiciones();

        }
    }

    public void transiciones() {

        for (int i = 0; i < transicion.getSize(); i++) {
            for (int j = 0; j < transicion.get_element_at(i).getConjunto().size(); j++) {
                for (int k = 0; k < siguientes.size(); k++) {
                    if (transicion.get_element_at(i).getConjunto().get(j) == siguientes.get(k).getId()) {
                        int est = transicion.estado(siguientes.get(k).getSiguientes());
                        transicion.get_element_at(i).setTransicion(est, transicion.get_element_at(i).getConjunto());
                    }

                }

            }

        }
    }

    public void setEstados() {
        estados = 0;
        Estado s = new Estado("S" + estados, estados);
        s.setConjunto(root.getPrimeros());

        transicion.add_last(s);
        estados++;//Nombre para los estados
        int conteo = 1;

        while (conteo != 0) {
            conteo--;
            for (int i = 0; i < transicion.getSize(); i++) {
                for (int j = 0; j < transicion.get_element_at(i).getConjunto().size(); j++) {
                    for (int k = 0; k < siguientes.size(); k++) {
                        if (transicion.get_element_at(i).getConjunto().get(j) == siguientes.get(k).getId()) {
                            if (!transicion.existe(siguientes.get(k).getSiguientes())) {
                                s = new Estado("S" + estados, estados);
                                s.setConjunto(siguientes.get(k).getSiguientes());
                                transicion.add_last(s);
                                conteo++;
                                estados++;
                            }

                        }
                    }
                }

            }
        }
        System.out.println("sali");
    }

    public boolean compare(LinkedList a, LinkedList b) {
        if (a.size() == 0 || b.size() == 0) {
            return true;
        }
        if (a.size() == b.size()) {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) != b.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }
    
    public String lexema(int a){
        for (int i = 0; i < siguientes.size(); i++) {
            if(siguientes.get(i).getId() == a){
                return siguientes.get(i).getLexema();
            }
        }
        return "";
    }
    
    
    private void g(){
        if (root!= null){
            ayuda=0;
            g(root);
        }
    }
    
    private void g(Nodo root) {
        
        if(root != null){
            g(root.getLeft());
            g(root.getRight());
            root.setGraficar("N"+ayuda);
            ayuda++;
                        
        }
        
    }
    
    public void reporteDFA(String i){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\dfa_" + i + ".dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("nodesep=0.8;\n");
            pw.println("ranksep=0.5;\n");
            
            pw.println(DFA());
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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\dfa_" + i + ".dot -o src\\dfa_" + i + ".png");
//            Process pa = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\AVLTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    private String DFA(){
        String dot ="";
        String nodos="";
        boolean aceptacion =false;
        String dir="";
        for (int i = 0; i < transicion.getSize(); i++) {
            for (int j = 0; j < transicion.get_element_at(i).getTransicion().length; j++) {
                for (int k = 0; k < transicion.get_element_at(i).getTransicion()[j].size() ; k++) {
                    if(transicion.get_element_at(i).getTransicion()[j].get(k)== siguientes.size() ){
                    aceptacion =true;
                    }
                }

                    
                }
            }
            if(aceptacion){
                nodos += transicion.get_element_at(i).getId() +"[shape= \"doublecircle\"  label = \" "+transicion.get_element_at(i).getId()+"\" ] \n";
            }else{
                nodos += transicion.get_element_at(i).getId() +"[shape= \"circle\"  label = \" "+transicion.get_element_at(i).getId()+"\" ] \n";
            }
            aceptacion = false;
            for (int j = 0; j < transicion.get_element_at(i).getTransicion().length; j++) {
                
                dir +="";
                //estado(LinkedList) METODO SABER EL ESTADO ID(int)
                
                
            }
            
            
        }
        
        
        dot += nodos+ dir;
        return dot;
    }
    
    
    
}
