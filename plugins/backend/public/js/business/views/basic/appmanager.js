/**
 * Created by wjr on 16-11-21.
 */
define(['backbone', 'component', 'js/business/views/basic/treemanager'], function(Backbone, Component, TreeManagerView){
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
            var json = this.form.serializeJson();
            $.postJSON(prefix + '/appmanager/add', json, function(d){
                SC.judge(d, function(){
                    self.parent.reload();
                    self.tabs.closeCurTab();
                    SC.Alert('', '保存成功');
                }, function(){
                    SC.Alert('', d['message'])
                });
                return false;
            });
            return false;
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '应用编码',
                    name: 'appid',
                    required: true
                },{
                    title: '应用Key',
                    name: 'appkey',
                    required: true
                },{
                    title: '应用名称',
                    name: 'appname',
                    required: true
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

    var platformManager = Backbone.View.extend({
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
        searParams: function() {
            var self = this ;
            var searParams = {
                fields:[{
                    title: '应用编码',
                    name: 'appid'
                },{
                    title: '应用名称',
                    name: 'appname'
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
        tableParams : function(){
            var self = this;
            var tableParams = {
                "ajax": {
                    url: prefix + '/appmanager/list',
                    type: 'POST'
                },
                columns: [{
                    title: "应用编码",
                    data: "appid"
                },{
                    title: '应用Key',
                    data: 'appkey'
                },{
                    title: "应用名称",
                    data: "appname"
                },{
                    title: '是否启用',
                    data: 'enabled',
                    render: function(a, b, data){
                        return '<a class="btn">' + (data['enabled'] == true ? '已启用' : '未启用') + '</a>';
                    },
                    createdCell: function(td,cellData, rowData){
                        $(td).find('a.btn').on('click', $.proxy(self.enable, self, rowData))
                    }
                },{
                    title: '操作',
                    data: null,
                    render: function(){
                        var btnManager =  '<input type="button" value="菜单配置" class="btn" name="treeManager"/>';
                        var btnModify =  '<input type="button" value="修改" class="btn" name="modify"/>';
                        var btnDelete =  '<input type="button" value="删除" class="btn" name="delete"/>';
                        return btnManager + btnModify + btnDelete;
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).find('input[name=treeManager]').on('click', $.proxy(self.treeManager, self, rowData));
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
        modify: function(d, e){
            var title = d == undefined ? '新增' : '修改';
            this.tabs.addTab({
                title: title,
                content: new ModifyView(d, this).$el
            })
        },
        delete: function(d){
            var self = this ;
            SC.Confirm('提示','是否确认删除?', function() {
                $.postJSON(prefix + '/appmanager/del', d, function(respD) {
                    SC.judge(respD, function(){
                        self.reload();
                        SC.Alert('', '删除成功');
                    }, function() {
                        SC.Alert('', respD['message'])
                    })
                })
            })
        },
        enable: function(d) {
            var self = this;
            var txt = d['enabled'] == false ? '启用' : '未启用' ;
            var params = {'id' : d['id']};
            params['enabled'] = !d['enabled'];
            SC.Confirm('提示', '是否修改为' + txt + "?", function() {
                $.postJSON(prefix + '/appmanager/add', params, function(d) {
                    SC.judge(d, function(){
                        self.reload();
                        SC.Alert('', '操作成功');
                    }, function(){
                        SC.Alert('', '操作失败');
                    })
                })
            })
        },
        treeManager: function(d) {
            var self = this;
            this.tabs.addTab({
                title: '菜单配置',
                content: new TreeManagerView(d, this, $.proxy(self.reload, self)).$el
            })
        }
    });
    return platformManager;
});