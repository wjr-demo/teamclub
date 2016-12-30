/**
 * Created by wjr on 16-12-16.
 */
define(function(){
    var enumsPopUp = {}
    /***
     * 应用列表
     * @type {{url: string, columns: *[], fields: *[], setField: string, showField: string}}
     */
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
    /***
     * 角色列表
     * @type {{url: string, columns: *[], setField: string, showField: string}}
     */
    enumsPopUp['ROLELIST'] = {
        url: '/backend/rolemanager/list',
        columns: [{
            title: "角色名称",
            data: "rolename"
        },{
            title: '角色描述',
            data: 'description'
        }],
        setField: 'id',
        showField: 'rolename'
    }

    /*** 部门管理 */
    enumsPopUp['DEPTLIST'] = {
        url: '/backend/appdepartment/list',
        columns: [{
            title: '部门名称',
            data: 'departName'
        }],
        setField: 'id',
        showField: 'departName'
    }
    return enumsPopUp
})