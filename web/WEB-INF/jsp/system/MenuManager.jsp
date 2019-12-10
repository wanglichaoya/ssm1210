<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/16
  Time: 上午 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/icon.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <style type="text/css">
        #fm {
            padding-top: 20px;
            padding-left: 20px;
        }

    </style>
    <script type="text/javascript">
        $(function () {
            document.getElementById('hiddenId').style.display = "none";
            $('#parentMenu').combobox({
                //url: '/user2/getProvince.action',
                url: '/system/getParentMenu.action',
                //panelHeight:'auto',
                panelHeight: '100px',
                valueField: 'id',
                textField: 'text',
                onChange: function (data) {
                    $("#parentMenuId").val(data);
                }
            });
        });

        /**提交表单**/
        function submitForm() {
            $('#fm').form('submit', {
                url: "/system/addMenu.action",
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (data) {
                    var res = JSON.parse(data);
                    if (res.success == 'addSucc') {
                        $.messager.alert({title: '消息', msg: "菜单新增成功！", icon: 'info', top: $(window).height() / 4});
                    } else if (res.error == "addError") {
                        $.messager.alert({title: '消息', msg: "菜单新增失败！", icon: 'error', top: $(window).height() / 4});

                    } else {
                        $.messager.alert({
                            title: '填写错误',
                            msg: "您选择的父级菜单下面有重复的菜单，确认！",
                            icon: 'error',
                            top: $(window).height() / 4
                        });
                    }
                    $('#fm').form('clear');

                }
            });
        }
    </script>
</head>
<body>

<form id="fm" method="post">
    <table>
        <tr id="hiddenId">
            <td>id:</td>
            <td><input name="MPid" id="parentMenuId" class="easyui-validatebox" readonly="readonly"></td>
        </tr>

        <tr>
            <td>父级菜单:</td>
            <td>
                <input id="parentMenu" name="parentMenu" style="width: 330px">
            </td>
        </tr>
        <tr>
            <td>菜单名称:</td>
            <td><input name="MName" class="easyui-validatebox" required="true" style="width: 330px"></td>
        </tr>
        <tr>
            <td>菜单路径:</td>
            <td><input name="MUrl" class="easyui-validatebox" required="true"
                       placeholder="示范：/urlRouteJump/toMenuManager.action" style="width: 330px"></td>
        </tr>

        <tr>
            <td>
                <button class="btn btn-info" type="button" onclick="submitForm()">提交</button>
            </td>
            <td>
                <button class="btn btn-info" type="reset">清空</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
