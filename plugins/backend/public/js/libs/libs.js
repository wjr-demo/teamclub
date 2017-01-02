/**
 * Created by wjr on 16-12-20.
 */
define(['jquery'], function(){
    //除法函数，用来得到精确的除法结果
    //说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
    //调用：accDiv(arg1,arg2)
    //返回值：arg1除以arg2的精确结果
    function accDiv(arg1,arg2){
        var t1=0,t2=0,r1,r2;
        try{t1=arg1.toString().split(".")[1].length}catch(e){}
        try{t2=arg2.toString().split(".")[1].length}catch(e){}
        with(Math){
            r1=Number(arg1.toString().replace(".",""));
            r2=Number(arg2.toString().replace(".",""));
            return (r1/r2)*pow(10,t2-t1);
        }
    }
    //给Number类型增加一个div方法，调用起来更加方便。
    Number.prototype.div = function (arg){
        return accDiv(this, arg);
    };
    //乘法函数，用来得到精确的乘法结果
    //说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
    //调用：accMul(arg1,arg2)
    //返回值：arg1乘以arg2的精确结果
    function accMul(arg1,arg2)
    {
        var m=0,s1=arg1.toString(),s2=arg2.toString();
        try{m+=s1.split(".")[1].length}catch(e){}
        try{m+=s2.split(".")[1].length}catch(e){}
        return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
    }
    //给Number类型增加一个mul方法，调用起来更加方便。
    Number.prototype.mul = function (arg){
        return accMul(arg, this);
    };
    //加法函数，用来得到精确的加法结果
    //说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
    //调用：accAdd(arg1,arg2)
    //返回值：arg1加上arg2的精确结果
    function accAdd(arg1,arg2){
        var r1,r2,m;
        try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
        try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
        m=Math.pow(10,Math.max(r1,r2));
        return (arg1*m+arg2*m)/m;
    }
    //给Number类型增加一个add方法，调用起来更加方便。
    Number.prototype.add = function (arg){
        return accAdd(arg,this);
    }
    //减法函数
    function accSub(arg1,arg2){
        var r1,r2,m,n;
        try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
        try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
        m=Math.pow(10,Math.max(r1,r2));
        //last modify by deeka
        //动态控制精度长度
        n=(r1>=r2)?r1:r2;
        return ((arg2*m-arg1*m)/m).toFixed(n);
    }
    ///给number类增加一个sub方法，调用起来更加方便
    Number.prototype.sub = function (arg){
        return accSub(arg,this);
    }

    Number.prototype.padLeft = function(base,chr){
        var  len = (String(base || 10).length - String(this).length)+1;
        return len > 0? new Array(len).join(chr || '0')+this : this;
    }
    var Libs = window.Libs = window.Libs || {};
    Libs.Dicts = {}
    Libs.Dicts['STUDY_LEVEL'] = [{"id":1,"name":"小学"},{"id":2,"name":"初级中学"},{"id":3,"name":"高级中学"},{"id":4,"name":"大学专科"},{"id":5,"name":"大学本科"},{"id":6,"name":"硕士研究生"},{"id":7,"name":"博士研究生"}]

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

    Func.convertToFour = function(name) {
        if(name.length < 2) {
            return name
        }else if(name.length == 2){
            return name[0] + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + name[1];
        }else if(name.length == 3){
            return name[0] + '&nbsp;&nbsp;' + name[1] + '&nbsp;&nbsp;' +name[2];
        }
    }

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
    /**
     * 金额千分位处理类
     */
    Func.NumUtils = function(_number){
        var num = _number + "";
        num = num.replace(new RegExp(",","g"),"");
        // 正负号处理
        var symble = "";
        if(/^([-+]).*$/.test(num)) {
            symble = num.replace(/^([-+]).*$/,"$1");
            num = num.replace(/^([-+])(.*)$/,"$2");
        }

        if(/^[0-9]+(\.[0-9]+)?$/.test(num)) {
            var num = num.replace(new RegExp("^[0]+","g"),"");
            if(/^\./.test(num)) {
                num = "0" + num;
            }

            var decimal = num.replace(/^[0-9]+(\.[0-9]+)?$/,"$1");
            var integer= num.replace(/^([0-9]+)(\.[0-9]+)?$/,"$1");

            var re=/(\d+)(\d{3})/;

            //符号 + 小数点前的数值 + 小数点后的数值
            var v = symble + integer + decimal;
            v = v == '' ? '0' : v;
            v = parseFloat(v).toFixed(2);
            while(re.test(v)){
                v = v.replace(re,"$1,$2");
            }
            this._value = v;
        } else {
            this._value = _number;
        }
    };
    Func.NumUtils.prototype = {
        getValue:function(){
            return this._value;
        }
    };


    var Department = window.Department = window.Department || {};
    Department.ADMINISTRATOR = "ADMINISTRATOR" //行政部
    Department.FINANCE = "FINANCE" //财务部
    Department.BUSINESS = "BUSINESS" //商务部
    Department.PRODUCER = "PRODUCER" //制造部
    Department.PURCHASE = "PURCHASE" //采购部
    Department.TECHNOLOGY = "TECHNOLOGY" //技术部
    Department.STORAGE = "STORAGE"  //仓库部
    Department.GENERALMANAGER = "GENERALMANAGER" //总经办

})

