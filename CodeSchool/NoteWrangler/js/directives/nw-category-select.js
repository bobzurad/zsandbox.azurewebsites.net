angular
  .module('NoteWrangler')
  .directive('nwCategorySelect',
  ['Category',
    function(Category) {
      'use strict';

      return {
        replace: true,
        restrict: 'E',
        templateUrl: 'templates/directives/nw-category-select.html',
        link: function(scope, elements, attrs) {
          scope.categories = Category.query();
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
