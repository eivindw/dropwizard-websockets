package eivindw.ws;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.junit.Test;

import java.net.URI;

public class WebSocketTester {

   @Test
   public void connect() throws Exception {
      final WebSocketClient client = new WebSocketClient();

      client.start();

      final WebSocketAdapter socket = new WebSocketAdapter() {
         @Override
         public void onWebSocketConnect(Session session) {
            session.getRemote().sendStringByFuture("yo man!");

            session.close();
         }
      };

      client.connect(
         socket,
         URI.create("ws://localhost:8080/ws/"),
         new ClientUpgradeRequest()
      );

      Thread.sleep(100L);

      client.stop();
   }
}
