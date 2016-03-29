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

//escaping user content, to protect from XSS attacks
var AppointmentView = Backbone.View.extend({
  template: _.template("<span><%= model.escape('title') %></span>"),
  render: function(){
    this.$el.html(this.template({model: this.model}));
  }
});

//redirect after save
var AppointmentForm = Backbone.View.extend({
  template: _.template('<form><input name="title" type="text" value="<%= title %>" /><input name="name" type="text" value="<%= name %>" /></form>'),
  render: function(){
    this.$el.html(this.template(this.model.attributes));
    return this;
  },
  events: {
    submit: "save"
  },
  save: function(e){
    e.preventDefault();
    var newTitle = this.$('input[name=title]').val();
    var newName = this.$('input[name=name]').val();
    this.model.save({title: newTitle, name: newName}, {
      success: function(model, response, options) {
        Backbone.history.navigate('', { trigger: true });
      }
    });
  }
});
