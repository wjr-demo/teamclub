/**
 * Created by wjr on 16-7-20.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var app = Backbone.View.extend({
        events: {
            'click #btn': 'showClick'
        },
        showClick: function(){
            this.tabs.closeCurTab();
        },
        initialize: function(d, parent){
            this.d = d || {};
            this.parent = parent;
            this.tabs = this.parent.tabs;
            this.component = new Component(this);
            this.form = undefined;
            this.render();
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '编号',
                    name: 'no'
                },{
                    title: '名称',
                    name: 'name'
                },{
                    title: '地区码',
                    name: 'areacode'
                },{
                    title: '地址',
                    name: 'address'
                }],
                btns: [{
                    title: '提交',
                    class: 'btn-primary',
                    callback: $.proxy(self.submit, self)
                }]
            };
            return formParams;
        },
        submit: function(){
            var self = this;
            var json = this.form.serializeJson();
            $.postJSON('/backend/bank/modify', json, function(d){
                self.parent.reload();
                self.tabs.closeCurTab();
                return false;
            });
        },
        render: function(){
            this.form = this.component.geneForm(this.formParams(), this.d);
            this.component
                .appendPanel('HAHA', this.form.form())
                .build();
        }
    });
    return app;
});