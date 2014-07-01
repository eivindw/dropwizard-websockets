package eivindw;

import eivindw.api.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.atmosphere.cpr.AtmosphereServlet;

public class TestApp extends Application<Configuration> {

   public static void main(String[] args) throws Exception {
      new TestApp().run(new String[]{"server"});
   }

   @Override
   public void initialize(Bootstrap<Configuration> bootstrap) {
   }

   @Override
   public void run(Configuration conf, Environment env) throws Exception {
      env.jersey().register(HelloResource.class);

      initAtmosphere(env);
   }

   private void initAtmosphere(Environment env) {
      AtmosphereServlet atmosphereServlet = new AtmosphereServlet();

      atmosphereServlet.framework().addInitParameter(
         "com.sun.jersey.config.property.packages",
         "eivindw.api.atmosphere"
      );
      atmosphereServlet.framework().addInitParameter(
         "org.atmosphere.websocket.messageContentType",
         "application/json"
      );

      env.servlets().addServlet("AtmosphereServlet", atmosphereServlet).addMapping("/atm/*");
   }
}
