/**
 * Created by wjr on 16-12-19.
 */
/**
 * Created by wjr on 16-12-19.
 */
define(['backbone', 'component'], function(Backbone, Component){

    var prefix = '/backend';

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
                .appendPanel('HAHA', this.form.form())
                .build();
        },
        submit: function(e) {
            var self = this;
            var json = this.form.serializeJson();
            SC.Save(prefix + '/operatormanager/add', json, function(d) {
                self.parent.reload();
                self.tabs.closeCurTab();
            });
            return false;
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '用户名',
                    name: 'username',
                    required: true
                },{
                    title: '真实姓名',
                    name: 'realname'
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
        initialize: function(){
            this.component = new Component(this);
            this.render();
        },
        render: function() {
            this.table = this.component.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.component.geneTab([{
                title: '员工管理',
                content: this.component.genePanel(undefined, this.table.geneTable()),
                active: true
            }]);
            this.component
                .appendNative(this.tabs.full())
                .build();
        },
        tableParams: function() {
            var self = this;
            var tableParams = {
                "ajax": {
                    url: prefix + '/operatormanager/list',
                    type: 'POST'
                },
                columns: [{
                    title: "用户名",
                    data: "username"
                },{
                    title: "真实姓名",
                    data: "realname"
                },{
                    title: '修改密码',
                    data: null,
                    render: function(d){
                        var btn = '<input type="button" class="btn" value="修改密码" name="modifyPwd" />';
                        return btn;
                    },
                    createdCell: function(td, cellData, rowData, row, col){
                        $(td).find('input[name=modifyPwd]').on('click', $.proxy(self.changePwd, self, rowData))
                    }
                },{
                    title: '操作',
                    data: null,
                    render: function(){
                        var btnModify =  '<input type="button" value="修改" class="btn" name="modify"/>';
                        var btnDelete =  '<input type="button" value="删除" class="btn" name="delete"/>';
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
        searParams: function() {
            var self = this ;
            var searParams = {
                fields:[{
                    title: '用户名',
                    name: 'username'
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
            SC.Delete(prefix + '/operatormanager/del', d, function(){
                self.reload();
            });
        },
        changePwd: function(d) {
            var self = this ;
            var params = {};
            params['winform'] = this.component.geneForm(this.formParams(), d);
            params['callback'] = function(){
                self.reload();
            };
            params['url'] = prefix + '/operatormanager/add';
            params['initData'] = d;
            SC.OpenWinForm(params);
        },
        formParams: function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '用户名',
                    name: 'username',
                    required: true
                },{
                    title: '用户密码',
                    name: 'password'
                }]
            };
            return formParams;
        }
    });
    return view;
});