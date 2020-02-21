package Analizadores;

import EDD.Arbol;
import Objetos.Conjuntos;
import Objetos.Token;
import Objetos.Error;
import Objetos.ExpresionesRegulares;
import Objetos.Lexema;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.*;

import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author loosc
 */
public class AnalizadorLexico {

    LinkedList<Error> errores = new LinkedList<Error>();
    LinkedList<Token> salida = new LinkedList<Token>();
    LinkedList<Conjuntos> conjuntos = new LinkedList<Conjuntos>();
    LinkedList<ExpresionesRegulares> er = new LinkedList<ExpresionesRegulares>();
    LinkedList<Lexema> lexemas = new LinkedList<Lexema>();
    int estado = 0;
    String auxLex = "";

    public LinkedList<ExpresionesRegulares> getEr() {
        return er;
    }

    public AnalizadorLexico() {
    errores = new LinkedList<Error>();
       salida = new LinkedList<Token>();
       conjuntos = new LinkedList<Conjuntos>();
        er = new LinkedList<ExpresionesRegulares>();

    }

    public LinkedList<Token> getSalida() {
        return salida;
    }

    public LinkedList<Conjuntos> getConjuntos() {
        return conjuntos;
    }

    public LinkedList<Error> getErrores() {
        return errores;
    }

    public LinkedList<Token> escanear(String ruta) {
        salida.clear();
        errores.clear();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead + "\n";
            }

