/**
 * Created by wjr on 16-7-22.
 */
define(['jquery','underscore','common', 'js/libs/component/form'], function($, _, Common, Form) {
    var Table = Backbone.Base.extend({
        initialize:function(tableParams, formParams){
            this.$initTable = undefined;
            this.$initForm = undefined;
            this.$rareForm = undefined;
            this.tableRef = undefined;
            this.tableParams = tableParams || {};
            this.formParams = formParams || {};
            this.$initTable = this.geneInitTable();
            if(formParams){
                this.$initForm = this.geneSearch(formParams);
            }
        },
        geneTable: function(){
            var $full = $('<div>');
            if(this.$initForm != undefined){
                $full.append(this.$initForm);
            };
            if(this.$initTable != undefined){
                $full.append(this.$initTable);
            }
            this.rebuildTable();
            return $full.children();
        },
        geneInitTable: function(){
            var table = $('<table class="table table-striped table-bordered table-hover" width="100%"></table>');
            return table;
        },
        geneSearch: function(params) {
            var self = this ;
            var reload = $.proxy(self.reload, self);
            var $form = new Form(params, {}, {'reload': reload, 'formType': 'searchForm'});
            this.$rareForm = $form;
            $form.searchBtnRegister(reload)
            return $form.form();
        },
        reload: function(){
            if(this.tableRef != undefined){
                this.tableRef.ajax.reload(null, false);
            }
        },
        rebuildTable: function(){
            var self = this ;
            if(this.$initForm != undefined && this.tableParams.ajax != undefined){
                this.tableParams.ajax.dataType = 'json';
                this.tableParams.ajax.contentType = 'application/json'
                this.tableParams.ajax.data = function(d){
                    var data = self.$initForm == undefined ? {} : self.$initForm.serializeJson(undefined, true);
                    delete d['columns'];
                    delete d['search'];
                    delete data['undefined']
                    $.extend(d, data);
                    if(self.$rareForm.renderSearchDataFunc != undefined) {
                        d =  self.$rareForm.renderSearchDataFunc(d)
                    }
                    if(self.tableParams.ajax.extendData != undefined) {
                        $.extend(d, self.tableParams.ajax.extendData)
                    }
                    d['currentPage'] = d['start'] / d['length']
                    d['pageSize'] = d['length']
                    delete d['start']
                    delete d['length']
                    return JSON.stringify(d)
                };
            }
            this.tableRef = this.$initTable.nDataTable(this.tableParams);
        }
    });
    return Table;
});