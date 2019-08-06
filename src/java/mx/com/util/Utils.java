
package mx.com.util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import mx.com.pojo.Request;
import mx.com.pojo.Response;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author
 */
public class Utils {

    private static Logger LOG = Logger.getLogger(Utils.class.getCanonicalName());

    private static final Integer W_H_DEFAULT = 15;
    private static final Integer WORD_NUM_DEFAULT = 10;
    private static final String[] WORD_UNIVERSE = {"letra", "palabra", "viento", "roca", "dedos", "teclado", "tocar", "torta", "tornado", "diamante",
        "tarro", "fiesta", "cerveza", "vino", "talento", "tarea", "español", "retrato", "diente", "foco",
        "luna", "lapiz", "carcel", "toro", "perro", "gato", "comida", "lentes", "bota", "plato",
        "tenedor", "esfera", "ciencia", "carga", "vaso", "botella", "maestro", "escuela", "espia", "jefe",
        "lider", "clase", "tijeras", "azucar", "cafe", "rojo", "salsa", "hule", "hielo", "negro",
        "hombre", "mujer", "hueso", "tierra", "torre", "avion", "carro", "guerra", "trompa", "guitarra",
        "muerto", "angel", "zapato", "chorro", "frasco", "fresa", "aguacate", "cilantro", "espejo", "sierra",
        "capitan", "barco", "marciano", "martes", "lunes", "viernes", "domingo", "jueves", "jirafa", "mochila",
        "portafolio", "mexico", "america", "aguila", "maquina", "jaguar", "mercado", "pluma", "ganso", "panamericano",
        "estado", "columna", "pizarron", "proyector", "sopa", "platillo", "tiempo", "minero", "compadre", "niño"
    };
    private static final char[] ABECEDARIO = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static char[][] getMatrix(Request request) {
        return new char[(request.getH() != null ? request.getH() : W_H_DEFAULT)][(request.getW() != null ? request.getW() : W_H_DEFAULT)];
    }

