/**
 * Created by wjr on 16-12-26.
 */
/**
 * Created by wjr on 16-7-20.
 */
define(['backbone', 'component'], function(Backbone, Component, Dash){
    var prefix = "/backend";

    var ModifyView = Backbone.View.extend({
        initialize: function(d, parent) {
            this.d = d || {};
            this.parent = parent;
            this.tabs = parent.tabs;
            this.component = new Component(this);
            this.render();
        },
        render: function(){
            this.form = this.component.geneForm(this.formParams(), this.d);
            this.component
                .appendPanel('', this.form.form())
                .build();
        },
        submit: function(e) {
            var self = this;
            var json = this.form.serializeJ();
            SC.Save(prefix + '/globalconfig/add',json , function(d){
                self.parent.reload();
                self.tabs.closeCurTab();
            });
            return false;
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '配置编码',
                    name: 'keyCode',
                    required: true
                },{
                    title: '配置名称',
                    name: 'cfgName',
                    required: true
                },{
                    title: '配置项类型',
                    name: 'cfgType',
                    type: 'dropdown',
                    data: [{"id":"select","name":"select"},{"id":"text","name":"text"}]
                },{
                    title: '配置值',
                    name: 'value',
                    type: 'textarea',
                    required: true
                },{
                    title: '配置描述',
                    type: 'textarea',
                    name: 'des'
                }],
                btns: [{
                    title: '提交',
                    class: 'btn-primary',
                    type: 'submit',
                    callback: $.proxy(self.submit, self)
                }]
            };
            return formParams;
        }
    });

    var view = Backbone.View.extend({
        tpl: '<div></div>',
        initialize: function(){
            this.component = new Component(this);
            this.render();
        },
        render: function(){
            this.table = this.component.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.component.geneTab([{
                title: '应用管理',
                content: this.component.genePanel(undefined, this.table.geneTable()),
                active: true
            }]);
            this.component
                .appendNative(this.tabs.full())
                .build();
        },
        tableParams: function(){
            var self = this;
            var tableParams = {
                "ajax": {
                    url: prefix + '/globalconfig/list',
                    type: 'POST'
                },
                columns: [{
                    title: "配置编码",
                    data: "keyCode"
                },{
                    title: '配置名称',
                    data: 'cfgName'
                },{
                    title: '配置描述',
                    data: 'des'
                },{
                    title: '操作',
                    data: null,
                    render: function(){
                        var btnModify =  '<input type="button" value="修改" name="modify"/>';
                        var btnDelete =  '<input type="button" value="删除" name="delete"/>';
                        return btnModify + btnDelete;
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).find('input[name=modify]').on('click', $.proxy(self.modify, self, rowData));
                        $(td).find('input[name=delete]').on('click', $.proxy(self.delete, self, rowData));
                    }
                }]
            };
            return tableParams;
        },
        reload: function(){
            this.table.reload();
        },
        modify: function(d) {
            var title = d == undefined ? '新增' : '修改';
            this.tabs.addTab({
                title: title,
                content: new ModifyView(d, this).$el
            })
        },
        delete: function(d) {
            var self = this ;
            SC.Delete(prefix + '/globalconfig/del', d, function(d){
                self.reload();
            });
        },
        searParams: function(){
            var self = this ;
            var searParams = {
                fields:[{
                    title: '配置编码',
                    name: 'keyCode'
                },{
                    title: '配置名称',
                    name: 'cfgName'
                }],
                btns: [{
                    title: '查询',
                    class: 'btn-primary',
                    callback: 'search'
                },{
                    title: '新增',
                    callback: $.proxy(self.modify, self, undefined)
                }]
            };
            return searParams;
        }
    });
    return view;
});