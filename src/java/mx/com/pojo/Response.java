package mx.com.pojo;

import java.util.ArrayList;

/**
*
* @author: 
* @design_pattern:  Singleton
* @description:     Class to return a Response object implenets singleton design pattern
*/
public class Response {
    
    private String oRetCod;
    private String oMsg;
    private String oUUID;
    private ArrayList<String> oList;
    private String oSoup;

    public Response() {
    }
    
    public static Response getInstance(String oRetCod, String oMsg){
        return new Response(oRetCod, oMsg);
    }

    private Response(String oRetCod, String oMsg) {
        this.oRetCod = oRetCod;
        this.oMsg = oMsg;
    }
       

    public String getoRetCod() {
        return oRetCod;
    }

    public void setoRetCod(String oRetCod) {
        this.oRetCod = oRetCod;
    }

    public String getoMsg() {
        return oMsg;
    }

    public void setoMsg(String oMsg) {
        this.oMsg = oMsg;
    }

    public String getoUUID() {
        return oUUID;
    }

    public void setoUUID(String oUUID) {
        this.oUUID = oUUID;
    }

    public ArrayList<String> getoList() {
        return oList;
    }

    public void setoList(ArrayList<String> oList) {
        this.oList = oList;
    }

    public String getoSoup() {
        return oSoup;
    }

    public void setoSoup(String oSoup) {
        this.oSoup = oSoup;
    }

    
    
}
