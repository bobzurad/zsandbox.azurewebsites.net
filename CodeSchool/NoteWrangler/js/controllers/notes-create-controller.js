angular
  .module('NoteWrangler')
  .controller('NotesCreateController', 
    ['$http', 
      function($http) {
        'use strict';
        
        this.saveNote = function(note){
          $http({method: 'POST', url: '/notes', data: note});
        };
      }
    ]
  );
