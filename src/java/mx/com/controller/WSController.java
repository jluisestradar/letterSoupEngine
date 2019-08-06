package mx.com.controller;

import com.google.gson.Gson;
import mx.com.business.CreateLetterSoup;
import mx.com.pojo.Request;
import mx.com.pojo.Response;
import mx.com.util.Utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author 
 */
public class WSController {
    
    private static Logger LOG = Logger.getLogger(WSController.class.getCanonicalName());
    
    public static String createSoup(String json){
        String response = null;
        Gson gson = null;
        try {
            gson = new Gson();
            if(json != null){
                response = CreateLetterSoup.createLetterSoup(gson.fromJson(json, Request.class));
            } else {
                response = gson.toJson(Response.getInstance("001", "JSON INVÁLIDO"));
            }
            return response;
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error creating soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        } finally {
            response = null;
            gson = null;
        }
        
    }
    
    public static String getList(String iUUID){
        String response = null;
        Gson gson = null;
        try {
            gson = new Gson();
            if(iUUID != null){
                response = CreateLetterSoup.getList(iUUID);
            } else {
                response = gson.toJson(Response.getInstance("001", "UUID ES REQUERIDO"));
            }
            return response;
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error getting list", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        } finally {
            response = null;
            gson = null;
        }
        
    }
    
    public static String getSoup(String iUUID){
        String response = null;
        Gson gson = null;
        try {
            gson = new Gson();
            if(iUUID != null){
                response = CreateLetterSoup.getSoup(iUUID);
            } else {
                response = gson.toJson(Response.getInstance("001", "UUID ES REQUERIDO"));
            }
            return response;
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error getting soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        } finally {
            response = null;
            gson = null;
        }
        
    }
    
    public static String findWord(String iUUID, String json){
        String response = null;
        Gson gson = null;
        try {
            gson = new Gson();
            if(json != null){
                response = CreateLetterSoup.findWord(iUUID, gson.fromJson(json, Request.class));
            } else {
                response = gson.toJson(Response.getInstance("001", "JSON INVÁLIDO"));
            }
            return response;
        } catch (Exception ex) {
            LOG.log(Level.FATAL, "Error getting soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    }
    
}
