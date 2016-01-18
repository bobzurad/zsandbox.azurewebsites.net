angular
  .module('NoteWrangler')
  .directive('nwCard', 
    [
      function() {
        return {
          restrict: 'E',
          templateUrl: 'templates/directives/nw-card.html',
          scope: {
            header: '=',
            description: '='
          },
          link: function(scope, element) {
            if (scope.tweeted) {
              element.addClass('tweeted');
            }
          }
        };
      } //function
    ] //array
  );//directive  
