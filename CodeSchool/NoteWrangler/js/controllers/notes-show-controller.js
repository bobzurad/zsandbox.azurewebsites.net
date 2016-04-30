angular
  .module('NoteWrangler')
  .controller('NotesShowController',
    ['$http', '$routeParams', 'Note',
      function($http, $routeParams, Note) {
        'use strict';

        var controller = this;

        controller.note = Note.get({id: $routeParams.id});
      } 
    ]
  );
