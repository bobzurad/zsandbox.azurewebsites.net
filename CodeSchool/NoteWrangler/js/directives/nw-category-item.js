angular
  .module('NoteWrangler')
  .directive('nwCategoryItem',
  [
    function() {
        'use strict';

        return {
          restrict: 'E',
          templateUrl: 'templates/directives/nw-category-item.html',
          scope: {
            category: '='
          },
          require: '^nwCategorySelect',  //lets nwCategoryItem directive access nwCategorySelect's controller. ^ indicates a parent directive
          link: function(scope, elements, attrs, nwCategorySelectCtrl) {
            scope.makeActive = function() {
              nwCategorySelectCtrl.setActiveCategory(scope.category);
            };
            scope.isCategoryActive = function() {
              return nwCategorySelectCtrl.getActiveCategory() === scope.category.name;
            };
          }
        };
    }
  ]);
