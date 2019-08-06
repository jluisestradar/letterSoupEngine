
package mx.com.webservice.restful;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mx.com.controller.WSController;
import mx.com.util.Utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author 
 */
@Path("alphabetSoup")
public class AlphabetSoupResource {

    private static Logger LOG = Logger.getLogger(AlphabetSoupResource.class.getCanonicalName());
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlphabetSoupResource
     */
    public AlphabetSoupResource() {
    }

    /**
     * Retrieves representation of an instance of mx.com.everis.webservice.restful.AlphabetSoupResource
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getLetterSoup(String json) {
        try{
            return WSController.createSoup(json);
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error creating soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        } 
    }
    
    @GET
    @Path("/list/{iUUID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces("text/plain; charset=UTF-8")
    public String getWordList(@PathParam("iUUID") String iUUID){
        try {
            return WSController.getList(iUUID);
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error getting list", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    } 

    @GET
    @Path("/view/{iUUID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces("text/plain; charset=UTF-8")
    public String getSoup(@PathParam("iUUID") String iUUID){
        try {
            return WSController.getSoup(iUUID);
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error getting soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    } 
    
    
    @PUT
    @Path("/{iUUID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces("text/plain; charset=UTF-8")
    public String findWord(@PathParam("iUUID") String iUUID, String json){
        try {
            return WSController.findWord(iUUID, json);
        } catch (Exception ex){
            LOG.log(Level.FATAL, "Error getting soup", ex);
            return Utils.getCustomMsgError("9999", "OCURRIO UN ERROR INTERNO");
        }
    } 
    
}
