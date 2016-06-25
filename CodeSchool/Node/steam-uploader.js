var fs = require('fs');
var http = require('http');

http.createServer(function(request, response) {
  var uploadedFile = fs.createWriteStream("uploaded-file.txt");
  request.pipe(uploadedFile);

  request.on('end', function() {
    response.end("uploaded!");
  });
}).listen(8080);

console.log("listening on port 8080")

//  curl --upload-file hello.js http://localhost:8080
