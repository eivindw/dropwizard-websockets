package eivindw.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import eivindw.ws.BroadcastSocket;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("broadcast")
@Consumes("application/json")
@Produces("application/json")
public class BroadcasterResource {

   private final ObjectMapper objectMapper;

   public BroadcasterResource(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
   }

   @POST
   public void broadcast(Object data) throws Exception {
      BroadcastSocket.broadcast(objectMapper.writeValueAsString(data));
   }
}
