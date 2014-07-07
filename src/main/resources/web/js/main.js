var dwws = dwws || {};

(function (dwws, $) {
   "use strict";

   var $status = $("#status");

   var request = {
      url: "/atm/async",
      contentType: "application/json",
      transport: "websocket",
      trackMessageLength: true,
      onOpen: function (resp) {
         console.log("connect", resp);
         $status.html("<p>Connected: " + resp.request.uuid + " - " + resp.request.url + " (" + resp.transport + ")</p>");
      },
      onMessage: function (resp) {
         var message = JSON.parse(resp.responseBody);
         console.log("message", message);
         $status.prepend("<p>" + JSON.stringify(message) + "</p>");
      },
      onClose: function (resp) {
         console.log("disconnect", resp);
         $status.prepend("<p>Disconnected: " + resp.reasonPhrase + " (" + resp.transport + ")</p>");
      },
      onError: function (resp) {
         console.log("error", resp);
         $status.prepend("<p>Error: " + resp.reasonPhrase + " (" + resp.transport + ")</p>");
      }
   };

   $.atmosphere.subscribe(request);
}(dwws, jQuery));
