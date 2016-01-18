angular
  .module('NoteWrangler')
  .controller('NotesShowController', 
    ['$http', '$routeParams',
      function($http, $routeParams) {
        'use strict';
        
        var controller = this;
        
        $http
          .get('/notes/' + $routeParams.id)
          .success(function(data) {
            controller.note = data;
          });
      } 
    ]
  );