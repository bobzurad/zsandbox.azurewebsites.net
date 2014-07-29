//this module exists to show how we can use private variables, as shown here:
//http://javascript.crockford.com/private.html
define(
  "Foo",
  ["jquery"],
  function($) {
    "use strict";

    //static variable, shared by all instances of Foo
    var staticProp = "defaultValue";

    //this.staticProp = "error"; //will throw an error because "this" is not bound to the context of Foo here

    //constructor
    var Foo = function(params) {
      //keyword "this" is instance of Foo
      var privateProp = params.privateProp;     //cannot be accessed by the Foo prototype
      this.publicProp = params.publicProp;      //can be accessed by the Foo prototype
      staticProp = params.staticProp;           //can be accessed by the Foo prototype, but it's value is shared with all instances of Foo

      var self = this;

      //cannot be accessed by the Foo prototype
      function privateFunction() {
        //"this" is now in context of privateFunction
        //"self" is in context of Foo
        var a = privateProp;
        var b = self.publicProp;
        var c = staticProp;
        var d = self.staticProp;  //will return undefined
        var e = self.privateProp; //will return undefined

        return a + b + c + d + e;
      }

      this.privateFunctionCalledFromConstructor = privateFunction();
    }

    Foo.prototype = {
      //public functions in the Foo prototype
      getPrivateProp: function() {
        return privateProp;   //will throw an error because privateProp is outside of the prototype scope
      },
      getPublicProp: function() {
        return this.publicProp;
      },
      getStaticProp: function() {
        return staticProp;
      },
      privateFunctionCalledFromPrototype: function() {
        return privateFunction(); //will throw an error because privateFunction is outside of the prototype scope
      }
    };

    return Foo;
  }
);
