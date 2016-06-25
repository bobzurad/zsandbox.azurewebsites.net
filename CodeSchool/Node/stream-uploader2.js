var fs = require('fs');
var http = require('http');

http.createServer(function(request, response) {
  var uploadedFile = fs.createWriteStream('uploaded-file2.txt');
  var fileBytes = request.headers['content-length'];
  var uploadedBytes = 0;

  request.on('readable', function() {
    var chunk = null;
    while(null !== (chunk = request.read())) {
      uploadedBytes += chunk.length;
      var progress = (uploadedBytes / fileBytes) * 100;
      response.write("progress:" + parseInt(progress, 10) + "%\n");
    }
  });

  request.pipe(uploadedFile);
}).listen(8080);

console.log("listening on port 8080...");
