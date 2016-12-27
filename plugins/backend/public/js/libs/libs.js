/**
 * Created by wjr on 16-12-20.
 */
define([], function() {
    Number.prototype.padLeft = function(base,chr){
        var  len = (String(base || 10).length - String(this).length)+1;
        return len > 0? new Array(len).join(chr || '0')+this : this;
    }
    var Libs = window.Libs = window.Libs || {};
    Libs.formatDate = function(timestamp) {
        var d = new Date(timestamp);
        var dformat = [
                    d.getFullYear(),
                    (d.getMonth()+1).padLeft(),
                    d.getDate().padLeft()
                    ].join('/')+
                ' ' +
                [ d.getHours().padLeft(),
                    d.getMinutes().padLeft(),
                    d.getSeconds().padLeft()].join(':');
        return dformat;
    };

    Libs.tableLine = function(title, modify, proxy) {
        var $hd = $('<div class="data-hd">')
        var $hdi = $('<label class="data-hd-i">' +  title + '</label>')
        var $hda = $('<label class="data-hd-a">[ ' + modify + ' ]</label>')
        $hda.on('click', proxy)
        $hd.append($hdi).append($hda)
        return $hd;
    };

    return Libs;
});