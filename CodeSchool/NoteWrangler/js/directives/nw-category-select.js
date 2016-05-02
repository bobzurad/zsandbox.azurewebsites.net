angular
  .module('NoteWrangler')
  .directive('nwCategorySelect',
  ['$http', 'Category',
    function($http, Category) {
      'use strict';

      return {
        replace: true,
        restrict: 'E',
        templateUrl: 'templates/directives/nw-category-select.html',
        link: function(scope, elements, attrs) {
          //scope.categories = Category.query();
          scope.categories = $http
            .get('categories.json')
            .success(function(data) {
              scope.categories = data;
            });
        },
        scope: {
          activeCategory: '='
        },
        controller: function($scope) {
          var controller = this;
          controller.getActiveCategory = function() {
            return $scope.activeCategory;
          };
          controller.setActiveCategory = function(category) {
            $scope.activeCategory = category.name;
          };
          return controller;  //for chaining?
        }
      };
    }
  ]);
