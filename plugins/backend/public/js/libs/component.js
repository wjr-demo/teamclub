/**
 * Created by Administrator on 2016/7/5.
 */
define([
    'jquery',
    'underscore',
    'js/libs/component/form',
    'js/libs/component/tab',
    'js/libs/component/table',
    'js/libs/component/modal',
    'datatables'
], function($, _, Form, Tab, Table, Modal){
    var SC = window.SC = window.SC || {};
    SC.Alert = function(title, content, type){
        title = title || '提示'
        content = content || ''
        $('body').append((new Modal({
            'type': type,
            'title': $("<div>" + title + "</div>"),
            'body': $("<div>" + content + "</div>")
        }).$el))
    };
    var base = Backbone.Base.extend({
        initialize: function(owner){
            this.tmpContent = $("<div></div>");
            this.owner = owner;
        },
        //直接添加
        appendNative: function($ele) {
            this.tmpContent.append($ele);
            return this;
        },
        //生成标题
        appendTitle: function(title){
            var $e = $('<div class="row col-md-12"><h3>' +　title + '</h3></div>');
            this.tmpContent.append($e);
            return this;
        },
        //生成面板
        genePanel: function($head, $body){
            var panel = $('<div class="panel panel-default"></div>');
            var panelHead = $('<div class="panel-heading"></div>');
            var panelBody = $('<div class="panel-body"></div>');
            if($head != undefined){
                panelHead.append($head);
                panel.append(panelHead);
            }
            if($body != undefined){
                panelBody.append($body);
                panel.append(panelBody);
            }
            return panel;
        },
        appendPanel: function($head, $body){
            var $panel = this.genePanel($head, $body);
            this.tmpContent.append($panel);
            return this;
        },
        geneTab: function(params) {
            return new Tab(params);
        },
        geneTable: function(tableParmas, formParams){
            return new Table(tableParmas, formParams);
        },
        geneForm: function(formParams, initD) {
            return new Form(formParams, initD);
        },
        build: function(){
            this.owner.$el.append(this.tmpContent.children());
            this.tmpContent = $('<div></div>');
        },
        generate: function(){
            return this.tmpContent;
        },
        rebuild: function(){
            this.owner.$el.empty();
            this.owner.$el.append(this.tmpContent.children());
            this.tmpContent = $('<div>');
        }
    });
    return base;
});