angular
  .module('NoteWrangler')
  .factory('Category',
    ['$resource',
      function($resource) {
        'use strict';

        return $resource("/categories", {}, {});
      }
    ]
  );
