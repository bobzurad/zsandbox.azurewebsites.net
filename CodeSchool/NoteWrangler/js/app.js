angular
  .module('NoteWrangler', ['ngRoute', 'ngResource'])
  .config(['$routeProvider', 'TweetableProvider', 'GravatarProvider',
    function($routeProvider, TweetableProvider, GravatarProvider) {
      'use strict';

      $routeProvider
        .when('/', {
          redirectTo: '/notes'
        })
        .when('/notes', {
          templateUrl: 'templates/pages/notes/index.html',
          controller: 'NotesIndexController'
        })
        .when('/notes/new', {
          templateUrl: 'templates/pages/notes/edit.html',
          controller: 'NotesCreateController',
          controllerAs: 'notesCreateCtrl'
        })
        .when('/users', {
          templateUrl: 'templates/pages/users/index.html'
        })
        .otherwise({
          redirectTo: '/'
        });

        TweetableProvider.setLength(40);
        GravatarProvider.setSize(100);
    }
  ]);
