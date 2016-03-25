/*
To encode and decode URL parameters:
decodeURIComponent()
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/decodeURIComponent
*/

//rewrite show route to only accept numeric input for the id parameter:
var AppRouter = new (Backbone.Router.extend({
  routes: {
    "appointments/:id":  "show"  //this line can be deleted, this is the old route that allows character
  },
  initialize: function() {
    this.route(/^appointments\/(\d+)$/, 'show');  //this only allows numeric for id param
  },
  show: function(id){
    var appointment = new Appointment({id: id});
    console.log(appointment);
  }
}))();
