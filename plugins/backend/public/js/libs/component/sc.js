/**
 * Created by wjr on 16-11-21.
 */
var SC = window.SC = window.SC || {};
SC.Alert = function(title, content, type){
    title = title || '提示'
    content = content || ''
    $('body').append((new Modal({
        'type': type,
        'title': $("<div>" + title + "</div>"),
        'body': $("<div>" + content + "</div>")
    }).$el))
};