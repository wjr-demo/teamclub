(function () {
    requirejs.config({
        baseUrl: '/assets',
        paths: {
            'jquery': 'bower_components/jquery/dist/jquery',
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
            'component': 'js/libs/component',
            'fileinput': 'bower_components/bootstrap-fileinput/js/fileinput',
            'zh':'bower_components/bootstrap-fileinput/js/locales/zh',
            'validator': 'bower_components/bootstrap-validator/js/validator',
            'jquery/ui': 'bower_components/jquery-ui/jquery-ui',
            'jquery-ui-touch': 'bower_components/jquery.ui.touch-punch/dist/jquery.ui.touch-punch'

        },
        shim: {
            'bootstrap': ['jquery'],
            'metisMenu': ['jquery'],
            'jquery-ui-touch': ['jquery', 'jquery/ui'],
            'sbAdmin': ['jquery', 'metisMenu'],
            'backbone': ['jquery', 'underscore'],
            'dataTables.bootstrap': ['datatables'],
            'dataTables.responsive': ['datatables'],
            'zh': ['fileinput']
        }
    });
    require(['jquery', 'underscore', 'backbone', 'common' , 'bootstrap',  'metisMenu', 'js/business/app',
        'datatables', 'datatables.bootstrap', 'datatables.responsive', 'component', 'zh', 'validator'], function(a, b, c, d, e, f, App) {
        App.init(function(){
            $('.nav-second-level li').on('click',function(e){
                $('.nav-second-level li').children('a').removeClass('active');
                $(e.currentTarget).children('a').addClass('active');
            });
            var assigned = false ;
            if(!assigned) {
                var lia = $($('#leftbar li a')[0]);
                var href = lia['href']
                if(href != undefined) {
                    window.location.href= window.location.href + href;
                }
            }
        });
    });
})();