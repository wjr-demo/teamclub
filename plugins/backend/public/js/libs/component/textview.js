/**
 * Created by wjr on 16-12-27.
 */
define(['jquery','underscore','common'], function($, _) {
    var textview = Backbone.Base.extend({
        textEle: _.template('<div class="form-group col-md-5" style="min-width: 300px;">\
            <label class="col-md-4 control-label"><%= k %></label>\
            <div class="col-md-8">\
                <div class="form-control" ><%= v %></div>\
            </div>\
        </div>'),
        initialize: function(fields, data) {
            this.fields = fields;
            this.initD = data;
            this.render();
        },
        render: function() {
            var self = this ;
            this.$el = $('<form role="form" class="form-horizontal" data-toggle="validator">');
            var array = [];
            _.each(this.fields, function(v, k){
                var title = v['title'] + "：";
                var value = self.initD[v['field']] || '';
                array.push(self.textEle({"k": title, "v": value}))
            });
            var i = 0 ;
            _.each(array, function(single) {
                self.$el.append(single)
            })
        }
    });
    return textview;
});