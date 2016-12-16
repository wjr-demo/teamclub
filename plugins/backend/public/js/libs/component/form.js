/**
 * Created by wjr on 16-7-6.
 */
define(['jquery','underscore','common', 'zh', 'js/libs/component/puretable'], function($, _, a, b, PureTable) {
    var multiType = [];
    multiType['searchForm'] = {
        'labelStyle': {'padding-top': '7px', 'text-align': 'right'},
        'row' : '<form role="form" class="form-inline" data-toggle="validator">',
        'singleClazz' : '',
        'btnClazz': 'col-md-2'

    };
    var Form = Backbone.Base.extend({
        formEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="col-md-8">\
                <input type="<%= type %>" class="form-control" name="<%= name %>" id="<%= name %>" placeholder="">\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        selectEle: _.template('<div class="form-group" style="min-width: 300px;">\
            <label for="<%= name %>" class="col-md-4 control-label"><%= title %></label>\
            <div class="col-md-8">\
                <select name="<%= name %>" class="form-control innerselect">\
                    <option></option>\
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
        initialize: function (params, initD, addParam) {
            var self = this;
            this.initD = initD || {};
            this.$searchBtn = undefined;
            this.addParam = addParam || {};
            this.factory = multiType[this.addParam['formType']] || {};
            if(this.factory['row'] == undefined) {
                this.$row = $('<form role="form" class="form-horizontal" data-toggle="validator">');
            } else {
                this.$row = $(this.factory['row']);
            }
            var i = 0 ;
            var btnIndex = 0 ;
            var $tmp = $('<div class="row">')
            _.each(params.fields, function(param){
                param['title'] = (param['title'] || '无题') + "：";
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
            var self = this;
            if(self.factory['singleClazz'] !== undefined) {
                var clazz  = self.factory['singleClazz'];
            }else {
                var clazz = 'col-md-6';
            }
            if(param['type'] == 'dropdown'){
                var $formEle = $(this.selectEle(param))
                if(param['dataurl'] != undefined) {
                    var key = param['name']
                    $.postJSON(param['dataurl'], {}, function(d){
                        _.each(d, function(dd){
                            if(self.initD[key] == dd['id']) {
                                var selected = "selected"
                            } else {
                                var selected = "" ;
                            }
                            $formEle.find('.innerselect').append('<option ' + selected + ' value="' + dd['id']  + '">' + dd['name'] + '</option>')
                        });
                    })
                }
            }else if(param['type'] == 'popUp'){
                var $formEle = $(this.popUpEle(param));
                var editCallback = function(v1, v2){
                    $formEle.find('input').attr('val',v1)
                    $formEle.find('input').val(v2)
                }
                var delCallback = function(d) {
                    $formEle.find('input').attr('val', '')
                    $formEle.find('input').val('')
                }
                var viewOption = param['viewOption'] || {}
                _.extend(viewOption, {'editCallback': editCallback})
                $formEle.find('.popEdit').on('click', viewOption ,popFunc)
                $formEle.find('.popDel').on('click', delCallback)
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
            this.$row.serializeJson(this.initD);
            this.$row.validator();
            return $tmp;
        },
        serializeJson: function(d){
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
        data['url'] = '/backend' + '/appmanager/list';
        var editCallback = data['editCallback'];
        var tableParam = {
            "ajax": {
                url: data['url'],  //'/backend' + '/appmanager/list',
                type: 'POST'
            },
            columns: data['columns']
        }
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
            }
            var $full = $('<div>');
            var $form = new Form(formParams, {}, {'formType': 'searchForm'})
            var $table = new PureTable(tableParam, events, $form);
            $full.append($form.form());
            $full.append($table.geneTable());
            return $full.children();
        }
        SC.OpenWin({
            'body': body
        })
    };
    return Form;
});