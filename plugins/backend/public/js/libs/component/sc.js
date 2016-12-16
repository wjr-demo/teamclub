/**
 * Created by wjr on 16-11-21.
 */
define([
    'jquery',
    'underscore',
    'js/libs/component/modal'],function($, _, Modal){
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
            'style': {'width': '800px'}
        }).$el))
    };
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
    }
})