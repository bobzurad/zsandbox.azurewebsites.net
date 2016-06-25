//nodejs docs:
//https://nodejs.org/en/docs/

//to run this at the command line: node hello.js
//to hit the server: curl http://localhost:8080

//hit the sever multiple times within 5 seconds to see that it can accept multiple
//connections while setTimeout is blocking

var http = require("http");

http.createServer(function(request, response) {
  response.writeHead(200);
  response.write("dog is running...");
  setTimeout(function() {
    response.write("dog is done.");
    response.end();
  }, 5000);
}).listen(8080);

console.log("listening on port 8080");
