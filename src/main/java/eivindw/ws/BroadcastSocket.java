package eivindw.ws;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;

public class BroadcastSocket extends WebSocketAdapter {

   @Override
   public void onWebSocketConnect(Session sess) {
      super.onWebSocketConnect(sess);
      System.out.println("Socket Connected: " + sess);
   }

   @Override
   public void onWebSocketText(String message) {
      super.onWebSocketText(message);
      System.out.println("Received TEXT message: " + message);
      try {
         getSession().getRemote().sendString("yo dawg!");
      } catch (IOException e) {
         System.err.println("Can't send message");
         e.printStackTrace();
      }
   }

   @Override
   public void onWebSocketClose(int statusCode, String reason) {
      super.onWebSocketClose(statusCode, reason);
      System.out.println("Socket Closed: [" + statusCode + "] " + reason);
   }

   @Override
   public void onWebSocketError(Throwable cause) {
      super.onWebSocketError(cause);
      cause.printStackTrace(System.err);
   }
}
