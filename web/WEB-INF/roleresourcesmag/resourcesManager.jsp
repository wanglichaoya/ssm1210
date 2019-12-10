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
    <title>资源管理页面</title>
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.7.0/themes/icon.css">
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>

    <script>
        $(function () {
            $('#tt').treegrid({
                url: '/RoleRes/loadMenuItem.action',
                //url: '../../json/treegrid_data1.json',
                method: 'get',
                rownumbers: true,
                idField: 'mId',
                checkbox: true,
                treeField: 'text',

                columns: [[
                    {field: 'text', title: '名称', width: 180},
                    {field: 'state', title: '数据大小', width: 60, align: 'right'},
                    {field: 'url', title: '日期', width: 360}
                ]]
            });
        });

        /* $(function(){
             $('#tt').treegrid({
                 //url: '/RoleRes/loadMenuItem.action',
                 url: '../../json/treegrid_data1.json',
                 method: 'get',
                 rownumbers: true,
                 idField: 'id',
                 treeField: 'name',
                 columns:[[
                     {field:'name',title:'名称',width:180},
                     {field:'size',title:'数据大小',width:60,align:'right'},
                     {field:'date',title:'日期',width:80}
                 ]]
             });
         });*/
    </script>
</head>

<body>
<table id="tt" class="easyui-treegrid" style="width:auto;height:auto">

</table>


</body>
</html>
