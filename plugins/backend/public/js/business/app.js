define(['backbone', 'jquery', 'common'], function(Backbone, $){
    var app = {
        currentView: null,
        router: null,
        lruCache: new $.LRUCache(),
        init: function(){
            console.log('app init');
        },
        showView: function(view){
            this.currentView = view;
            $("#page-wrapper").html(this.currentView.$el.children());
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
        loadHmtlByJs: function(url) {
            var self = this;
            self.modifyMenu();
            var urlArr = url.split('#');
            var jsUrl = urlArr[0];
            var paraUrl = urlArr[1];
            var paraJson = $.queryToJson(paraUrl);

            require([jsUrl], function(index){
                var view = new index($.extend({method: url}, paraJson));
                self.showView(view);
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
            app.loadHmtlByJs(actions);
        }
    });
    Backbone.history.start();
    return app;
});