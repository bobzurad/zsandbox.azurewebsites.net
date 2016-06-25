var fs = require('fs');

var file = fs.createReadStream('hello.js');
var newFile = fs.createWriteStream('hello-copy.js');

file.pipe(newFile);
