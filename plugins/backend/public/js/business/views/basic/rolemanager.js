/**
 * Created by wjr on 16-12-19.
 */
define(['backbone', 'component', 'bootstrap-treeview'], function(Backbone, Component){

    var prefix = '/backend';

    var AttachTreeView = Backbone.View.extend({
        tpl: '<div id="left"></div><div><input type="button" class="btn" id="submit" value="确认"/></div>',
        events: {
            'click #submit': 'submit'
        },
        submit: function(){
            var checked = this.$('#left').treeview('getChecked');
            var array = [];
            _.each(checked, function(d) {
                array.push(d['id'])
            });
            var nodeIds = array.join(',');
            var roleId = this.initD['id'];
            $.postJSON(prefix + '/approletree/add', {'roleId': roleId, "nodeIds": nodeIds}, function(d) {
                SC.judge(d, function(){
                    SC.Alert('', '保存成功')
                }, function(){
                    SC.Alert('', '保存失败')
                })
            });
            console.log(array.join(","))
        },
        renderTree: function() {
            var self = this ;
            $.postJSON(prefix + '/treeapprolelist', {'appId': self.initD['appId'], 'roleId': this.initD['id']}, function(d){
                self.$('#left').empty();
                self.$('#left').treeview({
                    data: d,
                    showCheckbox: true,
                    multiSelect: true
                });
            });
        },
        initialize: function(d, parent){
            this.initD = d;
            this.component = new Component(this);
            this.$el.append(this.tpl);
            this.renderTree();
        }
    });


    var ModifyView = Backbone.View.extend({
        initialize: function(d, parent) {
            this.d = d || {};
            this.parent = parent;
            this.tabs = parent.tabs;
            this.component = new Component(this);
            this.render();
        },
        render: function(){
            var self = this ;
            this.form = this.component.geneForm(this.formParams(), this.d);
            this.component
                .appendPanel('', this.form.form())
                .appendPanel('', "<div id='tipAttachCode'></div>")
                .build();
            var text = "编码字典： "
            $.postJSON(prefix + '/rolemanager/attachcodes', {}, function(d){
                _.each(d ,function(single) {
                    text += single['name'] + ": " + single['id'] + " \t"
                })
                self.$('#tipAttachCode').text(text)
            })
        },
        submit: function(e) {
            var self = this;
            var json = this.form.serializeJ();
            $.postJSON(prefix + '/rolemanager/add', json, function(d){
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
                    title: '角色名称',
                    name: 'rolename',
                    required: true
                },{
                    title: '关联编码',
                    name: 'attachCode',
                    placeholder: '编码逗号隔开'
                },{
                    title: '角色描述',
                    name: 'description',
                    type: 'textarea'
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

    var role = Backbone.View.extend({
        initialize: function(){
            this.component = new Component(this);
            this.render();
        },
        render: function() {
            this.table = this.component.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.component.geneTab([{
                title: '角色管理',
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
                    url: prefix + '/rolemanager/list',
                    type: 'POST'
                },
                columns: [{
                    title: "角色名称",
                    data: "rolename"
                },{
                    title: '角色描述',
                    data: 'description'
                },{
                    title: "创建时间",
                    data: "createdAt",
                    render: function(d) {
                        return Libs.formatDate(d);
                    }
                },{
                    title: '关联菜单',
                    data: 'id',
                    render: function(d) {
                        var btnAttachTree = '<input type="button" value="关联菜单" class="btn" name="attachTree"/>';
                        return btnAttachTree;
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).find('input[name=attachTree]').on('click', $.proxy(self.attachTree, self, rowData))
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
                    title: '角色名称',
                    name: 'roleName'
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
            SC.Delete(prefix + '/rolemanager/del', d, function(){
                self.reload();
            });
        },
        attachTree: function(d) {
            this.tabs.addTab({
                title: '关联菜单',
                content: new AttachTreeView(d, this).$el
            })
        }
    });
    return role;
});