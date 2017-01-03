/**
 * Created by wjr on 16-12-19.
 */
define(['backbone', 'component', 'md5', 'js/business/views/basic/userdepartchange'], function(Backbone, Component, md5, UserDepartChange){
    
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
            var self = this;
            this.form = this.component.geneForm(this.formParams(), this.d);
            this.recordForm = this.component.geneForm(this.recordFormParams(), this.d);
            this.companyAbountForm = this.component.geneForm(this.companyAbountFormParams(), this.d);
            this.formElOne = this.form.form()
            this.formElTwo = this.recordForm.form()
            this.formElThree = this.companyAbountForm.form()
            var config = {
                'panel': {'margin-bottom': '0px'},
                'panel-body' : {'padding': '0px 10px'},
            }
            var topConfig = {
                'panel': {'margin-bottom': '0px', 'margin-top': '20px'},
                'panel-body' : {'padding': '0px 10px'},
            }
            this.component
                .appendPanel(undefined, this.formElOne, topConfig)
                .appendPanel(undefined, this.formElTwo, config)
                .appendPanel(undefined, this.formElThree, config)
                .build();
            if(this.type == 'view') {
                this.$el.append(new UserDepartChange(this.d, this,  view).$el)
                this.component.setAsView();
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
            this.formElOne.validator('validate')
            this.formElTwo.validator('validate')
            this.formElThree.validator('validate')
            if(this.$('.has-error').length > 0 ){
                return false;
            }else {
                var recordFormData = this.recordForm.serializeJ();
                var companyAbountData = this.companyAbountForm.serializeJ();
                json['recordData'] = recordFormData;
                json['companyAbountData'] = companyAbountData;
                SC.Save(prefix + '/operatormanager/add', json, function(d) {
                    self.parent.reload();
                    self.tabs.closeCurTab();
                });
                return false;
            }
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
                    title: '离职时间',
                    name: 'leaveTime',
                    type: 'date'
                },{
                    title: '奖励记录',
                    name: 'awardRecord',
                    type: 'textarea',
                    formValue: {'width': '796px'},
                },{
                    title: '违纪记录',
                    name: 'breakRuleRecord',
                    type: 'textarea',
                    formValue: {'width': '796px'},
                },{
                    title: Func.convertToFour('备注'),
                    name: 'remark',
                    type: 'textarea',
                    formValue: {'width': '796px'},
                }],
                btns: [{
                    title: '提交',
                    class: '',
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
                    title: Func.convertToFour('生日'),
                    name: 'birthday',
                    type: 'date'
                },{
                    title: '手机号码',
                    name: 'telephone'
                },{
                    title: '教育程度',
                    name: 'educationLevel',
                    type: 'dropdown',
                    data: Libs.Dicts['STUDY_LEVEL']
                },{
                    title: Func.convertToFour('特长'),
                    name: 'strongPoint',
                    type: 'text',
                    formValue: {'width': '499px'},
                },{
                    title: '亲属姓名',
                    name: 'familyName'
                },{
                    title: '亲属关系',
                    name: 'familyRelation'
                },{
                    title: '亲属号码',
                    name: 'familyPhone'
                },{
                    title: Func.convertToFour('籍贯'),
                    name: 'nativePlaceProv',
                    type: 'dropdown',
                    formValue: {'width': '130px'},
                    dataurl: prefix + '/adminareacode/list',
                    params: {'parentCode': 0, 'all': true},
                    mapper: {'id': 'id', 'name': 'areaName'}
                },{
                    title: '',
                    name: 'nativePlaceCity',
                    type: 'dropdown',
                    formValue: {'width': '130px'},
                    dataurl: prefix + '/adminareacode/list',
                    params: {'parentCode': self.d['nativePlaceProv'], 'all': true},
                    mapper: {'id': 'id', 'name': 'areaName'}
                },{
                    title: '',
                    name: 'nativePlaceDetail',
                    formValue: {'width': '500px'},
                    type: 'text'
                }]
            }
            return formParams;
        },
        formParams : function() {
            var self = this;
            if(self.isModify) {
                var formParams = {
                    fields:[{
                        title: Func.convertToFour('姓名'),
                        name: 'realname',
                        formValue: {'width': '162px'},
                    },{
                        title: Func.convertToFour('性别'),
                        name: 'gender',
                        type: 'dropdown',
                        formValue: {'width': '70px'},
                        data: [{"id": '1', "name": '男'}, {'id': '2', 'name': '女'}]
                    },{
                        title: '婚姻状态',
                        name: 'marriageStatus',
                        type: 'dropdown',
                        formValue: {'width': '70px'},
                        formGroup: {'width': '500px'},
                        data: [{"id":"1","name":"未婚"},{"id":"2","name":"已婚"}]
                    },{
                        title: '用&nbsp;&nbsp;户&nbsp;&nbsp;名',
                        name: 'username',
                        required: true
                    },{
                        title: '部门管理员',
                        name: 'isDeptAdmin',
                        type: 'checkbox',
                        formValue: {'width': '90px'},
                    },{
                        title: '系统管理员',
                        name: 'isSysAdmin',
                        type: 'checkbox'
                    }]
                };
            }else {
                var formParams = {
                    fields:[{
                        title: Func.convertToFour('姓名'),
                        name: 'realname',
                        formValue: {'width': '162px'},
                    },{
                        title: Func.convertToFour('性别'),
                        name: 'gender',
                        type: 'dropdown',
                        formValue: {'width': '70px'},
                        data: [{"id": '1', "name": '男'}, {'id': '2', 'name': '女'}]
                    },{
                        title: '婚姻状态',
                        name: 'marriageStatus',
                        type: 'dropdown',
                        formValue: {'width': '70px'},
                        data: [{"id":"1","name":"未婚"},{"id":"2","name":"已婚"}]
                    },{
                        title: '系统管理员',
                        name: 'isSysAdmin',
                        type: 'checkbox'
                    },{
                        title: '用&nbsp;&nbsp;户&nbsp;&nbsp;名',
                        name: 'username',
                        required: true
                    },{
                        title: Func.convertToFour('密码'),
                        name: 'password'
                    },{
                        title: '部门管理员',
                        name: 'isDeptAdmin',
                        type: 'checkbox'
                    }]
                };
            }
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
                columnDefs: [{
                    "targets": [ 4 ],
                    "visible": SC.current.role.attachCode !=null && SC.current.role.attachCode.includes('EXAMINE') ? true :  false
                }],
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
                },{
                    title: '角色',
                    data: 'roleName'
                },{
                    title: '修改密码',
                    data: null,
                    render: function(d){
                        var btn = '<input type="button" value="修改密码" name="modifyPwd" />';
                        return btn;
                    },
                    createdCell: function(td, cellData, rowData, row, col){
                        $(td).find('input[name=modifyPwd]').on('click', $.proxy(self.changePwd, self, rowData))
                    }
                },{
                    title: '操作',
                    data: null,
                    render: function(d){
                        var btnWorkMove =  '<input type="button" value="职务委派" name="workMove"/>';
                        var btnExamine = '<input type="button" value="审核" name="examine"/>';
                        var btnUnExamine = '<input type="button" value="反审核" name="unExamine"/>';
                        var btnView =  '<input type="button" value="查看" name="view"/>';
                        var btnModify =  '<input type="button" value="修改" name="modify"/>';
                        var btnDelete =  '<input type="button" value="删除" name="delete"/>';
                        if(d['examineStatus'] == 1) {
                            if(SC.current.role.attachCode != null && SC.current.role.attachCode.includes('EXAMINE')) {
                                return btnWorkMove + btnUnExamine + btnView;
                            }else {
                                return btnWorkMove + btnView;
                            }
                        }else {
                            if(SC.current.role.attachCode !=null && SC.current.role.attachCode.includes('EXAMINE')) {
                                return btnWorkMove + btnExamine + btnView + btnModify + btnDelete;
                            } else {
                                return btnWorkMove + btnView + btnModify + btnDelete;
                            }
                        }
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        if($(td).find('input[name=workMove]') != undefined) {
                            var view = rowData['examineStatus'] == 1 ? true : false
                            $(td).find('input[name=workMove]').on('click', $.proxy(self.workMove, self, rowData, view));
                        }
                        if($(td).find('input[name=examine]') != undefined) {
                            $(td).find('input[name=examine]').on('click', $.proxy(self.examine, self, rowData));
                        }
                        if($(td).find('input[name=unExamine]') != undefined) {
                            $(td).find('input[name=unExamine]').on('click', $.proxy(self.unExamine, self, rowData));
                        }
                        if($(td).find('input[name=view]') != undefined) {
                            $(td).find('input[name=view]').on('click', $.proxy(self.view, self, rowData));
                        }
                        if($(td).find('input[name=modify]') != undefined) {
                            $(td).find('input[name=modify]').on('click', $.proxy(self.modify, self, rowData));
                        }
                        if($(td).find('input[name=delete]') != undefined) {
                            $(td).find('input[name=delete]').on('click', $.proxy(self.delete, self, rowData));
                        }
                    }
                }]
            };
            return tableParams;
        },
        unExamine: function(d) {
            var self = this
            d['examineStatus'] = 0
            d['password'] = undefined
            SC.Save(prefix + '/operatormanager/add', d, function(d) {
                self.reload()
            });
        },
        examine: function(d) {
            var self = this
            d['examineStatus'] = 1
            d['password'] = undefined
            SC.Save(prefix + '/operatormanager/add', d, function(d) {
                console.log(d);
                self.reload()
            });
        },
        workMove: function(d, view) {
            this.tabs.addTab({
                title: '职务委派',
                content: new UserDepartChange(d, this,  view).$el
            })
        },
        searParams: function() {
            var self = this ;
            var searParams = {
                fields:[{
                    title: Func.convertToFour('姓名'),
                    name: 'realname'
                },{
                    title: Func.convertToFour('部门'),
                    name: 'deptid',
                    type: 'popUp',
                    viewOption: self.component.enumsPopUp['DEPTLIST']
                },{
                    title: '入职时间',
                    name: 'entryTime',
                    type: 'date'
                }],
                btns: [{
                    title: '查询',
                    class: 'btn-primary',
                    callback: 'search'
                },{
                    title: '新增',
                    callback: $.proxy(self.modify, self, undefined)
                }],
                renderSearchData: function(d){
                    if(d['entryTime'] != undefined && d['entryTime'] != '0'){
                        var companyAboutData = {}
                        companyAboutData['entryTime'] = d['entryTime'];
                        d['companyAbountData'] = companyAboutData
                    }
                    if(SC.current.deptAttachCode == Department.FINANCE) {
                        d['examineStatus'] = 1
                    }
                    return d;
                }
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