            lexico(temp);
        } catch (Exception e) {
            System.err.println("No se encontro el archivo");
        }
        return salida;
    }

    public void lexico(String caracte) {
        salida.clear();
        errores.clear();
        char[] caracteres = caracte.toCharArray();
        estado = 0;

        for (int i = 0; i < caracteres.length; i++) {
            switch (estado) {
                case 0:
                    if (isLetter(caracteres[i])) {
                        //Ir a estado 8 para identificador
                        auxLex += String.valueOf(caracteres[i]);
                        estado = 8;
                    } else if (caracteres[i] == '<') {
                        //Ir al estado 4 comentario multilinea
                        auxLex += String.valueOf(caracteres[i]);
                        estado = 4;
                    } else if (caracteres[i] == '"') {
                        //Ir a estado 10 cadena o lexema
                        estado = 9;
                        
                    } else if (caracteres[i] == '/') {
                        //Ir a estado 1 para comentario una linea
                        auxLex += String.valueOf(caracteres[i]);
                        estado = 1;
                    } else if (caracteres[i] == (char) 10) {
                        //Ignora ya que son solo de paso   
                    }//Guarda simbolos
                    else if (caracteres[i] == '{') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 3, "Llave abre");
                    } else if (caracteres[i] == '}') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 4, "Llave cierra");
                    } else if (caracteres[i] == '-') {
                        estado = 10;
                        auxLex += String.valueOf(caracteres[i]);

                    } else if (caracteres[i] == '%') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 8, "Porcentaje");
                    } else if (caracteres[i] == ':') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 9, "Dos puntos");
                    } else if (caracteres[i] == ';') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 10, "Punto y coma");
                    } else if (caracteres[i] == ',') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 12, "Coma");
                    } else if (caracteres[i] == '~') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 13, "Virgulilla");
                    } else if (isDigit(caracteres[i])) {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 14, "Digitos");
                    } else if (caracteres[i] == '*') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 15, "Asterisco");
                    } else if (caracteres[i] == '|') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 17, "Pleca");
                    } else if (caracteres[i] == '.') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 16, "Punto");
                    } else if (caracteres[i] == '+') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 18, "Signo mas");
                    } else if (caracteres[i] == '?') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 19, "Interrogacion");
                    } else if (isSymbol(caracteres[i])) {
                        if (caracteres[i + 1] == '~' || caracteres[i + 1] == ';' || caracteres[i + 1] == ',') {
                            auxLex += String.valueOf(caracteres[i]);
                            addToken(auxLex, 21, "Simbolo");
                        } else {
                            if (caracteres[i] == ' ' || caracteres[i] == '"' || caracteres[i] == '“' || caracteres[i] == '”') {
                                //ignora
                            } else {

                                addError(caracteres[i]);
                            }
                        }

                    } else {
                        if (caracteres[i] == ' ' || caracteres[i] == '"' || caracteres[i] == '“' || caracteres[i] == '”') {

                        } else {
                            System.out.println(caracteres[i]);
                            addError(caracteres[i]);
                        }
                        //Error lexico

                    }

                    break;
                case 1:
                    if (caracteres[i] == '/') {
                        estado = 3;
                        auxLex += String.valueOf(caracteres[i]);
                    } else {
                        auxLex += String.valueOf(caracteres[i]);
                        addError(auxLex);
                    }
                    break;
                case 3:
                    if (caracteres[i] == (char) 10 || caracteres[i] == (char) 13) {
                        addToken(auxLex, 1, "Comentario una linea");
                        estado = 0;
                        auxLex = "";
                        //Ignora por ser comentario
                    } else {
                        auxLex += String.valueOf(caracteres[i]);
                    }
                    break;
                case 4:
                    if (caracteres[i] == '!') {
                        estado = 5;
                        auxLex += String.valueOf(caracteres[i]);
                    } else {
                        auxLex += String.valueOf(caracteres[i]);
                        addError(auxLex);
                    }
                    break;
                case 5:
                    if (caracteres[i] == '!') {
                        estado = 6;
                        auxLex += String.valueOf(caracteres[i]);
                    } else {
                        auxLex += String.valueOf(caracteres[i]);
                    }
                    break;
                case 6:
                    if (caracteres[i] == '>') {
                        //Ignora y resgresa a estado 0
                        addToken(auxLex, 2, "Comentario multilinea");
                        estado = 0;
                        auxLex = "";
                    } else {
                        auxLex += String.valueOf(caracteres[i]);
                        estado = 5;
                    }
                    break;
                case 8:
                    if (isDigit(caracteres[i]) || isLetter(caracteres[i]) || caracteres[i] == '_') {
                        auxLex += String.valueOf(caracteres[i]);
                    } else {
                        if (auxLex.equals("CONJ")) {
                            addToken(auxLex, 20, "Palabra reservada");
                        } else {

                            if (caracteres[i] == '~' || caracteres[i] == ';' || caracteres[i] == ',') {
                                addToken(auxLex, 7, "letra");
                            } else {
                                addToken(auxLex, 5, "Identificadores");
                            }

                        }

                        i--;
                    }
                    break;
                case 9:
                    if (caracteres[i] == '"') {
                        addToken(auxLex, 11, "Cadena");
                        
                        
                    } else {
                        auxLex += String.valueOf(caracteres[i]);
                    }
                    break;
                case 10:
                    if (caracteres[i] == '>') {
                        auxLex += String.valueOf(caracteres[i]);
                        addToken(auxLex, 6, "Flecha");
                    } else {
                        addToken(auxLex, 21, "Simbolo");
                        estado = 0;
                        i--;

                    }

                default:

                //Si no esta ninguno 
            }
        }
        guardarConjuntos();
        guardarER();
        guardarLex();
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private void addToken(String lex, int token, String d) {
        salida.addLast(new Token(lex, token, d));
        auxLex = "";
        estado = 0;
    }

    private void addError(String error) {
        errores.add(new Error(error));
        auxLex = "";
        estado = 0;
    }

    private void addError(char error) {
        errores.add(new Error(String.valueOf(error)));
        auxLex = "";
        estado = 0;
    }

    private boolean isSymbol(char c) {
        return (c >= ' ' && c <= '}') && !(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z');
    }

    public void tablaSimbolos(String name) throws IOException {
        PdfWriter writer = new PdfWriter(name);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Tabla simbolos"));
        Table tabla = new Table(new float[]{4, 4, 4});
        tabla.setWidth(100);
        tabla.addHeaderCell(new Cell().add(new Paragraph("Token")));
        tabla.addHeaderCell(new Cell().add(new Paragraph("Lexema")));
        tabla.addHeaderCell(new Cell().add(new Paragraph("Descripcion")));
        for (int i = 0; i < salida.size(); i++) {
            tabla.addCell(new Cell().add(new Paragraph(Integer.toString(salida.get(i).getToken()))));
            tabla.addCell(new Cell().add(new Paragraph(salida.get(i).getLexema())));
            tabla.addCell(new Cell().add(new Paragraph(salida.get(i).getDesc())));
        }
        document.add(tabla);
        document.close();

    }

    public void tablaErrores(String name) throws IOException {
        System.out.println(errores.size());
        PdfWriter writer = new PdfWriter(name);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.add(new Paragraph("Tabla simbolos"));
        Table tabla = new Table(new float[]{4, 4});
        tabla.setWidth(100);
        tabla.addHeaderCell(new Cell().add(new Paragraph("No.")));
        tabla.addHeaderCell(new Cell().add(new Paragraph("Error")));

        for (int i = 0; i < errores.size(); i++) {
            tabla.addCell(new Cell().add(new Paragraph(Integer.toString(i + 1))));
            tabla.addCell(new Cell().add(new Paragraph(errores.get(i).getError())));
        }
        document.add(tabla);

        document.close();

    }

    public void guardarConjuntos() {
        String nombre = "";
        //Bandeara para saber si esta dentro de un conjunto o no
        boolean dentroC = false;
        //Bandera para saber si es conjunto o macro
        // macro = 0   CONJ: ID -> char ~ char ;
        //conjunto = 1  CONJ: ID -> char,char,char ;
        int tipo = 3;
        boolean ayuda = false;
        Conjuntos temp = null;
        for (int i = 0; i < salida.size(); i++) {
            if (salida.get(i).getToken() == 20) {
                dentroC = true;
                //Determino el tipo
                if (salida.get(i + 5).getToken() == 13) {
                    tipo = 0;
                    ayuda = false;
                } else if (salida.get(i + 5).getToken() == 12) {
                    tipo = 1;
                }

            } else if (salida.get(i).getToken() == 5) {
                nombre = salida.get(i).getLexema();

            }
            if (dentroC) {
                //esta dentro tiene que evaluar entre los dos casos
                if (tipo == 0) {
                    if (salida.get(i).getToken() == 13) {
                        //Si es virgulilla ignora
                        ayuda = true;
                    } else if (salida.get(i).getToken() == 10) { //Si es punto y coma termino de guardar conjuntos
                        dentroC = false;
                    } else {//Debe guardar
                        if (ayuda) {
                            Conjuntos n = new Conjuntos(nombre);
                            n.llenar(salida.get(i - 2).getLexema().charAt(0), salida.get(i).getLexema().charAt(0));
                            conjuntos.add(n);
                            ayuda = false;
                        }
                    }
                } else if (tipo == 1) {

                    if (salida.get(i).getToken() == 12) {
                        //Si es coma ignora
                    } else if (salida.get(i).getToken() == 10) { //Si es punto y coma termino de guardar conjuntos
                        dentroC = false;
                        conjuntos.add(temp);

                    } else if (salida.get(i).getToken() == 6) {
                        ayuda = true;
                        temp = new Conjuntos(nombre);

                    } else {//Debe guardar
                        if (ayuda) {
                            temp.agregar(salida.get(i).getLexema().charAt(0));
                        }
                    }
                }
            }
        }
    }

    private void guardarER() {
        String name = "";
        //Bandera si encontro un id donde si esta la ER
        boolean bandera = false;

        ExpresionesRegulares temp = null;

        for (int i = 0; i < salida.size() - 3; i++) {
            if (salida.get(i).getToken() == 5) {
                if (salida.get(i - 2).getToken() != 20 && salida.get(i + 2).getToken() != 11 && salida.get(i + 3).getToken() != 10 && !bandera) {
                    name = salida.get(i).getLexema();
                    bandera = true;
                    i = i + 2;

                    temp = new ExpresionesRegulares(name);
                }
            }
            if (bandera) {
                if (salida.get(i).getToken() == 10) {
                    temp.setTree(new Arbol(temp.getTokens()));
                    temp.llenarTree();
                    temp.getTree().identificar();
                    temp.getTree().setAnulable();
                    temp.getTree().setPrimUlt();
                    temp.getTree().setSiguientes();
                    temp.getTree().setTransiciones();
                    
                    er.add(temp);

                    bandera = false;

                } else {
                    if (salida.get(i).getToken() == 3 || salida.get(i).getToken() == 4) {
                        //ignora
                    } else {
                        temp.addToken(salida.get(i));
                    }

                }
            }
        }

    }

    private void guardarLex() {
        for (int i = 0; i < salida.size(); i++) {
            if(salida.get(i).getToken()==11 && salida.get(i-1).getToken()==9){
                
                lexemas.add(new Lexema(salida.get(i),salida.get(i-2).getLexema()));
            }
        }
    }

}
