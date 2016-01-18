angular
  .module('NoteWrangler')
  .provider('Tweetable',
    [
      function TweetableProvider() {
        'use strict';
        var characterLength = 140;

        this.setLength = function(length) {
          characterLength = length;
        };

        this.$get = function($http) {
          return function(potentialTweet) {
            return $http({
              method: 'POST',
              url: 'http://gentle-spire-1153.herokuapp.com/tweet',
              data: {
                description: potentialTweet,
                maxLength: characterLength
              }
            });
          };
        };
      }
    ]
  );
