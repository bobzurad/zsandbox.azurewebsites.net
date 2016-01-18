angular
  .module('NoteWrangler')
  .controller('NotesIndexController',
    ['$scope', 'Note', 'Tweetable',
      function($scope, Note, Tweetable) {
        'use strict';

        $scope.notes = Note.query();

        $scope.tweetThatNote = function(note) {
           new Tweetable(note).success(function(data) {
             console.log(data);
           });
        };

      }//controller function
    ]
  );
