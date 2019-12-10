<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 上午 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jigsaw.css">
    <%-- <link href="/ico/favicon.ico" rel="shortcut icon">--%>
    <%-- //网站显示页logo图标--%>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/ico/favicon.ico">
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script>
        function checkAll() {
            var username = $('#username').val();
            var password = $('#password').val();
            if ("" == username || "" == password) {
                alert("您的输入有误！");
                return;
            }
        }
    </script>
    <style type="text/css">
        body {
            background-color: #E0E4E8;
        }

        #login-div {
            border-radius: 25px;
            margin-left: 500px;
            margin-top: 200px;
            padding-top: 10px;
            width: 400px;
            height: 250px;
            opacity: 0.8;
            background-color: #FFFFFF;
        }

        .container {
            width: 310px;
            margin: 100px auto;
        }

        input {
            display: block;
            width: 290px;
            line-height: 40px;
            margin: 10px 0;
            padding: 0 10px;
            outline: none;
            border: 1px solid #c8cccf;
            border-radius: 4px;
            color: #6a6f77;
        }

        #msg {
            width: 100%;
            line-height: 40px;
            font-size: 14px;
            text-align: center;
        }

        a:link, a:visited, a:hover, a:active {
            margin-left: 100px;
            color: #0366D6;
        }


    </style>
    <%-- 收藏用logo图标--%>
    <%--<link rel="bookmark"  type="image/x-icon"  href="${contextPage.request.contextPath}/ico/favicon.ico"/>--%>

</head>
<body>
<%--<div id="main">

    <div id="login-div">
        <!-- 登录表单 -->
        <form action="/loginController/login.action" method="post" style="margin-left:50px;margin-top:40px;">
            <div class="form-group" >
                <label for="username" stype="display:inline;margin-top:10px">账户：</label>
                <input type="text" class="form-control" id="username" name="userName"
                       style="display:inline;width:200px;"
                       autocomplete="off"/>

            </div>
            <div class="form-group">
                <label for="password" style="display:inline;">密码：</label>
                <input type="text" class="form-control" id="password" name="password"
                       style="display:inline;width:200px;"
                       autocomplete="off"/>
            </div>
            <div class="form-group">
                <label  style="display:block;">
                    <marquee direction="left"><span style="color: red">${error}</span></marquee>
                </label>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" onclick="checkAll()">登录</button>
                <button type="submit" class="btn btn-primary">注册</button>
            </div>

        </form>

    </div>


</div>--%>

<div class="container">
    <form action="/loginController/login.action" method="post" id="loginForm">
        用户名：<input id="userName" name="userName"/>
        密 码：<input id="password" type="password" name="password"/>
        <div id="captcha" style="position: relative"></div>
        <div id="msg">
            <marquee direction="left"><span style="color: red">${error}</span></marquee>
        </div>
        <input type="submit" value="提交" id="submit" style="display: none">
    </form>


</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jigsaw.js"></script>
<script type="text/javascript">
    jigsaw.init(document.getElementById('captcha'), function () {
        //document.getElementById('msg').innerHTML = '登录成功！'

        /*$.post(
            "/loginController/login.action",
            {
                userName: $("#userName").val(),
                password: $("#password").val()
            }

        );*/
        $("#submit").click();

        // window.location.href="/loginController/login.action?userName=" + $("#userName").val() +"&password=" +$("#password").val() ;
    })
</script>


</body>
</html>
