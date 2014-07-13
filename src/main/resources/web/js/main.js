var dwws = dwws || {};

(function (dwws, $) {
   "use strict";

   var $status = $("#status");

   var socket = new WebSocket("ws://localhost:8080/ws/");

   socket.onopen = function() {
      console.log("Connected!");
      socket.send("heihei!");
      $status.html("<p>Connected websocket!</p>");
   };

   socket.onmessage = function(msg) {
      console.log("Gots message", msg);
      $status.prepend("<p>" + msg.data + "</p>");
   };

}(dwws, jQuery));
