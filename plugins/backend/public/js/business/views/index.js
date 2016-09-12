/**
 * Created by wjr on 16-9-8.
 */
define(['backbone', 'component'], function(Backbone, Component){
    var prefix = '/teamclub';
    var ModifyView = Backbone.View.extend({
        initialize: function(d, parent) {
            this.d = this.d || {};
            this.parent = parent;
            this.tabs = parent.tabs;
            this.component = new Component(this);
            this.render();
        },
        render: function(){
            this.form = this.component.geneForm(this.formParams(), this.d);
            this.component
                .appendPanel('HAHA', this.form.form())
                .build();
        },
        submit: function() {
            var self = this;
            var json = this.form.serializeJson();
            $.postJSON( prefix + '/verManager/modify', json, function(d){
                if(d['status'] == 0 ){
                    self.parent.reload();
                    self.tabs.closeCurTab();
                    SC.Alert('', '保存成功');
                }else {
                    SC.Alert('', d['message'])
                }

                return false;
            });
        },
        formParams : function() {
            var self = this;
            var formParams = {
                fields:[{
                    title: 'Git版本',
                    name: 'commitVersion'
                },{
                    title: '保存路径',
                    name: 'filePath'
                }],
                btns: [{
                    title: '提交',
                    class: 'btn-primary',
                    callback: $.proxy(self.submit, self)
                }]
            };
            return formParams;
        },
    });

    var index = Backbone.View.extend({
        initialize: function(){
            this.compoment = new Component(this);
            this.render();
        },
        render: function(){
            this.table = this.compoment.geneTable(this.tableParams(), this.searParams());
            this.tabs = this.compoment.geneTab([{
                title: '标题档',
                content: this.compoment.genePanel(undefined, this.table.geneTable()),
                active: true
            }]);
            this.compoment
                .appendNative(this.tabs.full())
                .build();
        },
        reload: function(){
            this.table.reload();
        },
        searParams: function() {
            var searParams = {
                filters:[{
                    title: 'Git版本',
                    name: 'commitVersion'
                }],
                btns: [{
                    title: '查询',
                    class: 'btn-primary',
                    callback: 'search'
                },{
                    title: '新增',
                    callback: $.proxy(this.modify, this, undefined)
                }]
            };
            return searParams;
        },
        modify: function(d, e){
            var title = d == undefined ? '新增' : '修改';
            this.tabs.addTab({
                title: title,
                content: new ModifyView(d, this).$el.children()
            })
        },
        tableParams : function(){
            var self = this;
            var tableParams = {
                "ajax": {
                    url: prefix + '/verManager/list',
                    type: 'POST'
                },
                columns: [{
                    title: "GIT版本",
                    data: "commitVersion"
                },{
                    title: "保存路径",
                    data: "filePath"
                },{
                    title: '操作',
                    data: null,
                    render: function(data, type, full){
                        var btnModify =  '<input type="button" value="修改" class="btn" name="modify"/>';
                        var btnDelete =  '<input type="button" value="删除" class="btn" name="delete"/>';
                        return btnModify + btnDelete;
                    },
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).find('input[name=modify]').on('click', $.proxy(self.modify, self, rowData));
                        $(td).find('input[name=delete]').on('click', $.proxy(self.delete, self, rowData));
                    }
                }]
            };
            return tableParams;
        }
    });
    return index;
});