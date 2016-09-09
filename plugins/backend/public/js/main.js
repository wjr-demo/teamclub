(function () {
    requirejs.config({
        baseUrl: '/assets',
        paths: {
            'jquery': 'bower_components/jquery/dist/jquery.min',
            'bootstrap': 'bower_components/bootstrap/dist/js/bootstrap.min',
            'metisMenu': 'bower_components/metisMenu/dist/metisMenu.min',
            'datatables': 'bower_components/datatables/media/js/jquery.dataTables',
            'datatables.bootstrap': 'bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap',
            'datatables.responsive': 'bower_components/datatables-responsive/js/dataTables.responsive',
            'sbAdmin': 'js/thrid/sb-admin-2',
            'backbone': 'bower_components/backbone/backbone',
            'text': 'bower_components/text/text',
            'underscore': 'bower_components/underscore/underscore',
            'common': 'js/libs/common',
            'component': 'js/libs/component'
        },
        shim: {
            'bootstrap': ['jquery'],
            'metisMenu': ['jquery'],
            'sbAdmin': ['jquery', 'metisMenu'],
            'backbone': ['jquery', 'underscore'],
            'dataTables.bootstrap': ['datatables'],
            'dataTables.responsive': ['datatables']
        }
    });
    require(['jquery', 'underscore', 'backbone', 'common' , 'bootstrap', 'metisMenu', 'js/business/app',
        'datatables', 'datatables.bootstrap', 'datatables.responsive', 'component'], function(a, b, c, d, e, f, App) {
        if(window.location.href.indexOf("login") !== -1) {
            return false;
        }
        App.init();
        $('#side-menu li').on('click',function(e){
            $(e.currentTarget).siblings().children('a').removeClass('active');
            $(e.currentTarget).children('a').addClass('active');
        });
        var assigned = false ;
        if(window.location.href.split('#').length > 1) {
            var r = window.location.href.split('#')[1];
            $('#side-menu li a').removeClass('active');
            _.each($('#side-menu li a'), function(single){
                var $single = $(single);
                if(!assigned && $single.attr('href').indexOf(r) != -1){
                    $single.addClass('active');
                    assigned = true;
                }
            })
        }
        if(!assigned) {
            var lia = $($('#side-menu li a')[0]);
            window.location.href= window.location.href + lia.attr('href');
        }
    });
})();