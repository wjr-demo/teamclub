/**
 * Created by wjr on 16-11-21.
 */
define([
    'jquery',
    'underscore',
    'js/libs/component/modal', 'common'],function($, _, Modal){
    var SC = window.SC = window.SC || {};


    /***
      params => {
        title,
        content,
        footer,
        type: modal-sm modal-md modal-lg
      }
    */
    SC.OpenWin = function(params){
        params = params || {} ;
        params.modelStyle = params.modelStyle || {}
        params.bodyStyle = params.bodyStyle || {}
        var style = $.extend({'width': '800px', 'top': '-135px'},params.modelStyle)
        $('body').append((new Modal({
            'type': params.type ,
            'title': params.title || '提示',
            'body': params.body,
            'footer': params.footer,
            'style': style,
            'bodyStyle': params.bodyStyle,
            'needMove': true
        }).$el))
    };

    /***
     *
     * params.url
     * params.winform
     * params.callback
     *
     * @param params
     * @constructor
     */
    SC.OpenWinForm = function(params) {
        params = params || {};
        this.winform = params['winform'];
        params['body'] = this.winform.form();
        params['confirmFunc'] = function(d, remove) {
            if(params['prefixDataHandler'] !== undefined) {
                d = params['prefixDataHandler'](d);
            }
            $.postJSON(params['url'], d, function(resp) {
                SC.judge(resp, function(){
                    if(params.callback !== undefined) {
                        params.callback(resp);
                    }
                    remove();
                    SC.Alert('', '操作成功');
                }, function(){
                    SC.Alert('', d['message'])
                });

            });
        };
        var modal = new Modal({
            'type': 'OpenWinForm' ,
            'title': params.title || '提示',
            'body': params.body,
            'footer': params.footer,
            'confirmFunc': params.confirmFunc,
            'initData': params.initData,
            'style': {'width': '800px', 'top': '-135px'},
            'needMove': true
        });
        $('body').append((modal.$el))
    }

    SC.PicView = function($originEl){
        var src= "/assets/" + $originEl.val()
        var $el = $('<img>')
        $el.attr('src', src)
        $('body').append((new Modal({
            'type': 'Pic',
            'title': '文件查看',
            'body': $el,
            'needMove': true,
            'bodyStyle': {'min-height': '300px'}
        }).$el))
    }

    SC.PicWin = function($originEl) {
        var $el = $('<input type="file" multiple class="file-loading" />')
        $('body').append((new Modal({
            'type': 'Pic',
            'title': '文件上传',
            'body': $el,
            'needMove': true,
            'bodyStyle': {'min-height': '300px'}
        }).$el))
        $el.fileinput({
            language: 'zh', //设置语言
            uploadUrl: "/backend/upload/file", //上传的地址
//            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
        //异步上传返回结果处理
        $el.on('fileerror', function(event, data, msg) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
            // get message
            console.log(msg)
        });
        //异步上传返回结果处理
        $el.on("fileuploaded", function (event, data, previewId, index) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
            var obj = data.response;
            console.log(obj)
            $originEl.val(obj['path'])

        });
    }


    SC.Alert = function(title, content, clazz) {
        $('body').append((new Modal({
            'type': 'Alert',
            'clazz': clazz || 'modal-sm',
            'title': title || '提示',
            'body': content
        }).$el))
    }
    SC.Confirm = function(title, content, confirmFunc, clazz) {
        $('body').append((new Modal({
            'type': 'Confirm',
            'title': title || '提示',
            'clazz': clazz || 'modal-sm',
            'body': content,
            'confirmFunc': confirmFunc
        }).$el))
    };
    /***
     *
     * @param title
     * @param content
     * @param btns  [{el: $el, click:  click},{}]
     * @constructor
     */
    SC.MultiBtn = function(title, content, btns, clazz) {
        $('body').append((new Modal({
            'type': 'MultiBtn',
            'title': title || '提示',
            'clazz': clazz || 'modal-sm',
            'body': content,
            'btns': btns
        }).$el))
    };
    SC.judge = function(d,fun1, func2) {
        if(d['status'] == 0 ){
            fun1();
        }else {
            func2()
        }
    };
    SC.Delete = function(url, d, callback){
        SC.Confirm('提示','是否确认删除?', function() {
            $.postJSON(url, d, function(respD) {
                SC.judge(respD, function(){
                    callback();
                    SC.Alert('', '删除成功');
                }, function() {
                    SC.Alert('', respD['message'])
                })
            })
        })
    };
    SC.Save = function(url, params, callback) {
        $.postJSON(url, params, function(d){
            SC.judge(d, function(){
                callback(d);
                SC.Alert('', '操作成功');
            }, function(){
                SC.Alert('', d['message'])
            });
            return false;
        });
    };
    SC.SaveWithTip = function(tip, url, params, callback) {
        SC.Confirm('提示',tip , function() {
            $.postJSON(url, params, function(d){
                SC.judge(d, function(){
                    callback(d);
                    SC.Alert('', '保存成功');
                }, function(){
                    SC.Alert('', d['message'])
                });
                return false;
            });
        })
    }
})