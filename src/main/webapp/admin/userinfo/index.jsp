<%--
  Created by IntelliJ IDEA.
  User: 26053
  Date: 2020/5/27
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
</head>
<body>
    <table border="1" width="100%">
        <thead>
            <tr>
                <td>uid</td>
                <td>uname</td>
                <td>upass</td>
                <td>role</td>
                <td>状态</td>
                <td>修改</td>
            </tr>
        </thead>
        <tbody id="tableContent">

        </tbody>
    </table>
    <button type="button" onclick="addUserinfo()">增加</button>
</body>
</html>
<script>
    $(function(){
        findAll();
    })
    function findAll(){
        $.getJSON("findAll",{},function (json) {
            $.each(json, function(i,item){
                let state = "已删除";
                if(item.isdelete == null || item.isdelete == true){
                    state = "正常";
                }

                let rolenames = "";
                $.each(item.roles, function(i,it){
                    rolenames += it.rolename;
                    if(i < item.roles.length-1)
                        rolenames +=","
                });

                $("#tableContent").append("<tr><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.upass+"</td><td>"+rolenames+"</td><td>"+state+"</td><td><a href='addUserinfo.jsp?uid="+item.uid+"'>修改</a></td></tr>");
            });
        })
    }
    function addUserinfo() {
        window.location.href = "addUserinfo.jsp";
    }
</script>
