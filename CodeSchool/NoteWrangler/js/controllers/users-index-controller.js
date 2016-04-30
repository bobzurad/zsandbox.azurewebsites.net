angular
  .module('NoteWrangler')
  .controller('UsersIndexController',
    ['$scope', 'Gravatar',
      function($scope, Gravatar) {
        'use strict';
        
        $scope.gravatarUrl = function(email) {
          return Gravatar.generate(email);
        };
      }
    ]
  );
