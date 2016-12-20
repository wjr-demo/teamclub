define(['backbone', 'jquery', 'common'], function(Backbone, $){
    var app = {
        currentView: null,
        router: null,
        lruCache: new $.LRUCache(),
        init: function(callback){
            this.callback = callback;
            console.log('app init');
            this.render();
        },
        showView: function(view){
            this.currentView = view;
            $("#page-wrapper").html(this.currentView.$el.children());
        },
        geneTree: function(array, $menu){
            var s = this;
            _.each(array, function(single) {
                var $ele = $('<li><a href="#'+ single['module'] +'">'+ single['text'] +'<span style="display:none;" class="fa arrow"></span></a></li>')
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
            var self = this;
            $.postJSON('/backend/treemanager/list', {}, function(d){
                var $menu = $('<ul class="nav sidebar" style="position: relative;margin-top: 0px;"></ul>')
                self.geneTree(d, $menu)
                $menu.metisMenu();
                $('#leftbar').append($menu)
                self.callback();
            });
        },
        view404: Backbone.View.extend({
            initialize: function(err){
                this.$el.append($("<div>" + err.message  + "</div>"));
            }
        }),
        modifyMenu: function(){
            if(window.location.href.split('#').length > 1) {
                var r = window.location.href.split('#')[1];
                $('#side-menu li a').removeClass('active');
                var assigned = false ;
                _.each($('#side-menu li a'), function(single){
                    var $single = $(single);
                    if(!assigned && $single.attr('href').indexOf(r) != -1){
                        $single.addClass('active');
                        assigned = true;
                    }
                })
            }
        },
        loadHtmlByJs: function(url) {
            var self = this;
            self.modifyMenu();
            var urlArr = url.split('#');
            var jsUrl = urlArr[0];
            var paraUrl = urlArr[1];
            var paraJson = $.queryToJson(paraUrl);

            require([jsUrl], function(index){
                try{
                    var view = new index($.extend({method: url}, paraJson));
                    self.showView(view);
                }catch(err) {
                    var view404 = new self.view404(err);
                    self.showView(view404);
                }
            }, function(err){
                var view404 = new self.view404(err);
                self.showView(view404);
            });
        }
    };
    var Router = Backbone.Router.extend({
        routes: {
            "*actions": "defaultRoute"
        }
    });
    app.router = new Router();
    app.router.on('route:defaultRoute', function(actions){
        if(actions !== undefined && actions !== null){
            app.loadHtmlByJs(actions);
        }
    });
    Backbone.history.start();
    return app;
});