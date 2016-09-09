/**
 * Created by wjr on 16-7-4.
 */
define(['jquery', 'backbone', 'underscore', 'js/libs/stack', 'js/libs/lru'],function($, Backbone, _, Stack){
    Array.prototype.indexOf || (Array.prototype.indexOf = function(d, e) {
        var a;
        if (null == this) throw new TypeError('"this" is null or not defined');
        var c = Object(this),
            b = c.length >>> 0;
        if (0 === b) return -1;
        a = +e || 0;
        Infinity === Math.abs(a) && (a = 0);
        if (a >= b) return -1;
        for (a = Math.max(0 <= a ? a : b - Math.abs(a), 0); a < b;) {
            if (a in c && c[a] === d) return a;
            a++
        }
        return -1
    });
    $.fn.serializeJson = function(jsonData){
        if(this.length > 0){
            var self = this;
            if(jsonData){
                $('*[name]',self).each(function(){
                    var that = $(this);
                    var name = that.attr('name');
                    var value = name ? (jsonData[name]==undefined?'':jsonData[name]) : '';
                    var callback = that.attr('callback');
                    if(callback && $.isFunction(jQuery.callbacks[callback])){
                        value = jQuery.callbacks[callback].call(that,value);
                    }
                    if(that[0].tagName == 'INPUT' || that[0].tagName == 'TEXTAREA'){
                        if(that[0].type == 'checkbox' && jsonData[name] != undefined){
                            that[0].checked = jsonData[name] ? true : false;
                            return;
                        }
                        if(that[0].type == 'radio'){
                            $('input[name='+name+']',self).each(function(){
                                var radioItem = $(this);
                                if(radioItem.val() == jsonData[name]){
                                    radioItem[0].checked = true;
                                }
                            });
                            return;
                        }
                        if(jsonData[name] != undefined) that.val(value);
                        var val = that.attr('val');
                        if(jsonData[val] != undefined) that.attr('val',jsonData[val]);
                        else that.removeAttr('val');
                    }else if(that[0].tagName.toLowerCase() == 'select'){

                    }else{
                        that.text(value);
                    }
                });

                return this;
            }
            var res = {};
            $('input,select,textarea',self).each(function(){
                var that = $(this);
                var name = that.attr('name');
                if(res.hasOwnProperty(name))return;
                if(this.type && this.type == 'checkbox'){
                    res[name] = this.checked;
                    return;
                }
                if(this.type && this.type == 'radio'){
                    $('input[name='+name+']').each(function(){
                        var radioItem = $(this);
                        if(radioItem.is(':checked')){
                            res[name] = radioItem.val();
                        }
                    });
                    return;
                }
                var val = that.attr('val');
                val = val? val : that.val();
                if(val != undefined && val != ''){
                    res[name] = val;
                }
            });
            return res;
        }else{
            return {};
        }
    };
    $.postJSON = function(url,data,s,e){
        if($.isFunction(data)){
            e = s;
            s = data;
            data = {};
        }
        $.ajax({
            url:url,
            type:'post',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success:s,
            error:function(e) {
                alert('ajax调用失败');
            }
        });
    };
    Backbone.Base = function(){
        this.cid = _.uniqueId('base');
        this.initialize.apply(this, arguments);
    };
    _.extend(Backbone.Base.prototype, {}, {
        initialize: function(){}
    });
    Backbone.Base.extend = Backbone.View.extend;

    Backbone.View.prototype.close = function(){
        this.remove();
        this.unbind();
        if(this.onClose){
            this.onClose();
        }
    };
    $.queryToJson = function(queryString) {
        if(queryString == undefined || queryString == ""){
            return {};
        }
        var j, q;
        q = queryString.replace(/\?/, "").split("&");
        j = {};
        $.each(q, function(i, arr) {
            arr = arr.split('=');
            return j[arr[0]] = arr[1];
        });
        return j;
    };
    $.fn.nDataTable = function(params){
        var baseParam = {
            "processing": true,
            "serverSide": true,
            "bLengthChange": false,
            iDisplayLength :10,
            bAutoWidth: true,
            bFilter: false,
            "bSort": false,
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                },
                "sZeroRecords": "没有检索到数据",
                "sProcessing": "<img src='/assets/images/loading.gif' />"
            }
        };
        params = $.extend(baseParam, params);
        return this.DataTable(params);
    };
    $.Stack = Stack;
    $.LRUCache = LRUCache
});