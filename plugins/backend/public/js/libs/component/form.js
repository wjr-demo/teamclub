/**
 * Created by wjr on 16-7-6.
 */
define(['jquery','underscore','common', 'zh'], function($, _) {
    var Form = Backbone.Base.extend({
        formEle: _.template('<div class="form-group">\
            <div class="input-group">\
                <span class="input-group-addon"></span>\
                <input class="form-control">\
            </div>\
            <div class="help-block with-errors"></div>\
        </div>'),
        btnEle: _.template('<div class="form-group>\
            <input type="button" />\
        </div>'),
        initialize: function (params, initD) {
            var self = this;
            this.initD = initD;
            this.$row = $('<form role="form" data-toggle="validator">');
            var i = 0 ;
            var btnIndex = 0 ;
            var $tmp = $('<div class="row">')
            _.each(params.fields, function(param){
                var title = param.title || '无题';
                var type = param.type || 'text';
                var name = param.name ;
                $tmp.append((self.geneSingle(i++, title, type, name, param)));
            });
            self.$row.append($tmp)
            self.$row.append($('<div class="row" style="height: 20px;">'))
            var total = params.btns.length;
            $tmp = $('<div class="row">')
            _.each(params.btns, function(param){
                var title = param.title || '无题';
                var id = param.name ;
                var cls = param.class;
                $tmp.append(self.geneBtn(total, btnIndex++, title, id, cls, param));
            });
            self.$row.append($tmp);
        },
        geneBtn: function(totalRow, i, title, id, cls, param){
            if(totalRow == 1){
                clazz = 'col-md-2 col-md-offset-5'
            }else{
                if(i % 2 == 0 ) {
                    var clazz = 'col-md-2 col-md-offset-1'
                }else {
                    var clazz = 'col-md-2 col-md-offset-3'
                }
            }
            var $formgroup = $('<div class="form-group ' + clazz + '">');
            if(param['type'] == 'submit') {
                var $btn = $("<input type='submit' style='width: 80px;'>");
            }else {
                var $btn = $("<input type='button' style='width: 80px;'>");
            }
            if(id !== undefined) $btn.attr("id", id);
            $btn.val(title);
            $btn.addClass('btn');
            if(clazz !== undefined){
                $btn.addClass(cls);
            }else {
                $btn.addClass('btn-default');
            }
            if(param['type'] == 'submit') {
                this.$row.validator().on('submit', function(e) {
                    if (e.isDefaultPrevented()) {
                        return false;
                    }else {
                        param.callback(e)
                        return false;
                    }
                });
            }else {
                $btn.on('click', param.callback);
            }
            $formgroup.append($btn);
            return $formgroup;
        },
        geneSingle: function(i, title, type, name, param){
            if(i % 2 == 0 ) {
                var clazz = 'col-md-4 col-md-offset-1'
            }else {
                var clazz = 'col-md-4 col-md-offset-1'
            }
            var $formEle = $(this.formEle());
            $formEle.addClass(clazz);
            $formEle.find('.input-group-addon').text(title);
            $formEle.find('.form-control').attr('type', type).attr('name', name)
            if(param['required'] == true){
                $formEle.find('.form-control').attr('required', true);
                $formEle.find('.form-control').attr('data-error',"请输入")
            }
            if(type == 'file') {
                this.initFileInput($formEle.find('.form-control'), "/backend/upload")
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
            this.$row.serializeJson(this.initD);
            this.$row.validator()
            return $tmp;
        },
        serializeJson: function(d){
            var json = this.$row.serializeJson();
            delete json['undefined'];
            return _.extend(this.initD, json);
        }
    });
    return Form;
});