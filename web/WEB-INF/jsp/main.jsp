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
    //收藏用logo图标
    <link rel="bookmark" type="image/x-icon" href="${contextPage.request.contextPath}/ico/favicon.ico"/>
    //网站显示页logo图标
    <link rel="shortcut icon" href="${contextPage.request.contextPath}/ico/favicon.ico">

    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        $(function () {
            /* $("#dg").datagrid({
                 title:"用户信息列表",
                 url:"

            /user/userList.action",
                method:"get",
                pagination:true,
                pageSize:20,
                pageList:[5,8,10,50],
                row:true,
                singleSelect:true,
                fit:true,
                border:false,
                idField:"id",
                fitColumns:true, //去除滚动条
                columns:[[
                    {field:'id',title:'用户ID',width:200,hidden:true,align:'center'},
                    {field:'userName',title:'用户名',width:200,align:'center'},
                    {field:'sex',title:'性别',width:200,align:'center'},
                    {field:'age',title:'年龄',width:200,align:'center'},
                    {field:'province',title:'省份',width:200,align:'center'},
                    {field:'salary',title:'薪水',width:200,align:'center'}
                ]]
            })*/
        });

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
                url = '/user2/editUser.action';
                msg = '修改用户成功！';
                errMsg = "修改用户失败！";
            }
            if (dlgTitle == '新增用户') {
                url = '/user2/addUser.action';
                msg = '新增用户成功！';
                errMsg = "新增用户失败！";
            }
            var title = $('#dlg').get('Title');
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (result) {
                    debugger
                    //var result = eval('(' + result + ')');
                    //var result = result;
                    if (result == '错误的页面') {
                        $.messager.show({
                            title: 'Error',
                            msg: errMsg
                        });
                        //刷新datagrid 列表
                        $('#dg').datagrid('reload');
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: msg
                        });

                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
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
                            /*      $.post(
                                      "/user2/delUser.action",
                                      {
                                          "id":Number(row.id)
                                      },
                                      function (data,status) {
                                       alert("数据" + data + " 状态" +status );
                                       if(status == 'success'){
                                           $.messager.show({
                                               title: '提示',
                                               msg: '删除成功！'
                                           });
                                           //重新加载datagrid
                                           $('#dg').datagrid('reload');
                                       }else{
                                           $.messager.show({
                                               title: '提示',
                                               msg: '删除失败！'
                                           });
                                           $('#dg').datagrid('reload');
                                       }
                                          $('#dg').datagrid('reload');
                                  }) ;*/


                            $.post("/user2/delUser.action",
                                {
                                    "id": Number(row.id)
                                }
                            );

                            $('#dg').datagrid('reload');


                        }
                    }
                );
            } else {
                alert("请先选择一行要修改的记录！");
            }
        }


    </script>
</head>
<body>
欢迎您，${success} <a href="/user2/logOut.action">退出</a>

<table id="dg" class="easyui-datagrid" title="用户列表" style="width:100%;height:500px"
       toolbar="#toolbar"
       rownumbers="true" fitColumns="true" singleSelect="true"
       pagination="true"
       data-options="singleSelect:true,collapsible:true,url:'/user2/queryUserInfo2.action',method:'get'  ">
    <thead>
    <tr>
        <th data-options="field:'id',width:100">id</th>
        <th data-options="field:'userName',width:100">用户名</th>
        <th data-options="field:'sex',width:100">性别</th>
        <th data-options="field:'age',width:100">年龄</th>
        <th data-options="field:'province',width:100">省份</th>
        <th data-options="field:'salary',width:100">薪水</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增用户</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改用户</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
</div>

<!--新增用户-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User 信息</div>
    <form id="fm" method="post">
        <table>
            <tr id="hiddenId">
                <td>id:</td>
                <td><input name="id" class="easyui-validatebox" readonly="readonly"></td>
            </tr>
            <tr>
                <td>用户名:</td>
                <td><input name="userName" class="easyui-validatebox" required="true"></td>
            </tr>
            <%--<tr>
                <td>性别:</td>
                <td><input name="sex" class="easyui-validatebox" required="true"></td>
            </tr>--%>
            <tr>
                <td>性别:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="sex" style="width:200px;" required="true">
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
                <td><input name="province" class="easyui-validatebox" required="true"></td>
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

</body>
</html>
