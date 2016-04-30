angular
  .module('NoteWrangler')
  .directive('nwCard',
    [
      function() {
        return {
          restrict: 'E',
          templateUrl: 'templates/directives/nw-card.html',
          scope: {  //this scope is isolated to the directive, not inherited from the parent element
            header: '=',  //'=' creates a 2 way binding to the parent scope for .header
            body: '=',
            image: '='
          },
          link: function(scope, element, attrs) {
            //this function runs *after* the directive has been compiled and linked to the DOM.
            //can do DOM manipulation here
            element.on("click", function() {
              element("div.card p").toggleClass("hidden");
            });

            if (scope.tweeted) {
              element.addClass('tweeted');
            }

            console.log(attrs.header);
            console.log(attrs.body);
          }
        };
      } //function
    ] //array
  );//directive
