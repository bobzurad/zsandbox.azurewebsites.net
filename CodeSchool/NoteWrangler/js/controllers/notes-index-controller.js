angular
  .module('NoteWrangler')
  .controller('NotesIndexController',
    ['$scope', '$http', 'Note', 'Tweetable',
      function($scope, $http, Note, Tweetable) {
        'use strict';

        //$scope.notes = Note.query();  //<-- for use with services/note.js

        //use static data instead for now
        $scope.notes = $http
          .get('notes.json')
          .success(function(data) {
            $scope.notes = data;
          });

        $scope.tweetThatNote = function(note) {
           new Tweetable(note).success(function(data) {
             console.log(data);
           });
        };

      }//controller function
    ]
  );
