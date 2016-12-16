/**
 * Created by wjr on 16-12-16.
 */
define(function(){
    var enumsPopUp = {}
    enumsPopUp['APPMANAGER'] = {
        url: '/backend/appmanager/list',
        columns: [{
            title: "应用编码",
            data: "appid"
        },{
            title: '应用Key',
            data: 'appkey'
        },{
            title: "应用名称",
            data: "appname"
        }],
        fields: [{
            title: '应用编码',
            name: 'appid'
        },{
            title: '应用名称',
            name: 'appname'
        }],
        setField: 'appid',
        showField: 'appname'
    }
    return enumsPopUp
})