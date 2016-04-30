angular
  .module('NoteWrangler')
  .controller('NotesCreateController',
    ['$http',
      function($http) {
        'use strict';

        var controller = this;
        
        controller.note = new Note();

        this.saveNote = function(note){
          controller.errors = null;
          controller.updating = true;

          note.$save(note)
            .catch(function(note) {
              controller.errors = [note.data.error];
            })
            .finally(function() {
              controller.updating = false;
            });
        };
      }
    ]
  );
