/**
 * Created by Administrator on 2016/7/3.
 */
define(['backbone', 'component', 'js/business/views/dash'], function(Backbone, Component, Dash){
    var view = Backbone.View.extend({
        initialize: function(){
            this.tabs = undefined;
            this.table = undefined;
            this.tabs = undefined;
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
        modify: function(d, e){
            var title = d == undefined ? '新增' : '修改';
            this.tabs.addTab({
                title: title,
                content: new Dash(d, this).$el.children()
            })
        },
        delete: function(d, e){
            var self = this;
            $.postJSON('/backend/bank/delete', d, function(d){
                self.reload();
                return false;
            })
        },
        searParams : function() {
            var searParams = {
                filters:[{
                    title: '名称',
                    name: 'name'
                },{
                    title: '地址',
                    name: 'address'
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
        tableParams : function(){
            var self = this;
            var tableParams = {
                "ajax": {
                    url: '/backend/bank/list',
                    type: 'POST'
                },
                columns: [{
                    title: "编号",
                    data: "no"
                },{
                    title: "名称",
                    data: "name"
                },{
                    title: "地区码",
                    data: "areacode",
                    render: function(d, type, full){
                        return "<span style='color:red;'>" + d + "</span>";
                    }
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
    return view;
});