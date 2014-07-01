package eivindw.api;

import com.google.common.collect.ImmutableMap;
import org.atmosphere.cpr.MetaBroadcaster;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("hello")
@Produces("application/json")
public class HelloResource {

   @GET
   public Map helloWorld() {
      MetaBroadcaster.getDefault().broadcastTo("/", "yo");
      return ImmutableMap.of("message", "Hello World!");
   }
}
