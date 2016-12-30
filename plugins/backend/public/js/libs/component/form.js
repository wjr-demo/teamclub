/**
 * Created by wjr on 16-7-6.
 * form {
 *
 * }
 */
define(['jquery','underscore','common', 'zh', 'js/libs/component/puretable'], function($, _, a, b, PureTable) {
    var multiType = [];
    multiType['searchForm'] = {
        'labelStyle': {'padding-top': '7px', 'text-align': 'right'},
        'row' : '<form role="form" class="form-inline" data-toggle="validator">',
        'singleClazz' : '',
        'btnClazz': 'col-md-2'

    };

    var convertUrl = function(search) {
        search = search.substring(search.indexOf('?')).substring(1)
        return JSON.parse('{"' + decodeURI(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}')
    };

    // groupClz  divClz
    var Form = Backbone.Base.extend({
        formEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="<%= divClz %>">\
                <input type="<%= type %>" class="form-control" name="<%= name %>" id="<%= name %>" placeholder="">\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        dateTimeEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="<%= divClz %>">\
                <div class="input-group">\
                    <input type="dtime" class="form-control" name="<%= name %>" id="<%= name %>" placeholder="">\
                    <span class="timeRemove input-group-addon"><i class="fa fa-remove"></i></span>\
                </div>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        textAreaEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="col-md-8">\
                <textarea type="textarea" class="form-control" name="<%= name %>" id="<%= name %>" rows="3" placeholder=""　/>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        checkBoxEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="col-md-8">\
                <input type="checkbox" style="height: 34px;" name="<%= name %>" id="<%= name %>" placeholder="">\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        selectEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="col-md-8">\
                <select name="<%= name %>" id="<%= name %>" class="form-control innerselect">\
                    <option value="0"></option>\
                </select>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        popUpEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="col-md-8">\
                <div class="input-group">\
                    <input type="<%= type %>" class="form-control" name="<%= name %>" id="<%= name %>" placeholder="">\
                    <span class="popEdit input-group-addon"><i class="fa fa-pencil fa-fw"></i></span>\
                    <span class="popDel input-group-addon"><i class="fa fa-remove"></i></span>\
                </div>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        btnEle: _.template('<div class="form-group>\
            <input type="button" />\
        </div>'),
        initialize: function (initParams, initD, addParam) {
            var params = $.extend(true, {}, initParams);
            var self = this;
            this.initD = initD || {};
            this.seriFilterArray = [];
            this.$searchBtn = undefined;
            this.addParam = addParam || {};
            this.factory = multiType[this.addParam['formType']] || {};
            if(this.factory['row'] == undefined) {
                this.$row = $('<form role="form" class="form-horizontal" data-toggle="validator">');
            } else {
                this.$row = $(this.factory['row']);
            }
            this.$el = this.$row;
            var i = 0 ;
            var btnIndex = 0 ;
            var $tmp = $('<div class="row">')
            _.each(params.fields, function(param){
                param['title'] = (param['title'] || '无题') + "：";
                param['divClz'] = param['divClz'] || 'col-md-8';
                if(param['required'] == true) {
                    param['title'] += '<span style="color: red; vertical-align: sub;">*</span>'
                }
                param['type'] = param['type'] || 'text';
                $tmp.append((self.geneSingle(i++, param)));
            });
            self.$row.append($tmp);
            if(this.factory == undefined) {
                self.$row.append($('<div class="row" style="height: 20px;">'))
            }
            params.btns = params.btns || [];
            var total = params.btns.length;
            $tmp = $('<div class="row">')
            _.each(params.btns, function(param){
                param['title'] = param.title || '无题';
                param['id'] = param.name ;
                param['clazz'] = param.class;
                $tmp.append(self.geneBtn(total, btnIndex++, param));
            });
            self.$row.append($tmp);
        },
        hideEle: function(name) {
            this.$('input[name='+ name +']').closest('.form-group').hide()
        },
        geneBtn: function(totalRow, i, param){
            if(totalRow == 1){
                var btnClazz = 'col-md-2 col-md-offset-5'
            }else{
                if(i % 2 == 0 ) {
                    var btnClazz = 'col-md-2 col-md-offset-1'
                }else {
                    var btnClazz = 'col-md-2 col-md-offset-3'
                }
            }
            var clazz = this.factory.btnClazz != undefined ? this.factory.btnClazz : btnClazz;
            var $formgroupSuper = $('<div class="form-group"></div>');
            var $formgroupInner = $('<div></div>');
            $formgroupSuper.append($formgroupInner);
            $formgroupInner.addClass(clazz);
            if(param['type'] == 'submit') {
                var $btn = $("<input type='submit' style='width: 80px;'>");
            }else {
                var $btn = $("<input type='button' style='width: 80px;'>");
            }
            if(param['id'] !== undefined) $btn.attr("id", param['id']);
            $btn.val(param['title']);
            $btn.addClass('btn');
            if(param['clazz'] !== undefined){
                $btn.addClass(param['clazz']);
            }else {
                $btn.addClass('btn-default');
            }
            if(param['type'] == 'submit') {
                this.$row.validator().on('submit', function(e) {
                    if (e.isDefaultPrevented()) {
                        return false;
                    }else {
                        param.callback(e);
                        return false;
                    }
                });
            }else {
                if(param.callback !== undefined) {
                    if(param.callback == 'search') {
                        this.$searchBtn = $btn;
                    }else {
                        $btn.on('click', param.callback);
                    }
                }
            }
            $formgroupInner.append($btn);
            return $formgroupSuper;
        },
        geneSingle: function(i, param){
            if(param['el'] != undefined){
                return param['el'];
            }
            var self = this;
            if(self.factory['singleClazz'] !== undefined) {
                var clazz  = self.factory['singleClazz'];
            }else {
                var clazz = param['groupClz'] || 'col-md-6';
            }
            if(param['type'] == 'dropdown'){
                var $formEle = $(this.selectEle(param))
                var key = param['name']
                var mapper = param['mapper'] || {}
                if(param['dataurl'] != undefined) {
                    $.postJSON(param['dataurl'], param['params'], function(d){
                        d = d['data'] || d
                        var idK = mapper['id'] || 'id'
                        var idV = mapper['name'] || 'name'
                        _.each(d, function(dd){
                            if(self.initD[key] == dd[idK]) {
                                var selected = "selected"
                            } else {
                                var selected = "" ;
                            }
                            $formEle.find('.innerselect').append('<option ' + selected + ' value="' + dd[idK]  + '">' + dd[idV] + '</option>')
                        });
                    })
                }
                if(param['data'] != undefined) {
                    _.each(param['data'], function(dd){
                        if(self.initD[key] == dd['id']) {
                            var selected = "selected"
                        } else {
                            var selected = "" ;
                        }
                        $formEle.find('.innerselect').append('<option ' + selected + ' value="' + dd['id']  + '">' + dd['name'] + '</option>')
                    });
                }
            }else if(param['type'] == 'popUp'){
                var $formEle = $(this.popUpEle(param));
                var editCallback = function(v1, v2){
                    $formEle.find('input').attr('val',v1)
                    $formEle.find('input').val(v2)
                }
                var delCallback = function(d) {
                    $formEle.find('input').attr('val', 0)
                    $formEle.find('input').val('')
                }
                var viewOption = param['viewOption'] || {}
                if(this.initD && this.initD[param['name']] != undefined) {
                    this.seriFilterArray.push(param['name']);
                    var key = viewOption['setField'];
                    var value = this.initD[param['name']];
                    var postParam = {}
                    postParam[key] = value;
                    $.postJSON(viewOption['url'], postParam, function(d) {
                        if(d['data'].length < 1 ){
                            console.log(param['name'] + '无渲染')
                            editCallback(0, '')
                            return false ;
                        }else {
                            editCallback(value, d['data'][0][viewOption['showField']])
                        }
                    })
                }
                _.extend(viewOption, {'editCallback': editCallback})
                $formEle.find('.popEdit').on('click', viewOption ,popFunc)
                $formEle.find('.popDel').on('click', delCallback)
            }else if(param['type'] == 'textarea'){
                var $formEle = $(this.textAreaEle(param))
            }else if(param['type'] == 'checkbox') {
                var $formEle = $(this.checkBoxEle(param))
            }else if(param['type'] == 'date'){
                this.seriFilterArray.push(param['name']);
                var $formEle = $(this.dateTimeEle(param));
                var $elInput = $formEle.find('input');
                var $elRemove = $formEle.find('.timeRemove');
                $elRemove.on('click', function(){
                    $elInput.val('');
                    $elInput.attr('val', '0');
                });
                $elInput.on('change', function() {
                    var val = $elInput.val();
                    var timeV = new Date(Date.parse(val)).getTime()
                    $elInput.attr('val', timeV);
                });
                $elInput.attr('readonly', true)
                $elInput.datetimepicker({
                    format: 'yyyy-mm-dd',
                    language: 'zh-CN',
                    minView:'month',
                    maxView:'decade',
                    autoclose: true,
                    todayBtn: true
                });
                if(this.initD && this.initD[param['name']] != undefined) {
                    $elInput.attr('val', this.initD[param['name']])
                    $elInput.val(Libs.formatDateWith(new Date(this.initD[param['name']])))
                }
            }else if(param['type'] == 'datetime'){
                this.seriFilterArray.push(param['name']);
                var $formEle = $(this.dateTimeEle(param));
                var $elInput = $formEle.find('input');
                $elInput.on('change', function() {
                    var val = $elInput.val();
                    var timeV = new Date(Date.parse(val)).getTime()
                    $elInput.attr('val', timeV);
                });
                $elInput.attr('readonly', true)
                $elInput.datetimepicker({
                    format: 'yyyy-mm-dd hh:ii',
                    language: 'zh-CN',
                    minView:'hour',
                    maxView:'decade',
                    autoclose: true,
                    todayBtn: true
                });
                if(this.initD && this.initD[param['name']] != undefined) {
                    $elInput.attr('val', this.initD[param['name']])
                    $elInput.val(Libs.formatDate(new Date(this.initD[param['name']])))
                }
            }else {
                var $formEle = $(this.formEle(param));
            }
            $formEle.addClass(clazz);
            if(param['required'] == true){
                $formEle.find('.form-control').attr('required', true);
                $formEle.find('.form-control').attr('data-error',"请输入")
            }
            if(param['hide'] == true) {
                $formEle.hide()
            }
            if(param['type'] == 'file') {
                this.initFileInput($formEle.find('.form-control'), "/backend/upload")
            }
            $formEle.find('label').css(self.factory['labelStyle'] || {})
            if(param['hideLabel'] == true) {
                $formEle.find('label').hide();
            }
            if(param['callback'] != undefined) {
                var finalV = param['callback'](this.initD[param['name']])
                $formEle.find('input').val(finalV)
                this.seriFilterArray.push(param['name']);
            }
            return $formEle;
        },
        initFileInput: function ($el, uploadUrl) {
            $el.fileinput({
                language: 'zh', //设置语言
                uploadUrl: uploadUrl, //上传的地址
                showUpload: true, //是否显示上传按钮
                showCaption: true,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            });
        },
        form: function(){
            var $tmp = $('<div class="row col-md-12"></div>');
            $tmp.append(this.$row);
            var cloneInitD = _.clone(this.initD);
            //console.log(this.seriFilterArray)
            _.each(this.seriFilterArray, function(d) {
                delete cloneInitD[d];
            });
            this.$row.serializeJson(cloneInitD);
            this.$row.validator();
            return $tmp;
        },
        serializeJ: function(){
            var json = this.$row.serializeJson();
            delete json['undefined'];
            return _.extend(_.clone(this.initD), json);
        },
        searchBtnRegister: function(func) {
            if(this.$searchBtn !== undefined) {
                this.$searchBtn.on('click', func)
            }
        }
    });
    /***
     * e.data //  url, columns , fields, val, text,
     * @param e
     */
    var popFunc = function(e){
        var data = e.data;
        var editCallback = data['editCallback'];
        var tableParam = {
            "ajax": {
                url: data['url'],  //'/backend' + '/appmanager/list',
                type: 'POST'
            },
            columns: data['columns']
        };
        var formParams = {
            fields: data['fields'],
            btns: [{
                title: '查询',
                class: 'btn-primary',
                callback: 'search'
            }]
        };
        var body = function(callbackParams) {
            var events = {};
            events['dblclick'] = function(d){
                var v1 = d[data['setField']];
                var v2 = d[data['showField']];
                if(editCallback != undefined) editCallback(v1, v2);
                if( callbackParams['close'] != undefined){
                    callbackParams['close']();
                }
            };
            var $full = $('<div>');
            var $form = new Form(formParams, {}, {'formType': 'searchForm'})
            var $table = new PureTable(tableParam, events, $form);
            $full.append($form.form());
            $full.append($table.geneTable());
            return $full.children();
        };
        SC.OpenWin({
            'body': body
        })
    };
    return Form;
});