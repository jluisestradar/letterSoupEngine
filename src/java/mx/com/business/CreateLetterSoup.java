package mx.com.business;

import com.google.gson.Gson;
import java.util.ArrayList;
import mx.com.pojo.Request;
import mx.com.pojo.Response;
import mx.com.util.Utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author
 */
public class CreateLetterSoup {

    private static Logger LOG = Logger.getLogger(CreateLetterSoup.class.getCanonicalName());

    public static String createLetterSoup(Request request) {
        String uuid = null;
        ArrayList<String> words = null;
        Integer topW = 5;
        Response response = null;
        Gson gson = null;
        try {
            gson = new Gson();
            uuid = Utils.getUUID();
            char[][] matrix = Utils.getMatrix(request);
            matrix = Utils.setEmptyMatrix(matrix);
            words = Utils.getWords(null);

            for (int i = 0; i < topW; i++) {
                matrix = setWord(Utils.getRanInt(4) + 1, matrix, words.get(i));
            }
            matrix = Utils.completeMatrix(matrix);

            Utils.writeFile(matrix, uuid, words, topW);
            response = Response.getInstance("000", "PROCESO SATISFACTORIO");
            response.setoUUID(uuid);
            return gson.toJson(response);
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error creating soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        } finally {
            uuid = null;
            words = null;
            topW = 5;
            response = null;
            gson = null;
        }
    }

    private static char[][] setWord(Integer random, char[][] matrix, String word) {
        switch (random) {
            case 1:
                matrix = Utils.setTTB(matrix, word);
                break;
            case 2:
                matrix = Utils.setBTT(matrix, word);
                break;
            case 3:
                matrix = Utils.setLTR(matrix, word);
                break;
            case 4:
                matrix = Utils.setRTL(matrix, word);
                break;
            case 5:
                matrix = Utils.setTTB_D_LTR(matrix, word);
                break;
        }
        return matrix;
    }
    
    public static String getList(String iUUID){
        Response response = null;
        Gson gson = null;
        ArrayList<String> oList = null;
        try {
            gson = new Gson();
            oList = Utils.readListFromFile(iUUID);
            if(oList != null){
                response = Response.getInstance("000", "PROCESO SATISFACTORIIO");
                response.setoList(oList);
            } else {
                response = Response.getInstance("002", "OCURRIO UN ERROR AL LEER LA LISTA, UUID NO ENCONTRADO");
            }
            return gson.toJson(response);
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error creating soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    }

    public static String getSoup(String iUUID){
        Response response = null;
        Gson gson = null;
        String res = null;
        try {
            gson = new Gson();
            res = Utils.readSoupFromFile(iUUID);
            if(res != null){
                return res;
            } else {
                response = Response.getInstance("002", "OCURRIO UN ERROR AL LEER LA LISTA, UUID NO ENCONTRADO");
                return gson.toJson(response);
            }
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error getting soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    }
    
    
    public static String findWord(String iUUID, Request request){
        char [][] matrix;
        ArrayList<String> words = null;
        Response response = null;
        Gson gson = null;
        boolean find = false;
        try {
            matrix = Utils.getMatrixFromFile(iUUID);
            words = Utils.readListFromFile(iUUID);
            gson = new Gson();
            
            if(request.getSr() == request.getEr()){
                find = Utils.findWordHorizontal(matrix, words, request.getSr(), request.getSc(), request.getEc());
                if(find){
                    Utils.modifyWordHorizontal(matrix, words, request.getSr(), request.getSc(), request.getEc(), iUUID);
                }
                
            } else if(request.getSc() == request.getEc()) {
                find = Utils.findWordVertical(matrix, words, request.getSc(), request.getSr(), request.getEr());
                if(find){
                    Utils.modifyWordVertical(matrix, words, request.getSc(), request.getSr(), request.getEr(), iUUID);
                }
            } else {
                find = Utils.findWordDiagonal(matrix, words, request.getSr(), request.getEr(), request.getSc(), request.getEc());
                if(find){
                    Utils.modifyWordDiagonal(matrix, words, request.getSr(), request.getEr(), request.getSc(), request.getEc(), iUUID);
                }
            }
            
            if(find){
                response = Response.getInstance("000", "PALABRA ENCONTRADA");
            } else {
                response = Response.getInstance("010", "PALABRA NO ENCONTRADA");
            }
            return gson.toJson(response);
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error finding: ", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    }
    
}
