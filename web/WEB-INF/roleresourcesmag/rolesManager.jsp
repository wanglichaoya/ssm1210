<%--
  Created by IntelliJ IDEA.
  User: cube
  Date: 2019/7/11
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Easyui案例</title>
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#roleResourcesWindow").window('center');
            $("#roleResourcesWindow").window('close');
        })

        //新增
        function newUser() {
            $('#dlg').dialog('open').dialog('setTitle', '新增用户');
            $('#fm').form('clear');
            //$('#hiddenId').style.display = "none";
            document.getElementById('hiddenId').style.display = "none";
        }

        //保存用户
        function saveUser() {
            var url;
            var msg;
            var errMsg;
            debugger
            var dlgTitle = $('#dlg').panel('options').title;
            if (dlgTitle == '修改用户') {
                url = '/RoleRes/editRoles.action';
                msg = '修改用户成功！';
                errMsg = "修改用户失败！";
            }
            if (dlgTitle == '新增用户') {
                url = '/RoleRes/addRoles.action';
                msg = '新增用户成功！';
                errMsg = "新增用户失败！";
            }
            var title = $('#dlg').get('Title');
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (data) {
                    var res = JSON.parse(data);
                    if (res.success == 'editSucc') {
                        $.messager.alert({title: '消息', msg: "修改角色成功！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                    if (res.error == 'editError') {
                        $.messager.alert({title: '消息', msg: "修改角色失败！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                    if (res.success == 'addSucc') {
                        $.messager.alert({title: '消息', msg: "新增角色成功！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                    if (res.error == 'addError') {
                        $.messager.alert({title: '消息', msg: "新增角色失败！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }

                }
            });
        };

        //修改用户
        function editUser() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '修改用户');
                $('#fm').form('load', row);
            } else {
                alert("请先选择一行要修改的记录！");
            }
        };

        //删除用户
        function destroyUser() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('提示', '你确定要删除这条信息吗？', function (r) {
                        if (r) {
                            $.post(
                                "/RoleRes/delRoles.action",
                                {"rId": Number(row.rId)},
                                function (data) {
                                    var res = JSON.parse(data);
                                    if (res.success) {
                                        /*$.messager.show({
                                            title: '提示',
                                            top:$(window).height()/4,
                                            msg: "删除成功！"
                                        });*/
                                        $.messager.alert({
                                            title: '消息',
                                            msg: '删除成功！',
                                            icon: 'info',
                                            top: $(window).height() / 4
                                        });
                                        $('#dg').datagrid('reload');
                                    } else {
                                        $.messager.alert({
                                            title: '消息',
                                            msg: '删除成功！',
                                            icon: 'error',
                                            top: $(window).height() / 4
                                        });
                                    }
                                }
                            );
                        }
                    }
                );
            } else {
                alert("请先选择一行要修改的记录！");
            }
        }

        //编辑角色 ，给角色赋予资源
        function editRoleResources() {
            var row = $('#dg').datagrid('getSelected');
            var roleId = row.rId;
            if (row) {
                $("#roleResourcesWindow").window('open');
                $("#tree").tree({
                    // url: '../../loginController/loadTreeByRole.action',
                    //url: '../../RoleRes/queryRoleResources.action?params=' + row['rId'],
                    url: '../../RoleRes/queryAllResources.action',
                    lines: false,
                    animate: true,
                    checkbox: true,
                    onLoadSuccess: function (node, data) {
                        //给角色 自动勾选上已有的资源
                        /*var node = $('#tree').tree('find', data[1]['children'][1]);
                        $('#tree').tree('check', node.target);
*/
                        var treeData = data;
                        //查询勾选角色 可以访问的所有的资源
                        $.ajax({
                            type: "GET",
                            url: '../../RoleRes/queryResByRole.action',
                            data: {roleId: roleId},
                            success: function (result) {
                                debugger
                                var a = result;
                                for (var i = 0; i < treeData.length; i++) {
                                    var childList = treeData[i]['children'];
                                    for (var k = 0; k < childList.length; k++) {
                                        var child = childList[k]['mId'];
                                        for (var j = 0; j < result.length; j++) {
                                            var res = result[j].rId;
                                            if (child == res) {
                                                var node2 = $('#tree').tree('find', treeData[i]['children'][k]);
                                                $('#tree').tree('check', node2.target);
                                            }
                                        }
                                    }

                                }
                                /* if (data.code == 200) {
                                     //回显已有的权限
                                     var root = SysResourceTree.tree('getRoots'); // 取到树的根节点
                                     for (var i in root) {
                                         for (var j in data.data) {
                                             console.info(data.data[j]);
                                             SysRole.resource.checkTreeNode(root, data.data[j]);
                                         }
                                     }
                                 }*/
                            }
                        });
                    }

                });
            } else {
                $.messager.alert({title: '消息', msg: "请先选择一条数据！", icon: 'error', top: $(window).height() / 4});
            }
        }

        function editRoleReso() {
            //获取选中的数的数据
            var row = $('#dg').datagrid('getSelected')['rId'];
            var nodes = $('#tree').tree('getChecked');
            var node = [];
            var map = {};
            for (var i = 0; i < nodes.length; i++) {
                //node.push(nodes[i]['mId']);
                map[i] = nodes[i]['mId'];

            }
            var root = new Array();
            var map2 = {};

            for (var j = 0; j < nodes.length; j++) {
                map2[j] = nodes[j]['mId'];
                root[j] = nodes[j]['mId'];
            }
            var mapJson = JSON.stringify(map);
            var mapJson2 = JSON.stringify(map2);


            $.ajax({
                url: '../../RoleRes/setRoleResources.action',
                data: {
                    "selectedIDs": node,
                    "rowId": row,
                    'root': root,
                    "map2": mapJson2
                },
                //data: _list,
                dataType: "json",
                contenType: 'application/json',
                type: "POST",
                traditional: true,
                success: function (data) {
                    //var res = JSON.parse(data);
                    if (data.success == 'authorizationSucc') {
                        $.messager.alert({
                            title: '消息',
                            msg: '角色授权成功！',
                            icon: 'info',
                            top: $(window).height() / 4
                        });
                        $('#roleResourcesWindow').dialog('close');
                    }
                    if (data.error == 'authorizationError') {
                        $.messager.alert({
                            title: '消息',
                            msg: '角色授权失败！',
                            icon: 'error',
                            top: $(window).height() / 4
                        });
                        $('#roleResourcesWindow').dialog('close');
                    }
                    if (data.success == 'roleResClearSucc') {
                        $.messager.alert({
                            title: '消息',
                            msg: '角色对应的资源全部清空！',
                            icon: 'info',
                            top: $(window).height() / 4
                        });
                        $('#roleResourcesWindow').dialog('close');
                    }
                }
            });


            /*$.post(
                 '../../RoleRes/setRoleResources.action',
                 {params:node},
                 function(data){
                     var res = JSON.parse(data);
                     if (res.success == 'editSucc') {
                         $.messager.alert({title: '消息', msg: "角色设置资源成功！", icon: 'info', top: $(window).height() / 4});
                         $('#roleResourcesWindow').dialog('close');
                     }
                 }
            );*/
        }

    </script>
</head>

<body>
<table id="dg" class="easyui-datagrid" title="角色列表" style="width:100%;height:460px"
       toolbar="#toolbar"
       rownumbers="true" fitColumns="true" singleSelect="true"
       pagination="true"
       data-options="singleSelect:true,collapsible:true,url:'/RoleRes/queryRoles.action',method:'get'  ">
    <thead>
    <tr>
        <th data-options="field:'rId',width:100">id</th>
        <th data-options="field:'rName',width:100">角色名称</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增角色</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改角色</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除角色</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-abc" plain="true" onclick="editRoleResources()">设置可访问的资源</a>
</div>

<!--新增角色-->
<div id="dlg" class="easyui-dialog" modal="true" style="width:400px;height:200px;padding:50px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <tr id="hiddenId">
                <td>角色id:</td>
                <td><input name="rId" class="easyui-validatebox" readonly="true"></td>
            </tr>
            <tr>
                <td>角色名称:</td>
                <td><input name="rName" class="easyui-validatebox" required="true"></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<div id="roleResourcesWindow" class="easyui-window" title="角色资源窗口" style="width:800px;height:450px;"
     data-options="iconCls:'icon-abc',modal:true,inline:true">
    <div id="tree" style="width:200px;display:inline-block">

    </div>

    <div style="padding: 20px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" <%--plain="true"--%> onclick="editRoleReso()">保存资源</a>
    </div>
</div>
</body>
</html>
