angular
  .module('NoteWrangler')
  .directive('title', //this directive will overwrite the default HTML title attribute
    ['$timeout',
      function($timeout) {
        'use strict';

        return {
          restrict: 'A',
          link: function(scope, element) {
            $timeout(function() {
              //here we're using bootstrap's .tooltip() function
              element.tooltip({ container: 'body'});
            });
            scope.$on('$destroy', function() {
              //this function will run whenever the directive is removed and the scope is destroyed
              element.tooltip('destroy');
            });
          }
        };
      }
    ]
  );
