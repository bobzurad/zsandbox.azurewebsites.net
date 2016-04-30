angular
  .module('NoteWrangler')
  .provider('Gravatar',
    //providers run before everything else, so they can only inject other providers
    [
      function GravatarProvider() {
        var avatarSize = 80;
        var avatarUrl = "http://www.gravatar.com/avatar/";

        this.setSize = function(size) {
            avatarSize = size;
        };

        //all providers must implement this.$get
        this.$get = function() {
          return {
            generate: function(email) {
              return avatarUrl + CryptoJS.MD5(email) + "?size=" + avatarSize.toString();
            }
          };
        };
      }
    ]
  );
