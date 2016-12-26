/**
 * Created by wjr on 16-12-15.
 */
define(['jquery','underscore','common'], function($, _, Common) {
    var PureTable = Backbone.Base.extend({
        initialize:function(tableParams, events, $additionForm){
            this.$additionForm = $additionForm;
            this.events = events;
            this.$initTable = undefined;
            this.tableRef = undefined;
            this.tableParams = tableParams || {};
            this.$initTable = this.geneInitTable();
        },
        geneInitTable: function(){
            var table = $('<table class="table table-striped table-bordered table-hover" width="100%"></table>');
            return table;
        },
        registerEvent: function(){
            var self = this ;
            this.$initTable.on('dblclick', 'tr', function() {
                var data = self.tableRef.row(this).data();
                if(self.events['dblclick'] != undefined) {
                    self.events['dblclick'](data);
                }
            })
            var reload = $.proxy(self.reload, self)
            if( this.$additionForm !== undefined) {
                this.$additionForm.searchBtnRegister(reload)
            }
        },
        reload: function(){
            if(this.tableRef != undefined){
                this.tableRef.ajax.reload(null, false);
            }
        },
        rebuildTable: function(){
            var self = this ;
            this.tableParams.ajax.data = function(d){
                if(self.$additionForm !== undefined) {
                    var data = self.$additionForm.$row.serializeJson(undefined, true)
                }
                delete d['columns'];
                delete d['search'];
                delete data['undefined']
                $.extend(d, data);
                d['currentPage'] = d['start'] / d['length']
                d['pageSize'] = d['length']
                delete d['start']
                delete d['length']
            };
            this.tableRef = this.$initTable.nDataTable(this.tableParams);
        },
        geneTable: function(){
            var $full = $('<div>');
            if(this.$initTable != undefined){
                $full.append(this.$initTable);
            }
            this.rebuildTable();
            this.registerEvent();
            return $full.children();
        }
    });
    return PureTable;
})