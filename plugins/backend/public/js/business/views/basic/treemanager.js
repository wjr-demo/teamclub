/**
 * Created by wjr on 16-11-24.
 */
define(['backbone', 'component'], function(Backbone, Component) {
    var prefix = "/backend";
    var view = Backbone.View.extend({
        tpl: '<div class="row" style="margin-top: 10px">\
            <div class="col-md-3" id="left" style="min-width: 300px;"></div>\
            <div class="col-md-8">\
                <div class="row col-md-12">\
                    <div class="col-md-offset-1 col-md-11">\
                        <input id="addNode" type="button" class="btn btn-default" value="添加节点"/>\
                        <input id="modifyNode" type="button" class="btn btn-default" value="修改节点"/>\
                        <input id="delNode" type="button" class="btn btn-default" value="删除节点"/>\
                    </div>\
                </div>\
                <div class="col-md-12" id="right" style="margin-top: 20px;"></div>\
                </div>\
            </div>',
        initialize: function(d, parent, callback){
            this.component = new Component(this);
            this.form = undefined;
            this.render();
        },
        events: {
            'click ul li' : 'clickInvoke',
            'click #addNode': 'addNode',
            'click #modifyNode': 'modifyNode',
            'click #delNode': 'delNode'
        },
        modifyNode: function() {
            this.createForm({'type': 'modify'})
        },
        addNode: function() {
            this.createForm({'type': 'add'})
        },
        delNode: function() {
            var self = this ;
            var $hoved = this.$el.find('li').find('.hovered');
            if($hoved == undefined || $hoved.length == 0) {
                SC.Alert('', '请选中左侧菜单栏');
                return false;
            }else {
                var id = $hoved.attr('id').split('_')[1];
                SC.Confirm('', '确认删除?', function() {
                    $.postJSON(prefix + '/treemanager/del', {'id': id}, function(d) {
                        SC.judge(d, function(){
                            self.renderTree();
                            SC.Alert('', '删除成功');
                        }, function(){
                            SC.Alert('', d['message'])
                        });
                        return false;
                    })
                })
            }
        },
        createForm: function(params) {
            var self = this ;
            if(params['type']  == 'modify') {
                var $hoved = this.$el.find('li').find('.hovered');
                if($hoved == undefined || $hoved.length == 0) {
                    SC.Alert('', '请选中左侧菜单栏');
                    return false;
                }else {
                    var id = $hoved.attr('id').split('_')[1];
                    $.postJSON(prefix + '/treemanager/findById', {'id': id}, function(d) {
                        self.$('#right').empty();
                        self.form = self.component.geneForm(self.formParams(), d).form();
                        self.$('#right').append(self.form);
                    })
                }
            } else if(params['type']  == 'add') {
                this.$('#right').empty();
                this.form = this.component.geneForm(this.formParams(), {}).form();
                this.$('#right').append(this.form);
            }

        },
        clickInvoke: function(e) {
            $('#left ul li a').removeClass('hovered')
            $(e.currentTarget).children('a').addClass('hovered')
            return false;
        },
        geneTree: function(array, $menu){
            var s = this;
            _.each(array, function(single) {
                var $ele = $('<li><a href="javascript:void(0)" id="tree_' + single['id'] + '">'+ single['text'] +'<span style="display:none;" class="fa arrow"></span></a></li>')
                if(single['nodes'].length > 0) {
                    $ele.find('span').show();
                    var $ul = $('<ul class="nav nav-second-level collapse"></ul>');
                    $ele.append($ul);
                    s.geneTree(single['nodes'], $ul)
                }
                $menu.append($ele);
            });
        },
        render: function(){
            this.$el.append(this.tpl);
            this.renderTree();
        },
        renderTree: function() {
            var self = this ;
            self.$('#left').empty();
            $.postJSON(prefix + '/treemanager/list', {}, function(d){
                var $menu = $('<ul class="nav sidebar" style="position: relative;margin-top: 0px; width: 100%;"></ul>')
                self.geneTree(d, $menu)
                $menu.metisMenu();
                var panelLeft = self.component
                    .genePanel('菜单列表', $menu)
                self.$('#left').append(panelLeft)
            });
        },
        formParams : function(params) {
            var self = this;
            var formParams = {
                fields:[{
                    name: 'id',
                    hide: true
                },{
                    title: '上级菜单',
                    name: 'parent',
                    type: 'dropdown',
                    dataurl: prefix + '/treemanager/parentlist'
                },{
                    title: '名称',
                    name: 'text',
                    required: true
                },{
                    title: 'APPID',
                    name: 'appId',
                    required: true
                },{
                    title: 'JS地址',
                    name: 'module'
                }],
                btns: [{
                    title: '提交',
                    class: 'btn-primary',
                    type: 'submit',
                    callback: $.proxy(self.submit, self)
                }]
            };
            return formParams;
        },
        submit: function() {
            var self = this ;
            var json = this.form.serializeJson();
            json['name'] = json['text'];
            $.postJSON( prefix + '/treemanager/add', json, function(d){
                SC.judge(d, function(){
                    self.renderTree();
                    SC.Alert('', '保存成功');
                }, function(){
                    SC.Alert('', d['message'])
                });
                return false;
            })
        }
    });
    return view;
})