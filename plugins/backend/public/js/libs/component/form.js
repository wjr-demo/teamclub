/**
 * Created by wjr on 16-7-6.
 */
define(['jquery','underscore','common'], function($, _) {
    var Form = Backbone.Base.extend({
        initialize: function (params, initD) {
            var self = this;
            this.initD = initD;
            this.$row = $('<form role="form">');
            var i = 0 ;
            var btnIndex = 0 ;
            _.each(params.fields, function(param){
                var title = param.title || '无题';
                var type = param.type || 'text';
                var name = param.name ;
                self.$row.append((self.geneSingle(i++, title, type, name)));
            });
            var total = params.btns.length;
            _.each(params.btns, function(param){
                var title = param.title || '无题';
                var id = param.name ;
                var cls = param.class;
                self.$row.append(self.geneBtn(total, btnIndex++, title, id, cls, param));
            });
        },
        geneBtn: function(totalRow, i, title, id, cls, param){
            if(totalRow == 1){
                clazz = 'col-md-2 col-md-offset-5'
            }else{
                if(i % 2 == 0 ) {
                    var clazz = 'col-md-2 col-md-offset-4'
                }else {
                    var clazz = 'col-md-2'
                }
            }
            var $formgroup = $('<div class="form-group ' + clazz + '">');
            var $btn = $("<input type='button' style='width: 100%'>");
            if(id !== undefined) $btn.attr("id", id);
            $btn.val(title);
            $btn.addClass('btn');
            if(clazz !== undefined){
                $btn.addClass(cls);
            }else {
                $btn.addClass('btn-default');
            }
            $btn.on('click', param.callback);
            $formgroup.append($btn);
            return $formgroup;
        },
        geneSingle: function(i, title, type, name){
            if(i % 2 == 0 ) {
                var clazz = 'col-md-6'
            }else {
                var clazz = 'col-md-6'
            }
            var $formgroup = $('<div class="form-group ' + clazz + '">');
            var $formG = $('<div class="input-group"></div>');
            $formgroup.append($formG);
            var $inputgroupaddon = $('<span class="input-group-addon"></span>');
            $inputgroupaddon.text(title);
            var $input = $('<input class="form-control">');
            $input.attr('type', type);
            $input.attr('name', name);
            $formG.append($inputgroupaddon);
            $formG.append($input);
            return $formgroup;
        },
        form: function(){
            var $tmp = $('<div class="row col-md-12">');
            $tmp.append(this.$row);
            this.$row.serializeJson(this.initD);
            return $tmp;
        },
        serializeJson: function(d){
            var json = this.$row.serializeJson();
            return _.extend(this.initD, json);
        }
    });
    return Form;
});