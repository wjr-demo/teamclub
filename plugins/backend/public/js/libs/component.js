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
    'js/libs/component/sc',
    'js/libs/popup-enums',
    'js/libs/component/textview',
    'js/libs/libs',
    'datatables'
], function($, _, Form, Tab, Table, Modal, SC, PopEnums, TextView){
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
        setAsView: function($el) {
            this.owner.$el.find('input').attr("disabled","disabled");
            this.owner.$el.find('select').attr("disabled","disabled");
            this.owner.$el.find('textarea').attr("disabled","disabled");
            this.owner.$el.find('input[type=submit]:not(.view)').hide()
            this.owner.$el.find('input[type=button]:not(.view)').hide()
        },
        //生成面板
        genePanel: function($head, $body){
            var panel = $('<div class="panel panel-default"></div>');
            var panelHead = $('<div class="panel-heading"></div>');
            var panelBody = $('<div class="panel-body"></div>');
            if($head != undefined && $head != ""){
                panelHead.append($head);
                panel.append(panelHead);
            }
            if($body != undefined){
                panelBody.append($body);
                panel.append(panelBody);
            }
            return panel;
        },
        geneTextView: function(fields, data) {
            return new TextView(fields, data)
        },
        appendPanel: function($head, $body, config){
            config = config || {}
            var panelStyle = config['panel'] || {}
            var panelBodyStyle = config['panel-body'] || {}
            var $panel = this.genePanel($head, $body);
            $panel.css(panelStyle)
            $panel.find('.panel-body').css(panelBodyStyle)
            this.tmpContent.append($panel);
            return this;
        },
        appendTab: function(params) {
            this.tmpContent.append(new Tab(params).$full);
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
            this.owner.$el.empty();
            this.owner.$el.append(this.tmpContent.children());
            this.tmpContent = $('<div></div>');
            return this;
        },
        generate: function(){
            return this.tmpContent;
        },
        rebuild: function(){
            this.owner.$el.empty();
            this.owner.$el.append(this.tmpContent.children());
            this.tmpContent = $('<div>');
        },
        enumsPopUp:PopEnums
    });
    return base;
});