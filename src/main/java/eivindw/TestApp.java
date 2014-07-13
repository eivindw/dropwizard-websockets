package eivindw;

import com.bazaarvoice.dropwizard.webjars.WebJarBundle;
import eivindw.api.BroadcasterResource;
import eivindw.ws.BroadcastServlet;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TestApp extends Application<Configuration> {

   public static void main(String[] args) throws Exception {
      new TestApp().run(new String[]{"server"});
   }

   @Override
   public void initialize(Bootstrap<Configuration> bootstrap) {
      bootstrap.addBundle(new WebJarBundle());
      bootstrap.addBundle(new AssetsBundle("/web", "/web", "index.html", "web"));
   }

   @Override
   public void run(Configuration conf, Environment env) throws Exception {
      env.jersey().register(new BroadcasterResource(env.getObjectMapper()));

      env.getApplicationContext().getServletHandler().addServletWithMapping(
         BroadcastServlet.class, "/ws/*"
      );
   }
}
