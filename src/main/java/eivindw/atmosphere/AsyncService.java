package eivindw.atmosphere;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedService(path = "/atm/async")
public class AsyncService {

   private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

   @Ready
   public void connected(AtmosphereResource resource) {
      log.info("Connected: {} ({}) - {}",
         resource.uuid(), resource.transport(), resource.getBroadcaster().getID());
   }

   @Disconnect
   public void disconnected(AtmosphereResourceEvent event) {
      log.info("Disconnected: {}", event.getResource().uuid());
   }
}
