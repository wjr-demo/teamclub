/**
 * Created by wjr on 16-7-6.
 * form {
 *
 * }
 */
define(['jquery','underscore','common', 'zh', 'js/libs/component/puretable'], function($, _, a, b, PureTable) {
    var multiType = [];
    multiType['searchForm'] = {
        'labelStyle' : {"text-align": "-webkit-center"},
        'row' : '<form role="form" class="form-inline" data-toggle="validator">',
        'singleClazz' : '',
        'btnClazz': 'col-md-2',
        'withHideBtn': true

    };

    var convertUrl = function(search) {
        search = search.substring(search.indexOf('?')).substring(1)
        return JSON.parse('{"' + decodeURI(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}')
    };

    // groupClz  divClz
    var Form = Backbone.Base.extend({
        formEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <input type="<%= type %>" class="form-control" name="<%= name %>" id="<%= name %>" placeholder="<%= placeholder %>">\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        multipleCheckBoxEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
            </div>\
        </div>'),
        combineEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <div class="input-group">\
                    <input type="text" class="form-control" style="background-color: #FFF" name="<%= name %>" id="<%= name %>" placeholder="">\
                    <span class="input-group-addon"><%= combineTxt %></span>\
                </div>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        fileEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <div class="input-group">\
                    <input type="text" class="form-control" name="<%= name %>" disabled style="background-color: #FFF" id="<%= name %>" placeholder="">\
                    <span class="input-group-addon clazzUpload" style="cursor: pointer;"><i class="fa fa-upload"></i></span>\
                    <span class="input-group-addon clazzEye view" style="cursor: pointer;"><i class="fa fa-eye"></i></span>\
                </div>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        dateTimeEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <div class="input-group">\
                    <input type="dtime" class="form-control" style="background-color: #FFF" name="<%= name %>" id="<%= name %>" placeholder="">\
                    <span class="timeRemove input-group-addon" style="cursor: pointer;"><i class="fa fa-remove"></i></span>\
                </div>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        textAreaEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <textarea type="textarea" class="form-control" name="<%= name %>" id="<%= name %>" rows="3" placeholder=""　/>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        checkBoxEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <input type="checkbox" name="<%= name %>" id="<%= name %>" placeholder="">\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        selectEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <select name="<%= name %>" id="<%= name %>" class="form-control innerselect">\
                    <option value="0">全部</option>\
                </select>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        popUpEle: _.template('<div class="form-group">\
            <label for="<%= name %>" class=""><%= title %></label>\
            <div class="form-value">\
                <div class="input-group">\
                    <input type="<%= type %>" class="form-control" name="<%= name %>" id="<%= name %>" placeholder="">\
                    <span class="popEdit input-group-addon" style="cursor: pointer;"><i class="fa fa-pencil fa-fw"></i></span>\
                    <span class="popDel input-group-addon" style="cursor: pointer;"><i class="fa fa-remove"></i></span>\
                </div>\
                <div class="help-block with-errors"></div>\
            </div>\
        </div>'),
        btnEle: _.template('<div class="form-group>\
            <input type="button" />\
        </div>'),
        initialize: function (initParams, initD, addParam) {
            this.renderSearchDataFunc = initParams['renderSearchData']
            var params = $.extend(true, {}, initParams);
            var self = this;
            this.initD = initD || {};
            this.seriFilterArray = [];
            this.$searchBtn = undefined;
            this.addParam = addParam || {};
            this.factory = multiType[this.addParam['formType']] || {};
            this.withHideBtn =  this.factory['withHideBtn']
            if(this.factory['row'] == undefined) {
                this.$row = $('<form role="form" class="form-inline" data-toggle="validator">');
                if(params['css'] != undefined) {
                    this.$row.css(params['css'])
                }
            } else {
                this.$row = $(this.factory['row']);
            }
            this.$el = this.$row;
            var i = 0 ;
            var btnIndex = 0 ;
            var $tmp = $('<div class="row">')
            var $moreFields = $('<div id="moreFields" style="display: none;"></div>')
            var withMore = false;
            var maxFields = 3;
            params.fields = params.fields || {}
            params.fields.length > maxFields ? withMore = true : withMore = false
            _.each(params.fields, function(param){
                param['title'] = (param['title'] == undefined || param['title'] == "") ? param['title'] : param['title'] + "："
                param['divClz'] = param['divClz'] || 'col-md-8';
                param['placeholder'] = param['placeholder'] || '';
                if(param['required'] == true) {
                    param['title'] += '<span style="color: red; vertical-align: sub;">*</span>'
                }
                param['type'] = param['type'] || 'text';
                if(++i <= maxFields ) {
                    $tmp.append((self.geneSingle(i, param)));
                }else {
                    if(self.withHideBtn) {
                        $moreFields.append(self.geneSingle(i, param))
                    }else {
                        $tmp.append((self.geneSingle(i, param)));
                    }
                }
            });
            $tmp.append($moreFields);
            self.$row.append($tmp);
            if(this.factory == undefined) {
                self.$row.append($('<div class="row" style="height: 20px;">'))
            }
            params.btns = params.btns || [];
            var total = params.btns.length;
            if(total > 0 ){
                if(Object.keys(this.addParam).length != 0) {
                    $tmp = $('<div class="row">')
                }else {
                    $tmp = $('<div class="row" style="text-align: center">')
                }
            }
            _.each(params.btns, function(param){
                param['id'] = param.name ;
                param['clazz'] = param.class;
                $tmp.append(self.geneBtn(total, btnIndex++, param));
            });
            if(withMore && this.withHideBtn)  {
                var param = {}
                param['title'] = '高级搜索'
                param['id'] = 'toggleMore'
                param['callback'] = function(){
                    if(self.$('#moreFields').css('display') == 'inline') {
                        self.$('#moreFields').css({'display': 'none'})
                        self.$('#toggleMore').val('高级搜索')
                    }else {
                        self.$('#moreFields').css({'display': 'inline'})
                        self.$('#toggleMore').val('隐藏')
                    }
                }
                $tmp.append(self.geneBtn(total + 1, btnIndex, param))
            }
            self.$row.append($tmp);
        },
        hideEle: function(name) {
            this.$('input[name='+ name +']').closest('.form-group').hide()
        },
        geneBtn: function(totalRow, i, param){
            var btnClazz = ''
            if(totalRow == 1){
                // var btnClazz = 'col-md-2 col-md-offset-5'
            }else{
                if(i % 2 == 0 ) {
                    // var btnClazz = 'col-md-2 col-md-offset-1'
                }else {
                    // var btnClazz = 'col-md-2 col-md-offset-3'
                }
            }
            // var clazz = this.factory.btnClazz != undefined ? this.factory.btnClazz : btnClazz;
            var clazz = ''
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
            // $btn.addClass('btn');
            if(param['clazz'] !== undefined){
                // $btn.addClass(param['clazz']);
            }else {
                // $btn.addClass('btn-default');
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
        dropDown: function(param){
            var self = this ;
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
            return $formEle;
        },
        popUp: function(param){
            var self = this;
            var $formEle = $(this.popUpEle(param));
            var $input = $formEle.find('input')
            var editCallback = function(v1, v2){
                $input.attr('val',v1)
                $input.val(v2)
                $input.trigger('change')
            }
            var delCallback = function(d) {
                $input.attr('val', 0)
                $input.val('')
                $input.trigger('change')
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
            return $formEle;
        },
        monthFunc: function(param) {
            var self = this ;
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
                format: 'yyyy-mm',
                language: 'zh-CN',
                minView:'year',
                startView: 'year',
                maxView:'decade',
                autoclose: true,
                todayBtn: true
            });
            if(this.initD && this.initD[param['name']] != undefined) {
                $elInput.attr('val', this.initD[param['name']])
                $elInput.val(Libs.formatDateToMonth(new Date(this.initD[param['name']])))
            }
            return $formEle;
        },
        dateFunc: function(param) {
            var self = this ;
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
            return $formEle;
        },
        dateTimeFunc: function(param) {
            var self = this ;
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
            return $formEle;
        },
        fileFunc: function(param){
            var self = this;
            var $formEle = $(this.fileEle(param))
            var $input = $formEle.find('input')
            $formEle.find('.clazzUpload').on('click', function(){
                SC.PicWin($input)
            })
            $formEle.find('.clazzEye').on('click', function(){
                SC.PicView($input)
            })
            return $formEle;
        },
        combineFunc: function(param) {
            var $formEle = $(this.combineEle(param))
            return $formEle;
        },
        multipleCheckboxFunc: function(param) {
            this.seriFilterArray.push(param['name']);
            var initD = this.initD[param['name']] || "";
            var array = initD.split(',')
            var $formEle = $(this.multipleCheckBoxEle(param))
            var $formValue = $formEle.find('.form-value')
            _.each(param.data, function(k, v) {
                console.log(k)
                console.log(v)
                if(array.includes(k['id'])) {
                    var checked = true
                }else {
                    var checked = false
                }
                $formValue.append('<label class="checkbox-inline"><input type="checkbox"' + (checked ? "checked" : "") + ' class="multi" name="' + param['name'] + '" value="' + k['id'] +  '">' + k['name'] + '</label>');
            })
            return $formEle
        },
        geneSingle: function(i, param){
            var self = this;
            if(param['el'] != undefined){
                return param['el'];
            }
            param['title'] = param['title'] || ''
            param['placeholder'] = param['placeholder'] || ''
            if(param['type'] == 'dropdown'){
                var $formEle = this.dropDown(param);
            }else if(param['type'] == 'popUp'){
                var $formEle = this.popUp(param)
            }else if(param['type'] == 'textarea'){
                var $formEle = $(this.textAreaEle(param))
                $formEle.find('label').css({"vertical-align": "top"})
            }else if(param['type'] == 'checkbox') {
                var $formEle = $(this.checkBoxEle(param))
            }else if(param['type'] == 'date'){
                var $formEle = this.dateFunc(param)
            }else if(param['type'] == 'datetime'){
                var $formEle = this.dateTimeFunc(param)
            }else if(param['type'] == 'month'){
                var $formEle = this.monthFunc(param)
            }else if(param['type'] == 'file') {
                var $formEle = this.fileFunc(param);
            }else if(param['type'] == 'combineTxt'){
                var $formEle = this.combineFunc(param)
            }else if(param['type'] == 'multiCheckbox') {
                var $formEle = this.multipleCheckboxFunc(param)
            }else {
                var $formEle = $(this.formEle(param));
            }
            if(param['required'] == true){
                $formEle.find('.form-control').attr('required', true);
                $formEle.find('.form-control').attr('data-error',"请输入")
            }
            if(param['hide'] == true) {
                $formEle.hide()
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
            if(param['title'] == "") {
                if($formEle.find('label') != undefined && $formEle.find('label').text() == "") {
                    $formEle.find('label').css({'width': '0px'})
                }
            }
            if(param['formValue'] != undefined) {
                $formEle.find('.form-value').css(param['formValue'])
            }
            if(param['formGroup'] != undefined) {
                $formEle.css(param['formGroup'])
            }
            return $formEle;
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
        },
        validator: function(){
            this.$row.validator();
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