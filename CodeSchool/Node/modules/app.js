//note: you can't require modules were installed with npm install -g
//      you can require local files and files in the local node_modules folder
//        if you're requiring local files in node_modules, you don't need to start with ./
//      ./ denotes local files, not in node_modules

var hello = require('./hello-module');
var gb = require('./goodbye-module');
var makeRequest = require('./makeRequest-module');

hello();
gb.goodbye();

require('./goodbye-module').goodbye();

makeRequest("you say goodbye, I say hello");
