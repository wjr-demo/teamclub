/**
 * Created by wjr on 16-7-22.
 */
define(['jquery','underscore','common'], function($, _) {
    var Table = Backbone.Base.extend({
        initialize:function(tableParams, formParams){
            this.$initTable = undefined;
            this.$initForm = undefined;
            this.tableRef = undefined;
            this.tableParams = tableParams;
            this.formParams = formParams;
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
            var $form = $('<div></div>');
            var $tmp = $('<div class="row"></div>');
            for(var i = 0 ; i < params.filters.length ; i++){
                $tmp.append(this.geneSingle(params.filters[i]));
            }
            $form.append($tmp);
            var $btns = $('<div class="row col-md-12">');
            for(var j = 0 ; j < params.btns.length; j++){
                params.btns[j]['type'] = 'button';
                $btns.append(this.geneBtn(params.btns[j]));
            }
            $form.append($btns);
            return $form;
        },
        geneSingle: function(param){
            var $e = $('<div class="col-md-3 form-group"></div>');
            var $group = $('<div class="input-group"></div>');
            $group.append($('<span class="input-group-addon">'+ param['title'] +'</span>'));
            if(param['type'] == undefined || param['type'] == 'text'){
                $group.append($('<input type="text" name="' + param['name'] + '" class="form-control" />'));
            }
            $e.append($group);
            return $e;
        },
        reload: function(){
            if(this.tableRef != undefined){
                this.tableRef.ajax.reload(null, false);
            }
        },
        geneBtn : function(param) {
            var self = this;
            if(param['type'] == 'button') {
                var clazz = param['class'] == undefined ? 'btn-default' : param['class'];
                var $btn = $('<button type="submit" class="btn ' + clazz + '" style="min-width: 80px; margin-right: 10px; margin-bottom: 10px;">'+ param['title'] +'</button>');
                if(param.callback == 'search'){
                    $btn.on('click', function(){
                        if(self.tableRef != undefined){
                            self.tableRef.ajax.reload();
                        }
                    })
                }else{
                    $btn.on('click', param.callback);
                }
                return $btn;
            }
        },
        rebuildTable: function(){
            var self = this ;
            if(this.$initForm != undefined && this.tableParams.ajax != undefined){
                this.tableParams.ajax.data = function(d){
                    var data = self.$initForm == undefined ? {} : self.$initForm.serializeJson();
                    delete d['columns'];
                    delete d['search'];
                    $.extend(d, data);
                };
                _.extend(this.tableParams)
            }
            this.tableRef = this.$initTable.nDataTable(this.tableParams);
        }
    });
    return Table;
});