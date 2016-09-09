define(['backbone', 'component', 'js/business/views/dash'], function(Backbone, Component, Dash){
    var demo = Backbone.View.extend({
        initialize: function(){
            this.$el.append('<div>HELLO</div>');
        },
        render: function(){

        }
    });
    return demo;
});