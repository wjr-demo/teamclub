/**
 * Created by wjr on 16-12-19.
 */
define(['backbone', 'component', 'md5'], function(Backbone, Component, md5){

    var prefix = '/backend';

    var EntityView = Backbone.view.extend({
        initialize: function(d, parent) {
            this.d = d || {};
        }
    })

    var ModifyView = Backbone.View.extend({
        initialize: function(d, parent) {
            this.isModify = d != undefined ;
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
            if(this.isModify) {
                this.form.hideEle('password');
            }
        },
        submit: function(e) {
            var self = this;
            var json = this.form.serializeJ();
            if(this.isModify) { //修改
                json['password'] = undefined
            }else { //添加
                if(json['password'] != undefined) json['password'] = md5(json['password'])
            }
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
                },{
                    title: '密码',
                    name: 'password'
                },{
                    title: '角色',
                    name: 'roletype',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['ROLELIST'],
                },{
                    title: '部门管理员',
                    name: 'isDeptAdmin',
                    type: 'checkbox'
                },{
                    title: '系统管理员',
                    name: 'isSysAdmin',
                    type: 'checkbox'
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
                    data: "username",
                    render: function(d, x, fd) {
                        return fd['isSysAdmin'] ? d + "(系统管理员)" : d ;
                    }
                },{
                    title: "真实姓名",
                    data: "realname"
                },{
                    title: '所属部门',
                    data: 'deptName',
                    render: function(d, x, fd) {
                        return fd['isDeptAdmin'] ? d + "(部门管理员)" : d
                    }
                },{
                    title: '角色',
                    data: 'roleName'
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
                        var btnView   =  '<input type="button" value="查看" class="btn" name="view"  />';
                        var btnModify =  '<input type="button" value="修改" class="btn" name="modify"/>';
                        var btnDelete =  '<input type="button" value="删除" class="btn" name="delete"/>';
                        return btnView + btnModify + btnDelete;
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).find('input[name=view]').on('click', $.proxy(self.view, self, rowData));
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
                },{
                    title: '所属部门',
                    name: 'deptid',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['ROLELIST']
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
        view: function(d) {
            this.tabs.addTab({
                title: '查看',
                content: new EntityView(d, this).$el
            })
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
            var cloneD = _.clone(d);
            cloneD['password'] = undefined
            params['winform'] = this.component.geneForm(this.formParams(), cloneD);
            params['callback'] = function(){
                self.reload();
            };
            params['url'] = prefix + '/operatormanager/add';
            params['initData'] = d;
            params['prefixDataHandler'] = function(d) { d['password'] = md5(d['password']); return d ;}
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