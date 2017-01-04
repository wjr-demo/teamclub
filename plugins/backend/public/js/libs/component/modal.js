/**
 * Created by wjr on 16-9-2.
 */
/**
 * Created by wjr on 16-7-22.
 */
define(['jquery','underscore','common', 'jquery/ui'], function($, _, Common, jqueryUITouch) {

    var Modal = Backbone.Base.extend({
        templ: _.template('<div class="modal-dialog" role="document">\
            <div class="modal-content">\
                <div class="modal-header">\
                    <button type="button" id="xClose" class="close" data-dismiss="modal" aria-label="Close">\
                        <span aria-hidden="true">&times;</span>\
                    </button>\
                    <h4 class="modal-title"></h4>\
                </div>\
                <div class="modal-body" style="overflow: hidden;"></div>\
                <div class="modal-footer">\
                </div>\
            </div>\
        </div>'),
        /***
         * {
         *  title: $el
         *  body: $el
         *  footer: {}
         * }
         */
        initialize:function(params){
            this.params = params ;
            this.initD = params['initData'] || {};
            this.$el = $('<div class="modal fade in" tabindex="-1" role="dialog" style="display: block; padding-top: 10%">')
            this.render()
        },
        render: function() {
            var self = this ;
            this.$el.append(this.templ());
            this.$('#xClose').on('click', function(){
                self.remove()
            });
            this.renderView();
            this.switchType();

            this.$('.modal-dialog').draggable();
            this.$('.modal-dialog').css({'top': '0px', 'left': '0px'})
            this.$('.modal-content').resizable({
                minHeight: 300,
                minWidth: 300
            });
            this.$el.on('show.bs.modal', function () {
                $(this).find('.modal-body').css({
                    'max-height':'100%'
                });
            });
            $('body').addClass('modal-open');
            if(this.params['style'] !== undefined) {
                this.$('.modal-dialog').css(this.params['style']);
            }
            if(this.params['bodyStyle'] !== undefined) {
                this.$('.modal-body').css(this.params['bodyStyle']);
            }
        },
        renderView: function(){
            var self = this ;
            if(this.params['clazz'] != undefined) {
                this.$el.find('.modal-dialog').addClass(this.params['clazz'])
            }
            if(this.params['title'] !== undefined) {
                this.$el.find('.modal-title').append(this.params['title'])
            }
            if(this.params['body'] !== undefined) {
                if($.isFunction(this.params['body'])) {
                    var func = this.params['body']
                    this.$el.find('.modal-body').append(func({
                        'close': $.proxy(self.remove, self)
                    }))
                } else {
                    this.$el.find('.modal-body').append(this.params['body'])
                }

            }
            if(this.params['footer'] !== undefined) {
                this.$el.find('.modal-footer').append(this.params['footer'])
            }
        },
        switchType: function(){
            var self = this ;
            if(this.params['type'] == 'Alert') {
                var $closeBtn = $('<button type="button" class="" data-dismiss="modal">关闭</button>')
                $closeBtn.on('click', function(){
                    self.remove()
                });
                this.$el.find('.modal-footer').append($closeBtn)
            }else if(this.params['type'] == 'Confirm') {
                var $confirmBtn = $('<button type="button" class="">确认</button>')
                var $closeBtn = $('<button type="button" class="">关闭</button>')
                $confirmBtn.on('click', function(){
                    if($.isFunction(self.params['confirmFunc'])) {
                        self.params['confirmFunc']();
                        self.remove()
                    }
                });
                $closeBtn.on('click', function(){
                    self.remove()
                });
                this.$el.find('.modal-footer').append($confirmBtn)
                this.$el.find('.modal-footer').append($closeBtn)
            }else if(this.params['type'] == 'MultiBtn') {
                if(this.params['btns'] !== undefined) {
                    _.each(this.params['btns'], function(single) {
                        var $el = single['el'];
                        $el.on('click', function() {
                            single['click']();
                            self.remove();
                        });
                        self.$('.modal-footer').append($el);
                    })
                }
            }else if(this.params['type'] == 'OpenWinForm') {
                var $confirmBtn = $('<button type="button" class="">确认</button>')
                var $closeBtn = $('<button type="button" class="">关闭</button>')
                $confirmBtn.on('click', function(){
                    if($.isFunction(self.params['confirmFunc'])) {
                        self.params['confirmFunc']($.extend(_.clone(self.initD), self.$el.serializeJson()) , $.proxy(self.remove, self));
                    }
                });
                $closeBtn.on('click', function(){
                    self.remove()
                });
                this.$el.find('.modal-footer').append($confirmBtn)
                this.$el.find('.modal-footer').append($closeBtn)
            }
        },
        remove: function(){
            $('body').removeClass('modal-open');
            this.$el.remove()
        }

    });
    return Modal;
});