/**
 * Created by wjr on 16-11-21.
 */
define(['backbone', 'component'], function(Backbone, Component, Dash){
    var demo = Backbone.View.extend({
        initialize: function(){
            this.$el.append('<div>HELLO</div>');
        },
        render: function(){
        }
    });
    return demo;
});