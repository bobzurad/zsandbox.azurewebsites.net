angular
  .module('NoteWrangler')
  .factory('Note',
    ['$resource',
      function NoteFactory($resource) {
        'use strict';

        return $resource("/notes/:id", {}, {
          tweetIt: {
            method: 'PUT'
          },
          update: {
            method: 'PUT'
          }
        });
      }
    ]
  );
