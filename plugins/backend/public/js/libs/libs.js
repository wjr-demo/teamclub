/**
 * Created by wjr on 16-12-20.
 */
define(['jquery'], function(){
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
            ].join('-')+
            ' ' +
            [ d.getHours().padLeft(),
                d.getMinutes().padLeft()].join(':');
        return dformat;
    };
    Libs.formatDateWith = function(timestamp, ch) {
        ch = ch || '-'
        var d = new Date(timestamp);
        var dformat = [
            d.getFullYear(),
            (d.getMonth()+1).padLeft(),
            d.getDate().padLeft()
        ].join(ch)
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

    var Func = window.Func = window.Func || {};

    Func.DictUtils = function(_status,method,param){
        var self = this;
        if($.isPlainObject(_status)){
            self._status = _status || {};
        }else{
            if($.inArray(method,['post','postJSON','get']) != -1){
                $[method](_status,param ? param : {},function(res){
                    self._status = {'dict':res};
                });
            }
        }
    };
    Func.DictUtils.prototype = {
        /**
         * 设置字典项
         * @param {String} type 类型
         * @param {Object || Array} itemObj 字典项值，可以为一个对象或一组对象
         * @method
         */
        setItem:function(type,itemObj){
            var _status = this._status, throwMsg = '';
            if(!_status.hasOwnProperty(type)){
                _status[type] = [];
            }

            if($.isArray(itemObj)){
                var i=0, _itemObj = {};
                for(; i<itemObj.length; i++){
                    _itemObj = itemObj[i];
                    if((typeof(throwMsg = this._hasItemObjFormat(itemObj)) !== "boolean")){
                        throw throwMsg;
                    }
                }
                _status[type] = itemObj;
            }else{
                if((typeof(throwMsg = this._hasItemObjFormat(itemObj)) !== "boolean")){
                    throw throwMsg;
                }
                _status[type].push(itemObj);
            }

        },
        /**
         * 指定下标设置对象类型的字典值
         * @param {String} type 类型
         * @param {Number} ind 值下标
         * @param {Object} itemObj 字典类型值
         * @method
         */
        setTypeIndItem:function(type,ind,itemObj){
            var _status = this._status;
            if(!_status.hasOwnProperty(type)){
                throw 'type does not exist!';
            }
            if((typeof(throwMsg = this._hasItemObjFormat(itemObj)) !== "boolean")){
                throw throwMsg;
            }

            var len = _status[type].length, tmp, temp;

            if(ind === -1 || ind > len){
                return undefined;
            }
            tmp = _status[type][ind];
            _status[type][ind] = itemObj;
            while(ind < len){
                temp = _status[type][ind + 1];
                _status[type][ind + 1] = tmp;
                tmp = temp;
                ind++;
            }
        },
        /**
         * 设置指定类型的所有值
         * @param {String} type 类型
         * @param {Array[Object]} 值
         * @method
         */
        setTypeAllItem:function(type, arr){
            if($.isArray(arr)){
                var i=0, _itemObj = {}, throwMsg = '';
                for(; i<arr.length; i++){
                    _itemObj = arr[i];
                    if((typeof(throwMsg = this._hasItemObjFormat(_itemObj)) !== "boolean")){
                        throw throwMsg;
                    }
                }
            }
            this._status[type] = arr;
        },
        /**
         * 设置字典值
         * @param {Object} _status 值
         * @method
         */
        setAllItem:function(_status){
            this._status = _status;
        },
        /**
         * 获取所有字典值
         * @return {Object} 字典值
         * @method
         */
        getAll:function(){
            return this._status;
        },
        /**
         * 根据类型获取字典值
         * @param {String} type 类型
         * @return {Array} 返回这个类型的所有值
         * @method
         */
        getTypeAll:function(type){
            var _status = this._status, throwMsg = '';
            if(typeof(throwMsg = this._hasType(type)) !== 'boolean'){
                console.log('getTypeAll:',throwMsg);
                return null;
            }
            return _status[type];
        },
        /**
         * 根据类型翻译对象值的名称
         * @param {String} type 类型
         * @param {Number || String} id 值
         * @return {Number || String || Boolean} 返回显示名
         */
        getName:function(type,id){
            var status = this._status[type], throwMsg = '';
            if(typeof(throwMsg = this._hasType(type)) !== 'boolean'){
                console.log('getName:',throwMsg);
                return null;
            }
            for(var i=0;i<status.length;i++){
                if(status[i].id == id){
                    return status[i].name;
                }
            }
            return '未知';
        },
        /**
         * 根据类型翻译对象显示名的值
         * @param {String} type  类型或key
         * @param {Number || String} name 显示名
         * @return {Number || String || Boolean} 返回值
         */
        getValue:function(type,name){
            var argLen = arguments.length, throwMsg = '', status = this._status[type];
            if(argLen == 1){
                if(typeof(this._hasType(type)) !== 'boolean'){
                    return '未知';
                }
                return status;
            }

            if(typeof(throwMsg = this._hasType(type)) !== 'boolean'){
                console.log('getValue:',throwMsg);
                return null;
            }

            for(var i=0;i<status.length;i++){
                if(status[i].name == name){
                    return status[i].id;
                }
            }
            return '-1';
        },
        _hasType:function(type){
            if(!this._status.hasOwnProperty(type)){
                return 'You need to type does not exist!';
            }
            return true;
        },
        _hasItemObjFormat:function(_itemObj){
            if(!_itemObj.hasOwnProperty('id') && !_itemObj.hasOwnProperty('name')){
                return 'You entered the wrong data format!';
            }
            return true;
        }
    };
})

