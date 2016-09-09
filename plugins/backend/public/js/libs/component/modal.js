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
            this.$el = $('<div>')
            this.render()
        },
        render: function() {
            var self = this ;
            this.$el.append(this.templ())
            if(this.params['type'] = 'small') {
                this.$el.find('modal-dialog').addClass('modal-sm')
            }else if(this.params['type'] = 'large'){
                this.$el.find('modal-dialog').addClass('modal-lg')
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
            var $closeBtn = $('<button type="button"　class="btn btn-default" data-dismiss="modal">关闭</button>')
            $closeBtn.on('click', function(){
                self.remove()
            })
            this.$el.find('.modal-footer').append($closeBtn)
        },
        remove: function(){
            this.$el.remove()
        }

    });
    return Modal;
});