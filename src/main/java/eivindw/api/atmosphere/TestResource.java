package eivindw.api.atmosphere;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

   private final Logger log = LoggerFactory.getLogger(TestResource.class);

   @Suspend(contentType = MediaType.APPLICATION_JSON)
   @GET
   public String suspend() {
      log.info("suspend!!");
      return "";
   }

   @Broadcast(writeEntity = false)
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   public String broadcast(String message) {
      log.info("broadcast message: {}", message);
      return message.toUpperCase();
   }
}
