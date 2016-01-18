angular
  .module('NoteWrangler')
  .factory('Note',
    ['$resource',
      function NoteFactory($resource) {
        'use strict';

        return $resource("/notes", {}, {
          tweetIt: {
            method: 'PUT'
          }
        });
      }
    ]
  );
