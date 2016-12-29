/**
 * Created by wjr on 16-12-19.
 */
define(['backbone', 'component', 'md5'], function(Backbone, Component, md5){
    
    var prefix = '/backend';

    var ModifyView = Backbone.View.extend({
        initialize: function(d, parent, type) {
            this.type = type;
            this.isModify = d != undefined ;
            this.d = d || {};
            this.parent = parent;
            this.tabs = parent.tabs;
            this.component = new Component(this);
            this.render();
        },
        events: {
            'change #nativePlaceProv': 'changeProv'
        },
        render: function(){
            this.form = this.component.geneForm(this.formParams(), this.d);
            this.recordForm = this.component.geneForm(this.recordFormParams(), this.d);
            this.companyAbountForm = this.component.geneForm(this.companyAbountFormParams(), this.d);
            this.component
                .appendPanel('基本信息', this.form.form())
                .appendPanel('档案信息', this.recordForm.form())
                .appendPanel('相关信息', this.companyAbountForm.form())
                .build();
            if(this.type == 'view') {
                this.component.setAsView();
            }
            if(this.isModify) {
                this.form.hideEle('password');
            }
        },
        changeProv: function(){
            var self = this ;
            var nativeProvV = this.$('#nativePlaceProv').val();
            var params = {'parentCode': nativeProvV, 'all': true};
            this.$('#nativePlaceCity').find('option').remove().end().append('<option>')
            $.postJSON(prefix + '/adminareacode/list',params, function(d){
                _.each(d, function(single){
                    self.$('#nativePlaceCity').append('<option value="' + single['id'] +  '">' + single['areaName'] + '</option>')
                })
            })
        },
        submit: function(e) {
            var self = this;
            var json = this.form.serializeJ();
            if(this.isModify) { //修改
                json['password'] = undefined
            }else { //添加
                if(json['password'] != undefined) json['password'] = md5(json['password'])
            }
            var recordFormData = this.recordForm.serializeJ();
            var companyAbountData = this.companyAbountForm.serializeJ();
            json['recordData'] = recordFormData;
            json['companyAbountData'] = companyAbountData;
            SC.Save(prefix + '/operatormanager/add', json, function(d) {
                self.parent.reload();
                self.tabs.closeCurTab();
            });
            return false;
        },
        companyAbountFormParams: function() {
            var self = this ;
            var formParams = {
                fields:[{
                    title: '入职时间',
                    name: 'entryTime',
                    type: 'date'
                },{
                    title: '转正时间',
                    name: 'positiveTime',
                    type: 'date'
                },{
                    title: '预期离职',
                    name: 'expectedLeave',
                    type: 'date'
                },{
                    title: '企业qq帐号',
                    name: 'comQqNum'
                },{
                    title: '企业qq密码',
                    name: 'comQqPasswd'
                },{
                    title: '企业qq权限',
                    name: 'comQqPermit',
                    type: 'textarea'
                },{
                    title: '上网IP',
                    name: 'netIp'
                },{
                    title: '上网速度',
                    name: 'netSpeed'
                },{
                    title: '网络权限',
                    name: 'netPermit',
                    type: 'textarea'
                },{
                    title: '电脑编号',
                    name:'computerNo'
                },{
                    title: '电脑密码',
                    name: 'computerPasswd'
                },{
                    title: '电脑配置',
                    name: 'computerConfig',
                    type: 'textarea'
                },{
                    title: '备注',
                    name: 'remark',
                    type: 'textarea'
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
        recordFormParams: function(){
            var self = this ;
            var formParams = {
                fields:[{
                    title: '身份证号',
                    name: 'identifyNo'
                },{
                    title: '生日',
                    name: 'birthday',
                    type: 'date'
                },{
                    title: '婚姻状态',
                    name: 'marriageStatus',
                    type: 'dropdown',
                    data: [{"id":"1","name":"未婚"},{"id":"2","name":"已婚"}]
                },{
                    title: '教育程度',
                    name: 'educationLevel',
                    type: 'dropdown',
                    data: Libs.Dicts['STUDY_LEVEL']
                },{
                    title: '籍贯-省',
                    name: 'nativePlaceProv',
                    type: 'dropdown',
                    dataurl: prefix + '/adminareacode/list',
                    params: {'parentCode': 0, 'all': true},
                    mapper: {'id': 'id', 'name': 'areaName'}
                },{
                    title: '籍贯-市',
                    name: 'nativePlaceCity',
                    type: 'dropdown',
                    dataurl: prefix + '/adminareacode/list',
                    params: {'parentCode': self.d['nativePlaceProv'], 'all': true},
                    mapper: {'id': 'id', 'name': 'areaName'}
                },{
                    title: '籍贯-详细',
                    name: 'nativePlaceDetail',
                    type: 'textarea'
                },{
                    title: '特长',
                    name: 'strongPoint',
                    type: 'textarea'
                },{
                    title: '亲属姓名',
                    name: 'familyName'
                },{
                    title: '亲属关系',
                    name: 'familyRelation'
                },{
                    title: '亲属号码',
                    name: 'familyPhone'
                }]
            }
            return formParams;
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
                    name: 'roleType',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['ROLELIST'],
                },{
                    title: '所属部门',
                    name: 'deptid',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['DEPTLIST'],
                },{
                    title: '部门管理员',
                    name: 'isDeptAdmin',
                    type: 'checkbox'
                },{
                    title: '系统管理员',
                    name: 'isSysAdmin',
                    type: 'checkbox'
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
                        var btnView =  '<input type="button" value="查看" class="btn" name="view"/>';
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
                    title: '部门',
                    name: 'deptid',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['DEPTLIST']
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
                content: new ModifyView(d, this, 'view').$el
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