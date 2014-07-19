package eivindw.ws;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BroadcastSocket extends WebSocketAdapter {

   private static Set<Session> sessions = new CopyOnWriteArraySet<>();

   @Override
   public void onWebSocketConnect(Session session) {
      super.onWebSocketConnect(session);
      sessions.add(session);
      System.out.println("Socket Connected: " + session);
   }

   @Override
   public void onWebSocketClose(int statusCode, String reason) {
      sessions.remove(getSession());
      super.onWebSocketClose(statusCode, reason);
      System.out.println("Socket Closed: [" + statusCode + "] " + reason);
   }

   @Override
   public void onWebSocketError(Throwable cause) {
      super.onWebSocketError(cause);
      cause.printStackTrace(System.err);
   }

   public static void broadcast(String msg) {
      sessions.forEach(session -> {
         try {
            session.getRemote().sendString(msg);
         } catch (IOException e) {
            System.err.println("Problem broadcasting message: " + e.getMessage());
            e.printStackTrace();
         }
      });
   }
}
