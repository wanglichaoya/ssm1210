<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: cube
  Date: 2019/7/4
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理系统</title>
    <%-- 收藏用logo图标--%>
    <link rel="bookmark" type="image/x-icon" href="${contextPage.request.contextPath}/ico/favicon.ico"/>
    <%-- //网站显示页logo图标--%>
    <link rel="shortcut icon" href="${contextPage.request.contextPath}/ico/favicon.ico">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../js/easyui-menu-tree/easyui-menu-tree.js"></script>
    <style>
        .head-info {
            margin: 5px;
            float: right;
        }

        .logo-info {
            display: inline-block;
            margin: 5px;
            color: blue;
        }

        #laba-png {
            display: inline-block;
            position: relative;
            top: 2.8px;

        }
    </style>
    <script>
        (function () {
            refreshQuery();
        })

        setInterval(
            "document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",
            500);

        function refreshQuery() {
            document.getElementById('nowTime').innerHTML = new Date().toLocaleString()
                + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());

        }
    </script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',title:'',split:false" style="height:40px;">
    <%--<img src="http://hbimg.huabanimg.com/ccf3ccab410471da2a9ddfdbbacdf2f03faeae6f41f66-HNlFOI_fw658"
         width="100px" height="20px">--%>
    <%--<img src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2131970252,1447438032&fm=26&gp=0.jpg"
         width="500px" height="30px">--%>

    <span class="logo-info" style="font-size: larger;display: inline-block;">权限管理系统</span>
    <span id="laba-png"><img src="../../image/laba.png" style="width:25px;height:30px;"></span>
    <span style="color: red;display: inline-block;text-align: center">
            <marquee direction="left">现在时间：<span id="nowTime"></span></marquee>
        </span>
    <div class="head-info" style="font-size: larger;">
        欢迎您，<span style="color: red">${success}</span> ，您的角色是：<span style="color: red">${userRoleName}</span> <span
            style="text-align: right"> <a href="/urlRouteJump/logOut.action">退出</a></span>
    </div>
</div>
<div data-options="region:'south',title:'',split:false" style="height:30px;">
    <span style="padding:5px;display: inline-block">版权所有，翻版必究！Copyright © 2004 - 2019</span>
</div>
<div data-options="region:'west',title:'菜单',split:true" style="width:150px;">
    <div id="tree">
    </div>
</div>
<div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    <div id="tabs" class="easyui-tabs" style="width:100%;height:532px;">
    </div>

</div>


</body>
</html>
