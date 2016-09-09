/**
 * Created by wjr on 16-7-22.
 */
define(['jquery','underscore','common'], function($, _) {
    var Tab = Backbone.Base.extend({
        initialize: function(params){
            var self = this;
            this.$top = $('<ul class="nav nav-tabs">');
            this.$bottom = $('<div class="tab-content">');
            this.tabs = {};
            this.contents = {};
            this.idxArr = [];
            _.each(params, function(d, idx) {
                self.addTab(d);
            });
            this.$full = $('<div class="row">');
            this.$full.append(this.$top);
            this.$full.append(this.$bottom);
        },
        full: function(){
            return this.$full;
        },
        addTab: function(params){
            var idx = this.add(params);
            this.idxArr.push(idx);
            this.tabs[idx].find('a').tab('show');
        },
        closeCurTab: function(){
            if(this.idxArr.length > 1) {
                var idx = this.idxArr[this.idxArr.length - 2];
                this.remove(idx);
                this.tabs[idx].find('a').tab('show');
            }
        },
        add: function(params){
            var self = this;
            var id = _.uniqueId("tab");
            var active = params['active'] == true ? 'active' : '';
            var panActive = params['active'] == true ? 'active in' : '';
            var $li = $('<li style="min-width: 100px;text-align: center;" class="' + active + '"><a href="#'+ id +'" data-toggle="tab">' + params['title'] + '</a></li>');
            $li.on('click', function(e){
                var idx = $(e.currentTarget).children('a').attr('href');
                self.remove(idx);
            });
            var $con = $('<div class="tab-pane fade '+ panActive +'" id="' + id + '"></div>');
            $con.html(params['content']);
            this.tabs[id] = $li;
            this.contents[id] = $con;
            self.$top.append($li);
            self.$bottom.append($con);
            return id;
        },
        remove: function(idx){
            var self = this;
            if(idx.indexOf('#') != -1) idx = idx.substring(1);
            _.each(this.tabs, function(v, k){
                if(k.length > idx.length || k > idx){
                    v.remove();
                    var _index = self.idxArr.indexOf(k);
                    if(_index != -1){
                        self.idxArr.pop(_index);
                    }
                    delete self.tabs[k];
                }
            });
            _.each(this.contents, function(v, k){
                if(k.length > idx.length || k > idx){
                    v.remove();
                    delete self.contents[k];
                }
            });
        }
    });
    return Tab;
});
