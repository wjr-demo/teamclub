/**
 * Created by wjr on 16-12-29.
 */
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
    var prefix = "/backend"

    var ModifyView = Backbone.View.extend({
        initialize: function(d, parent, type) {
            this.type = type ;
            this.d = d || {};
            this.parent = parent;
            this.tabs = parent.tabs;
            this.component = new Component(this);
            this.render();
        },
        render: function(){
            var config = {
                'panel': {'margin-bottom': '0px'},
                'panel-body' : {'padding': '0px 10px'},
            }
            var topConfig = {
                'panel': {'margin-bottom': '0px', 'margin-top': '20px'},
                'panel-body' : {'padding': '0px 10px'},
            }

            this.form = this.component.geneForm(this.formParams(), this.d);
            this.moreForm = this.component.geneForm(this.moreFormParams(), this.d)
            this.formOne = this.form.form()
            this.formTwo = this.moreForm.form()
            this.component
                .appendPanel(undefined, this.formOne, topConfig)
                .appendPanel(undefined, this.formTwo, config)
                .build();
            if(this.type == 'view') {
                this.component.setAsView();
            }
        },
        submit: function(e) {
            var self = this;
            this.form.validator('validate')
            this.moreForm.validator('validate')
            if(this.$('.has-error').length > 0 ){
                return false;
            }else {
                var json = this.form.serializeJ();
                json['wages']  != undefined ? json['wages'] = parseFloat(json['wages']).mul(100) : ""
                var moreD = this.moreForm.serializeJ();
                json['userDepartChangeAnother'] = moreD;
                SC.Save(prefix + '/userdepartchange/add',json , function(d){
                    self.parent.reload();
                    self.tabs.closeCurTab();
                });
                return false;
            }
        },
        moreFormParams: function(){
            var self = this ;
            var formParams = {
                fields: [{
                    title: '企qq帐号',
                    name: 'comQqNum'
                },{
                    title: '企qq密码',
                    name: 'comQqPasswd'
                },{
                    title: '企qq权限',
                    name: 'comQqPermit',
                    type: 'text'
                },{
                    title: '上网地址',
                    name: 'netIp'
                },{
                    title: '上网速度',
                    name: 'netSpeed'
                },{
                    title: '网络权限',
                    name: 'netPermit',
                    type: 'text'
                },{
                    title: '电脑编号',
                    name:'computerNo'
                },{
                    title: '电脑密码',
                    name: 'computerPasswd'
                },{
                    title: '电脑配置',
                    name: 'computerConfig',
                    type: 'textarea',
                    formValue: {'width': '796px'},
                },{
                    title: '备注',
                    name: 'remark',
                    type: 'textarea',
                    formValue: {'width': '796px'}
                }],
                btns: [{
                    title: '提交',
                    class: 'btn-primary',
                    type: 'submit',
                    callback: $.proxy(self.submit, self)
                }]
            }
            return formParams;
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: '所属部门',
                    name: 'departId',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['DEPTLIST'],
                    required: true
                },{
                    title: '所属角色',
                    name: 'roleId',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['ROLELIST'],
                    required: true
                },{
                    title: '公司座机',
                    name: 'phone'
                },{
                    title: '综合工资',
                    name: 'wages',
                    callback: function(d) {
                        d == undefined ? d = 0 : d
                        return d.div(100)
                    }
                },{
                    title: '计算方式',
                    name: 'calcuStyle',
                    type: 'dropdown',
                    data: window.globalCfgDict.getTypeAll('USER_PAY_STYLE')
                }]
            };
            return formParams;
        }
    });

    var view = Backbone.View.extend({
        tpl: '<div></div>',
        initialize: function(d, parent, view){
            this.view = view ;
            if(d !== undefined) {
                this.initD = {'userId': d['id']}
            }
            this.parent = parent;
            this.component = new Component(this);
            this.render();
        },
        render: function(){
            this.table = this.component.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.component.geneTab([{
                title: '职务变动记录',
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
                    url: prefix + '/userdepartchange/list',
                    type: 'POST',
                    extendData: {'userId': self.initD['userId']}
                },
                columns: [{
                    title: "变动日期",
                    data: "changeDate",
                    render: function(d) {
                        return Libs.formatDate(d);
                    }
                },{
                    title: '所属部门',
                    data: 'departName'
                },{
                    title: '所属角色',
                    data: 'roleName'
                },{
                    title: '公司座机',
                    data: 'phone'
                },{
                    title: '综合工资',
                    data: 'wages',
                    render: function(d){
                        return new Func.NumUtils(d.div(100)).getValue()
                    }
                },{
                    title: '计算方式',
                    data: 'calcuStyleName'
                },{
                    title: '操作',
                    data: null,
                    render: function(){
                        var btnView =  '<input type="button" class="view" value="查看" name="view"/>';
                        return btnView ;
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).find('input[name=view]').on('click', $.proxy(self.modify, self, rowData, 'view'));
                    }
                }]
            };
            return tableParams;
        },
        reload: function(){
            this.table.reload();
            if(this.parent != undefined) {
                this.parent.reload();
            }
        },
        modify: function(d, type) {
            var title = d == undefined ? '编辑' : '编辑';
            title = type == 'view' ? '查看': title
            this.tabs.addTab({
                title: title,
                content: new ModifyView(d, this, type).$el
            })
        },
        delete: function(d) {
            var self = this ;
            SC.Delete(prefix + '/userdepartchange/del', d, function(d){
                self.reload();
            });
        },
        searParams: function(){
            var self = this ;
            if(this.view == true) {
                var searParams = {}
            } else {
                var searParams = {
                    btns: [{
                        title: '新增',
                        callback: $.proxy(self.modify, self, self.initD)
                    }]
                };
            }
            return searParams;
        }
    });
    return view;
});