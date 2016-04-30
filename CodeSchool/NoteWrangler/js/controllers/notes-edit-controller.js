angular
  .module('NoteWrangler')
  .controller('NoteEditController',
    ['$scope', '$routeParams', 'Note',
      function($scope, $routeParams, Note) {
        'use strict';

        var contoller = this;

        controller.note = Note.get({id: $routeParams.id});

        controller.updateNote = function(note) {
            controller.errors = null;
            controller.updating = true;

            note.$update()
              .catch(function(note) {
                controller.errors = [note.data.error];
              })
              .finally(function() {
                controller.updating = false;
              });
        };

        controller.deleteNote = function(note) {
          Note.$delete(note);
        };
      }
    ]
  );
