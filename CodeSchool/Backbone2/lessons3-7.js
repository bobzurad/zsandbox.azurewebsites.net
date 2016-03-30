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


//app organization
var AppointmentApp = {
  Collections: {},
  Models: {},
  Views: {}
};
AppointmentApp.Models.Appointment = Backbone.Model.extend({});
AppointmentApp.Collections.Appointments = Backbone.Collection.extend({});
AppointmentApp.Views.Appointment = Backbone.View.extend({});
AppointmentApp.Views.Appointments = Backbone.View.extend({});
AppointmentApp.AppRouter = new (Backbone.Router.extend({}))();


//bootstrapping
var AppointmentApp = new (Backbone.View.extend({
  Collections: {},
  Models: {},
  Views: {},
  events: {
    'click a[data-backbone]': function(e){
      e.preventDefault();
      Backbone.history.navigate(e.target.pathname, { trigger: true });
    }
  },
  start: function(data){
    this.appointments = new AppointmentApp.Collections.Appointments(data.appointments);
    var appointmentsView = new AppointmentApp.Views.Appointments({collection: this.appointments});
    $('#app').html(appointmentsView.render().el);
  }
}))({el: document.body});



//readonly model
App.Models.Appointment = Backbone.Model.extend({
  sync: function(method, model, options){
    if (method === "read" || method === "create") {
      Backbone.sync(method, model, options);
    } else {
      console.error("You cannot " + method + " the Appointment model");
    }
  }
});


//localstorage
App.Models.Appointment = Backbone.Model.extend({
  sync: function(method, model, options){
    options = options || {};

    var key = "Appointment-" + model.id;

    switch(method){
      case "delete":
        localStorage.removeItem(key);
        break;
      case "update":
      case "create":
        localStorage.setItem(key, JSON.stringify(model));
        break;
      case "read":
        var result = localStorage.getItem(key);
        if(result){
          result = JSON.parse(result);
          //options.success && options.success(result);
        }else if(options.error){
          options.error("Couldn't find Appointment with id=" + model.id);
        }
        break;
    }
  }
});
