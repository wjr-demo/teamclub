/**
 * Created by Administrator on 2016/12/26.
 */
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
            SC.Save(prefix + '/appdepartment/add',json , function(d){
                self.parent.reload();
                self.tabs.closeCurTab();
            });
            return false;
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '部门名称',
                    name: 'departName',
                    required: true
                },{
                    title: '关联编码',
                    name: 'attachCode',
                    type: 'dropdown',
                    dataurl: prefix + '/appdepartment/keycode'
                },{
                    title: '部门描述',
                    name: 'departDesc',
                    type: 'textarea',
                    formValue: {'width': '500px'}
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
                    url: prefix + '/appdepartment/list',
                    type: 'POST'
                },
                columns: [{
                    title: "部门名称",
                    data: "departName"
                },{
                    title: '关联编码',
                    data: 'attachCode'
                },{
                    title: '部门描述',
                    data: 'departDesc'
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
            SC.Delete(prefix + '/appdepartment/del', d, function(d){
                self.reload();
            });
        },
        searParams: function(){
            var self = this ;
            var searParams = {
                fields:[{
                    title: '部门名称',
                    name: 'departName'
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