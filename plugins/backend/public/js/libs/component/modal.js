/**
 * Created by wjr on 16-9-2.
 */
/**
 * Created by wjr on 16-7-22.
 */
define(['jquery','underscore','common'], function($, _) {

    var Modal = Backbone.Base.extend({
        templ: _.template('<div class="modal-dialog">\
            <div class="modal-content">\
                <div class="modal-header">\
                    <button type="button" id="xClose" class="close" data-dismiss="modal" aria-label="Close">\
                        <span aria-hidden="true">&times;</span>\
                    </button>\
                    <h4 class="modal-title"></h4>\
                </div>\
                <div class="modal-body"></div>\
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
            this.params = params
            this.$el = $('<div class="modal fade in" tabindex="-1" role="dialog" style="display: block;">')
            this.render()
        },
        render: function() {
            var self = this ;
            this.$el.append(this.templ())
            this.$('#xClose').on('click', function(){
                self.remove()
            })
            if(this.params['clazz'] != undefined) {
                this.$el.find('.modal-dialog').addClass(this.params['clazz'])
            }
            if(this.params['title'] !== undefined) {
                this.$el.find('.modal-title').append(this.params['title'])
            }
            if(this.params['body'] !== undefined) {
                this.$el.find('.modal-body').append(this.params['body'])
            }
            if(this.params['footer'] !== undefined) {
                this.$el.find('.modal-footer').append(this.params['footer'])
            }
            if(this.params['type'] == 'Alert') {
                var $closeBtn = $('<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>')
                $closeBtn.on('click', function(){
                    self.remove()
                });
                this.$el.find('.modal-footer').append($closeBtn)
            }else if(this.params['type'] == 'Confirm') {
                var $confirmBtn = $('<button type="button" class="btn btn-primary">确认</button>')
                var $closeBtn = $('<button type="button" class="btn btn-default">关闭</button>')
                $confirmBtn.on('click', function(){
                    if($.isFunction(self.params['confirmFunc'])) {
                        self.params['confirmFunc']()
                        self.remove()
                    }
                });
                $closeBtn.on('click', function(){
                    self.remove()
                })
                this.$el.find('.modal-footer').append($confirmBtn)
                this.$el.find('.modal-footer').append($closeBtn)
            }
        },
        remove: function(){
            this.$el.remove()
        }

    });
    return Modal;
});