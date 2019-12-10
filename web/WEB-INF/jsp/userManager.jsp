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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${contextPage.request.contextPath}/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${contextPage.request.contextPath}/jquery-easyui-1.7.0/themes/icon.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="${contextPage.request.contextPath}/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript"
            src="${contextPage.request.contextPath}/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${contextPage.request.contextPath}/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="../../js/datagrid-export.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#userRolesWindow").window('center');
            $("#userRolesWindow").window('close');
        })

        //新增jsons
        function newUser() {
            $('#dlg').dialog('open').dialog('setTitle', '新增用户');
            $('#province').combobox({
                url: '${contextPage.request.contextPath}/user2/getProvince.action',
                //panelHeight:'auto',
                editable: false,
                panelHeight: '100px',
                valueField: 'id',
                textField: 'text'
            });
            $('#fm').form('clear');
            //$('#hiddenId').style.display = "none";
            document.getElementById('hiddenId').style.display = "none";
            document.getElementById('hiddenPass').style.display = "";
            /* document.getElementById('hiddenPass').style.display = "none";*/
        }

        //保存用户
        function saveUser() {
            var url;
            var msg;
            var errMsg;
            debugger
            var dlgTitle = $('#dlg').panel('options').title;
            if (dlgTitle == '修改用户') {
                url = '${contextPage.request.contextPath}/user2/editUser.action';
                msg = '修改用户成功！';
                errMsg = "修改用户失败！";
            }
            if (dlgTitle == '新增用户') {
                url = '${contextPage.request.contextPath}/user2/addUser.action';
                msg = '新增用户成功！';
                errMsg = "新增用户失败！";
            }
            var title = $('#dlg').get('Title');
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    debugger

                    var userName = $("#userName").val();
                    var flag;
                    $.post(
                        "${contextPage.request.contextPath}/user2/checkUserName.action",
                        {
                            userName: userName
                        },
                        function (result) {
                            var res = JSON.parse(result);
                            if (res.error == 'checkError') {
                                flag = false;
                                $.messager.alert({
                                    title: '消息',
                                    msg: "用户名重复！",
                                    icon: 'error',
                                    top: $(window).height() / 4
                                });

                            } else {
                                flag = true;
                            }

                            if (flag == false) {
                                return false;
                            } else {
                                return $(this).form('validate');
                            }
                        }
                    );


                },
                success: function (data) {
                    var res = JSON.parse(data);
                    if (res.success == 'editSucc') {
                        $.messager.alert({title: '消息', msg: "修改用户成功！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                    if (res.error == 'editError') {
                        $.messager.alert({title: '消息', msg: "修改用户失败！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                    if (res.success == 'addSucc') {
                        $.messager.alert({title: '消息', msg: "新增用户成功！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }
                    if (res.error == 'addError') {
                        $.messager.alert({title: '消息', msg: "新增用户失败！", icon: 'info', top: $(window).height() / 4});
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }

                }
            });
        };

        //修改用户
        function editUser() {
            document.getElementById('hiddenPass').style.display = "none ";
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                var uId = row.id;
                $('#dlg').dialog('open').dialog('setTitle', '修改用户');
                $('#province').combobox({
                    url: '${contextPage.request.contextPath}/user2/getProvince.action',
                    //panelHeight:'auto',
                    panelHeight: '100px',
                    valueField: 'id',
                    textField: 'text'
                });
                /* $('#fm').form('load', row);*/
                /* $("#fm").form('load',
                     "/user2/findUserById.action");*/

                $('#fm').form("load", "/user2/findUserById.action?uid=" + uId);
                /* $('#fm').form("load",{
                     uid:uId
                 });*/

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
                                "/user2/delUser.action",
                                {"id": Number(row.id)},
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

        //设置用户角色 开始
        function editUserRole() {
            var row = $('#dg').datagrid('getSelected');
            if (row == null) {
                $.messager.alert({title: '消息', msg: "清先选择一行！", icon: 'error', top: $(window).height() / 4});
            }
            var userId = row.id;
            if (row) {
                $("#userRolesWindow").window('open');
                $("#tree").tree({
                    url: '../../user2/loadUserRoles.action',
                    lines: true,
                    animate: true,
                    checkbox: true,
                    onLoadSuccess: function (node, data) {
                        //给角色 自动勾选上已有的资源
                        /*var node = $('#tree').tree('find', data[1]['children'][1]);
                        $('#tree').tree('check', node.target);
*/

                        /*    var node = $('#tree').tree('find', data[1]);
                            $('#tree').tree('check', node.target);*/
                        var treeData = data;
                        //查询勾选角色 可以访问的所有的资源
                        $.ajax({
                            type: "GET",
                            url: '../../user2/queryUsersRoleById.action',
                            data: {userId: userId},
                            success: function (result) {
                                debugger
                                var a = result;
                                for (var i = 0; i < treeData.length; i++) {
                                    var child = treeData[i]['rId'];
                                    for (var j = 0; j < result.length; j++) {
                                        var res = result[j].rid;
                                        if (child == res) {
                                            /* var node2 = $('#tree').tree('find', child);*/
                                            $('#tree').tree('check', treeData[i].target);
                                        }
                                    }

                                }

                            }
                        });
                    }
                })
                ;
            } else {
                $.messager.alert({title: '消息', msg: "请先选择一条数据！", icon: 'error', top: $(window).height() / 4});
            }
        }

        function setUserRole2() {
            debugger
            var row = $('#dg').datagrid('getSelected')['id'];
            var nodes = $('#tree').tree('getChecked');
            var map = {};
            for (var j = 0; j < nodes.length; j++) {
                map[j] = nodes[j]['rId'];
            }
            var mapJson = JSON.stringify(map);
            $.ajax({
                // url: '../../user2/setUserRole.action',
                url: '../../user2/setUserRole3.action',
                data: {
                    "row": row,
                    "map": mapJson
                },
                dataType: "json",
                contenType: 'application/json',
                type: "POST",
                traditional: true,
                success: function (responseJSON) {
                    // your logic
                    alert('Ok');
                }
            });
        }

        function setUserRole() {
            debugger
            var row = $('#dg').datagrid('getSelected')['id'];
            var nodes = $('#tree').tree('getChecked');
            var map = [];
            for (var j = 0; j < nodes.length; j++) {
                map[j] = nodes[j]['rId'];
            }
            $.ajax({
                // url: '../../user2/setUserRole.action',
                url: '../../user2/setUserRole3.action',
                data: {
                    "row": row,
                    "map": map
                },
                type: "POST",
                traditional: true,
                success: function (data) {
                    var res = JSON.parse(data);
                    if (res.success == 'success') {
                        $("#userRolesWindow").window('close');
                        $.messager.alert({title: '消息', msg: "角色设置成功！", icon: 'info', top: $(window).height() / 4});

                    }
                    if (res.error == 'error') {
                        $("#userRolesWindow").window('close');
                        $.messager.alert({title: '消息', msg: "角色设置失败！", icon: 'error', top: $(window).height() / 4});

                    }

                }
            });
        }

        /**关键字查询**/
        function selectUsersByKeyWord() {
            var keyword = $("#keyword").val();
            $.ajax({
                url: '/user2/queryUserInfo2.action',
                data: {
                    "keyword": keyword

                },
                dataType: "json",
                contenType: 'application/json',
                type: "POST",
                traditional: true,
                success: function (responseJSON) {
                    // your logic
                    $('#dg').datagrid('loadData', responseJSON);

                }
            });
        }

        //新增或者修改的时候验证用户名是否重复，如果重复则给出提示
        function checkUserName() {
            var userName = $("#userName").val();
            var flag;
            $.post(
                "${contextPage.request.contextPath}/user2/checkUserName.action",
                {
                    userName: userName
                },
                function (result) {
                    var res = JSON.parse(result);
                    if (res.error == 'checkError') {
                        flag = false;
                        $.messager.alert({title: '消息', msg: "用户名重复！", icon: 'error', top: $(window).height() / 4});

                    } else {
                        flag = true;
                    }
                }
            );
            return flag;
        }


    </script>

    <style type="text/css">
        div.input-group a {
            margin-right: 10px;
            font-size: 18px;
        }
    </style>
</head>

<body>
<table id="dg" class="easyui-datagrid" title="用户列表" style="width:100%;height:460px"
       toolbar="#toolbar"
       rownumbers="true" fitColumns="true" singleSelect="true"
       pagination="true"
       data-options="<%--singleSelect:true,checkOnSelect:true,--%>collapsible:true,url:'/user2/queryUserInfo2.action',method:'get'  ">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:100">id</th>
        <th data-options="field:'userName',width:100">用户名</th>
        <th data-options="field:'sex',width:100">性别</th>
        <th data-options="field:'age',width:100">年龄</th>
        <th data-options="field:'p_Name',width:100">省份</th>
        <th data-options="field:'salary',width:100">薪水</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <%--<div class="col-lg-12">
        <a href="#" class="btn btn-sm btn-primary "  iconCls="icon-add" plain="true" onclick="newUser()">新增用户</a>
        <a href="#" class="btn btn-sm btn-primary " iconCls="icon-edit" plain="true" onclick="editUser()">修改用户</a>
        <a href="#" class="btn btn-sm btn-primary " iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        <a href="#" class="btn btn-sm btn-primary " iconCls="icon-edit" plain="true" onclick="editUserRole()">设置角色</a>
    </div>--%>


    <div class="col-lg-9">

        <div class="input-group">
            <a href="#" class="btn btn-sm btn-primary btn-success" iconCls="icon-add" plain="true" onclick="newUser()">新增用户</a>
            <a href="#" class="btn btn-sm btn-info " iconCls="icon-edit" plain="true" onclick="editUser()">修改用户</a>
            <a href="#" class="btn btn-sm btn-primary " iconCls="icon-remove" plain="true"
               onclick="destroyUser()">删除</a>
            <a href="#" class="btn btn-sm btn-warning " iconCls="icon-edit" plain="true"
               onclick="editUserRole()">设置角色</a>
            <%-- <a href="#" class="btn btn-sm btn-warning" iconCls="icon-edit" plain="true" onclick="exportExcel">导出excel</a>--%>

            <a href="#" class="btn btn-sm btn-info" onclick="$('#dg').datagrid('toExcel','dg.xls')">导出excel</a>
            <a href="#" class="btn btn-sm btn-primary" onclick="$('#dg').datagrid('print','DataGrid')">Print</a>

            <input type="text" class="form-control" style="width:auto" id="keyword" placeholder="请输入用户id 或者用户名">
            <button class="btn btn-default" type="button" onclick="selectUsersByKeyWord()">Go!</button>
            <%--  <span class="input-group-btn">
        <button class="btn btn-default" type="button" onclick="selectUsersByKeyWord()">Go!</button>
      </span>--%>
        </div>

    </div>


    <%-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改用户</a>
     <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
     <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUserRole()">设置角色</a>--%>

</div>

<!--新增(修改)用户-->
<div id="dlg" class="easyui-dialog" modal="true" style="width:400px;height:350px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table>
            <tr id="hiddenId">
                <td>id:</td>
                <td><input name="id" class="easyui-validatebox" readonly="readonly"></td>
            </tr>
            <tr>
                <td>用户名:</td>
                <td><input name="userName" id="userName" class="easyui-validatebox" required="true"
                           onblur="checkUserName()"></td>
            </tr>
            <tr id="hiddenPass">
                <td>密码:</td>
                <td><input name="password" class="easyui-passwordbox" prompt="请输入密码" iconWidth="28"
                           required="password" style="width:100%;height:34px;padding:10px"
                ></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="sex" style="width:170px;" required="true"
                            data-options="editable:false,panelHeight:'auto'">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>年龄</td>
                <td><input type="text" class="easyui-numberbox" name="age" value="100" data-options="min:10"
                           required="true"></input>
                </td>
            </tr>
            <tr>
                <td>省份:</td>
                <td>
                    <input id="province" name="p_Id">
                </td>
            </tr>
            <tr>
                <td>薪水:</td>
                <td><input name="salary" class="easyui-validatebox" required="true"></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>


<div id="userRolesWindow" class="easyui-window" title="用户角色窗口" style="width:800px;height:450px;"
     data-options="iconCls:'icon-abc',modal:true,inline:true">
    <div id="tree" style="width:200px;display:inline-block">

    </div>
    <div style="margin: 30px">
        <button type="button" class="btn btn-primary btn-sm" onclick="setUserRole()">保存</button>
    </div>
</div>
</body>
</html>