    public static char[][] setEmptyMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = ' ';
            }
        }
        return matrix;
    }

    public static String getWord() {
        return WORD_UNIVERSE[new Random().nextInt(WORD_UNIVERSE.length - 1)];
    }

    public static ArrayList<String> getWords(Integer top) {
        ArrayList<String> words = new ArrayList();
        Integer max = (top != null) ? top : WORD_NUM_DEFAULT;
        while (words.size() < max) {
            if (words.isEmpty()) {
                words.add(getWord());
            }
            String wAux = getWord();
            if (!isInArray(words, wAux)) {
                words.add(wAux);
            }

        }
        return words;
    }

    private static boolean isInArray(ArrayList<String> names, String key) {
        boolean isIn = false;
        try {
            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).equals(key)) {
                    isIn = true;
                }
            }
            return isIn;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error finding name", ex);
            return false;
        }
    }

    public static char[][] setTTB(char[][] matrix, String word) {
        boolean isOk = false;
        try {
            while (!isOk) {
                int x = getRanInt((matrix.length - word.length()) - 1);
                int y = getRanInt(matrix[0].length);
                boolean okLen = true;
                int top = x + word.length();
                if (matrix[x][y] == ' ') {
                    for (int i = x; i < top; i++) {
                        if (matrix[i][y] != ' ') {
                            okLen = false;
                        }
                    }
                    if (okLen) {
                        int init = x;
                        for (int i = 0; i < word.length(); i++) {
                            matrix[init][y] = word.charAt(i);
                            init++;
                        }
                        isOk = true;
                    }
                } else {
                    System.out.println("no entro");
                }
            }
            return matrix;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error in setLTR, word: " + word, ex);
            return matrix;
        }

    }

    public static char[][] setBTT(char[][] matrix, String word) {
        boolean isOk = false;
        try {
            while (!isOk) {
                int x = getRanInt((matrix.length - word.length()) - 1);
                int y = getRanInt(matrix[0].length);
                boolean okLen = true;
                int top = x + word.length();
                if (matrix[x][y] == ' ') {
                    for (int i = x; i < top; i++) {
                        if (matrix[i][y] != ' ') {
                            okLen = false;
                        }
                    }
                    if (okLen) {
                        int init = x + word.length();
                        for (int i = 0; i < word.length(); i++) {
                            matrix[init][y] = word.charAt(i);
                            init--;
                        }
                        isOk = true;
                    }
                } else {
                    System.out.println("no entro");
                }
            }
            return matrix;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error in setLTR, word: " + word, ex);
            return matrix;
        }

    }

    public static char[][] setLTR(char[][] matrix, String word) {
        boolean isOk = false;
        try {
            while (!isOk) {
                int x = getRanInt(matrix.length);
                int y = getRanInt((matrix[0].length - word.length()) - 1);
                boolean okLen = true;
                int top = y + word.length();
                if (matrix[x][y] == ' ') {
                    for (int i = y; i < top; i++) {
                        if (matrix[x][i] != ' ') {
                            okLen = false;
                        }
                    }
                    if (okLen) {
                        int init = y;
                        for (int i = 0; i < word.length(); i++) {
                            matrix[x][init] = word.charAt(i);
                            init++;
                        }
                        isOk = true;
                    }
                } else {
                    System.out.println("no entro");
                }
            }
            return matrix;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error in setLTR, word: " + word, ex);
            return matrix;
        }

    }

    public static char[][] setRTL(char[][] matrix, String word) {
        boolean isOk = false;
        try {
            while (!isOk) {
                int x = getRanInt(matrix.length);
                int y = getRanInt((matrix[0].length - word.length()) - 1);
                boolean okLen = true;
                int top = y + word.length();
                if (matrix[x][y] == ' ') {
                    for (int i = y; i < top; i++) {
                        if (matrix[x][i] != ' ') {
                            okLen = false;
                        }
                    }
                    if (okLen) {
                        int init = y + word.length();
                        for (int i = 0; i < word.length(); i++) {
                            matrix[x][init] = word.charAt(i);
                            init--;
                        }
                        isOk = true;
                    }
                } else {
                    System.out.println("no entro");
                }
            }
            return matrix;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error in setLTR, word: " + word, ex);
            return matrix;
        }

    }

    //Diagonal
    public static char[][] setTTB_D_LTR(char[][] matrix, String word) {
        boolean isOk = false;
        try {
            while (!isOk) {
                int x = getRanInt((matrix.length - word.length()) - 1);
                int y = getRanInt((matrix[0].length - word.length()) - 1);
                boolean okLen = true;
                int topx = x + word.length();
                int topy = y + word.length();
                if (matrix[x][y] == ' ') {
                    for (int i = x; i < topx; i++) {
                        for (int j = 0; j < topy; j++) {
                            if (matrix[i][j] != ' ') {
                                okLen = false;
                            }
                        }
                    }
                    if (okLen) {
                        int initx = x;
                        int inity = y;
                        for (int i = 0; i < word.length(); i++) {
                            matrix[initx][inity] = word.charAt(i);
                            initx++;
                            inity++;
                        }
                        isOk = true;
                    }
                } else {
                    System.out.println("no entro");
                }
            }
            return matrix;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error in setLTR, word: " + word, ex);
            return matrix;
        }

    }

    public static int getRanInt(int max) {
        return new Random().nextInt(max);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getCustomMsgError(String oRetCod, String oMsg) {
        return new Gson().toJson(Response.getInstance(oRetCod, oMsg));
    }

    public static char[][] completeMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == ' ') {
                    matrix[i][j] = ABECEDARIO[getRanInt(ABECEDARIO.length - 1)];
                }
            }
        }
        return matrix;
    }

    public static void writeFile(char[][] matrix, String iName, ArrayList<String> words, Integer topW) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("C:/JLER/java_apps/LetterSoupEngine/file/" + iName + ".txt", "UTF-8");

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.print(" " + matrix[i][j] + " ");
                }
                writer.print("\r\n");
            }
            writer.print("---\r\n");

            for (int i = 0; i < topW; i++) {
                writer.print(words.get(i) + "\r\n");
            }

            writer.close();
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error writing file: ", ex);
        } finally {
            writer = null;
        }
    }

    public static ArrayList<String> readListFromFile(String iUUID) {
        ArrayList<String> oList = null;
        BufferedReader br = null;
        String line = null;
        boolean flag = false;
        try {
            br = new BufferedReader(new FileReader("C:/JLER/java_apps/LetterSoupEngine/file/" + iUUID + ".txt"));
            oList = new ArrayList();
            while ((line = br.readLine()) != null) {
                if (flag) {
                    oList.add(line);
                }
                if (line.trim().equals("---")) {
                    flag = true;
                }
            }
            br.close();
            return oList;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error reading file: ", ex);
            return null;
        } finally {
            oList = null;
            br = null;
            line = null;
        }
    }

    public static String readSoupFromFile(String iUUID) {
        BufferedReader br = null;
        String line = null;
        boolean flag = true;
        String response = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("C:/JLER/java_apps/LetterSoupEngine/file/" + iUUID + ".txt"), "UTF8"));
            response = "";
            while ((line = br.readLine()) != null) {
                if (flag) {
                    response += line + "\r\n";
                }
                if (line.trim().equals("---")) {
                    flag = false;
                }
            }
            br.close();
            return response;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error reading file: ", ex);
            return null;
        } finally {
            response = null;
            br = null;
            line = null;
        }
    }

    public static char[][] getMatrixFromFile(String iUUID) {
        ArrayList<String> oList = null;
        BufferedReader br = null;
        String line = null;
        boolean flag = true;
        char matrix[][];
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("C:/JLER/java_apps/LetterSoupEngine/file/" + iUUID + ".txt"), "UTF8"));
            oList = new ArrayList();
            while ((line = br.readLine()) != null) {
                if (flag) {
                    if (!line.trim().equals("---")) {
                        line = line.replaceAll(" ", "");
                        oList.add(line);
                    }
                }
                if (line.trim().equals("---")) {
                    flag = false;
                }
            }

            if (!oList.isEmpty()) {
                matrix = new char[oList.size()][oList.get(0).length()];

            } else {
                matrix = new char[W_H_DEFAULT][W_H_DEFAULT];
            }

            for (int i = 0; i < oList.size(); i++) {
                for (int j = 0; j < oList.get(i).length(); j++) {
                    matrix[i][j] = oList.get(i).charAt(j);
                }
            }

            br.close();
            return matrix;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error reading file: ", ex);
            return null;
        } finally {
            oList = null;
            br = null;
            line = null;
        }
    }

    //Find word in soup
    public static boolean findWordHorizontal(char [][] matrix, ArrayList<String> words, Integer row, Integer sinceCol, Integer endCol) {
        String palabra = "", aux = "";
        boolean find = false;
        
        try {
            //LTR
            for(int i = sinceCol; i <= endCol; i++){
                palabra += matrix[row][i];
            }
            palabra = palabra.toLowerCase();
            for(int i = 0; i < words.size(); i++){
                if(palabra.equals(words.get(i))){
                    find = true;
                }
            }
            //RTL
            for(int i = palabra.length() - 1; i >= 0; i--){
                aux += palabra.charAt(i);
            }
            
            for(int i = 0; i < words.size(); i++){
                if(aux.equals(words.get(i))){
                    find = true;
                }
            }
            return find;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error finding: ", ex);
            return false;
        } finally {

        }
    } 
    
    public static boolean findWordVertical(char [][] matrix, ArrayList<String> words, Integer col, Integer sinceRow, Integer endRow) {
        String palabra = "", aux = "";
        boolean find = false;
        try {
            //TTB
            for(int i = sinceRow; i <= endRow; i++){
                palabra += matrix[i][col];
            }
            palabra = palabra.toLowerCase();
            for(int i = 0; i < words.size(); i++){
                if(palabra.equals(words.get(i))){
                    find = true;
                }
            }
            //BTT
            for(int i = palabra.length() - 1; i >= 0; i--){
                aux += palabra.charAt(i);
            }
            
            for(int i = 0; i < words.size(); i++){
                if(aux.equals(words.get(i))){
                    find = true;
                }
            }
            
            return find;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error finding: ", ex);
            return false;
        } finally {

        }
    } 
    
    public static boolean findWordDiagonal(char [][] matrix, ArrayList<String> words, Integer sinceRow, Integer endRow, Integer sinceCol, Integer endCol) {
        String palabra = "", aux = "";
        boolean find = false;
        Integer max = null;
        try {
            
            max = endRow - sinceRow;
            
            //LTR
            for(int i = 0; i <= max; i++){
                palabra += matrix[sinceRow][sinceCol];
                sinceRow++;
                sinceCol++;
            }
            palabra = palabra.toLowerCase();
            for(int i = 0; i < words.size(); i++){
                if(palabra.equals(words.get(i))){
                    find = true;
                }
            }
            //RTL
            for(int i = palabra.length() - 1; i >= 0; i--){
                aux += palabra.charAt(i);
            }
            
            for(int i = 0; i < words.size(); i++){
                if(aux.equals(words.get(i))){
                    find = true;
                }
            }
            return find;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error finding: ", ex);
            return false;
        } finally {

        }
    } 
    
    
    //Modify
    public static void modifyWordHorizontal(char [][] matrix, ArrayList<String> words, Integer row, Integer sinceCol, Integer endCol, String iUUID) {        
        try {
            //LTR
            for(int i = sinceCol; i <= endCol; i++){
                matrix[row][i] = ((matrix[row][i] + "").toUpperCase()).charAt(0);
            }
            writeFile(matrix, iUUID, words, words.size());            
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error updating soup: ", ex);
        } finally {

        }
    } 
    
    public static void modifyWordVertical(char [][] matrix, ArrayList<String> words, Integer col, Integer sinceRow, Integer endRow, String iUUID) {
        try {
            //TTB
            for(int i = sinceRow; i <= endRow; i++){
                matrix[i][col] = ((matrix[i][col] + "").toUpperCase()).charAt(0);
            }
            writeFile(matrix, iUUID, words, words.size());
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error updating soup: ", ex);
        } finally {

        }
    } 

    
    public static void modifyWordDiagonal(char [][] matrix, ArrayList<String> words, Integer sinceRow, Integer endRow, Integer sinceCol, Integer endCol, String iUUID) {
        String palabra = "", aux = "";
        boolean find = false;
        Integer max = null;
        try {
            
            max = endRow - sinceRow;
            
            //LTR
            for(int i = 0; i <= max; i++){
                matrix[sinceRow][sinceCol] = ((matrix[sinceRow][sinceCol] + "").toUpperCase()).charAt(0);
                sinceRow++;
                sinceCol++;
            }
            
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error updating soup: ", ex);
            
        } finally {

        }
    } 
    
}
