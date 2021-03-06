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
            this.tmpOrganNo = this.d['organNo'] || SC.current.organNo;
            if(this.d['username'] != undefined) {
                var idx = this.d['username'].indexOf('@');
                if(idx != -1) {
                    this.d['username'] = this.d['username'].substr(0, idx)
                }
            }
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

            this.departChangeForm = this.component.geneForm(this.departChangeFormParams(), this.d['departChange']);
            this.departChangeMoreForm = this.component.geneForm(this.departChangeMoreFormParams(), this.d['departChange']);

            this.formElOne = this.form.form();
            this.formElTwo = this.recordForm.form();
            this.formElThree = this.companyAbountForm.form();
            var config = {
                'panel': {'margin-bottom': '0px'},
                'panel-body' : {'padding': '0px 10px'},
            };
            var topConfig = {
                'panel': {'margin-bottom': '0px', 'margin-top': '20px'},
                'panel-body' : {'padding': '0px 10px'},
            };
            var panel = this.component
                .appendPanel(undefined, this.formElOne, topConfig)
                .appendPanel(undefined, this.formElTwo, config)
                .appendPanel(undefined, this.formElThree, config);
            if(this.type == 'view') {
                this.formElFour = this.departChangeForm.form();
                this.formElFive = this.departChangeMoreForm.form();
                panel.appendPanel(undefined, this.formElFour, config);
                panel.appendPanel(undefined, this.formElFive, config)
            }
            panel.build();
            if(this.isModify) {
                this.$('input[name=password]').val('');
                //this.$('input[name=password]').attr('disabled','disabled')
            }
            if(this.type == 'view') {
                this.$el.append(new UserDepartChange(this.d, this,  true).$el);
                this.component.setAsView();
            }
        },
        changeProv: function(){
            var self = this ;
            var nativeProvV = this.$('#nativePlaceProv').val();
            var params = {'parentCode': nativeProvV, 'all': true};
            this.$('#nativePlaceCity').find('option').remove().end().append('<option>');
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
                if(json['password'] == undefined || json['password'].trim() == "" ) {
                    json['password'] = undefined;
                }else {
                    json['password'] = md5(json['password']);
                }
            }else { //添加
                if(json['password'] != undefined && json['password'].trim() != "") {
                    json['password'] = md5(json['password'])
                }else {
                    json['password'] = undefined;
                }
            }
            if(json['username'] != undefined && json['username'].trim() != "") {
                json['username'] = json['username'] + '@' + self.tmpOrganNo
            }
            this.formElOne.validator('validate');
            this.formElTwo.validator('validate');
            this.formElThree.validator('validate');
            if(this.$('.has-error').length > 0 ){
                return false;
            }else {
                var recordFormData = this.recordForm.serializeJ();
                var companyAbountData = this.companyAbountForm.serializeJ();
                recordFormData['avatar'] = json['avatar'];
                recordFormData['dorm'] = companyAbountData['dorm'];
                json['recordData'] = recordFormData;
                json['companyAbountData'] = companyAbountData;
                SC.Save(prefix + '/operatormanager/add', json, function(d) {
                    self.parent.reload();
                    self.tabs.closeCurTab();
                });
                return false;
            }
        },
        departChangeMoreFormParams: function(){
            var self = this ;
            var formParams = {
                fields: [{
                    title: '企qq帐号',
                    name: 'comQqNum'
                },{
                    title: '企qq密码',
                    name: 'comQqPasswd'
                },{
                    title: '上网地址',
                    name: 'netIp'
                },{
                    title: '上网速度',
                    name: 'netSpeed'
                },{
                    title: '电脑编号',
                    name:'computerNo'
                },{
                    title: '电脑密码',
                    name: 'computerPasswd'
                },{
                    title: '企qq权限',
                    name: 'comQqPermit',
                    type: 'textarea',
                    formValue: {'width': '796px'}
                },{
                    title: '网络权限',
                    name: 'netPermit',
                    type: 'textarea',
                    formValue: {'width': '796px'}
                },{
                    title: '电脑配置',
                    name: 'computerConfig',
                    type: 'textarea',
                    formValue: {'width': '796px'}
                },{
                    title: Func.convertToFour('备注'),
                    name: 'remark',
                    type: 'textarea',
                    formValue: {'width': '796px'}
                }]
            };
            return formParams;
        },
        departChangeFormParams : function() {
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
                        d == undefined ? d = 0 : d;
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
                    title : Func.convertToFour('宿舍'),
                    name: 'dorm'
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
                }]
            };
            if(this.type != 'view') {
                formParams['btns'] = [{
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
            };
            return formParams;
        },
        formParams : function() {
            var self = this;
            var noAvatar = this.d['avatar'] == undefined || this.d['avatar'].trim() == "";
            if(noAvatar) {
                var avatar = "images/default-avatar.jpg"
            }else {
                var avatar = this.d['avatar']
            }
            var formParams = {
                css: {"position": "relative"},
                fields:[{
                    title: Func.convertToFour('头像'),
                    name: 'avatar',
                    type: 'file'
                },{
                    title: Func.convertToFour('姓名'),
                    name: 'realname',
                    formValue: {'width': '162px'},
                    required: true
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
                    type: 'checkbox',
                    formValue: {'width': '50px'},
                },{
                    title: '部门管理员',
                    name: 'isDeptAdmin',
                    type: 'checkbox',
                    formValue: {'width': '52px'},
                },{
                    title: Func.convertToFour('用户名'),
                    name: 'username',
                    type: 'combineTxt',
                    combineTxt: '@' + self.tmpOrganNo
                },{
                    title: Func.convertToFour('密码'),
                    name: 'password'
                }, {
                    el: '<div style="height: 140px;width: 110px;position: absolute;top: 0px;right: -30px;"><img style="height:auto; width:110px;" src="/assets/' + avatar + '"></div>'
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
            var self = this ;
            this.table = this.component.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.component.geneTab([{
                title: '员工管理',
                content: this.component.genePanel('<div id="quitrate"></div>', this.table.geneTable()),
                active: true
            }]);
            this.component
                .appendNative(this.tabs.full())
                .build();
            this.calcCompanyStatus()
        },
        tableParams: function() {
            var self = this;
            var tableParams = {
                "ajax": {
                    url: prefix + '/operatormanager/list',
                    type: 'POST'
                },
                columnDefs: [{
                    //"targets": [ 5 ],
                    //"visible": SC.current.role.attachCode !=null && SC.current.role.attachCode.includes('EXAMINE') ? true :  false
                }],
                columns: [{
                    title: "真实姓名",
                    data: "realname"
                },{
                    title: '所属部门',
                    render: function(d, x, fd) {
                        return fd['department']['departName'];
                    }
                },{
                    title: '角色',
                    render: function(d, x, fd) {
                        return fd['role']['rolename'];
                    }
                },{
                    title: '操作',
                    data: null,
                    render: function(d){
                        var btnWorkMove =  '<input type="button" value="委派" name="workMove"/>';
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
                        $(td).css({'width': '280px'});
                        if($(td).find('input[name=workMove]') != undefined) {
                            var view = rowData['examineStatus'] == 1 ? true : false;
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
            var self = this;
            d['examineStatus'] = 0;
            d['password'] = undefined;
            SC.Save(prefix + '/operatormanager/add', d, function(d) {
                self.reload()
            });
        },
        examine: function(d) {
            var self = this;
            d['examineStatus'] = 1;
            d['password'] = undefined;
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
                    title: Func.convertToFour('生日'),
                    name: 'seaBirthday',
                    type: 'dropdown',
                    data: [
                        {"id": '1', "name": '1月'},
                        {'id': '2', 'name': '2月'},
                        {'id': '3', 'name': '3月'},
                        {'id': '4', 'name': '4月'},
                        {'id': '5', 'name': '5月'},
                        {'id': '6', 'name': '6月'},
                        {'id': '7', 'name': '7月'},
                        {'id': '8', 'name': '8月'},
                        {'id': '9', 'name': '9月'},
                        {'id': '10', 'name': '10月'},
                        {'id': '11', 'name': '11月'},
                        {'id': '12', 'name': '12月'}
                    ]
                },{
                    title: Func.convertToFour('性别'),
                    name: 'gender',
                    type: 'dropdown',
                    data: [{"id": '1', "name": '男'}, {'id': '2', 'name': '女'}]
                },{
                    title: '教育程度',
                    name: 'educationLevel',
                    type: 'dropdown',
                    data: Libs.Dicts['STUDY_LEVEL']
                },{
                    title: Func.convertToFour('特长'),
                    name: 'strongPoint',
                    type: 'text'
                },{
                    title: '婚姻状态',
                    name: 'marriageStatus',
                    type: 'dropdown',
                    data: [{"id":"1","name":"未婚"},{"id":"2","name":"已婚"}]
                },{
                    title: '在职状态',
                    name: 'workState',
                    type: 'dropdown',
                    data: [{"id":"1", name:"在职"}, {"id": "2", "name": "离职"}, {"id": "3", "name": "未转正"}]
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
                    var companyAboutData = {};
                    companyAboutData['seaBirthday'] = d['seaBirthday'];
                    var recordData = {};
                    recordData['educationLevel'] = d['educationLevel'];
                    recordData['strongPoint'] = d['strongPoint'];
                    recordData['marriageStatus'] = d['marriageStatus'];
                    d['recordData'] = recordData;
                    d['companyAbountData'] = companyAboutData;
                    var searForm = {};
                    searForm['workState'] = d['workState'];
                    d['recordData']['searForm'] = searForm;
                    return d;
                }
            };
            return searParams;
        },
        reload: function(){
            this.table.reload();
            this.calcCompanyStatus()
        },
        calcCompanyStatus : function() {
            var self = this ;
            $.postJSON(prefix + '/operatormanager/calcquitrate', {}, function(d) {
                var full = d['full'];
                var leave = d['leave'];
                var unPositive = d['unPositive'];
                var rate = (Math.round(leave / full * 10000) / 100.00 + "%");
                console.log(rate);
                self.tabs.$full.find('#quitrate').text('当前员工离职率为：' + rate + "  [员工总数为：" + full + " ，未转正人数为：" + unPositive + " ，离职人数为：" + leave + " ]")
            })
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
            cloneD['password'] = undefined;
            params['winform'] = this.component.geneForm(this.formParams(), cloneD);
            params['callback'] = function(){
                self.reload();
            };
            params['url'] = prefix + '/operatormanager/add';
            params['initData'] = d;
            params['prefixDataHandler'] = function(d) { d['password'] = md5(d['password']); return d ;};
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