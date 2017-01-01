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
        $('body').append((new Modal({
            'type': params.type ,
            'title': params.title || '提示',
            'body': params.body,
            'footer': params.footer,
            'style': {'width': '800px', 'top': '-135px'},
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